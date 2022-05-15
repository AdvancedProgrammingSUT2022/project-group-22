package views;

import controllers.RegisterMenuController;
import enums.Command;

import java.util.regex.Matcher;

public class RegisterMenuView extends Processor {
    public static String run() {
        RegisterMenuController registerMenuController = new RegisterMenuController();
        Matcher matcher;
        String command;
        while (scanner.hasNext()) {
            command = getInput();
            if ((matcher = getMatcher(command, Command.CREATEUSER1)) != null
                    || (matcher = getMatcher(command, Command.CREATEUSER2)) != null
                    || (matcher = getMatcher(command, Command.CREATEUSER3)) != null
                    || (matcher = getMatcher(command, Command.CREATEUSER4)) != null
                    || (matcher = getMatcher(command, Command.CREATEUSER5)) != null
                    || (matcher = getMatcher(command, Command.CREATEUSER6)) != null) {
                registerMenuController.createUser(matcher);
            } else if ((matcher = getMatcher(command, Command.LOGIN1)) != null
                    || (matcher = getMatcher(command, Command.LOGIN2)) != null) {
                if (registerMenuController.canLogin(matcher)) {
                    loggedIn();
                    return matcher.group("username");
                }
            } else if ((matcher = getMatcher(command, Command.SHOWMENU)) != null) {
                showCurrentMenu("register menu");
            } else if ((matcher = getMatcher(command, Command.MENUEXIT)) != null) {
                return "exit";
            } else {
                System.out.println("invalid command!");
            }
        }
        return "exit";
    }

    public static void accountExists(String username) {
        System.out.format("user with username %s already exists\n", username);
    }

    public static void nicknameExists(String nickname) {
        System.out.format("user with nickname %s already exists\n", nickname);
    }

    public static void userCreated() {
        System.out.println("user created successfully");
    }

    public static void navigationNotPossible() {
        System.out.println("menu navigation is not possible");
    }

    public static void accountDoesNotExists() {
        System.out.println("no account with this username exists");
    }

    public static void incorrectPassword() {
        System.out.println("incorrect password");
    }

    public static void loggedIn() {
        System.out.println("user logged in successfully");
    }

}
