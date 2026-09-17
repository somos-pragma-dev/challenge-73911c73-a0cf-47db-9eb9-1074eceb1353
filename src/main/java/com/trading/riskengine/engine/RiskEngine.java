package com.trading.riskengine.engine;

import com.lmax.disruptor.RingBuffer;
import com.trading.riskengine.feed.MarketDataEvent;
import com.trading.riskengine.model.VaRModel;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class RiskEngine {
    private static final double DEFAULT_MAX_VAR = 1_000_000.0;
    private static final int MAX_ORDERS_PER_INSTRUMENT = 1000;
    private static final long CIRCUIT_BREAKER_RESET_TIMEOUT_MS = 30_000;
    private static final double VOLATILITY_THRESHOLD_HIGH = 0.25;
    private static final double VOLATILITY_THRESHOLD_CRITICAL = 0.40;
    
    private final VaRModel varModel;
    private final RingBuffer<MarketDataEvent> ringBuffer;
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final CircuitBreaker[] circuitBreakersByInstrument;
    private final AtomicReference<OrderSubmissionResult> lastResult;
    private final AtomicLong ordersEvaluated;
    private final AtomicLong ordersRejected;
    private final AtomicLong circuitBreakerTriggers;
    private final AtomicBoolean isShutdown;
    private final RiskMetrics metrics;
    private final OrderValidator[] validators;
    
    public RiskEngine(VaRModel varModel, RingBuffer<MarketDataEvent> ringBuffer, int numInstruments) {
        this.varModel = varModel;
        this.ringBuffer = ringBuffer;
        this.circuitBreakerRegistry = createCircuitBreakerRegistry();
        this.circuitBreakersByInstrument = new CircuitBreaker[numInstruments];
        this.lastResult = new AtomicReference<>(OrderSubmissionResult.APPROVED);
        this.ordersEvaluated = new AtomicLong(0);
        this.ordersRejected = new AtomicLong(0);
        this.circuitBreakerTriggers = new AtomicLong(0);
        this.isShutdown = new AtomicBoolean(false);
        this.metrics = new RiskMetrics();
        this.validators = new OrderValidator[0];
        initializeCircuitBreakers(numInstruments);
        initializeValidators(numInstruments);
    }
    
    private void initializeCircuitBreakers(int numInstruments) {
        for (int i = 0; i < numInstruments; i++) {
            circuitBreakersByInstrument[i] = circuitBreakerRegistry.circuitBreaker("instrument-" + i);
        }
    }
    
    private void initializeValidators(int numInstruments) {
    }
    
    private CircuitBreakerRegistry createCircuitBreakerRegistry() {
        return CircuitBreakerRegistry.ofDefaults();
    }
    
    public OrderSubmissionResult evaluateOrder(Order order) {
        if (isShutdown.get()) {
            return OrderSubmissionResult.REJECTED;
        }
        
        ordersEvaluated.incrementAndGet();
        
        int instrumentId = order.instrumentId;
        long orderValue = order.orderValue;
        
        double volatility = varModel.getInstrumentVolatility(instrumentId);
        double maxVaR = calculateDynamicVaRLimit(volatility);
        long varAmount = (long) varModel.calculateVaR(orderValue, instrumentId);
        
        if (varAmount > maxVaR) {
            ordersRejected.incrementAndGet();
            lastResult.set(OrderSubmissionResult.REJECTED);
            return OrderSubmissionResult.REJECTED;
        }
        
        long currentExposure = varModel.getTraderExposure(order.traderId);
        long newExposure = varModel.updateTraderExposure(order.traderId, orderValue);
        
        if (varModel.exceedsVaR(newExposure, instrumentId, maxVaR)) {
            varModel.updateTraderExposure(order.traderId, -orderValue);
            ordersRejected.incrementAndGet();
            lastResult.set(OrderSubmissionResult.REJECTED);
            return OrderSubmissionResult.REJECTED;
        }
        
        lastResult.set(OrderSubmissionResult.APPROVED);
        return OrderSubmissionResult.APPROVED;
    }
    
    // Method needed for RiskEngineLatencyTest
    public RiskResult evaluateRisk(MarketDataEvent event) {
        if (isShutdown.get()) {
            return new RiskResult(false, 0.0, 0, 0.0);
        }
        
        ordersEvaluated.incrementAndGet();
        
        int instrumentId = event.getInstrumentId();
        long orderValue = event.getTradedSize();
        
        double volatility = varModel.getInstrumentVolatility(instrumentId);
        double maxVaR = calculateDynamicVaRLimit(volatility);
        long varAmount = (long) varModel.calculateVaR(orderValue, instrumentId);
        
        boolean approved = varAmount <= maxVaR;
        
        if (!approved) {
            ordersRejected.incrementAndGet();
        }
        
        return new RiskResult(approved, varAmount / maxVaR, varAmount, volatility);
    }
    
    private double calculateDynamicVaRLimit(double volatility) {
        double baseVaR = DEFAULT_MAX_VAR;
        if (volatility > VOLATILITY_THRESHOLD_CRITICAL) {
            return baseVaR * 0.5;
        } else if (volatility > VOLATILITY_THRESHOLD_HIGH) {
            return baseVaR * 0.75;
        }
        return baseVaR;
    }
    
    public void onTradeExecuted(int instrumentId, double price, long size) {
        long notional = (long) (price * size);
        varModel.updateStrategyExposure(0, notional);
    }
    
    public void onMarketImbalance(int instrumentId, long imbalance) {
    }
    
    public void triggerCircuitBreaker(int instrumentId, String reason, double value) {
        circuitBreakerTriggers.incrementAndGet();
    }
    
    private void triggerKillSwitch(int instrumentId, String reason, double value) {
    }
    
    public RiskMetrics getMetrics() { return metrics; }
    public long getOrdersEvaluated() { return ordersEvaluated.get(); }
    public long getOrdersRejected() { return ordersRejected.get(); }
    public long getCircuitBreakerTriggers() { return circuitBreakerTriggers.get(); }
    
    public void reset() {
        ordersEvaluated.set(0);
        ordersRejected.set(0);
        circuitBreakerTriggers.set(0);
    }
    
    public void shutdown() {
        isShutdown.set(true);
    }
    
    public enum OrderSubmissionResult {
        APPROVED,
        REJECTED,
        CIRCUIT_BREAKER_OPEN,
        KILL_SWITCH_ACTIVE
    }
    
    public enum RejectionReason {
        VAR_LIMIT_EXCEEDED,
        POSITION_LIMIT_EXCEEDED,
        CIRCUIT_BREAKER_OPEN,
        KILL_SWITCH_ACTIVE,
        VOLATILITY_TOO_HIGH
    }
    
    public static class Order {
        public int traderId;
        public int strategyId;
        public int instrumentId;
        public long orderValue;
        public String orderType;
        
        public Order(int traderId, int strategyId, int instrumentId, long orderValue) {
            this.traderId = traderId;
            this.strategyId = strategyId;
            this.instrumentId = instrumentId;
            this.orderValue = orderValue;
        }
    }
    
    private static class OrderValidator {
    }
    
    public static class RiskMetrics {
        private volatile long totalEvaluations;
        private volatile long totalRejections;
        private volatile double averageRiskScore;
        
        public void recordEvaluation(boolean approved, double riskScore) {
            totalEvaluations++;
            if (!approved) totalRejections++;
            averageRiskScore = (averageRiskScore * (totalEvaluations - 1) + riskScore) / totalEvaluations;
        }
        
        public long getTotalEvaluations() { return totalEvaluations; }
        public long getTotalRejections() { return totalRejections; }
        public double getAverageRiskScore() { return averageRiskScore; }
    }
    
    // Inner class for evaluateRisk return type
    public static class RiskResult {
        private final boolean approved;
        private final double riskScore;
        private final long varAmount;
        private final double volatility;
        
        public RiskResult(boolean approved, double riskScore, long varAmount, double volatility) {
            this.approved = approved;
            this.riskScore = riskScore;
            this.varAmount = varAmount;
            this.volatility = volatility;
        }
        
        public boolean isApproved() { return approved; }
        public double getRiskScore() { return riskScore; }
        public long getVarAmount() { return varAmount; }
        public double getVolatility() { return volatility; }
    }
}