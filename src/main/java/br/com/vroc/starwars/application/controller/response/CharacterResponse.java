package br.com.vroc.starwars.application.controller.response;

import java.util.UUID;

public record CharacterResponse(
    UUID id,
    String name,
    String species,
    String planet,
    String affiliation,
    String lightsaberColor
) {

}
