package com.daw.pokedex.model;

import java.util.List;

public class PokemonListResponse {

    private int count;  // Total de elementos
    private List<PokemonSummary> items;  // Lista de Pokémon solicitada

    public PokemonListResponse(int count, List<PokemonSummary> items) {
        this.count = count;
        this.items = items;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<PokemonSummary> getItems() {
        return items;
    }

    public void setItems(List<PokemonSummary> items) {
        this.items = items;
    }
}
