package example;

import io.gatling.javaapi.core.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class BasicSimulation extends Simulation {

    // Escenario con nombre personalizado para mostrar "kata evaluación" en el menú
    ScenarioBuilder kataScenario = scenario("kata evaluación")
        .exec(
            http("GET Products")
                .get("https://fakestoreapi.com/products")
                .check(status().is(200))
        )
        .pause(1)
        .exec(
            http("POST Product")
                .post("https://fakestoreapi.com/products")
                .body(StringBody("{\"title\":\"Producto de prueba POST\",\"price\":19.99,\"description\":\"Este es un producto creado por Gatling\",\"image\":\"https://i.pravatar.cc\",\"category\":\"electronics\"}")).asJson()
                .check(status().in(200, 201))
        )
        .pause(1)
        .exec(
            http("PUT Product")
                .put("https://fakestoreapi.com/products/1")
                .body(StringBody("{\"title\":\"Producto actualizado PUT\",\"price\":29.99,\"description\":\"Este es un producto actualizado por Gatling\",\"image\":\"https://i.pravatar.cc\",\"category\":\"electronics\"}")).asJson()
                .check(status().is(200))
        )
        .pause(1)
        .exec(
            http("DELETE Product")
                .delete("https://fakestoreapi.com/products/1")
                .check(status().is(200))
        );

    {
        setUp(
            kataScenario.injectOpen(
                atOnceUsers(1),
                rampUsers(1).during(5),
                constantUsersPerSec(1).during(5)
            ),
            kataScenario.injectClosed(
                constantConcurrentUsers(1).during(5),
                rampConcurrentUsers(1).to(2).during(5)
            )
        );
    }
}
