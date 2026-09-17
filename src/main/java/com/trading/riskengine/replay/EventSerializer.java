package com.trading.riskengine.replay;

import net.openhft.chronicle.wire.Wire;
import net.openhft.chronicle.wire.WireIn;
import java.time.Instant;

public class EventSerializer {
    
    public void writeEvent(Wire wire, RiskEvent event) {
        wire.write("seq").int64(event.getSequence());
        wire.write("ts").int64(event.getTimestamp().toEpochMilli());
        wire.write("type").text(event.getEventType());
        wire.write("inst").int32(event.getInstrumentId());
        wire.write("trader").int32(event.getTraderId());
        wire.write("strat").int32(event.getStrategyId());
        wire.write("val").int64(event.getOrderValue());
        wire.write("dec").text(event.getDecision());
        wire.write("rsn").text(event.getReason());
        wire.write("vol").float64(event.getVolatility());
    }
    
    public RiskEvent readEvent(Wire wire) {
        RiskEvent event = new RiskEvent();
        event.setSequence(wire.read("seq").int64());
        long epochMilli = wire.read("ts").int64();
        event.setTimestamp(Instant.ofEpochMilli(epochMilli));
        event.setEventType(wire.read("type").text());
        event.setInstrumentId(wire.read("inst").int32());
        event.setTraderId(wire.read("trader").int32());
        event.setStrategyId(wire.read("strat").int32());
        event.setOrderValue(wire.read("val").int64());
        event.setDecision(wire.read("dec").text());
        event.setReason(wire.read("rsn").text());
        event.setVolatility(wire.read("vol").float64());
        return event;
    }
    
    public void writeAuditRecord(Wire wire, AuditRecord record) {
        wire.write("seq").int64(record.sequence());
        wire.write("ts").int64(record.timestamp().toEpochMilli());
        wire.write("type").text(record.eventType());
        wire.write("inst").int32(record.instrumentId());
        wire.write("trader").int32(record.traderId());
        wire.write("strat").int32(record.strategyId());
        wire.write("val").int64(record.orderValue());
        wire.write("dec").text(record.decision());
        wire.write("rsn").text(record.reason());
    }
    
    public AuditRecord readAuditRecord(Wire wire) {
        long seq = wire.read("seq").int64();
        long epochMilli = wire.read("ts").int64();
        Instant ts = Instant.ofEpochMilli(epochMilli);
        String type = wire.read("type").text();
        int inst = wire.read("inst").int32();
        int trader = wire.read("trader").int32();
        int strat = wire.read("strat").int32();
        long val = wire.read("val").int64();
        String dec = wire.read("dec").text();
        String rsn = wire.read("rsn").text();
        return new AuditRecord(seq, ts, type, inst, trader, strat, val, dec, rsn);
    }
}