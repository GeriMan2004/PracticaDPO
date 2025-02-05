package src.Bussines;

import src.Persistence.CharactersJsonDao;
import src.Persistence.ObjectsJsonDao;
import src.Persistence.TeamsJsonDao;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.lang.reflect.Member;
import java.util.List;

public class ManagerCombat {

    private ObjectsJsonDao itemsJsonDao;

    /**
     * Constructor de la clase ManagerCombat
     * @param itemsJsonDao
     */
    public ManagerCombat(ObjectsJsonDao itemsJsonDao) {
        this.itemsJsonDao = itemsJsonDao;
    }

    /**
     * Este metodo se encarga de verificar si el combate ha finalizado
     * todo el equipo 1 o el equipo 2 estan noqueados
     * @param team1
     * @param team2
     * @return boolean
     */
    private boolean isMatchFinished(Team team1, Team team2) {

        boolean team1AllKO = true;
        for (Character member : team1.getMembers()) {
            if (!member.isKnockedOut()) {
                team1AllKO = false;
                break;
            }
        }

        boolean team2AllKO = true;
        for (Character member : team2.getMembers()) {
            if (!member.isKnockedOut()) {
                team2AllKO = false;
                break;
            }
        }
        return (team1AllKO || team2AllKO);
    }

    /**
     * Este metodo se encarga de incializar la
     * clase combate con todos los datos necesarios
     * @param teamsSelected
     * @return Combat
     */
    public Combat initCombat(List<Team> teamsSelected) {
        teamsSelected = matchRandomWeaponArmour(teamsSelected.get(0), teamsSelected.get(1));
        return new Combat(teamsSelected.get(0), teamsSelected.get(1));
    }
    /**
     * Este metodo se encarga de assignar un arma
     * y armadura aleatoria a cada personaje del combate
     * @param team1
     * @return team2
     * @return Combat
     */
    public List<Team> matchRandomWeaponArmour(Team team1, Team team2){
        List<Team> teamsCombat = new ArrayList<>();
        List<Item> items = itemsJsonDao.readObjects();
        List<Character> members1 = team1.getMembers();
        List<Character> members2 = team2.getMembers();
        String type = "";
        int randomNumber;

        for(Character character: members1){
            do{
                randomNumber = new Random().nextInt(items.size());
                type = items.get(randomNumber).getObject_type();
            } while (!type.equals("Armor"));
            character.setArmour(items.get(randomNumber));
            do {
                randomNumber = new Random().nextInt(items.size());
                type = items.get(randomNumber).getObject_type();
            } while (!type.equals("Weapon"));
            character.setWeapon(items.get(randomNumber));
        }
        for(Character character: members2){
            do{
                randomNumber = new Random().nextInt(items.size());
                type = items.get(randomNumber).getObject_type();
            } while (!type.equals("Armor"));
            character.setArmour(items.get(randomNumber));
            do {
                randomNumber = new Random().nextInt(items.size());
                type = items.get(randomNumber).getObject_type();
            } while (!type.equals("Weapon"));
            character.setWeapon(items.get(randomNumber));
        }
        team1.setMembers(members1);
        team2.setMembers(members2);

        teamsCombat.add(team1);
        teamsCombat.add(team2);

        return teamsCombat;
    }




}