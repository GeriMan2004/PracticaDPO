package src;

import src.Bussines.*;
import src.Bussines.Character;
import src.Persistence.CharactersJsonDao;
import src.Presentation.Controller;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CharactersJsonDao charactersJsonDao = new CharactersJsonDao();
        ObjectsJsonDao objectsJsonDao = new ObjectsJsonDao();
        StatsJsonDao statsJsonDao = new StatsJsonDao();
        TeamsJsonDao teamsJsonDao = new TeamsJsonDao();

        ManagerCharacter ManagerCharacter = new ManagerCharacter(charactersJsonDao);
        ManagerTeam ManagerTeam = new ManagerTeam(teamsJsonDao, statsJsonDao);
        ManagerObject ManagerObject = new ManagerObject(objectsJsonDao);
        ManagerCombat ManagerCombat = new ManagerCombat(objectsJsonDao);

        ManagerLSBRO managerLSBRO = new ManagerLSBRO(ManagerCharacter, ManagerTeam, ManagerObject, ManagerCombat);

        Controller controller = new Controller(managerLSBRO);
        controller.start();

    }
}