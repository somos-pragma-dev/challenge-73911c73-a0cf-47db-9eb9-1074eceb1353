# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `AuditRecord`: El import com.trading.riskengine.replay.AuditRecord no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- `src/main/java/com/trading/riskengine/Main.java` — `MarketDataEvent.copyFrom`: Se invoca `copyFrom` sobre `MarketDataEvent`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/model/VaRModel.java` — `VolatilityState.updateVolatility`: Se invoca `updateVolatility` sobre `VolatilityState`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/model/VaRModel.java` — `VolatilityState.getEwmaVolatility`: Se invoca `getEwmaVolatility` sobre `VolatilityState`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/killswitch/KillSwitchPolicy.java` — `AnomalyDetector.detectLatencyAnomaly`: Se invoca `detectLatencyAnomaly` sobre `AnomalyDetector`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/replay/DeterministicReplay.java` — `EventProcessor.process`: Se invoca `process` sobre `EventProcessor`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/replay/DeterministicReplay.java` — `AuditRecord.traderId`: Se invoca `traderId` sobre `AuditRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/replay/EventSerializer.java` — `AuditRecord.sequence`: Se invoca `sequence` sobre `AuditRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/replay/EventSerializer.java` — `AuditRecord.timestamp`: Se invoca `timestamp` sobre `AuditRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/replay/EventSerializer.java` — `AuditRecord.eventType`: Se invoca `eventType` sobre `AuditRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/replay/EventSerializer.java` — `AuditRecord.instrumentId`: Se invoca `instrumentId` sobre `AuditRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/replay/EventSerializer.java` — `AuditRecord.traderId`: Se invoca `traderId` sobre `AuditRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/replay/EventSerializer.java` — `AuditRecord.strategyId`: Se invoca `strategyId` sobre `AuditRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/replay/EventSerializer.java` — `AuditRecord.orderValue`: Se invoca `orderValue` sobre `AuditRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/replay/EventSerializer.java` — `AuditRecord.decision`: Se invoca `decision` sobre `AuditRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/replay/EventSerializer.java` — `AuditRecord.reason`: Se invoca `reason` sobre `AuditRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setDecisionId`: Se invoca `setDecisionId` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setTimestamp`: Se invoca `setTimestamp` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setComplianceRequirement`: Se invoca `setComplianceRequirement` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setRecordingStatus`: Se invoca `setRecordingStatus` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setFailureReason`: Se invoca `setFailureReason` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getDecisionId`: Se invoca `getDecisionId` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getTimestamp`: Se invoca `getTimestamp` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getTraderId`: Se invoca `getTraderId` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getStrategyId`: Se invoca `getStrategyId` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getInstrumentId`: Se invoca `getInstrumentId` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getOrderId`: Se invoca `getOrderId` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getRiskScore`: Se invoca `getRiskScore` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getExposure`: Se invoca `getExposure` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getVarLimit`: Se invoca `getVarLimit` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getVolatility`: Se invoca `getVolatility` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getCircuitBreakerState`: Se invoca `getCircuitBreakerState` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getComplianceRequirement`: Se invoca `getComplianceRequirement` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getJustification`: Se invoca `getJustification` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.getReplayToken`: Se invoca `getReplayToken` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setTraderId`: Se invoca `setTraderId` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setStrategyId`: Se invoca `setStrategyId` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setInstrumentId`: Se invoca `setInstrumentId` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setOrderId`: Se invoca `setOrderId` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setDecision`: Se invoca `setDecision` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setRiskScore`: Se invoca `setRiskScore` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setExposure`: Se invoca `setExposure` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setVarLimit`: Se invoca `setVarLimit` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setVolatility`: Se invoca `setVolatility` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setCircuitBreakerState`: Se invoca `setCircuitBreakerState` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setJustification`: Se invoca `setJustification` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecisionType.setReplayToken`: Se invoca `setReplayToken` sobre `RiskDecisionType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecision.setInstrumentId`: Se invoca `setInstrumentId` sobre `RiskDecision`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecision.setDecision`: Se invoca `setDecision` sobre `RiskDecision`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecision.setCircuitBreakerState`: Se invoca `setCircuitBreakerState` sobre `RiskDecision`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecision.setJustification`: Se invoca `setJustification` sobre `RiskDecision`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecision.setComplianceRequirement`: Se invoca `setComplianceRequirement` sobre `RiskDecision`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecision.setStrategyId`: Se invoca `setStrategyId` sobre `RiskDecision`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecision.setTraderId`: Se invoca `setTraderId` sobre `RiskDecision`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecision.setExposure`: Se invoca `setExposure` sobre `RiskDecision`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java` — `RiskDecision.setVarLimit`: Se invoca `setVarLimit` sobre `RiskDecision`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
el comando de build o arranque canonico del stack elegido
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Contexto técnico original
Sistema master-l2 que evalúa el riesgo de cada orden antes de enviarla al exchange, en menos de 500 microsegundos p99. Consume feed de market data (nivel 2 orderbook + trades), mantiene un modelo de VaR intraday, aplica límites por trader/estrategia/instrumento en tiempo real, y dispara circuit breakers dinámicos que bloquean nuevas órdenes cuando la exposición supera thresholds calibrados por volatilidad. Debe justificar el uso de estructuras lock-free vs mutex, la elección entre C++ vs Rust vs Java LMAX Disruptor, cómo garantiza la consistencia entre múltiples risk engines corriendo en paralelo (consensus vs sharding por instrument), la política de kill switch cuando detecta un algoritmo que se comporta de forma anómala, y la estrategia de replay determinístico para post-mortem de incidents. Incluye compliance con regulaciones MiFID II para trazabilidad de decisiones de riesgo.

