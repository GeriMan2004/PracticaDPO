package src.Presentation;

import java.util.Scanner;

public class Controller {
    private UI ui = new UI();

    public void start() {

        ui.displayWelcome();
        boolean menu = true;
        while (menu) {
            CasesMenu option = ui.displayMainMenu();
            switch (option) {
                case LIST_CHARACTERS -> crearPersonaje();
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

    private void crearPersonaje() {
        ui.printAllCharacters();
        Scanner sc = new Scanner(System.in);

        int op  = ui.askForInteger("Choose an option: ", sc);



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
    }

    private void combatSimulator() {
    }

    private void createTeam() {
    }

    private void listTeams() {
    }

    private void deleteTeam() {
    }

}
