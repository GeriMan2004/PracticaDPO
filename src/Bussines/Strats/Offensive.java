package src.Bussines.Strats;

import src.Bussines.*;
import src.Bussines.Character;

import java.util.List;

public class Offensive extends CombatStrategies {

    @Override
    public void performAction(Character character, List<Item> items, Team enemyTeam, StringBuilder messageRound, ManagerCharacter managerCharacter) {
        if (character.getWeapon() == null) {
            requestWeapon(character, items, messageRound);
        } else {
            attackRandom(character, enemyTeam, messageRound, managerCharacter);
        }
    }
}
