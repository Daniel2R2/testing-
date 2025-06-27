package simulations;

import io.gatling.javaapi.core.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class FakeStoreApiSimulation extends Simulation {

    // URL base de la API
    private static final String BASE_URL = "https://fakestoreapi.com";
    // Rutas de los archivos JSON para los cuerpos de POST y PUT
    private static final String CREATE_BODY = "bodies/createProduct.json";
    private static final String UPDATE_BODY = "bodies/updateProduct.json";

    // Escenario para modelo abierto (nombre único y buena práctica)
    ScenarioBuilder apiScenarioAbierto = scenario("kata evaluación - modelo abierto")
        .exec(
            http("GET Products")
                .get("/products")
                .check(status().is(200))
        )
        .pause(1)
        .exec(
            http("POST Product")
                .post("/products")
                .body(ElFileBody("bodies/createProduct.json")).asJson()
                .check(status().in(200, 201))
        )
        .pause(1)
        .exec(
            http("PUT Product")
                .put("/products/1")
                .body(ElFileBody("bodies/updateProduct.json")).asJson()
                .check(status().is(200))
        )
        .pause(1)
        .exec(
            http("DELETE Product")
                .delete("/products/1")
                .check(status().is(200))
        );

    // Escenario para modelo cerrado (nombre único y buena práctica)
    ScenarioBuilder apiScenarioCerrado = scenario("kata evaluación - modelo cerrado")
        .exec(
            http("GET Products")
                .get("/products")
                .check(status().is(200))
        )
        .pause(1)
        .exec(
            http("POST Product")
                .post("/products")
                .body(ElFileBody("bodies/createProduct.json")).asJson()
                .check(status().in(200, 201))
        )
        .pause(1)
        .exec(
            http("PUT Product")
                .put("/products/1")
                .body(ElFileBody("bodies/updateProduct.json")).asJson()
                .check(status().is(200))
        )
        .pause(1)
        .exec(
            http("DELETE Product")
                .delete("/products/1")
                .check(status().is(200))
        );

    {
        setUp(
            // Modelo abierto: 3 tipos de inyección de usuarios (sin nothingFor)
            apiScenarioAbierto.injectOpen(
                atOnceUsers(1),
                rampUsers(1).during(5),
                constantUsersPerSec(1).during(5)
            ),
            // Modelo cerrado: 2 tipos de usuarios concurrentes
            apiScenarioCerrado.injectClosed(
                constantConcurrentUsers(1).during(5),
                rampConcurrentUsers(1).to(2).during(5)
            )
        ).protocols(http.baseUrl("https://fakestoreapi.com"));
    }
}


