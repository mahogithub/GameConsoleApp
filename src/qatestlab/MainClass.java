package qatestlab;

import qatestlab.units.Squad;
import qatestlab.units.elves.ElvenArcher;
import qatestlab.units.elves.ElvenMage;
import qatestlab.units.elves.ElvenWarrior;
import qatestlab.units.humans.HumanCrossbowman;
import qatestlab.units.humans.HumanMage;
import qatestlab.units.humans.HumanWarrior;
import qatestlab.units.orcs.OrcArcher;
import qatestlab.units.orcs.OrcShaman;
import qatestlab.units.undead.UndeadHunter;
import qatestlab.units.undead.UndeadNecromancer;
import qatestlab.units.undead.UndeadZombie;
import qatestlab.units.orcs.OrcGoblin;
import qatestlab.units.Unit;
import qatestlab.utils.GameLogger;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by user on 07.06.2017.
 * This is the Main class of the program. Use it as entry point.
 */
public class MainClass {

    /**
     * To run the game
     * @param args - console arguments to the program
     */
    public static void main(String args[]) {
        Squad unitsOfLight = initUnitsOfLight();
        Squad unitsOfDarkness = initUnitsOfDarkness();
        int currentRound = 1;
        do {
            GameLogger.log("\n ******* Round " + String.valueOf(currentRound) + " *********\n");
            switch (new Random().nextInt(2)) {
                case 0: {
                    unitsOfLight.charge(unitsOfDarkness);
                    break;
                }
                case 1: {
                    unitsOfDarkness.charge(unitsOfLight);
                    break;
                }
            }
            currentRound++;
        } while (true);
    }

    /**
     * To initialize Elves or Humans squad
     * @return
     */
    public static Squad initUnitsOfLight() {
        Map<Unit, Integer> creaturesMap = new HashMap<>();
        String squadRace = "Light";
        switch (new Random().nextInt(2)) {
            case 0: {
                creaturesMap.put(new ElvenMage(), 1);
                creaturesMap.put(new ElvenArcher(), 3);
                creaturesMap.put(new ElvenWarrior(), 4);
                squadRace = "Elves";
                break;
            }
            case 1: {
                creaturesMap.put(new HumanMage(), 1);
                creaturesMap.put(new HumanCrossbowman(), 3);
                creaturesMap.put(new HumanWarrior(), 4);
                squadRace = "Humans";
                break;
            }
        }
        return new Squad(creaturesMap, squadRace);
    }

    /**
     * To initialize Orcs or Undead squad
     * @return
     */
    public static Squad initUnitsOfDarkness() {
        Map<Unit, Integer> creaturesMap = new HashMap<>();
        String squadRace = "Darkness";
        switch (new Random().nextInt(2)) {
            case 0: {
                creaturesMap.put(new OrcShaman(), 1);
                creaturesMap.put(new OrcArcher(), 3);
                creaturesMap.put(new OrcGoblin(), 4);
                squadRace = "Orcs";
                break;
            }
            case 1: {
                creaturesMap.put(new UndeadNecromancer(), 1);
                creaturesMap.put(new UndeadHunter(), 3);
                creaturesMap.put(new UndeadZombie(), 4);
                squadRace = "Undead";
                break;
            }
        }
        return new Squad(creaturesMap, squadRace);
    }


}
