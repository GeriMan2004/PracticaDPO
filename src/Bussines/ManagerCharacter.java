package src.Bussines;

import edu.salle.url.api.exception.ApiException;
import src.Bussines.ItemTypes.Armor;
import src.Bussines.ItemTypes.Weapon;
import src.Persistence.Characters.CharactersApiDao;
import src.Persistence.Characters.CharactersDao;
import src.Persistence.Characters.CharactersJsonDao;
import java.util.ArrayList;
import java.util.List;

/**
 * ManagerCharacter es la clase que se encarga de gestionar los personajes del juego
 * {@link CharactersDao}
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
        } else if (charactersJsonDao.checkAvailable()) {
            charactersDao = charactersJsonDao;
        }
    }

    /**
     * Metodo para cargar los personajes
     * @return List<Character>
     */
    public List<Character> UploadCharacters() throws ApiException {
        return charactersDao.readCharacters();
    }

    /**
     * Metodo para verificar si el archivo de personajes existe
     * @return boolean true si existe, false si no
     */
    public int checkCharacterFile() throws ApiException {
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
     * @param defender es el personaje que ataca
     * @param attack es el daño base hecho por el ataque
     * @param attackerWeight es el peso del atacante
     * @return double
     */
    public double reciveDamage(Character defender, double attack) {
        double defenderDamageReceived = defender.getDamage_received();
        double defenderWeight = defender.getWeight();
        double armorEffective = 0;

        if(defender.getArmour() != null) {
            if(defender.getArmour() instanceof Armor) {
                armorEffective = ((Armor) defender.getArmour()).getEffectiveArmor(defenderWeight);
            } else {
                armorEffective = defender.getArmour().getPowerValue() / 20.0;
            }
        }

        double baseReduction = (200 * (1 - defenderDamageReceived)) / defenderWeight;

        double finalDamage = (attack - (baseReduction + armorEffective) * 1.4) / 100.0;

        if (defender.getDeffendingMode()) {
            finalDamage -= defender.getDamage_reduction();
        }
        return finalDamage;
    }


    /**
     * Metodo para calcular el daño base hecho por un personaje
     * @param attacker es el personaje que ataca
     * @return float daño total hecho por el ataque, a falta de reducciones del defensor
     */
    public float attack(Character attacker) {
        double attackerWeight = attacker.getWeight();
        double attackerDamageReceived = attacker.getDamage_received();
        double weaponEffectiveAttack = 0;

        if(attacker.getWeapon() != null) {
            // We assume that attacker.getWeapon() now returns an instance of Weapon (or SuperWeapon)
            if (attacker.getWeapon() instanceof Weapon) {
                weaponEffectiveAttack = ((Weapon) attacker.getWeapon()).getEffectiveAttack(attackerWeight);
            } else {
                // fallback if needed
                weaponEffectiveAttack = attacker.getWeapon().getPowerValue() / 20.0;
            }
        }

        return (float) ((attackerWeight * (1 - attackerDamageReceived)) / 10 + 18 + weaponEffectiveAttack);
    }

    /**
     * Metodo para obtener el nombre de un personaje a partir de su id
     * @param members characters
     * @return matchedCharacters
     */
    public List<Character> matchCharacters(List<Character> members) throws ApiException {
        List<Character> characters = charactersDao.readCharacters();
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
