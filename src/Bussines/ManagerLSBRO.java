package src.Bussines;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ManagerLSBRO {
    private ManagerCharacter managerCharacter;
    private ManagerTeam managerTeam;
    private ManagerObject managerObject;
    private ManagerCombat managerCombat;

    public ManagerLSBRO(ManagerCharacter managerCharacter, ManagerTeam managerTeam, ManagerObject managerObject, ManagerCombat managerCombat) {
        this.managerCharacter = managerCharacter;
        this.managerTeam = managerTeam;
        this.managerObject = managerObject;
        this.managerCombat = managerCombat;
    }

    /**
     * @title: Función para simular rondas de los combates
     * Esta función simula una ronda de los combates, en la que se enfrentan los personajes de los equipos seleccionados
     * respetando turnos de los personajes, estrategias, armas y armaduras, a traves de un algorítmo calculado
     * @autor: Gerard Perez
     * @autor: Walter-Arnau Quintili
     * @return void
     */
    public List<Object> simulateRound (Combat combat) {
        double dmg  = 0;
        int randomNumber = 0;
        int probability = 0;
        String type;
        List<Object> result = new ArrayList<>();
        List<Item> items = ManagerObject.uploadObjects();
        StringBuilder messageRound = new StringBuilder();
        Team team1 = combat.getTeam1();
        Team team2 = combat.getTeam2();
        List<Character> members1 = team1.getMembers();
        List<Character> members2 = team2.getMembers();
        messageRound.append("\n");

        for (Character member : members1) {
            if (member.getWeapon() != null && member.getDamage_received() > 0.5 && member.getDamage_received() < 1 && member.getArmour() != null && !member.isKnockedOut()){
                member.setDeffendingMode(true);
            } else {
                member.setDeffendingMode(false);
            }
        }
        for (Character member : members2) {
            if (member.getWeapon() != null && member.getDamage_received() > 0.5 && member.getDamage_received() < 1 && member.getArmour() != null && !member.isKnockedOut()){
                member.setDeffendingMode(true);
            } else {
                member.setDeffendingMode(false);
            }
        }
        for (Character member : members1) {
            if (member.getWeapon() == null) {
                // Ask for weapon mode
                do {
                    randomNumber = new Random().nextInt(items.size());
                    type = items.get(randomNumber).getObject_type();
                } while (!type.equals("Weapon"));
                member.setWeapon(items.get(randomNumber));
                messageRound.append(member.getName()).append(member.getName()).append(" RECIVED WEAPON " + member.getWeapon().getName());
            } else if (!member.getDeffendingMode() && !member.isKnockedOut()){
                // Attacking mode
                do {
                    randomNumber = new Random().nextInt(4);
                } while (members2.get(randomNumber).isKnockedOut());
                dmg = managerCharacter.attack(member, members2.get(randomNumber));
                messageRound.append(member.getName()).append(" ATTACKS ").append(members2.get(randomNumber).getName())
                        .append(" WITH ").append(member.getWeapon().getName()).append(" FOR ").append(dmg).append(" damage.\n");
                dmg = managerCharacter.reciveDamage(members2.get(randomNumber), dmg);
                messageRound.append("\t").append(members2.get(randomNumber).getName())
                        .append(" RECIVES ").append(dmg).append(" DAMAGE.\n");
                member.getWeapon().decreaseDurability();
                members2.get(randomNumber).getWeapon().decreaseDurability();
            }
        }
        messageRound.append("\n");
        for (Character member : members2) {
            if (member.getWeapon() == null) {
                do {
                    randomNumber = new Random().nextInt(items.size());
                    type = items.get(randomNumber).getObject_type();
                } while (!type.equals("Weapon"));
                messageRound.append(member.getName()).append(member.getName()).append(" RECIVED WEAPON ");
            } else {

            }
                // Attacking mode
                if (team1.allMembersKnockedOut()) {
                    messageRound.append("Round Stopped, Team 2 has deffeated all members of Team 1.\n");
                    break;
                }
                do {
                    randomNumber = new Random().nextInt(4);
                } while (members1.get(randomNumber).isKnockedOut());
                dmg = managerCharacter.attack(member, members1.get(randomNumber));
                messageRound.append(member.getName()).append(" ATTACKS ").append(members1.get(randomNumber).getName())
                        .append(" WITH ").append(member.getWeapon().getName()).append(" FOR ").append(dmg).append(" damage.\n");
                dmg = managerCharacter.reciveDamage(members1.get(randomNumber), dmg);
                messageRound.append("\t").append(members1.get(randomNumber).getName())
                        .append(" RECIVES ").append(dmg).append(" DAMAGE.\n");

        }
        messageRound.append("\n");
        for (Character member : members1) {
            if (member.getWeapon().getDurability() <= 0 && !member.isKnockedOut()) {
                member.setWeapon(null);
                messageRound.append(member.getName()).append("Oh no! "+member.getName()+ "'s "+ member.getWeapon()+" breaks!");
            }
            if (member.getArmour().getDurability() <= 0 && !member.isKnockedOut()) {
                member.setArmour(null);
                messageRound.append(member.getName()).append("Oh no! "+member.getName()+ "'s "+ member.getArmour()+" breaks!");
            }
        }
        messageRound.append("\n");
        for (Character member : members2) {
            if (member.getWeapon().getDurability() <= 0 && !member.isKnockedOut()) {
                member.setWeapon(null);
                messageRound.append(member.getName()).append("Oh no! "+member.getName()+ "'s "+ member.getWeapon()+" breaks!");
            }
            if (member.getArmour().getDurability() <= 0 && !member.isKnockedOut()) {
                member.setArmour(null);
                messageRound.append(member.getName()).append("Oh no! "+member.getName()+ "'s "+ member.getArmour()+" breaks!");
            }
        }
        messageRound.append("\n");
        for(Character member : members1) {
            randomNumber = new Random().nextInt(200);
            probability = randomNumber/100;
            if (member.getDamage_received() >  probability && !member.isKnockedOut()) {
                member.setKnockedOut(true);
                messageRound.append(member.getName()).append(" flies away! It’s a KO!\n");
            }
        }
        result.add(combat);
        result.add(messageRound.toString());
        return result;
    }

    public void setManagerCharacter(ManagerCharacter managerCharacter) {
        this.managerCharacter = managerCharacter;
    }

    public void setManagerTeam(ManagerTeam managerTeam) {
        this.managerTeam = managerTeam;
    }

    public void setManagerObject(ManagerObject managerObject) {
        this.managerObject = managerObject;
    }

    public void setManagerCombat(ManagerCombat managerCombat) {
        this.managerCombat = managerCombat;
    }

    public ManagerCharacter getManagerCharacter() {
        return managerCharacter;
    }

    public ManagerTeam getManagerTeam() {
        return managerTeam;
    }

    public ManagerObject getManagerObject() {
        return managerObject;
    }

    public ManagerCombat getManagerCombat() {
        return managerCombat;
    }
}
