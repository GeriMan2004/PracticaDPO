package src.Persistence.Stats;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.salle.url.api.ApiHelper;
import edu.salle.url.api.exception.ApiException;
import src.Bussines.Stats;
import src.Bussines.Team;

import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StatsApiDao implements StatsDao{
    private final static String url = "https://balandrau.salle.url.edu/dpoo/S1-Project_15/stats";
    private static final Gson gson = new Gson();
    private boolean status = false;
    private final ApiHelper apiHelper;

    public StatsApiDao() throws ApiException {
        this.apiHelper = new ApiHelper();
    }

    /**
     * Función que lee todos los personajes de la API con url '<a href="https://balandrau.salle.url.edu/dpoo/characters">...</a>'
     * En caso de que no se pueda acceder a la API, se lanzará una excepción, lo que significará que no se puede acceder a la API
     * Por lo qye en caso de error, se utilizará el fichero 'characters.json' para obtener los personajes
     * @return lista de personajes
     */
    public List<Team> readStats() throws ApiException {
        List<Team> teams;
        // Primero nos conectamos a la API y obtenemos los datos a través del ApiHelper, el cual nos devuelve un String con formato JSON
        String jsonFormat = apiHelper.getFromUrl(url);
        // A continuación, convertimos el String JSON a una lista de objetos de tipo Character
        Type itemListType = new TypeToken<ArrayList<Team>>(){}.getType();
        teams = gson.fromJson(jsonFormat, itemListType);
        return teams;
    }

    /**
     * Función que actualiza las estadísticas de los equipos en la API
     *
     * @param team1 estadísticas del equipo 1
     * @param team2 estadísticas del equipo 2
     */
    public void updateStats(Stats team1, Stats team2) throws ApiException {
        List<Team> teams = readStats();
        List<Stats> stats = new ArrayList<>(teams.size());

        for (Team team : teams) {
            if (team.getName().equals(team1.getTeam_name())) {
                new Stats(team1.getTeam_name(), team1.getGames_won(),
                        team1.getGames_played(), team1.getKO_done(), team1.getKO_received());
            } else if (team.getName().equals(team2.getTeam_name())) {
                new Stats(team2.getTeam_name(), team2.getGames_won(),
                        team2.getGames_played(), team2.getKO_done(), team2.getKO_received());
            }
        }

        deleteTeams();

        try {
            apiHelper.postToUrl(url, gson.toJson(stats));
        } catch (ApiException e) {
            // Logic to handle the error
        }

    }

    /**
     * Función que elimina los datos de la API
     */
    private void deleteTeams() {
        try {
            apiHelper.deleteFromUrl(url);
        } catch (ApiException e) {
            // Logic to handle the error
        }
    }

    /**
     * Función que verifica si la API está disponible
     * @return boolean
     */
    public boolean checkAvailable() {
        try {
            apiHelper.getFromUrl(url);
            status = true;
        } catch (ApiException e) {
            status = false;
        }
        return status;
    }

    public boolean isStatus() {
        return status;
    }
}
