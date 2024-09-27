package br.com.vroc.starwars.stub;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import org.mockserver.client.MockServerClient;

public class StarWarsApiStub {

    private final MockServerClient mockServerClient;

    public StarWarsApiStub(MockServerClient mockServerClient) {
        this.mockServerClient = mockServerClient;
    }

    public void stubGetCharacter() {
        mockServerClient.when(
                request()
                    .withMethod("GET")
                    .withPath("/starwars/character")
            )
            .respond(
                response()
                    .withStatusCode(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody(
                        "{ \"name\": \"Luke Skywalker\", \"species\": \"Human\", \"planet\": \"Tatooine\", \"affiliation\": \"Rebel Alliance\", \"lightsaberColor\": \"Green\" }"
                    )
            );
    }

}