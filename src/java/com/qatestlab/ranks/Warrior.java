package java.com.qatestlab.ranks;

import java.com.qatestlab.units.Unit;

/**
 * Created by user on 07.06.2017.
 * This Interface specifies behavior of warrior rank
 */
public interface Warrior {
    /**
     * To attack enemy unit
     * @param unitToAttack
     */
    void attack (Unit unitToAttack);
}
