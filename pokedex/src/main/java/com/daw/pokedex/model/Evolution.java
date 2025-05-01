package com.daw.pokedex.model;

import java.util.List;

public class Evolution {
    private int id;
    private String name;
    private int weight;
    private List<TypeSlot> types;
    private List<AbilitySlot> abilities;
    private Sprite sprites;

    public Evolution() {
    }

    public Evolution(int id, String name, int weight, List<TypeSlot> types, List<AbilitySlot> abilities, Sprite sprites) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.types = types;
        this.abilities = abilities;
        this.sprites = sprites;
    }

    // Getters and setters
}


