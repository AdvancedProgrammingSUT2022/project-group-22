package controllers;

import models.*;
import views.GameMenuView;
import enums.*;
import java.util.*;
import java.util.regex.*;

public class GameMenuController {
    private static GameMenuController instance = null;
    private static Database database = Database.getInstance();
    Random random = new Random();

//<<<<<<< HEAD
//    public String run() {
//        String state = GameMenuView.getInstance().run();
//        Database.getInstance().createGame(Database.getInstance().getPlayers(),0 , 10);
//        if(state.equals("startGame")) state = startGame();
//        return state;
//=======
    public static GameMenuController getInstance() {
        instance = instance != null ? instance : new GameMenuController();
        return instance;
//>>>>>>> a5a653a676641feffd3712a9aa9423a937f44490
    }

    public void startGame(User player1, User player2) {
        ArrayList<User> players = new ArrayList<User>();
        players.add(player1);
        players.add(player2);
        database.createGame(players, 20, 42);

        Tile tile1 = database.getMap()[random.nextInt(19)][random.nextInt(41)];
        Tile tile2 = database.getMap()[random.nextInt(19)][random.nextInt(41)];
        while (tile1.equals(tile2)) {
            tile2 = database.getMap()[random.nextInt(19)][random.nextInt(41)];
        }
        player1.getCivilization().addCivilianUnit(new CivilianUnit(UnitType.SETTLER, tile1));
        player2.getCivilization().addCivilianUnit(new CivilianUnit(UnitType.SETTLER, tile2));

        GameMenuView.getInstance().gameStarted();
    }

    public Boolean playGame(Matcher matcher) {
        User player1 = Database.getInstance().getUserByUsername(matcher.group("username1").trim());
        User player2 = Database.getInstance().getUserByUsername(matcher.group("username2").trim());
        if (player1 == null) {
            GameMenuView.getInstance().noUserExists(1);
            return false;
        } else if (player2 == null) {
            GameMenuView.getInstance().noUserExists(2);
            return false;
        } else {
            startGame(player1, player2);
            return true;
        }
    }

    public String run() {
        return GameMenuView.getInstance().run();
    }
}
