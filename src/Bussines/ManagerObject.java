package src.Bussines;

import src.Persistence.Objects.ObjectsJsonDao;
import java.util.List;

/**
 * Esta clase se encarga de gestionar los objetos del juego
 * {@link Item}
 */
public class ManagerObject {

    ObjectsJsonDao objectsJsonDao;

    /**
     * Constructor de la clase ManagerObject
     * @param objectsJsonDao es el objeto que se encarga de la persistencia de los objetos
     */
    public ManagerObject(ObjectsJsonDao objectsJsonDao) {
        this.objectsJsonDao = objectsJsonDao;
    }

   /**
     * Metodo para verificar si el archivo de objetos existe
     * @return boolean
     */
    public boolean checkItemFile()
    {
        ObjectsJsonDao objectsJsonDao = new ObjectsJsonDao();
        return objectsJsonDao.checkObjectsFile();
    }

    /**
     * Metodo para cargar los objetos
     * @return List<Item>
     */
    public static List<Item> uploadObjects() {
        ObjectsJsonDao ObjectsJsonDao = new ObjectsJsonDao();
        return ObjectsJsonDao.readObjects();
    }
}
