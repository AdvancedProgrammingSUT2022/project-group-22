package civilization.controllers;

import civilization.models.Database;
import civilization.models.User;
import civilization.views.Processor;

public class Controller {
    private final RegisterMenuController registerMenuController = new RegisterMenuController();
    private final MainMenuController mainMenuController = new MainMenuController();
    private final ProfileMenuController profileMenuController = new ProfileMenuController();
    private final GameMenuController gameMenuController = new GameMenuController();
    private final GameController gameController = new GameController();
    private User user = new User("a", "a", "a", "a", null);

    public void run() {
        String input;
        input = registerMenuController.run();
        if (input.equals("exit")) {
            Processor.getInstance().closeScanner();
            return;
        }
        user = Database.getInstance().getUserByUsername(input);
        input = mainMenuController.run(user);
        while (true) {
            if (input.equals("exit")) {
                break;
            } else if (input.equals("mainMenu")) {
                input = mainMenuController.run(user);
            } else if (input.equals("gameMenu")) {
                input = gameMenuController.run();
            } else if (input.equals("game")) {
                input = gameController.run();
            } else if (input.equals("registerMenu")) {
                input = registerMenuController.run();
                user = Database.getInstance().getUserByUsername(input);
                input = mainMenuController.run(user);
            } else if (input.equals("profileMenu")) {
                input = profileMenuController.run(user);
            }
        }
        Processor.getInstance().closeScanner();
        return;
    }
}
