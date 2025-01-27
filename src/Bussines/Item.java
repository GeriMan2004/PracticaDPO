package src.Bussines;

import com.google.gson.annotations.SerializedName;

public class Item {
    private long id;
    private String name;
    @SerializedName("class")
    private String class_name;
    private int power;
    private int durability;
    private boolean isBroken;

    /**
     * @title Constructor para establecer los parametros necesarios del objeto
     * @param id
     * @param name
     * @param class_name
     * @param power
     * @param durability
     * @param isBroken
     */
    public Item(long id, String name, String class_name, int power, int durability, boolean isBroken) {
        this.id = id;
        this.name = name;
        this.class_name = class_name;
        this.power = power;
        this.durability = durability;
        this.isBroken = isBroken;
    }

    /**
     * @title Este metodo se encarga de obtener el id del objeto
     * @return devuelve el id del objeto
     */
    public long getId_object() {
        return id;
    }
    /**
     * @title Este metodo se encarga de establecer el id del objeto
     * @param id_object a establecer
     */
    public void setId_object(long id_object) {
        this.id = id_object;
    }

    /**
     * @title Este metodo se encarga de obtener el nombre del objeto
     * @return devuelve el nombre del objeto
     */
    public String getName() {
        return name;
    }
    /**
     * @title Este metodo se encarga de establecer el nombre del objeto
     * @param name a establecer
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @title Este metodo se encarga de obtener el tipo de objeto
     * @return devuelve el tipo de objeto
     */
    public String getObject_type() {
        return class_name;
    }
    /**
     * @title Este metodo se encarga de establecer el tipo de objeto
     * @param object_type a establecer
     */
    public void setObject_type(String object_type) {
        this.class_name = object_type;
    }
    /**
     * @title Este metodo se encarga de obtener el poder del objeto
     * @return devuelve el poder del objeto
     */
    public int getPowerValue() {
        return power;
    }
    /**
     * @title Este metodo se encarga de establecer el poder del objeto
     * @param powerValue a establecer
     */
    public void setPowerValue(int powerValue) {
        this.power = powerValue;
    }
    /**
     * @title Este metodo se encarga de obtener la durabilidad del objeto
     * @return devuelve la durabilidad del objeto
     */
    public int getDurability() {
        return durability;
    }
    /**
     * @title Este metodo se encarga de establecer la durabilidad del objeto
     * @param durability a establecer
     */
    public void setDurability(int durability) {
        this.durability = durability;
    }

    /**
     * @title Este metodo se encarga de obtener si el objeto esta roto
     * @return
     */
    public boolean isBroken() {
        return isBroken;
    }
    /**
     * @title Este metodo se encarga de establecer si el objeto esta roto
     * @param broken
     */
    public void setBroken(boolean broken) {
        isBroken = broken;
    }
    /**
     * @title Este metodo se encarga de disminuir la durabilidad del objeto
     */
    public void decreaseDurability()
    {
        durability--;
        if(durability == 0){
            isBroken = true;
        }
    }
    @Override
    public String toString() {
        return "\n\tID:"+id+
                "\n\tNAME:"+name+
                "\n\tCLASS:"+class_name+
                "\n\tPOWER:"+power+
                "\n\tDURABILITY:"+ durability;
    }

}

