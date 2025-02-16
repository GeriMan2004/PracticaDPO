package src.Bussines.Strats;

import src.Bussines.*;
import src.Bussines.Character;

import java.util.List;
import java.util.Random;

public abstract class CombatStrategies implements Strategy {

    protected void requestWeapon(Character character, List<Item> items, StringBuilder messageRound) {
        Random random = new Random();
        int randomNumber;
        String type;
        do {
            randomNumber = random.nextInt(items.size());
            type = items.get(randomNumber).getObject_type();
        } while (!"Weapon".equalsIgnoreCase(type));
        character.setWeapon(items.get(randomNumber));
        messageRound.append(character.getName())
                .append(" RECEIVED WEAPON ")
                .append(character.getWeapon().getName())
                .append("\n");
    }

    protected void attackTarget(Character attacker, Character defender, StringBuilder messageRound, ManagerCharacter managerCharacter) {
        defender.setAttacked(true);
        double baseDamage = managerCharacter.attack(attacker);
        messageRound.append(attacker.getName())
                .append(" ATTACKS ")
                .append(defender.getName())
                .append(" WITH ")
                .append(attacker.getWeapon() != null ? attacker.getWeapon().getName() : "FISTS")
                .append(" FOR ")
                .append(String.format("%.1f", baseDamage))
                .append(" DAMAGE!\n");
        double finalDamage = managerCharacter.reciveDamage(defender, baseDamage);
        if (defender.getDeffendingMode()) {
            messageRound.append("\t")
                    .append(defender.getName())
                    .append(" REDUCES damage by ")
                    .append(String.format("%.2f", attacker.getDamage_reduction()))
                    .append(" using defending mode.\n");
        }
        defender.setDamage_received(defender.getDamage_received() + finalDamage);
        messageRound.append("\t")
                .append(defender.getName())
                .append(" RECEIVES ")
                .append(String.format("%.2f", finalDamage))
                .append(" DAMAGE.\n");
        if (attacker.getWeapon() != null) {
            attacker.getWeapon().decreaseDurability();
        }
        if (defender.getWeapon() != null) {
            defender.getWeapon().decreaseDurability();
        }
    }

    protected void attackRandom(Character attacker, Team enemyTeam, StringBuilder messageRound, ManagerCharacter managerCharacter) {
        Random random = new Random();
        List<Character> enemies = enemyTeam.getMembers();
        Character defender;
        do {
            int index = random.nextInt(enemies.size());
            defender = enemies.get(index);
        } while (defender.isKnockedOut());
        attackTarget(attacker, defender, messageRound, managerCharacter);
    }
}
