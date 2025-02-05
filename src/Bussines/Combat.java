package src.Bussines;

public class Combat {
    private Team team1;
    private Team team2;
    private String winner;
    private int rounds;
    private boolean finished;

    /**
     * Este constructor se encarga de estableces todos los valores necesarios
     * para que el combate pueda llevarse a cabo
     *
     * @param team1 tiene la informacion del primer equipo
     * @param team2 tiene la informacion del segundo equipo
     */
    public Combat(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.rounds = 1;
        this.finished = false;
    }

    /**
     * El objetivo principal de este metodo es obtener el numero de la ronda en la que se encuentra el combate
     * @return devuelve el numero de la ronda en la que se encuentra el combate
     */
    public int getRounds() {
        return rounds;
    }

    /**
     * Este metodo incrementa el numero de la ronda en la que se encuentra el combate
     */
    public void setRounds(int rounds) {this.rounds = rounds;}

    /**
     * El principal objetivo es obtener el equipo 1
     * @return devuelve el primer equipo que se encuentra en combate
     */
    public Team getTeam1() {return team1;}

    /**
     * El principal objetivo es obtener el equipo 2
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
     * El principal objetivo es establecer el equipo 1 del combate
     */
    public void setTeam1(Team team1) {this.team1 = team1;}

    /**
     * El principal objetivo es establecer el equipo 2 del combate
     */
    public void setTeam2(Team team2) {this.team2 = team2;}

    /**
     *  El principal objetivo es establecer si el combate ha finalizado
     */
    public void setFinished(boolean finished) {this.finished = finished;}

    /**
     * El principal objetivo es obtener si el combate ha finalizado
     * @return devuelve si el combate ha finalizado
     */
    public boolean isFinished() {return finished;}
}
