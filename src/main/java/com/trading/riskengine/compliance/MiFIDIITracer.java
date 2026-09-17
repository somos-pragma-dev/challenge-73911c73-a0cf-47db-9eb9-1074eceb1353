package com.trading.riskengine.compliance;

import com.trading.riskengine.replay.AuditRecord;
import net.openhft.chronicle.queue.ChronicleQueue;
import net.openhft.chronicle.queue.ExcerptAppender;
import net.openhft.chronicle.queue.ExcerptReader;
import net.openhft.chronicle.wire.DocumentContext;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MiFIDIITracer {
    private static final DateTimeFormatter TIMESTAMP_FORMAT = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("UTC"));
    
    private final ChronicleQueue auditQueue;
    private final ConcurrentHashMap<String, RiskDecision> activeDecisions;
    private final AtomicLong decisionCounter;
    private final String complianceEndpoint;
    private final boolean synchronousWrite;
    
    public MiFIDIITracer(String queuePath, boolean synchronousWrite) {
        this.auditQueue = ChronicleQueue.singleBuilder(queuePath)
            .blockSize(256 << 10)
            .writeBufferMode(ChronicleQueue.WriteBufferMode.asynchronous)
            .build();
        this.activeDecisions = new ConcurrentHashMap<>();
        this.decisionCounter = new AtomicLong(0);
        this.synchronousWrite = synchronousWrite;
        this.complianceEndpoint = "https://compliance.trading.internal/audit/v2";
    }
    
    public String recordRiskDecision(RiskDecision decision) {
        String decisionId = generateDecisionId(decision);
        decision.setDecisionId(decisionId);
        decision.setTimestamp(Instant.now().toString());
        decision.setComplianceRequirement("MiFIDII-2018-ART27");
        
        activeDecisions.put(decisionId, decision);
        
        try {
            appendToQueue(decision);
            if (synchronousWrite) {
                auditQueue.sync();
            }
        } catch (Exception e) {
            decision.setRecordingStatus(RecordingStatus.FAILED);
            decision.setFailureReason("Queue write error: " + e.getMessage());
        }
        
        return decisionId;
    }
    
    private void appendToQueue(RiskDecision decision) {
        ExcerptAppender appender = auditQueue.acquireAppender();
        try (DocumentContext ctx = appender.writingDocument()) {
            ctx.wire().write("decisionId").text(decision.getDecisionId());
            ctx.wire().write("timestamp").text(decision.getTimestamp());
            ctx.wire().write("traderId").int32(decision.getTraderId());
            ctx.wire().write("strategyId").int32(decision.getStrategyId());
            ctx.wire().write("instrumentId").text(decision.getInstrumentId());
            ctx.wire().write("orderId").text(decision.getOrderId());
            ctx.wire().write("decision").text(decision.getDecision().name());
            ctx.wire().write("riskScore").double64(decision.getRiskScore());
            ctx.wire().write("exposure").int64(decision.getExposure());
            ctx.wire().write("varLimit").int64(decision.getVarLimit());
            ctx.wire().write("volatility").double64(decision.getVolatility());
            ctx.wire().write("circuitBreakerState").text(decision.getCircuitBreakerState());
            ctx.wire().write("complianceRequirement").text(decision.getComplianceRequirement());
            ctx.wire().write("justification").text(decision.getJustification());
            ctx.wire().write("replayToken").text(decision.getReplayToken());
        }
        decision.setRecordingStatus(RecordingStatus.RECORDED);
    }
    
    private String generateDecisionId(RiskDecision decision) {
        long sequence = decisionCounter.incrementAndGet();
        String uniquePart = UUID.randomUUID().toString().substring(0, 8);
        return String.format("DEC-%d-%s-%d", 
            System.currentTimeMillis(), uniquePart, sequence);
    }
    
    public RiskDecision getDecision(String decisionId) {
        RiskDecision cached = activeDecisions.get(decisionId);
        if (cached != null) {
            return cached;
        }
        return searchHistorical(decisionId);
    }
    
    private RiskDecision searchHistorical(String decisionId) {
        ExcerptReader reader = auditQueue.createTailer();
        while (reader.readDocument() != null) {
            String id = reader.document().wire().read("decisionId").text();
            if (decisionId.equals(id)) {
                return mapToRiskDecision(reader.document().wire());
            }
        }
        return null;
    }
    
    private RiskDecision mapToRiskDecision(net.openhft.chronicle.wire.WireIn wire) {
        RiskDecision decision = new RiskDecision();
        decision.setDecisionId(wire.read("decisionId").text());
        decision.setTimestamp(wire.read("timestamp").text());
        decision.setTraderId(wire.read("traderId").int32());
        decision.setStrategyId(wire.read("strategyId").int32());
        decision.setInstrumentId(wire.read("instrumentId").text());
        decision.setOrderId(wire.read("orderId").text());
        decision.setDecision(RiskDecisionType.valueOf(wire.read("decision").text()));
        decision.setRiskScore(wire.read("riskScore").double64());
        decision.setExposure(wire.read("exposure").int64());
        decision.setVarLimit(wire.read("varLimit").int64());
        decision.setVolatility(wire.read("volatility").double64());
        decision.setCircuitBreakerState(wire.read("circuitBreakerState").text());
        decision.setComplianceRequirement(wire.read("complianceRequirement").text());
        decision.setJustification(wire.read("justification").text());
        decision.setReplayToken(wire.read("replayToken").text());
        decision.setRecordingStatus(RecordingStatus.RECORDED);
        return decision;
    }
    
    public void recordCircuitBreakerEvent(String instrumentId, String state, String reason) {
        RiskDecision event = new RiskDecision();
        event.setInstrumentId(instrumentId);
        event.setDecision(RiskDecisionType.CIRCUIT_BREAKER_TRIGGERED);
        event.setCircuitBreakerState(state);
        event.setJustification(reason);
        event.setComplianceRequirement("MiFIDII-2018-ART27-4");
        recordRiskDecision(event);
    }
    
    public void recordKillSwitchEvent(String strategyId, String trigger, String details) {
        RiskDecision event = new RiskDecision();
        event.setStrategyId(Integer.parseInt(strategyId.replaceAll("[^0-9]", "")));
        event.setDecision(RiskDecisionType.KILL_SWITCH_ACTIVATED);
        event.setJustification("Trigger: " + trigger + ". Details: " + details);
        event.setComplianceRequirement("MiFIDII-2018-ART27-5");
        recordRiskDecision(event);
    }
    
    public void recordVaRLimitBreach(int traderId, int strategyId, long exposure, long varLimit) {
        RiskDecision event = new RiskDecision();
        event.setTraderId(traderId);
        event.setStrategyId(strategyId);
        event.setExposure(exposure);
        event.setVarLimit(varLimit);
        event.setDecision(RiskDecisionType.VAR_LIMIT_EXCEEDED);
        event.setJustification(String.format("Exposure %d exceeds VaR limit %d (%.2f%%)", 
            exposure, varLimit, ((double)(exposure - varLimit) / varLimit) * 100));
        event.setComplianceRequirement("MiFIDII-2018-ART27-2");
        recordRiskDecision(event);
    }
    
    public String generateAuditReport(String startTime, String endTime) {
        StringBuilder report = new StringBuilder();
        report.append("MiFID II Audit Report\n");
        report.append("Period: ").append(startTime).append(" to ").append(endTime).append("\n");
        report.append("Generated: ").append(Instant.now()).append("\n");
        report.append("================================================================================\n");
        
        long totalDecisions = 0;
        long approvedOrders = 0;
        long rejectedOrders = 0;
        long circuitBreakerEvents = 0;
        long killSwitchEvents = 0;
        
        ExcerptReader reader = auditQueue.createTailer();
        while (reader.readDocument() != null) {
            String timestamp = reader.document().wire().read("timestamp").text();
            if (timestamp.compareTo(startTime) >= 0 && timestamp.compareTo(endTime) <= 0) {
                totalDecisions++;
                String decision = reader.document().wire().read("decision").text();
                switch (decision) {
                    case "APPROVED" -> approvedOrders++;
                    case "REJECTED" -> rejectedOrders++;
                    case "CIRCUIT_BREAKER_TRIGGERED" -> circuitBreakerEvents++;
                    case "KILL_SWITCH_ACTIVATED" -> killSwitchEvents++;
                }
            }
        }
        
        report.append(String.format("Total Risk Decisions: %d\n", totalDecisions));
        report.append(String.format("Approved Orders: %d\n", approvedOrders));
        report.append(String.format("Rejected Orders: %d\n", rejectedOrders));
        report.append(String.format("Circuit Breaker Events: %d\n", circuitBreakerEvents));
        report.append(String.format("Kill Switch Events: %d\n", killSwitchEvents));
        
        return report.toString();
    }
    
    public void close() {
        auditQueue.close();
    }
    
    public enum RiskDecisionType {
        APPROVED,
        REJECTED,
        VAR_LIMIT_EXCEEDED,
        CIRCUIT_BREAKER_TRIGGERED,
        KILL_SWITCH_ACTIVATED,
        VOLATILITY_THRESHOLD_BREACH,
        POSITION_LIMIT_EXCEEDED
    }
    
    public enum RecordingStatus {
        PENDING,
        RECORDED,
        FAILED,
        REPLAYED
    }
    
    public static class RiskDecision {
        private String decisionId;
        private String timestamp;
        private int traderId;
        private int strategyId;
        private String instrumentId;
        private String orderId;
        private RiskDecisionType decision;
        private double riskScore;
        private long exposure;
        private long varLimit;
        private double volatility;
        private String circuitBreakerState;
        private String complianceRequirement;
        private String justification;
        private String replayToken;
        private RecordingStatus recordingStatus;
        private String failureReason;
        
        public RiskDecision() {
            this.recordingStatus = RecordingStatus.PENDING;
        }
        
        public String getDecisionId() { return decisionId; }
        public void setDecisionId(String decisionId) { this.decisionId = decisionId; }
        public String getTimestamp() { return timestamp; }
        public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
        public int getTraderId() { return traderId; }
        public void setTraderId(int traderId) { this.traderId = traderId; }
        public int getStrategyId() { return strategyId; }
        public void setStrategyId(int strategyId) { this.strategyId = strategyId; }
        public String getInstrumentId() { return instrumentId; }
        public void setInstrumentId(String instrumentId) { this.instrumentId = instrumentId; }
        public String getOrderId() { return orderId; }
        public void setOrderId(String orderId) { this.orderId = orderId; }
        public RiskDecisionType getDecision() { return decision; }
        public void setDecision(RiskDecisionType decision) { this.decision = decision; }
        public double getRiskScore() { return riskScore; }
        public void setRiskScore(double riskScore) { this.riskScore = riskScore; }
        public long getExposure() { return exposure; }
        public void setExposure(long exposure) { this.exposure = exposure; }
        public long getVarLimit() { return varLimit; }
        public void setVarLimit(long varLimit) { this.varLimit = varLimit; }
        public double getVolatility() { return volatility; }
        public void setVolatility(double volatility) { this.volatility = volatility; }
        public String getCircuitBreakerState() { return circuitBreakerState; }
        public void setCircuitBreakerState(String circuitBreakerState) { this.circuitBreakerState = circuitBreakerState; }
        public String getComplianceRequirement() { return complianceRequirement; }
        public void setComplianceRequirement(String complianceRequirement) { this.complianceRequirement = complianceRequirement; }
        public String getJustification() { return justification; }
        public void setJustification(String justification) { this.justification = justification; }
        public String getReplayToken() { return replayToken; }
        public void setReplayToken(String replayToken) { this.replayToken = replayToken; }
        public RecordingStatus getRecordingStatus() { return recordingStatus; }
        public void setRecordingStatus(RecordingStatus recordingStatus) { this.recordingStatus = recordingStatus; }
        public String getFailureReason() { return failureReason; }
        public void setFailureReason(String failureReason) { this.failureReason = failureReason; }
    }
}