# Implementación de un motor de risk scoring en tiempo real para trading algorítmico con circuit breakers dinámicos

Diseña y construye un motor de risk scoring que evalúa el riesgo de cada orden antes de enviarla al exchange, en menos de 500 microsegundos p99. El sistema consume un feed de market data (nivel 2 orderbook + trades), mantiene un modelo de VaR intraday, aplica límites por trader/estrategia/instrumento en tiempo real, y dispara circuit breakers dinámicos que bloquean nuevas órdenes cuando la exposición supera thresholds calibrados por volatilidad. Deberás justificar el uso de estructuras lock-free vs mutex, la elección entre C++ vs Rust vs Java LMAX Disruptor, cómo garantizas la consistencia entre múltiples risk engines corriendo en paralelo (consensus vs sharding por instrument), la política de kill switch cuando detecta un algoritmo que se comporta de forma anómala, y la estrategia de replay determinístico para post-mortem de incidents. Incluye compliance con regulaciones MiFID II para trazabilidad de decisiones de riesgo.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | TEST-CT |
| **Nivel** | master-l2 |
| **Tipo** | mixed |
| **Tiempo estimado** | 2 semanas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Exploración y modelado del sistema

**Objetivo:** Comprender y modelar las necesidades del sistema de risk scoring en términos de dominio y requisitos funcionales.

**Tiempo estimado:** 3 días

**Instrucciones:**

- Identifica y documenta los actores y componentes clave del sistema (feed de market data, risk engine, circuit breakers, kill switch, etc.).
- Establece los umbrales y restricciones del dominio (500 microsegundos p99, thresholds de volatilidad, compliance con MiFID II).
- Modela el flujo de datos y las interacciones entre componentes.

**Entregable:** Diagrama de relaciones y registro de decisiones inicial que incluye contexto, fuerzas, opciones con pros/contras, y decisiones tomadas.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la latencia y throughput requeridos para el p99.
- Evalúa los trade-offs entre diferentes tecnologías y estructuras de datos.

</details>

### Fase 2: Implementación del motor de risk scoring

**Objetivo:** Implementar el motor de risk scoring que evalúa el riesgo de cada orden en tiempo real.

**Tiempo estimado:** 5 días

**Instrucciones:**

- Desarrolla el componente que consume el feed de market data y aplica el modelo de VaR intraday.
- Implementa los límites por trader/estrategia/instrumento y los circuit breakers dinámicos.
- Asegura la consistencia entre múltiples risk engines corriendo en paralelo.

**Entregable:** Motor de risk scoring funcional que cumple con los requisitos de latencia y throughput, y que aplica los límites y circuit breakers definidos.

<details>
<summary>Pistas de conocimiento</summary>

- Considera el uso de estructuras lock-free vs mutex para garantizar la consistencia.
- Evalúa la elección entre C++ vs Rust vs Java LMAX Disruptor.

</details>

### Fase 3: Política de kill switch y replay determinístico

**Objetivo:** Implementar la política de kill switch y la estrategia de replay determinístico para post-mortem de incidents.

**Tiempo estimado:** 4 días

**Instrucciones:**

- Desarrolla la política de kill switch que bloquea nuevas órdenes cuando se detecta un comportamiento anómalo.
- Implementa la estrategia de replay determinístico para analizar incidents y garantizar la trazabilidad de decisiones de riesgo conforme a MiFID II.

**Entregable:** Política de kill switch funcional y estrategia de replay determinístico que permite el análisis de incidents y garantiza la trazabilidad de decisiones de riesgo.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo detectar comportamientos anómalos y cuándo activar el kill switch.
- Evalúa diferentes estrategias para lograr un replay determinístico.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es un motor de risk scoring y cuáles son sus componentes principales?
- **paraQueSirve**: ¿Para qué sirve un motor de risk scoring en el contexto del trading algorítmico?
- **comoSeUsa**: ¿Cómo se usa un motor de risk scoring para evaluar el riesgo de cada orden en tiempo real?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar un motor de risk scoring y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica la implementación de un motor de risk scoring, como la elección de tecnología y estructuras de datos?

## Criterios de Evaluacion

- Comprensión y modelado del sistema de risk scoring.
- Implementación del motor de risk scoring con los requisitos de latencia y throughput.
- Garantía de consistencia entre múltiples risk engines.
- Implementación de la política de kill switch y la estrategia de replay determinístico.
- Compliance con regulaciones MiFID II para trazabilidad de decisiones de riesgo.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
el comando de build o arranque canonico del stack elegido
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
