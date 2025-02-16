package src.Bussines.Strats;

import src.Bussines.*;
import src.Bussines.Character;
import src.Bussines.Strategy;

import java.util.List;

public class Balanced extends CombatStrategies {
    @Override
    public void performAction(Character character, List<Item> items, Team enemyTeam, StringBuilder messageRound, ManagerCharacter managerCharacter) {
        if (character.getWeapon() == null) {
            requestWeapon(character, items, messageRound);
        } else {
            if (character.getArmour() != null) {
                if (character.getDamage_received() >= 0.5 && character.getDamage_received() <= 1.0) {
                    // Defend
                    character.setDeffendingMode(true);
                    messageRound.append(character.getName()).append(" DEFENDS.\n");
                } else {
                    // Attack randomly
                    attackRandom(character, enemyTeam, messageRound, managerCharacter);
                }
            } else {
                // Attack if no armour
                attackRandom(character, enemyTeam, messageRound, managerCharacter);
            }
        }
    }
}
