package com.trading.riskengine.feed;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkerPool;
import com.trading.riskengine.engine.RiskEngine;
import com.trading.riskengine.model.VaRModel;
import net.openhft.chronicle.queue.ChronicleQueue;
import net.openhft.chronicle.queue.ExcerptAppender;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Handler del Disruptor que procesa eventos de market data y actualiza
 * el modelo de VaR intraday de forma thread-safe.
 * 
 * Este handler es responsable de:
 * - Procesar orderbook updates y trades del feed
 * - Actualizar la volatilidad por instrumento usando EWMA
 * - Notificar al RiskEngine cuando los thresholds se exceden
 * - Persistir eventos para replay determinístico
 */
public class MarketDataHandler implements EventHandler<MarketDataEvent> {

    private static final double EWMA_LAMBDA = 0.94;
    private static final double MIN_VOLATILITY = 0.01;
    private static final double MAX_VOLATILITY = 5.0;
    private static final long VOLATILITY_UPDATE_INTERVAL_NS = 1_000_000_000L;

    private final VaRModel varModel;
    private final RiskEngine riskEngine;
    private final ChronicleQueue eventLog;
    private final ExcerptAppender appender;
    private final AtomicLong lastVolatilityUpdate;
    private final AtomicBoolean isInitialized;
    private volatile double lastMidPrice;
    private volatile double currentVolatility;
    private volatile int processedCount;
    private volatile int errorCount;

    public MarketDataHandler(VaRModel varModel, RiskEngine riskEngine, ChronicleQueue eventLog) {
        this.varModel = varModel;
        this.riskEngine = riskEngine;
        this.eventLog = eventLog;
        this.appender = eventLog.acquireAppender();
        this.lastVolatilityUpdate = new AtomicLong(0L);
        this.isInitialized = new AtomicBoolean(false);
        this.currentVolatility = 0.02;
        this.processedCount = 0;
        this.errorCount = 0;
    }

    @Override
    public void onEvent(MarketDataEvent event, long sequence, boolean endOfBatch) throws Exception {
        try {
            if (!isInitialized.get()) {
                initialize(event.getInstrumentId());
            }

            switch (event.getEventType()) {
                case ORDERBOOK_SNAPSHOT:
                case ORDERBOOK_UPDATE:
                    processOrderBookUpdate(event);
                    break;
                case TRADE:
                    processTrade(event);
                    break;
                case TRADE_CANCEL:
                    processTradeCancel(event);
                    break;
            }

            updateVolatilityIfNeeded(event);
            persistEventForReplay(event);
            
            processedCount++;
            
            if (endOfBatch) {
                checkAndTriggerRiskAlerts();
            }

        } catch (Exception e) {
            errorCount++;
            handleProcessingError(event, e);
        }
    }

    private void initialize(int instrumentId) {
        if (isInitialized.compareAndSet(false, true)) {
            lastMidPrice = calculateMidPriceFromEvent(
                varModel.getInstrumentVolatility(instrumentId) * 1000);
            currentVolatility = varModel.getInstrumentVolatility(instrumentId);
            if (currentVolatility < MIN_VOLATILITY) {
                currentVolatility = 0.02;
            }
        }
    }

    private void processOrderBookUpdate(MarketDataEvent event) {
        int instrumentId = event.getInstrumentId();
        
        if (event.getLevelsCount() > 0) {
            long bestBid = event.getPriceAtLevel(0);
            long bestAsk = event.getPriceAtLevel(1);
            
            if (bestBid > 0 && bestAsk > 0) {
                double midPrice = (bestBid + bestAsk) / 200.0;
                
                if (lastMidPrice > 0) {
                    double priceChange = Math.abs(midPrice - lastMidPrice) / lastMidPrice;
                    updateVolatility(priceChange);
                }
                
                lastMidPrice = midPrice;
                varModel.updateInstrumentVolatility(instrumentId, currentVolatility);
            }
        }

        long imbalance = event.getBidImbalance();
        if (Math.abs(imbalance) > 1000000) {
            riskEngine.onMarketImbalance(instrumentId, imbalance);
        }
    }

