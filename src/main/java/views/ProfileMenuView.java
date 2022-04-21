package views;

import controllers.ProfileMenuController;
import enums.Commands;

import java.util.regex.Matcher;

public class ProfileMenuView extends Processor{
    //TODO: print messages for profile menu controllerf
    public static String run(){
        ProfileMenuController profileMenuController = new ProfileMenuController();
        while (true){
            String command = Processor.getInstance().getInput();
            Matcher matcher;
            if((matcher = Commands.getMatcher(command,Commands.NICKNAMECHANGE)) != null) profileMenuController.changeNickname(matcher);
            else if((matcher =Commands.getMatcher(command,Commands.CHANGEPASSWORD1)) != null
                   ||(matcher = Commands.getMatcher(command,Commands.CHANGEPASSWORD2)) != null) profileMenuController.changePassword(matcher);
        }
    }

    public static void nicknameExists(String nickname){
        System.out.format("user with nickname %s already exists\n", nickname);
    }

    public static void changed(String string){
        System.out.format("%s changed successfully\n",string);
    }

    public static void passwordInvalid(){
        System.out.println("current password is invalid");
    }

    public static void samePassword(){
        System.out.println("please enter a new password");
    }


}
