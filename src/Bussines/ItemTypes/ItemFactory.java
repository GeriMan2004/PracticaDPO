package src.Bussines.ItemTypes;

import src.Bussines.Item;

public class ItemFactory {
    public static Item createItem(Item baseItem) {
        String type = baseItem.getObject_type();
        if(type.equalsIgnoreCase("Superweapon")) {
            return new SuperWeapon(baseItem.getId_object(), baseItem.getName(), type, baseItem.getPowerValue(), baseItem.getDurability());
        } else if(type.equalsIgnoreCase("Weapon")) {
            return new Weapon(baseItem.getId_object(), baseItem.getName(), type, baseItem.getPowerValue(), baseItem.getDurability());
        } else if(type.equalsIgnoreCase("Superarmor")) {
            return new SuperArmor(baseItem.getId_object(), baseItem.getName(), type, baseItem.getPowerValue(), baseItem.getDurability());
        } else if(type.equalsIgnoreCase("Armor")) {
            return new Armor(baseItem.getId_object(), baseItem.getName(), type, baseItem.getPowerValue(), baseItem.getDurability());
        } else {
            return baseItem; // fallback (or throw an exception)
        }
    }
}