### Reto
- Tema: TEST-CT
- Seniority: master-l2
- Tipo: mixed
- Título: Implementación de un motor de risk scoring en tiempo real para trading algorítmico con circuit breakers dinámicos
- Tiempo estimado: 2 semanas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Exploración y modelado del sistema — objetivo: Comprender y modelar las necesidades del sistema de risk scoring en términos de dominio y requisitos funcionales. — entregable (NO resolver): Diagrama de relaciones y registro de decisiones inicial que incluye contexto, fuerzas, opciones con pros/contras, y decisiones tomadas.
- Fase 2: Implementación del motor de risk scoring — objetivo: Implementar el motor de risk scoring que evalúa el riesgo de cada orden en tiempo real. — entregable (NO resolver): Motor de risk scoring funcional que cumple con los requisitos de latencia y throughput, y que aplica los límites y circuit breakers definidos.
- Fase 3: Política de kill switch y replay determinístico — objetivo: Implementar la política de kill switch y la estrategia de replay determinístico para post-mortem de incidents. — entregable (NO resolver): Política de kill switch funcional y estrategia de replay determinístico que permite el análisis de incidents y garantiza la trazabilidad de decisiones de riesgo.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.trading</groupId>
    <artifactId>risk-engine</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>Risk Engine</name>
    <description>Real-time risk scoring engine for algorithmic trading with dynamic circuit breakers</description>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <disruptor.version>4.0.0</disruptor.version>
        <resilience4j.version>2.1.0</resilience4j.version>
        <chronicle.version>5.24.12</chronicle.version>
        <jmh.version>1.36</jmh.version>
        <junit.version>5.10.0</junit.version>
    </properties>

    <dependencies>
        <!-- LMAX Disruptor for lock-free ring buffer -->
        <dependency>
            <groupId>com.lmax</groupId>
            <artifactId>disruptor</artifactId>
            <version>${disruptor.version}</version>
        </dependency>

        <!-- Resilience4j for circuit breaker pattern -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-circuitbreaker</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>

        <!-- Chronicle Queue for deterministic replay -->
        <dependency>
            <groupId>net.openhft</groupId>
            <artifactId>chronicle-queue</artifactId>
            <version>${chronicle.version}</version>
        </dependency>

        <!-- JMH for latency benchmarks -->
        <dependency>
            <groupId>org.openjdk.jmh</groupId>
            <artifactId>jmh-core</artifactId>
            <version>${jmh.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- JUnit 5 for testing -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-params</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>21</source>
                    <target>21</target>
                    <enablePreview>true</enablePreview>
                </configuration>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.1.2</version>
                <configuration>
                    <includes>
                        <include>**/*Test.java</include>
                    </includes>
                    <parallel>methods</parallel>
                    <threadCount>4</threadCount>
                    <perCoreThreadCount>true</perCoreThreadCount>
                </configuration>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.3.0</version>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>com.trading.riskengine.Main</mainClass>
                            <addClasspath>true</addClasspath>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/trading/riskengine/Main.java ===
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

// === ARCHIVO: src/main/java/com/trading/riskengine/model/VaRModel.java ===
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

// === ARCHIVO: src/main/java/com/trading/riskengine/feed/MarketDataEvent.java ===
package com.trading.riskengine.feed;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.Arrays;

/**
 * Estructura de datos lock-free que representa un evento de market data.
 * Soporta tanto orderbook updates como trades con actualizaciones atómicas
 * usando VarHandle para garantizar consistencia en entorno multi-threaded.
 */
public class MarketDataEvent {

    private static final VarHandle PRICE_HANDLE = MethodHandles.arrayElementVarHandle(long[].class);
    private static final VarHandle SIZE_HANDLE = MethodHandles.arrayElementVarHandle(long[].class);
    private static final VarHandle TIMESTAMP_NS_HANDLE = MethodHandles.lookup().findVarHandle(
            MarketDataEvent.class, "timestampNs", long.class);
    private static final VarHandle SEQUENCE_HANDLE = MethodHandles.lookup().findVarHandle(
            MarketDataEvent.class, "sequence", long.class);

    public enum EventType {
        ORDERBOOK_SNAPSHOT,
        ORDERBOOK_UPDATE,
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

    public MarketDataEvent() {
        this.priceLevels = new long[20];
        this.sizeLevels = new long[20];
        this.levelsCount = 0;
        this.timestampNs = System.nanoTime();
    }

    public void reset() {
        TIMESTAMP_NS_HANDLE.set(this, System.nanoTime());
        SEQUENCE_HANDLE.getAndAdd(this, 1L);
        this.eventType = null;
        this.instrumentId = 0;
        this.side = null;
        Arrays.fill(priceLevels, 0L);
        Arrays.fill(sizeLevels, 0L);
        this.levelsCount = 0;
        this.tradedPrice = 0.0;
        this.tradedSize = 0L;
        this.orderBookDelta = null;
    }

    public void setOrderBookUpdate(int instrumentId, Side side, long[] prices, long[] sizes, int count) {
        this.eventType = EventType.ORDERBOOK_UPDATE;
        this.instrumentId = instrumentId;
        this.side = side;
        this.levelsCount = Math.min(count, 20);
        for (int i = 0; i < this.levelsCount; i++) {
            PRICE_HANDLE.set(this.priceLevels, i, prices[i]);
            SIZE_HANDLE.set(this.sizeLevels, i, sizes[i]);
        }
        this.timestampNs = System.nanoTime();
    }

    public void setTrade(int instrumentId, double price, long size) {
        this.eventType = EventType.TRADE;
        this.instrumentId = instrumentId;
        this.tradedPrice = price;
        this.tradedSize = size;
        this.timestampNs = System.nanoTime();
    }

    public void setOrderBookSnapshot(int instrumentId, long[] bids, long[] bidSizes, 
                                      long[] asks, long[] askSizes, int count) {
        this.eventType = EventType.ORDERBOOK_SNAPSHOT;
        this.instrumentId = instrumentId;
        this.levelsCount = Math.min(count, 20);
        for (int i = 0; i < this.levelsCount; i++) {
            PRICE_HANDLE.set(this.priceLevels, i * 2, bids[i]);
            SIZE_HANDLE.set(this.sizeLevels, i * 2, bidSizes[i]);
            PRICE_HANDLE.set(this.priceLevels, i * 2 + 1, asks[i]);
            SIZE_HANDLE.set(this.sizeLevels, i * 2 + 1, askSizes[i]);
        }
        this.timestampNs = System.nanoTime();
    }

    public long getSequence() {
        return (long) SEQUENCE_HANDLE.get(this);
    }

    public void setSequence(long seq) {
        SEQUENCE_HANDLE.set(this, seq);
    }

    public long getTimestampNs() {
        return timestampNs;
    }

    public void setTimestampNs(long ts) {
        TIMESTAMP_TIMESTAMP_NS_HANDLE.set(this, ts);
    }

    public EventType getEventType() {
        return eventType;
    }

    public int getInstrumentId() {
        return instrumentId;
    }

    public Side getSide() {
        return side;
    }

    public long getPriceAtLevel(int level) {
        if (level < 0 || level >= levelsCount) return 0L;
        return (long) PRICE_HANDLE.get(this.priceLevels, level);
    }

    public long getSizeAtLevel(int level) {
        if (level < 0 || level >= levelsCount) return 0L;
        return (long) SIZE_HANDLE.get(this.sizeLevels, level);
    }

    public int getLevelsCount() {
        return levelsCount;
    }

    public double getTradedPrice() {
        return tradedPrice;
    }

    public long getTradedSize() {
        return tradedSize;
    }

    public long getBidImbalance() {
        if (eventType != EventType.ORDERBOOK_SNAPSHOT && eventType != EventType.ORDERBOOK_UPDATE) {
            return 0L;
        }
        long totalBidSize = 0;
        long totalAskSize = 0;
        for (int i = 0; i < levelsCount; i++) {
            if (i % 2 == 0) {
                totalBidSize += (long) SIZE_HANDLE.get(this.sizeLevels, i);
            } else {
                totalAskSize += (long) SIZE_HANDLE.get(this.sizeLevels, i);
            }
        }
        return totalBidSize - totalAskSize;
    }

    public Instant getTimestamp() {
        return Instant.ofEpochSecond(0, timestampNs);
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long id) {
        this.eventId = id;
    }

    public int getSourcePartition() {
        return sourcePartition;
    }

    public void setSourcePartition(int partition) {
        this.sourcePartition = partition;
    }

    public ByteBuffer getOrderBookDelta() {
        return orderBookDelta;
    }

    public void setOrderBookDelta(ByteBuffer delta) {
        this.orderBookDelta = delta;
    }

    public boolean isTrade() {
        return eventType == EventType.TRADE || eventType == EventType.TRADE_CANCEL;
    }

    public boolean isOrderBook() {
        return eventType == EventType.ORDERBOOK_SNAPSHOT || eventType == EventType.ORDERBOOK_UPDATE;
    }
}

// === ARCHIVO: src/main/java/com/trading/riskengine/feed/MarketDataHandler.java ===
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

// === ARCHIVO: src/main/java/com/trading/riskengine/engine/RiskEngine.java ===
package com.trading.riskengine.engine;

import com.lmax.disruptor.Disruptor;
import com.lmax.disruptor.RingBuffer;
import com.trading.riskengine.feed.MarketDataEvent;
import com.trading.riskengine.model.VaRModel;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Motor principal que evalúa el riesgo de cada orden en menos de 500μs p99.
 * Usa el Disruptor para procesamiento reactivo con sharding por instrumento
 * para evitar contención entre threads.
 * 
 * Responsabilidades principales:
 * - Evaluación de órdenes contra límites de VaR
 * - Sharding de riesgo por instrumento
 * - Circuit breakers dinámicos basados en volatilidad
 * - Detección de anomalías y触发 de kill switch
 */
public class RiskEngine {

    private static final double DEFAULT_MAX_VAR = 100000.0;
    private static final int MAX_ORDERS_PER_INSTRUMENT = 100;
    private static final long CIRCUIT_BREAKER_RESET_TIMEOUT_MS = 5000;
    private static final double VOLATILITY_THRESHOLD_HIGH = 0.15;
    private static final double VOLATILITY_THRESHOLD_CRITICAL = 0.30;

    private final VaRModel varModel;
    private final RingBuffer<MarketDataEvent> ringBuffer;
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final CircuitBreaker[] circuitBreakersByInstrument;
    private final AtomicReference<OrderSubmissionResult> lastResult;
    private final AtomicLong ordersEvaluated;
    private final AtomicLong ordersRejected;
    private final AtomicLong circuitBreakerTriggers;
    private final AtomicBoolean isShutdown;
    private final RiskMetrics metrics;
    private final OrderValidator[] validators;

    public RiskEngine(VaRModel varModel, RingBuffer<MarketDataEvent> ringBuffer, int numInstruments) {
        this.varModel = varModel;
        this.ringBuffer = ringBuffer;
        this.circuitBreakerRegistry = createCircuitBreakerRegistry();
        this.circuitBreakersByInstrument = new CircuitBreaker[numInstruments];
        this.lastResult = new AtomicReference<>(OrderSubmissionResult.ACCEPTED);
        this.ordersEvaluated = new AtomicLong(0L);
        this.ordersRejected = new AtomicLong(0L);
        this.circuitBreakerTriggers = new AtomicLong(0L);
        this.isShutdown = new AtomicBoolean(false);
        this.metrics = new RiskMetrics();
        this.validators = new OrderValidator[numInstruments];

        initializeCircuitBreakers(numInstruments);
        initializeValidators(numInstruments);
    }

    private void initializeCircuitBreakers(int numInstruments) {
        for (int i = 0; i < numInstruments; i++) {
            CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .slowCallRateThreshold(80)
                .slowCallDurationThreshold(Duration.ofMillis(100))
                .waitDurationInOpenState(Duration.ofMillis(CIRCUIT_BREAKER_RESET_TIMEOUT_MS))
                .permittedNumberOfCallsInHalfState(10)
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(100)
                .minimumNumberOfCalls(20)
                .build();
            
            circuitBreakersByInstrument[i] = circuitBreakerRegistry.circuitBreaker(
                "instrument-" + i, config);
        }
    }

    private void initializeValidators(int numInstruments) {
        for (int i = 0; i < numInstruments; i++) {
            validators[i] = new OrderValidator(
                DEFAULT_MAX_VAR,
                MAX_ORDERS_PER_INSTRUMENT,
                circuitBreakersByInstrument[i]
            );
        }
    }

    private CircuitBreakerRegistry createCircuitBreakerRegistry() {
        CircuitBreakerConfig defaultConfig = CircuitBreakerConfig.custom()
            .failureRateThreshold(50)
            .waitDurationInOpenState(Duration.ofSeconds(5))
            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED)
            .slidingWindowSize(60)
            .minimumNumberOfCalls(10)
            .build();
        
        return CircuitBreakerRegistry.of(defaultConfig);
    }

    /**
     * Evalúa una orden contra los límites de riesgo actuales.
     * Retorna en menos de 500μs p99.
     */
    public OrderSubmissionResult evaluateOrder(Order order) {
        if (isShutdown.get()) {
            return OrderSubmissionResult.REJECTED_SYSTEM_SHUTDOWN;
        }

        long startTime = System.nanoTime();
        int instrumentId = order.getInstrumentId();

        if (instrumentId < 0 || instrumentId >= circuitBreakersByInstrument.length) {
            ordersRejected.incrementAndGet();
            return OrderSubmissionResult.REJECTED_INVALID_INSTRUMENT;
        }

        CircuitBreaker cb = circuitBreakersByInstrument[instrumentId];
        if (cb.getState() == CircuitBreaker.State.OPEN) {
            circuitBreakerTriggers.incrementAndGet();
            ordersRejected.incrementAndGet();
            metrics.recordRejection(RejectionReason.CIRCUIT_BREAKER_OPEN);
            return OrderSubmissionResult.REJECTED_CIRCUIT_BREAKER;
        }

        OrderValidator validator = validators[instrumentId];
        double volatility = varModel.getInstrumentVolatility(instrumentId);
        
        if (volatility > VOLATILITY_THRESHOLD_CRITICAL) {
            circuitBreakerTriggers.incrementAndGet();
            ordersRejected.incrementAndGet();
            metrics.recordRejection(RejectionReason.CRITICAL_VOLATILITY);
            triggerKillSwitch(instrumentId, "CRITICAL_VOLATILITY", volatility);
            return OrderSubmissionResult.REJECTED_HIGH_RISK;
        }

        long currentExposure = varModel.getTraderExposure(order.getTraderId());
        double maxVar = calculateDynamicVaRLimit(volatility);
        
        if (varModel.exceedsVaR(currentExposure + order.getNotional(), instrumentId, maxVar)) {
            ordersRejected.incrementAndGet();
            metrics.recordRejection(RejectionReason.VAR_LIMIT_EXCEEDED);
            return OrderSubmissionResult.REJECTED_VAR_LIMIT;
        }

        if (!validator.validate(order, currentExposure, maxVar)) {
            ordersRejected.incrementAndGet();
            metrics.recordRejection(RejectionReason.VALIDATION_FAILED);
            return OrderSubmissionResult.REJECTED_VALIDATION;
        }

        long newExposure = varModel.updateTraderExposure(order.getTraderId(), order.getNotional());
        varModel.updateStrategyExposure(order.getStrategyId(), order.getNotional());

        ordersEvaluated.incrementAndGet();
        metrics.recordAcceptance(System.nanoTime() - startTime);
        lastResult.set(OrderSubmissionResult.ACCEPTED);
        
        return OrderSubmissionResult.ACCEPTED;
    }

    private double calculateDynamicVaRLimit(double volatility) {
        double dynamicLimit = DEFAULT_MAX_VAR;
        if (volatility > VOLATILITY_THRESHOLD_HIGH) {
            dynamicLimit = DEFAULT_MAX_VAR * (1.0 - (volatility - VOLATILITY_THRESHOLD_HIGH) * 3);
        }
        return Math.max(dynamicLimit, DEFAULT_MAX_VAR * 0.1);
    }

    public void onTradeExecuted(int instrumentId, double price, long size) {
        long notional = (long) (price * size);
        varModel.updateTraderExposure(ThreadLocalRandom.current().nextInt(10), -notional);
    }

    public void onMarketImbalance(int instrumentId, long imbalance) {
        if (Math.abs(imbalance) > 5000000) {
            metrics.recordMarketImbalance(instrumentId, imbalance);
        }
    }

    public void triggerCircuitBreaker(int instrumentId, String reason, double value) {
        if (instrumentId >= 0 && instrumentId < circuitBreakersByInstrument.length) {
            CircuitBreaker cb = circuitBreakersByInstrument[instrumentId];
            cb.transitionToOpenState();
            circuitBreakerTriggers.incrementAndGet();
            metrics.recordCircuitBreakerTrigger(instrumentId, reason, value);
        }
    }

    private void triggerKillSwitch(int instrumentId, String reason, double value) {
        System.err.println("KILL_SWITCH triggered for instrument " + instrumentId + 
            " reason=" + reason + " value=" + value);
    }

    public RiskMetrics getMetrics() {
        return metrics;
    }

    public long getOrdersEvaluated() {
        return ordersEvaluated.get();
    }

    public long getOrdersRejected() {
        return ordersRejected.get();
    }

    public long getCircuitBreakerTriggers() {
        return circuitBreakerTriggers.get();
    }

    public void reset() {
        ordersEvaluated.set(0L);
        ordersRejected.set(0L);
        circuitBreakerTriggers.set(0L);
        metrics.reset();
    }

    public void shutdown() {
        isShutdown.set(true);
    }

    public enum OrderSubmissionResult {
        ACCEPTED,
        REJECTED_VAR_LIMIT,
        REJECTED_CIRCUIT_BREAKER,
        REJECTED_HIGH_RISK,
        REJECTED_VALIDATION,
        REJECTED_INVALID_INSTRUMENT,
        REJECTED_SYSTEM_SHUTDOWN
    }

    public enum RejectionReason {
        VAR_LIMIT_EXCEEDED,
        CIRCUIT_BREAKER_OPEN,
        CRITICAL_VOLATILITY,
        VALIDATION_FAILED
    }

    public static class Order {
        private final int traderId;
        private final int strategyId;
        private final int instrumentId;
        private final long notional;
        private final double price;
        private final Side side;

        public Order(int traderId, int strategyId, int instrumentId, long notional, double price, Side side) {
            this.traderId = traderId;
            this.strategyId = strategyId;
            this.instrumentId = instrumentId;
            this.notional = notional;
            this.price = price;
            this.side = side;
        }

        public int getTraderId() { return traderId; }
        public int getStrategyId() { return strategyId; }
        public int getInstrumentId() { return instrumentId; }
        public long getNotional() { return notional; }
        public double getPrice() { return price; }
        public Side getSide() { return side; }

        public enum Side { BUY, SELL }
    }

    private static class OrderValidator {
        private final double maxVar;
        private final int maxOrders;
        private final CircuitBreaker circuitBreaker;
        private final AtomicLong orderCount;

        OrderValidator(double maxVar, int maxOrders, CircuitBreaker circuitBreaker) {
            this.maxVar = maxVar;
            this.maxOrders = maxOrders;
            this.circuitBreaker = circuitBreaker;
            this.orderCount = new AtomicLong(0);
        }

        boolean validate(Order order, long currentExposure, double dynamicMaxVar) {
            if (orderCount.get() >= maxOrders) {
                return false;
            }
            orderCount.incrementAndGet();
            return true;
        }
    }

    public static class RiskMetrics {
        private final AtomicLong totalLatencyNanos;
        private final AtomicLong evaluationCount;
        private final AtomicLong[] rejectionsByReason;
        private final AtomicLong marketImbalanceCount;

        RiskMetrics() {
            this.totalLatencyNanos = new AtomicLong(0);
            this.evaluationCount = new AtomicLong(0);
            this.rejectionsByReason = new AtomicLong[RejectionReason.values().length];
            this.marketImbalanceCount = new AtomicLong(0);
            
            for (int i = 0; i < rejectionsByReason.length; i++) {
                rejectionsByReason[i] = new AtomicLong(0);
            }
        }

        void recordAcceptance(long latencyNanos) {
            totalLatencyNanos.addAndGet(latencyNanos);
            evaluationCount.incrementAndGet();
        }

        void recordRejection(RejectionReason reason) {
            rejectionsByReason[reason.ordinal()].incrementAndGet();
        }

        void recordMarketImbalance(int instrumentId, long imbalance) {
            marketImbalanceCount.incrementAndGet();
        }

        void recordCircuitBreakerTrigger(int instrumentId, String reason, double value) {
            rejectionsByReason[RejectionReason.CIRCUIT_BREAKER_OPEN.ordinal()].incrementAndGet();
        }

        double getAverageLatencyMicros() {
            long count = evaluationCount.get();
            if (count == 0) return 0;
            return (totalLatencyNanos.get() / count) / 1000.0;
        }

        void reset() {
            totalLatencyNanos.set(0);
            evaluationCount.set(0);
            marketImbalanceCount.set(0);
            for (AtomicLong reason : rejectionsByReason) {
                reason.set(0);
            }
        }
    }
}

// === ARCHIVO: src/main/java/com/trading/riskengine/circuitbreaker/DynamicCircuitBreaker.java ===
package com.trading.riskengine.circuitbreaker;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.CircuitBreakerState;
import io.github.resilience4j.circuitbreaker.CircuitBreakerTransition;
import io.github.resilience4j.circuitbreaker.EventType;
import io.github.resilience4j.circuitbreaker.CircuitBreakerEvent;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicCircuitBreaker {
    private static final Logger log = LoggerFactory.getLogger(DynamicCircuitBreaker.class);
    private static final double DEFAULT_VOLATILITY_THRESHOLD = 0.25;
    private static final double HIGH_VOLATILITY_THRESHOLD = 0.45;
    private static final int SLIDING_WINDOW_SIZE = 100;
    private static final int MINIMUM_NUMBER_OF_CALLS = 10;
    private static final double FAILURE_RATE_THRESHOLD = 0.50;
    private static final Duration WAIT_DURATION_IN_OPEN_STATE = Duration.ofSeconds(30);
    private static final Duration PERMITTED_CALLS_IN_HALF_OPEN_STATE = Duration.ofSeconds(15);
    
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final Map<Integer, CircuitBreaker> circuitBreakersByInstrument;
    private final Map<Integer, AtomicReference<Double>> currentVolatilityByInstrument;
    private final Map<Integer, Double> thresholdsByInstrument;
    private final CircuitBreakerEventListener eventListener;
    
    public DynamicCircuitBreaker() {
        this.circuitBreakerRegistry = createDefaultRegistry();
        this.circuitBreakersByInstrument = new ConcurrentHashMap<>();
        this.currentVolatilityByInstrument = new ConcurrentHashMap<>();
        this.thresholdsByInstrument = new ConcurrentHashMap<>();
        this.eventListener = new CircuitBreakerEventListener();
        initializeGlobalThresholds();
    }
    
    private CircuitBreakerRegistry createDefaultRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
            .slidingWindowSize(SLIDING_WINDOW_SIZE)
            .minimumNumberOfCalls(MINIMUM_NUMBER_OF_CALLS)
            .failureRateThreshold(FAILURE_RATE_THRESHOLD)
            .waitDurationInOpenState(WAIT_DURATION_IN_OPEN_STATE)
            .permittedNumberOfCallsInHalfOpenState(3)
            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
            .automaticTransitionFromOpenToHalfOpenEnabled(true)
            .build();
        return CircuitBreakerRegistry.of(config);
    }
    
    private void initializeGlobalThresholds() {
        thresholdsByInstrument.put(0, DEFAULT_VOLATILITY_THRESHOLD);
    }
    
    public boolean isOrderAllowed(int instrumentId, long orderValue) {
        CircuitBreaker cb = getOrCreateCircuitBreaker(instrumentId);
        return cb.executeSupplier(() -> checkOrderAllowed(instrumentId, orderValue));
    }
    
    private boolean checkOrderAllowed(int instrumentId, long orderValue) {
        AtomicReference<Double> volatilityRef = currentVolatilityByInstrument.get(instrumentId);
        if (volatilityRef == null) {
            return true;
        }
        double currentVolatility = volatilityRef.get();
        double threshold = getThresholdForVolatility(currentVolatility);
        if (currentVolatility > HIGH_VOLATILITY_THRESHOLD) {
            log.warn("High volatility detected for instrument {}: {}. Blocking new orders.", 
                     instrumentId, currentVolatility);
            return false;
        }
        double riskScore = calculateRiskScore(orderValue, currentVolatility);
        return riskScore < threshold;
    }
    
    private double getThresholdForVolatility(double volatility) {
        if (volatility < 0.10) {
            return 0.40;
        } else if (volatility < 0.20) {
            return 0.30;
        } else if (volatility < 0.30) {
            return 0.20;
        } else {
            return 0.10;
        }
    }
    
    private double calculateRiskScore(long orderValue, double volatility) {
        double volatilityMultiplier = 1.0 + (volatility * 2.0);
        double normalizedValue = Math.log1p(orderValue) / 20.0;
        return normalizedValue * volatilityMultiplier;
    }
    
    public void updateVolatility(int instrumentId, double newVolatility) {
        AtomicReference<Double> volatilityRef = currentVolatilityByInstrument.computeIfAbsent(
            instrumentId, 
            k -> new AtomicReference<>(0.0)
        );
        double previousVolatility = volatilityRef.getAndSet(newVolatility);
        adjustThresholdForInstrument(instrumentId, previousVolatility, newVolatility);
        log.debug("Updated volatility for instrument {}: {} -> {}", 
                  instrumentId, previousVolatility, newVolatility);
    }
    
    private void adjustThresholdForInstrument(int instrumentId, double previous, double current) {
        double changeRatio = Math.abs(current - previous) / (previous + 0.001);
        if (changeRatio > 0.5) {
            CircuitBreaker cb = circuitBreakersByInstrument.get(instrumentId);
            if (cb != null && cb.getState() == CircuitBreakerState.CLOSED) {
                cb.transitionToOpenState();
                log.warn("Circuit breaker opened for instrument {} due to volatility spike", instrumentId);
            }
        }
    }
    
    private CircuitBreaker getOrCreateCircuitBreaker(int instrumentId) {
        return circuitBreakersByInstrument.computeIfAbsent(instrumentId, this::createCircuitBreaker);
    }
    
    private CircuitBreaker createCircuitBreaker(int instrumentId) {
        String name = "circuit-breaker-" + instrumentId;
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
            .slidingWindowSize(SLIDING_WINDOW_SIZE)
            .minimumNumberOfCalls(5)
            .failureRateThreshold(FAILURE_RATE_THRESHOLD)
            .waitDurationInOpenState(WAIT_DURATION_IN_OPEN_STATE)
            .permittedNumberOfCallsInHalfOpenState(3)
            .build();
        CircuitBreaker cb = circuitBreakerRegistry.circuitBreaker(name, config);
        cb.getEventPublisher()
            .onStateTransition(eventListener::handleStateTransition)
            .onFailureRateExceeded(eventListener::handleFailureRateExceeded);
        return cb;
    }
    
    public CircuitBreakerState getState(int instrumentId) {
        CircuitBreaker cb = circuitBreakersByInstrument.get(instrumentId);
        return cb != null ? cb.getState() : CircuitBreakerState.CLOSED;
    }
    
    public double getFailureRate(int instrumentId) {
        CircuitBreaker cb = circuitBreakersByInstrument.get(instrumentId);
        return cb != null ? cb.getMetrics().getFailureRate() : 0.0;
    }
    
    public void resetCircuitBreaker(int instrumentId) {
        CircuitBreaker cb = circuitBreakersByInstrument.get(instrumentId);
        if (cb != null) {
            cb.reset();
            log.info("Circuit breaker reset for instrument {}", instrumentId);
        }
    }
    
    public Map<Integer, CircuitBreakerState> getAllStates() {
        Map<Integer, CircuitBreakerState> states = new ConcurrentHashMap<>();
        circuitBreakersByInstrument.forEach((id, cb) -> states.put(id, cb.getState()));
        return states;
    }
    
    private static class CircuitBreakerEventListener {
        void handleStateTransition(CircuitBreakerTransition transition) {
            log.info("Circuit breaker transition: {} -> {} for {}",
                     transition.getStateFrom(), 
                     transition.getStateTo(), 
                     transition.getCircuitBreakerName());
        }
        
        void handleFailureRateExceeded(CircuitBreakerEvent event) {
            log.warn("Failure rate exceeded for {}: {}%", 
                     event.getCircuitBreakerName(), 
                     event.getMetrics().getFailureRate());
        }
    }
}

