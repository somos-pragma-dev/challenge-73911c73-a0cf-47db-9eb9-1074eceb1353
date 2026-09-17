package com.trading.riskengine.feed;

import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

public class MarketDataEvent {
    private static final VarHandle PRICE_HANDLE;
    private static final VarHandle SIZE_HANDLE;
    
    public enum EventType {
        ORDER_BOOK_SNAPSHOT,
        ORDER_BOOK_UPDATE,
        TRADE,
        TRADE_CANCEL
    }
    
    public enum Side {
        BID,
        ASK
    }
    
    private volatile long sequence;
    private volatile long timestampNs;
    private EventType eventType;
    private int instrumentId;
    private Side side;
    private long[] priceLevels;
    private long[] sizeLevels;
    private int levelsCount;
    private double tradedPrice;
    private long tradedSize;
    private ByteBuffer orderBookDelta;
    private long eventId;
    private int sourcePartition;
    
    static {
        try {
            PRICE_HANDLE = MethodHandles.lookup().findVarHandle(
                MarketDataEvent.class, "tradedPrice", double.class);
            SIZE_HANDLE = MethodHandles.lookup().findVarHandle(
                MarketDataEvent.class, "tradedSize", long.class);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    
    public MarketDataEvent() {
        this.priceLevels = new long[10];
        this.sizeLevels = new long[10];
    }
    
    public void reset() {
        sequence = 0;
        timestampNs = 0;
        eventType = null;
        instrumentId = 0;
        side = null;
        Arrays.fill(priceLevels, 0);
        Arrays.fill(sizeLevels, 0);
        levelsCount = 0;
        tradedPrice = 0.0;
        tradedSize = 0;
        orderBookDelta = null;
        eventId = 0;
        sourcePartition = 0;
    }
    
    public void setOrderBookUpdate(int instrumentId, Side side, long[] prices, long[] sizes, int count) {
        this.instrumentId = instrumentId;
        this.side = side;
        this.eventType = EventType.ORDER_BOOK_UPDATE;
        this.levelsCount = Math.min(count, Math.min(prices.length, sizeLevels.length));
        System.arraycopy(prices, 0, priceLevels, 0, levelsCount);
        System.arraycopy(sizes, 0, sizeLevels, 0, levelsCount);
    }
    
    public void setTrade(int instrumentId, double price, long size) {
        this.instrumentId = instrumentId;
        this.eventType = EventType.TRADE;
        this.tradedPrice = price;
        this.tradedSize = size;
    }
    
    public void setOrderBookSnapshot(int instrumentId, long[] bids, long[] bidSizes, 
                                      long[] asks, long[] askSizes, int count) {
        this.instrumentId = instrumentId;
        this.eventType = EventType.ORDER_BOOK_SNAPSHOT;
        this.levelsCount = count;
        System.arraycopy(bids, 0, priceLevels, 0, Math.min(count, bids.length));
        System.arraycopy(bidSizes, 0, sizeLevels, 0, Math.min(count, bidSizes.length));
    }
    
    public long getSequence() { return sequence; }
    public void setSequence(long seq) { this.sequence = seq; }
    public long getTimestampNs() { return timestampNs; }
    public void setTimestampNs(long ts) { this.timestampNs = ts; }
    public EventType getEventType() { return eventType; }
    public int getInstrumentId() { return instrumentId; }
    public Side getSide() { return side; }
    public long getPriceAtLevel(int level) {
        if (level >= 0 && level < levelsCount) return priceLevels[level];
        return 0;
    }
    public long getSizeAtLevel(int level) {
        if (level >= 0 && level < levelsCount) return sizeLevels[level];
        return 0;
    }
    public int getLevelsCount() { return levelsCount; }
    public double getTradedPrice() { return tradedPrice; }
    public long getTradedSize() { return tradedSize; }
    public long getBidImbalance() {
        if (levelsCount == 0) return 0;
        long totalBidSize = 0;
        long totalAskSize = 0;
        for (int i = 0; i < levelsCount; i++) {
            totalBidSize += sizeLevels[i];
        }
        return totalBidSize - totalAskSize;
    }
    public Instant getTimestamp() {
        return Instant.ofEpochSecond(0, timestampNs);
    }
    public long getEventId() { return eventId; }
    public void setEventId(long id) { this.eventId = id; }
    public int getSourcePartition() { return sourcePartition; }
    public void setSourcePartition(int partition) { this.sourcePartition = partition; }
    public ByteBuffer getOrderBookDelta() { return orderBookDelta; }
    public void setOrderBookDelta(ByteBuffer delta) { this.orderBookDelta = delta; }
    public boolean isTrade() { return eventType == EventType.TRADE; }
    public boolean isOrderBook() { 
        return eventType == EventType.ORDER_BOOK_SNAPSHOT || eventType == EventType.ORDER_BOOK_UPDATE; 
    }
    
    // Additional setters needed for tests
    public void setInstrumentId(int instrumentId) { this.instrumentId = instrumentId; }
    public void setBidPrice(double price) { this.tradedPrice = price; }
    public void setAskPrice(double price) { this.tradedPrice = price; }
    public void setBidSize(long size) { this.tradedSize = size; }
    public void setAskSize(long size) { this.tradedSize = size; }
    public void setTimestamp(long timestamp) { this.timestampNs = timestamp; }
}