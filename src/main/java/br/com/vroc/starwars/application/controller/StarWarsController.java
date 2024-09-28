package br.com.vroc.starwars.application.controller;

import static org.springframework.http.HttpStatus.CREATED;

import br.com.vroc.starwars.application.controller.request.CharacterRequest;
import br.com.vroc.starwars.application.controller.response.CharacterResponse;
import br.com.vroc.starwars.application.logging.VrocLogger;
import br.com.vroc.starwars.application.mapper.CharacterMapper;
import br.com.vroc.starwars.domain.business.StarWarsCharacterBusiness;
import br.com.vroc.starwars.domain.entity.Character;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/starwars")
public class StarWarsController {

    private final VrocLogger logger = VrocLogger.getLogger(StarWarsController.class);

    private final StarWarsCharacterBusiness business;

    private final CharacterMapper mapper;

    @PostMapping("/characters")
    @ResponseStatus(CREATED)
    public CharacterResponse createCharacter(@RequestBody CharacterRequest request) {
        logger.info("Criando personagem", request);

        Character character = business.createCharacter(mapper.toDomain(request));

        logger.info("Personagem criado", character);

        return mapper.toResponse(character);
    }

}
