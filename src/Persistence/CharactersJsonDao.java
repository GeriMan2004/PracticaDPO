package src.Persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import src.Bussines.Character;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CharactersJsonDao {

    private static String path = "data/characters.json";
    private static Gson gson = new Gson(); // Make gson static

    /**
     * Función que lee todos los personajes del fichero 'characters.json'
     * @autor: Gerard Perez
     * @autor: Walter-Arnau Quintili
     * @return retorna la lista de personajes
     */
    public static List<Character> readCharacters() {

        List<Character> characters = new ArrayList<>();

        try (FileReader reader = new FileReader(path)) {
            Type characterListType = new TypeToken<ArrayList<Character>>(){}.getType();
            characters = gson.fromJson(reader, characterListType);
        } catch (IOException e) {
            System.out.println("Error: The characters.json file can’t be accessed.\n");
            System.out.println("Shutting down.\n");
        }

        return characters;
    }

    public boolean checkCharactersFile() {
        try (FileReader reader = new FileReader(path)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}