package src.Bussines;

import src.Persistence.StatsJsonDao;
import src.Persistence.TeamsJsonDao;

import java.util.ArrayList;
import java.util.List;

public class ManagerTeam {

    public List<Team> getAllTeams()
    {
        TeamsJsonDao teamsJsonDao = new TeamsJsonDao();
        StatsJsonDao statsJsonDao = new StatsJsonDao();
        List<Team> teams = teamsJsonDao.readTeams();
        List<Team> stats = statsJsonDao.readStats();
        matchStats(teams, stats);

        return teams;
    }

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
}
