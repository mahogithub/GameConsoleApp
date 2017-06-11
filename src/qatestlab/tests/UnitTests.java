package qatestlab.tests;


import qatestlab.units.Squad;
import qatestlab.units.Unit;
import qatestlab.units.elves.ElvenMage;
import qatestlab.units.orcs.OrcGoblin;
import qatestlab.utils.GameLogger;
import org.junit.*;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by user on 08.06.2017.
 */
public class UnitTests {

    public Random random = new Random();

    @Test
    @Ignore
    public void testAttack() {
        ElvenMage elvenMage = new ElvenMage();
        OrcGoblin testOrcGoblin = new OrcGoblin();
        while (testOrcGoblin.getHp() > 0 && elvenMage.getHp() > 0) {
            switch (random.nextInt(2)) {
                case 0: {
                    testOrcGoblin.attack(elvenMage);
                    break;
                }
                case 1: {
                    elvenMage.attack(testOrcGoblin);
                    break;
                }
            }
            GameLogger.log(String.valueOf(elvenMage.getHp()));
            GameLogger.log(String.valueOf(testOrcGoblin.getHp()));
        }
    }

    @Test
    @Ignore
    public void testCursed() {
        OrcGoblin goblin = new OrcGoblin();
        ElvenMage mage = new ElvenMage();
        goblin.attack(mage);
        goblin.setCursed(true);
        Assert.assertTrue(goblin.isCursed());
        goblin.attack(mage);
        Assert.assertFalse(goblin.isCursed());
        goblin.attack(mage);
    }

    @Test
    @Ignore
    public void magePrivilegeHimself() {
        HashMap<Unit, Integer> map = new HashMap<>();
        map.put(new ElvenMage(), 1);
        Squad squad = new Squad(map, "Elves");
        squad.privilegeUnit(squad.getUnits().get(0));

    }


    @Test
    public void testMageAct() {
        HashMap<Unit, Integer> map = new HashMap<>();
        map.put(new ElvenMage(), 2);
        Squad squad = new Squad(map, "Elves");
        squad.charge(squad);
    }
}
