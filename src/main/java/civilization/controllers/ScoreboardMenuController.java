package civilization.controllers;

import java.util.*;

import civilization.models.*;
import civilization.views.*;
import civilization.views.components.UserView;

public class ScoreboardMenuController {
    private static ScoreboardMenuController instance = null;
    private Database database = Database.getInstance();

    public static ScoreboardMenuController getInstance() {
        return instance != null ? instance : new ScoreboardMenuController();
    }

    public ArrayList<UserView> createUserView() {
        database.sortUsers();
        List<User> users = database.getUsers().size() > 10 ? database.getUsers().subList(0, 10)
                : database.getUsers();
        ArrayList<UserView> scoreboard = new ArrayList<UserView>();
        for (int i = 9; i >= 0; i--) {
            User user = users.get(i);
            scoreboard.add(new UserView(user.getUsername(), user.getNickname(), user.getAvatar(),
                    user.getScore(), user.getLastWinTime(), user.getLastLoginTime(),
                    database.getLoggedInUser() != null && database.getLoggedInUser().equals(user)));
        }
        return scoreboard;
    }
}
