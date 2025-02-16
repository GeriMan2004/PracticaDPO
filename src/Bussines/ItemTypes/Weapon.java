package src.Bussines.ItemTypes;

import src.Bussines.Item;

public class Weapon extends Item {
    public Weapon(long id, String name, String objectType, int power, int durability) {
        super(id, name, objectType, power, durability);
    }

    /**
     * For a normal weapon, the effective attack contribution is:
     * power / 20.
     */
    public double getEffectiveAttack(double attackerWeight) {
        return power / 20.0;
    }
}
