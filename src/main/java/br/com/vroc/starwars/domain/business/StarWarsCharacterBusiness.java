package br.com.vroc.starwars.domain.business;

import br.com.vroc.starwars.domain.client.StarWarsApiClient;
import br.com.vroc.starwars.domain.entity.Character;
import br.com.vroc.starwars.domain.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StarWarsCharacterBusiness {

    private final CharacterRepository repository;
    private final StarWarsApiClient apiClient;

    public Character createCharacter(Character character) {
        var apiCharacter = apiClient.getCharacterDetails(character.name());

        var newCharacter = character.toBuilder()
            .species(apiCharacter.species())
            .planet(apiCharacter.planet())
            .lightsaberColor(apiCharacter.lightsaberColor())
            .build();

        return repository.save(newCharacter);
    }

}
