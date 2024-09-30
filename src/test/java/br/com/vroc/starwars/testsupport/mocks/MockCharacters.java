package br.com.vroc.starwars.testsupport.mocks;

import br.com.vroc.starwars.application.controller.request.CharacterRequest;
import br.com.vroc.starwars.application.controller.response.CharacterResponse;
import br.com.vroc.starwars.domain.entity.Character;
import java.util.Optional;
import java.util.UUID;

public class MockCharacters {

    public static CharacterRequest request() {
        return new CharacterRequest("Luke", "Rebel Alliance");
    }

    public static Character fromRequest(CharacterRequest request) {
        return Character.builder()
            .name(request.name())
            .affiliation(request.affiliation())
            .build();
    }

    public static Character createDefault() {
        return createDefaultWithoutId().withId(UUID.randomUUID());
    }

    public static Character createDefaultWithoutId() {
        return Character.builder()
            .name("Luke")
            .affiliation("Rebel Alliance")
            .lightsaberColor("Green")
            .planet("Tatooine")
            .species("Human")
            .build();
    }

    public static CharacterResponse response(Character character) {
        return CharacterResponse.builder()
            .id(Optional.ofNullable(character.id()).orElse(UUID.randomUUID()))
            .name(Optional.ofNullable(character.name()).orElse("Luke"))
            .affiliation(Optional.ofNullable(character.affiliation()).orElse("Rebel Alliance"))
            .lightsaberColor(Optional.ofNullable(character.lightsaberColor()).orElse("Green"))
            .planet(Optional.ofNullable(character.planet()).orElse("Tatooine"))
            .species(Optional.ofNullable(character.species()).orElse("Human"))
            .build();
    }

}
