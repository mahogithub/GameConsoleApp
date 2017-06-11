package qatestlab.units.humans;

import qatestlab.ranks.Archer;
import qatestlab.units.Race;
import qatestlab.units.Squad;
import qatestlab.utils.RandomHelper;
import qatestlab.units.Unit;
import qatestlab.utils.GameLogger;

import java.util.Random;

/**
 * Created by user on 11.06.2017.
 * This class specifies Crossbowman of Humans race
 */
public class HumanCrossbowman extends Unit implements Archer {

    /**
     * damage of secondary attack
     */
    private int secondaryDamage;

    /**
     * To initialize humans crossbowman
     */
    public HumanCrossbowman() {
        primaryDamage = 5;
        secondaryDamage  = 3;
        race = Race.HUMAN;
        name = "Crossbowman";
    }

    /**
     * To specify primary attack of humans crossbowman against enemy unit
     * @param unitToAttack - enemy unit
     */
    @Override
    public void primaryAttack(Unit unitToAttack) {
        int currentDamage = giveCurrentDamage(primaryDamage);
        unitToAttack.receiveDamage(currentDamage);
        GameLogger.log(getRaceAndName() + " attacked " + unitToAttack.getRaceAndName() + " with an crossbow shot for "
                + String.valueOf(currentDamage) + " HP ");
    }

    /**
     * To specify secondary attack of humans crossbowman against enemy unit
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
     * To specify action of humans crossbowman during the round
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
     * To create humans crossbowman
     * @return
     */
    @Override
    public Unit createUnit() {
        return new HumanCrossbowman();
    }
}
