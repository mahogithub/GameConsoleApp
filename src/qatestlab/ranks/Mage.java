package qatestlab.ranks;

import qatestlab.units.Unit;

/**
 * Created by user on 07.06.2017.
 * This interface specifies behavior of mage rank
 */
public interface Mage {
    /**
     * To buff the unit
     * @param unitToBuff - friendly or enemy unit to buff
     */
    void buff(Unit unitToBuff);

    /**
     * To attack the unit
     * @param unitToAttack - enemy unit to attack
     */
    void attack(Unit unitToAttack);
}
