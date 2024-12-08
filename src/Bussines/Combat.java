package src.Bussines;

public class Combat {
    private Team team1;
    private Team team2;
    private int rounds;
    private boolean finished;

    public Combat(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.rounds = 1;
        this.finished = false;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isFinished() {
        return finished;
    }
}
