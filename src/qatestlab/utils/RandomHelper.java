package qatestlab.utils;

import qatestlab.units.Squad;
import qatestlab.units.Unit;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 08.06.2017.
 * This is class for complex random actions
 */
public class RandomHelper {
    /**
     * To get random unit of the squad using both privileged and general group
     * @param squadToGet - squad of the units
     * @return random unit of the squad
     */
    public static Unit getRandomUnit(Squad squadToGet) {
        ArrayList<Unit> units = new ArrayList<>();
        units.addAll(squadToGet.getUnits());
        units.addAll(squadToGet.getPrivilegedUnits());
        return units.get(new Random().nextInt(units.size()));

    }
}