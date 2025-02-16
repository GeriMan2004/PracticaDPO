package src.Bussines.Strats;

import src.Bussines.*;
import src.Bussines.Character;

import java.util.List;

public class Sniper extends CombatStrategies {

    public void performAction(Character character, List<Item> items, Team enemyTeam, StringBuilder messageRound, ManagerCharacter managerCharacter) {
        if (character.getWeapon() == null) {
            requestWeapon(character, items, messageRound);
        } else {
            // Attack the enemy with the highest accumulated damage
            Character target = null;
            double maxDamage = -1;
            for (Character enemy : enemyTeam.getMembers()) {
                if (!enemy.isKnockedOut() && enemy.getDamage_received() > maxDamage) {
                    maxDamage = enemy.getDamage_received();
                    target = enemy;
                }
            }
            if (target != null) {
                attackTarget(character, target, messageRound, managerCharacter);
            }
        }
    }
}
