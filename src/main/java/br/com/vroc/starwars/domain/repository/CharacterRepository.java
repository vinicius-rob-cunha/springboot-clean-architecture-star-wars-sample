package br.com.vroc.starwars.domain.repository;

import br.com.vroc.starwars.domain.entity.Character;

public interface CharacterRepository {

    Character save(Character character);
}
