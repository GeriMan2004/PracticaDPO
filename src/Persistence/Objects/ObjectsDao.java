package src.Persistence.Objects;

import edu.salle.url.api.exception.ApiException;
import src.Bussines.Item;
import java.util.List;

public interface ObjectsDao {
    List<Item> readObjects() throws ApiException;

}
