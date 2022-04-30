package views;

import controllers.ProfileMenuController;
import enums.Command;
import models.User;

import java.util.regex.Matcher;

public class ProfileMenuView extends Processor {
    // TODO: print messages for profile menu controllerf
    public static String run(User user) {
        ProfileMenuController profileMenuController = new ProfileMenuController();
        while (true) {
            String command = Processor.getInstance().getInput();
            Matcher matcher;
            if ((matcher = Command.getMatcher(command, Command.NICKNAMECHANGE)) != null)
                profileMenuController.changeNickname(matcher, user);
            else if ((matcher = Command.getMatcher(command, Command.CHANGEPASSWORD1)) != null
                    || (matcher = Command.getMatcher(command, Command.CHANGEPASSWORD2)) != null)
                profileMenuController.changePassword(matcher, user);
            else if ((matcher = Command.getMatcher(command, Command.SHOWMENU)) != null)
                showMenu();
            else if ((matcher = Command.getMatcher(command, Command.MENUENTER)) != null)
                return matcher.group("menuname");
            else if ((matcher = Command.getMatcher(command, Command.MENUEXIT)) != null)
                return "mainMenu";
            else
                System.out.println("invalid Command!");
        }
    }

    public static void nicknameExists(String nickname) {
        System.out.format("user with nickname %s already exists\n", nickname);
    }

    public static void changed(String string) {
        System.out.format("%s changed successfully\n", string);
    }

    public static void passwordInvalid() {
        System.out.println("current password is invalid");
    }

    public static void samePassword() {
        System.out.println("please enter a new password");
    }

    public static void showMenu() {
        System.out.println("Profile Menu");
    }

    public static void menuNavigationNotPossible() {
        System.out.println("Menu navigation is not possible");
    }

}
