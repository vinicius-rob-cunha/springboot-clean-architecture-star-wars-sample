package br.com.vroc.starwars.infrastructure.client.response;

public record StarWarsSearchResponse(
    String name,
    String species,
    String planet,
    String affiliation,
    String lightsaberColor
) {

}
