//package civilization.views;
//
//import civilization.controllers.ProfileMenuController;
//import civilization.enums.Command;
//import civilization.models.User;
//
//import java.util.regex.Matcher;
//
//public class ProfileMenuView extends Processor {
//    // TODO: print messages for profile menu controllerf
//    public static String run(User user) {
////        ProfileMenuController profileMenuController = new ProfileMenuController();
////        Matcher matcher;
////        String command;
////        while (scanner.hasNext()) {
////            command = getInput();
////            if ((matcher = getMatcher(command, Command.NICKNAMECHANGE)) != null)
////                profileMenuController.changeNickname(matcher, user);
////            else if ((matcher = getMatcher(command, Command.CHANGEPASSWORD1)) != null
////                    || (matcher = getMatcher(command, Command.CHANGEPASSWORD2)) != null)
////                profileMenuController.changePassword(matcher, user);
////            else if ((matcher = getMatcher(command, Command.SHOWMENU)) != null)
////                showCurrentMenu("profile menu");
////            else if ((matcher = getMatcher(command, Command.MENUENTER)) != null)
////                return matcher.group("menuname");
////            else if ((matcher = getMatcher(command, Command.MENUEXIT)) != null)
////                return "mainMenu";
////            else
////                System.out.println("invalid Command!");
////        }
//        return "mainMenu";
//    }
//
////    public static void nicknameExists(String nickname) {
////        System.out.format("user with nickname %s already exists\n", nickname);
////    }
//
////    public static void changed(String string) {
////        System.out.format("%s changed successfully\n", string);
////    }
//
////    public static void passwordInvalid() {
////        System.out.println("current password is invalid");
////    }
//
////    public static void samePassword() {
////        System.out.println("please enter a new password");
////    }
//
////    public static void menuNavigationNotPossible() {
////        System.out.println("Menu navigation is not possible");
////    }
//
//}
