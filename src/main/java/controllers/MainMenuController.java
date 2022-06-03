package controllers;

import models.*;

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
