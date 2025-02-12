package src.Bussines;

import src.Persistence.Characters.CharactersJsonDao;
import src.Persistence.Stats.StatsJsonDao;
import src.Persistence.Teams.TeamsJsonDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase se encarga de gestionar los equipos del juego
 * {@link Team}
 */
public class ManagerTeam {

    TeamsJsonDao teamsJsonDao;
    StatsJsonDao statsJsonDao;

    /**
     * Constructor de la clase ManagerTeam
     * @param teamsJsonDao es el objeto que se encarga de la persistencia de los equipos
     * @param statsJsonDao es el objeto que se encarga de la persistencia de las estadísticas
     */
    public ManagerTeam(TeamsJsonDao teamsJsonDao, StatsJsonDao statsJsonDao) {
        this.teamsJsonDao = teamsJsonDao;
        this.statsJsonDao = statsJsonDao;
    }

    /**
     * Metodo para obtener los equipos
     * @return List<Team>
     */
    public List<Team> getAllTeams()
    {
        TeamsJsonDao teamsJsonDao = new TeamsJsonDao();
        StatsJsonDao statsJsonDao = new StatsJsonDao();
        List<Team> teams = teamsJsonDao.readTeams();
        List<Team> stats = statsJsonDao.readStats();
        matchStats(teams, stats);

        return teams;
    }

    /**
     * Metodo para encontrar y vincular un peronsaje a un equipo
     * @param character es el personaje a vincular
     * @return List<Team>
     */
    public List<Team> matchTeams(Character character){
        TeamsJsonDao teamsJsonDao = new TeamsJsonDao();
        List<Team> teams = teamsJsonDao.readTeams();
        List<Team> matchedTeams = new ArrayList<>();
        for (Team team : teams) {
            for (Character characters : team.getMembers()) {
                if (characters.getId() == character.getId()) {
                    matchedTeams.add(team);
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
        TeamsJsonDao teamsJsonDao = new TeamsJsonDao();
        List<Team> teamsupdated = teamsJsonDao.readTeams();
        teamsupdated.add(team);
        teamsJsonDao.writeTeams(teamsupdated);
    }

    /**
     * Metodo para añadir equipos
     * @param teams es la lista de equipos a añadir
     * @throws IOException
     */
    public void addTeams(List<Team> teams) throws IOException {
        TeamsJsonDao teamsJsonDao = new TeamsJsonDao();
        teamsJsonDao.writeTeams(teams);
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

    /**
     * Metodo para verificar si un personaje existe
     * @param newID es el nuevo ID a verificar
     * @param inputCharacter es el personaje a verificar
     * @return boolean
     */
    public boolean existCharacter(long newID, String inputCharacter) {
        List<Character> characters = CharactersJsonDao.readCharacters();
        for (Character character : characters) {
            String current_character = character.getName();
            if (character.getId() == newID || current_character.equals(inputCharacter)) {
                return true;
            }
        }
        return false;
    }

    public boolean existTeam(String teamName) {
        List<Team> teams = TeamsJsonDao.readTeams();
        assert teams != null;
        for (Team team : teams) {
            if (team.getName().equals(teamName)) {
                return true;
            }
        }
        return false;
    }
}
