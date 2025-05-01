package com.daw.pokedex.model;

public class AbilitySlot {
    private Ability ability;
    private boolean isHidden;
    private int slot;

    public AbilitySlot(Ability ability, boolean isHidden, int slot) {
        this.ability = ability;
        this.isHidden = isHidden;
        this.slot = slot;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}
