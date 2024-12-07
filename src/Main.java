package src;

import src.Bussines.ManagerCharacter;
import src.Bussines.ManagerCombat;
import src.Bussines.ManagerObject;
import src.Bussines.ManagerTeam;
import src.Persistence.CharactersJsonDao;
import src.Presentation.Controller;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ManagerCharacter ManagerCharacter = new ManagerCharacter();
        ManagerTeam ManagerTeam = new ManagerTeam();
        ManagerObject ManagerObject = new ManagerObject();
        ManagerCombat ManagerCombat = new ManagerCombat();

        Controller controller = new Controller(ManagerCharacter, ManagerTeam, ManagerObject, ManagerCombat);
        controller.start();

    }
}