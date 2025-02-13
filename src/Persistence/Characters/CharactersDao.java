package src.Persistence.Characters;

import edu.salle.url.api.exception.ApiException;
import src.Bussines.Character;

import java.util.List;

public interface CharactersDao {
    List<Character> readCharacters() throws ApiException;
    boolean checkAvailable();
}
