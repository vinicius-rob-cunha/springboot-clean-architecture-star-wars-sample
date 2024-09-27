package br.com.vroc.starwars.infrastructure.client;

import br.com.vroc.starwars.infrastructure.client.response.StarWarsSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "starWarsApiClient", url = "${feign.client.starWars.url}", path = "/starwars")
public interface StarWarsApiFeignClient {

    @GetMapping("/character")
    StarWarsSearchResponse getCharacterDetails(@RequestParam("search") String name);
}
