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
    private Item Weapon;
    private Item armour;

    /**
     * @title Constructor para establecer los parametros necesarios del personaje
     * @param id del personaje a estableces los parametros
     * @param strategia del perosnaje a establecer los parametros
     */
    public Character(long id, String strategy) {
        this.id = id;
        this.strategy = strategy;
        this.damage_received = 0;
        this.knockedOut = false;
        this.Weapon = null;
        this.armour = null;
    }

    /**
     * @title Este metodo se encarga de obtener el id del personaje
     * @return devuelve el id del personaje
     */
    public long getId() {return id;}

    /**
     * @title este metodo se encarga de establecer el id del personaje
     * @param id a establecer
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @title Este metodo se encarga de obtener el nombre del personaje
     * @return devuelve el nombre del personaje
     */
    public String getName() {
        return name;
    }

    /**
     * @title Este metodo se encarga de obtener la estrategia del personaje
     * @return devuelve la estrategia que sigue el personaje
     */
    public String getStrategy() {
        return strategy;
    }

    /**
     * @title Este metodo se encarga de establecer la estrategia del personaje
     * @param estrategia a establecer
     */
    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    /**
     * @title Este metodo se encarga de establecer el nombre del personaje
     * @param nombre del personaje a establecer
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @title Este metodo se encarga de obtener el peso del personaje
     * @return devuelve el peso del personaje
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @title Este metodo se encarga de establecer el peso del personaje
     * @param arma a establecer al personaje
     */
    public void setWeapon(Item weapon) {
        Weapon = weapon;
    }

    /**
     * @title Este metodo se encarga de establecer la armadura del personaje
     * @param armadura a establecer al personaje
     */
    public void setArmour(Item armour) {
        this.armour = armour;
    }
    /**
     * @title Este metodo se encarga de obtener el arma del personaje
     * @return devuelve el arma del personaje
     */
    public Item getWeapon() {
        return Weapon;
    }

    /**
     * @title Este metodo se encarga de obtener la armadura del personaje
     * @return devuelve la armadura del personaje
     */
    public Item getArmour() {
        return armour;
    }

    /**
     * @title Este metodo se encarga de establecer el peso del personaje
     * @param peso a establecer del personaje
     */
    public void weight (int weight) {
        this.weight = weight;
    }
    /**
     * @title Este metodo se encarga de controlar si el personaje esta knockeado
     * @return devuelve si el personaje esta knockeado o no
     */
    public boolean isKnockedOut() {
        return knockedOut;
    }

    /**
     * @title Este metodo se encarga de establecer si el personaje esta knockeado
     * @param booleano que indica si el personaje esta knockeado o no
     */
    public void setKnockedOut(boolean knockedOut) {
        this.knockedOut = knockedOut;
    }

    /**
     * @title Este metodo se encarga de obtener el daño recibido por el personaje
     * @return devuelve el daño recibido por el personaje
     */
    public double getDamage_received() {
        return damage_received;
    }
}