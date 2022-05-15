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

    public static GameMenuController getInstance() {
        instance = instance != null ? instance : new GameMenuController();
        return instance;
    }

    // added unit selection for easier testing, could be removed later
    public void setSettlers(User player1, User player2) {
        Tile[][] map = database.getMap();
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (!(map[i][j].getLandType().equals(LandType.MOUNTAIN)
                        || map[i][j].getLandType().equals(LandType.OCEAN)
                        || (map[i][j].getFeature() != null && map[i][j].getFeature().equals(Feature.ICE)))) {
                    tiles.add(map[i][j]);
                }
            }
        }
        CivilianUnit civUnit;
        int i = random.nextInt(tiles.size());
        player1.getCivilization().addCivilianUnit((civUnit = new CivilianUnit(UnitType.SETTLER, tiles.get(i))));
        player1.getCivilization().setCurrentCivilian(civUnit);
        System.out.println(
                "player #1 unit: " + tiles.get(i).getCoordinates()[0] + ":" + tiles.get(i).getCoordinates()[1]);
        tiles.remove(i);
        i = random.nextInt(tiles.size());
        player2.getCivilization().addCivilianUnit((civUnit = new CivilianUnit(UnitType.SETTLER, tiles.get(i))));
        player2.getCivilization().setCurrentCivilian(civUnit);
        System.out.println(
                "player #2 unit: " + tiles.get(i).getCoordinates()[0] + ":" + tiles.get(i).getCoordinates()[1]);
    }

    public void startGame(User player1, User player2) {
        ArrayList<User> players = new ArrayList<User>();
        players.add(player1);
        players.add(player2);
        database.createGame(players, 20, 42);
        setSettlers(player1, player2);
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
