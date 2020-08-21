package eu.davidemartorana.cloud.gcp.quarkus.poc;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ControllerAPITest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello World!"));

        given()
                .when()
                    .param("name", "Mickey")
                    .get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello World Mickey!"));

        given()
                .when()
                .param("name", "Mickey")
                .param("lastName", "Mouse")
                .get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello World Mickey Mouse!"));
    }

}
