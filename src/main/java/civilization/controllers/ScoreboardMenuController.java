package civilization.controllers;

import java.util.*;

import civilization.models.*;
import civilization.views.*;

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
        for (User user : users) {
            scoreboard.add(new UserView(user.getNickname(), user.getAvatarAddress(), user.getScore(),
                    user.getLastWinTime(), user.getLastActivityTime(),
                    database.getLoggedInUser() != null && database.getLoggedInUser().equals(user)));
        }
        return scoreboard;
    }
}
