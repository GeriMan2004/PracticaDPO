package src.Bussines;

import edu.salle.url.api.exception.ApiException;
import src.Persistence.Objects.ObjectsApiDao;
import src.Persistence.Objects.ObjectsDao;
import src.Persistence.Objects.ObjectsJsonDao;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

/**
 * Esta clase se encarga de gestionar los combates entre los equipos, en concreto de cargar los equipos con armas y armaduras aleatorias.
 * {@link Combat}
 */
public class ManagerCombat {

    ObjectsDao objectsDao;

    /**
     * Constructor de la clase ManagerCombat
     * @param objectsJsonDao En caso de que el objectsApi Falle, utilizaremos este
     * @param objectsApiDao es nuestra primera opción en caso de que funcione
     */
    public ManagerCombat(ObjectsJsonDao objectsJsonDao, ObjectsApiDao objectsApiDao) {
        if (objectsApiDao.checkAvailable()){
            this.objectsDao = objectsApiDao;
        } else if (objectsJsonDao.checkAvailable()) {
            this.objectsDao = objectsJsonDao;
        }
    }

    /**
     * Este metodo se encarga de incializar la
     * clase combate con todos los datos necesarios
     * @param teamsSelected es la lista de equipos seleccionados
     * @return Combat
     */
    public Combat initCombat(List<Team> teamsSelected) throws ApiException {
        teamsSelected = matchRandomWeaponArmour(teamsSelected.get(0), teamsSelected.get(1));
        return new Combat(teamsSelected.get(0), teamsSelected.get(1));
    }

    /**
     * Este metodo se encarga de assignar un arma
     * y armadura aleatoria a cada personaje del combate
     * @param team1 es el primer equipo
     * @return List<Team>
     */
    public List<Team> matchRandomWeaponArmour(Team team1, Team team2) throws ApiException {
        List<Team> teamsCombat = new ArrayList<>();
        List<Item> items = objectsDao.readObjects();
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