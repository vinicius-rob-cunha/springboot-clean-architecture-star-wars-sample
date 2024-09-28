package br.com.vroc.starwars.stub;

import br.com.vroc.starwars.it.MockServerManager;

public class StarWarsStubFactory {

    public static StarWarsApiStub createCharacterStub() {
        return new StarWarsApiStub(MockServerManager.getMockServerClient());
    }

}
