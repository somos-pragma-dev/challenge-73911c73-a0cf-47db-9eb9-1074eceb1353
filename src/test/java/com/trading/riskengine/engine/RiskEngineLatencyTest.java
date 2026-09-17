package com.trading.riskengine.engine;

import com.trading.riskengine.feed.MarketDataEvent;
import com.trading.riskengine.model.VaRModel;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.random.RandomGenerator;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(2)
@State(Scope.Thread)
public class RiskEngineLatencyTest {

    private RiskEngine riskEngine;
    private VaRModel varModel;
    private MarketDataEvent[] testEvents;
    private RandomGenerator rng;

    @Setup
    public void setup() {
        int shardCount = Runtime.getRuntime().availableProcessors();
        this.varModel = new VaRModel(shardCount);
        this.varModel.initialize();
        this.riskEngine = new RiskEngine(null, null, shardCount);
        this.rng = RandomGenerator.getDefault();
        this.testEvents = new MarketDataEvent[100];

        for (int i = 0; i < testEvents.length; i++) {
            MarketDataEvent event = new MarketDataEvent();
            event.setInstrumentId(i % 50);
            event.setBidPrice(100.0 + rng.nextDouble() * 10);
            event.setAskPrice(100.0 + rng.nextDouble() * 10);
            event.setBidSize(100 + rng.nextInt(1000));
            event.setAskSize(100 + rng.nextInt(1000));
            event.setTimestamp(System.nanoTime());
            testEvents[i] = event;
        }
    }

    @Benchmark
    public void measureSingleRiskEvaluation(Blackhole bh) {
        MarketDataEvent event = testEvents[rng.nextInt(testEvents.length)];
        RiskEngine.RiskResult result = riskEngine.evaluateRisk(event);
        bh.consume(result);
    }

    @Benchmark
    public void measureBatchRiskEvaluation(Blackhole bh) {
        RiskEngine.RiskResult[] results = new RiskEngine.RiskResult[10];
        for (int i = 0; i < 10; i++) {
            MarketDataEvent event = testEvents[i];
            results[i] = riskEngine.evaluateRisk(event);
        }
        bh.consume(results);
    }

    @Benchmark
    public void measureVaRCalculation(Blackhole bh) {
        int instrumentId = rng.nextInt(50);
        long exposure = 1000000L + rng.nextLong(5000000L);
        double var = varModel.calculateVaR(exposure, instrumentId);
        bh.consume(var);
    }

    @Benchmark
    public void measureConcurrentUpdates(Blackhole bh) {
        int traderId = rng.nextInt(100);
        long delta = 10000L + rng.nextLong(50000L);
        long newExposure = varModel.updateTraderExposure(traderId, delta);
        bh.consume(newExposure);
    }
}