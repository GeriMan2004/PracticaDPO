package src.Bussines;

public class Character {
    private long id;
    private String name;
    private int weight;
    private double damage_received;
    private boolean knockedOut;
    private boolean attacking;
    private boolean defending;

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int weight() {
        return weight;
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