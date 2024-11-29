package src.Bussines;

import java.util.List;

public class Team {

    private String name;
    private int games_played;
    private int games_won;
    private int KO_done;
    private int KO_received;
    private boolean combatDone;
    private List<Character> members;
    private String Strategy;


    public Team(String name, int games_played, int games_won, int KO_done, int KO_received, boolean combatDone) {
        this.name = name;
        this.games_played = games_played;
        this.games_won = games_won;
        this.KO_done = KO_done;
        this.KO_received = KO_received;
        this.combatDone = combatDone;
    }


    public String getStrategy() {
        return Strategy;
    }

    public List<Character> getMembers() {
        return members;
    }

    public String getName() {return name;}

    public void setMembers(List<Character> members) {
        this.members = members;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGames_played(int games_played) {
        this.games_played = games_played;
    }

    public void setGames_won(int games_won) {
        this.games_won = games_won;
    }

    public void setKO_done(int KO_done) {
        this.KO_done = KO_done;
    }

    public void setKO_received(int KO_received) {
        this.KO_received = KO_received;
    }

    public void setCombatDone(boolean combatDone) {
        this.combatDone = combatDone;
    }

    public void setStrategy(String strategy) {
        Strategy = strategy;
    }

    public int getGames_played() {
        return games_played;
    }

    public int getGames_won() {
        return games_won;
    }

    public int getKO_done() {
        return KO_done;
    }

    public int getKO_received() {
        return KO_received;
    }

    public boolean isCombatDone() {
        return combatDone;
    }
}