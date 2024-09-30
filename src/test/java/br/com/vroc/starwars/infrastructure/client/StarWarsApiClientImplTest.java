package br.com.vroc.starwars.infrastructure.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.vroc.starwars.application.mapper.CharacterMapper;
import br.com.vroc.starwars.domain.entity.Character;
import br.com.vroc.starwars.infrastructure.client.response.StarWarsSearchResponse;
import br.com.vroc.starwars.testsupport.mocks.MockCharacters;
import org.junit.jupiter.api.Test;

class StarWarsApiClientImplTest {

    private final StarWarsApiFeignClient feignClient = mock();

    private final CharacterMapper mapper = mock();

    private final StarWarsApiClientImpl client = new StarWarsApiClientImpl(feignClient, mapper);

    @Test
    void getCharacterDetails_shouldReturnMappedCharacter() {
        String characterName = "Luke Skywalker";
        StarWarsSearchResponse apiResponse = new StarWarsSearchResponse(characterName, "Human",
            "Tatooine", "Rebels", "Blue");
        Character mappedCharacter = MockCharacters.createDefaultWithoutId();

        when(feignClient.getCharacterDetails(characterName)).thenReturn(apiResponse);
        when(mapper.toDomain(apiResponse)).thenReturn(mappedCharacter);

        Character result = client.getCharacterDetails(characterName);

        assertThat(result).isEqualTo(mappedCharacter);

        verify(feignClient).getCharacterDetails(characterName);
        verify(mapper).toDomain(apiResponse);
    }
}