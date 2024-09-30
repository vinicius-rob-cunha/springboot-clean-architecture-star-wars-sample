package br.com.vroc.starwars.business;

import static br.com.vroc.starwars.testsupport.mocks.MockCharacters.createDefaultWithoutId;
import static br.com.vroc.starwars.testsupport.mocks.MockCharacters.fromRequest;
import static br.com.vroc.starwars.testsupport.mocks.MockCharacters.request;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.vroc.starwars.domain.business.StarWarsCharacterBusiness;
import br.com.vroc.starwars.domain.client.StarWarsApiClient;
import br.com.vroc.starwars.domain.entity.Character;
import br.com.vroc.starwars.domain.repository.CharacterRepository;
import org.junit.jupiter.api.Test;

class StarWarsCharacterBusinessTest {

    private final CharacterRepository repository = mock();
    private final StarWarsApiClient client = mock();
    private final StarWarsCharacterBusiness business = new StarWarsCharacterBusiness(repository, client);

    @Test
    void createCharacter_shouldSaveCharacterWithApiDetails() {
        Character character = fromRequest(request());
        Character apiCharacter = createDefaultWithoutId();
        Character newCharacter = character.toBuilder()
            .species(apiCharacter.species())
            .planet(apiCharacter.planet())
            .lightsaberColor(apiCharacter.lightsaberColor())
            .build();

        when(client.getCharacterDetails(any(String.class))).thenReturn(apiCharacter);
        when(repository.save(any(Character.class))).thenReturn(newCharacter);

        Character result = business.createCharacter(character);

        assertThat(result).isEqualTo(newCharacter);

        verify(client).getCharacterDetails(character.name());
        verify(repository).save(newCharacter);
    }

}