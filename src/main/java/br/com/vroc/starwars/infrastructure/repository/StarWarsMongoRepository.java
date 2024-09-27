package br.com.vroc.starwars.infrastructure.repository;

import br.com.vroc.starwars.infrastructure.entity.CharacterEntity;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StarWarsMongoRepository extends MongoRepository<CharacterEntity, UUID> {

}
