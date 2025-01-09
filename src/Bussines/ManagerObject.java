package src.Bussines;

import src.Persistence.ObjectsJsonDao;

import java.util.List;

public class ManagerObject {
    ObjectsJsonDao objectsJsonDao;

    public ManagerObject(ObjectsJsonDao objectsJsonDao) {
        this.objectsJsonDao = objectsJsonDao;
    }

    public boolean checkItemFile()
    {
        ObjectsJsonDao objectsJsonDao = new ObjectsJsonDao();
        return objectsJsonDao.checkObjectsFile();
    }

    public static List<Item> uploadObjects() {

        ObjectsJsonDao ObjectsJsonDao = new ObjectsJsonDao();
        List<Item> objects = ObjectsJsonDao.readObjects();

        return objects;
    }
}
