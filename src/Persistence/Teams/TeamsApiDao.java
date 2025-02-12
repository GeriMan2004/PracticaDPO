package src.Persistence.Teams;

import com.google.gson.Gson;
import edu.salle.url.api.ApiHelper;

public class TeamsApiDao {
    private final static String url = "https://balandrau.salle.url.edu/dpoo/teams";
    private final ApiHelper apiHelper = null;
    private static final Gson gson = new Gson();
    private boolean status = false;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
