package src.Presentation;

import src.Bussines.Character;
import src.Bussines.ManagerCharacter;
import src.Bussines.ManagerCombat;
import src.Bussines.ManagerObject;
import src.Bussines.ManagerTeam;

import java.util.List;
import java.util.Scanner;

public class Controller {

    private UI ui= new UI();
    private Scanner sc = new Scanner(System.in);

    private ManagerCharacter managerCharacter;
    private ManagerTeam managerTeam ;
    private ManagerObject managerObject;
    private ManagerCombat managerCombat;

    public Controller (ManagerCharacter managerCharacter, ManagerTeam managerTeam, ManagerObject managerObject, ManagerCombat managerCombat) {
        this.managerCharacter = managerCharacter;
        this.managerTeam = managerTeam;
        this.managerObject = managerObject;
        this.managerCombat = managerCombat;
    }

    public void start() {

        ui.displayWelcome();
        boolean menu = true;
        while (menu) {
            CasesMenu option = ui.displayMainMenu();
            switch (option) {
                case LIST_CHARACTERS -> listarPersonaje(managerCharacter);
                case MANAGE_TEAMS -> manageTeams();
                case LIST_ITEMS -> objectsList();
                case COMBAT_SIMULATOR -> combatSimulator();
                case EXIT_MAIN_MENU -> {
                    ui.displayExit();
                    menu = false;
                }
            }
        }
    }

    private void listarPersonaje(ManagerCharacter managerCharacter) {

        int op;

        List<Character> characters = managerCharacter.UploadCharacters();

        do {
            ui.printAllCharacters(characters);
            op = ui.askForInteger("Choose an option: ", sc);
            if (op > 0 && op < characters.size()) {

                ui.showCharacterDetails(characters.get(op - 1));
            }

        } while (op < 0 && op > characters.size());


    }

    private void manageTeams() {
        boolean menu = true;
        while (menu) {
            ui.displayManageTeamsMenu();
            CasesMenu option = ui.displayManageTeamsMenu();
            switch (option) {
                case CREATE_TEAM -> createTeam();
                case LIST_TEAMS -> listTeams();
                case DELETE_TEAM -> deleteTeam();
                case EXIT_TEAMS -> menu = false;
            }
        }
    }

    private void objectsList() {
        ui.printAllObjects();
        int op  = ui.askForInteger("Choose an option: ", sc);
    }
    private void listTeams() {

    }
    private void combatSimulator() {
    }

    private void createTeam() {
    }



    private void deleteTeam() {
    }

}
