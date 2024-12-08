package src.Bussines;

public class Combat {
    private Team team1;
    private Team team2;

    public Combat(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }
}
