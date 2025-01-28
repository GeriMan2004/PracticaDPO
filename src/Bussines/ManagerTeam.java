package src.Bussines;

import src.Persistence.CharactersJsonDao;
import src.Persistence.StatsJsonDao;
import src.Persistence.TeamsJsonDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManagerTeam {

    TeamsJsonDao teamsJsonDao;
    StatsJsonDao statsJsonDao;

    /**
     * @title Constructor de la clase ManagerTeam
     * @param teamsJsonDao
     * @param statsJsonDao
     */
    public ManagerTeam(TeamsJsonDao teamsJsonDao, StatsJsonDao statsJsonDao) {
        this.teamsJsonDao = teamsJsonDao;
        this.statsJsonDao = statsJsonDao;
    }
    /**
     * @title Metodo para obtener los equipos
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
     * @title Metodo para encontrar y vincular un peronsaje a un equipo
     * @param character
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
     * @title Metodo para añadir un equipo
     * @param team
     * @throws IOException
     */
    public void addTeam(Team team) throws IOException {
        TeamsJsonDao teamsJsonDao = new TeamsJsonDao();
        List<Team> teamsupdated = teamsJsonDao.readTeams();
        teamsupdated.add(team);
        teamsJsonDao.writeTeams(teamsupdated);
    }

    /**
     * @title Metodo para añadir equipos
     * @param teams
     * @throws IOException
     */
    public void addTeams(List<Team> teams) throws IOException {
        TeamsJsonDao teamsJsonDao = new TeamsJsonDao();
        teamsJsonDao.writeTeams(teams);
    }

    /**
     * @title Metodo para actualizar las estadísticas
     * de un equipo
     * @param teams
     * @param stats
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
     * @title Metodo para actualizar los miembros de un equipo
     * @param team
     * @param members
     * @return Team
     */
    public Team updateMembers(Team team, List<Character> members) {
        team.setMembers(members);
        return team;
    }

    /**
     * @title Metodo para verificar si un personaje existe
     * @param newID
     * @param inputCharacter
     * @return boolean
     */
    public boolean existCharacter(long newID, String inputCharacter) {
        CharactersJsonDao characterJson = new CharactersJsonDao();
        List<Character> characters = characterJson.readCharacters();
        for (Character character : characters) {
            String current_character = character.getName();
            if (character.getId() == newID || current_character.equals(inputCharacter)) {
                return true;
            }
        }
        return false;
    }
}
