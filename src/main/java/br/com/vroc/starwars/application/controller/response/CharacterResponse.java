package br.com.vroc.starwars.application.controller.response;

import java.util.UUID;
import lombok.Builder;

@Builder
public record CharacterResponse(
    UUID id,
    String name,
    String species,
    String planet,
    String affiliation,
    String lightsaberColor
) {

}
