package br.com.vroc.starwars.testsupport.stub;

import br.com.vroc.starwars.testsupport.MockServerManager;

public class Stubs {

    public static StarWarsApiStub characterStub() {
        return new StarWarsApiStub(MockServerManager.getMockServerClient());
    }

}