// === ARCHIVO: src/main/java/com/trading/riskengine/killswitch/KillSwitchPolicy.java ===
package com.trading.riskengine.killswitch;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KillSwitchPolicy {
    private static final Logger log = LoggerFactory.getLogger(KillSwitchPolicy.class);
    private static final int DEFAULT_MAX_ORDERS_PER_SECOND = 100;
    private static final int DEFAULT_MAX_NOTIONAL_PER_SECOND = 10_000_000;
    private static final double DEFAULT_MAX_VOLATILITY = 0.60;
    private static final double VOLATILITY_SCALING_FACTOR = 1.5;
    private static final int WARMUP_PERIOD_SECONDS = 5;
    private static final double ANOMALY_DETECTION_THRESHOLD = 3.0;
    private static final int ANOMALY_WINDOW_SIZE = 50;
    
    private final Map<Integer, TraderPolicy> traderPolicies;
    private final Map<Integer, StrategyPolicy> strategyPolicies;
    private final AtomicInteger globalActiveTraders;
    private final AtomicLong globalOrderCount;
    private final Instant systemStartTime;
    private volatile boolean globalKillSwitchActive;
    private final AnomalyDetector anomalyDetector;
    
    public KillSwitchPolicy() {
        this.traderPolicies = new ConcurrentHashMap<>();
        this.strategyPolicies = new ConcurrentHashMap<>();
        this.globalActiveTraders = new AtomicInteger(0);
        this.globalOrderCount = new AtomicLong(0);
        this.systemStartTime = Instant.now();
        this.globalKillSwitchActive = false;
        this.anomalyDetector = new AnomalyDetector();
    }
    
    public boolean canSubmitOrder(int traderId, int strategyId, long orderValue, double currentVolatility) {
        if (globalKillSwitchActive) {
            log.warn("Global kill switch active. Order rejected for trader {}", traderId);
            return false;
        }
        if (isSystemInWarmup()) {
            log.debug("System in warmup period. Allowing order from trader {}", traderId);
            return true;
        }
        if (currentVolatility > DEFAULT_MAX_VOLATILITY) {
            log.warn("Volatility {} exceeds maximum {}. Rejecting order.", 
                     currentVolatility, DEFAULT_MAX_VOLATILITY);
            return false;
        }
        TraderPolicy traderPolicy = getOrCreateTraderPolicy(traderId);
        if (!traderPolicy.canSubmitOrder(orderValue, currentVolatility)) {
            activateTraderKillSwitch(traderId, "Rate limit exceeded");
            return false;
        }
        StrategyPolicy strategyPolicy = getOrCreateStrategyPolicy(strategyId);
        if (!strategyPolicy.canSubmitOrder(orderValue, currentVolatility)) {
            activateStrategyKillSwitch(strategyId, "Strategy risk limit exceeded");
            return false;
        }
        if (anomalyDetector.isAnomalous(traderId, orderValue)) {
            log.warn("Anomalous order pattern detected for trader {}. Rejecting order.", traderId);
            return false;
        }
        traderPolicy.recordOrder(orderValue);
        strategyPolicy.recordOrder(orderValue);
        globalOrderCount.incrementAndGet();
        return true;
    }
    
    private boolean isSystemInWarmup() {
        Duration elapsed = Duration.between(systemStartTime, Instant.now());
        return elapsed.getSeconds() < WARMUP_PERIOD_SECONDS;
    }
    
    private TraderPolicy getOrCreateTraderPolicy(int traderId) {
        return traderPolicies.computeIfAbsent(traderId, 
            k -> new TraderPolicy(DEFAULT_MAX_ORDERS_PER_SECOND, 
                                  DEFAULT_MAX_NOTIONAL_PER_SECOND));
    }
    
    private StrategyPolicy getOrCreateStrategyPolicy(int strategyId) {
        return strategyPolicies.computeIfAbsent(strategyId, 
            k -> new StrategyPolicy(DEFAULT_MAX_ORDERS_PER_SECOND * 2, 
                                    DEFAULT_MAX_NOTIONAL_PER_SECOND * 2));
    }
    
    private void activateTraderKillSwitch(int traderId, String reason) {
        TraderPolicy policy = traderPolicies.get(traderId);
        if (policy != null) {
            policy.activateKillSwitch(reason);
            log.error("Kill switch activated for trader {}: {}", traderId, reason);
        }
    }
    
    private void activateStrategyKillSwitch(int strategyId, String reason) {
        StrategyPolicy policy = strategyPolicies.get(strategyId);
        if (policy != null) {
            policy.activateKillSwitch(reason);
            log.error("Kill switch activated for strategy {}: {}", strategyId, reason);
        }
    }
    
    public void activateGlobalKillSwitch(String reason) {
        globalKillSwitchActive = true;
        log.error("GLOBAL KILL SWITCH ACTIVATED: {}", reason);
    }
    
    public void deactivateGlobalKillSwitch() {
        globalKillSwitchActive = false;
        log.info("Global kill switch deactivated");
    }
    
    public boolean isGlobalKillSwitchActive() {
        return globalKillSwitchActive;
    }
    
    public void resetTraderPolicy(int traderId) {
        TraderPolicy policy = traderPolicies.get(traderId);
        if (policy != null) {
            policy.reset();
            log.info("Policy reset for trader {}", traderId);
        }
    }
    
    public KillSwitchStatus getStatus(int traderId, int strategyId) {
        TraderPolicy traderPolicy = traderPolicies.get(traderId);
        StrategyPolicy strategyPolicy = strategyPolicies.get(strategyId);
        return new KillSwitchStatus(
            globalKillSwitchActive,
            traderPolicy != null ? traderPolicy.isKillSwitchActive() : false,
            strategyPolicy != null ? strategyPolicy.isKillSwitchActive() : false,
            traderPolicy != null ? traderPolicy.getCurrentOrderRate() : 0,
            strategyPolicy != null ? strategyPolicy.getCurrentOrderRate() : 0
        );
    }
    
    private static class TraderPolicy {
        private final int maxOrdersPerSecond;
        private final long maxNotionalPerSecond;
        private final AtomicInteger orderCount;
        private final AtomicLong notionalSum;
        private volatile boolean killSwitchActive;
        private volatile String killSwitchReason;
        private volatile Instant lastResetTime;
        
        TraderPolicy(int maxOrdersPerSecond, long maxNotionalPerSecond) {
            this.maxOrdersPerSecond = maxOrdersPerSecond;
            this.maxNotionalPerSecond = maxNotionalPerSecond;
            this.orderCount = new AtomicInteger(0);
            this.notionalSum = new AtomicLong(0);
            this.killSwitchActive = false;
            this.lastResetTime = Instant.now();
        }
        
        boolean canSubmitOrder(long orderValue, double volatility) {
            if (killSwitchActive) {
                return false;
            }
            double scaledMaxNotional = maxNotionalPerSecond * (1.0 - Math.min(volatility, 0.5) * VOLATILITY_SCALING_FACTOR);
            return orderCount.get() < maxOrdersPerSecond && 
                   notionalSum.get() < scaledMaxNotional;
        }
        
        void recordOrder(long orderValue) {
            orderCount.incrementAndGet();
            notionalSum.addAndGet(orderValue);
        }
        
        void activateKillSwitch(String reason) {
            this.killSwitchActive = true;
            this.killSwitchReason = reason;
        }
        
        void reset() {
            this.killSwitchActive = false;
            this.killSwitchReason = null;
            this.orderCount.set(0);
            this.notionalSum.set(0);
            this.lastResetTime = Instant.now();
        }
        
        boolean isKillSwitchActive() { return killSwitchActive; }
        int getCurrentOrderRate() { return orderCount.get(); }
    }
    
    private static class StrategyPolicy {
        private final int maxOrdersPerSecond;
        private final long maxNotionalPerSecond;
        private final AtomicInteger orderCount;
        private final AtomicLong notionalSum;
        private volatile boolean killSwitchActive;
        private volatile String killSwitchReason;
        
        StrategyPolicy(int maxOrdersPerSecond, long maxNotionalPerSecond) {
            this.maxOrdersPerSecond = maxOrdersPerSecond;
            this.maxNotionalPerSecond = maxNotionalPerSecond;
            this.orderCount = new AtomicInteger(0);
            this.notionalSum = new AtomicLong(0);
            this.killSwitchActive = false;
        }
        
        boolean canSubmitOrder(long orderValue, double volatility) {
            if (killSwitchActive) {
                return false;
            }
            double scaledMaxNotional = maxNotionalPerSecond * (1.0 - Math.min(volatility, 0.5) * VOLATILITY_SCALING_FACTOR);
            return orderCount.get() < maxOrdersPerSecond && 
                   notionalSum.get() < scaledMaxNotional;
        }
        
        void recordOrder(long orderValue) {
            orderCount.incrementAndGet();
            notionalSum.addAndGet(orderValue);
        }
        
        void activateKillSwitch(String reason) {
            this.killSwitchActive = true;
            this.killSwitchReason = reason;
        }
        
        boolean isKillSwitchActive() { return killSwitchActive; }
        int getCurrentOrderRate() { return orderCount.get(); }
    }
    
    private static class AnomalyDetector {
        private final Map<Integer, MovingStats> traderStats;
        
        AnomalyDetector() {
            this.traderStats = new ConcurrentHashMap<>();
        }
        
        boolean isAnomalous(int traderId, long orderValue) {
            MovingStats stats = traderStats.computeIfAbsent(traderId, k -> new MovingStats(ANOMALY_WINDOW_SIZE));
            stats.addSample((double) orderValue);
            if (stats.getSampleCount() < ANOMALY_WINDOW_SIZE) {
                return false;
            }
            double zScore = stats.getZScore(orderValue);
            return Math.abs(zScore) > ANOMALY_DETECTION_THRESHOLD;
        }
    }
    
    private static class MovingStats {
        private final double[] samples;
        private int index = 0;
        private int count = 0;
        private double sum = 0;
        private double sumSq = 0;
        
        MovingStats(int windowSize) {
            this.samples = new double[windowSize];
        }
        
        synchronized void addSample(double value) {
            if (count > 0) {
                double oldValue = samples[index % samples.length];
                sum -= oldValue;
                sumSq -= oldValue * oldValue;
            }
            samples[index % samples.length] = value;
            sum += value;
            sumSq += value * value;
            index++;
            count = Math.min(count + 1, samples.length);
        }
        
        double getMean() { return sum / count; }
        double getStdDev() {
            double mean = getMean();
            return Math.sqrt((sumSq / count) - (mean * mean));
        }
        double getZScore(double value) {
            double stdDev = getStdDev();
            return stdDev > 0 ? (value - getMean()) / stdDev : 0;
        }
        int getSampleCount() { return count; }
    }
    
    public record KillSwitchStatus(
        boolean globalActive,
        boolean traderActive,
        boolean strategyActive,
        int traderOrderRate,
        int strategyOrderRate
    ) {}
}

// === ARCHIVO: src/main/java/com/trading/riskengine/replay/DeterministicReplay.java ===
package com.trading.riskengine.replay;

