package qatestlab.units.elves;

import qatestlab.ranks.Archer;
import qatestlab.units.Race;
import qatestlab.units.Squad;
import qatestlab.units.Unit;
import qatestlab.utils.GameLogger;
import qatestlab.utils.RandomHelper;

import java.util.Random;

/**
 * Created by user on 08.06.2017.
 * This class specifies Archer of Elves race
 */
public class ElvenArcher extends Unit implements Archer {

    /**
     * secondary attack damage value;
     */
    private int secondaryDamage;

    /**
     * To specify Elven Archer creation logic
     */
    public ElvenArcher() {
        primaryDamage = 7;
        secondaryDamage = 3;
        race = Race.ELF;
        name = "Archer";
    }

    /**
     * To specify primary attack against enemy unit
     * @param unitToAttack - enemy unit
     */
    @Override
    public void primaryAttack(Unit unitToAttack) {
        int currentDamage = giveCurrentDamage(primaryDamage);
        unitToAttack.receiveDamage(currentDamage);
        GameLogger.log(getRaceAndName() + " attacked " + unitToAttack.getRaceAndName() + " with an archery shot for "
                + String.valueOf(currentDamage) + " HP ");
    }

    /**
     * To specify secondary attack against enemy unit
     * @param unitToAttack
     */
    @Override
    public void secondaryAttack(Unit unitToAttack) {
        int currentDamage = giveCurrentDamage(secondaryDamage);
        unitToAttack.receiveDamage(currentDamage);
        GameLogger.log(getRaceAndName() + " attacked " + unitToAttack.getRaceAndName() + " to "
                + String.valueOf(currentDamage) + " HP ");
    }

    /**
     * To specify action of Elven Archer during the round
     * @param teammates - friendly units squad
     * @param enemies - enemy units squad
     */
    @Override
    public void doAction(Squad teammates, Squad enemies) {
        setActioned(true);
        Unit unitToDoAction = RandomHelper.getRandomUnit(enemies);
        switch (new Random().nextInt(2)) {
            case 0: {
                primaryAttack(unitToDoAction);
                enemies.checkKilled(unitToDoAction);
                break;
            }
            case 1: {
                secondaryAttack(unitToDoAction);
                enemies.checkKilled(unitToDoAction);
                break;
            }
        }
    }

    /**
     * To create Elven Archer
     * @return Elven Archer
     */
    @Override
    public Unit createUnit() {
        return new ElvenArcher();
    }
}
