package qatestlab.units.orcs;

import qatestlab.ranks.Warrior;
import qatestlab.units.Race;
import qatestlab.units.Squad;
import qatestlab.utils.GameLogger;
import qatestlab.utils.RandomHelper;
import qatestlab.units.Unit;

/**
 * Created by user on 08.06.2017.
 * This class specifies Goblin of Orcs race
 */
public class OrcGoblin extends Unit implements Warrior {

    /**
     * To specify Goblin creation logic
     */
    public OrcGoblin() {
        race = Race.ORC;
        primaryDamage = 20;
        name = "Goblin";
    }

    /**
     * To attack the enemy unit
     * @param unitToAttack - enemy unit to attack
     */
    @Override
    public void attack(Unit unitToAttack) {
        int currentDamage = giveCurrentDamage(primaryDamage);
        unitToAttack.receiveDamage(currentDamage);
        GameLogger.log(getRaceAndName() + " attacked " + unitToAttack.getRaceAndName() + " with a club to "
                + String.valueOf(currentDamage) + " HP ");
    }

    /**
     * To specify action of Goblin during the round
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
     * To create Goblin
     * @return
     */
    @Override
    public Unit createUnit() {
        return new OrcGoblin();
    }
}
