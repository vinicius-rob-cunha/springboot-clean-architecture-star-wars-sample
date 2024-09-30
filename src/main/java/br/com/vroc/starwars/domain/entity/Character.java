package br.com.vroc.starwars.domain.entity;

import java.util.UUID;
import lombok.Builder;
import lombok.With;

@With
@Builder(toBuilder = true)
public record Character(
    UUID id,
    String name,
    String species,
    String planet,
    String affiliation,
    String lightsaberColor
) {

}
