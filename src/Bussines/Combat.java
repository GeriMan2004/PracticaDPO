package src.Bussines;

public class Combat {
    private Team team1;
    private Team team2;
    private String winner;
    private int rounds;
    private boolean finished;

    /**
     * @title Este constructor se encarga de estableces todos los valores necesarios
     * para que el combate pueda llevarse a cabo
     *
     * @param 1 equipo del combate
     * @param 2 equipo del combate
     */
    public Combat(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.rounds = 1;
        this.finished = false;
    }

    /**
     * @title El objetivo principal de este metodo es obtener el numero de la ronda en la que se encuentra el combate
     * @return devuelve el numero de la ronda en la que se encuentra el combate
     */
    public int getRounds() {
        return rounds;
    }

    /**
     * @title Este metodo incrementa el numero de la ronda en la que se encuentra el combate
     */
    public void setRounds(int rounds) {this.rounds = rounds;}

    /**
     * @title El principal objetivo es obtener el equipo 1
     * @return devuelve el primer equipo que se encuentra en combate
     */
    public Team getTeam1() {return team1;}

    /**
     * @title El principal objetivo es obtener el equipo 2
     * @return devuelve el segundo equipo que se encuentra en combate
     */
    public Team getTeam2() {return team2;}

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }

    /**
     * @title El principal objetivo es establecer el equipo 1 del combate
     */
    public void setTeam1(Team team1) {this.team1 = team1;}

    /**
     * @title El principal objetivo es establecer el equipo 2 del combate
     */
    public void setTeam2(Team team2) {this.team2 = team2;}

    /**
     * @title El principal objetivo es establecer si el combate ha finalizado
     */
    public void setFinished(boolean finished) {this.finished = finished;}

    /**
     * @title El principal objetivo es obtener si el combate ha finalizado
     * @return devuelve si el combate ha finalizado
     */
    public boolean isFinished() {return finished;}
}
