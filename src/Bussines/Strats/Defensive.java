package src.Bussines.Strats;

import src.Bussines.*;
import src.Bussines.Character;

import java.util.List;

public class Defensive extends CombatStrategies {
    @Override
    public void performAction(Character character, List<Item> items, Team enemyTeam, StringBuilder messageRound, ManagerCharacter managerCharacter) {
        if (character.getArmour() != null) {
            if (character.getDamage_received() < 1.0) {
                character.setDeffendingMode(true);
                messageRound.append(character.getName()).append(" DEFENDS.\n");
            } else {
                attackRandom(character, enemyTeam, messageRound, managerCharacter);
            }
        } else {
            attackRandom(character, enemyTeam, messageRound, managerCharacter);
        }
    }
}
