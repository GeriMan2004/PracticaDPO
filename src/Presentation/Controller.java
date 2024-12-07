package src.Presentation;

import src.Bussines.*;
import src.Bussines.Character;
import src.Persistence.CharactersJsonDao;

import javax.naming.ldap.Control;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {

    private UI ui = new UI();
    private Scanner sc = new Scanner(System.in);

    private ManagerCharacter managerCharacter;
    private ManagerTeam managerTeam;
    private ManagerObject managerObject;
    private ManagerCombat managerCombat;

    public Controller(ManagerCharacter managerCharacter, ManagerTeam managerTeam, ManagerObject managerObject, ManagerCombat managerCombat) {
        this.managerCharacter = managerCharacter;
        this.managerTeam = managerTeam;
        this.managerObject = managerObject;
        this.managerCombat = managerCombat;
    }

    public void start() throws IOException {
        ui.displayWelcome();
        UI.displayMessage("Verifying local files...");
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
                ui.showCharacterDetails(characters.get(op - 1), managerTeam.matchTeams(characters.get(op - 1)));
            } else if (op != 0) {
                UI.displayMessage("\nInvalid option, please enter an option between 1 and " + characters.size());
            }
        } while (op < 0 || op > characters.size());
    }

    private void manageTeams() throws IOException {
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

    private void objectsList() {
        int op = 0;
        List<Item> items = ManagerObject.uploadObjects();

        do {
            ui.printAllObjects();
            op = UI.askForInteger("\nChoose an option: ", sc);
            if (op > 0 && op <= items.size()) {
                ui.showItemsDetail(items.get(op - 1));
            } else if (op != 0) {
                UI.displayMessage("\nInvalid option, please enter an option between 1 and " + items.size());
            }
        } while (op < 0 || op > items.size());

    }

    private void combatSimulator() {
    }

    private void createTeam() throws IOException {
        Team team = new Team("", 0, 0, 0, 0, false);
        ManagerTeam managerTeam = new ManagerTeam();
        List<Character> members = new ArrayList<>();
        int op;

        String team_name = UI.askForString("Please enter the team's name: ", sc);
        team.setName(team_name);

        for (int i = 0; i < 4; i++) {
            String input = UI.askForString("Please enter name or id for character #" + (i + 1) + ": ", sc);
            Character character;
            try {
                int newID = Integer.parseInt(input);
                character = new Character(newID, ""); // Asumiendo estrategia por defecto
            } catch (NumberFormatException e) {
                List<Character> characters = CharactersJsonDao.readCharacters();
                character = new Character(0, "");
                for (Character c : characters) {
                    if (c.getName().equals(input)) {
                        character.setId(c.getId());
                    }
                    character.setName(input);
                }
            }
                do {
                    UI.displayMessage("Game strategy for character #" + (i + 1)
                            + "\n\t" + "1)Balanced");
                    op = UI.askForInteger("\tChoose an option:", sc);
                    if (op > 1 || op < 1) {
                        UI.displayMessage("\tOption not valid!");
                    }
                    switch (op) {
                        case 1:
                            character.setStrategy("Balanced");
                            break;
                    }
                } while (op != 1);

                members.add(character);
        }
            team.setMembers(members);
            team.setGames_won(1);
            team.setKO_done(0);
            team.setKO_received(0);
            team.setGames_played(1);

            UI.displayMessage(team.getName() + " has been successfully created!");
            managerTeam.addTeam(team);





    }
    private void deleteTeam () {
        int op;
        List<Team> teams = managerTeam.getAllTeams();
        do {
            ui.printAllTeams(teams);
            op = UI.askForInteger("Choose an option: ", sc);
            if (op > 0 && op <= teams.size()) {
                UI.displayMessage("Team " + teams.get(op - 1).getName() + " has been successfully deleted!");
                teams.remove(op - 1);
                try {
                    managerTeam.addTeams(teams);
                } catch (IOException e) {
                    UI.displayMessage("Error: The teams.json file can’t be accessed.");
                }
            } else if (op != 0) {
                UI.displayMessage("\nInvalid option, please enter an option between 1 and " + teams.size());
            }
        } while (op != 0);
    }
}
