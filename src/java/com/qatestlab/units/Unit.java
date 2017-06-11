package java.com.qatestlab.units;

/**
 * Created by user on 07.06.2017.
 *
 * This class is parent class for all game characters. Specifies general logic
 */
public abstract class Unit {

    /**
     * health points of the unit. Value 100 is default.
     */
    protected int hp = 100;
    /**
     * name of the unit
     */
    protected String name;
    /**
     * race of the unit. See Race enum for details.
     */
    protected Race race;
    /**
     * primary damage value
     */
    protected int primaryDamage;
    /**
     * damage bonus given by privilege
     */
    protected float privilegeBonus = 1.5f;
    /**
     * privilege existence for unit flag
     */
    protected boolean privilege = false;
    /**
     * cursed unit flag
     */
    private boolean cursed = false;
    /**
     * Coefficient of damage decreased by curse
     */
    private int cursedCoefficient = 2;
    /**
     * Action of the unit during current round flag
     */
    private boolean actioned = false;

    /**
     * Returns the value if actioned flag
     * @return true if unit actioned during the round
     */
    public boolean isActioned() {
        return actioned;
    }

    /**
     * Set if the unit actioned during the round
     * @param actioned - true or false
     */
    public void setActioned(boolean actioned) {
        this.actioned = actioned;
    }

    /**
     * To validate if the unit privileged or not
     * @return true if privileged and false if not
     */
    protected boolean isPrivilege() {
        return privilege;
    }

    /**
     * Returns the value of privileged flag
     * @param privilege - true if the unit is privileged
     */
    public void setPrivilege(boolean privilege) {
        this.privilege = privilege;
    }

    /**
     * To get units race and name values
     * @return String, concatenation of race and name values
     */
    public String getRaceAndName() {
        return race.getRace() + name;
    }

    /**
     * Returns current value of damage given by the unit including curse and privilege
     * @param damage - initiated value of damage givin
     * @return calculated value by damage given
     */
    protected int giveCurrentDamage(int damage) {
        damage = isCursed() ? damage/cursedCoefficient : damage;
        setCursed(false);
        damage = isPrivilege() ? (int) (damage * privilegeBonus) : damage;
        return damage;
    }

    /**
     * To set value of units HP with damage received
     * @param damageReceived - value of damage received
     */
    public void receiveDamage(int damageReceived) {
        this.hp -= damageReceived;
    }

    /**
     * To get value of units HP
     * @return value of units HP
     */
    public int getHp() { return hp; }

    /**
     * To validate if the unit is cursed
     * @return cursed flag value
     */
    public boolean isCursed() {
        return cursed;
    }

    /**
     * To set value of cursed flag of the unit
     * @param cursed
     */
    public void setCursed(boolean cursed) {
        this.cursed = cursed;
    }

    /**
     * To implement logic of units action with friendly or enemy squad
     * @param teammates - friendly units squad
     * @param enemies - enemy units squad
     */
    public abstract void doAction(Squad teammates, Squad enemies);

    /**
     * To implement units creation logic
     * @return
     */
    public abstract Unit createUnit();
}
