package br.com.vroc.starwars.infrastructure.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.vroc.starwars.application.mapper.CharacterMapper;
import br.com.vroc.starwars.domain.entity.Character;
import br.com.vroc.starwars.infrastructure.entity.CharacterEntity;
import br.com.vroc.starwars.testsupport.mocks.MockCharacters;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class CharacterRepositoryImplTest {

    private final StarWarsMongoRepository mongoRepository = mock();

    private final CharacterMapper mapper = mock();

    private final CharacterRepositoryImpl repository = new CharacterRepositoryImpl(mongoRepository, mapper);

    @Test
    void save_shouldSaveCharacterAndReturnMappedCharacter() {
        Character character = MockCharacters.createDefaultWithoutId();
        CharacterEntity entity = new CharacterEntity(UUID.randomUUID(), "Luke Skywalker", "Human",
            "Tatooine", "Rebels", "Blue");
        Character savedCharacter = MockCharacters.createDefault();

        when(mapper.toEntity(character)).thenReturn(entity);
        when(mongoRepository.save(any(CharacterEntity.class))).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(savedCharacter);

        Character result = repository.save(character);

        assertThat(result).isEqualTo(savedCharacter);

        verify(mapper).toEntity(character);
        verify(mongoRepository).save(any(CharacterEntity.class));
        verify(mapper).toDomain(entity);
    }
}