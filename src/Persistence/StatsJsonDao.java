package src.Persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import src.Bussines.Team;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class StatsJsonDao {

    String path = "data/stats.json";
    Gson gson = new Gson();

    public List<Team> readStats () {
        try {
            return gson.fromJson(new FileReader(path), new TypeToken<List<Team>>() {}.getType());
        } catch (IOException e) {
            System.out.println("Error: The stats.json file can’t be accessed.\n");
            System.out.println("Shutting down.\n");
            return null;
        }
    }

}
