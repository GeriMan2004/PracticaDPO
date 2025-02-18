package src.Persistence.Stats;

import edu.salle.url.api.exception.ApiException;
import src.Bussines.Stats;
import src.Bussines.Team;
import java.util.List;

public interface StatsDao {
    List<Team> readStats() throws ApiException;
    void updateStats(Stats team1, Stats team2) throws ApiException;
    boolean checkAvailable();
}
