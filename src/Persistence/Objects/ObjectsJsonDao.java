package src.Persistence.Objects;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import src.Bussines.Item;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que se encarga de leer los objetos del fichero 'items.json'
 */
public class ObjectsJsonDao implements ObjectsDao {

    private final String path = "data/items.json";
    private final Gson gson = new Gson();

    /**
     * Función que lee todos los objetos del fichero 'items.json'
     * @return lista de objetos
     */
    public List<Item> readObjects() {

        List<Item> items = new ArrayList<>();

        try (FileReader reader = new FileReader(path)) {
            Type itemListType = new TypeToken<ArrayList<Item>>() {
            }.getType();
            items = gson.fromJson(reader, itemListType);
        } catch (IOException e) {
            // Logic to handle the error
        }

        return items;
    }

    /**
     * Función que verifica si el fichero 'items.json' existe
     * @return boolean
     */
    public boolean checkAvailable()
    {
        try (FileReader _ = new FileReader(path)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
