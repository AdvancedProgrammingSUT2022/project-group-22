package controllers;

import models.Database;
import models.User;
import views.Processor;

public class Controller {
    // private static Controller instance = new Controller();
//    protected Database database = Database.getInstance();
//    protected Processor processor = Processor.getInstance();
    private final RegisterMenuController registerMenuController = new RegisterMenuController();
    private final MainMenuController mainMenuController = new MainMenuController();
    private final ProfileMenuController profileMenuController = new ProfileMenuController();
    private final GameController gameController = new GameController();
    // private final UnitController unitController = new UnitController();
    private final GameMenuController gameMenuController = new GameMenuController();
    private User user = new User("a", "a", "a");

    // public static Controller getInstance() {
    // return instance;
    // }

    public String run() {
        String input;
        input = registerMenuController.run();
        user = Database.getInstance().getUserByUsername(input);
        input = mainMenuController.run(user);
        while (true) {
            if (input.equals("Exit"))
                break;
            else if (input.equals("mainMenu"))
                input = mainMenuController.run(user);
            else if (input.equals("gameMenu"))
                input = gameMenuController.run();
            else if (input.equals("registerMenu")) {
                input = registerMenuController.run();
                user = Database.getInstance().getUserByUsername(input);
                input = mainMenuController.run(user);
            }
            else if (input.equals("profileMenu"))
                input = profileMenuController.run(user);
        }
        Processor.getInstance().closeScanner();
        return null;
    }
}
