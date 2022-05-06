package controllers;

import models.Civilization;
import models.Database;
import models.User;
import views.GameMenuView;

import java.util.regex.Matcher;

public class GameMenuController {
    GameController gameController = new GameController();

    public String run() {
        String state = GameMenuView.getInstance().run();
        return state;
    }

    public boolean playGame(Matcher matcher) {
        if (!doesPlayerExists(matcher.group("username1").trim())) {
            GameMenuView.getInstance().noUserExists(1);
            return false;
        }
        if (!doesPlayerExists(matcher.group("username2").trim())) {
            GameMenuView.getInstance().noUserExists(2);
            return false;
        }
        addPlayer(matcher.group("username1"));
        addPlayer(matcher.group("username2"));
        GameMenuView.getInstance().gameStarted();
        return true;
    }

    private boolean doesPlayerExists(String username) {
        return Database.getInstance().getUserByUsername(username) != null;
    }

    private void addPlayer(String username) {
        User user;
        user = Database.getInstance().getUserByUsername(username);
        Database.getInstance().addPlayer(user);
        user.setCivilization(new Civilization());
    }

    // return to controller
//    private void startGame() {
//        while (true) {
//            for (int i = 0; i < Database.getInstance().getPlayers().size(); i++) {
//                Database.getInstance().setCurrentPlayer(Database.getInstance().getPlayers().get(i));
//                GameController.getInstance().run();
//            }
//        }
//    }
    // if(!isPlayerExists(matcher.group("username1"))) {
    // GameMenuView.noUserExists(1);
    // return false;
    // }
    // if(!isPlayerExists(matcher.group("username2"))){
    // GameMenuView.noUserExists(2);
    // return false;
    // }
    // addPlayer(matcher.group("username1"));
    // addPlayer(matcher.group("username2"));

}