import net.openhft.chronicle.queue.ChronicleQueue;
import net.openhft.chronicle.queue.ExcerptAppender;
import net.openhft.chronicle.queue.ExcerptTailer;
import net.openhft.chronicle.queue.RollCycle;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueueBuilder;
import net.openhft.chronicle.wire.DocumentContext;
import net.openhft.chronicle.wire.Wire;
import net.openhft.chronicle.wire.WireType;
import net.openhft.chronicle.bytes.Bytes;
import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeterministicReplay implements Closeable {
    private static final Logger log = LoggerFactory.getLogger(DeterministicReplay.class);
    private static final int DEFAULT_QUEUE_SIZE = 64 * 1024;
    private static final RollCycle DEFAULT_ROLL_CYCLE = RollCycle.DAILY;
    private static final String REPLAY_CHANNEL = "risk-events";
    private static final String AUDIT_CHANNEL = "audit-trail";
    
    private final Path eventLogPath;
    private final ChronicleQueue eventQueue;
    private final ChronicleQueue auditQueue;
    private final EventSerializer serializer;
    private final AtomicLong eventSequence;
    private final AtomicLong lastReplayPosition;
    private final ConcurrentHashMap<String, EventProcessor> eventProcessors;
    private volatile ReplayState currentState;
    
    public DeterministicReplay(Path eventLogPath) {
        this.eventLogPath = eventLogPath;
        this.serializer = new EventSerializer();
        this.eventSequence = new AtomicLong(0);
        this.lastReplayPosition = new AtomicLong(0);
        this.eventProcessors = new ConcurrentHashMap<>();
        this.eventQueue = createQueue(eventLogPath, REPLAY_CHANNEL);
        this.auditQueue = createQueue(eventLogPath.resolve("../audit"), AUDIT_CHANNEL);
        this.currentState = ReplayState.IDLE;
    }
    
    private ChronicleQueue createQueue(Path path, String channel) {
        return SingleChronicleQueueBuilder
            .builder(path.resolve(channel), WireType.FIELDLESS_BINARY)
            .rollCycle(DEFAULT_ROLL_CYCLE)
            .build();
    }
    
    public void recordEvent(RiskEvent event) {
        if (currentState == ReplayState.REPLAYING) {
            throw new IllegalStateException("Cannot record events during replay");
        }
        long sequence = eventSequence.incrementAndGet();
        event.setSequence(sequence);
        event.setTimestamp(Instant.now());
        try (ExcerptAppender appender = eventQueue.acquireAppender()) {
            appender.writeDocument(wire -> serializer.writeEvent(wire, event));
        }
        recordAuditEvent(event);
        log.debug("Recorded event {} with sequence {}", event.getEventType(), sequence);
    }
    
    private void recordAuditEvent(RiskEvent event) {
        AuditRecord audit = new AuditRecord(
            event.getSequence(),
            event.getTimestamp(),
            event.getEventType(),
            event.getInstrumentId(),
            event.getTraderId(),
            event.getStrategyId(),
            event.getOrderValue(),
            event.getDecision(),
            event.getReason()
        );
        try (ExcerptAppender appender = auditQueue.acquireAppender()) {
            appender.writeDocument(wire -> serializer.writeAuditRecord(wire, audit));
        }
    }
    
    public void replay(Consumer<RiskEvent> eventHandler) {
        if (currentState == ReplayState.REPLAYING) {
            throw new IllegalStateException("Replay already in progress");
        }
        currentState = ReplayState.REPLAYING;
        log.info("Starting deterministic replay from position {}", lastReplayPosition.get());
        try (ExcerptTailer tailer = eventQueue.createTailer()) {
            tailer.moveToIndex(lastReplayPosition.get());
            long processedCount = 0;
            while (true) {
                try (DocumentContext ctx = tailer.readingDocument()) {
                    if (!ctx.isPresent()) {
                        break;
                    }
                    Wire wire = ctx.wire();
                    RiskEvent event = serializer.readEvent(wire);
                    processEvent(event, eventHandler);
                    processedCount++;
                    lastReplayPosition.set(ctx.index());
                }
            }
            log.info("Replay completed. Processed {} events. Final position: {}", 
                     processedCount, lastReplayPosition.get());
        } catch (Exception e) {
            log.error("Error during replay", e);
            throw new RuntimeException("Replay failed", e);
        } finally {
            currentState = ReplayState.IDLE;
        }
    }
    
    private void processEvent(RiskEvent event, Consumer<RiskEvent> handler) {
        String processorKey = event.getEventType();
        EventProcessor processor = eventProcessors.get(processorKey);
        if (processor != null) {
            processor.process(event);
        }
        handler.accept(event);
    }
    
    public void registerProcessor(String eventType, EventProcessor processor) {
        eventProcessors.put(eventType, processor);
    }
    
    public void replayFromTimestamp(Instant fromTimestamp, Consumer<RiskEvent> eventHandler) {
        currentState = ReplayState.REPLAYING;
        log.info("Starting replay from timestamp {}", fromTimestamp);
        try (ExcerptTailer tailer = eventQueue.createTailer()) {
            long processedCount = 0;
            while (true) {
                try (DocumentContext ctx = tailer.readingDocument()) {
                    if (!ctx.isPresent()) {
                        break;
                    }
                    Wire wire = ctx.wire();
                    RiskEvent event = serializer.readEvent(wire);
                    if (event.getTimestamp().isBefore(fromTimestamp)) {
                        continue;
                    }
                    processEvent(event, eventHandler);
                    processedCount++;
                }
            }
            log.info("Timestamp replay completed. Processed {} events", processedCount);
        } catch (Exception e) {
            log.error("Error during timestamp replay", e);
            throw new RuntimeException("Timestamp replay failed", e);
        } finally {
            currentState = ReplayState.IDLE;
        }
    }
    
    public List<AuditRecord> getAuditTrail(int traderId) {
        List<AuditRecord> records = new ArrayList<>();
        try (ExcerptTailer tailer = auditQueue.createTailer()) {
            while (true) {
                try (DocumentContext ctx = tailer.readingDocument()) {
                    if (!ctx.isPresent()) {
                        break;
                    }
                    AuditRecord record = serializer.readAuditRecord(ctx.wire());
                    if (record.traderId() == traderId) {
                        records.add(record);
                    }
                }
            }
        }
        return records;
    }
    
    public long getEventCount() {
        return eventSequence.get();
    }
    
    public ReplayState getCurrentState() {
        return currentState;
    }
    
    public void resetToBeginning() {
        lastReplayPosition.set(0);
        log.info("Replay position reset to beginning");
    }
    
    @Override
    public void close() throws IOException {
        eventQueue.close();
        auditQueue.close();
        log.info("DeterministicReplay closed. Total events recorded: {}", eventSequence.get());
    }
    
    public enum ReplayState {
        IDLE,
        REPLAYING,
        PAUSED
    }
    
    @FunctionalInterface
    public interface EventProcessor {
        void process(RiskEvent event);
    }
}

// === ARCHIVO: src/main/java/com/trading/riskengine/replay/RiskEvent.java ===
package com.trading.riskengine.replay;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RiskEvent {
    private long sequence;
    private Instant timestamp;
    private String eventType;
    private int instrumentId;
    private int traderId;
    private int strategyId;
    private long orderValue;
    private String decision;
    private String reason;
    private double volatility;
    private Map<String, Object> metadata;
    
    public RiskEvent() {
        this.metadata = new ConcurrentHashMap<>();
    }
    
    public RiskEvent(String eventType, int instrumentId, int traderId, int strategyId, 
                     long orderValue, String decision, String reason) {
        this();
        this.eventType = eventType;
        this.instrumentId = instrumentId;
        this.traderId = traderId;
        this.strategyId = strategyId;
        this.orderValue = orderValue;
        this.decision = decision;
        this.reason = reason;
    }
    
    public long getSequence() { return sequence; }
    public void setSequence(long sequence) { this.sequence = sequence; }
    
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
    
    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    
    public int getInstrumentId() { return instrumentId; }
    public void setInstrumentId(int instrumentId) { this.instrumentId = instrumentId; }
    
    public int getTraderId() { return traderId; }
    public void setTraderId(int traderId) { this.traderId = traderId; }
    
    public int getStrategyId() { return strategyId; }
    public void setStrategyId(int strategyId) { this.strategyId = strategyId; }
    
    public long getOrderValue() { return orderValue; }
    public void setOrderValue(long orderValue) { this.orderValue = orderValue; }
    
    public String getDecision() { return decision; }
    public void setDecision(String decision) { this.decision = decision; }
    
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    
    public double getVolatility() { return volatility; }
    public void setVolatility(double volatility) { this.volatility = volatility; }
    
    public Map<String, Object> getMetadata() { return metadata; }
    public void setMetadata(Map<String, Object> metadata) { this.metadata = metadata; }
    
    public void addMetadata(String key, Object value) {
        this.metadata.put(key, value);
    }
}

// === ARCHIVO: src/main/java/com/trading/riskengine/replay/AuditRecord.java ===
package com.trading.riskengine.replay;

import java.time.Instant;

public record AuditRecord(
    long sequence,
    Instant timestamp,
    String eventType,
    int instrumentId,
    int traderId,
    int strategyId,
    long orderValue,
    String decision,
    String reason
) {
    public String toMiFIDIIFormat() {
        return String.format(
            "MIFID2_SEQ=%d|TIME=%s|EVT=%s|INST=%d|TRD=%d|STRAT=%d|VAL=%d|DEC=%s|RSN=%s",
            sequence, timestamp, eventType, instrumentId, traderId, 
            strategyId, orderValue, decision, reason
        );
    }
}

// === ARCHIVO: src/main/java/com/trading/riskengine/replay/EventSerializer.java ===
package com.trading.riskengine.replay;

import net.openhft.chronicle.wire.Wire;
import net.openhft.chronicle.wire.WireIn;
import net.openhft.chronicle.wire.WireOut;
import net.openhft.chronicle.wireMarshallable;
import java.time.Instant;

public class EventSerializer {
    
    public void writeEvent(Wire wire, RiskEvent event) {
        wire.write("seq").int64(event.getSequence());
        wire.write("ts").int64(event.getTimestamp().toEpochMilli());
        wire.write("type").text(event.getEventType());
        wire.write("inst").int32(event.getInstrumentId());
        wire.write("trader").int32(event.getTraderId());
        wire.write("strat").int32(event.getStrategyId());
        wire.write("val").int64(event.getOrderValue());
        wire.write("dec").text(event.getDecision());
        wire.write("rsn").text(event.getReason());
        wire.write("vol").float64(event.getVolatility());
    }
    
    public RiskEvent readEvent(Wire wire) {
        RiskEvent event = new RiskEvent();
        WireIn in = wire.read("seq");
        event.setSequence(in.int64());
        long epochMilli = wire.read("ts").int64();
        event.setTimestamp(Instant.ofEpochMilli(epochMilli));
        event.setEventType(wire.read("type").text());
        event.setInstrumentId(wire.read("inst").int32());
        event.setTraderId(wire.read("trader").int32());
        event.setStrategyId(wire.read("strat").int32());
        event.setOrderValue(wire.read("val").int64());
        event.setDecision(wire.read("dec").text());
        event.setReason(wire.read("rsn").text());
        event.setVolatility(wire.read("vol").float64());
        return event;
    }
    
    public void writeAuditRecord(Wire wire, AuditRecord record) {
        wire.write("seq").int64(record.sequence());
        wire.write("ts").int64(record.timestamp().toEpochMilli());
        wire.write("type").text(record.eventType());
        wire.write("inst").int32(record.instrumentId());
        wire.write("trader").int32(record.traderId());
        wire.write("strat").int32(record.strategyId());
        wire.write("val").int64(record.orderValue());
        wire.write("dec").text(record.decision());
        wire.write("rsn").text(record.reason());
    }
    
    public AuditRecord readAuditRecord(Wire wire) {
        long seq = wire.read("seq").int64();
        long epochMilli = wire.read("ts").int64();
        Instant ts = Instant.ofEpochMilli(epochMilli);
        String type = wire.read("type").text();
        int inst = wire.read("inst").int32();
        int trader = wire.read("trader").int32();
        int strat = wire.read("strat").int32();
        long val = wire.read("val").int64();
        String dec = wire.read("dec").text();
        String rsn = wire.read("rsn").text();
        return new AuditRecord(seq, ts, type, inst, trader, strat, val, dec, rsn);
    }
}

// === ARCHIVO: src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java ===
package com.trading.riskengine.compliance;

