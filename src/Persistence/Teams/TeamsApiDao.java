package src.Persistence.Teams;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.salle.url.api.ApiHelper;
import edu.salle.url.api.exception.ApiException;
import src.Bussines.Item;
import src.Bussines.Team;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TeamsApiDao implements TeamsDao{
    private final static String url = "https://balandrau.salle.url.edu/dpoo/S1-Project_15/teams";
    private static final Gson gson = new Gson();
    private boolean status = false;
    private final ApiHelper apiHelper;

    public TeamsApiDao() throws ApiException {
        this.apiHelper = new ApiHelper();
    }

    /**
     * Función que lee todos los personajes de la API con url '<a href="https://balandrau.salle.url.edu/dpoo/characters">...</a>'
     * En caso de que no se pueda acceder a la API, se lanzará una excepción, lo que significará que no se puede acceder a la API
     * Por lo qye en caso de error, se utilizará el fichero 'characters.json' para obtener los personajes
     * @return lista de personajes
     */
    public List<Team> readTeams() throws ApiException {
        List<Team> teams;
        // Primero nos conectamos a la API y obtenemos los datos a través del ApiHelper, el cual nos devuelve un String con formato JSON
        String jsonFormat = apiHelper.getFromUrl(url);
        // A continuación, convertimos el String JSON a una lista de objetos de tipo Character
        Type itemListType = new TypeToken<ArrayList<Team>>(){}.getType();
        teams = gson.fromJson(jsonFormat, itemListType);
        return teams;
    }

    /**
     * Esta función sobreescribe los equipos de la API, en caso de que se pueda acceder a ella
     * Primero borra todos los datos y luego los vuelve a escribir
     * @param teams es la lista de equipos a escribir en el fichero
     */
    public void writeTeams (List<Team> teams) {
        deleteTeams();
        try {
            apiHelper.postToUrl(url, gson.toJson(teams));
        } catch (ApiException e) {
            // Logic to handle the error
        }
    }

    /**
     * Esta función borra todos los equipos de la API, en caso de que se pueda acceder a ella
     */
    private void deleteTeams() {
        try {
            apiHelper.deleteFromUrl(url);
        } catch (ApiException e) {
            System.out.println("Error: The teams couldn't be deleted from the API.\n");
            System.out.println("Shutting down.\n");
        }
    }

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
