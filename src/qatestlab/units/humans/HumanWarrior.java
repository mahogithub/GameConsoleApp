package qatestlab.units.humans;

import qatestlab.ranks.Warrior;
import qatestlab.units.Race;
import qatestlab.units.Squad;
import qatestlab.units.Unit;
import qatestlab.utils.GameLogger;
import qatestlab.utils.RandomHelper;

/**
 * Created by user on 09.06.2017.
 * This class specifies Warrior of Humans race
 */
public class HumanWarrior extends Unit implements Warrior {

    /**
     * To specify Human Warrior creation logic
     */
    public HumanWarrior() {
        primaryDamage = 18;
        race = Race.HUMAN;
        name = "Warrior";
    }

    /**
     * To attack enemy unit
     * @param unitToAttack - enemy unit
     */
    @Override
    public void attack(Unit unitToAttack) {
        int currentDamage = giveCurrentDamage(primaryDamage);
        unitToAttack.receiveDamage(currentDamage);
        GameLogger.log(getRaceAndName() + " attacked " + unitToAttack.getRaceAndName() + " with a sword to "
                + String.valueOf(currentDamage) + " HP ");
    }

    /**
     * To specify Human Warrior acton during the round
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
     * To create Human Warrior
     * @return
     */
    @Override
    public Unit createUnit() {
        return new HumanWarrior();
    }
}
