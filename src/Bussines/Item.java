package src.Bussines;

import com.google.gson.annotations.SerializedName;

/**
 * Esta clase se encarga de las instancias de objetos en el juego, como armas y armaduras
 */
public class Item {
    private final long id;
    private final String name;
    @SerializedName("class")
    private String class_name;
    protected final int power;
    private int durability;

    /**
     * Constructor para establecer los parametros necesarios del objeto
     * @param id
     * @param name
     * @param class_name
     * @param power
     * @param durability
     */
    public Item(long id, String name, String class_name, int power, int durability) {
        this.id = id;
        this.name = name;
        this.class_name = class_name;
        this.power = power;
        this.durability = durability;
    }

    // Método "por defecto" para obtener el poder efectivo en ataque, en caso de que
    public double getEffectiveAttack(double attackerWeight) {
        return power / 20.0;
    }

    // Método "por defecto" para obtener el valor efectivo de la armadura
    public double getEffectiveArmor(double attackerWeight) {
        return power / 20.0;
    }

    /**
     * Este metodo se encarga de obtener el id del objeto
     * @return devuelve el id del objeto
     */
    public long getId_object() {
        return id;
    }

    /**
     * Este metodo se encarga de obtener el nombre del objeto
     * @return devuelve el nombre del objeto
     */
    public String getName() {
        return name;
    }
    /**
     * Este metodo se encarga de obtener el tipo de objeto
     * @return devuelve el tipo de objeto
     */
    public String getObject_type() {
        return class_name;
    }
    /**
     * Este metodo se encarga de obtener el poder del objeto
     * @return devuelve el poder del objeto
     */
    public int getPowerValue() {
        return power;
    }
    /**
     * Este metodo se encarga de obtener la durabilidad del objeto
     * @return devuelve la durabilidad del objeto
     */
    public int getDurability() {
        return durability;
    }
    /**
     * Este metodo se encarga de disminuir la durabilidad del objeto
     */
    public void decreaseDurability()
    {
        durability--;
    }
    /**
     * Este metodo se encarga de pasar a string los atributos del objeto
     */
    @Override
    public String toString() {
        return "\n\tID:"+id+
                "\n\tNAME:"+name+
                "\n\tCLASS:"+class_name+
                "\n\tPOWER:"+power+
                "\n\tDURABILITY:"+ durability;
    }
}