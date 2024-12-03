package src.Bussines;

import src.Persistence.ObjectsJsonDao;

import java.util.List;

public class ManagerObject {

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
