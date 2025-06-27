# Proyecto de Automatización de Pruebas - API FakeStore

Este repositorio contiene el desarrollo de tres ejercicios (Katas) enfocados en automatización de pruebas sobre la API pública de FakeStore:  
https://fakestoreapi.com/docs

Cada ejercicio implementa buenas prácticas de automatización, validación de respuestas HTTP, lectura de cuerpos desde archivos JSON, ejecución en paralelo y generación de reportes visuales.

---

## Kata 1: Automatización con Java, JUnit y Reporte Swing

**Objetivo:**  
Simular distintos tipos de consumo a una API REST (GET, POST, PUT, DELETE), incluyendo casos exitosos y fallidos. Mostrar los resultados en una tabla visual con reporte.

**Funcionalidades implementadas:**

- Consumo del API con estado exitoso (GET, POST)
- Consumo del API con estado fallido (GET con ID inexistente)
- Lectura del body desde un archivo JSON (`producto.json`)
- Ejecución de 3 servicios en paralelo
- Validación de códigos de respuesta HTTP
- Impresión y visualización de respuestas en una tabla Swing
- Reporte generado con interfaz gráfica al finalizar

**Ejecución del ejercicio:**

1. Abrir el proyecto en Visual Studio Code.
2. Verificar que esté instalado el Extension Pack for Java.
3. Ejecutar la clase `FakeStoreKataTest.java`, específicamente el método `ejecutarEnParaleloYReporte()`.
4. Al finalizar, se mostrará una ventana emergente con la tabla resumen.

**Importante:** No ejecutar con `mvn test`, ya que el proceso termina antes de mostrar la interfaz Swing.

---

## Kata 2: Automatización con Gatling

**Objetivo:**  
Realizar pruebas de rendimiento sobre la API usando Gatling, simulando tráfico de usuarios con modelos abiertos y cerrados.

**Funcionalidades implementadas:**

- 3 peticiones en modelo abierto (GET, POST, PUT)
- 2 peticiones en modelo cerrado (GET con ID, DELETE)
- Lectura de body desde archivo JSON
- Validación de estado HTTP
- Reporte HTML automático generado por Gatling

**Estructura del proyecto:**

gatling-maven-plugin-demo-java-main/
├── src/
│ └── test/
│ ├── java/
│ │ └── simulations/
│ │ └── FakeStoreSimulation.java
│ └── resources/
│ └── data/
│ └── producto.json


**Ejecución:**

```bash
mvn gatling:test

target/gatling/fakestoresimulation-<timestamp>/index.html

Kata 3: Pruebas con JMeter (Carga, Estrés y Concurrencia)
Objetivo:
Diseñar escenarios de pruebas de performance usando Apache JMeter sobre la API de FakeStore.

Escenarios implementados:

Prueba de carga (Load Testing)

Prueba de estrés (Stress Testing)

Prueba de concurrencia (Concurrency Testing)

Cada escenario incluye:

Peticiones GET, POST, PUT, DELETE

Validaciones de código de estado 200 (Assertions)

Visualización con Graph Results, Summary Report y View Results Tree

Ejecución del plan JMeter:

Abrir apache-jmeter-5.6.3/bin/jmeter.bat.

Cargar el archivo FakeStoreTestPlan.jmx.

Ejecutar el test con el botón verde.

Observar resultados en los listeners configurados.

Exportar la tabla si se requiere, desde Summary Report o similares.

