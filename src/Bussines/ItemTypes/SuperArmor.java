package src.Bussines.ItemTypes;

public class SuperArmor extends Armor {
    public SuperArmor(long id, String name, String objectType, int power, int durability) {
        super(id, name, objectType, power, durability);
    }
    /**
     * For a superarmour, the effective armor value is:
     * (power * attackerWeight) / 20.
     */
    @Override
    public double getEffectiveArmor(double defenderWeight) {
        return (power * defenderWeight) / 20.0;
    }
}
