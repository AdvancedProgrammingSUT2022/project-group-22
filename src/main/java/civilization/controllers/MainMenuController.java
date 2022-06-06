package civilization.controllers;

import civilization.models.*;
import civilization.views.ScoreboardPage;

public class MainMenuController {
    private static MainMenuController instance = null;
    private Database database = Database.getInstance();

    public static MainMenuController getInstance() {
        return instance != null ? instance : new MainMenuController();
    }

    public void showScoreboard() {
        database.sortUsers();
        for (User user : database.getUsers()) {
            ScoreboardPage.getInstance().addUser(user.getNickname(), user.getAvatarAddress(), user.getScore(),
                    user.getLastWinTime(), user.getLastActivityTime(),
                    database.getLoggedInUser() != null && database.getLoggedInUser().equals(user));
        }
        // ScoreboardPage.getInstance().addElements();
    }
}
