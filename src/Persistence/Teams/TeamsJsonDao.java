package src.Persistence.Teams;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import src.Bussines.Team;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Clase que se encarga de leer los equipos del fichero 'teams.json'
 */
public class TeamsJsonDao {

    private static final String path = "data/teams.json";
    private static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

    /**
     * Función que lee los equipos del fichero 'teams.json'
     * @return lista de equipos
     */
    public static List<Team> readTeams() {
        try (FileReader reader = new FileReader(path)) {
            Type teamListType = new TypeToken<List<Team>>() {}.getType();
            return gson.fromJson(reader, teamListType);
        } catch (IOException e) {
            System.out.println("Error: The teams.json file can’t be accessed.\n");
            System.out.println("Shutting down.\n");
            return null;
        }
    }

    /**
     * Esta función sobreescribe los equipos en el fichero 'teams.json'
     * @param teams es la lista de equipos a escribir en el fichero
     */
    public void writeTeams(List<Team> teams) throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(teams, writer);
        }
    }
}