package src.Presentation;

import src.Bussines.*;
import src.Bussines.Character;

import java.util.ArrayList;
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
        UI.displayMessage("Loading data...");
        boolean menu = verifyLocalFiles();
        if (!menu) {
            UI.displayMessage("Shutting down....");
            return;
        }
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

    private boolean verifyLocalFiles() {
        boolean menu = true;
        if (!managerCharacter.checkCharacterFile()) {
            menu = false;
            UI.displayMessage("Error: The characters.json file can’t be accessed.");
        }
        if (!managerObject.checkItemFile()) {
            menu = false;
            UI.displayMessage("Error: The objects.json file can’t be accessed.");
        }
        return menu;
    }

    private void listarPersonaje(ManagerCharacter managerCharacter) {
        int op;
        List<Character> characters = managerCharacter.UploadCharacters();
        do {
            ui.printAllCharacters(characters);
            op = UI.askForInteger("Choose an option: ", sc);
            if (op > 0 && op <= characters.size()) {
                ui.showCharacterDetails(characters.get(op - 1));
            } else if (op != 0) {
                UI.displayMessage("\nInvalid option, please enter an option between 1 and " + characters.size());
            }
        } while (op != 0);
    }

    private void manageTeams() {
        boolean menu = true;
        while (menu) {
            CasesMenu option = ui.displayManageTeamsMenu();
            switch (option) {
                case CREATE_TEAM -> createTeam();
                case LIST_TEAMS -> listTeams(managerTeam);
                case DELETE_TEAM -> deleteTeam();
                case EXIT_TEAMS -> menu = false;
                default -> UI.displayMessage("\nInvalid option, please enter an option between 1 and 4");
            }
        }
    }

    private void objectsList() {
        ui.printAllObjects();
        int op  = UI.askForInteger("Choose an option: ", sc);
    }
    private void listTeams(ManagerTeam managerTeam) {
        int op = 0;
        List<Team> teams = managerTeam.getAllTeams();
        do {
            ui.printAllTeams(teams);
            op = UI.askForInteger("Choose an option: ", sc);

            if (op > 0 && op <= teams.size()) {
                List<Character> members = teams.get(op - 1).getMembers();
                List<Character> charactersMatch = matchCharacters(members);
                ui.showTeamDetails(teams.get(op - 1), charactersMatch);
            } else if (op != 0) {
                UI.displayMessage("\nInvalid option, please enter an option between 1 and " + teams.size());
            }
        } while (op != 0);

    }

    private List<Character> matchCharacters(List<Character> members) {
        List<Character> characters = managerCharacter.UploadCharacters();
        List<Character> matchCharacters = new ArrayList<>();

        for (Character member : members) {
            for (Character character : characters) {
                if (member.getId() == character.getId()) {
                    character.setStrategy(member.getStrategy());
                    matchCharacters.add(character);
                }
            }
        }
        return matchCharacters;
    }

    private void combatSimulator() {
    }

    private void createTeam() {
    }



    private void deleteTeam() {
    }

}
