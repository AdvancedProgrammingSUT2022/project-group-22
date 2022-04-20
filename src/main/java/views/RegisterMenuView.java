package views;

import controllers.RegisterMenuController;
import enums.Commands;

import java.util.regex.Matcher;

public class RegisterMenuView {
    //TODO: print messages for register menu controller
    public static void run(){
        RegisterMenuController registerMenuController = new RegisterMenuController();
        while(true){
            String command = Processor.getInstance().getInput();
            Matcher matcher;
            if((matcher = Commands.getMatcher(command, Commands.CREATEUSER1)) != null
             || (matcher = Commands.getMatcher(command,Commands.CREATEUSER2)) != null
             || (matcher = Commands.getMatcher(command,Commands.CREATEUSER3)) != null
             || (matcher = Commands.getMatcher(command,Commands.CREATEUSER4)) != null
             || (matcher = Commands.getMatcher(command,Commands.CREATEUSER5)) != null
             || (matcher = Commands.getMatcher(command,Commands.CREATEUSER6)) != null)
                 if (registerMenuController.createUser(matcher)) System.out.println("");;
            if((matcher = Commands.getMatcher(command,Commands.)))
        }
    }
}
