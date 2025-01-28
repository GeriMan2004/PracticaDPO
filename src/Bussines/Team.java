package src.Bussines;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Team {
    @Expose
    private List<Character> members;
    @Expose
    private String name;
    private int games_played;
    private int games_won;
    private int KO_done;
    private int KO_received;
    private boolean combatDone;
    @Expose
    private String Strategy;

    /**
     * @title Constructor de la clase Team
     * @param name
     * @param games_played
     * @param games_won
     * @param KO_done
     * @param KO_received
     * @param combatDone
     */
    public Team(String name, int games_played, int games_won, int KO_done, int KO_received, boolean combatDone) {
        this.name = name;
        this.games_played = games_played;
        this.games_won = games_won;
        this.KO_done = KO_done;
        this.KO_received = KO_received;
        this.combatDone = combatDone;
    }

    //revisar si se necesita
    public String getStrategy() {
        return Strategy;
    }

    /**
     * @title Metodo para obtener los miembros del equipo
     * @return List<Character>
     */
    public List<Character> getMembers() {
        return members;
    }

    /**
     * @title Metodo para obtener el nombre del equipo
     * @return String
     */
    public String getName() {return name;}

    /**
     * @title Metodo para establecer los miembros del equipo
     * @param members
     */
    public void setMembers(List<Character> members) {
        this.members = members;
    }

    /**
     * @title Metodo para establecer el nombre del equipo
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @title Metodo para establecer los juegos jugados
     * @param games_played
     */
    public void setGames_played(int games_played) {
        this.games_played = games_played;
    }

    /**
     * @title Metodo para establecer los juegos ganados
     * @param games_won
     */
    public void setGames_won(int games_won) {
        this.games_won = games_won;
    }

    /**
     * @title Metodo para establecer los KO hechos
     * @param KO_done
     */
    public void setKO_done(int KO_done) {
        this.KO_done = KO_done;
    }

    /**
     * @title Metodo para establecer los KO recibidos
     * @param KO_received
     */
    public void setKO_received(int KO_received) {
        this.KO_received = KO_received;
    }

    //revisar si se necesita
    public void setCombatDone(boolean combatDone) {
        this.combatDone = combatDone;
    }

    //revisar si se necesita
    public void setStrategy(String strategy) {
        Strategy = strategy;
    }

    /**
     * @title Metodo para obtener los juegos jugados
     * @return int
     */
    public int getGames_played() {
        return games_played;
    }

    /**
     * @title Metodo para obtener los juegos ganados
     * @return int
     */
    public int getGames_won() {
        return games_won;
    }

    /**
     * @title Metodo para obtener los KO hechos
     * @return int
     */
    public int getKO_done() {
        return KO_done;
    }

    /**
     * @title Metodo para obtener los KO recibidos
     * @return int
     */
    public int getKO_received() {
        return KO_received;
    }

    //revisar si se necesita
    public boolean isCombatDone() {
        return combatDone;
    }

    //revisar si se necesita
    public boolean allMembersKnockedOut() {
        boolean allKO = true;
        for (Character character : members) {
            if (!character.isKnockedOut()) {
                allKO = false;
            }
        }
        return allKO;
    }
}