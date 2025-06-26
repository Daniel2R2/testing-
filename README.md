# Pruebas Automatizadas con API de FakeStore

Este repositorio contiene dos ejercicios (Katas) enfocados en pruebas automatizadas sobre la API pública de [FakeStore](https://fakestoreapi.com/docs), utilizando Java + JUnit para el primer punto y Gatling para pruebas de carga en el segundo punto.

## Kata 1: Automatización con JUnit y Reporte Swing

### Requisitos implementados:

- Consumo del API con estado *exitoso (GET, POST)*
- Consumo del API con estado *fallido (GET a ID inexistente)*
- El **body se lee desde un archivo JSON** (`src/main/resources/data/producto.json`)
- Se **genera un reporte gráfico en Swing** con tabla detallada
- Se ejecutan **3 servicios en paralelo** (`GET` con IDs distintos)
- Se **valida el código de respuesta HTTP** de cada API
- Se imprimen y muestran las **respuestas de cada API** de forma ordenada

### Instrucciones para ejecutar:

1. Abre el proyecto en Visual Studio Code.
2. Asegúrate de tener el pack de Java instalado (`Extension Pack for Java`).
3. Ve a la clase `FakeStoreKataTest.java`.
4. Haz clic derecho sobre el método `ejecutarEnParaleloYReporte()` o usa el explorador de pruebas (Testing Explorer) para ejecutarlo.
5. Al finalizar, aparecerá una **ventana emergente** con el reporte en tabla.

No usar `mvn test` ya que cierra el proceso Swing y no muestra la ventana.


## Kata 2: Automatización con Gatling

### Requisitos implementados:

- Envío de **3 peticiones en modelo abierto** (GET, POST, PUT)
- Envío de **2 peticiones en modelo cerrado** (GET por ID, DELETE)
- El **body se lee desde un archivo JSON**
- Se **genera reporte Gatling** automático al ejecutar el test

## Estructura del proyecto:

gatling-maven-plugin-demo-java-main/
├── src/
│   └── test/
│       ├── java/
│       │   └── simulations/
│       │       └── FakeStoreSimulation.java
│       └── resources/
│           └── data/
│               └── producto.json

### Para ejecutar Gatling:

 bash
mvn gatling:test

Luego abre el reporte HTML generado:

target/gatling/fakestoresimulation-TIMESTAMP/index.html

