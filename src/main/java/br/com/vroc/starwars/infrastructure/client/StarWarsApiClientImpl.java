package br.com.vroc.starwars.infrastructure.client;

import br.com.vroc.starwars.application.mapper.CharacterMapper;
import br.com.vroc.starwars.domain.client.StarWarsApiClient;
import br.com.vroc.starwars.domain.entity.Character;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StarWarsApiClientImpl implements StarWarsApiClient {

    private final StarWarsApiFeignClient client;
    private final CharacterMapper mapper;

    @Override
    public Character getCharacterDetails(String name) {
        return mapper.toDomain(client.getCharacterDetails(name));
    }
}
