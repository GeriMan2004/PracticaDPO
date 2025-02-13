package src.Persistence.Characters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.salle.url.api.ApiHelper;
import edu.salle.url.api.exception.ApiException;
import src.Bussines.Character;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CharactersApiDao implements CharactersDao {
    private final static String url = "https://balandrau.salle.url.edu/dpoo/shared/characters";
    private static final Gson gson = new Gson();
    private boolean status = false;
    private final ApiHelper apiHelper;

    public CharactersApiDao() throws ApiException {
        this.apiHelper = new ApiHelper();
    }



    /**
     * Función que lee todos los personajes de la API con url '<a href="https://balandrau.salle.url.edu/dpoo/characters">...</a>'
     * En caso de que no se pueda acceder a la API, se lanzará una excepción, lo que significará que no se puede acceder a la API
     * Por lo qye en caso de error, se utilizará el fichero 'characters.json' para obtener los personajes
     * @return lista de personajes
     */
    public List<Character> readCharacters() throws ApiException {
        List<Character> characters;
        // Primero nos conectamos a la API y obtenemos los datos a través del ApiHelper, el cual nos devuelve un String con formato JSON
        String jsonFormat = apiHelper.getFromUrl(url);
        // A continuación, convertimos el String JSON a una lista de objetos de tipo Character
        Type characterListType = new TypeToken<ArrayList<Character>>(){}.getType();
        characters = gson.fromJson(jsonFormat, characterListType);
        return characters;
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
