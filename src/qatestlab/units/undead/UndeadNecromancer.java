package qatestlab.units.undead;

import qatestlab.ranks.Mage;
import qatestlab.units.Race;
import qatestlab.units.Squad;
import qatestlab.units.Unit;
import qatestlab.utils.GameLogger;
import qatestlab.utils.RandomHelper;

import java.util.Random;

/**
 * Created by user on 11.06.2017.
 * This class specifies Necromancer of Undead race
 */
public class UndeadNecromancer extends Unit implements Mage {

    /**
     * To specify Undead Necromancer creation logic
     */
    public UndeadNecromancer() {
        primaryDamage = 5;
        race = Race.UNDEAD;
        name = "Necromancer";
    }

    /**
     * To set enemy unit cursed
     * @param unitToBuff - enemy unit to set cursed
     */
    @Override
    public void buff(Unit unitToBuff) {
        unitToBuff.setCursed(true);
        GameLogger.log("Necromancer buffed " + unitToBuff.getRaceAndName());
    }

    /**
     * To attack enemy unit
     * @param unitToAttack - enemy unit to attack
     */
    @Override
    public void attack(Unit unitToAttack) {
        int currentDamage = giveCurrentDamage(primaryDamage);
        unitToAttack.receiveDamage(currentDamage);
        GameLogger.log(getRaceAndName() + " attacked " + unitToAttack.getRaceAndName() + " with magic for "
                + String.valueOf(currentDamage) + " HP ");
    }

    /**
     * To specify action of Undead Necromancer during the round
     * @param teammates - friendly units squad
     * @param enemies - enemy units squad
     */
    @Override
    public void doAction(Squad teammates, Squad enemies) {
        setActioned(true);
        Unit unitToDoAction = RandomHelper.getRandomUnit(enemies);
        switch (new Random().nextInt(2)) {
            case 0: {
                buff(unitToDoAction);
            }
            case 1: {
                attack(unitToDoAction);
                enemies.checkKilled(unitToDoAction);
                break;
            }
        }
    }

    /**
     * To create Undead Necromancer
     * @return
     */
    @Override
    public Unit createUnit() {
        return new UndeadNecromancer();
    }
}
