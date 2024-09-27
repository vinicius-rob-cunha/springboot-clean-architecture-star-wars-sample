package br.com.vroc.starwars.infrastructure.entity;

import java.util.UUID;
import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@With
@Document(collection = "characters")
public record CharacterEntity(
    @MongoId
    UUID id,
    String name,
    String species,
    String planet,
    String affiliation,
    String lightsaberColor
) {

}
