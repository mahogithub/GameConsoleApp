package qatestlab.units.undead;

import qatestlab.ranks.Archer;
import qatestlab.units.Race;
import qatestlab.units.Squad;
import qatestlab.units.Unit;
import qatestlab.utils.GameLogger;
import qatestlab.utils.RandomHelper;

import java.util.Random;

/**
 * Created by user on 11.06.2017.
 * This class specifies Undead Hunter
 */
public class UndeadHunter extends Unit implements Archer {

    /**
     * secondary attack damage value;
     */
    private int secondaryDamage;

    /**
     * To specify Undead Hunter creation logic
     */
    public UndeadHunter() {
        primaryDamage = 7;
        secondaryDamage = 3;
        race = Race.UNDEAD;
        name = "Hunter";
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
     * @param unitToAttack - enemy unit
     */
    @Override
    public void secondaryAttack(Unit unitToAttack) {
        int currentDamage = giveCurrentDamage(secondaryDamage);
        unitToAttack.receiveDamage(currentDamage);
        GameLogger.log(getRaceAndName() + " attacked " + unitToAttack.getRaceAndName() + " to "
                + String.valueOf(currentDamage) + " HP ");
    }

    /**
     * To specify action of Undead Hunter during the round
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
     * To create Undead Hunter
     * @return
     */
    @Override
    public Unit createUnit() {
        return new UndeadHunter();
    }
}