    private void processTrade(MarketDataEvent event) {
        int instrumentId = event.getInstrumentId();
        double tradePrice = event.getTradedPrice();
        long tradeSize = event.getTradedSize();

        if (lastMidPrice > 0) {
            double priceChange = Math.abs(tradePrice - lastMidPrice) / lastMidPrice;
            updateVolatility(priceChange);
        }

        long exposureDelta = (long) (tradePrice * tradeSize);
        varModel.updateTraderExposure(determineTraderFromEvent(event), exposureDelta);
        varModel.updateStrategyExposure(determineStrategyFromEvent(event), exposureDelta);

        riskEngine.onTradeExecuted(instrumentId, tradePrice, tradeSize);
    }

    private void processTradeCancel(MarketDataEvent event) {
        int instrumentId = event.getInstrumentId();
        long tradeSize = event.getTradedSize();
        double tradePrice = event.getTradedPrice();

        long exposureDelta = -(long) (tradePrice * tradeSize);
        varModel.updateTraderExposure(determineTraderFromEvent(event), exposureDelta);
        varModel.updateStrategyExposure(determineStrategyFromEvent(event), exposureDelta);
    }

    private void updateVolatility(double priceChange) {
        double ewmaUpdate = EWMA_LAMBDA * currentVolatility + (1 - EWMA_LAMBDA) * priceChange;
        currentVolatility = Math.max(MIN_VOLATILITY, Math.min(MAX_VOLATILITY, ewmaUpdate));
    }

    private void updateVolatilityIfNeeded(MarketDataEvent event) {
        long currentTime = System.nanoTime();
        long lastUpdate = lastVolatilityUpdate.get();
        
        if (currentTime - lastUpdate > VOLATILITY_UPDATE_INTERVAL_NS) {
            if (lastVolatilityUpdate.compareAndSet(lastUpdate, currentTime)) {
                int instrumentId = event.getInstrumentId();
                varModel.updateInstrumentVolatility(instrumentId, currentVolatility);
            }
        }
    }

    private void persistEventForReplay(MarketDataEvent event) {
        try {
            appender.writeBytes(b -> {
                b.writeLong(event.getSequence());
                b.writeLong(event.getTimestampNs());
                b.writeInt(event.getInstrumentId());
                b.writeInt(event.getEventType().ordinal());
                b.writeDouble(event.getTradedPrice());
                b.writeLong(event.getTradedSize());
            });
        } catch (Exception e) {
            System.err.println("Failed to persist event: " + e.getMessage());
        }
    }

    private void checkAndTriggerRiskAlerts() {
        int[] instruments = {0, 1, 2, 3, 4};
        for (int instrumentId : instruments) {
            double volatility = varModel.getInstrumentVolatility(instrumentId);
            long exposure = varModel.getTraderExposure(0);
            
            if (volatility > MAX_VOLATILITY * 0.8) {
                riskEngine.triggerCircuitBreaker(instrumentId, 
                    "HIGH_VOLATILITY", volatility);
            }
        }
    }

    private void handleProcessingError(MarketDataEvent event, Exception e) {
        System.err.println("Error processing event: " + event.getEventType() + 
            " for instrument " + event.getInstrumentId() + 
            " at sequence " + event.getSequence() + 
            ": " + e.getMessage());
    }

    private int determineTraderFromEvent(MarketDataEvent event) {
        return Math.abs(event.getInstrumentId()) % 10;
    }

    private int determineStrategyFromEvent(MarketDataEvent event) {
        return Math.abs(event.getInstrumentId()) % 5;
    }

    private double calculateMidPriceFromEvent(double price) {
        return price > 0 ? price : 100.0;
    }

    public int getProcessedCount() {
        return processedCount;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public double getCurrentVolatility() {
        return currentVolatility;
    }

    public void reset() {
        processedCount = 0;
        errorCount = 0;
        currentVolatility = 0.02;
        lastMidPrice = 0;
    }
}