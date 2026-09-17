package com.trading.riskengine;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.trading.riskengine.engine.RiskEngine;
import com.trading.riskengine.feed.MarketDataEvent;
import com.trading.riskengine.feed.MarketDataHandler;
import com.trading.riskengine.model.VaRModel;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Punto de entrada del sistema de risk scoring.
 * Inicializa el Disruptor, los handlers y las estructuras de sharding por instrumento.
 * El sistema está diseñado para procesar eventos de mercado con latencia < 500us p99.
 */
public class Main {

    private static final int RING_BUFFER_SIZE = 1 << 16; // 65536 eventos
    private static final int SHARD_COUNT = 64; // Shards por instrumento para paralelismo

    private final Disruptor<MarketDataEvent> disruptor;
    private final VaRModel varModel;
    private final RiskEngine[] riskEngines;
    private final RingBuffer<MarketDataEvent> ringBuffer;

    public Main() {
        this.varModel = createVarModel();
        this.riskEngines = createRiskEngineShards();
        this.disruptor = createDisruptor();
        this.ringBuffer = disruptor.getRingBuffer();
    }

    /**
     * Crea el modelo de VaR con estructuras lock-free para cada shard de instrumento.
     * Utiliza VarHandle para actualizaciones atómicas sin bloquear el hilo.
     */
    private VaRModel createVarModel() {
        VaRModel model = new VaRModel(SHARD_COUNT);
        model.initialize();
        return model;
    }

    /**
     * Crea un array de RiskEngine, uno por cada shard de instrumento.
     * Esta arquitectura permite procesamiento paralelo sin contención entre shards.
     */
    private RiskEngine[] createRiskEngineShards() {
        RiskEngine[] engines = new RiskEngine[SHARD_COUNT];
        for (int i = 0; i < SHARD_COUNT; i++) {
            engines[i] = new RiskEngine(varModel, i);
            engines[i].initialize();
        }
        return engines;
    }

    /**
     * Configura el Disruptor con múltiples productores y estrategia de espera bloqueante.
     * El tamaño del ring buffer (65536) permite absorber picos de eventos sin perder datos.
     */
    private Disruptor<MarketDataEvent> createDisruptor() {
        ThreadFactory threadFactory = new ThreadFactory() {
            private final AtomicLong counter = new AtomicLong(0);

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("risk-engine-" + counter.getAndIncrement());
                t.setDaemon(true);
                return t;
            }
        };

        Disruptor<MarketDataEvent> disruptor = new Disruptor<>
            (MarketDataEvent::new,
             RING_BUFFER_SIZE,
             threadFactory,
             ProducerType.MULTI,
             new BlockingWaitStrategy());

        // Registrar el handler que procesa eventos de market data
        // Cada evento se rutea al shard correspondiente según el instrumento
        MarketDataHandler handler = new MarketDataHandler(varModel, riskEngines);
        disruptor.handleEventsWith(handler);

        disruptor.setDefaultExceptionHandler(new DisruptorExceptionHandler());
        return disruptor;
    }

    /**
     * Inicia el sistema y queda escuchando eventos.
     */
    public void start() {
        disruptor.start();
        System.out.println("[Main] Risk Engine iniciado con " + SHARD_COUNT + " shards");
        System.out.println("[Main] Ring buffer size: " + RING_BUFFER_SIZE);
        System.out.println("[Main] Listo para procesar eventos de mercado");
    }

    /**
     * Publica un evento de market data en el ring buffer.
     * Retorna el sequence number del evento publicado.
     */
    public long publishMarketDataEvent(MarketDataEvent event) {
        long sequence = ringBuffer.next();
        try {
            MarketDataEvent published = ringBuffer.get(sequence);
            published.copyFrom(event);
        } finally {
            ringBuffer.publish(sequence);
        }
        return sequence;
    }

    /**
     * Detiene el sistema de forma ordenada.
     */
    public void shutdown() {
        disruptor.shutdown();
        for (RiskEngine engine : riskEngines) {
            engine.shutdown();
        }
        System.out.println("[Main] Risk Engine detenido");
    }

    public VaRModel getVarModel() {
        return varModel;
    }

    public RiskEngine[] getRiskEngines() {
        return riskEngines;
    }

    /**
     * Excepción handler para el Disruptor que registra errores sin detener el sistema.
     */
    private static class DisruptorExceptionHandler implements com.lmax.disruptor.ExceptionHandler<MarketDataEvent> {

        @Override
        public void handleEventException(Throwable ex, long sequence, MarketDataEvent event) {
            System.err.println("[DisruptorExceptionHandler] Error procesando evento " + sequence + ": " + ex.getMessage());
        }

        @Override
        public void handleOnStartException(Throwable ex) {
            System.err.println("[DisruptorExceptionHandler] Error al iniciar Disruptor: " + ex.getMessage());
        }

        @Override
        public void handleOnShutdownException(Throwable ex) {
            System.err.println("[DisruptorExceptionHandler] Error al detener Disruptor: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();

        // Graceful shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(main::shutdown));
    }
}