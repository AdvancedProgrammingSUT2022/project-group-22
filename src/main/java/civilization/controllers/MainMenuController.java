package civilization.controllers;

<<<<<<< Updated upstream:src/main/java/controllers/MainMenuController.java
import models.*;
=======
import civilization.models.User;
import civilization.views.MainMenuView;

import java.util.regex.*;
>>>>>>> Stashed changes:src/main/java/civilization/controllers/MainMenuController.java

public class MainMenuController {
    private static MainMenuController instance = null;

    public static MainMenuController getInstance() {
        instance = instance == null ? new MainMenuController() : instance;
        return instance;
    }

    public Boolean isLoggedIn() {
        return Database.getInstance().getCurrentPlayer() != null;
    }
}
