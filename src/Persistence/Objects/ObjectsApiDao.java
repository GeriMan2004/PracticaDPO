package src.Persistence.Objects;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.salle.url.api.ApiHelper;
import edu.salle.url.api.exception.ApiException;
import src.Bussines.Item;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ObjectsApiDao implements ObjectsDao{
    private final static String url = "https://balandrau.salle.url.edu/dpoo/items";
    private static final Gson gson = new Gson();
    private boolean status = false;
    private final ApiHelper apiHelper;

    public ObjectsApiDao() throws ApiException {
        this.apiHelper = new ApiHelper();
    }



    /**
     * Función que lee todos los personajes de la API con url '<a href="https://balandrau.salle.url.edu/dpoo/characters">...</a>'
     * En caso de que no se pueda acceder a la API, se lanzará una excepción, lo que significará que no se puede acceder a la API
     * Por lo qye en caso de error, se utilizará el fichero 'characters.json' para obtener los personajes
     * @return lista de personajes
     */
    public List<Item> readObjects() throws ApiException {
        List<Item> items;
        // Primero nos conectamos a la API y obtenemos los datos a través del ApiHelper, el cual nos devuelve un String con formato JSON
        String jsonFormat = apiHelper.getFromUrl(url);
        // A continuación, convertimos el String JSON a una lista de objetos de tipo Character
        Type itemListType = new TypeToken<ArrayList<Item>>(){}.getType();
        items = gson.fromJson(jsonFormat, itemListType);
        return items;
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
