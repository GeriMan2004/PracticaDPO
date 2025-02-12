package src.Bussines;

import src.Persistence.Characters.CharactersApiDao;
import src.Persistence.Characters.CharactersDao;
import src.Persistence.Characters.CharactersJsonDao;
import java.util.ArrayList;
import java.util.List;

/**
 * ManagerCharacter es la clase que se encarga de gestionar los personajes del juego
 * {@link CharactersJsonDao}
 */
public class ManagerCharacter {
    CharactersDao charactersDao;
    /**
     * Contructor de la clase ManagerCharacter
     * @param charactersJsonDao es el objeto que se encarga de la persistencia de los personajes
     */
    public ManagerCharacter(CharactersJsonDao charactersJsonDao, CharactersApiDao charactersApiDao) {
        // En caso de que la API esté disponible, se utilizará para obtener los personajes, en caso contrario, se utilizará el fichero 'characters.json'
        if (charactersApiDao.checkAvailable()) {
            charactersDao = charactersApiDao;
        } else {
            charactersDao = charactersJsonDao;
        }
    }

    /**
     * Metodo para cargar los personajes
     * @return List<Character>
     */
    public List<Character> UploadCharacters()
    {
        return CharactersJsonDao.readCharacters();
    }

    /**
     * Metodo para verificar si el archivo de personajes existe
     * @return boolean true si existe, false si no
     */
    public int checkCharacterFile()
    {
        CharactersDao charactersApiDao = new CharactersApiDao();
        if (charactersApiDao.checkAvailable()) {
            return 1;
        }
        CharactersDao charactersJsonDao = new CharactersJsonDao();
        if (charactersJsonDao.checkAvailable()) {
            return 2;
        }
        return 0;
    }

    /**
     * Metodo para calcular el daño final hecho
     * por el ataque de un personaje
     * @param character es el personaje que ataca
     * @param attack es el daño base hecho por el ataque
     * @return double
     */
    public double reciveDamage(Character character, double attack) {
        double finalattack;
        double defensorDamageRecived = character.getDamage_received();
        double defensorWeight = character.getWeight();
        Item defensorArmor = character.getArmour();
        int armorValue = defensorArmor.getPowerValue();

        finalattack = ((attack - (((200 * (1 - defensorDamageRecived)) / defensorWeight) + ((double) armorValue / 20))) / 100);
        if (character.getDeffendingMode()) {
            finalattack = finalattack - character.getDamage_reduction();
        }
        return finalattack;
    }

    /**
     * Metodo para calcular el daño base hecho por un personaje
     * @param character es el personaje que ataca
     * @return float daño total hecho por el ataque, a falta de reducciones del defensor
     */
    public float attack(Character character) {
        float attackerWeight = character.getWeight();
        double attackerDamageRecived = character.getDamage_received();
        return ((float) ((attackerWeight *(1 - attackerDamageRecived))/10+18+ (double) (character.getWeapon().getPowerValue()) /20));
    }

    /**
     * Metodo para obtener el nombre de un personaje a partir de su id
     * @param members characters
     * @return matchedCharacters
     */
    public List<Character> matchCharacters(List<Character> members) {
        List<Character> characters = CharactersJsonDao.readCharacters();
        List<Character> matchedCharacters = new ArrayList<>();
        for (Character character : characters) {
            for (Character member : members) {
                if (character.getId() == member.getId()) {
                    Character cloned = new Character(character.getId(), character.getStrategy());
                    cloned.setName(character.getName());
                    cloned.setStrategy(member.getStrategy());
                    cloned.setWeight(character.getWeight());
                    matchedCharacters.add(cloned);
                }
            }
        }
        return matchedCharacters;
    }
}
