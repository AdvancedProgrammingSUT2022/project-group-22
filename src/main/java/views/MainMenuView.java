package views;

import enums.*;
import java.util.regex.*;

public class MainMenuView extends Processor {
    // TODO: print messages for main menu controller
    public static String run() {
        Matcher matcher;
        String command;
        while (true) {
            command = getInput();
            if (!scanner.hasNext()) {
                return "exit";
            } else if ((matcher = getMatcher(command, Command.MENUENTER)) != null)
                return matcher.group("menuname");
            else if ((matcher = getMatcher(command, Command.MENUEXIT)) != null)
                return "exit";
            else if ((matcher = getMatcher(command, Command.LOGOUT)) != null) {
                loggedOut();
                return "logout";
            } else if ((matcher = getMatcher(command, Command.SHOWMENU)) != null)
                showCurrentMenu("main menu");
            else
                System.out.println("invalid command!");
        }
    }

    public static void menuNavigationNotPossible() {
        System.out.println("menu navigation is not possible");
    }

    public static void loggedOut() {
        System.out.println("user logged out successfully");
    }
}
