package src.Bussines;

import com.google.gson.annotations.Expose;

import java.util.List;
/**
 * Esta clase se encarga de las instancias de equipos en el juego, con
 * sus respectivos miembros, estadisticas y otros atributos utiles en el combate
 */
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
     * Constructor de la clase Team
     * @param name es el nombre del equipo
     * @param games_played es la cantidad de juegos jugados
     * @param games_won es la cantidad de juegos ganados
     * @param KO_done es la cantidad de KO hechos
     * @param KO_received es la cantidad de KO recibidos
     * @param combatDone es un booleano que indica si el combate ha sido completado
     */
    public Team(String name, int games_played, int games_won, int KO_done, int KO_received, boolean combatDone) {
        this.name = name;
        this.games_played = games_played;
        this.games_won = games_won;
        this.KO_done = KO_done;
        this.KO_received = KO_received;
        this.combatDone = combatDone;
    }

    /**
     * Metodo para obtener los miembros del equipo
     * @return List<Character>
     */
    public List<Character> getMembers() {
        return members;
    }

    /**
     * Metodo para obtener el nombre del equipo
     * @return String
     */
    public String getName() {return name;}

    /**
     * Metodo para establecer los miembros del equipo
     * @param members es la lista de personajes que conforman el equipo
     */
    public void setMembers(List<Character> members) {
        this.members = members;
    }

    /**
     * Metodo para establecer el nombre del equipo
     * @param name es el nombre del equipo
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Metodo para establecer los juegos jugados
     * @param games_played es la cantidad de juegos jugados
     */
    public void setGames_played(int games_played) {
        this.games_played = games_played;
    }

    /**
     * Metodo para establecer los juegos ganados
     * @param games_won es la cantidad de juegos ganados
     */
    public void setGames_won(int games_won) {
        this.games_won = games_won;
    }

    /**
     * Metodo para establecer los KO hechos
     * @param KO_done es la cantidad de KO hechos
     */
    public void setKO_done(int KO_done) {
        this.KO_done = KO_done;
    }

    /**
     * Metodo para establecer los KO recibidos
     * @param KO_received es la cantidad de KO recibidos
     */
    public void setKO_received(int KO_received) {
        this.KO_received = KO_received;
    }

    /**
     * Metodo para obtener los juegos jugados
     * @return int
     */
    public int getGames_played() {
        return games_played;
    }

    /**
     * Metodo para obtener los juegos ganados
     * @return int
     */
    public int getGames_won() {
        return games_won;
    }

    /**
     * Metodo para obtener los KO hechos
     * @return int
     */
    public int getKO_done() {
        return KO_done;
    }

    /**
     * Metodo para obtener los KO recibidos
     * @return int
     */
    public int getKO_received() {
        return KO_received;
    }
}