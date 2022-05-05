package views;

import controllers.MainMenuController;
import enums.Command;

import java.util.regex.Matcher;

public class MainMenuView extends Processor {
    // TODO: print messages for main menu controller
    public static String run() {
        MainMenuController mainMenuController = new MainMenuController();
        while (true) {
            String command = getInput();
            Matcher matcher;
            if ((matcher = getMatcher(command, Command.MENUENTER)) != null)
                return matcher.group("menuname");
            else if ((matcher = getMatcher(command, Command.MENUEXIT)) != null)
                return "Exit";
            else if ((matcher = getMatcher(command, Command.LOGOUT)) != null) {
                loggedOut();
                return "logout";
            } else if ((matcher = getMatcher(command, Command.SHOWMENU)) != null)
                showMenu();
            else
                System.out.println("invalid command!");
        }

    }

    public static void menuNavigationNotPossible() {
        System.out.println("menu navigation is not possible");
    }

    public static void showMenu() {
        System.out.println("Main Menu");
    }

    public static void loggedOut() {
        System.out.println("user logged out successfully");
    }
}
