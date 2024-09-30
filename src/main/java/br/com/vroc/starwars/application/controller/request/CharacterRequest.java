package br.com.vroc.starwars.application.controller.request;

import lombok.Builder;
import lombok.With;

@With
@Builder(toBuilder = true)
public record CharacterRequest(
    String name,
    String affiliation
) {

}