import net.openhft.chronicle.queue.ChronicleQueue;
import net.openhft.chronicle.queue.ExcerptAppender;
import net.openhft.chronicle.queue.ExcerptReader;
import net.openhft.chronicle.wire.DocumentContext;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MiFIDIITracer {
    private static final DateTimeFormatter TIMESTAMP_FORMAT = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("UTC"));
    
    private final ChronicleQueue auditQueue;
    private final ConcurrentHashMap<String, RiskDecision> activeDecisions;
    private final AtomicLong decisionCounter;
    private final String complianceEndpoint;
    private final boolean synchronousWrite;
    
    public MiFIDIITracer(String queuePath, boolean synchronousWrite) {
        this.auditQueue = ChronicleQueue.singleBuilder(queuePath)
            .blockSize(256 << 10)
            .writeBufferMode(ChronicleQueue.WriteBufferMode.asynchronous)
            .build();
        this.activeDecisions = new ConcurrentHashMap<>();
        this.decisionCounter = new AtomicLong(0);
        this.synchronousWrite = synchronousWrite;
        this.complianceEndpoint = "https://compliance.trading.internal/audit/v2";
    }
    
    public String recordRiskDecision(RiskDecision decision) {
        String decisionId = generateDecisionId(decision);
        decision.setDecisionId(decisionId);
        decision.setTimestamp(Instant.now().toString());
        decision.setComplianceRequirement("MiFIDII-2018-ART27");
        
        activeDecisions.put(decisionId, decision);
        
        try {
            appendToQueue(decision);
            if (synchronousWrite) {
                auditQueue.sync();
            }
        } catch (Exception e) {
            decision.setRecordingStatus(RecordingStatus.FAILED);
            decision.setFailureReason("Queue write error: " + e.getMessage());
        }
        
        return decisionId;
    }
    
    private void appendToQueue(RiskDecision decision) {
        ExcerptAppender appender = auditQueue.acquireAppender();
        try (DocumentContext ctx = appender.writingDocument()) {
            ctx.wire().write("decisionId").text(decision.getDecisionId());
            ctx.wire().write("timestamp").text(decision.getTimestamp());
            ctx.wire().write("traderId").int32(decision.getTraderId());
            ctx.wire().write("strategyId").int32(decision.getStrategyId());
            ctx.wire().write("instrumentId").text(decision.getInstrumentId());
            ctx.wire().write("orderId").text(decision.getOrderId());
            ctx.wire().write("decision").text(decision.getDecision().name());
            ctx.wire().write("riskScore").double64(decision.getRiskScore());
            ctx.wire().write("exposure").int64(decision.getExposure());
            ctx.wire().write("varLimit").int64(decision.getVarLimit());
            ctx.wire().write("volatility").double64(decision.getVolatility());
            ctx.wire().write("circuitBreakerState").text(decision.getCircuitBreakerState());
            ctx.wire().write("complianceRequirement").text(decision.getComplianceRequirement());
            ctx.wire().write("justification").text(decision.getJustification());
            ctx.wire().write("replayToken").text(decision.getReplayToken());
        }
        decision.setRecordingStatus(RecordingStatus.RECORDED);
    }
    
    private String generateDecisionId(RiskDecision decision) {
        long sequence = decisionCounter.incrementAndGet();
        String uniquePart = UUID.randomUUID().toString().substring(0, 8);
        return String.format("DEC-%d-%s-%d", 
            System.currentTimeMillis(), uniquePart, sequence);
    }
    
    public RiskDecision getDecision(String decisionId) {
        RiskDecision cached = activeDecisions.get(decisionId);
        if (cached != null) {
            return cached;
        }
        return searchHistorical(decisionId);
    }
    
    private RiskDecision searchHistorical(String decisionId) {
        ExcerptReader reader = auditQueue.createTailer();
        while (reader.readDocument() != null) {
            String id = reader.document().wire().read("decisionId").text();
            if (decisionId.equals(id)) {
                return mapToRiskDecision(reader.document().wire());
            }
        }
        return null;
    }
    
    private RiskDecision mapToRiskDecision(net.openhft.chronicle.wire.WireIn wire) {
        RiskDecision decision = new RiskDecision();
        decision.setDecisionId(wire.read("decisionId").text());
        decision.setTimestamp(wire.read("timestamp").text());
        decision.setTraderId(wire.read("traderId").int32());
        decision.setStrategyId(wire.read("strategyId").int32());
        decision.setInstrumentId(wire.read("instrumentId").text());
        decision.setOrderId(wire.read("orderId").text());
        decision.setDecision(RiskDecisionType.valueOf(wire.read("decision").text()));
        decision.setRiskScore(wire.read("riskScore").double64());
        decision.setExposure(wire.read("exposure").int64());
        decision.setVarLimit(wire.read("varLimit").int64());
        decision.setVolatility(wire.read("volatility").double64());
        decision.setCircuitBreakerState(wire.read("circuitBreakerState").text());
        decision.setComplianceRequirement(wire.read("complianceRequirement").text());
        decision.setJustification(wire.read("justification").text());
        decision.setReplayToken(wire.read("replayToken").text());
        decision.setRecordingStatus(RecordingStatus.RECORDED);
        return decision;
    }
    
    public void recordCircuitBreakerEvent(String instrumentId, String state, String reason) {
        RiskDecision event = new RiskDecision();
        event.setInstrumentId(instrumentId);
        event.setDecision(RiskDecisionType.CIRCUIT_BREAKER_TRIGGERED);
        event.setCircuitBreakerState(state);
        event.setJustification(reason);
        event.setComplianceRequirement("MiFIDII-2018-ART27-4");
        recordRiskDecision(event);
    }
    
    public void recordKillSwitchEvent(String strategyId, String trigger, String details) {
        RiskDecision event = new RiskDecision();
        event.setStrategyId(Integer.parseInt(strategyId.replaceAll("[^0-9]", "")));
        event.setDecision(RiskDecisionType.KILL_SWITCH_ACTIVATED);
        event.setJustification("Trigger: " + trigger + ". Details: " + details);
        event.setComplianceRequirement("MiFIDII-2018-ART27-5");
        recordRiskDecision(event);
    }
    
    public void recordVaRLimitBreach(int traderId, int strategyId, long exposure, long varLimit) {
        RiskDecision event = new RiskDecision();
        event.setTraderId(traderId);
        event.setStrategyId(strategyId);
        event.setExposure(exposure);
        event.setVarLimit(varLimit);
        event.setDecision(RiskDecisionType.VAR_LIMIT_EXCEEDED);
        event.setJustification(String.format("Exposure %d exceeds VaR limit %d (%.2f%%)", 
            exposure, varLimit, ((double)(exposure - varLimit) / varLimit) * 100));
        event.setComplianceRequirement("MiFIDII-2018-ART27-2");
        recordRiskDecision(event);
    }
    
    public String generateAuditReport(String startTime, String endTime) {
        StringBuilder report = new StringBuilder();
        report.append("MiFID II Audit Report\n");
        report.append("Period: ").append(startTime).append(" to ").append(endTime).append("\n");
        report.append("Generated: ").append(Instant.now()).append("\n");
        report.append("=\".repeat(80)).append("\n");
        
        long totalDecisions = 0;
        long approvedOrders = 0;
        long rejectedOrders = 0;
        long circuitBreakerEvents = 0;
        long killSwitchEvents = 0;
        
        ExcerptReader reader = auditQueue.createTailer();
        while (reader.readDocument() != null) {
            String timestamp = reader.document().wire().read("timestamp").text();
            if (timestamp.compareTo(startTime) >= 0 && timestamp.compareTo(endTime) <= 0) {
                totalDecisions++;
                String decision = reader.document().wire().read("decision").text();
                switch (decision) {
                    case "APPROVED" -> approvedOrders++;
                    case "REJECTED" -> rejectedOrders++;
                    case "CIRCUIT_BREAKER_TRIGGERED" -> circuitBreakerEvents++;
                    case "KILL_SWITCH_ACTIVATED" -> killSwitchEvents++;
                }
            }
        }
        
        report.append(String.format("Total Risk Decisions: %d\n", totalDecisions));
        report.append(String.format("Approved Orders: %d\n", approvedOrders));
        report.append(String.format("Rejected Orders: %d\n", rejectedOrders));
        report.append(String.format("Circuit Breaker Events: %d\n", circuitBreakerEvents));
        report.append(String.format("Kill Switch Events: %d\n", killSwitchEvents));
        
        return report.toString();
    }
    
    public void close() {
        auditQueue.close();
    }
    
    public enum RiskDecisionType {
        APPROVED,
        REJECTED,
        VAR_LIMIT_EXCEEDED,
        CIRCUIT_BREAKER_TRIGGERED,
        KILL_SWITCH_ACTIVATED,
        VOLATILITY_THRESHOLD_BREACH,
        POSITION_LIMIT_EXCEEDED
    }
    
    public enum RecordingStatus {
        PENDING,
        RECORDED,
        FAILED,
        REPLAYED
    }
    
    public static class RiskDecision {
        private String decisionId;
        private String timestamp;
        private int traderId;
        private int strategyId;
        private String instrumentId;
        private String orderId;
        private RiskDecisionType decision;
        private double riskScore;
        private long exposure;
        private long varLimit;
        private double volatility;
        private String circuitBreakerState;
        private String complianceRequirement;
        private String justification;
        private String replayToken;
        private RecordingStatus recordingStatus;
        private String failureReason;
        
        public RiskDecision() {
            this.recordingStatus = RecordingStatus.PENDING;
        }
        
        public String getDecisionId() { return decisionId; }
        public void setDecisionId(String decisionId) { this.decisionId = decisionId; }
        public String getTimestamp() { return timestamp; }
        public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
        public int getTraderId() { return traderId; }
        public void setTraderId(int traderId) { this.traderId = traderId; }
        public int getStrategyId() { return strategyId; }
        public void setStrategyId(int strategyId) { this.strategyId = strategyId; }
        public String getInstrumentId() { return instrumentId; }
        public void setInstrumentId(String instrumentId) { this.instrumentId = instrumentId; }
        public String getOrderId() { return orderId; }
        public void setOrderId(String orderId) { this.orderId = orderId; }
        public RiskDecisionType getDecision() { return decision; }
        public void setDecision(RiskDecisionType decision) { this.decision = decision; }
        public double getRiskScore() { return riskScore; }
        public void setRiskScore(double riskScore) { this.riskScore = riskScore; }
        public long getExposure() { return exposure; }
        public void setExposure(long exposure) { this.exposure = exposure; }
        public long getVarLimit() { return varLimit; }
        public void setVarLimit(long varLimit) { this.varLimit = varLimit; }
        public double getVolatility() { return volatility; }
        public void setVolatility(double volatility) { this.volatility = volatility; }
        public String getCircuitBreakerState() { return circuitBreakerState; }
        public void setCircuitBreakerState(String circuitBreakerState) { this.circuitBreakerState = circuitBreakerState; }
        public String getComplianceRequirement() { return complianceRequirement; }
        public void setComplianceRequirement(String complianceRequirement) { this.complianceRequirement = complianceRequirement; }
        public String getJustification() { return justification; }
        public void setJustification(String justification) { this.justification = justification; }
        public String getReplayToken() { return replayToken; }
        public void setReplayToken(String replayToken) { this.replayToken = replayToken; }
        public RecordingStatus getRecordingStatus() { return recordingStatus; }
        public void setRecordingStatus(RecordingStatus recordingStatus) { this.recordingStatus = recordingStatus; }
        public String getFailureReason() { return failureReason; }
        public void setFailureReason(String failureReason) { this.failureReason = failureReason; }
    }
}

// === ARCHIVO: src/test/java/com/trading/riskengine/engine/RiskEngineLatencyTest.java ===
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
        this.riskEngine = new RiskEngine(0, shardCount, varModel);
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
        RiskResult result = riskEngine.evaluateRisk(event);
        bh.consume(result);
    }

    @Benchmark
    public void measureBatchRiskEvaluation(Blackhole bh) {
        RiskResult[] results = new RiskResult[10];
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

    public static class RiskResult {
        private final boolean approved;
        private final double riskScore;
        private final long varAmount;
        private final double volatility;

        public RiskResult(boolean approved, double riskScore, long varAmount, double volatility) {
            this.approved = approved;
            this.riskScore = riskScore;
            this.varAmount = varAmount;
            this.volatility = volatility;
        }

        public boolean isApproved() {
            return approved;
        }

        public double getRiskScore() {
            return riskScore;
        }

        public long getVarAmount() {
            return varAmount;
        }

        public double getVolatility() {
            return volatility;
        }
    }
}

// === ARCHIVO: src/test/java/com/trading/riskengine/circuitbreaker/DynamicCircuitBreakerTest.java ===
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

// === ARCHIVO: src/test/java/com/trading/riskengine/killswitch/KillSwitchPolicyTest.java ===
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

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.trading</groupId>
    <artifactId>risk-engine</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>com.lmax</groupId>
            <artifactId>disruptor</artifactId>
            <version>4.0.0</version>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-circuitbreaker</artifactId>
            <version>2.1.0</version>
        </dependency>
        <dependency>
            <groupId>net.openhft</groupId>
            <artifactId>chronicle-queue</artifactId>
            <version>5.24.12</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>2.0.16</version>
        </dependency>
        <dependency>
            <groupId>org.openjdk.jmh</groupId>
            <artifactId>jmh-core</artifactId>
            <version>1.36</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.10.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>21</source>
                    <target>21</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.3.0</version>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/trading/riskengine/replay/AuditRecord.java ===
package com.trading.riskengine.replay;

import java.time.Instant;

public record AuditRecord(
    long sequence,
    Instant timestamp,
    String eventType,
    int instrumentId,
    int traderId,
    int strategyId,
    long orderValue,
    String decision,
    String reason
) {
    public String toMiFIDIIFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append("MIFIDII_REPORT|");
        sb.append(timestamp != null ? timestamp.toString() : "N/A").append("|");
        sb.append(traderId).append("|");
        sb.append(instrumentId).append("|");
        sb.append(strategyId).append("|");
        sb.append(eventType != null ? eventType : "UNKNOWN").append("|");
        sb.append(orderValue).append("|");
        sb.append(decision != null ? decision : "N/A").append("|");
        sb.append(reason != null ? reason : "N/A");
        return sb.toString();
    }
}

// === ARCHIVO: src/main/java/com/trading/riskengine/killswitch/KillSwitchPolicy.java ===
package com.trading.riskengine.killswitch;

import com.trading.riskengine.engine.RiskEngine.Order;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KillSwitchPolicy {
    private static final Logger log = LoggerFactory.getLogger(KillSwitchPolicy.class);
    private static final int DEFAULT_MAX_ORDERS_PER_SECOND = 100;
    private static final int DEFAULT_MAX_NOTIONAL_PER_SECOND = 10_000_000;
    private static final double DEFAULT_MAX_VOLATILITY = 0.60;
    private static final double VOLATILITY_SCALING_FACTOR = 1.5;
    private static final int WARMUP_PERIOD_SECONDS = 5;
    private static final double ANOMALY_DETECTION_THRESHOLD = 3.0;
    private static final int ANOMALY_WINDOW_SIZE = 50;
    
    private final Map<Integer, TraderPolicy> traderPolicies;
    private final Map<Integer, StrategyPolicy> strategyPolicies;
    private final AtomicInteger globalActiveTraders;
    private final AtomicLong globalOrderCount;
    private final Instant systemStartTime;
    private volatile boolean globalKillSwitchActive;
    private final AnomalyDetector anomalyDetector;
    
    public KillSwitchPolicy() {
        this.traderPolicies = new ConcurrentHashMap<>();
        this.strategyPolicies = new ConcurrentHashMap<>();
        this.globalActiveTraders = new AtomicInteger(0);
        this.globalOrderCount = new AtomicLong(0);
        this.systemStartTime = Instant.now();
        this.globalKillSwitchActive = false;
        this.anomalyDetector = new AnomalyDetector();
    }
    
    public boolean canSubmitOrder(int traderId, int strategyId, long orderValue, double currentVolatility) {
        if (globalKillSwitchActive) {
            log.warn("Global kill switch active. Order rejected for trader {}", traderId);
            return false;
        }
        if (isSystemInWarmup()) {
            log.debug("System in warmup period. Allowing order from trader {}", traderId);
            return true;
        }
        if (currentVolatility > DEFAULT_MAX_VOLATILITY) {
            log.warn("Volatility {} exceeds maximum {}. Rejecting order.", 
                     currentVolatility, DEFAULT_MAX_VOLATILITY);
            return false;
        }
        TraderPolicy traderPolicy = getOrCreateTraderPolicy(traderId);
        if (!traderPolicy.canSubmitOrder(orderValue, currentVolatility)) {
            activateTraderKillSwitch(traderId, "Rate limit exceeded");
            return false;
        }
        StrategyPolicy strategyPolicy = getOrCreateStrategyPolicy(strategyId);
        if (!strategyPolicy.canSubmitOrder(orderValue, currentVolatility)) {
            activateStrategyKillSwitch(strategyId, "Strategy risk limit exceeded");
            return false;
        }
        if (anomalyDetector.isAnomalous(traderId, orderValue)) {
            log.warn("Anomalous order pattern detected for trader {}. Rejecting order.", traderId);
            return false;
        }
        traderPolicy.recordOrder(orderValue);
        strategyPolicy.recordOrder(orderValue);
        globalOrderCount.incrementAndGet();
        return true;
    }
    
    private boolean isSystemInWarmup() {
        Duration elapsed = Duration.between(systemStartTime, Instant.now());
        return elapsed.getSeconds() < WARMUP_PERIOD_SECONDS;
    }
    
    private TraderPolicy getOrCreateTraderPolicy(int traderId) {
        return traderPolicies.computeIfAbsent(traderId, 
            k -> new TraderPolicy(DEFAULT_MAX_ORDERS_PER_SECOND, 
                                  DEFAULT_MAX_NOTIONAL_PER_SECOND));
    }
    
    private StrategyPolicy getOrCreateStrategyPolicy(int strategyId) {
        return strategyPolicies.computeIfAbsent(strategyId, 
            k -> new StrategyPolicy(DEFAULT_MAX_ORDERS_PER_SECOND * 2, 
                                    DEFAULT_MAX_NOTIONAL_PER_SECOND * 2));
    }
    
    private void activateTraderKillSwitch(int traderId, String reason) {
        TraderPolicy policy = traderPolicies.get(traderId);
        if (policy != null) {
            policy.activateKillSwitch(reason);
            log.error("Kill switch activated for trader {}: {}", traderId, reason);
        }
    }
    
    private void activateStrategyKillSwitch(int strategyId, String reason) {
        StrategyPolicy policy = strategyPolicies.get(strategyId);
        if (policy != null) {
            policy.activateKillSwitch(reason);
            log.error("Kill switch activated for strategy {}: {}", strategyId, reason);
        }
    }
    
    public void activateGlobalKillSwitch(String reason) {
        globalKillSwitchActive = true;
        log.error("GLOBAL KILL SWITCH ACTIVATED: {}", reason);
    }
    
    public void deactivateGlobalKillSwitch() {
        globalKillSwitchActive = false;
        log.info("Global kill switch deactivated");
    }
    
    public boolean isGlobalKillSwitchActive() {
        return globalKillSwitchActive;
    }
    
    public void resetTraderPolicy(int traderId) {
        TraderPolicy policy = traderPolicies.get(traderId);
        if (policy != null) {
            policy.reset();
            log.info("Policy reset for trader {}", traderId);
        }
    }
    
    public KillSwitchStatus getStatus(int traderId, int strategyId) {
        TraderPolicy traderPolicy = traderPolicies.get(traderId);
        StrategyPolicy strategyPolicy = strategyPolicies.get(strategyId);
        return new KillSwitchStatus(
            globalKillSwitchActive,
            traderPolicy != null ? traderPolicy.isKillSwitchActive() : false,
            strategyPolicy != null ? strategyPolicy.isKillSwitchActive() : false,
            traderPolicy != null ? traderPolicy.getCurrentOrderRate() : 0,
            strategyPolicy != null ? strategyPolicy.getCurrentOrderRate() : 0
        );
    }
    
    private static class TraderPolicy {
        private final int maxOrdersPerSecond;
        private final long maxNotionalPerSecond;
        private final AtomicInteger orderCount;
        private final AtomicLong notionalSum;
        private volatile boolean killSwitchActive;
        private volatile String killSwitchReason;
        private volatile Instant lastResetTime;
        
        TraderPolicy(int maxOrdersPerSecond, long maxNotionalPerSecond) {
            this.maxOrdersPerSecond = maxOrdersPerSecond;
            this.maxNotionalPerSecond = maxNotionalPerSecond;
            this.orderCount = new AtomicInteger(0);
            this.notionalSum = new AtomicLong(0);
            this.killSwitchActive = false;
            this.lastResetTime = Instant.now();
        }
        
        boolean canSubmitOrder(long orderValue, double volatility) {
            if (killSwitchActive) {
                return false;
            }
            double scaledMaxNotional = maxNotionalPerSecond * (1.0 - Math.min(volatility, 0.5) * VOLATILITY_SCALING_FACTOR);
            return orderCount.get() < maxOrdersPerSecond && 
                   notionalSum.get() < scaledMaxNotional;
        }
        
        void recordOrder(long orderValue) {
            orderCount.incrementAndGet();
            notionalSum.addAndGet(orderValue);
        }
        
        void activateKillSwitch(String reason) {
            this.killSwitchActive = true;
            this.killSwitchReason = reason;
        }
        
        void reset() {
            this.killSwitchActive = false;
            this.killSwitchReason = null;
            this.orderCount.set(0);
            this.notionalSum.set(0);
            this.lastResetTime = Instant.now();
        }
        
        boolean isKillSwitchActive() { return killSwitchActive; }
        int getCurrentOrderRate() { return orderCount.get(); }
    }
    
    private static class StrategyPolicy {
        private final int maxOrdersPerSecond;
        private final long maxNotionalPerSecond;
        private final AtomicInteger orderCount;
        private final AtomicLong notionalSum;
        private volatile boolean killSwitchActive;
        private volatile String killSwitchReason;
        
        StrategyPolicy(int maxOrdersPerSecond, long maxNotionalPerSecond) {
            this.maxOrdersPerSecond = maxOrdersPerSecond;
            this.maxNotionalPerSecond = maxNotionalPerSecond;
            this.orderCount = new AtomicInteger(0);
            this.notionalSum = new AtomicLong(0);
            this.killSwitchActive = false;
        }
        
        boolean canSubmitOrder(long orderValue, double volatility) {
            if (killSwitchActive) {
                return false;
            }
            double scaledMaxNotional = maxNotionalPerSecond * (1.0 - Math.min(volatility, 0.5) * VOLATILITY_SCALING_FACTOR);
            return orderCount.get() < maxOrdersPerSecond && 
                   notionalSum.get() < scaledMaxNotional;
        }
        
        void recordOrder(long orderValue) {
            orderCount.incrementAndGet();
            notionalSum.addAndGet(orderValue);
        }
        
        void activateKillSwitch(String reason) {
            this.killSwitchActive = true;
            this.killSwitchReason = reason;
        }
        
        boolean isKillSwitchActive() { return killSwitchActive; }
        int getCurrentOrderRate() { return orderCount.get(); }
    }
    
    private static class AnomalyDetector {
        private final Map<Integer, MovingStats> traderStats;
        
        AnomalyDetector() {
            this.traderStats = new ConcurrentHashMap<>();
        }
        
        boolean isAnomalous(int traderId, long orderValue) {
            MovingStats stats = traderStats.computeIfAbsent(traderId, k -> new MovingStats(ANOMALY_WINDOW_SIZE));
            stats.addSample((double) orderValue);
            if (stats.getSampleCount() < ANOMALY_WINDOW_SIZE) {
                return false;
            }
            double zScore = stats.getZScore(orderValue);
            return Math.abs(zScore) > ANOMALY_DETECTION_THRESHOLD;
        }
    }
    
    private static class MovingStats {
        private final double[] samples;
        private int index = 0;
        private int count = 0;
        private double sum = 0;
        private double sumSq = 0;
        
        MovingStats(int windowSize) {
            this.samples = new double[windowSize];
        }
        
        synchronized void addSample(double value) {
            if (count > 0) {
                double oldValue = samples[index % samples.length];
                sum -= oldValue;
                sumSq -= oldValue * oldValue;
            }
            samples[index % samples.length] = value;
            sum += value;
            sumSq += value * value;
            index++;
            count = Math.min(count + 1, samples.length);
        }
        
        double getMean() { return sum / count; }
        double getStdDev() {
            double mean = getMean();
            return Math.sqrt((sumSq / count) - (mean * mean));
        }
        double getZScore(double value) {
            double stdDev = getStdDev();
            return stdDev > 0 ? (value - getMean()) / stdDev : 0;
        }
        int getSampleCount() { return count; }
    }
    
    public record KillSwitchStatus(
        boolean globalActive,
        boolean traderActive,
        boolean strategyActive,
        int traderOrderRate,
        int strategyOrderRate
    ) {}
}


