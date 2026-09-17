# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de un motor de risk scoring en tiempo real para trading algorítmico con circuit breakers dinámicos**.

| | |
|---|---|
| Tema | TEST-CT |
| Nivel | master-l2 |
| Chapter | Generico |
| Especialidad | Inferido del contexto |
| Stack | Java / LMAX Disruptor 4.0 |
| Patron arquitectonico | reactivo con anillo lock-free y sharding por instrumento |
| Tiempo estimado | 2 semanas |

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `el comando de build o arranque canonico del stack elegido` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `el comando de build o arranque canonico del stack elegido` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Exploración y modelado del sistema**: Diagrama de relaciones y registro de decisiones inicial que incluye contexto, fuerzas, opciones con pros/contras, y decisiones tomadas.
- **Fase 2 — Implementación del motor de risk scoring**: Motor de risk scoring funcional que cumple con los requisitos de latencia y throughput, y que aplica los límites y circuit breakers definidos.
- **Fase 3 — Política de kill switch y replay determinístico**: Política de kill switch funcional y estrategia de replay determinístico que permite el análisis de incidents y garantiza la trazabilidad de decisiones de riesgo.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Referencias colgando (56)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `AuditRecord`
      El import com.trading.riskengine.replay.AuditRecord no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- [ ] `src/main/java/com/trading/riskengine/Main.java` — `MarketDataEvent.copyFrom`
      Se invoca `copyFrom` sobre `MarketDataEvent`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/model/VaRModel.java` — `VolatilityState.updateVolatility`
      Se invoca `updateVolatility` sobre `VolatilityState`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/model/VaRModel.java` — `VolatilityState.getEwmaVolatility`
      Se invoca `getEwmaVolatility` sobre `VolatilityState`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/killswitch/KillSwitchPolicy.java` — `AnomalyDetector.detectLatencyAnomaly`
      Se invoca `detectLatencyAnomaly` sobre `AnomalyDetector`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/replay/DeterministicReplay.java` — `EventProcessor.process`
      Se invoca `process` sobre `EventProcessor`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/replay/DeterministicReplay.java` — `AuditRecord.traderId`
      Se invoca `traderId` sobre `AuditRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/replay/EventSerializer.java` — `AuditRecord.sequence`
      Se invoca `sequence` sobre `AuditRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/replay/EventSerializer.java` — `AuditRecord.timestamp`
      Se invoca `timestamp` sobre `AuditRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/replay/EventSerializer.java` — `AuditRecord.eventType`
      Se invoca `eventType` sobre `AuditRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/replay/EventSerializer.java` — `AuditRecord.instrumentId`
      Se invoca `instrumentId` sobre `AuditRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/replay/EventSerializer.java` — `AuditRecord.traderId`
      Se invoca `traderId` sobre `AuditRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/replay/EventSerializer.java` — `AuditRecord.strategyId`
      Se invoca `strategyId` sobre `AuditRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/replay/EventSerializer.java` — `AuditRecord.orderValue`
      Se invoca `orderValue` sobre `AuditRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/replay/EventSerializer.java` — `AuditRecord.decision`
      Se invoca `decision` sobre `AuditRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/replay/EventSerializer.java` — `AuditRecord.reason`
      Se invoca `reason` sobre `AuditRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setDecisionId`
      Se invoca `setDecisionId` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setTimestamp`
      Se invoca `setTimestamp` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setComplianceRequirement`
      Se invoca `setComplianceRequirement` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setRecordingStatus`
      Se invoca `setRecordingStatus` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setFailureReason`
      Se invoca `setFailureReason` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getDecisionId`
      Se invoca `getDecisionId` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getTimestamp`
      Se invoca `getTimestamp` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getTraderId`
      Se invoca `getTraderId` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getStrategyId`
      Se invoca `getStrategyId` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getInstrumentId`
      Se invoca `getInstrumentId` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getOrderId`
      Se invoca `getOrderId` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getRiskScore`
      Se invoca `getRiskScore` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getExposure`
      Se invoca `getExposure` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getVarLimit`
      Se invoca `getVarLimit` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getVolatility`
      Se invoca `getVolatility` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getCircuitBreakerState`
      Se invoca `getCircuitBreakerState` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getComplianceRequirement`
      Se invoca `getComplianceRequirement` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getJustification`
      Se invoca `getJustification` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getReplayToken`
      Se invoca `getReplayToken` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setTraderId`
      Se invoca `setTraderId` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setStrategyId`
      Se invoca `setStrategyId` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setInstrumentId`
      Se invoca `setInstrumentId` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setOrderId`
      Se invoca `setOrderId` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setDecision`
      Se invoca `setDecision` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setRiskScore`
      Se invoca `setRiskScore` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setExposure`
      Se invoca `setExposure` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setVarLimit`
      Se invoca `setVarLimit` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setVolatility`
      Se invoca `setVolatility` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setCircuitBreakerState`
      Se invoca `setCircuitBreakerState` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setJustification`
      Se invoca `setJustification` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setReplayToken`
      Se invoca `setReplayToken` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecision.setInstrumentId`
      Se invoca `setInstrumentId` sobre `RiskDecision`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecision.setDecision`
      Se invoca `setDecision` sobre `RiskDecision`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecision.setCircuitBreakerState`
      Se invoca `setCircuitBreakerState` sobre `RiskDecision`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecision.setJustification`
      Se invoca `setJustification` sobre `RiskDecision`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecision.setComplianceRequirement`
      Se invoca `setComplianceRequirement` sobre `RiskDecision`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecision.setStrategyId`
      Se invoca `setStrategyId` sobre `RiskDecision`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecision.setTraderId`
      Se invoca `setTraderId` sobre `RiskDecision`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecision.setExposure`
      Se invoca `setExposure` sobre `RiskDecision`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecision.setVarLimit`
      Se invoca `setVarLimit` sobre `RiskDecision`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (16)

