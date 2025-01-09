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

    // Constructor
    public Item(long id, String name, String class_name, int power, int durability, boolean isBroken) {
        this.id = id;
        this.name = name;
        this.class_name = class_name;
        this.power = power;
        this.durability = durability;
        this.isBroken = isBroken;
    }

    // Getters and setters
    public long getId_object() {
        return id;
    }

    public void setId_object(long id_object) {
        this.id = id_object;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObject_type() {
        return class_name;
    }

    public void setObject_type(String object_type) {
        this.class_name = object_type;
    }

    public int getPowerValue() {
        return power;
    }

    public void setPowerValue(int powerValue) {
        this.power = powerValue;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public boolean isBroken() {
        return isBroken;
    }

    public void setBroken(boolean broken) {
        isBroken = broken;
    }
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

