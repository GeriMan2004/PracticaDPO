package src.Bussines;

import src.Persistence.CharactersJsonDao;

import java.util.List;

public class ManagerCharacter {


    public List<Character> UploadCharacters()
    {
        CharactersJsonDao charactersJsonDao = new CharactersJsonDao();
        List<Character> characters = charactersJsonDao.readCharacters();
        return characters;
    }

    public boolean checkCharacterFile()
    {
        CharactersJsonDao charactersJsonDao = new CharactersJsonDao();
        return charactersJsonDao.checkCharactersFile();
    }
}
