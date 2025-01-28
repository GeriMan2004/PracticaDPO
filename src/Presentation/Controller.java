package src.Presentation;

import src.Bussines.*;
import src.Bussines.Character;
import src.Persistence.CharactersJsonDao;

import javax.naming.ldap.Control;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Controller {

    private UI ui = new UI();
    private ManagerLSBRO managerLSBRO;

    public Controller(ManagerLSBRO managerLSBRO) {
        this.managerLSBRO = managerLSBRO;
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
                case LIST_CHARACTERS -> listarPersonaje(managerLSBRO.getManagerCharacter());
                case MANAGE_TEAMS -> manageTeams();
                case LIST_ITEMS -> objectsList();
                case COMBAT_SIMULATOR -> runcombatSimulator();
                case EXIT_MAIN_MENU -> {
                    ui.displayExit();
                    menu = false;
                }
            }
        }
    }

    private boolean verifyLocalFiles() {
        boolean menu = true;
        if (!managerLSBRO.getManagerCharacter().checkCharacterFile()) {
            menu = false;
            UI.displayMessage("Error: The characters.json file can’t be accessed.");
        }
        if (!managerLSBRO.getManagerObject().checkItemFile()) {
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
            op = UI.askForInteger("Choose an option: ");
            if (op > 0 && op <= characters.size()) {
                ui.showCharacterDetails(characters.get(op - 1), managerLSBRO.getManagerTeam().matchTeams(characters.get(op - 1)));
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
                case LIST_TEAMS -> listTeams(managerLSBRO.getManagerTeam());
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
            op = UI.askForInteger("Choose an option: ");

            if (op > 0 && op <= teams.size()) {
                List<Character> members = teams.get(op - 1).getMembers();
                List<Character> charactersMatch = managerLSBRO.getManagerCharacter().matchCharacters(members);
                ui.showTeamDetails(teams.get(op - 1), charactersMatch);
            } else if (op != 0) {
                UI.displayMessage("\nInvalid option, please enter an option between 1 and " + teams.size());
            }
        } while (op != 0);

    }

    private void objectsList() {
        int op = 0;
        List<Item> items = ManagerObject.uploadObjects();

        do {
            ui.printAllObjects();
            op = UI.askForInteger("\nChoose an option: ");
            if (op > 0 && op <= items.size()) {
                ui.showItemsDetail(items.get(op - 1));
            } else if (op != 0) {
                UI.displayMessage("\nInvalid option, please enter an option between 1 and " + items.size());
            }
        } while (op < 0 || op > items.size());

    }

    private void createTeam() throws IOException {
        Team team = new Team("", 0, 0, 0, 0, false);
        List<Character> members = new ArrayList<>();
        int op;
        boolean found;

        String team_name = UI.askForString("Please enter the team's name: ");
        team.setName(team_name);

        for (int i = 0; i < 4; i++) {
            Character character;
            do {
                String input = UI.askForString("Please enter name or id for character #" + (i + 1) + ": ");
                try {
                    long newID = Long.parseLong(input);
                    character = new Character(newID, "");
                    found = managerLSBRO.getManagerTeam().existCharacter(newID, "");

                } catch (NumberFormatException e) {
                    List<Character> characters = CharactersJsonDao.readCharacters();
                    found = managerLSBRO.getManagerTeam().existCharacter(-50, input);
                    character = new Character(0, "");
                    for (Character c : characters) {
                        if (c.getName().equals(input)) {
                            character.setId(c.getId());
                        }
                        character.setName(input);
                    }
                }
                if (!found) {
                    UI.displayMessage("Character not found!, please enter again.");
                }
            } while (!found);
            do {
                UI.displayMessage("Game strategy for character #" + (i + 1)
                        + "\n\t" + "1)Balanced");
                op = UI.askForInteger("\tChoose an option:");
                if (op != 1) {
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
        managerLSBRO.getManagerTeam().addTeam(team);

        UI.displayMessage(team.getName() + " has been successfully created!");
    }

    private void deleteTeam () throws IOException {
        List<Team> teams = managerLSBRO.getManagerTeam().getAllTeams();
        String teamName = "";
        String op = "No";
        boolean found = false;
        do {
            teamName = UI.askForString("Enter the name of the team to remove: ");
            if (teamName.equals("exit")) {
                return;
            }
            for (Team team : teams) {
                if (team.getName().equals(teamName)) {
                    found = true;
                    op = UI.askForString("Are you sure you want to remove '" + team.getName() + "' ? ");
                    if (op.equals("Yes")) {
                        UI.displayMessage("'" + team.getName() + "' has been removed from the system.");
                        teams.remove(team);
                        managerLSBRO.getManagerTeam().addTeams(teams);
                        return;
                    }
                }
            }
            if (!found) {
                UI.displayMessage("Team not found, please try again. (Type 'exit' to cancel)");
            }
        } while (true);
    }

    private void runcombatSimulator () {

        UI.displayMessage("\nStarting simulation...");
        List<Object> combatRound = new ArrayList<>();
        Combat combat = escojerEquipos();
        // start the combat
        do {
            UI.displayMessage("\n--- Round " + combat.getRounds() + "! ---");
            UI.showTeamStatus(combat);
            combatRound = managerLSBRO.simulateRound(combat);
            combat = (Combat) combatRound.get(0);
            UI.displayMessage((String) combatRound.get(1));
        } while (!combat.isFinished());
        UI.showEndCombat(combat);
    }

    private Combat escojerEquipos () {
        UI.displayMessage("Looking for available teams...\n");
        List <Team> updatedTeams = new ArrayList<>();
        List <Team> teams = managerLSBRO.getManagerTeam().getAllTeams();
        List <Team> teamsSelected = ui.askForTeams(teams);
        UI.displayMessage("Initializing teams...");
        List <Character> members = managerLSBRO.getManagerCharacter().matchCharacters(teamsSelected.getFirst().getMembers());
        updatedTeams.add(managerLSBRO.getManagerTeam().updateMembers(teamsSelected.getFirst(), members));
        members = managerLSBRO.getManagerCharacter().matchCharacters(teamsSelected.get(1).getMembers());
        updatedTeams.add(managerLSBRO.getManagerTeam().updateMembers(teamsSelected.get(1), members));
        Combat combat = managerLSBRO.getManagerCombat().initCombat(updatedTeams);
        ui.teamsDetailsCombat(updatedTeams);
        UI.displayMessage("Combat ready!");
        return combat;
    }

    public static void displayMessage(String message) {UI.displayMessage(message);}
}