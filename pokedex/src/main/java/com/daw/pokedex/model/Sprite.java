package com.daw.pokedex.model;

public class Sprite {
    private String frontDefault;

    public Sprite(String frontDefault) {
        this.frontDefault = frontDefault;
    }

    public String getFrontDefault() {
        return frontDefault;
    }

    public void setFrontDefault(String frontDefault) {
        this.frontDefault = frontDefault;
    }
}
