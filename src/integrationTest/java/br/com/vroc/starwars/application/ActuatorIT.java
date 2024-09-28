package br.com.vroc.starwars.application;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.springframework.http.HttpStatus.CREATED;

import br.com.vroc.starwars.it.BaseIntegrationTest;
import br.com.vroc.starwars.stub.StarWarsApiStub;
import br.com.vroc.starwars.stub.StarWarsStubFactory;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ActuatorIT extends BaseIntegrationTest {

    private final StarWarsApiStub stub = StarWarsStubFactory.createCharacterStub();

    @BeforeEach
    void startMockServer() {
        RestAssured.basePath = "/api/v1/starwars";
    }

    @Test
    void shouldReturn200WhenCallHealthCheckEndpoint() {
        RestAssured.basePath = "";

        when().
            get("/actuator/health").
            then().
            statusCode(200).
            body("status", equalTo("UP"));
    }

    @Test
    void shouldCreateCharacterAndReturn201() {
        stub.stubGetCharacter();

        String request = """
                {
                    "name": "Luke Skywalker",
                    "affiliation": "Jedi"
                }
            """;

        given()
            .contentType("application/json")
            .body(request)
            .when()
            .post("/characters")
            .then()
            .statusCode(CREATED.value())
            //valida schema
            .body(matchesJsonSchemaInClasspath("response/get-character-response.json"))
            //valida conteúdo
            .body("$", hasKey("id"))
            .body("affiliation", equalTo("Jedi"))
            .body("lightsaberColor", equalTo("Green"));
    }
}
