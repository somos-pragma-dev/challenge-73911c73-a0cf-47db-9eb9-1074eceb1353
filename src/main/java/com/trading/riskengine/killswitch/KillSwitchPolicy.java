package com.trading.riskengine.killswitch;

import com.trading.riskengine.model.VaRModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class KillSwitchPolicy {
    private static final Logger log = LoggerFactory.getLogger(KillSwitchPolicy.class);
    private static final int DEFAULT_MAX_ORDERS_PER_SECOND = 100;
    private static final int DEFAULT_MAX_NOTIONAL_PER_SECOND = 10_000_000;
    private static final double DEFAULT_MAX_VOLATILITY = 0.50;
    private static final double VOLATILITY_SCALING_FACTOR = 2.0;
    private static final int WARMUP_PERIOD_SECONDS = 5;
    private static final double ANOMALY_DETECTION_THRESHOLD = 3.0;
    private static final int ANOMALY_WINDOW_SIZE = 10;

    private final Map<Integer, TraderPolicy> traderPolicies;
    private final Map<Integer, StrategyPolicy> strategyPolicies;
    private final AtomicInteger globalActiveTraders;
    private final AtomicLong globalOrderCount;
    private final Instant systemStartTime;
    private volatile boolean globalKillSwitchActive;
    private final AnomalyDetector anomalyDetector;
    private final VaRModel varModel;
    private final Consumer<Integer> emergencyCallback;
    private final Map<Integer, TriggerReason> triggerReasons;

    public KillSwitchPolicy() {
        this.traderPolicies = new ConcurrentHashMap<>();
        this.strategyPolicies = new ConcurrentHashMap<>();
        this.globalActiveTraders = new AtomicInteger(0);
        this.globalOrderCount = new AtomicLong(0);
        this.systemStartTime = Instant.now();
        this.anomalyDetector = new AnomalyDetector();
        this.varModel = null;
        this.emergencyCallback = null;
        this.triggerReasons = new ConcurrentHashMap<>();
    }

    public KillSwitchPolicy(VaRModel varModel, AtomicInteger emergencyCallCount) {
        this.traderPolicies = new ConcurrentHashMap<>();
        this.strategyPolicies = new ConcurrentHashMap<>();
        this.globalActiveTraders = new AtomicInteger(0);
        this.globalOrderCount = new AtomicLong(0);
        this.systemStartTime = Instant.now();
        this.anomalyDetector = new AnomalyDetector();
        this.varModel = varModel;
        this.emergencyCallback = id -> emergencyCallCount.incrementAndGet();
        this.triggerReasons = new ConcurrentHashMap<>();
    }

    public boolean canSubmitOrder(int traderId, int strategyId, long orderValue, double currentVolatility) {
        if (globalKillSwitchActive) {
            return false;
        }
        if (isSystemInWarmup()) {
            return true;
        }
        TraderPolicy traderPolicy = traderPolicies.get(traderId);
        if (traderPolicy != null && traderPolicy.killSwitchActive) {
            return false;
        }
        StrategyPolicy strategyPolicy = strategyPolicies.get(strategyId);
        if (strategyPolicy != null && strategyPolicy.killSwitchActive) {
            return false;
        }
        if (currentVolatility > DEFAULT_MAX_VOLATILITY) {
            return false;
        }
        return true;
    }

    public boolean canTrade(int traderId) {
        TraderPolicy policy = traderPolicies.get(traderId);
        return policy == null || !policy.killSwitchActive;
    }

    private boolean isSystemInWarmup() {
        long elapsed = System.currentTimeMillis() - systemStartTime.toEpochMilli();
        return elapsed < WARMUP_PERIOD_SECONDS * 1000;
    }

    private TraderPolicy getOrCreateTraderPolicy(int traderId) {
        return traderPolicies.computeIfAbsent(traderId, id -> new TraderPolicy());
    }

    private StrategyPolicy getOrCreateStrategyPolicy(int strategyId) {
        return strategyPolicies.computeIfAbsent(strategyId, id -> new StrategyPolicy());
    }

    private void activateTraderKillSwitch(int traderId, String reason) {
        TraderPolicy policy = getOrCreateTraderPolicy(traderId);
        policy.killSwitchActive = true;
        policy.triggerReason = reason;
        log.warn("Kill switch activated for trader {}: {}", traderId, reason);
        if (emergencyCallback != null) {
            emergencyCallback.accept(traderId);
        }
    }

    private void activateStrategyKillSwitch(int strategyId, String reason) {
        StrategyPolicy policy = getOrCreateStrategyPolicy(strategyId);
        policy.killSwitchActive = true;
        policy.triggerReason = reason;
        log.warn("Kill switch activated for strategy {}: {}", strategyId, reason);
    }

    public void trigger(int traderId, TriggerReason reason) {
        triggerReasons.put(traderId, reason);
        activateTraderKillSwitch(traderId, reason.name());
    }

    public boolean isActive(int traderId) {
        TraderPolicy policy = traderPolicies.get(traderId);
        return policy != null && policy.killSwitchActive;
    }

    public void reset(int traderId) {
        TraderPolicy policy = traderPolicies.get(traderId);
        if (policy != null) {
            policy.killSwitchActive = false;
            policy.triggerReason = null;
            policy.orderCount.set(0);
            policy.notionalTotal.set(0);
        }
        triggerReasons.remove(traderId);
    }

    public TriggerReason getTriggerReason(int traderId) {
        return triggerReasons.get(traderId);
    }

    public void activateGlobalKillSwitch(String reason) {
        globalKillSwitchActive = true;
        log.error("Global kill switch activated: {}", reason);
    }

    public void deactivateGlobalKillSwitch() {
        globalKillSwitchActive = false;
        log.info("Global kill switch deactivated");
    }

    public boolean isGlobalKillSwitchActive() {
        return globalKillSwitchActive;
    }

    public void resetTraderPolicy(int traderId) {
        reset(traderId);
    }

    public KillSwitchStatus getStatus(int traderId, int strategyId) {
        TraderPolicy traderPolicy = traderPolicies.get(traderId);
        StrategyPolicy strategyPolicy = strategyPolicies.get(strategyId);
        boolean traderActive = traderPolicy != null && traderPolicy.killSwitchActive;
        boolean strategyActive = strategyPolicy != null && strategyPolicy.killSwitchActive;
        return new KillSwitchStatus(traderActive, strategyActive, globalKillSwitchActive);
    }

    public boolean detectAnomaly(int traderId, long[] latencies) {
        return anomalyDetector.detectLatencyAnomaly(latencies);
    }

    public boolean detectExcessiveOrderRate(int traderId, int orderCount, long timeWindowMs) {
        if (timeWindowMs <= 0) {
            return false;
        }
        double ordersPerSecond = (orderCount * 1000.0) / timeWindowMs;
        return ordersPerSecond > DEFAULT_MAX_ORDERS_PER_SECOND * VOLATILITY_SCALING_FACTOR;
    }

    public boolean checkPositionLimitViolation(int traderId, long limit) {
        if (varModel == null) {
            return false;
        }
        long exposure = varModel.getTraderExposure(traderId);
        return exposure > limit;
    }

    public enum TriggerReason {
        LATENCY_SPIKE,
        EXCESSIVE_LOSS,
        CIRCUIT_BREAKER_OPEN,
        VIOLATION,
        VOLATILITY_SPIKE
    }

    private static class TraderPolicy {
        volatile boolean killSwitchActive = false;
        String triggerReason;
        AtomicLong orderCount = new AtomicLong(0);
        AtomicLong notionalTotal = new AtomicLong(0);
    }

    private static class StrategyPolicy {
        volatile boolean killSwitchActive = false;
        String triggerReason;
        AtomicLong orderCount = new AtomicLong(0);
        AtomicLong notionalTotal = new AtomicLong(0);
    }

    private static class AnomalyDetector {
        public boolean detectLatencyAnomaly(long[] values) {
            if (values == null || values.length < ANOMALY_WINDOW_SIZE) {
                return false;
            }
            double mean = calculateMean(values);
            double stdDev = calculateStdDev(values, mean);
            double lastValue = values[values.length - 1];
            return Math.abs(lastValue - mean) > ANOMALY_DETECTION_THRESHOLD * stdDev;
        }

        private double calculateMean(long[] values) {
            double sum = 0;
            for (long v : values) {
                sum += v;
            }
            return sum / values.length;
        }

        private double calculateStdDev(long[] values, double mean) {
            double sumSquaredDiff = 0;
            for (long v : values) {
                double diff = v - mean;
                sumSquaredDiff += diff * diff;
            }
            return Math.sqrt(sumSquaredDiff / values.length);
        }
    }

    private static class MovingStats {
        private final int windowSize;
        private final double[] values;
        private int index = 0;
        private int count = 0;
        private double sum = 0;

        MovingStats(int windowSize) {
            this.windowSize = windowSize;
            this.values = new double[windowSize];
        }

        void add(double value) {
            if (count > 0) {
                sum -= values[index];
            }
            values[index] = value;
            sum += value;
            index = (index + 1) % windowSize;
            count = Math.min(count + 1, windowSize);
        }

        double mean() {
            return count > 0 ? sum / count : 0;
        }
    }

    public record KillSwitchStatus(
        boolean traderKillSwitchActive,
        boolean strategyKillSwitchActive,
        boolean globalKillSwitchActive
    ) {}
}