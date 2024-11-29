package src.Bussines;

import src.Persistence.ObjectsJsonDao;

public class ManagerObject {

    public boolean checkItemFile()
    {
        ObjectsJsonDao objectsJsonDao = new ObjectsJsonDao();
        return objectsJsonDao.checkObjectsFile();
    }


}
