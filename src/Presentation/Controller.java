package src.Presentation;

import src.Bussines.*;
import src.Bussines.Character;
import src.Persistence.CharactersJsonDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase se encarga de gestionar la interacción entre el usuario y el programa,
 * actúa como intermediario entre la interfaz de usuario y la lógica de "bussines".
 */
public class Controller {

    private final UI ui = new UI();
    private final ManagerLSBRO managerLSBRO;

    /**
     * Constructor de la clase Controller
     * @param managerLSBRO es el objeto que se encarga de la lógica de negocio
     */
    public Controller(ManagerLSBRO managerLSBRO) {
        this.managerLSBRO = managerLSBRO;
    }

    /**
     * Metodo para iniciar el programa
     */
    public void start() throws IOException {
        ui.displayWelcome();
        UI.displayMessage("Verifying local files...");
        boolean menu = verifyLocalFiles();
        if (!menu) {
            UI.displayMessage("Shutting down....");
            return;
        }else{
            UI.displayMessage("Files OK." + "\n" + "Starting program...");
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

    /**
     * Metodo para verificar los archivos locales
     * @return devuelve un booleano que indica si los archivos locales se han verificado con éxito
     */
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

    /**
     * Metodo para listar personajes
     * @param managerCharacter es el objeto que se encarga de la gestión de personajes
     */
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

    /**
     * Metodo para gestionar equipos, abre el menu de gestion de equipos.
     */
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

    /**
     * Metodo para listar equipos, muestra los equipos de la base de datos
     * @param managerTeam es el objeto que se encarga de la gestión de equipos
     */
    private void listTeams(ManagerTeam managerTeam) {
        int op;
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

    /**
     * Metodo para listar objetos
     */
    private void objectsList() {
        int op;
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

    /**
     * Metodo para crear un equipo
     */
    private void createTeam() throws IOException {
        Team team = new Team("", 0, 0, 0, 0, false);
        List<Character> members = new ArrayList<>();
        int op;
        boolean found;

        UI.displayMessage("");
        String team_name = UI.askForString("Please enter the team's name: ");
        if (!managerLSBRO.getManagerTeam().existTeam(team_name)) {
            team.setName(team_name);

            for (int i = 0; i < 4; i++) {
                Character character;
                do {
                    String input = UI.askForString("\nPlease enter name or id for character #" + (i + 1) + ": ");
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
                            + "\n\t" + "1)Balanced\n");
                    op = UI.askForInteger("\tChoose an option:");
                    if (op != 1) {
                        UI.displayMessage("\tOption not valid!");
                    }
                    if (op == 1) {
                        character.setStrategy("Balanced");
                    }
                } while (op != 1);
                members.add(character);
            }
            team.setMembers(members);
            managerLSBRO.getManagerTeam().addTeam(team);
            UI.displayMessage("");
            UI.displayMessage(team.getName() + " has been successfully created!");
        } else {
            UI.displayMessage("\nWe are sorry \"" + team_name + "\" is taken.");
        }
    }

    /**
     * Metodo para eliminar un equipo
     */
    private void deleteTeam () throws IOException {
        List<Team> teams = managerLSBRO.getManagerTeam().getAllTeams();
        String teamName;
        String op;
        boolean found = false;
        do {
            teamName = UI.askForString("\n\tEnter the name of the team to remove: ");
            if (teamName.equals("exit")) {
                return;
            }
            UI.displayMessage("");
            for (Team team : teams) {
                if (team.getName().equals(teamName)) {
                    found = true;
                    op = UI.askForString("\tAre you sure you want to remove '" + team.getName() + "' ? ");
                    UI.displayMessage("");
                    if (op.equals("Yes")) {
                        UI.displayMessage("\t\"" + team.getName() + "\" has been removed from the system.");
                        teams.remove(team);
                        managerLSBRO.getManagerTeam().addTeams(teams);
                        return;
                    }
                }
            }
            if (!found) {
                UI.displayMessage("\tTeam not found, please try again. (Type 'exit' to cancel)");
            }
        } while (true);
    }

    /**
     * Metodo para simular un combate
     */
    private void runcombatSimulator () {
        UI.displayMessage("\nStarting simulation...");

        // CombatRound es una variable que almacena el resultado de la simulación de un round, tanto
        // los datos de la variable combat, como los prints que se deben mostrar en pantalla
        List<Object> combatRound;
        Combat combat = escojerEquipos();

        UI.displayMessage("");
        do {
            if (combat.getRounds() == 1) {
                UI.displayMessage("--- Round 1! ---\n");
            } else {
                UI.displayMessage("--- Round " + combat.getRounds() + " ---");
            }
            UI.showTeamStatus(combat, true);
            combatRound = managerLSBRO.simulateRound(combat);
            combat = (Combat) combatRound.get(0);
            UI.displayMessage((String) combatRound.get(1));
        } while (!combat.isFinished());
        UI.showEndCombat(combat);
    }

    /**
     * Metodo para escoger equipos para el combate
     * @return Combat
     */
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
        UI.displayMessage("\nCombat ready!");
        UI.printAndWait();
        return combat;
    }
}