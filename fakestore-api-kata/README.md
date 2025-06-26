# Kata Automatización API - FakeStore

Este proyecto automatiza pruebas sobre la API pública de FakeStore usando Java, Maven y JUnit 5.  
Incluye ejemplos de consumo GET, POST, manejo de errores, concurrencia y reporte de resultados.

## Estructura del proyecto

- `pom.xml`: Configuración de dependencias y plugins de Maven.
- `src/main/resources/data/producto.json`: Archivo de datos para pruebas POST.
- `src/test/java/com/daniel/tests/FakeStoreKataTest.java`: Todas las pruebas automatizadas.
- `target/surefire-reports/`: Reportes generados automáticamente por Maven.

## ¿Cómo funciona cada parte?

- **Pruebas GET y POST**: Validan que la API responde correctamente tanto en casos exitosos como de error.
- **Lectura de JSON externo**: Permite separar los datos de prueba del código.
- **Ejecución en paralelo**: Simula carga concurrente sobre la API.
- **Reportes automáticos**: Los resultados de las pruebas quedan en la carpeta `target/surefire-reports/`.

## Ejecución

```bash
mvn clean test
```

¡Listo para usar en entrevistas técnicas o como base para proyectos de automatización de APIs!