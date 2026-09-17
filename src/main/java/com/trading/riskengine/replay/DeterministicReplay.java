package com.trading.riskengine.replay;

import net.openhft.chronicle.queue.ChronicleQueue;
import net.openhft.chronicle.queue.ExcerptAppender;
import net.openhft.chronicle.queue.ExcerptTailer;
import net.openhft.chronicle.queue.RollCycle;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueueBuilder;
import net.openhft.chronicle.wire.DocumentContext;
import net.openhft.chronicle.wire.Wire;
import net.openhft.chronicle.wire.WireType;
import net.openhft.chronicle.bytes.Bytes;
import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeterministicReplay implements Closeable {
    private static final Logger log = LoggerFactory.getLogger(DeterministicReplay.class);
    private static final int DEFAULT_QUEUE_SIZE = 64 * 1024;
    private static final RollCycle DEFAULT_ROLL_CYCLE = RollCycle.DAILY;
    private static final String REPLAY_CHANNEL = "risk-events";
    private static final String AUDIT_CHANNEL = "audit-trail";
    
    private final Path eventLogPath;
    private final ChronicleQueue eventQueue;
    private final ChronicleQueue auditQueue;
    private final EventSerializer serializer;
    private final AtomicLong eventSequence;
    private final AtomicLong lastReplayPosition;
    private final ConcurrentHashMap<String, EventProcessor> eventProcessors;
    private volatile ReplayState currentState;
    
    public DeterministicReplay(Path eventLogPath) {
        this.eventLogPath = eventLogPath;
        this.serializer = new EventSerializer();
        this.eventSequence = new AtomicLong(0);
        this.lastReplayPosition = new AtomicLong(0);
        this.eventProcessors = new ConcurrentHashMap<>();
        this.eventQueue = createQueue(eventLogPath, REPLAY_CHANNEL);
        this.auditQueue = createQueue(eventLogPath.resolve("../audit"), AUDIT_CHANNEL);
        this.currentState = ReplayState.IDLE;
    }
    
    private ChronicleQueue createQueue(Path path, String channel) {
        return SingleChronicleQueueBuilder
            .builder(path.resolve(channel), WireType.FIELDLESS_BINARY)
            .rollCycle(DEFAULT_ROLL_CYCLE)
            .build();
    }
    
    public void recordEvent(RiskEvent event) {
        if (currentState == ReplayState.REPLAYING) {
            throw new IllegalStateException("Cannot record events during replay");
        }
        long sequence = eventSequence.incrementAndGet();
        event.setSequence(sequence);
        event.setTimestamp(Instant.now());
        try (ExcerptAppender appender = eventQueue.acquireAppender()) {
            appender.writeDocument(wire -> serializer.writeEvent(wire, event));
        }
        recordAuditEvent(event);
        log.debug("Recorded event {} with sequence {}", event.getEventType(), sequence);
    }
    
    private void recordAuditEvent(RiskEvent event) {
        AuditRecord audit = new AuditRecord(
            event.getSequence(),
            event.getTimestamp(),
            event.getEventType(),
            event.getInstrumentId(),
            event.getTraderId(),
            event.getStrategyId(),
            event.getOrderValue(),
            event.getDecision(),
            event.getReason()
        );
        try (ExcerptAppender appender = auditQueue.acquireAppender()) {
            appender.writeDocument(wire -> serializer.writeAuditRecord(wire, audit));
        }
    }
    
    public void replay(Consumer<RiskEvent> eventHandler) {
        if (currentState == ReplayState.REPLAYING) {
            throw new IllegalStateException("Replay already in progress");
        }
        currentState = ReplayState.REPLAYING;
        log.info("Starting deterministic replay from position {}", lastReplayPosition.get());
        try (ExcerptTailer tailer = eventQueue.createTailer()) {
            tailer.moveToIndex(lastReplayPosition.get());
            long processedCount = 0;
            while (true) {
                try (DocumentContext ctx = tailer.readingDocument()) {
                    if (!ctx.isPresent()) {
                        break;
                    }
                    Wire wire = ctx.wire();
                    RiskEvent event = serializer.readEvent(wire);
                    processEvent(event, eventHandler);
                    processedCount++;
                    lastReplayPosition.set(ctx.index());
                }
            }
            log.info("Replay completed. Processed {} events. Final position: {}", 
                     processedCount, lastReplayPosition.get());
        } catch (Exception e) {
            log.error("Error during replay", e);
            throw new RuntimeException("Replay failed", e);
        } finally {
            currentState = ReplayState.IDLE;
        }
    }
    
    private void processEvent(RiskEvent event, Consumer<RiskEvent> handler) {
        String processorKey = event.getEventType();
        EventProcessor processor = eventProcessors.get(processorKey);
        if (processor != null) {
            processor.process(event);
        }
        handler.accept(event);
    }
    
    public void registerProcessor(String eventType, EventProcessor processor) {
        eventProcessors.put(eventType, processor);
    }
    
    public void replayFromTimestamp(Instant fromTimestamp, Consumer<RiskEvent> eventHandler) {
        currentState = ReplayState.REPLAYING;
        log.info("Starting replay from timestamp {}", fromTimestamp);
        try (ExcerptTailer tailer = eventQueue.createTailer()) {
            long processedCount = 0;
            while (true) {
                try (DocumentContext ctx = tailer.readingDocument()) {
                    if (!ctx.isPresent()) {
                        break;
                    }
                    Wire wire = ctx.wire();
                    RiskEvent event = serializer.readEvent(wire);
                    if (event.getTimestamp().isBefore(fromTimestamp)) {
                        continue;
                    }
                    processEvent(event, eventHandler);
                    processedCount++;
                }
            }
            log.info("Timestamp replay completed. Processed {} events", processedCount);
        } catch (Exception e) {
            log.error("Error during timestamp replay", e);
            throw new RuntimeException("Timestamp replay failed", e);
        } finally {
            currentState = ReplayState.IDLE;
        }
    }
    
    public List<AuditRecord> getAuditTrail(int traderId) {
        List<AuditRecord> records = new ArrayList<>();
        try (ExcerptTailer tailer = auditQueue.createTailer()) {
            while (true) {
                try (DocumentContext ctx = tailer.readingDocument()) {
                    if (!ctx.isPresent()) {
                        break;
                    }
                    AuditRecord record = serializer.readAuditRecord(ctx.wire());
                    if (record.traderId() == traderId) {
                        records.add(record);
                    }
                }
            }
        }
        return records;
    }
    
    public long getEventCount() {
        return eventSequence.get();
    }
    
    public ReplayState getCurrentState() {
        return currentState;
    }
    
    public void resetToBeginning() {
        lastReplayPosition.set(0);
        log.info("Replay position reset to beginning");
    }
    
    @Override
    public void close() throws IOException {
        eventQueue.close();
        auditQueue.close();
        log.info("DeterministicReplay closed. Total events recorded: {}", eventSequence.get());
    }
    
    public enum ReplayState {
        IDLE,
        REPLAYING,
        PAUSED
    }
    
    @FunctionalInterface
    public interface EventProcessor {
        void process(RiskEvent event);
    }
}