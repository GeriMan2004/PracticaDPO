package src.Bussines;

import edu.salle.url.api.exception.ApiException;
import src.Persistence.Stats.StatsDao;


import java.util.List;

public class ManagerStats {

    StatsDao statsJsonDao;

    public void updateStats(Combat combat, int countKo, int teamKo) throws ApiException {
        Team team1 = combat.getTeam1();
        Team team2 = combat.getTeam2();

        if (combat.getWinner() != null) {
            if (combat.getWinner().equals(team1.getName())) {
                team1.setGames_won(team1.getGames_won() + 1);
            } else {
                team2.setGames_won(team2.getGames_won() + 1);
            }
        }

        if (teamKo == 1) {
            team1.setKO_done(team1.getKO_done() + countKo);
            team2.setKO_received(team2.getKO_received() + countKo);
        } else {
            team2.setKO_done(team2.getKO_done() + countKo);
            team1.setKO_received(team1.getKO_received() + countKo);
        }

        Stats stats1 = new Stats(team1.getName(), team1.getGames_won(), team1.getGames_played() + 1, team1.getKO_done(), team1.getKO_received());
        Stats stats2 = new Stats(team2.getName(), team2.getGames_won(), team2.getGames_played() + 1, team2.getKO_done(), team2.getKO_received());

        statsJsonDao.updateStats(stats1, stats2);
    }

}

