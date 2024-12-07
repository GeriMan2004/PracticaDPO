package src.Persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import src.Bussines.Team;
import src.Presentation.UI;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class TeamsJsonDao {

    private String path = "data/teams.json";
    private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

    public List<Team> readTeams() {
        try (FileReader reader = new FileReader(path)) {
            Type teamListType = new TypeToken<List<Team>>() {}.getType();
            List<Team> teams = gson.fromJson(reader, teamListType);
            return teams;
        } catch (IOException e) {
            UI.displayMessage("Error: The teams.json file can’t be accessed.\n");
            UI.displayMessage("Shutting down.\n");
            return null;
        }
    }

    public void writeTeams(List<Team> teams) throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(teams, writer);
        }
    }
}