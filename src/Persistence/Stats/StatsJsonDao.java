package src.Persistence.Stats;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import src.Bussines.Team;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Clase que se encarga de leer las estadísticas de los equipos del fichero 'stats.json'
 */
public class StatsJsonDao {
    private final Gson gson = new Gson();

    /**
     * Función que lee las estadísticas de los equipos del fichero 'stats.json'
     * @return lista de equipos
     */
    public List<Team> readStats () {
        try {
            String path = "data/stats.json";
            return gson.fromJson(new FileReader(path), new TypeToken<List<Team>>() {}.getType());
        } catch (IOException e) {
            System.out.println("Error: The stats.json file can’t be accessed.\n");
            System.out.println("Shutting down.\n");
            return null;
        }
    }

}
