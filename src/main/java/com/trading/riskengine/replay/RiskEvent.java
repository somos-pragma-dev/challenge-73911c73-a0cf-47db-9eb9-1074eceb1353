package com.trading.riskengine.replay;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RiskEvent {
    private long sequence;
    private Instant timestamp;
    private String eventType;
    private int instrumentId;
    private int traderId;
    private int strategyId;
    private long orderValue;
    private String decision;
    private String reason;
    private double volatility;
    private Map<String, Object> metadata;
    
    public RiskEvent() {
        this.metadata = new ConcurrentHashMap<>();
    }
    
    public RiskEvent(String eventType, int instrumentId, int traderId, int strategyId, 
                     long orderValue, String decision, String reason) {
        this();
        this.eventType = eventType;
        this.instrumentId = instrumentId;
        this.traderId = traderId;
        this.strategyId = strategyId;
        this.orderValue = orderValue;
        this.decision = decision;
        this.reason = reason;
    }
    
    public long getSequence() { return sequence; }
    public void setSequence(long sequence) { this.sequence = sequence; }
    
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
    
    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    
    public int getInstrumentId() { return instrumentId; }
    public void setInstrumentId(int instrumentId) { this.instrumentId = instrumentId; }
    
    public int getTraderId() { return traderId; }
    public void setTraderId(int traderId) { this.traderId = traderId; }
    
    public int getStrategyId() { return strategyId; }
    public void setStrategyId(int strategyId) { this.strategyId = strategyId; }
    
    public long getOrderValue() { return orderValue; }
    public void setOrderValue(long orderValue) { this.orderValue = orderValue; }
    
    public String getDecision() { return decision; }
    public void setDecision(String decision) { this.decision = decision; }
    
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    
    public double getVolatility() { return volatility; }
    public void setVolatility(double volatility) { this.volatility = volatility; }
    
    public Map<String, Object> getMetadata() { return metadata; }
    public void setMetadata(Map<String, Object> metadata) { this.metadata = metadata; }
    
    public void addMetadata(String key, Object value) {
        this.metadata.put(key, value);
    }
}