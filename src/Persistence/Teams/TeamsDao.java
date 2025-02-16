package src.Persistence.Teams;

import edu.salle.url.api.exception.ApiException;
import src.Bussines.Team;
import java.util.List;

public interface TeamsDao {
    List<Team> readTeams() throws ApiException;
    void writeTeams (List<Team> teams);
}
