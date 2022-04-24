package controllers;

import java.util.regex.*;

import models.*;
import views.*;

public class Controller {
//    private static Controller instance = new Controller();
    protected Database database = Database.getInstance();
    protected Processor processor = Processor.getInstance();
    private final RegisterMenuController registerMenuController = new RegisterMenuController();
    private final MainMenuController mainMenuController = new MainMenuController();
    private final ProfileMenuController profileMenuController = new ProfileMenuController();
    private final GameController gameController = new GameController();
    private final UnitController unitController = new UnitController();
    private final GameMenuController gameMenuController = new GameMenuController();
    private User user = new User("a","a","a");

//    public static Controller getInstance() {
//        return instance;
//    }

    public String run() {
         String input;
         input = registerMenuController.run();
         user = Database.getInstance().getUserByUsername(input);
         input = mainMenuController.run(user);
         while (true) {
         if (input.equals("Exit")) break;
         if (input.equals("mainMenu")) input = mainMenuController.run(user);
         if (input.equals("gameMenu")) input = gameMenuController.run();
//         if (input.equals("createGame")) input = gameMenuController.run();
         if(input.equals("registerMenu")){
             input = registerMenuController.run();
             user = Database.getInstance().getUserByUsername(input);
             input = mainMenuController.run(user);
         }
         if(input.equals("profileMenu")) input = profileMenuController.run(user);
         }
         processor.closeScanner();
         return null;
    }

    protected Matcher getMatcher(String input, String regex) {
        regex = "^\\s*" + regex.replace(" ", "\\s+") + "\\s*$";
        Matcher matcher = Pattern.compile(regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
