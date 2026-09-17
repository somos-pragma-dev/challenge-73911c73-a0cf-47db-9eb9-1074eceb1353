package com.trading.riskengine.circuitbreaker;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class DynamicCircuitBreaker {
    private static final Logger log = LoggerFactory.getLogger(DynamicCircuitBreaker.class);
    private static final double DEFAULT_VOLATILITY_THRESHOLD = 0.30;
    private static final double HIGH_VOLATILITY_THRESHOLD = 0.60;
    private static final int SLIDING_WINDOW_SIZE = 100;
    private static final int MINIMUM_NUMBER_OF_CALLS = 10;
    private static final double FAILURE_RATE_THRESHOLD = 0.50;
    private static final Duration WAIT_DURATION_IN_OPEN_STATE = Duration.ofSeconds(5);
    private static final Duration PERMITTED_CALLS_IN_HALF_OPEN_STATE = Duration.ofSeconds(3);

    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final Map<Integer, CircuitBreaker> circuitBreakersByInstrument;
    private final Map<Integer, AtomicReference<Double>> currentVolatilityByInstrument;
    private final Map<Integer, Double> thresholdsByInstrument;
    private final Map<Integer, AtomicInteger> failureCountByInstrument;
    private final Map<Integer, AtomicInteger> successCountByInstrument;
    private final CircuitBreakerEventListener eventListener;
    private final double baseVolatilityThreshold;
    private final double highVolatilityMultiplier;
    private final int maxFailuresBeforeOpen;

    public DynamicCircuitBreaker() {
        this.circuitBreakerRegistry = createDefaultRegistry();
        this.circuitBreakersByInstrument = new ConcurrentHashMap<>();
        this.currentVolatilityByInstrument = new ConcurrentHashMap<>();
        this.thresholdsByInstrument = new ConcurrentHashMap<>();
        this.failureCountByInstrument = new ConcurrentHashMap<>();
        this.successCountByInstrument = new ConcurrentHashMap<>();
        this.eventListener = new CircuitBreakerEventListener();
        this.baseVolatilityThreshold = DEFAULT_VOLATILITY_THRESHOLD;
        this.highVolatilityMultiplier = HIGH_VOLATILITY_THRESHOLD;
        this.maxFailuresBeforeOpen = 100;
        initializeGlobalThresholds();
    }

    public DynamicCircuitBreaker(Object varModel, double baseThreshold, double highMultiplier, int maxFailures) {
        this.circuitBreakerRegistry = createDefaultRegistry();
        this.circuitBreakersByInstrument = new ConcurrentHashMap<>();
        this.currentVolatilityByInstrument = new ConcurrentHashMap<>();
        this.thresholdsByInstrument = new ConcurrentHashMap<>();
        this.failureCountByInstrument = new ConcurrentHashMap<>();
        this.successCountByInstrument = new ConcurrentHashMap<>();
        this.eventListener = new CircuitBreakerEventListener();
        this.baseVolatilityThreshold = baseThreshold;
        this.highVolatilityMultiplier = highMultiplier;
        this.maxFailuresBeforeOpen = maxFailures;
        initializeGlobalThresholds();
    }

    private CircuitBreakerRegistry createDefaultRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
            .failureRateThreshold(FAILURE_RATE_THRESHOLD)
            .waitDurationInOpenState(WAIT_DURATION_IN_OPEN_STATE)
            .permittedNumberOfCallsInHalfOpenState(PERMITTED_CALLS_IN_HALF_OPEN_STATE.toMillis())
            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
            .minimumNumberOfCalls(MINIMUM_NUMBER_OF_CALLS)
            .build();
        return CircuitBreakerRegistry.of(config);
    }

    private void initializeGlobalThresholds() {
        thresholdsByInstrument.put(-1, baseVolatilityThreshold);
    }

    public boolean canTrade(int instrumentId, long orderValue) {
        return isOrderAllowed(instrumentId, orderValue);
    }

    public boolean isOrderAllowed(int instrumentId, long orderValue) {
        return checkOrderAllowed(instrumentId, orderValue);
    }

    private boolean checkOrderAllowed(int instrumentId, long orderValue) {
        Double threshold = thresholdsByInstrument.getOrDefault(instrumentId, baseVolatilityThreshold);
        AtomicReference<Double> volatilityRef = currentVolatilityByInstrument.get(instrumentId);
        
        if (volatilityRef == null) {
            return true;
        }
        
        double volatility = volatilityRef.get();
        if (volatility > threshold) {
            log.warn("Volatility {} exceeds threshold {} for instrument {}", volatility, threshold, instrumentId);
            return false;
        }
        
        double riskScore = calculateRiskScore(orderValue, volatility);
        return riskScore < 1.0;
    }

    private double getThresholdForVolatility(double volatility) {
        if (volatility < 0.15) {
            return baseVolatilityThreshold;
        } else if (volatility < 0.30) {
            return baseVolatilityThreshold * 0.8;
        } else if (volatility < 0.50) {
            return baseVolatilityThreshold * 0.6;
        } else {
            return baseVolatilityThreshold * 0.4;
        }
    }

    private double calculateRiskScore(long orderValue, double volatility) {
        if (volatility < 0.01) {
            return 0.0;
        }
        return (orderValue / 1_000_000.0) * volatility;
    }

    public void updateVolatility(int instrumentId, double newVolatility) {
        AtomicReference<Double> previous = currentVolatilityByInstrument.computeIfAbsent(
            instrumentId, k -> new AtomicReference<>(0.0));
        double prev = previous.getAndSet(newVolatility);
        adjustThresholdForInstrument(instrumentId, prev, newVolatility);
    }

    private void adjustThresholdForInstrument(int instrumentId, double previous, double current) {
        double newThreshold = getThresholdForVolatility(current);
        thresholdsByInstrument.put(instrumentId, newThreshold);
        log.debug("Adjusted threshold for instrument {} from {} to {}", instrumentId, previous, current);
    }

    private CircuitBreaker getOrCreateCircuitBreaker(int instrumentId) {
        return circuitBreakersByInstrument.computeIfAbsent(instrumentId, this::createCircuitBreaker);
    }

    private CircuitBreaker createCircuitBreaker(int instrumentId) {
        CircuitBreaker cb = circuitBreakerRegistry.circuitBreaker("instrument-" + instrumentId);
        cb.getEventPublisher().onEvent(eventListener);
        return cb;
    }

    public void recordFailure(int instrumentId) {
        AtomicInteger count = failureCountByInstrument.computeIfAbsent(instrumentId, k -> new AtomicInteger(0));
        int current = count.incrementAndGet();
        successCountByInstrument.computeIfAbsent(instrumentId, k -> new AtomicInteger(0)).set(0);
        
        if (current >= maxFailuresBeforeOpen) {
            CircuitBreaker cb = getOrCreateCircuitBreaker(instrumentId);
            cb.transitionToOpenState();
            log.warn("Circuit breaker opened for instrument {} after {} failures", instrumentId, current);
        }
    }

    public void recordSuccess(int instrumentId) {
        AtomicInteger failCount = failureCountByInstrument.get(instrumentId);
        if (failCount != null) {
            failCount.set(0);
        }
        AtomicInteger successCount = successCountByInstrument.computeIfAbsent(instrumentId, k -> new AtomicInteger(0));
        successCount.incrementAndGet();
        
        CircuitBreaker cb = circuitBreakersByInstrument.get(instrumentId);
        if (cb != null && cb.getState() == CircuitBreaker.State.HALF_OPEN) {
            cb.transitionToClosedState();
        }
    }

    public int getFailureCount(int instrumentId) {
        AtomicInteger count = failureCountByInstrument.get(instrumentId);
        return count != null ? count.get() : 0;
    }

    public double getDynamicThreshold(int instrumentId) {
        AtomicReference<Double> volatilityRef = currentVolatilityByInstrument.get(instrumentId);
        double volatility = volatilityRef != null ? volatilityRef.get() : 0.0;
        return getThresholdForVolatility(volatility);
    }

    public CircuitBreakerState getState(int instrumentId) {
        CircuitBreaker cb = circuitBreakersByInstrument.get(instrumentId);
        if (cb == null) {
            return CircuitBreakerState.CLOSED;
        }
        return switch (cb.getState()) {
            case CLOSED -> CircuitBreakerState.CLOSED;
            case OPEN -> CircuitBreakerState.OPEN;
            case HALF_OPEN -> CircuitBreakerState.HALF_OPEN;
        };
    }

    public double getFailureRate(int instrumentId) {
        CircuitBreaker cb = circuitBreakersByInstrument.get(instrumentId);
        if (cb == null) {
            return 0.0;
        }
        return cb.getMetrics().getFailureRate();
    }

    public void resetCircuitBreaker(int instrumentId) {
        failureCountByInstrument.remove(instrumentId);
        successCountByInstrument.remove(instrumentId);
        CircuitBreaker cb = circuitBreakersByInstrument.get(instrumentId);
        if (cb != null) {
            cb.transitionToClosedState();
        }
    }

    public Map<Integer, CircuitBreakerState> getAllStates() {
        Map<Integer, CircuitBreakerState> result = new ConcurrentHashMap<>();
        circuitBreakersByInstrument.forEach((id, cb) -> result.put(id, getState(id)));
        return result;
    }

    public enum CircuitBreakerState {
        CLOSED, OPEN, HALF_OPEN
    }

    private static class CircuitBreakerEventListener implements CircuitBreaker.EventListener {
        @Override
        public void onSuccess(long durationInNanos) {
            log.debug("Circuit breaker success: {}ns", durationInNanos);
        }

        @Override
        public void onFailure(long durationInNanos, Throwable throwable) {
            log.warn("Circuit breaker failure: {}ns, error: {}", durationInNanos, throwable.getMessage());
        }

        @Override
        public void onStateTransition(State state, State newState) {
            log.info("Circuit breaker state transition: {} -> {}", state, newState);
        }

        @Override
        public void onIgnoredError(long durationInNanos, Throwable throwable) {
            log.debug("Circuit breaker ignored error: {}", throwable.getMessage());
        }

        @Override
        public void onCallNotPermitted(long durationInNanos) {
            log.warn("Circuit breaker call not permitted");
        }

        @Override
        public void onFailureRateExceeded(long durationInNanos, float failureRate) {
            log.warn("Circuit breaker failure rate exceeded: {}%", failureRate);
        }
    }
}