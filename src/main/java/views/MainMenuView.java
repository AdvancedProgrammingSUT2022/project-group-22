package views;

import controllers.MainMenuController;
import enums.Commands;

import java.util.regex.Matcher;

public class MainMenuView extends Processor{
    //TODO: print messages for main menu controller
    public static String run(){
        MainMenuController mainMenuController = new MainMenuController();
        while(true){
            String command = Processor.getInstance().getInput();
            Matcher matcher;
            if((matcher = Commands.getMatcher(command, Commands.MENUENTER)) != null) return matcher.group("menuname");
            else if((matcher = Commands.getMatcher(command,Commands.MENUEXIT)) != null) return "Exit";
            else if((matcher = Commands.getMatcher(command,Commands.LOGOUT)) != null) {
                loggedOut();
                return "logout";
            }else if((matcher = Commands.getMatcher(command, Commands.SHOWMENU)) != null) showMenu();
            else System.out.println("invalid command!");
        }

    }

    public static void menuNavigationNotPossible(){
        System.out.println("menu navigation is not possible");
    }

    public static void showMenu(){
        System.out.println("Main Menu");
    }

    public static void loggedOut(){
        System.out.println("user logged out successfully");
    }
}
