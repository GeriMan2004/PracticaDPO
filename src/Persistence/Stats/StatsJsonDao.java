package src.Persistence.Stats;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import src.Bussines.Stats;
import src.Bussines.Team;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Clase que se encarga de leer las estadísticas de los equipos del fichero 'stats.json'
 */
public class StatsJsonDao implements StatsDao{
    private final Gson gson = new Gson();
    private final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Función que lee las estadísticas de los equipos del fichero 'stats.json'
     * @return lista de equipos
     */
    public List<Team> readStats () {
        try {
            String path = "data/stats.json";
            return gson.fromJson(new FileReader(path), new TypeToken<List<Team>>() {}.getType());
        } catch (IOException e) {
            // Logic to handle the error and return null
            return null;
        }
    }
    public void updateStats(Stats stats1, Stats stats2) {
        String path = "data/stats.json";
        try {
            // Leer el archivo JSON
            List<Stats> statsList = gson.fromJson(new FileReader(path), new TypeToken<List<Stats>>() {}.getType());

            // Actualizar las estadísticas de los equipos
            for (Stats stats : statsList) {
                if (stats.getTeam_name().equals(stats1.getTeam_name())) {
                    stats.setGames_won(stats1.getGames_won());
                    stats.setGames_played(stats1.getGames_played());
                    stats.setKO_done(stats1.getKO_done());
                    stats.setKO_received(stats1.getKO_received());
                } else if (stats.getTeam_name().equals(stats2.getTeam_name())) {
                    stats.setGames_won(stats2.getGames_won());
                    stats.setGames_played(stats2.getGames_played());
                    stats.setKO_done(stats2.getKO_done());
                    stats.setKO_received(stats2.getKO_received());
                }
            }

            // Escribir el archivo JSON actualizado con pretty printing
            FileWriter writer = new FileWriter(path);
            prettyGson.toJson(statsList, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: The stats.json file can’t be accessed.\n");
            System.out.println("Shutting down.\n");
        }
    }
}


