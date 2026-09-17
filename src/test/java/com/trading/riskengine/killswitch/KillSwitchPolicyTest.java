package com.trading.riskengine.killswitch;

import com.trading.riskengine.model.VaRModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.random.RandomGenerator;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("KillSwitchPolicy Tests")
class KillSwitchPolicyTest {

    private KillSwitchPolicy killSwitchPolicy;
    private VaRModel varModel;
    private RandomGenerator rng;
    private AtomicInteger emergencyCallCount;

    @BeforeEach
    void setUp() {
        int shardCount = Runtime.getRuntime().availableProcessors();
        this.varModel = new VaRModel(shardCount);
        this.varModel.initialize();
        this.emergencyCallCount = new AtomicInteger(0);
        this.killSwitchPolicy = new KillSwitchPolicy(varModel, emergencyCallCount);
        this.rng = RandomGenerator.getDefault();
    }

    @Test
    @DisplayName("Should detect anomalous latency spike")
    void shouldDetectAnomalousLatencySpike() {
        int traderId = 1;
        long[] latencies = {100, 120, 110, 95000, 130, 115};

        boolean detected = killSwitchPolicy.detectAnomaly(traderId, latencies);
        assertTrue(detected, "Debe detectar spike anómalo de latencia");
    }

    @Test
    @DisplayName("Should not trigger with normal latency variance")
    void shouldNotTriggerWithNormalLatencyVariance() {
        int traderId = 2;
        long[] latencies = {100, 120, 110, 130, 115, 125};

        boolean detected = killSwitchPolicy.detectAnomaly(traderId, latencies);
        assertFalse(detected, "No debe detectar anomalía con varianza normal");
    }

    @Test
    @DisplayName("Should detect excessive order rate")
    void shouldDetectExcessiveOrderRate() {
        int traderId = 3;
        int orderCount = 500;
        long timeWindowMs = 100;

        boolean detected = killSwitchPolicy.detectExcessiveOrderRate(traderId, orderCount, timeWindowMs);
        assertTrue(detected, "Debe detectar tasa excesiva de órdenes");
    }

    @Test
    @DisplayName("Should not trigger with normal order rate")
    void shouldNotTriggerWithNormalOrderRate() {
        int traderId = 4;
        int orderCount = 50;
        long timeWindowMs = 1000;

        boolean detected = killSwitchPolicy.detectExcessiveOrderRate(traderId, orderCount, timeWindowMs);
        assertFalse(detected, "No debe detectar tasa normal de órdenes");
    }

    @Test
    @DisplayName("Should activate kill switch when triggered")
    void shouldActivateKillSwitchWhenTriggered() {
        int traderId = 5;

        killSwitchPolicy.trigger(traderId, KillSwitchPolicy.TriggerReason.LATENCY_SPIKE);

        boolean isActive = killSwitchPolicy.isActive(traderId);
        assertTrue(isActive, "Kill switch debe activarse");
    }

    @Test
    @DisplayName("Should block orders when kill switch is active")
    void shouldBlockOrdersWhenKillSwitchIsActive() {
        int traderId = 6;
        killSwitchPolicy.trigger(traderId, KillSwitchPolicy.TriggerReason.EXCESSIVE_LOSS);

        boolean canTrade = killSwitchPolicy.canTrade(traderId);
        assertFalse(canTrade, "Debe bloquearTrading con kill switch activo");
    }

    @Test
    @DisplayName("Should allow orders when kill switch is not active")
    void shouldAllowOrdersWhenKillSwitchIsNotActive() {
        int traderId = 7;

        boolean canTrade = killSwitchPolicy.canTrade(traderId);
        assertTrue(canTrade, "Debe permitirTrading sin kill switch activo");
    }

    @Test
    @DisplayName("Should execute emergency callback on activation")
    void shouldExecuteEmergencyCallbackOnActivation() {
        int traderId = 8;

        killSwitchPolicy.trigger(traderId, KillSwitchPolicy.TriggerReason.CIRCUIT_BREAKER_OPEN);

        assertEquals(1, emergencyCallCount.get(),
            "Debe ejecutar callback de emergencia");
    }

    @Test
    @DisplayName("Should allow manual reset of kill switch")
    void shouldAllowManualResetOfKillSwitch() {
        int traderId = 9;
        killSwitchPolicy.trigger(traderId, KillSwitchPolicy.TriggerReason.VIOLATION);

        killSwitchPolicy.reset(traderId);

        boolean isActive = killSwitchPolicy.isActive(traderId);
        assertFalse(isActive, "Debe permitir reset manual");
    }

    @Test
    @DisplayName("Should track multiple traders independently")
    void shouldTrackMultipleTradersIndependently() {
        killSwitchPolicy.trigger(10, KillSwitchPolicy.TriggerReason.LATENCY_SPIKE);
        killSwitchPolicy.trigger(11, KillSwitchPolicy.TriggerReason.EXCESSIVE_LOSS);

        assertTrue(killSwitchPolicy.isActive(10));
        assertTrue(killSwitchPolicy.isActive(11));
        assertFalse(killSwitchPolicy.isActive(12));
    }

    @Test
    @DisplayName("Should detect violation of position limits")
    void shouldDetectViolationOfPositionLimits() {
        int traderId = 12;
        varModel.updateTraderExposure(traderId, 50000000L);

        boolean detected = killSwitchPolicy.checkPositionLimitViolation(traderId, 10000000L);
        assertTrue(detected, "Debe detectar violación de límites de posición");
    }

    @Test
    @DisplayName("Should provide trigger reason for audit")
    void shouldProvideTriggerReasonForAudit() {
        int traderId = 13;
        killSwitchPolicy.trigger(traderId, KillSwitchPolicy.TriggerReason.CIRCUIT_BREAKER_OPEN);

        KillSwitchPolicy.TriggerReason reason = killSwitchPolicy.getTriggerReason(traderId);
        assertEquals(KillSwitchPolicy.TriggerReason.CIRCUIT_BREAKER_OPEN, reason);
    }
}