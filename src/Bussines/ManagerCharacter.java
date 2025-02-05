package src.Bussines;

import src.Persistence.CharactersJsonDao;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase ManagerCharacter
 */
public class ManagerCharacter {

    CharactersJsonDao charactersJsonDao;
    /**
     * Contructor de la clase ManagerCharacter
     * @param charactersJsonDao
     */
    public ManagerCharacter(CharactersJsonDao charactersJsonDao) {
        this.charactersJsonDao = charactersJsonDao;
    }
    /**
     * Metodo para cargar los personajes
     * @return List<Character>
     */
    public List<Character> UploadCharacters()
    {
        CharactersJsonDao charactersJsonDao = new CharactersJsonDao();
        return CharactersJsonDao.readCharacters();
    }
    /**
     * Metodo para verificar si el archivo de personajes existe
     * @return boolean
     */
    public boolean checkCharacterFile()
    {
        CharactersJsonDao charactersJsonDao = new CharactersJsonDao();
        return charactersJsonDao.checkCharactersFile();
    }

    /**
     * Metodo para calcular el daño final hecho
     * por el ataque de un personaje
     * @param character
     * @param attack
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
     * @return float
     */
    public float attack(Character character) {
        float attack=0;
        float attackerWeight = character.getWeight();
        double attackerDamageRecived = character.getDamage_received();

        if(character.getWeapon() == null) {
            attack = (float) ((attackerWeight *(1 - attackerDamageRecived))/10+18);
        }else{
            attack = (float) ((attackerWeight *(1 - attackerDamageRecived))/10+18+ (double) (character.getWeapon().getPowerValue()) /20);
        }

        return attack;
    }

    /**
     * Metodo para obtener el nombre de un personaje a partir de su id
     * @param members characters
     * @return List<Character>
     */
    public List<Character> matchCharacters(List<Character> members) {
        CharactersJsonDao charactersJsonDao = new CharactersJsonDao();
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
