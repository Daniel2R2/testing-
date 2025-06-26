package example;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.nio.file.Files;
import java.nio.file.Paths;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class MySimulation extends Simulation {

    // Cargar el JSON como String
    String bodyString = "";

    {
        try {
            bodyString = new String(Files.readAllBytes(Paths.get("src/test/resources/data/producto.json")));
        } catch (Exception e) {
            System.out.println("Error leyendo JSON: " + e.getMessage());
        }
    }

    // Configurar HTTP
    HttpProtocolBuilder httpProtocol = http
        .baseUrl("https://fakestoreapi.com")
        .acceptHeader("application/json");

    // Escenario modelo abierto (open model)
    ScenarioBuilder openScenario = scenario("Open Model Requests")
        .exec(http("GET Productos").get("/products"))
        .exec(http("POST Producto").post("/products").body(StringBody(bodyString)).asJson())
        .exec(http("PUT Producto").put("/products/1").body(StringBody(bodyString)).asJson());

    // Escenario modelo cerrado (closed model)
    ScenarioBuilder closedScenario = scenario("Closed Model Requests")
        .exec(http("GET Producto por ID").get("/products/1"))
        .exec(http("DELETE Producto").delete("/products/1"));

    {
        setUp(
            openScenario.injectOpen(
                atOnceUsers(3) // modelo abierto: 3 de una vez
            ),
            closedScenario.injectClosed(
                constantConcurrentUsers(2).during(10) // modelo cerrado: 2 usuarios constantes durante 10s
            )
        ).protocols(httpProtocol);
    }
}
