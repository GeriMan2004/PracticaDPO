package src.Persistence.Stats;

import edu.salle.url.api.exception.ApiException;
import src.Bussines.Team;
import java.util.List;

public interface StatsDao {
    List<Team> readStats() throws ApiException;
}
