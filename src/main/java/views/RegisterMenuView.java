package views;

import controllers.RegisterMenuController;
import enums.Commands;

import java.util.regex.Matcher;

public class RegisterMenuView {
    //TODO: print messages for register menu controller
    public static Matcher run(){
        RegisterMenuController registerMenuController = new RegisterMenuController();
        while(true){
            String command = Processor.getInstance().getInput();
            Matcher matcher;
            if(( matcher = Commands.getMatcher(command,Commands.CREATEUSER1)) != null
             || (matcher = Commands.getMatcher(command,Commands.CREATEUSER2)) != null
             || (matcher = Commands.getMatcher(command,Commands.CREATEUSER3)) != null
             || (matcher = Commands.getMatcher(command,Commands.CREATEUSER4)) != null
             || (matcher = Commands.getMatcher(command,Commands.CREATEUSER5)) != null
             || (matcher = Commands.getMatcher(command,Commands.CREATEUSER6)) != null)
                registerMenuController.createUser(matcher);
            else if((matcher = Commands.getMatcher(command,Commands.LOGIN1)) != null
            || (matcher = Commands.getMatcher(command,Commands.LOGIN2)) != null) {
                if (registerMenuController.login(matcher)) {
                    loggedIn();
                    return matcher;
                }
            }else if((matcher = Commands.getMatcher(command,Commands.SHOWMENU)) != null) showMenu();
            else System.out.println("invalid command!");

        }
    }

    public static void accountExists(String username){
        System.out.format("user with username %s already exists\n",username);
    }

    public static void nicknameExists(String nickname){
        System.out.format("user with nickname %s already exists\n",nickname);
    }

    public static void userCreated(){
        System.out.println("user created successfully");
    }

    public static void navigationNotPossible(){
        System.out.println("menu navigation is not possible");
    }

    public static void showMenu(){
        System.out.println("Login Menu");
    }

    public static void accountDoesNotExists(){
        System.out.println("username and password didn't match");
    }

    public static void loggedIn(){
        System.out.println("user logged in successfully");
    }



}
