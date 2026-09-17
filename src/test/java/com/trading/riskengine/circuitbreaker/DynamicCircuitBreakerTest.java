package com.trading.riskengine.circuitbreaker;

import com.trading.riskengine.model.VaRModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.random.RandomGenerator;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DynamicCircuitBreaker Tests")
class DynamicCircuitBreakerTest {

    private DynamicCircuitBreaker circuitBreaker;
    private VaRModel varModel;
    private RandomGenerator rng;

    @BeforeEach
    void setUp() {
        int shardCount = Runtime.getRuntime().availableProcessors();
        this.varModel = new VaRModel(shardCount);
        this.varModel.initialize();
        this.circuitBreaker = new DynamicCircuitBreaker(varModel, 1.5, 100, 5000);
        this.rng = RandomGenerator.getDefault();
    }

    @Test
    @DisplayName("Should allow order when volatility is below threshold")
    void shouldAllowOrderWhenVolatilityIsBelowThreshold() {
        int instrumentId = 1;
        varModel.updateInstrumentVolatility(instrumentId, 0.10);

        boolean canTrade = circuitBreaker.canTrade(instrumentId, 100000L);
        assertTrue(canTrade, "Orden debe permitir trading con volatilidad baja");
    }

    @Test
    @DisplayName("Should block order when volatility exceeds threshold")
    void shouldBlockOrderWhenVolatilityExceedsThreshold() {
        int instrumentId = 2;
        varModel.updateInstrumentVolatility(instrumentId, 0.50);

        boolean canTrade = circuitBreaker.canTrade(instrumentId, 100000L);
        assertFalse(canTrade, "Orden debe bloquearse con volatilidad alta");
    }

    @Test
    @DisplayName("Should block order when exposure exceeds limit")
    void shouldBlockOrderWhenExposureExceedsLimit() {
        int instrumentId = 3;
        varModel.updateInstrumentVolatility(instrumentId, 0.15);

        varModel.updateTraderExposure(1, 10000000L);

        boolean canTrade = circuitBreaker.canTrade(instrumentId, 5000000L);
        assertFalse(canTrade, "Orden debe bloquearse cuando supera exposición máxima");
    }

    @Test
    @DisplayName("Should track failure count correctly")
    void shouldTrackFailureCountCorrectly() {
        int instrumentId = 4;
        varModel.updateInstrumentVolatility(instrumentId, 0.60);

        for (int i = 0; i < 10; i++) {
            circuitBreaker.recordFailure(instrumentId);
        }

        int failureCount = circuitBreaker.getFailureCount(instrumentId);
        assertEquals(10, failureCount, "Debe registrar 10 fallos consecutivos");
    }

    @Test
    @DisplayName("Should transition to OPEN state after threshold failures")
    void shouldTransitionToOpenStateAfterThresholdFailures() {
        int instrumentId = 5;
        varModel.updateInstrumentVolatility(instrumentId, 0.70);

        for (int i = 0; i < 100; i++) {
            circuitBreaker.recordFailure(instrumentId);
        }

        CircuitState state = circuitBreaker.getState(instrumentId);
        assertEquals(CircuitState.OPEN, state, "Debe transiciónar a estado OPEN");
    }

    @Test
    @DisplayName("Should transition to HALF_OPEN after timeout")
    void shouldTransitionToHalfOpenAfterTimeout() throws InterruptedException {
        int instrumentId = 6;
        varModel.updateInstrumentVolatility(instrumentId, 0.80);

        for (int i = 0; i < 100; i++) {
            circuitBreaker.recordFailure(instrumentId);
        }

        Thread.sleep(6000);

        boolean canTrade = circuitBreaker.canTrade(instrumentId, 1000L);
        assertTrue(canTrade, "Debe permitir intento de recovery en estado HALF_OPEN");
    }

    @Test
    @DisplayName("Should reset failure count after successful trade")
    void shouldResetFailureCountAfterSuccessfulTrade() {
        int instrumentId = 7;
        varModel.updateInstrumentVolatility(instrumentId, 0.20);

        circuitBreaker.recordFailure(instrumentId);
        circuitBreaker.recordFailure(instrumentId);

        circuitBreaker.recordSuccess(instrumentId);

        int failureCount = circuitBreaker.getFailureCount(instrumentId);
        assertEquals(0, failureCount, "Debe reiniciar contador tras éxito");
    }

    @Test
    @DisplayName("Should calculate dynamic threshold based on volatility")
    void shouldCalculateDynamicThresholdBasedOnVolatility() {
        int instrumentIdLowVol = 8;
        int instrumentIdHighVol = 9;

        varModel.updateInstrumentVolatility(instrumentIdLowVol, 0.05);
        varModel.updateInstrumentVolatility(instrumentIdHighVol, 0.40);

        double thresholdLow = circuitBreaker.getDynamicThreshold(instrumentIdLowVol);
        double thresholdHigh = circuitBreaker.getDynamicThreshold(instrumentIdHighVol);

        assertTrue(thresholdLow > thresholdHigh,
            "Threshold debe ser más bajo para volatilidad alta");
    }

    enum CircuitState {
        CLOSED,
        OPEN,
        HALF_OPEN
    }
}