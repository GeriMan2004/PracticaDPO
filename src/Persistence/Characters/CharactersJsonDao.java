package src.Persistence.Characters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import src.Bussines.Character;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que se encarga de leer los personajes del fichero 'characters.json'
 */
public class CharactersJsonDao implements CharactersDao{
    private static final String path = "data/characters.json";
    private static final Gson gson = new Gson();

    /**
     * Función que lee todos los personajes del fichero 'characters.json'
     * @return lista de personajes
     */
    public List<Character> readCharacters() {

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

    /**
     * Función que verifica si el fichero 'characters.json' existe
     * @return boolean
     */
    public boolean checkAvailable() {
        try (FileReader _ = new FileReader(path)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}