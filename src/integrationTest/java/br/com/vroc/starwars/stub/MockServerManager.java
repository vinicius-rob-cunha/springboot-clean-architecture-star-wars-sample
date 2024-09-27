package br.com.vroc.starwars.stub;

import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;

public class MockServerManager {

    private static ClientAndServer mockServer;

    public static void start() {
        mockServer = ClientAndServer.startClientAndServer(0);
    }

    public static void stop() {
        mockServer.stop();
    }

    public static void clearStubs() {
        mockServer.clear(HttpRequest.request());
    }

    public static String getBaseUrl() {
        return "http://localhost:" + mockServer.getLocalPort();
    }

    public static StarWarsApiStub createCharacterStub() {
        return new StarWarsApiStub(mockServer);
    }

}
