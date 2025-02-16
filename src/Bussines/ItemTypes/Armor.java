package src.Bussines.ItemTypes;

import src.Bussines.Item;

public class Armor extends Item {
    public Armor(long id, String name, String objectType, int power, int durability) {
        super(id, name, objectType, power, durability);
    }
    // Esta clase no tiene métodos adicionales, debido a que utiliza el metodo por defecto de la clase Item
    // Realmente esta clase no es necesaria, pero para un futuro desarrollo del juego se puede añadir funcionalidades
}
