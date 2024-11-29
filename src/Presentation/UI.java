package src.Presentation;

import src.Bussines.Character;
import src.Bussines.Item;
import src.Bussines.Member;
import src.Bussines.Team;
import src.Persistence.CharactersJsonDao;
import src.Persistence.ObjectsJsonDao;
import src.Persistence.TeamsJsonDao;

import javax.sound.midi.MetaMessage;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UI {

    /**
     * Mensaje de bienvenida
     * @autor: Gerard Perez
     * @autor: Walter-Arnau Quintili
     */
    private static String MENSAJE_WELCOME = "  ___                      _    ___     ___         _ \n" +
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
            int option = askForInteger("\nChoose an option: ", new Scanner(System.in));
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

    public void showCharacterDetails(Character character) {

        System.out.println("\n\tID:"+character.getId()+
                "\n\tNAME:"+character.getName()+
                "\n\tWEIGHT:"+character.getWeight()+" kg");
        //función buscar equipos
    }

    private static String MENSAJE_MANAGE_TEAMS = "\nTeam management.\n" +
            "\t1) Create a Team\n" +
            "\t2) List Teams\n" +
            "\t3) Delete a Team\n\n" +
            "\t4) Back";

    public CasesMenu displayManageTeamsMenu() {
        do {
            System.out.println(MENSAJE_MANAGE_TEAMS);
            int option = askForInteger("\nChoose an option: ", new Scanner(System.in));
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

        System.out.println("\n\tTeam Name:"+team.getName()+"\n");
        for(Character character : MatchCharacters) {

            System.out.println("\t"+"Character #"+ i + ": " + character.getName()
            +"\t" + character.getStrategy().toUpperCase());
            i++;
        }

        System.out.println();
    }

    public void printAllObjects() {
        int i=1;

        ObjectsJsonDao objectsJsonDao = new ObjectsJsonDao();
        List<Item> items = objectsJsonDao.readObjects();
        
        for(Item item: items)
        {
            System.out.println(i + ")" + item.getName());
            i++;
        }
    }

    private static String MENSAJE_GOODBYE = "\nWe hope to see you again!";

    public void displayExit() {
        System.out.println(MENSAJE_GOODBYE);
    }

    public static String askForString(String message, Scanner scanner) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static int askForInteger(String message, Scanner scanner) {
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

    public static double askForDouble(String message, Scanner scanner) {
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

    public static void displayMessage(String message) {
        System.out.println(message);
    }
}