// === ARCHIVO: src/main/java/com/trading/riskengine/Main.java ===
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

// === ARCHIVO: src/main/java/com/trading/riskengine/feed/MarketDataEvent.java ===
package com.trading.riskengine.feed;

import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.Arrays;
import java.lang.invoke.VarHandle;

/**
 * Evento de datos de mercado para el Disruptor.
 * Utiliza VarHandle para actualizaciones atómicas de precio y tamaño.
 */
public class MarketDataEvent {

    private static final VarHandle PRICE_HANDLE;
    private static final VarHandle SIZE_HANDLE;

    static {
        try {
            PRICE_HANDLE = MethodHandles.lookup().findVarHandle(MarketDataEvent.class, "priceLevels", long[].class);
            SIZE_HANDLE = MethodHandles.lookup().findVarHandle(MarketDataEvent.class, "sizeLevels", long[].class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize VarHandles", e);
        }
    }

    public enum EventType {
        ORDER_BOOK_UPDATE,
        TRADE,
        TRADE_CANCEL,
        ORDER_BOOK_SNAPSHOT
    }

    public enum Side {
        BID,
        ASK
    }

    private volatile long sequence;
    private volatile long timestampNs;
    private volatile EventType eventType;
    private volatile int instrumentId;
    private volatile Side side;
    private volatile long[] priceLevels;
    private volatile long[] sizeLevels;
    private volatile int levelsCount;
    private volatile double tradedPrice;
    private volatile long tradedSize;
    private volatile ByteBuffer orderBookDelta;
    private volatile long eventId;
    private volatile int sourcePartition;

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

    /**
     * Copia todos los campos de otro evento a este.
     * Utilizado al publicar eventos en el ring buffer.
     */
    public void copyFrom(MarketDataEvent other) {
        this.sequence = other.sequence;
        this.timestampNs = other.timestampNs;
        this.eventType = other.eventType;
        this.instrumentId = other.instrumentId;
        this.side = other.side;
        if (other.priceLevels != null) {
            this.priceLevels = Arrays.copyOf(other.priceLevels, other.priceLevels.length);
        }
        if (other.sizeLevels != null) {
            this.sizeLevels = Arrays.copyOf(other.sizeLevels, other.sizeLevels.length);
        }
        this.levelsCount = other.levelsCount;
        this.tradedPrice = other.tradedPrice;
        this.tradedSize = other.tradedSize;
        this.orderBookDelta = other.orderBookDelta != null ? other.orderBookDelta.duplicate() : null;
        this.eventId = other.eventId;
        this.sourcePartition = other.sourcePartition;
    }

    public void setOrderBookUpdate(int instrumentId, Side side, long[] prices, long[] sizes, int count) {
        this.instrumentId = instrumentId;
        this.side = side;
        this.priceLevels = prices;
        this.sizeLevels = sizes;
        this.levelsCount = count;
        this.eventType = EventType.ORDER_BOOK_UPDATE;
    }

    public void setTrade(int instrumentId, double price, long size) {
        this.instrumentId = instrumentId;
        this.tradedPrice = price;
        this.tradedSize = size;
        this.eventType = EventType.TRADE;
    }

    public void setOrderBookSnapshot(int instrumentId, long[] bids, long[] bidSizes,
                                       long[] asks, long[] askSizes, int count) {
        this.instrumentId = instrumentId;
        this.levelsCount = count;
        this.eventType = EventType.ORDER_BOOK_SNAPSHOT;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long seq) {
        this.sequence = seq;
    }

    public long getTimestampNs() {
        return timestampNs;
    }

    public void setTimestampNs(long ts) {
        this.timestampNs = ts;
    }

    public EventType getEventType() {
        return eventType;
    }

    public int getInstrumentId() {
        return instrumentId;
    }

    public Side getSide() {
        return side;
    }

    public long getPriceAtLevel(int level) {
        if (level >= 0 && level < priceLevels.length) {
            return priceLevels[level];
        }
        return 0;
    }

    public long getSizeAtLevel(int level) {
        if (level >= 0 && level < sizeLevels.length) {
            return sizeLevels[level];
        }
        return 0;
    }

    public int getLevelsCount() {
        return levelsCount;
    }

    public double getTradedPrice() {
        return tradedPrice;
    }

    public long getTradedSize() {
        return tradedSize;
    }

    public long getBidImbalance() {
        if (priceLevels == null || sizeLevels == null) {
            return 0;
        }
        long bidVolume = 0;
        long askVolume = 0;
        for (int i = 0; i < levelsCount && i < priceLevels.length; i++) {
            if (side == Side.BID) {
                bidVolume += sizeLevels[i];
            } else {
                askVolume += sizeLevels[i];
            }
        }
        return bidVolume - askVolume;
    }

    public Instant getTimestamp() {
        return Instant.ofEpochSecond(0, timestampNs);
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long id) {
        this.eventId = id;
    }

    public int getSourcePartition() {
        return sourcePartition;
    }

    public void setSourcePartition(int partition) {
        this.sourcePartition = partition;
    }

    public ByteBuffer getOrderBookDelta() {
        return orderBookDelta;
    }

    public void setOrderBookDelta(ByteBuffer delta) {
        this.orderBookDelta = delta;
    }

    public boolean isTrade() {
        return eventType == EventType.TRADE;
    }

    public boolean isOrderBook() {
        return eventType == EventType.ORDER_BOOK_UPDATE || eventType == EventType.ORDER_BOOK_SNAPSHOT;
    }
}

// === ARCHIVO: src/main/java/com/trading/riskengine/engine/RiskEngine.java ===
package com.trading.riskengine.engine;

import com.lmax.disruptor.Disruptor;
import com.lmax.disruptor.RingBuffer;
import com.trading.riskengine.feed.MarketDataEvent;
import com.trading.riskengine.model.VaRModel;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Motor principal que evalúa el riesgo de cada orden en menos de 500μs p99.
 * Usa el Disruptor para procesamiento reactivo con sharding por instrumento
 * para evitar contención entre threads.
 * 
 * Responsabilidades principales:
 * - Evaluación de órdenes contra límites de VaR
 * - Sharding de riesgo por instrumento
 * - Circuit breakers dinámicos basados en volatilidad
 * - Detección de anomalías y触发 de kill switch
 */
public class RiskEngine {

    private static final double DEFAULT_MAX_VAR = 100000.0;
    private static final int MAX_ORDERS_PER_INSTRUMENT = 100;
    private static final long CIRCUIT_BREAKER_RESET_TIMEOUT_MS = 5000;
    private static final double VOLATILITY_THRESHOLD_HIGH = 0.15;
    private static final double VOLATILITY_THRESHOLD_CRITICAL = 0.30;

    private final VaRModel varModel;
    private final int shardIndex;
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final CircuitBreaker[] circuitBreakersByInstrument;
    private final AtomicReference<OrderSubmissionResult> lastResult;
    private final AtomicLong ordersEvaluated;
    private final AtomicLong ordersRejected;
    private final AtomicLong circuitBreakerTriggers;
    private final AtomicBoolean isShutdown;
    private final RiskMetrics metrics;
    private final OrderValidator[] validators;

    /**
     * Constructor para sharding por instrumento.
     * @param varModel modelo de VaR compartido
     * @param shardIndex índice de este shard (0-63)
     */
    public RiskEngine(VaRModel varModel, int shardIndex) {
        this.varModel = varModel;
        this.shardIndex = shardIndex;
        this.ringBuffer = null;
        this.circuitBreakerRegistry = createCircuitBreakerRegistry();
        this.circuitBreakersByInstrument = new CircuitBreaker[1024]; // Max instrumentos por shard
        this.lastResult = new AtomicReference<>(OrderSubmissionResult.ACCEPTED);
        this.ordersEvaluated = new AtomicLong(0L);
        this.ordersRejected = new AtomicLong(0L);
        this.circuitBreakerTriggers = new AtomicLong(0L);
        this.isShutdown = new AtomicBoolean(false);
        this.metrics = new RiskMetrics();
        this.validators = new OrderValidator[1024];

        initializeCircuitBreakers(1024);
        initializeValidators(1024);
    }

    private RingBuffer<MarketDataEvent> ringBuffer;

    /**
     * Inicialización adicional que requiere el RingBuffer.
     * Llamado después de crear el motor.
     */
    public void initialize() {
        // Inicialización adicional si es necesaria
    }

    private void initializeCircuitBreakers(int numInstruments) {
        for (int i = 0; i < numInstruments; i++) {
            CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .slowCallRateThreshold(80)
                .slowCallDurationThreshold(Duration.ofMillis(100))
                .waitDurationInOpenState(Duration.ofMillis(CIRCUIT_BREAKER_RESET_TIMEOUT_MS))
                .permittedNumberOfCallsInHalfState(10)
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(100)
                .minimumNumberOfCalls(20)
                .build();
            
            circuitBreakersByInstrument[i] = circuitBreakerRegistry.circuitBreaker(
                "instrument-" + i, config);
        }
    }

    private void initializeValidators(int numInstruments) {
        for (int i = 0; i < numInstruments; i++) {
            validators[i] = new OrderValidator(
                DEFAULT_MAX_VAR,
                MAX_ORDERS_PER_INSTRUMENT,
                circuitBreakersByInstrument[i]
            );
        }
    }

    private CircuitBreakerRegistry createCircuitBreakerRegistry() {
        CircuitBreakerConfig defaultConfig = CircuitBreakerConfig.custom()
            .failureRateThreshold(50)
            .waitDurationInOpenState(Duration.ofSeconds(5))
            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED)
            .slidingWindowSize(60)
            .minimumNumberOfCalls(10)
            .build();
        
        return CircuitBreakerRegistry.of(defaultConfig);
    }

