package qatestlab.ranks;

import qatestlab.units.Unit;

/**
 * Created by user on 07.06.2017.
 * This interface specifies behavior of archer rank
 */
public interface Archer {

    /**
     * To implement primary attack of archer against enemy unit
     * @param unitToAttack - enemy unit
     */
    void primaryAttack(Unit unitToAttack);

    /**
     * To implement secondary attack of archer against enemy unit
     * @param unitToAttack
     */
    void secondaryAttack(Unit unitToAttack);
}
