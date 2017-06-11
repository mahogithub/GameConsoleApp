package qatestlab.units.humans;

import qatestlab.units.Race;
import qatestlab.units.Squad;
import qatestlab.utils.RandomHelper;
import qatestlab.ranks.Mage;
import qatestlab.units.Unit;
import qatestlab.utils.GameLogger;

import java.util.Random;

/**
 * Created by user on 08.06.2017.
 * This class specifies Mage of Human race
 */
public class HumanMage extends Unit implements Mage {

    /**
     * To specify Human Mage creation logic
     */
    public HumanMage() {
        primaryDamage = 4;
        race = Race.HUMAN;
        name = "Mage";
    }

    /**
     * To set friendly unit privileged
     * @param unitToBuff - friendly unit to set privileged
     */
    @Override
    public void buff(Unit unitToBuff) {
        unitToBuff.setPrivilege(true);
    }

    /**
     * To attack enemy unit
     * @param unitToAttack - enemy unit to attack
     */
    @Override
    public void attack(Unit unitToAttack) {
        unitToAttack.receiveDamage(primaryDamage);
    }


    /**
     * To specify action of Human Mage during round
     * @param teammates - friendly units squad
     * @param enemies - enemy units squad
     */
    @Override
    public void doAction(Squad teammates, Squad enemies) {
        setActioned(true);
        Unit unitToDoAction;
        switch (new Random().nextInt(2)) {
            case 0: {
                unitToDoAction = RandomHelper.getRandomUnit(teammates);
                buff(unitToDoAction);
                if(unitToDoAction.isActioned()) {
                    teammates.privilegeUnit(unitToDoAction);
                }
                else {
                    GameLogger.log(unitToDoAction.getRaceAndName() + " is not actioned");
                    unitToDoAction.doAction(teammates, enemies);
                    unitToDoAction.setPrivilege(false);
                    unitToDoAction.setActioned(true);
                }
                break;
            }
            case 1: {
                unitToDoAction = RandomHelper.getRandomUnit(enemies);
                attack(unitToDoAction);
                enemies.checkKilled(unitToDoAction);
                break;
            }
        }

    }

    /**
     * To create Human Mage
     * @return
     */
    @Override
    public Unit createUnit() {
        return new HumanMage();
    }

}