    /**
     * Evalúa una orden contra los límites de riesgo actuales.
     * Retorna en menos de 500μs p99.
     */
    public OrderSubmissionResult evaluateOrder(Order order) {
        if (isShutdown.get()) {
            return OrderSubmissionResult.REJECTED_SYSTEM_SHUTDOWN;
        }

        long startTime = System.nanoTime();
        int instrumentId = order.getInstrumentId();

        if (instrumentId < 0 || instrumentId >= circuitBreakersByInstrument.length) {
            ordersRejected.incrementAndGet();
            return OrderSubmissionResult.REJECTED_INVALID_INSTRUMENT;
        }

        CircuitBreaker cb = circuitBreakersByInstrument[instrumentId];
        if (cb.getState() == CircuitBreaker.State.OPEN) {
            circuitBreakerTriggers.incrementAndGet();
            ordersRejected.incrementAndGet();
            metrics.recordRejection(RejectionReason.CIRCUIT_BREAKER_OPEN);
            return OrderSubmissionResult.REJECTED_CIRCUIT_BREAKER;
        }

        OrderValidator validator = validators[instrumentId];
        double volatility = varModel.getInstrumentVolatility(instrumentId);
        
        if (volatility > VOLATILITY_THRESHOLD_CRITICAL) {
            circuitBreakerTriggers.incrementAndGet();
            ordersRejected.incrementAndGet();
            metrics.recordRejection(RejectionReason.CRITICAL_VOLATILITY);
            triggerKillSwitch(instrumentId, "CRITICAL_VOLATILITY", volatility);
            return OrderSubmissionResult.REJECTED_HIGH_RISK;
        }

        long currentExposure = varModel.getTraderExposure(order.getTraderId());
        double maxVar = calculateDynamicVaRLimit(volatility);
        
        if (varModel.exceedsVaR(currentExposure + order.getNotional(), instrumentId, maxVar)) {
            ordersRejected.incrementAndGet();
            metrics.recordRejection(RejectionReason.VAR_LIMIT_EXCEEDED);
            return OrderSubmissionResult.REJECTED_VAR_LIMIT;
        }

        if (!validator.validate(order, currentExposure, maxVar)) {
            ordersRejected.incrementAndGet();
            metrics.recordRejection(RejectionReason.VALIDATION_FAILED);
            return OrderSubmissionResult.REJECTED_VALIDATION;
        }

        long newExposure = varModel.updateTraderExposure(order.getTraderId(), order.getNotional());
        varModel.updateStrategyExposure(order.getStrategyId(), order.getNotional());

        ordersEvaluated.incrementAndGet();
        metrics.recordAcceptance(System.nanoTime() - startTime);
        lastResult.set(OrderSubmissionResult.ACCEPTED);
        
        return OrderSubmissionResult.ACCEPTED;
    }

    private double calculateDynamicVaRLimit(double volatility) {
        double dynamicLimit = DEFAULT_MAX_VAR;
        if (volatility > VOLATILITY_THRESHOLD_HIGH) {
            dynamicLimit = DEFAULT_MAX_VAR * (1.0 - (volatility - VOLATILITY_THRESHOLD_HIGH) * 3);
        }
        return Math.max(dynamicLimit, DEFAULT_MAX_VAR * 0.1);
    }

    public void onTradeExecuted(int instrumentId, double price, long size) {
        long notional = (long) (price * size);
        varModel.updateTraderExposure(ThreadLocalRandom.current().nextInt(10), -notional);
    }

    public void onMarketImbalance(int instrumentId, long imbalance) {
        if (Math.abs(imbalance) > 5000000) {
            metrics.recordMarketImbalance(instrumentId, imbalance);
        }
    }

    public void triggerCircuitBreaker(int instrumentId, String reason, double value) {
        if (instrumentId >= 0 && instrumentId < circuitBreakersByInstrument.length) {
            CircuitBreaker cb = circuitBreakersByInstrument[instrumentId];
            cb.transitionToOpenState();
            circuitBreakerTriggers.incrementAndGet();
            metrics.recordCircuitBreakerTrigger(instrumentId, reason, value);
        }
    }

    private void triggerKillSwitch(int instrumentId, String reason, double value) {
        System.err.println("KILL_SWITCH triggered for instrument " + instrumentId + 
            " reason=" + reason + " value=" + value);
    }

    public RiskMetrics getMetrics() {
        return metrics;
    }

    public long getOrdersEvaluated() {
        return ordersEvaluated.get();
    }

    public long getOrdersRejected() {
        return ordersRejected.get();
    }

    public long getCircuitBreakerTriggers() {
        return circuitBreakerTriggers.get();
    }

    public void reset() {
        ordersEvaluated.set(0L);
        ordersRejected.set(0L);
        circuitBreakerTriggers.set(0L);
        metrics.reset();
    }

    public void shutdown() {
        isShutdown.set(true);
    }

    public int getShardIndex() {
        return shardIndex;
    }

    public enum OrderSubmissionResult {
        ACCEPTED,
        REJECTED_VAR_LIMIT,
        REJECTED_CIRCUIT_BREAKER,
        REJECTED_HIGH_RISK,
        REJECTED_VALIDATION,
        REJECTED_INVALID_INSTRUMENT,
        REJECTED_SYSTEM_SHUTDOWN
    }

    public enum RejectionReason {
        VAR_LIMIT_EXCEEDED,
        CIRCUIT_BREAKER_OPEN,
        CRITICAL_VOLATILITY,
        VALIDATION_FAILED
    }

    public static class Order {
        private final int traderId;
        private final int strategyId;
        private final int instrumentId;
        private final long notional;
        private final double price;
        private final Side side;

        public Order(int traderId, int strategyId, int instrumentId, long notional, double price, Side side) {
            this.traderId = traderId;
            this.strategyId = strategyId;
            this.instrumentId = instrumentId;
            this.notional = notional;
            this.price = price;
            this.side = side;
        }

        public int getTraderId() { return traderId; }
        public int getStrategyId() { return strategyId; }
        public int getInstrumentId() { return instrumentId; }
        public long getNotional() { return notional; }
        public double getPrice() { return price; }
        public Side getSide() { return side; }

        public enum Side { BUY, SELL }
    }

    private static class OrderValidator {
        private final double maxVar;
        private final int maxOrders;
        private final CircuitBreaker circuitBreaker;
        private final AtomicLong orderCount;

        OrderValidator(double maxVar, int maxOrders, CircuitBreaker circuitBreaker) {
            this.maxVar = maxVar;
            this.maxOrders = maxOrders;
            this.circuitBreaker = circuitBreaker;
            this.orderCount = new AtomicLong(0);
        }

        boolean validate(Order order, long currentExposure, double dynamicMaxVar) {
            if (orderCount.get() >= maxOrders) {
                return false;
            }
            orderCount.incrementAndGet();
            return true;
        }
    }

    public static class RiskMetrics {
        private final AtomicLong totalLatencyNanos;
        private final AtomicLong evaluationCount;
        private final AtomicLong[] rejectionsByReason;
        private final AtomicLong marketImbalanceCount;

        RiskMetrics() {
            this.totalLatencyNanos = new AtomicLong(0);
            this.evaluationCount = new AtomicLong(0);
            this.rejectionsByReason = new AtomicLong[RejectionReason.values().length];
            this.marketImbalanceCount = new AtomicLong(0);
            
            for (int i = 0; i < rejectionsByReason.length; i++) {
                rejectionsByReason[i] = new AtomicLong(0);
            }
        }

        void recordAcceptance(long latencyNanos) {
            totalLatencyNanos.addAndGet(latencyNanos);
            evaluationCount.incrementAndGet();
        }

        void recordRejection(RejectionReason reason) {
            rejectionsByReason[reason.ordinal()].incrementAndGet();
        }

        void recordMarketImbalance(int instrumentId, long imbalance) {
            marketImbalanceCount.incrementAndGet();
        }

        void recordCircuitBreakerTrigger(int instrumentId, String reason, double value) {
            rejectionsByReason[RejectionReason.CIRCUIT_BREAKER_OPEN.ordinal()].incrementAndGet();
        }

        double getAverageLatencyMicros() {
            long count = evaluationCount.get();
            if (count == 0) return 0;
            return (totalLatencyNanos.get() / count) / 1000.0;
        }

        void reset() {
            totalLatencyNanos.set(0);
            evaluationCount.set(0);
            marketImbalanceCount.set(0);
            for (AtomicLong reason : rejectionsByReason) {
                reason.set(0);
            }
        }
    }
}


// === ARCHIVO: src/main/java/com/trading/riskengine/replay/AuditRecord.java ===
package com.trading.riskengine.replay;

import java.time.Instant;

public record AuditRecord(
    long sequence,
    Instant timestamp,
    String eventType,
    int instrumentId,
    int traderId,
    int strategyId,
    long orderValue,
    String decision,
    String reason
) {
    public String toMiFIDIIFormat() {
        return String.format("AUDIT|%d|%s|%s|%d|%d|%d|%d|%s|%s",
            sequence,
            timestamp.toString(),
            eventType,
            instrumentId,
            traderId,
            strategyId,
            orderValue,
            decision,
            reason);
    }
}

// === ARCHIVO: src/main/java/com/trading/riskengine/replay/EventSerializer.java ===
package com.trading.riskengine.replay;

import net.openhft.chronicle.wire.Wire;
import net.openhft.chronicle.wire.WireIn;
import java.time.Instant;

public class EventSerializer {
    
    public void writeEvent(Wire wire, RiskEvent event) {
        wire.write("seq").int64(event.getSequence());
        wire.write("ts").int64(event.getTimestamp().toEpochMilli());
        wire.write("type").text(event.getEventType());
        wire.write("inst").int32(event.getInstrumentId());
        wire.write("trader").int32(event.getTraderId());
        wire.write("strat").int32(event.getStrategyId());
        wire.write("val").int64(event.getOrderValue());
        wire.write("dec").text(event.getDecision());
        wire.write("rsn").text(event.getReason());
        wire.write("vol").float64(event.getVolatility());
    }
    
    public RiskEvent readEvent(Wire wire) {
        RiskEvent event = new RiskEvent();
        event.setSequence(wire.read("seq").int64());
        long epochMilli = wire.read("ts").int64();
        event.setTimestamp(Instant.ofEpochMilli(epochMilli));
        event.setEventType(wire.read("type").text());
        event.setInstrumentId(wire.read("inst").int32());
        event.setTraderId(wire.read("trader").int32());
        event.setStrategyId(wire.read("strat").int32());
        event.setOrderValue(wire.read("val").int64());
        event.setDecision(wire.read("dec").text());
        event.setReason(wire.read("rsn").text());
        event.setVolatility(wire.read("vol").float64());
        return event;
    }
    
    public void writeAuditRecord(Wire wire, AuditRecord record) {
        wire.write("seq").int64(record.sequence());
        wire.write("ts").int64(record.timestamp().toEpochMilli());
        wire.write("type").text(record.eventType());
        wire.write("inst").int32(record.instrumentId());
        wire.write("trader").int32(record.traderId());
        wire.write("strat").int32(record.strategyId());
        wire.write("val").int64(record.orderValue());
        wire.write("dec").text(record.decision());
        wire.write("rsn").text(record.reason());
    }
    
    public AuditRecord readAuditRecord(Wire wire) {
        long seq = wire.read("seq").int64();
        long epochMilli = wire.read("ts").int64();
        Instant ts = Instant.ofEpochMilli(epochMilli);
        String type = wire.read("type").text();
        int inst = wire.read("inst").int32();
        int trader = wire.read("trader").int32();
        int strat = wire.read("strat").int32();
        long val = wire.read("val").int64();
        String dec = wire.read("dec").text();
        String rsn = wire.read("rsn").text();
        return new AuditRecord(seq, ts, type, inst, trader, strat, val, dec, rsn);
    }
}

// === ARCHIVO: src/main/java/com/trading/riskengine/compliance/MiFIDIITracer.java ===
package com.trading.riskengine.compliance;

