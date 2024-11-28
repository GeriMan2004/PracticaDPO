package src.Bussines;

public class Item {
    private long id_object;
    private String name;
    private String object_type;
    private int powerValue;
    private int durability;
    private boolean isBroken;

    // Constructor
    public Item(long id_object, String name, String object_type, int powerValue, int durability, boolean isBroken) {
        this.id_object = id_object;
        this.name = name;
        this.object_type = object_type;
        this.powerValue = powerValue;
        this.durability = durability;
        this.isBroken = isBroken;
    }

    // Getters and setters
    public long getId_object() {
        return id_object;
    }

    public void setId_object(long id_object) {
        this.id_object = id_object;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObject_type() {
        return object_type;
    }

    public void setObject_type(String object_type) {
        this.object_type = object_type;
    }

    public int getPowerValue() {
        return powerValue;
    }

    public void setPowerValue(int powerValue) {
        this.powerValue = powerValue;
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

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id_object +
                ", \"name\": \"" + name + '\"' +
                ", \"power\": " + powerValue +
                ", \"durability\": " + durability +
                ", \"class\": \"" + object_type + '\"' +
                '}';
    }
}