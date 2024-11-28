package src.Bussines;

public class Team {

    private String name;
    private int games_played;
    private int games_won;
    private int KO_done;
    private int KO_received;
    private boolean combatDone;


    public Team(String name, int games_played, int games_won, int KO_done, int KO_received, boolean combatDone) {
        this.name = name;
        this.games_played = games_played;
        this.games_won = games_won;
        this.KO_done = KO_done;
        this.KO_received = KO_received;
        this.combatDone = combatDone;
    }

    public String getName (){
        return name;
    }

}
