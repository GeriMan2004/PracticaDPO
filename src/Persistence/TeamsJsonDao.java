package src.Persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import src.Bussines.Team;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class TeamsJsonDao {

    private String path = "data/teams.json";
    private Gson gson = new Gson();

    public List<Team> readTeams() {

        try (FileReader reader = new FileReader(path)) {

            Type teamListType = new TypeToken<List<Team>>() {}.getType();
            return gson.fromJson(reader, teamListType);

        } catch (IOException e) {
            System.out.println("Error: The characters.json file can’t be accessed.\n");
            System.out.println("Shutting down.\n");
            return null;
        }
    }
}