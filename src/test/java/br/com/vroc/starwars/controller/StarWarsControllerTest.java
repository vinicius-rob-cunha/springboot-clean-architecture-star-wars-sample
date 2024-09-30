package br.com.vroc.starwars.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.vroc.starwars.application.controller.StarWarsController;
import br.com.vroc.starwars.application.controller.request.CharacterRequest;
import br.com.vroc.starwars.application.controller.response.CharacterResponse;
import br.com.vroc.starwars.application.mapper.CharacterMapper;
import br.com.vroc.starwars.domain.business.StarWarsCharacterBusiness;
import br.com.vroc.starwars.domain.entity.Character;
import br.com.vroc.starwars.testsupport.mocks.MockCharacters;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StarWarsControllerTest {

    private final CharacterMapper mapper = mock();

    private final StarWarsCharacterBusiness business = mock();

    private final StarWarsController controller = new StarWarsController(business, mapper);

    @Test
    void createCharacter_shouldReturnCharacterResponse_whenValidRequest() {
        var request = MockCharacters.request();

        var character = MockCharacters.fromRequest(request);

        var expectedResponse = MockCharacters.response(character);

        when(mapper.toDomain(any(CharacterRequest.class))).thenReturn(character);
        when(business.createCharacter(any(Character.class))).thenReturn(MockCharacters.createDefault());
        when(mapper.toResponse(any(Character.class))).thenReturn(expectedResponse);

        CharacterResponse response = controller.createCharacter(request);

        Assertions.assertThat(response).isEqualTo(expectedResponse);

        verify(business).createCharacter(any(Character.class));
    }

}