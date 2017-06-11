package qatestlab.units.undead;

import qatestlab.ranks.Warrior;
import qatestlab.units.Race;
import qatestlab.units.Squad;
import qatestlab.units.Unit;
import qatestlab.utils.GameLogger;
import qatestlab.utils.RandomHelper;

/**
 * Created by user on 11.06.2017.
 * This class specifies Zombie of Undead race
 */
public class UndeadZombie extends Unit implements Warrior {

    /**
     * To specify Zombie creation logic
     */
    public UndeadZombie() {
        primaryDamage = 18;
        race = Race.UNDEAD;
        name = "Zombie";
    }

    /**
     * To attack enemy unit
     * @param unitToAttack - enemy unit to attack
     */
    @Override
    public void attack(Unit unitToAttack) {
        int currentDamage = giveCurrentDamage(primaryDamage);
        unitToAttack.receiveDamage(currentDamage);
        GameLogger.log(getRaceAndName() + " attacked " + unitToAttack.getRaceAndName() + " with a lance for "
                + String.valueOf(currentDamage) + " HP ");
    }

    /**
     * To specify action of Zombie during round
     * @param teammates - friendly units squad
     * @param enemies - enemy units squad
     */
    @Override
    public void doAction(Squad teammates, Squad enemies) {
        setActioned(true);
        Unit unitToDoAction = RandomHelper.getRandomUnit(enemies);
        attack(unitToDoAction);
        enemies.checkKilled(unitToDoAction);
    }



    /**
     * To create Zombie
     * @return
     */
    @Override
    public Unit createUnit() {
        return new UndeadZombie();
    }
}