- `pom.xml`
- `src/main/java/com/trading/riskengine/Main.java`
- `src/main/java/com/trading/riskengine/model/VaRModel.java`
- `src/main/java/com/trading/riskengine/feed/MarketDataEvent.java`
- `src/main/java/com/trading/riskengine/feed/MarketDataHandler.java`
- `src/main/java/com/trading/riskengine/engine/RiskEngine.java`
- `src/main/java/com/trading/riskengine/circuitbreaker/DynamicCircuitBreaker.java`
- `src/main/java/com/trading/riskengine/killswitch/KillSwitchPolicy.java`
- `src/main/java/com/trading/riskengine/replay/DeterministicReplay.java`
- `src/main/java/com/trading/riskengine/replay/RiskEvent.java`
- `src/main/java/com/trading/riskengine/replay/AuditRecord.java`
- `src/main/java/com/trading/riskengine/replay/EventSerializer.java`
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java`
- `src/test/java/com/trading/riskengine/engine/RiskEngineLatencyTest.java`
- `src/test/java/com/trading/riskengine/circuitbreaker/DynamicCircuitBreakerTest.java`
- `src/test/java/com/trading/riskengine/killswitch/KillSwitchPolicyTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/trading/riskengine`
- `src/main/java/com/trading/riskengine/feed`
- `src/main/java/com/trading/riskengine/model`
- `src/main/java/com/trading/riskengine/engine`
- `src/main/java/com/trading/riskengine/circuitbreaker`
- `src/main/java/com/trading/riskengine/killswitch`
- `src/main/java/com/trading/riskengine/replay`
- `src/main/java/com/trading/riskengine/compliance`
- `src/test/java/com/trading/riskengine`

## Verificacion

```bash
el comando de build o arranque canonico del stack elegido
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **reactivo con anillo lock-free y sharding por instrumento**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Brecha que el reto ataca: Sistema master-l2 que evalúa el riesgo de cada orden antes de enviarla al exchange, en menos de 500 microsegundos p99. Consume feed de market data (nivel 2 orderbook + trades), mantiene un modelo de VaR intraday, aplica límites por trader/estrategia/instrumento en tiempo real, y dispara circuit breakers dinámicos que bloquean nuevas órdenes cuando la exposición supera thresholds calibrados por volatilidad. Debe justificar el uso de estructuras lock-free vs mutex, la elección entre C++ vs Rust vs Java LMAX Disruptor, cómo garantiza la consistencia entre múltiples risk engines corriendo en paralelo (consensus vs sharding por instrument), la política de kill switch cuando detecta un algoritmo que se comporta de forma anómala, y la estrategia de replay determinístico para post-mortem de incidents. Incluye compliance con regulaciones MiFID II para trazabilidad de decisiones de riesgo.

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
