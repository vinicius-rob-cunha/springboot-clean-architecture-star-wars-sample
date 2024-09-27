package br.com.vroc.starwars.domain.client;

import br.com.vroc.starwars.domain.entity.Character;

public interface StarWarsApiClient {

    Character getCharacterDetails(String name);

}
