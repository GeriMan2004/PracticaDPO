package src.Persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import src.Bussines.Character;
import src.Bussines.Item;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ObjectsJsonDao {

    private String path = "data/items.json";
    Gson gson = new Gson();


    /**
     * Función que lee todos los objetos del fichero 'objects.json'
     * @autor: Gerard Perez
     * @autor: Walter-Arnau Quintili
     * @return retorna la lista de objetos
     */
    public List<Item> readObjects() {

        List<Item> items = new ArrayList<>();

        try (FileReader reader = new FileReader(path)) {
            Type itemListType = new TypeToken<ArrayList<Item>>() {
            }.getType();
            items = gson.fromJson(reader, itemListType);
        } catch (IOException e) {
            System.out.println("Error: The objects.json file can’t be accessed.\n");
            System.out.println("Shutting down.\n");
        }

        for(Item item : items){
            System.out.println(item);
        }

        return items;
    }

}
