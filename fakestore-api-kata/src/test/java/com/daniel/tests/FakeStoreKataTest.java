package com.daniel.tests;

// Importamos Unirest para hacer peticiones HTTP de forma sencilla
import kong.unirest.*;
// Importamos JUnit 5 para estructurar y ejecutar las pruebas
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Ordena los tests según el número de @Order
@TestMethodOrder(OrderAnnotation.class)
public class FakeStoreKataTest {

    // URL base de la API FakeStore, aquí apuntan todas las peticiones
    private static final String BASE_URL = "https://fakestoreapi.com/products";
    // Lista para almacenar los resultados de cada prueba y generar el reporte al final
    private static final List<String[]> reporteResultados = new ArrayList<>();

    // Método utilitario para leer el contenido de un archivo JSON externo
    // Así separamos los datos de prueba del código y facilitamos cambios futuros
    private String getRequestBody(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    // Test 1: Consulta exitosa de un producto existente (GET)
    @Test
    @Order(1)
    void getProducto_Exito() {
        HttpResponse<String> response = Unirest.get(BASE_URL + "/1").asString();
        System.out.println("GET Exitoso: " + response.getBody());
        reporteResultados.add(new String[]{"1", "GET", String.valueOf(response.getStatus()),
                response.getStatus() == 200 ? "Éxito" : "Fallo"});
        Assertions.assertEquals(200, response.getStatus());
    }

    // Test 2: Consulta de un producto que NO existe (GET)
    @Test
    @Order(2)
    void getProducto_NoExito() {
        HttpResponse<String> response = Unirest.get(BASE_URL + "/9999").asString();
        System.out.println("GET Fallido: " + response.getBody());
        reporteResultados.add(new String[]{"9999", "GET", String.valueOf(response.getStatus()),
                response.getStatus() == 404 ? "Error esperado" : "Inesperado"});
        Assertions.assertEquals(404, response.getStatus());
    }

    // Test 3: Crear un producto usando el body desde un archivo JSON externo (POST)
    @Test
    @Order(3)
    void postProducto() throws IOException {
        String json = getRequestBody("src/main/resources/data/producto.json");
        HttpResponse<String> response = Unirest.post(BASE_URL)
                .header("Content-Type", "application/json")
                .body(json)
                .asString();
        System.out.println("POST Respuesta: " + response.getBody());
        reporteResultados.add(new String[]{"-", "POST", String.valueOf(response.getStatus()),
                response.getStatus() == 200 ? "Éxito" : "Fallo"});
        Assertions.assertEquals(200, response.getStatus());
    }

    // Test 4: Actualiza un producto usando el body desde producto_update.json (PUT)
    @Test
    @Order(4)
    void putProducto() throws IOException {
        String json = getRequestBody("src/main/resources/data/producto_update.json");
        HttpResponse<String> response = Unirest.put(BASE_URL + "/1")
                .header("Content-Type", "application/json")
                .body(json)
                .asString();
        System.out.println("PUT Respuesta: " + response.getBody());
        reporteResultados.add(new String[]{
            "1", "PUT", String.valueOf(response.getStatus()),
            response.getStatus() == 200 ? "Éxito" : "Fallo"
        });
        Assertions.assertEquals(200, response.getStatus());
    }

    // Test 5: Elimina un producto por ID (DELETE)
    @Test
    @Order(5)
    void deleteProducto() {
        HttpResponse<String> response = Unirest.delete(BASE_URL + "/1").asString();
        System.out.println("DELETE Respuesta: " + response.getBody());
        reporteResultados.add(new String[]{
            "1", "DELETE", String.valueOf(response.getStatus()),
            response.getStatus() == 200 ? "Éxito" : "Fallo"
        });
        Assertions.assertEquals(200, response.getStatus());
    }

    // Test 6: Ejecutar 3 consultas GET en paralelo y mostrar reporte en tabla Swing
    @Test
    @Order(6)
    void ejecutarEnParaleloYReporte() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<?>> futures = new ArrayList<>();
        for (int id = 1; id <= 3; id++) {
            final int finalId = id;
            futures.add(executor.submit(() -> {
                HttpResponse<String> response = Unirest.get(BASE_URL + "/" + finalId).asString();
                System.out.println("Paralelo ID " + finalId + ": " + response.getBody());
                reporteResultados.add(new String[]{
                        String.valueOf(finalId),
                        "GET",
                        String.valueOf(response.getStatus()),
                        response.getStatus() == 200 ? "Éxito" : "Fallo"
                });
                Assertions.assertEquals(200, response.getStatus());
            }));
        }
        for (Future<?> future : futures) future.get();
        executor.shutdown();

        // Mostrar el reporte en una ventana Swing
        SwingUtilities.invokeLater(() -> new com.daniel.utils.ReporteUI(reporteResultados));
        // Pausa para que la ventana no se cierre inmediatamente
        try {
            System.out.println("Presiona ENTER en la consola para cerrar la ventana del reporte...");
            System.in.read();
        } catch (Exception ignored) {}
    }
}