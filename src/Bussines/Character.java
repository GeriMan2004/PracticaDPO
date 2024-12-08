package src.Bussines;

import com.google.gson.annotations.Expose;

public class Character {
   @Expose
    private long id;
    @Expose
    private String strategy;

    private String name;
    private int weight;
    private double damage_received;
    private boolean knockedOut;
    private boolean attacking;
    private boolean defending;

    public Character(long id, String strategy) {
        this.id = id;
        this.strategy = strategy;
    }
    // Getters and setters
    public int getId() {
        return (int) id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeapon(Item weapon) {
        Weapon = weapon;
    }

    public void setArmour(Item armour) {
        this.armour = armour;
    }

    public Item getWeapon() {
        return Weapon;
    }

    public Item getArmour() {
        return armour;
    }

    public void weight (int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }


}