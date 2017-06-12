package qatestlab.units;

import qatestlab.utils.GameLogger;

import java.util.*;
/**
 * Created by user on 08.06.2017.
 * This class specifies a squad of units in the game
 */
public class Squad {
    private List<Unit> unitsToBePriveleged;
    /**
     * group of privileged units
     */
    private List<Unit> privilegedUnits;
    /**
     * group of non-privileged units
     */
    private List<Unit> units;
    /**
     * race of the squad
     */
    private String squadRace;

    /**
     * To initialize the squad of units
     * @param initUnits - map, keys are units, values are amount of units accordingly
     * @param squadRace - race of the squad
     */
    public Squad(Map<Unit, Integer> initUnits, String squadRace ) {
        this.squadRace = squadRace;
        units = new LinkedList<>();
        privilegedUnits = new LinkedList<>();
        unitsToBePriveleged = new LinkedList<>();
        for(Map.Entry<Unit, Integer> unitIntegerEntry: initUnits.entrySet()) {
            for(int i = 0; i < unitIntegerEntry.getValue(); i++) {
                units.add(unitIntegerEntry.getKey().createUnit());
            }
        }

    }

    /**
     * To check if the unit is killed
     * @param unit - unit to check
     */
    public void checkKilled(Unit unit) {
        if(unit.getHp() <= 0) {
            GameLogger.log(unit.getRaceAndName() + " has been killed." );
            units.remove(unit);
            if(!privilegedUnits.isEmpty()) {
                privilegedUnits.remove(unit);
            }
            //to check if all units of the squad are killed
            checkAllKilled();
        }
    }

    /**
     * To check if all units of the squad is killed and game over.
     */
    private void checkAllKilled() {
        if(units.isEmpty() && privilegedUnits.isEmpty()) {
            GameLogger.log(squadRace + " have been killed. Game over.");
            System.exit(0);
        }
    }

    /**
     * To get group of non-privileged units
     * @return group of non-privileged units
     */
    public List<Unit> getUnits() { return units; }

    /**
     *To charge enemy squad
     * @param target - enemy squad
     */
    public void charge( Squad target ) {
        //set all units of the squad as not actioned
        resetActioned();
        GameLogger.log("Privileged " + squadRace + " action start");
        for(Unit privilegeUnit: privilegedUnits) {
            privilegeUnit.doAction(this, target);
            unPrivilegeUnit(privilegeUnit);
        }
        GameLogger.log("Privileged " + squadRace + " action end");
        GameLogger.log("Non-privileged " + squadRace + " action start");
        for(Unit generalUnit: units) {
            if(!generalUnit.isActioned())generalUnit.doAction(this, target);
        }
        GameLogger.log("Non-privileged " + squadRace + " action end");

        //set newly privileged units as privileged
        units.removeAll(unitsToBePriveleged);
        privilegedUnits.clear();
        privilegedUnits.addAll(unitsToBePriveleged);
        unitsToBePriveleged.clear();
    }

    /**
     * To reset actioned flag for privilege and non-privilege groups of unit in squad
     */
    public void  resetActioned() {
        for(Unit unit: privilegedUnits) {
            unit.setActioned(false);
        }

        for(Unit unit: units) {
            unit.setActioned(false);
        }
    }

    /**
     * To get privileged units group
     * @return privileged units group
     */
    public List<Unit> getPrivilegedUnits() {
        return privilegedUnits;
    }

    /**
     * To privilege the unit
     * @param unitToPrivilege - unit to privilege
     */
    public void privilegeUnit(Unit unitToPrivilege) {
        unitsToBePriveleged.add(unitToPrivilege);
        GameLogger.log(unitToPrivilege.getRaceAndName() + " has been moved to priviledge group");
    }

    /**
     * To disable privilege of the unit and move it to non-privileged units group
     * @param unit
     */
    public void unPrivilegeUnit(Unit unit) {
        unit.setPrivilege(false);
        units.add(unit);
        privilegedUnits.remove(unit);
    }

    /**
     * To get race of the squad
     * @return race of the squad
     */
    public String getSquadRace() {
        return squadRace;
    }
}