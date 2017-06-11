package qatestlab.units.elves;

import qatestlab.ranks.Warrior;
import qatestlab.units.Race;
import qatestlab.units.Squad;
import qatestlab.units.Unit;
import qatestlab.utils.GameLogger;
import qatestlab.utils.RandomHelper;

/**
 * Created by user on 08.06.2017.
 * This class specifies Warrior of Elves
 */
public class ElvenWarrior extends Unit implements Warrior {

    /**
     * To specify Elven Warrior creaton logic
     */
    public ElvenWarrior() {
        primaryDamage = 15;
        race = Race.ELF;
        name = "Warrior";
    }

    /**
     * To attack enemy unit
     * @param unitToAttack - unit to attack
     */
    @Override
    public void attack(Unit unitToAttack) {
        int currentDamage = giveCurrentDamage(primaryDamage);
        unitToAttack.receiveDamage(currentDamage);
        GameLogger.log(getRaceAndName() + " attacked " + unitToAttack.getRaceAndName() + " with a sword to "
                + String.valueOf(currentDamage) + " HP ");
    }

    /**
     * To specify action of Elven Warrior during the round
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
     * To create Elven Warrior
     * @return
     */
    @Override
    public Unit createUnit() {
        return new ElvenWarrior();
    }
}
