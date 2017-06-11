package java.com.qatestlab.units;

/**
 * Created by user on 08.06.2017.
 * This is an enum for declaration races of the units in the game
 */
public enum Race {
    ELF("Elven "),
    HUMAN("Human "),
    ORC("Orc "),
    UNDEAD("Undead ");

    private String race;
    public String getRace() {
        return race;
    }
    Race(String race) {
        this.race = race;
    }
}
