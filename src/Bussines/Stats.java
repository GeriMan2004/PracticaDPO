package src.Bussines;

import com.google.gson.annotations.SerializedName;

public class Stats {
    @SerializedName("name")
    private String team_name;
    private int games_won;
    private int games_played;
    private int KO_done;
    private int KO_received;

    public Stats(String team_name, int games_won, int games_played, int KO_done, int KO_received) {
        this.team_name = team_name;
        this.games_won = games_won;
        this.games_played = games_played;
        this.KO_done = KO_done;
        this.KO_received = KO_received;
    }

    public String getTeam_name() {
        return team_name;
    }
    public void setGames_won(int games_won) {
        this.games_won = games_won;
    }
    public void setGames_played(int games_played) {
        this.games_played = games_played;
    }
    public int getGames_won() {
        return games_won;
    }
    public int getGames_played() {
        return games_played;
    }
    public int getKO_done() {
        return KO_done;
    }
    public void setKO_done(int KO_done) {
        this.KO_done = KO_done;
    }
    public int getKO_received() {
        return KO_received;
    }
    public void setKO_received(int KO_received) {
        this.KO_received = KO_received;
    }

}
