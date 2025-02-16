package src.Bussines;

import edu.salle.url.api.exception.ApiException;
import src.Persistence.Characters.CharactersApiDao;
import src.Persistence.Characters.CharactersDao;
import src.Persistence.Characters.CharactersJsonDao;
import src.Persistence.Objects.ObjectsApiDao;
import src.Persistence.Objects.ObjectsDao;
import src.Persistence.Objects.ObjectsJsonDao;
import java.util.List;

/**
 * Esta clase se encarga de gestionar los objetos del juego
 * {@link Item}
 */
public class ManagerObject {

    ObjectsDao objectsDao;

    /**
     * Constructor de la clase ManagerObject
     * @param objectsJsonDao es el objeto que se encarga de la persistencia de los objetos
     */
    public ManagerObject(ObjectsJsonDao objectsJsonDao, ObjectsApiDao objectsApiDao) {
        if (objectsApiDao.checkAvailable()){
            this.objectsDao = objectsApiDao;
        } else if (objectsJsonDao.checkAvailable()) {
            this.objectsDao = objectsJsonDao;
        }
    }

   /**
     * Metodo para verificar si el archivo de objetos existe
     * @return int; significando 1 que
     */
    public int checkItemFile() throws ApiException {
        ObjectsApiDao objectsApiDao = new ObjectsApiDao();
        if (objectsApiDao.checkAvailable()) {
            return 1;
        }
        ObjectsJsonDao objectsJsonDao = new ObjectsJsonDao();
        if (objectsJsonDao.checkAvailable()) {
            return 2;
        }
        return 0;
    }

    /**
     * Metodo para cargar los objetos
     * @return List<Item>
     */
    public List<Item> uploadObjects() throws ApiException {
        return objectsDao.readObjects();
    }
}