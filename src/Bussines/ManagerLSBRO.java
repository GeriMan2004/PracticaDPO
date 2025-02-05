package src.Bussines;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ManagerLSBRO {
    private ManagerCharacter managerCharacter;
    private ManagerTeam managerTeam;
    private ManagerObject managerObject;
    private ManagerCombat managerCombat;

    /**
     * Constructor de la clase ManagerLSBRO, inicializa todos los managers necesarios para el correcto funcionamiento del programa
     * @param managerCharacter
     * @param managerTeam
     * @param managerObject
     * @param managerCombat
     */
    public ManagerLSBRO(ManagerCharacter managerCharacter, ManagerTeam managerTeam, ManagerObject managerObject, ManagerCombat managerCombat) {
        this.managerCharacter = managerCharacter;
        this.managerTeam = managerTeam;
        this.managerObject = managerObject;
        this.managerCombat = managerCombat;
    }

    /**
     * Esta función simula una ronda de los combates, en la que se enfrentan los personajes de los equipos seleccionados
     * respetando turnos de los personajes, estrategias, armas y armaduras, a traves de un algorítmo calculado
     * @param combat - El combate a simular
     * @return List<Object> - El objeto Combat modificado y el mensaje de la ronda
     */
    public List<Object> simulateRound (Combat combat) {
        List<Object> result = new ArrayList<>();

        // Obtén la lista de ítems disponibles
        List<Item> items = ManagerObject.uploadObjects();
        StringBuilder messageRound = new StringBuilder();

        // Obtén los equipos para no tener que acceder a ellos continuamente con la función getTeams
        Team team1 = combat.getTeam1();
        Team team2 = combat.getTeam2();

        messageRound.append("\n");

        // ========== REVISA SI ALGÚN PERSONAJE ESTÁ EN MODO DEFENSA EN EL EQUIPO 1 ==========
        checkMode(team1);

        // ========== REVISA SI ALGÚN PERSONAJE ESTÁ EN MODO DEFENSA EN EL EQUIPO 2 ==========
        checkMode(team2);

        // ========== ATAQUES DEL EQUIPO 1 ==========
        attacks(items, messageRound, team1, team2);

        // ========== ATAQUES DEL EQUIPO 2 ==========
        attacks(items, messageRound, team2, team1);

        // ========== REVISA SI ALGÚN ARMA O ARMADURA SE ROMPE EN EL EQUIPO 1 ==========
        equipmentCheck(messageRound, team1);

        // ========== REVISA SI ALGÚN ARMA O ARMADURA SE ROMPE EN EL EQUIPO 2 ==========
        equipmentCheck(messageRound, team2);

        // ========== REVISA SI ALGÚN PERSONAJE HA SIDO NOQUEADO EN EL EQUIPO 1 ==========
        checkKOs(messageRound, team1);

        // ========== REVISA SI ALGÚN PERSONAJE HA SIDO NOQUEADO EN EL EQUIPO 2 ==========
        checkKOs(messageRound, team2);

        // Sumamos Ronda tras terminarla
        combat.setRounds(combat.getRounds() + 1);
        checkifFinished(combat);

        // Devuelve el combate actualizado y el mensaje de la ronda
        result.add(combat);
        result.add(messageRound.toString());
        return result;
    }

    /**
     * Esta función añade los mensajes de los personajes cuyas armas o armaduras se rompen
     * @param messageRound
     * @param team1
     * @return void
     */
    private void equipmentCheck(StringBuilder messageRound, Team team1) {
        int auxLength = messageRound.length();
        for (Character member : team1.getMembers()) {
            // Revisa el arma
            if (member.getWeapon() != null && member.getWeapon().getDurability() <= 0 && !member.isKnockedOut()) {
                Item brokenWeapon = member.getWeapon();
                member.setWeapon(null);
                messageRound.append(member.getName())
                        .append(": Oh no! ")
                        .append(member.getName())
                        .append("'s ")
                        .append(brokenWeapon.getName())
                        .append(" breaks!\n");
            }
            // Revisa la armadura
            if (member.getArmour() != null && member.getArmour().getDurability() <= 0 && !member.isKnockedOut()) {
                Item brokenArmour = member.getArmour();
                member.setArmour(null);
                messageRound.append(member.getName())
                        .append(": Oh no! ")
                        .append(member.getName())
                        .append("'s ")
                        .append(brokenArmour.getName())
                        .append(" breaks!\n");
            }
        }
        if (messageRound.length() > auxLength) {
            messageRound.append("\n");
        }
    }

    /**
     * Esta función simula los ataques de los personajes de un equipo a los personajes de otro equipo
     * @param items
     * @param messageRound
     * @param team1
     * @param team2
     */
    private void attacks(List<Item> items, StringBuilder messageRound, Team team1, Team team2) {
        int randomNumber;
        String type;
        for (Character member : team1.getMembers()) {
            // Si el personaje no tiene arma y no está KO, busca un arma aleatoria
            if (member.getWeapon() == null && !member.isKnockedOut()) {
                do {
                    randomNumber = new Random().nextInt(items.size());
                    type = items.get(randomNumber).getObject_type();
                } while (!type.equals("Weapon"));

                member.setWeapon(items.get(randomNumber));
                messageRound.append(member.getName())
                        .append(" RECEIVED WEAPON ")
                        .append(member.getWeapon().getName()).append("\n");

                // De lo contrario, si no está en modo defensa y no está KO, ataca
            } else if (!member.getDeffendingMode() && !member.isKnockedOut()) {
                // Elige un oponente vivo al azar en el equipo contrario
                Character defender;
                do {
                    randomNumber = new Random().nextInt(team2.getMembers().size());
                    defender = team2.getMembers().get(randomNumber);
                } while (defender.isKnockedOut());

                // Calcula el daño base del ataque por parte del personaje atacante
                double baseDamage = managerCharacter.attack(member);

                messageRound.append(member.getName())
                        .append(" ATTACKS ")
                        .append(defender.getName())
                        .append(" WITH ")
                        .append(member.getWeapon() != null ? member.getWeapon().getName() : "FISTS")
                        .append(" FOR ")
                        .append(String.format("%.1f", baseDamage))
                        .append(" DAMAGE!\n");

                // Ajusta el daño final según reciveDamage (defensas, modo defensa, armadura, etc.)
                double finalDamage = managerCharacter.reciveDamage(defender, baseDamage);

                // **Aplica el daño acumulándolo** en el atributo damage_received
                defender.setDamage_received(defender.getDamage_received() + finalDamage);

                messageRound.append("\t")
                        .append(defender.getName())
                        .append(" RECEIVES ")
                        .append(String.format("%.2f", finalDamage))
                        .append(" DAMAGE.\n");

                // Disminuye durabilidad del arma del atacante, si existe
                if (member.getWeapon() != null) {
                    member.getWeapon().decreaseDurability();
                }
                // Disminuye durabilidad del arma del defensor, si existe
                if (defender.getWeapon() != null) {
                    defender.getWeapon().decreaseDurability();
                }
            }
        }
        messageRound.append("\n");
    }

    /**
     * Función para comprobar si el combate ha terminado
     * Esta función comprueba si el combate ha terminado, es decir, si todos los personajes de un equipo están KO
     * @param combat
     */
    private void checkifFinished(Combat combat) {
        int aux = 1;
        for (Character member : combat.getTeam1().getMembers()) {
            if (!member.isKnockedOut()) {
                aux = 0;
            }
        }
        if (aux == 1) {
            combat.setFinished(true);
            combat.setWinner(combat.getTeam2().getName());
        }
        aux = 1;
        for (Character member : combat.getTeam2().getMembers()) {
            if (!member.isKnockedOut()) {
                aux = 0;
            }
        }
        if (aux == 1) {
            combat.setFinished(true);
            combat.setWinner(combat.getTeam1().getName());
        }
    }

    /**
     * Esta función comprueba si un personaje ha sido noqueado, es decir, si su daño recibido supera un umbral
     * @param messageRound
     * @param team
     */
    private void checkKOs(StringBuilder messageRound, Team team) {
        int randomNumber;
        int probability;
        for (Character member : team.getMembers()) {
            // Ejemplo de cálculo de probabilidad, pero ojo con la división entera
            randomNumber = new Random().nextInt(200);
            probability = randomNumber / 100;
            // Revisa si supera el "threshold" y no está KO
            if (member.getDamage_received() > probability && !member.isKnockedOut()) {
                member.setKnockedOut(true);
                messageRound.append(member.getName()).append(" flies away! It’s a KO!\n");
            }
        }
    }

    /**
     * Función para comprobar el modo de defensa
     * @param team
     */
    private void checkMode(Team team) {
        for (Character member : team.getMembers()) {
            member.setDeffendingMode(member.getWeapon() != null && member.getDamage_received() > 0.5 && member.getDamage_received() < 1 && member.getArmour() != null && !member.isKnockedOut());
        }
    }

    /**
     * Función para obtener el ManagerCharacter
     * @return ManagerCharacter
     */
    public ManagerCharacter getManagerCharacter() {
        return managerCharacter;
    }
    /**
     * Función para obtener el ManagerTeam
     * @return ManagerTeam
     */
    public ManagerTeam getManagerTeam() {
        return managerTeam;
    }
    /**
     * Función para obtener el ManagerObject
     * @return ManagerObject
     */
    public ManagerObject getManagerObject() {
        return managerObject;
    }
    /**
     * Función para obtener el ManagerCombat
     * @return ManagerCombat
     */
    public ManagerCombat getManagerCombat() {
        return managerCombat;
    }
}
