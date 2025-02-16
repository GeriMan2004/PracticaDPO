package src.Bussines;

import edu.salle.url.api.exception.ApiException;
import src.Persistence.Characters.CharactersJsonDao;
import src.Persistence.Stats.StatsApiDao;
import src.Persistence.Stats.StatsDao;
import src.Persistence.Stats.StatsJsonDao;
import src.Persistence.Teams.TeamsApiDao;
import src.Persistence.Teams.TeamsDao;
import src.Persistence.Teams.TeamsJsonDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase se encarga de gestionar los equipos del juego
 * {@link Team}
 */
public class ManagerTeam {

    TeamsDao teamsDao;
    StatsDao statsDao;

    /**
     * Constructor de la clase ManagerTeam
     * @param teamsJsonDao es el objeto que se encarga de la persistencia de los equipos
     * @param statsJsonDao es el objeto que se encarga de la persistencia de las estadísticas
     */
    public ManagerTeam(TeamsJsonDao teamsJsonDao, StatsJsonDao statsJsonDao, TeamsApiDao teamsApiDao, StatsApiDao statsApiDao) {
        if (teamsApiDao.checkAvailable()){
            this.teamsDao = teamsApiDao;
        } else {
            this.teamsDao = teamsJsonDao;
        }
        if (statsApiDao.checkAvailable()){
            this.statsDao = statsApiDao;
        } else {
            this.statsDao = statsJsonDao;
        }
    }

    /**
     * Metodo para obtener los equipos
     * @return List<Team>
     */
    public List<Team> getAllTeams() throws ApiException {
        List<Team> teams = teamsDao.readTeams();
        List<Team> stats = statsDao.readStats();
        matchStats(teams, stats);
        return teams;
    }

    /**
     * Metodo para encontrar y vincular un peronsaje a un equipo
     * @param character es el personaje a vincular
     * @return List<Team>
     */
    public List<Team> matchTeams(Character character) throws ApiException {
        List<Team> teams = teamsDao.readTeams();
        List<Team> matchedTeams = new ArrayList<>();
        if (teams != null) {
            for (Team team : teams) {
                for (Character characters : team.getMembers()) {
                    if (characters.getId() == character.getId()) {
                        matchedTeams.add(team);
                    }
                }
            }
        }
        return matchedTeams;
    }

    /**
     * Metodo para añadir un equipo
     * @param team es el equipo a añadir
     * @throws IOException
     */
    public void addTeam(Team team) throws IOException {
        List<Team> teamsupdated = teamsDao.readTeams();
        teamsupdated.add(team);
        teamsDao.writeTeams(teamsupdated);
    }

    /**
     * Metodo para añadir equipos
     * @param teams es la lista de equipos a añadir
     * @throws IOException
     */
    public void addTeams(List<Team> teams) {
        teamsDao.writeTeams(teams);
    }

    /**
     * Metodo para actualizar las estadísticas
     * de un equipo
     * @param teams son los equipos a actualizar
     * @param stats son las estadísticas a actualizar de los equipos
     */
    private void matchStats(List<Team> teams, List<Team> stats) {
        for (Team team : teams) {
            for (Team stat : stats) {
                if (team.getName().equals(stat.getName())) {
                    team.setGames_played(stat.getGames_played());
                    team.setGames_won(stat.getGames_won());
                    team.setKO_done(stat.getKO_done());
                    team.setKO_received(stat.getKO_received());
                }
            }
        }
    }

    /**
     * Metodo para actualizar los miembros de un equipo
     * @param team es el equipo a actualizar
     * @param members son los miembros a añadir
     * @return Team
     */
    public Team updateMembers(Team team, List<Character> members) {
        team.setMembers(members);
        return team;
    }



    public boolean existTeam(String teamName) throws ApiException {
        List<Team> teams = teamsDao.readTeams();
        assert teams != null;
        for (Team team : teams) {
            if (team.getName().equals(teamName)) {
                return true;
            }
        }
        return false;
    }
}