import com.trading.riskengine.replay.AuditRecord;
import net.openhft.chronicle.queue.ChronicleQueue;
import net.openhft.chronicle.queue.ExcerptAppender;
import net.openhft.chronicle.queue.ExcerptReader;
import net.openhft.chronicle.wire.DocumentContext;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MiFIDIITracer {
    private static final DateTimeFormatter TIMESTAMP_FORMAT = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("UTC"));
    
    private final ChronicleQueue auditQueue;
    private final ConcurrentHashMap<String, RiskDecision> activeDecisions;
    private final AtomicLong decisionCounter;
    private final String complianceEndpoint;
    private final boolean synchronousWrite;
    
    public MiFIDIITracer(String queuePath, boolean synchronousWrite) {
        this.auditQueue = ChronicleQueue.singleBuilder(queuePath)
            .blockSize(256 << 10)
            .writeBufferMode(ChronicleQueue.WriteBufferMode.asynchronous)
            .build();
        this.activeDecisions = new ConcurrentHashMap<>();
        this.decisionCounter = new AtomicLong(0);
        this.synchronousWrite = synchronousWrite;
        this.complianceEndpoint = "https://compliance.trading.internal/audit/v2";
    }
    
    public String recordRiskDecision(RiskDecision decision) {
        String decisionId = generateDecisionId(decision);
        decision.setDecisionId(decisionId);
        decision.setTimestamp(Instant.now().toString());
        decision.setComplianceRequirement("MiFIDII-2018-ART27");
        
        activeDecisions.put(decisionId, decision);
        
        try {
            appendToQueue(decision);
            if (synchronousWrite) {
                auditQueue.sync();
            }
        } catch (Exception e) {
            decision.setRecordingStatus(RecordingStatus.FAILED);
            decision.setFailureReason("Queue write error: " + e.getMessage());
        }
        
        return decisionId;
    }
    
    private void appendToQueue(RiskDecision decision) {
        ExcerptAppender appender = auditQueue.acquireAppender();
        try (DocumentContext ctx = appender.writingDocument()) {
            ctx.wire().write("decisionId").text(decision.getDecisionId());
            ctx.wire().write("timestamp").text(decision.getTimestamp());
            ctx.wire().write("traderId").int32(decision.getTraderId());
            ctx.wire().write("strategyId").int32(decision.getStrategyId());
            ctx.wire().write("instrumentId").text(decision.getInstrumentId());
            ctx.wire().write("orderId").text(decision.getOrderId());
            ctx.wire().write("decision").text(decision.getDecision().name());
            ctx.wire().write("riskScore").double64(decision.getRiskScore());
            ctx.wire().write("exposure").int64(decision.getExposure());
            ctx.wire().write("varLimit").int64(decision.getVarLimit());
            ctx.wire().write("volatility").double64(decision.getVolatility());
            ctx.wire().write("circuitBreakerState").text(decision.getCircuitBreakerState());
            ctx.wire().write("complianceRequirement").text(decision.getComplianceRequirement());
            ctx.wire().write("justification").text(decision.getJustification());
            ctx.wire().write("replayToken").text(decision.getReplayToken());
        }
        decision.setRecordingStatus(RecordingStatus.RECORDED);
    }
    
    private String generateDecisionId(RiskDecision decision) {
        long sequence = decisionCounter.incrementAndGet();
        String uniquePart = UUID.randomUUID().toString().substring(0, 8);
        return String.format("DEC-%d-%s-%d", 
            System.currentTimeMillis(), uniquePart, sequence);
    }
    
    public RiskDecision getDecision(String decisionId) {
        RiskDecision cached = activeDecisions.get(decisionId);
        if (cached != null) {
            return cached;
        }
        return searchHistorical(decisionId);
    }
    
    private RiskDecision searchHistorical(String decisionId) {
        ExcerptReader reader = auditQueue.createTailer();
        while (reader.readDocument() != null) {
            String id = reader.document().wire().read("decisionId").text();
            if (decisionId.equals(id)) {
                return mapToRiskDecision(reader.document().wire());
            }
        }
        return null;
    }
    
    private RiskDecision mapToRiskDecision(net.openhft.chronicle.wire.WireIn wire) {
        RiskDecision decision = new RiskDecision();
        decision.setDecisionId(wire.read("decisionId").text());
        decision.setTimestamp(wire.read("timestamp").text());
        decision.setTraderId(wire.read("traderId").int32());
        decision.setStrategyId(wire.read("strategyId").int32());
        decision.setInstrumentId(wire.read("instrumentId").text());
        decision.setOrderId(wire.read("orderId").text());
        decision.setDecision(RiskDecisionType.valueOf(wire.read("decision").text()));
        decision.setRiskScore(wire.read("riskScore").double64());
        decision.setExposure(wire.read("exposure").int64());
        decision.setVarLimit(wire.read("varLimit").int64());
        decision.setVolatility(wire.read("volatility").double64());
        decision.setCircuitBreakerState(wire.read("circuitBreakerState").text());
        decision.setComplianceRequirement(wire.read("complianceRequirement").text());
        decision.setJustification(wire.read("justification").text());
        decision.setReplayToken(wire.read("replayToken").text());
        decision.setRecordingStatus(RecordingStatus.RECORDED);
        return decision;
    }
    
    public void recordCircuitBreakerEvent(String instrumentId, String state, String reason) {
        RiskDecision event = new RiskDecision();
        event.setInstrumentId(instrumentId);
        event.setDecision(RiskDecisionType.CIRCUIT_BREAKER_TRIGGERED);
        event.setCircuitBreakerState(state);
        event.setJustification(reason);
        event.setComplianceRequirement("MiFIDII-2018-ART27-4");
        recordRiskDecision(event);
    }
    
    public void recordKillSwitchEvent(String strategyId, String trigger, String details) {
        RiskDecision event = new RiskDecision();
        event.setStrategyId(Integer.parseInt(strategyId.replaceAll("[^0-9]", "")));
        event.setDecision(RiskDecisionType.KILL_SWITCH_ACTIVATED);
        event.setJustification("Trigger: " + trigger + ". Details: " + details);
        event.setComplianceRequirement("MiFIDII-2018-ART27-5");
        recordRiskDecision(event);
    }
    
    public void recordVaRLimitBreach(int traderId, int strategyId, long exposure, long varLimit) {
        RiskDecision event = new RiskDecision();
        event.setTraderId(traderId);
        event.setStrategyId(strategyId);
        event.setExposure(exposure);
        event.setVarLimit(varLimit);
        event.setDecision(RiskDecisionType.VAR_LIMIT_EXCEEDED);
        event.setJustification(String.format("Exposure %d exceeds VaR limit %d (%.2f%%)", 
            exposure, varLimit, ((double)(exposure - varLimit) / varLimit) * 100));
        event.setComplianceRequirement("MiFIDII-2018-ART27-2");
        recordRiskDecision(event);
    }
    
    public String generateAuditReport(String startTime, String endTime) {
        StringBuilder report = new StringBuilder();
        report.append("MiFID II Audit Report\n");
        report.append("Period: ").append(startTime).append(" to ").append(endTime).append("\n");
        report.append("Generated: ").append(Instant.now()).append("\n");
        report.append("================================================================================\n");
        
        long totalDecisions = 0;
        long approvedOrders = 0;
        long rejectedOrders = 0;
        long circuitBreakerEvents = 0;
        long killSwitchEvents = 0;
        
        ExcerptReader reader = auditQueue.createTailer();
        while (reader.readDocument() != null) {
            String timestamp = reader.document().wire().read("timestamp").text();
            if (timestamp.compareTo(startTime) >= 0 && timestamp.compareTo(endTime) <= 0) {
                totalDecisions++;
                String decision = reader.document().wire().read("decision").text();
                switch (decision) {
                    case "APPROVED" -> approvedOrders++;
                    case "REJECTED" -> rejectedOrders++;
                    case "CIRCUIT_BREAKER_TRIGGERED" -> circuitBreakerEvents++;
                    case "KILL_SWITCH_ACTIVATED" -> killSwitchEvents++;
                }
            }
        }
        
        report.append(String.format("Total Risk Decisions: %d\n", totalDecisions));
        report.append(String.format("Approved Orders: %d\n", approvedOrders));
        report.append(String.format("Rejected Orders: %d\n", rejectedOrders));
        report.append(String.format("Circuit Breaker Events: %d\n", circuitBreakerEvents));
        report.append(String.format("Kill Switch Events: %d\n", killSwitchEvents));
        
        return report.toString();
    }
    
    public void close() {
        auditQueue.close();
    }
    
    public enum RiskDecisionType {
        APPROVED,
        REJECTED,
        VAR_LIMIT_EXCEEDED,
        CIRCUIT_BREAKER_TRIGGERED,
        KILL_SWITCH_ACTIVATED,
        VOLATILITY_THRESHOLD_BREACH,
        POSITION_LIMIT_EXCEEDED
    }
    
    public enum RecordingStatus {
        PENDING,
        RECORDED,
        FAILED,
        REPLAYED
    }
    
    public static class RiskDecision {
        private String decisionId;
        private String timestamp;
        private int traderId;
        private int strategyId;
        private String instrumentId;
        private String orderId;
        private RiskDecisionType decision;
        private double riskScore;
        private long exposure;
        private long varLimit;
        private double volatility;
        private String circuitBreakerState;
        private String complianceRequirement;
        private String justification;
        private String replayToken;
        private RecordingStatus recordingStatus;
        private String failureReason;
        
        public RiskDecision() {
            this.recordingStatus = RecordingStatus.PENDING;
        }
        
        public String getDecisionId() { return decisionId; }
        public void setDecisionId(String decisionId) { this.decisionId = decisionId; }
        public String getTimestamp() { return timestamp; }
        public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
        public int getTraderId() { return traderId; }
        public void setTraderId(int traderId) { this.traderId = traderId; }
        public int getStrategyId() { return strategyId; }
        public void setStrategyId(int strategyId) { this.strategyId = strategyId; }
        public String getInstrumentId() { return instrumentId; }
        public void setInstrumentId(String instrumentId) { this.instrumentId = instrumentId; }
        public String getOrderId() { return orderId; }
        public void setOrderId(String orderId) { this.orderId = orderId; }
        public RiskDecisionType getDecision() { return decision; }
        public void setDecision(RiskDecisionType decision) { this.decision = decision; }
        public double getRiskScore() { return riskScore; }
        public void setRiskScore(double riskScore) { this.riskScore = riskScore; }
        public long getExposure() { return exposure; }
        public void setExposure(long exposure) { this.exposure = exposure; }
        public long getVarLimit() { return varLimit; }
        public void setVarLimit(long varLimit) { this.varLimit = varLimit; }
        public double getVolatility() { return volatility; }
        public void setVolatility(double volatility) { this.volatility = volatility; }
        public String getCircuitBreakerState() { return circuitBreakerState; }
        public void setCircuitBreakerState(String circuitBreakerState) { this.circuitBreakerState = circuitBreakerState; }
        public String getComplianceRequirement() { return complianceRequirement; }
        public void setComplianceRequirement(String complianceRequirement) { this.complianceRequirement = complianceRequirement; }
        public String getJustification() { return justification; }
        public void setJustification(String justification) { this.justification = justification; }
        public String getReplayToken() { return replayToken; }
        public void setReplayToken(String replayToken) { this.replayToken = replayToken; }
        public RecordingStatus getRecordingStatus() { return recordingStatus; }
        public void setRecordingStatus(RecordingStatus recordingStatus) { this.recordingStatus = recordingStatus; }
        public String getFailureReason() { return failureReason; }
        public void setFailureReason(String failureReason) { this.failureReason = failureReason; }
    }
}

// === ARCHIVO: src/main/java/com/trading/riskengine/feed/MarketDataEvent.java ===
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

// === ARCHIVO: src/main/java/com/trading/riskengine/engine/RiskEngine.java ===
package com.trading.riskengine.engine;

import com.lmax.disruptor.RingBuffer;
import com.trading.riskengine.feed.MarketDataEvent;
import com.trading.riskengine.model.VaRModel;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class RiskEngine {
    private static final double DEFAULT_MAX_VAR = 1_000_000.0;
    private static final int MAX_ORDERS_PER_INSTRUMENT = 1000;
    private static final long CIRCUIT_BREAKER_RESET_TIMEOUT_MS = 30_000;
    private static final double VOLATILITY_THRESHOLD_HIGH = 0.25;
    private static final double VOLATILITY_THRESHOLD_CRITICAL = 0.40;
    
    private final VaRModel varModel;
    private final RingBuffer<MarketDataEvent> ringBuffer;
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final CircuitBreaker[] circuitBreakersByInstrument;
    private final AtomicReference<OrderSubmissionResult> lastResult;
    private final AtomicLong ordersEvaluated;
    private final AtomicLong ordersRejected;
    private final AtomicLong circuitBreakerTriggers;
    private final AtomicBoolean isShutdown;
    private final RiskMetrics metrics;
    private final OrderValidator[] validators;
    
    public RiskEngine(VaRModel varModel, RingBuffer<MarketDataEvent> ringBuffer, int numInstruments) {
        this.varModel = varModel;
        this.ringBuffer = ringBuffer;
        this.circuitBreakerRegistry = createCircuitBreakerRegistry();
        this.circuitBreakersByInstrument = new CircuitBreaker[numInstruments];
        this.lastResult = new AtomicReference<>(OrderSubmissionResult.APPROVED);
        this.ordersEvaluated = new AtomicLong(0);
        this.ordersRejected = new AtomicLong(0);
        this.circuitBreakerTriggers = new AtomicLong(0);
        this.isShutdown = new AtomicBoolean(false);
        this.metrics = new RiskMetrics();
        this.validators = new OrderValidator[0];
        initializeCircuitBreakers(numInstruments);
        initializeValidators(numInstruments);
    }
    
    private void initializeCircuitBreakers(int numInstruments) {
        for (int i = 0; i < numInstruments; i++) {
            circuitBreakersByInstrument[i] = circuitBreakerRegistry.circuitBreaker("instrument-" + i);
        }
    }
    
    private void initializeValidators(int numInstruments) {
    }
    
    private CircuitBreakerRegistry createCircuitBreakerRegistry() {
        return CircuitBreakerRegistry.ofDefaults();
    }
    
    public OrderSubmissionResult evaluateOrder(Order order) {
        if (isShutdown.get()) {
            return OrderSubmissionResult.REJECTED;
        }
        
        ordersEvaluated.incrementAndGet();
        
        int instrumentId = order.instrumentId;
        long orderValue = order.orderValue;
        
        double volatility = varModel.getInstrumentVolatility(instrumentId);
        double maxVaR = calculateDynamicVaRLimit(volatility);
        long varAmount = (long) varModel.calculateVaR(orderValue, instrumentId);
        
        if (varAmount > maxVaR) {
            ordersRejected.incrementAndGet();
            lastResult.set(OrderSubmissionResult.REJECTED);
            return OrderSubmissionResult.REJECTED;
        }
        
        long currentExposure = varModel.getTraderExposure(order.traderId);
        long newExposure = varModel.updateTraderExposure(order.traderId, orderValue);
        
        if (varModel.exceedsVaR(newExposure, instrumentId, maxVaR)) {
            varModel.updateTraderExposure(order.traderId, -orderValue);
            ordersRejected.incrementAndGet();
            lastResult.set(OrderSubmissionResult.REJECTED);
            return OrderSubmissionResult.REJECTED;
        }
        
        lastResult.set(OrderSubmissionResult.APPROVED);
        return OrderSubmissionResult.APPROVED;
    }
    
    // Method needed for RiskEngineLatencyTest
    public RiskResult evaluateRisk(MarketDataEvent event) {
        if (isShutdown.get()) {
            return new RiskResult(false, 0.0, 0, 0.0);
        }
        
        ordersEvaluated.incrementAndGet();
        
        int instrumentId = event.getInstrumentId();
        long orderValue = event.getTradedSize();
        
        double volatility = varModel.getInstrumentVolatility(instrumentId);
        double maxVaR = calculateDynamicVaRLimit(volatility);
        long varAmount = (long) varModel.calculateVaR(orderValue, instrumentId);
        
        boolean approved = varAmount <= maxVaR;
        
        if (!approved) {
            ordersRejected.incrementAndGet();
        }
        
        return new RiskResult(approved, varAmount / maxVaR, varAmount, volatility);
    }
    
    private double calculateDynamicVaRLimit(double volatility) {
        double baseVaR = DEFAULT_MAX_VAR;
        if (volatility > VOLATILITY_THRESHOLD_CRITICAL) {
            return baseVaR * 0.5;
        } else if (volatility > VOLATILITY_THRESHOLD_HIGH) {
            return baseVaR * 0.75;
        }
        return baseVaR;
    }
    
    public void onTradeExecuted(int instrumentId, double price, long size) {
        long notional = (long) (price * size);
        varModel.updateStrategyExposure(0, notional);
    }
    
    public void onMarketImbalance(int instrumentId, long imbalance) {
    }
    
    public void triggerCircuitBreaker(int instrumentId, String reason, double value) {
        circuitBreakerTriggers.incrementAndGet();
    }
    
    private void triggerKillSwitch(int instrumentId, String reason, double value) {
    }
    
    public RiskMetrics getMetrics() { return metrics; }
    public long getOrdersEvaluated() { return ordersEvaluated.get(); }
    public long getOrdersRejected() { return ordersRejected.get(); }
    public long getCircuitBreakerTriggers() { return circuitBreakerTriggers.get(); }
    
    public void reset() {
        ordersEvaluated.set(0);
        ordersRejected.set(0);
        circuitBreakerTriggers.set(0);
    }
    
    public void shutdown() {
        isShutdown.set(true);
    }
    
    public enum OrderSubmissionResult {
        APPROVED,
        REJECTED,
        CIRCUIT_BREAKER_OPEN,
        KILL_SWITCH_ACTIVE
    }
    
    public enum RejectionReason {
        VAR_LIMIT_EXCEEDED,
        POSITION_LIMIT_EXCEEDED,
        CIRCUIT_BREAKER_OPEN,
        KILL_SWITCH_ACTIVE,
        VOLATILITY_TOO_HIGH
    }
    
    public static class Order {
        public int traderId;
        public int strategyId;
        public int instrumentId;
        public long orderValue;
        public String orderType;
        
        public Order(int traderId, int strategyId, int instrumentId, long orderValue) {
            this.traderId = traderId;
            this.strategyId = strategyId;
            this.instrumentId = instrumentId;
            this.orderValue = orderValue;
        }
    }
    
    private static class OrderValidator {
    }
    
    public static class RiskMetrics {
        private volatile long totalEvaluations;
        private volatile long totalRejections;
        private volatile double averageRiskScore;
        
        public void recordEvaluation(boolean approved, double riskScore) {
            totalEvaluations++;
            if (!approved) totalRejections++;
            averageRiskScore = (averageRiskScore * (totalEvaluations - 1) + riskScore) / totalEvaluations;
        }
        
        public long getTotalEvaluations() { return totalEvaluations; }
        public long getTotalRejections() { return totalRejections; }
        public double getAverageRiskScore() { return averageRiskScore; }
    }
    
    // Inner class for evaluateRisk return type
    public static class RiskResult {
        private final boolean approved;
        private final double riskScore;
        private final long varAmount;
        private final double volatility;
        
        public RiskResult(boolean approved, double riskScore, long varAmount, double volatility) {
            this.approved = approved;
            this.riskScore = riskScore;
            this.varAmount = varAmount;
            this.volatility = volatility;
        }
        
        public boolean isApproved() { return approved; }
        public double getRiskScore() { return riskScore; }
        public long getVarAmount() { return varAmount; }
        public double getVolatility() { return volatility; }
    }
}

// === ARCHIVO: src/test/java/com/trading/riskengine/engine/RiskEngineLatencyTest.java ===
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


// === ARCHIVO: src/main/java/com/trading/riskengine/circuitbreaker/DynamicCircuitBreaker.java ===
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

// === ARCHIVO: src/main/java/com/trading/riskengine/killswitch/KillSwitchPolicy.java ===
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
```
