package br.com.vroc.starwars.domain.business;

import br.com.vroc.starwars.domain.client.StarWarsApiClient;
import br.com.vroc.starwars.domain.entity.Character;
import br.com.vroc.starwars.domain.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StarWarsCharacterBusiness {

    private final CharacterRepository starWarsRepository;
    private final StarWarsApiClient starWarsApiClient;

    public Character createCharacter(Character request) {
        var apiCharacter = starWarsApiClient.getCharacterDetails(request.name());

        var newCharacter = request.withSpecies(apiCharacter.species())
            .withPlanet(apiCharacter.planet())
            .withLightsaberColor(apiCharacter.lightsaberColor());

        return starWarsRepository.save(newCharacter);
    }

}
