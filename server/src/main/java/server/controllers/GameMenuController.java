package server.controllers;

import server.enums.*;
import server.models.*;

import java.util.ArrayList;
import java.util.Random;

public class GameMenuController {
    private static GameMenuController instance = null;
    private static Database database = Database.getInstance();
    Random random = new Random();

    public static GameMenuController getInstance() {
        instance = instance != null ? instance : new GameMenuController();
        return instance;
    }

    public void startGame(ArrayList<UserView> users) {
        ArrayList<User> players = new ArrayList<User>();
        for (UserView user : users) {
            players.add(database.getUserByUsername(user.getUsername()));
        }
        database.createGame(players, 15, 20);
        // setSettlers(player1, player2);
        // GameMenuView.getInstance().gameStarted();
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
        tiles.remove(i);
        i = random.nextInt(tiles.size());
        player2.getCivilization().addCivilianUnit((civUnit = new CivilianUnit(UnitType.SETTLER, tiles.get(i))));
        player2.getCivilization().setCurrentCivilian(civUnit);
    }
}
