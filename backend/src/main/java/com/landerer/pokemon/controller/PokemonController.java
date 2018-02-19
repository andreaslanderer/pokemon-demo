package com.landerer.pokemon.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.landerer.pokemon.dto.Pokemon;
import com.landerer.pokemon.dto.PokemonPreview;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v01/pokemon")
@CrossOrigin
public class PokemonController {

    @GetMapping
    @Cacheable("pokemonList")
    public List<PokemonPreview> getPokemon() {
        final RestTemplate template = new RestTemplate();
        final GetPokemonResponse response = template
                .getForObject("https://pokeapi.co/api/v2/pokemon", GetPokemonResponse.class);
        return response.getResults()
                .stream()
                .map(this::mapPokemonPreview)
                .collect(Collectors.toList());
    }

    private PokemonPreview mapPokemonPreview(PokemonResult result) {
        final PokemonPreview preview = new PokemonPreview();
        preview.setName(result.getName());
        final String[] urlParts = result.getUrl().split("/");
        preview.setId(Integer.valueOf(urlParts[urlParts.length-1]));
        return preview;
    }

    @GetMapping("/{id}")
    @Cacheable("pokemon")
    public Pokemon getPokemonForId(@PathVariable int id) {
        final RestTemplate template = new RestTemplate();
        String url = MessageFormat.format("https://pokeapi.co/api/v2/pokemon/{0}", id);
        final GetPokemonInfoResponse response = template.getForObject(url, GetPokemonInfoResponse.class);
        final Pokemon pokemon = new Pokemon();
        pokemon.setId(Integer.valueOf(response.getId()));
        pokemon.setType(response.getTypes().stream()
                .map(t -> t.getType().getName())
                .collect(Collectors.joining(" ")));
        return pokemon;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class GetPokemonResponse {
    private List<PokemonResult> results;

    public List<PokemonResult> getResults() {
        return results;
    }

    public void setResults(List<PokemonResult> results) {
        this.results = results;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class PokemonResult {
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class GetPokemonInfoResponse {
    private String id;

    private List<TypeDefinition> types;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TypeDefinition> getTypes() {
        return types;
    }

    public void setTypes(List<TypeDefinition> types) {
        this.types = types;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class TypeDefinition {
    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Type {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
