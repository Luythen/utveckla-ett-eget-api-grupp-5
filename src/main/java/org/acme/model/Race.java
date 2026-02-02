package org.acme.model;

import java.util.List;

public enum Race {

    HUMAN   ("human"    , "Human are potent in just about anything, but they don't excel in any particular trait. Humans gain +2 to all stats, on level up."),
    ORC     ("orc"      , "Orcs are massive, intimidating, and strong. Orcs gain +1 to all stats, and +3 to Strength, on level up."),
    ELF     ("elf"      , "Elves are naturally agile and quick on their feet. Elves gain +1 to all stats, and +3 to Dexterity, on level up."),
    DWARF   ("dwarf"    , "Dwarves are short, but sturdy. They know how to take a punch. Dwarves gain +1 to all stats, and +3 to Constitution, on level up.");

    private final String race;
    private final String flavour;

    public String getRace() {
        return race;
    }

    public String getFlavour(){
        return flavour;
    }

    Race(String race, String flavour) {
        this.race = race;
        this.flavour = flavour;
    }

    public static List<Race> RaceList() {
        return List.of(Race.values());
    }

    public static Race fromString(String value) {
        for (Race r : Race.values()) {
            if (r.race.equalsIgnoreCase(value)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Unknown attribute: " + value);
    }

}
