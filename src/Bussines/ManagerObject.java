package src.Bussines;

import src.Persistence.ObjectsJsonDao;

import java.util.List;

public class ManagerObject {

    ObjectsJsonDao objectsJsonDao;

    /**
     * @title Constructor de la clase ManagerObject
     * @param objectsJsonDao
     */
    public ManagerObject(ObjectsJsonDao objectsJsonDao) {
        this.objectsJsonDao = objectsJsonDao;
    }

   /**
     * @title Metodo para verificar si el archivo de objetos existe
     * @return boolean
     */
    public boolean checkItemFile()
    {
        ObjectsJsonDao objectsJsonDao = new ObjectsJsonDao();
        return objectsJsonDao.checkObjectsFile();
    }
    /**
     * @title Metodo para cargar los objetos
     * @return List<Item>
     */
    public static List<Item> uploadObjects() {

        ObjectsJsonDao ObjectsJsonDao = new ObjectsJsonDao();
        List<Item> objects = ObjectsJsonDao.readObjects();

        return objects;
    }
}
