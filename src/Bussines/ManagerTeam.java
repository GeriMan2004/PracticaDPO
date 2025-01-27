package src.Bussines;

import src.Persistence.StatsJsonDao;
import src.Persistence.TeamsJsonDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManagerTeam {
    TeamsJsonDao teamsJsonDao;
    StatsJsonDao statsJsonDao;

    public ManagerTeam(TeamsJsonDao teamsJsonDao, StatsJsonDao statsJsonDao) {
        this.teamsJsonDao = teamsJsonDao;
        this.statsJsonDao = statsJsonDao;
    }

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

    public void addTeam(Team team) throws IOException {
        TeamsJsonDao teamsJsonDao = new TeamsJsonDao();
        List<Team> teamsupdated = teamsJsonDao.readTeams();
        teamsupdated.add(team);
        teamsJsonDao.writeTeams(teamsupdated);
    }

    public void addTeams(List<Team> teams) throws IOException {
        TeamsJsonDao teamsJsonDao = new TeamsJsonDao();
        teamsJsonDao.writeTeams(teams);
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

    public Team updateMembers(Team team, List<Character> members) {
        team.setMembers(members);
        return team;
    }

    public boolean existCharacter(long newID, String s) {
        TeamsJsonDao teamsJsonDao = new TeamsJsonDao();
        List<Team> teams = teamsJsonDao.readTeams();
        for (Team team : teams) {
            for (Character character : team.getMembers()) {
                if (character.getId() == newID || character.getName().equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }
}
