package br.com.vroc.starwars.infrastructure.repository;

import br.com.vroc.starwars.application.mapper.CharacterMapper;
import br.com.vroc.starwars.domain.entity.Character;
import br.com.vroc.starwars.domain.repository.CharacterRepository;
import br.com.vroc.starwars.infrastructure.entity.CharacterEntity;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CharacterRepositoryImpl implements CharacterRepository {

    private final StarWarsMongoRepository mongoRepository;
    private final CharacterMapper mapper;

    @Override
    public Character save(Character character) {
        CharacterEntity entity = mapper.toEntity(character).withId(UUID.randomUUID());
        var newCharacter = mongoRepository.save(entity);
        return mapper.toDomain(newCharacter);
    }

}
