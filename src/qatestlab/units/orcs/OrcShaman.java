package qatestlab.units.orcs;

import qatestlab.ranks.Mage;
import qatestlab.units.Race;
import qatestlab.units.Squad;
import qatestlab.units.Unit;
import qatestlab.utils.GameLogger;
import qatestlab.utils.RandomHelper;

import java.util.Random;

/**
 * Created by user on 09.06.2017.
 */
public class OrcShaman extends Unit implements Mage {
    /**
     * To specify Orc Shaman creation logig
     */
    public OrcShaman() {
        race = Race.ORC;
        name = "Shaman";
    }

    /**
     * To privilege friendly squad unit
     * @param unitToBuff - friendly unit
     */
    @Override
    public void buff(Unit unitToBuff) {
        unitToBuff.setPrivilege(true);
        GameLogger.log(unitToBuff.getRaceAndName() + " is privileged!");
    }

    /**
     * To unprivilege enemy squad unit
     * @param unitToAttack - enemy unit to unprivilege
     */
    @Override
    public void attack(Unit unitToAttack) {
        unitToAttack.setPrivilege(false);
        GameLogger.log(unitToAttack.getRaceAndName() + " is unprivileged !");
    }

    /**
     * To specify action of Orc Shaman during the round
     * @param teammates - friendly units squad
     * @param enemies - enemy units squad
     */
    @Override
    public void doAction(Squad teammates, Squad enemies) {
        setActioned(true);
        Unit unitToDoAction;
        switch (new Random().nextInt(2)) {
            case 0:  {
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
                if(enemies.getPrivilegedUnits().isEmpty()) {
                    GameLogger.log("There are no privileged " + enemies.getSquadRace());
                    return;
                }
                unitToDoAction = RandomHelper.getRandomUnit(enemies);
                attack(unitToDoAction);
                enemies.getPrivilegedUnits().remove(unitToDoAction);
                break;
            }
        }
    }

    /**
     * To create Orc Shaman
     * @return Orc Shaman
     */
    @Override
    public Unit createUnit() {
        return new OrcShaman();
    }
}
