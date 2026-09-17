package com.trading.riskengine.model;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Modelo de Value-at-Risk intraday con estructuras lock-free.
 * Mantiene el estado de exposición por trader, estrategia e instrumento.
 * Utiliza VarHandle para actualizaciones atómicas sin locks.
 */
public class VaRModel {

    // Constantes de configuración
    private static final double DEFAULT_VOLATILITY = 0.02;
    private static final double EWMA_LAMBDA = 0.94;
    private static final double VAR_CONFIDENCE_LEVEL = 0.99;
    private static final long NANOS_PER_SECOND = 1_000_000_000L;

    // Arrays lock-free para exposición por trader/estrategia/instrumento
    private final AtomicLongArray exposureByTrader;
    private final AtomicLongArray exposureByStrategy;
    private final AtomicReferenceArray<VolatilityState> volatilityByInstrument;

    // Estado de volatilidad por instrumento (EWMA)
    private static final class VolatilityState {
        private volatile double ewmaVolatility;
        private volatile long lastUpdateNanos;

        VolatilityState(double initialVolatility) {
            this.ewmaVolatility = initialVolatility;
            this.lastUpdateNanos = System.nanoTime();
        }

        double getEwmaVolatility() {
            return ewmaVolatility;
        }

        void updateVolatility(double newVolatility, long currentNanos) {
            this.ewmaVolatility = EWMA_LAMBDA * ewmaVolatility + (1 - EWMA_LAMBDA) * newVolatility;
            this.lastUpdateNanos = currentNanos;
        }
    }

    // VarHandle para actualizaciones atómicas de posiciones
    private static final VarHandle POSITION_HANDLE;

    static {
        try {
            POSITION_HANDLE = MethodHandles.arrayElementVarHandle(long[].class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize VarHandle", e);
        }
    }

    private final int shardCount;
    private final int maxTraders;
    private final int maxStrategies;
    private final int maxInstruments;

    /**
     * Constructor del modelo de VaR con sharding.
     * @param shardCount número de shards para paralelismo
     */
    public VaRModel(int shardCount) {
        this.shardCount = shardCount;
        this.maxTraders = 1000;
        this.maxStrategies = 500;
        this.maxInstruments = 10000;

        this.exposureByTrader = new AtomicLongArray(maxTraders);
        this.exposureByStrategy = new AtomicLongArray(maxStrategies);
        this.volatilityByInstrument = new AtomicReferenceArray<>(maxInstruments);
    }

    /**
     * Inicializa el modelo con valores por defecto.
     */
    public void initialize() {
        for (int i = 0; i < maxInstruments; i++) {
            volatilityByInstrument.set(i, new VolatilityState(DEFAULT_VOLATILITY));
        }
    }

    /**
     * Actualiza la exposición de un trader de forma atómica.
     * @param traderId identificador del trader
     * @param delta cambio en la exposición (positivo o negativo)
     * @return nueva exposición total del trader
     */
    public long updateTraderExposure(int traderId, long delta) {
        if (traderId < 0 || traderId >= maxTraders) {
            throw new IllegalArgumentException("Invalid trader ID: " + traderId);
        }
        long current;
        long newValue;
        do {
            current = exposureByTrader.get(traderId);
            newValue = current + delta;
        } while (!POSITION_HANDLE.compareAndSet(exposureByTrader, traderId, current, newValue));
        return newValue;
    }

    /**
     * Actualiza la exposición de una estrategia de forma atómica.
     * @param strategyId identificador de la estrategia
     * @param delta cambio en la exposición
     * @return nueva exposición total de la estrategia
     */
    public long updateStrategyExposure(int strategyId, long delta) {
        if (strategyId < 0 || strategyId >= maxStrategies) {
            throw new IllegalArgumentException("Invalid strategy ID: " + strategyId);
        }
        long current;
        long newValue;
        do {
            current = exposureByStrategy.get(strategyId);
            newValue = current + delta;
        } while (!POSITION_HANDLE.compareAndSet(exposureByStrategy, strategyId, current, newValue));
        return newValue;
    }

    /**
     * Obtiene la exposición actual de un trader.
     */
    public long getTraderExposure(int traderId) {
        return exposureByTrader.get(traderId);
    }

    /**
     * Obtiene la exposición actual de una estrategia.
     */
    public long getStrategyExposure(int strategyId) {
        return exposureByStrategy.get(strategyId);
    }

    /**
     * Actualiza la volatilidad de un instrumento usando EWMA.
     * @param instrumentId identificador del instrumento
     * @param newVolatility nueva volatilidad observada
     */
    public void updateInstrumentVolatility(int instrumentId, double newVolatility) {
        if (instrumentId < 0 || instrumentId >= maxInstruments) {
            return;
        }
        VolatilityState state = volatilityByInstrument.get(instrumentId);
        if (state != null) {
            state.updateVolatility(newVolatility, System.nanoTime());
        }
    }

    /**
     * Obtiene la volatilidad EWMA de un instrumento.
     */
    public double getInstrumentVolatility(int instrumentId) {
        if (instrumentId < 0 || instrumentId >= maxInstruments) {
            return DEFAULT_VOLATILITY;
        }
        VolatilityState state = volatilityByInstrument.get(instrumentId);
        return state != null ? state.getEwmaVolatility() : DEFAULT_VOLATILITY;
    }

    /**
     * Calcula el VaR para una exposición dada usando la volatilidad del instrumento.
     * @param exposure exposición en unidades monetarias
     * @param instrumentId identificador del instrumento
     * @return VaR estimado al nivel de confianza configurado
     */
    public double calculateVaR(long exposure, int instrumentId) {
        double volatility = getInstrumentVolatility(instrumentId);
        // VaR simple: exposure * volatility * z-score para 99% confianza
        // z-score de 2.33 para confianza del 99%
        double zScore = 2.33;
        return Math.abs(exposure) * volatility * zScore;
    }

    /**
     * Calcula el límite de posición basado en el VaR y la volatilidad.
     * @param instrumentId identificador del instrumento
     * @param maxVar VaR máximo permitido
     * @return límite de posición recomendado
     */
    public long calculatePositionLimit(int instrumentId, double maxVar) {
        double volatility = getInstrumentVolatility(instrumentId);
        if (volatility <= 0) {
            return 0;
        }
        double zScore = 2.33;
        return (long) (maxVar / (volatility * zScore));
    }

    /**
     * Verifica si la exposición excede el VaR permitido.
     */
    public boolean exceedsVaR(long exposure, int instrumentId, double maxVar) {
        double currentVaR = calculateVaR(exposure, instrumentId);
        return currentVaR > maxVar;
    }

    /**
     * Obtiene el número de shards del modelo.
     */
    public int getShardCount() {
        return shardCount;
    }

    /**
     * Resetea el modelo清理 todas las posiciones.
     */
    public void reset() {
        for (int i = 0; i < maxTraders; i++) {
            exposureByTrader.set(i, 0);
        }
        for (int i = 0; i < maxStrategies; i++) {
            exposureByStrategy.set(i, 0);
        }
        for (int i = 0; i < maxInstruments; i++) {
            volatilityByInstrument.set(i, new VolatilityState(DEFAULT_VOLATILITY));
        }
    }
}