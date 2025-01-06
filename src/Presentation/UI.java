package src.Presentation;

import src.Bussines.Character;
import src.Bussines.Item;
import src.Bussines.Team;
import src.Persistence.ObjectsJsonDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UI {
    private static Scanner scanner = new Scanner(System.in);
    /**
     * Mensaje de bienvenida
     * @autor: Gerard Perez
     * @autor: Walter-Arnau Quintili
     */
    private static String MENSAJE_WELCOME =
            "  ___                      _    ___     ___         _ \n" +
            " / __|_  _ _ __  ___ _ _  | |  / __|   | _ )_ _ ___| |\n" +
            " \\__ \\ || | '_ \\/ -_) '_| | |__\\__ \\_  | _ \\ '_/ _ \\_|\n" +
            " |___/\\_,_| .__/\\___|_|   |____|___( ) |___/_| \\___(_)\n" +
            "          |_|                      |/                 \n" +
            "\nWelcome to Super LS, Bro! Simulator.\n";

    /**
     * Función que muestra el mensaje de bienvenida
     * @autor: Gerard Perez
     * @autor: Walter-Arnau Quintili
     * @return void
     */
    public void displayWelcome() {
        System.out.println(MENSAJE_WELCOME);
    }

    /**
     * String de opciones del menu principal
     * @autor: Gerard Perez
     * @autor: Walter-Arnau Quintili
     */
    private static String MENSAJE_MAIN_MENU = "\n\t1) List Characters\n" +
            "\t2) Manage Teams\n" +
            "\t3) List Items\n" +
            "\t4) Simulate Combat\n\n" +
            "\t5) Exit";

    /**
     * Función que muestra menu principal y pide al usuario que elija una opción que se usará en el controller
     * @autor: Gerard Perez
     * @autor: Walter-Arnau Quintili
     * @return 'CasesMenu' opción elegida por el usuario
     */
    public CasesMenu displayMainMenu() {
        do {
            System.out.println(MENSAJE_MAIN_MENU);
            int option = askForInteger("\nChoose an option: ");
            switch (option) {
                case 1:
                    return CasesMenu.LIST_CHARACTERS;
                case 2:
                    return CasesMenu.MANAGE_TEAMS;
                case 3:
                    return CasesMenu.LIST_ITEMS;
                case 4:
                    return CasesMenu.COMBAT_SIMULATOR;
                case 5:
                    return CasesMenu.EXIT_MAIN_MENU;
            }
        } while (true);
    }

    public void printAllCharacters(List<Character> characters) {
        int i = 1;

        for(Character character : characters) {
            System.out.println("\t"+ i + ") " + character.getName());
            i++;
        }
        System.out.println("\n\t0) Back");
    }

    public void showCharacterDetails(Character character, List<Team> matchTeams) {

        System.out.println("\n\tID:\t\t"+character.getId()+
                "\n\tNAME:\t"+character.getName()+
                "\n\tWEIGHT:\t"+character.getWeight()+" kg");
        if (!matchTeams.isEmpty()) {
            System.out.println("\n\tTEAMS:");
            for (Team team : matchTeams) {
                System.out.println("\t\t- " + team.getName());
            }
        } else {
            System.out.println("\n\tTEAMS: This character is not in any team.");
        }
    }




    private static String MENSAJE_MANAGE_TEAMS = "\nTeam management.\n" +
            "\t1) Create a Team\n" +
            "\t2) List Teams\n" +
            "\t3) Delete a Team\n\n" +
            "\t4) Back";

    public CasesMenu displayManageTeamsMenu() {
        do {
            System.out.println(MENSAJE_MANAGE_TEAMS);
            int option = askForInteger("\nChoose an option: ");
            switch (option) {
                case 1:
                    return CasesMenu.CREATE_TEAM;
                case 2:
                    return CasesMenu.LIST_TEAMS;
                case 3:
                    return CasesMenu.DELETE_TEAM;
                case 4:
                    return CasesMenu.EXIT_TEAMS;
                default:
                    System.out.println("\nInvalid option, please enter an option between 1 and 4");
            }
        } while (true);
    }

    public void printAllTeams(List<Team> teams) {
        int i = 1;

        for(Team team : teams) {
            System.out.println("\t"+ i + ") " + team.getName());
            i++;
        }
        System.out.println("\n\t0) Back");
    }

    public void showTeamDetails(Team team, List<Character> MatchCharacters) {

        int i=1;
        Float winrate;

        System.out.println("\n\tTeam Name:"+team.getName()+"\n");
        for(Character character : MatchCharacters) {

            System.out.println("\t"+"Character #"+ i + ": "+character.getName()
            +"\t("+character.getStrategy().toUpperCase()+")");
            i++;
        }

        if(team.getGames_played()==0) {
            winrate = 100.0F;
            System.out.println("\n\tCombats played: " + team.getGames_played() +
                    "\n\tCombats won:\t" + team.getGames_won() +
                    "\n\tWin rate:\t\t" + winrate + "%" +
                    "\n\tKOs done:\t\t" + team.getKO_done() +
                    "\n\tKOs received:\t" + team.getKO_received());
        }else{
            winrate = (float) (team.getGames_won()*100/team.getGames_played());
            System.out.println("\n\tCombats played: "+team.getGames_played()+
                    "\n\tCombats won:\t"+team.getGames_won()+
                    "\n\tWin rate:\t\t" + winrate  +"%"+
                    "\n\tKOs done:\t\t"+team.getKO_done()+
                    "\n\tKOs received:\t"+team.getKO_received());

            System.out.println();
        }

    }

    public void printAllObjects() {
        int i=1;

        ObjectsJsonDao objectsJsonDao = new ObjectsJsonDao();
        List<Item> items = objectsJsonDao.readObjects();
        
        for(Item item: items)
        {
            System.out.println("\t" + i + ") " + item.getName());
            i++;
        }
        System.out.println("\n\t0) Back");
    }

    public void showItemsDetail(Item item)
    {
        System.out.println("\n\tID:"+"\t"+item.getId_object()+
                "\n\tNAME:"+"\t"+item.getName()+
                "\n\tTYPE:"+"\t"+item.getObject_type()+
                "\n\tPOWER:"+"\t"+item.getPowerValue()+
                "\n\tDURABILITY:"+"\t"+item.getDurability()+
                "\n\tBROKEN:"+"\t"+item.isBroken());
    }

    public List<Team> askForTeams(List<Team> teams) {
        // User should select 2 teams
        List<Team> teamsSelected = new ArrayList<>();
        int i = 1, op = 0;
        for (Team team : teams) {
            System.out.println("\t"+ i + ") " + team.getName());
            i++;
        }
        System.out.println();
        i = 1;
        do {
            op = askForInteger("Choose team #" + i + ": ");
            if (op > 0 && op <= teams.size()) {
                teamsSelected.add(teams.get(op - 1));
                i++;
            } else if (op != 0) {
                System.out.println("\nInvalid option, please enter an option between 1 and " + teams.size());
            }
        } while (i <= 2);
        System.out.println();
        return teamsSelected;
    }

    public void teamsDetailsCombat (List<Team> teamsSelected) {
        int i = 1;
        for (Team team : teamsSelected) {
            System.out.println("\n\tTeam #"+ i + ": "+team.getName());
            for(Character character : team.getMembers()) {
                System.out.println("\t-"+character.getName());
                System.out.println("\t\tWeapon: "+character.getWeapon().getName());
                System.out.println("\t\tArmor: "+character.getArmour().getName());
            }
        }
    }

    private static String MENSAJE_GOODBYE = "\nWe hope to see you again!";

    public void displayExit() {
        System.out.println(MENSAJE_GOODBYE);
    }

    public static String askForString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static int askForInteger(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("That's not a valid integer, try again!");
            } finally {
                scanner.nextLine();
            }
        }
    }

    public static double askForDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("That's not a valid integer, try again!");
            } finally {
                scanner.nextLine();
            }
        }
    }

    public static void printAndWait() {
        System.out.print("<Press any key to continue...>");
        scanner.nextLine();
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }
}
