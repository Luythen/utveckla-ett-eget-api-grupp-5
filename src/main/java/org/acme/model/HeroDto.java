package org.acme.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Data Transfer Object som skickas från frontend. Innehåller endast nödvändiga fält.

@Entity
public class HeroDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private Race race;

    public int getId() {
        return id;
    }

    public HeroDto setId(int id) {
        this.id = id;
        return this;
    }

    public Race getRace() {

        return race;
    }

    public HeroDto setRace(Race race) {
        this.race = race;
        return this;
    }

}
