package src.Bussines.ItemTypes;

import src.Bussines.Item;

public class Armor extends Item {
    public Armor(long id, String name, String objectType, int power, int durability) {
        super(id, name, objectType, power, durability);
    }

    /**
     * For a normal armour, the effective defensive value is:
     * power / 20.
     */
    public double getEffectiveArmor(double defenderWeight) {
        return power / 20.0;
    }
}
