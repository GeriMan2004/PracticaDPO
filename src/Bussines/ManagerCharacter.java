package src.Bussines;

import src.Persistence.CharactersJsonDao;

import java.util.ArrayList;
import java.util.List;

public class ManagerCharacter {

    CharactersJsonDao charactersJsonDao;

    public ManagerCharacter(CharactersJsonDao charactersJsonDao) {
        this.charactersJsonDao = charactersJsonDao;
    }

    public List<Character> UploadCharacters()
    {
        CharactersJsonDao charactersJsonDao = new CharactersJsonDao();
        return CharactersJsonDao.readCharacters();
    }

    public boolean checkCharacterFile()
    {
        CharactersJsonDao charactersJsonDao = new CharactersJsonDao();
        return charactersJsonDao.checkCharactersFile();
    }


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

    public float attack(Character character, Character defender) {
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

    public List<Character> matchCharacters(List<Character> members) {
        CharactersJsonDao charactersJsonDao = new CharactersJsonDao();
        List<Character> characters = CharactersJsonDao.readCharacters();
        List<Character> matchedCharacters = new ArrayList<>();
        for (Character character : characters) {
            for (Character member : members) {
                if (character.getId() == member.getId()) {
                    matchedCharacters.add(character);
                }
            }
        }
        return matchedCharacters;
    }
}
