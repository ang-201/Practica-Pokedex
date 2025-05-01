package com.daw.pokedex.controller;

import com.daw.pokedex.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")  // Prefijo de las rutas
public class PokemonController {

    private final List<PokemonSummary> pokemonList = Arrays.asList(
            new PokemonSummary(30, "nidorina", 200,
                    List.of(new TypeSlot(new Type("poison", "https://pokeapi.co/api/v2/type/4/"), 1)),
                    List.of(
                            new AbilitySlot(new Ability("poison-point", "https://pokeapi.co/api/v2/ability/38/"), false, 1),
                            new AbilitySlot(new Ability("rivalry", "https://pokeapi.co/api/v2/ability/79/"), false, 2),
                            new AbilitySlot(new Ability("hustle", "https://pokeapi.co/api/v2/ability/55/"), true, 3)
                    ),
                    new Sprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/30.png")
            ),
            new PokemonSummary(31, "nidoqueen", 600,
                    List.of(
                            new TypeSlot(new Type("poison", "https://pokeapi.co/api/v2/type/4/"), 1),
                            new TypeSlot(new Type("ground", "https://pokeapi.co/api/v2/type/5/"), 2)
                    ),
                    List.of(
                            new AbilitySlot(new Ability("poison-point", "https://pokeapi.co/api/v2/ability/38/"), false, 1),
                            new AbilitySlot(new Ability("rivalry", "https://pokeapi.co/api/v2/ability/79/"), false, 2),
                            new AbilitySlot(new Ability("sheer-force", "https://pokeapi.co/api/v2/ability/125/"), true, 3)
                    ),
                    new Sprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/31.png")
            ),
            new PokemonSummary(25, "pikachu", 60,
                    List.of(new TypeSlot(new Type("electric", "https://pokeapi.co/api/v2/type/13/"), 1)),
                    List.of(
                            new AbilitySlot(new Ability("static", "https://pokeapi.co/api/v2/ability/9/"), false, 1),
                            new AbilitySlot(new Ability("lightning-rod", "https://pokeapi.co/api/v2/ability/31/"), true, 3)
                    ),
                    new Sprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png")
            ),
            new PokemonSummary(172, "pichu", 20,
                    List.of(new TypeSlot(new Type("electric", "https://pokeapi.co/api/v2/type/13/"), 1)),
                    List.of(
                            new AbilitySlot(new Ability("static", "https://pokeapi.co/api/v2/ability/9/"), false, 1),
                            new AbilitySlot(new Ability("lightning-rod", "https://pokeapi.co/api/v2/ability/31/"), true, 3)
                    ),
                    new Sprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/172.png")
            ),
            new PokemonSummary(26, "raichu", 300,
                    List.of(new TypeSlot(new Type("electric", "https://pokeapi.co/api/v2/type/13/"), 1)),
                    List.of(
                            new AbilitySlot(new Ability("static", "https://pokeapi.co/api/v2/ability/9/"), false, 1),
                            new AbilitySlot(new Ability("lightning-rod", "https://pokeapi.co/api/v2/ability/31/"), true, 3)
                    ),
                    new Sprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/26.png")
            )
    );

    @GetMapping("/pokedex")
    public ResponseEntity<PokemonListResponse> getPokemonList(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "2") int limit) {

        // Validar los parámetros para evitar errores
        if (offset < 0 || limit <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PokemonListResponse(0, List.of()));  // Respuesta vacía si los parámetros son incorrectos
        }

        try {
            // Total de pokemons
            int total = pokemonList.size();

            // Asegurarse de que el índice de corte no sea mayor que el total
            int toIndex = Math.min(offset + limit, total);

            // Paginación: devuelve solo los elementos solicitados
            List<PokemonSummary> paginatedList = pokemonList.subList(Math.min(offset, total), toIndex);

            // Retorno de la respuesta con el total y la sublista
            return ResponseEntity.ok(new PokemonListResponse(total, paginatedList));

        } catch (Exception e) {
            e.printStackTrace();  // Depuracion en caso de errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Agregar método para obtener un Pokémon por ID
    @GetMapping("/pokemon/{id}")
    public ResponseEntity<PokemonSummary> getPokemonById(@PathVariable int id) {
        Optional<PokemonSummary> pokemon = pokemonList.stream()
                .filter(p -> p.getId() == id)
                .findFirst();

        if (pokemon.isPresent()) {
            return ResponseEntity.ok(pokemon.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
