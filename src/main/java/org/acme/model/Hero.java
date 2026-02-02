package org.acme.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private Race race;
    private boolean focusedFire;     // Elf
    private boolean steadyFrame;     // Dwarf
    private boolean strongArms;      // Orc
    private boolean jackOfAllTrades; // Human

    public int getId() {
        return id;
    }

    public Hero setId(int id) {
        this.id = id;
        return this;
    }

    public Race getRace() {

        return race;
    }

    public Hero setRace(Race race) {
        this.race = race;
        return this;
    }

    public boolean getFocusedFire() {
        return focusedFire;
    }

    public Hero setFocusedFire(boolean focusedFire) {
        this.focusedFire = focusedFire;
        return this;
    }

    public boolean getSteadyFrame() {
        return steadyFrame;
    }

    public Hero setSteadyFrame(boolean steadyFrame) {
        this.steadyFrame = steadyFrame;
        return this;
    }

    public boolean getStrongArms() {
        return strongArms;
    }

    public Hero setStrongArms(boolean strongArms) {
        this.strongArms = strongArms;
        return this;
    }

    public boolean getJackOfAllTrades() {
        return jackOfAllTrades;
    }

    public Hero setJackOfAllTrades(boolean jackOfAllTrades) {
        this.jackOfAllTrades = jackOfAllTrades;
        return this;
    }

}
