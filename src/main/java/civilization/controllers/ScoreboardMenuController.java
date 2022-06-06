package civilization.controllers;

import java.util.*;

import civilization.models.*;
import civilization.views.ScoreboardPage;

public class ScoreboardMenuController {
    private static ScoreboardMenuController instance = null;
    private Database database = Database.getInstance();

    public static ScoreboardMenuController getInstance() {
        return instance != null ? instance : new ScoreboardMenuController();
    }

    public void addUserData() {
        database.sortUsers();
        List<User> users = database.getUsers().size() > 10 ? database.getUsers().subList(0, 10)
                : database.getUsers();
        for (User user : users) {
            ScoreboardPage.getInstance().addUser(user.getNickname(), user.getAvatarAddress(), user.getScore(),
                    user.getLastWinTime(), user.getLastActivityTime(),
                    database.getLoggedInUser() != null && database.getLoggedInUser().equals(user));
        }
    }
}
