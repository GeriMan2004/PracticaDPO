package src;

import src.Bussines.*;
import src.Persistence.Characters.CharactersApiDao;
import src.Persistence.Characters.CharactersJsonDao;
import src.Persistence.Objects.ObjectsApiDao;
import src.Persistence.Objects.ObjectsJsonDao;
import src.Persistence.Stats.StatsApiDao;
import src.Persistence.Stats.StatsJsonDao;
import src.Persistence.Teams.TeamsApiDao;
import src.Persistence.Teams.TeamsJsonDao;
import src.Presentation.Controller;

import java.io.IOException;

/**
 * Clase principal del programa, donde se instancian los DAO, Managers y el controlador.
 * Se ejecuta el programa a través de la función start del controlador.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // Instanciamos los DAOs
        CharactersJsonDao charactersJsonDao = new CharactersJsonDao();
        ObjectsJsonDao objectsJsonDao = new ObjectsJsonDao();
        StatsJsonDao statsJsonDao = new StatsJsonDao();
        TeamsJsonDao teamsJsonDao = new TeamsJsonDao();
        CharactersApiDao charactersApiDao = new CharactersApiDao();
        ObjectsApiDao objectsApiDao = new ObjectsApiDao();
        TeamsApiDao teamsApiDao = new TeamsApiDao();
        StatsApiDao statsApiDao = new StatsApiDao();

        // Instanciamos los Managers
        ManagerCharacter ManagerCharacter = new ManagerCharacter(charactersJsonDao, charactersApiDao);
        ManagerTeam ManagerTeam = new ManagerTeam(teamsJsonDao, statsJsonDao, teamsApiDao, statsApiDao);
        ManagerObject ManagerObject = new ManagerObject(objectsJsonDao, objectsApiDao);
        ManagerCombat ManagerCombat = new ManagerCombat(objectsJsonDao, objectsApiDao);

        // Instanciamos el ManagerLSBRO con los Managers anteriores como parámetros
        ManagerLSBRO managerLSBRO = new ManagerLSBRO(ManagerCharacter, ManagerTeam, ManagerObject, ManagerCombat);

        // Instanciamos el controlador y ejecutamos el programa a través de la función start
        Controller controller = new Controller(managerLSBRO);
        controller.start();
    }
}