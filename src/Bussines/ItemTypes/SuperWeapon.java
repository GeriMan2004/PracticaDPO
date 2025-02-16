package src.Bussines.ItemTypes;

public class SuperWeapon extends Weapon {
    public SuperWeapon(long id, String name, String objectType, int power, int durability) {
        super(id, name, objectType, power, durability);
    }

    /**
     * For a superweapon, the effective attack is modified by the attacker’s weight:
     * (power * attackerWeight) / 20.
     */
    @Override
    public double getEffectiveAttack(double attackerWeight) {
        return (power * attackerWeight) / 20.0;
    }
}
