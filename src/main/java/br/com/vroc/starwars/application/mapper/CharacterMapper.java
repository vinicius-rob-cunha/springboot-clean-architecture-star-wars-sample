package br.com.vroc.starwars.application.mapper;

import br.com.vroc.starwars.application.controller.request.CharacterRequest;
import br.com.vroc.starwars.application.controller.response.CharacterResponse;
import br.com.vroc.starwars.domain.entity.Character;
import br.com.vroc.starwars.infrastructure.client.response.StarWarsSearchResponse;
import br.com.vroc.starwars.infrastructure.entity.CharacterEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    Character toDomain(CharacterRequest character);

    Character toDomain(StarWarsSearchResponse character);

    Character toDomain(CharacterEntity character);

    CharacterResponse toResponse(Character character);

    CharacterEntity toEntity(Character character);

}
