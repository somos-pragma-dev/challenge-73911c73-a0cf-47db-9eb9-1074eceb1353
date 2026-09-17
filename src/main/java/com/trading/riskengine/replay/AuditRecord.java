package com.trading.riskengine.replay;

import java.time.Instant;

public record AuditRecord(
    long sequence,
    Instant timestamp,
    String eventType,
    int instrumentId,
    int traderId,
    int strategyId,
    long orderValue,
    String decision,
    String reason
) {
    public String toMiFIDIIFormat() {
        return String.format("AUDIT|%d|%s|%s|%d|%d|%d|%d|%s|%s",
            sequence,
            timestamp.toString(),
            eventType,
            instrumentId,
            traderId,
            strategyId,
            orderValue,
            decision,
            reason);
    }
}