package controllers;

import java.util.regex.*;

import models.*;
import views.*;

public class Controller {
    private static Controller instance = new Controller();
    protected Database database = Database.getInstance();
    protected Processor processor = Processor.getInstance();
    private RegisterMenuController registerMenuController = new RegisterMenuController();
    private MainMenuController mainMenuController = new MainMenuController();
    private ProfileMenuController profileMenuController = new ProfileMenuController();
    private GameController gameController = new GameController();
    private UnitController unitController = new UnitController();

    public static Controller getInstance() {
        return instance;
    }

    public String run() {
         String input;
         input = registerMenuController.run();
         while (true) {
         if (input.equals("Exit")) break;
         if (input.equals("mainMenu")) input = mainMenuController.run();
         if (input.equals("gameMenu")) input = gameController.run();
         if (input.equals("createGame")) input = gameController.run();
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
