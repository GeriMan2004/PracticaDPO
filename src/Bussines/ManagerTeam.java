package src.Bussines;

import src.Persistence.TeamsJsonDao;

import java.util.List;

public class ManagerTeam {

    public List<Team> getAllTeams()
    {
        TeamsJsonDao teamsJsonDao = new TeamsJsonDao();
        List<Team> teams = teamsJsonDao.readTeams();

        return teams;
    }
}
