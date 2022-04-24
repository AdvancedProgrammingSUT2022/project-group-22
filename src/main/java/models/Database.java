package models;

import java.util.*;
import controllers.*;

public class Database {
    private static final Database instance = new Database();
    private ArrayList<User> users = new ArrayList<User>();
    private Tile[][] map;
    private Boolean[][] rivers;
    private String state = "register";

    private GameController gameController;
    private MapController mapController;
    private ArrayList<Player> players;
    private Player currentPlayer;

    public static Database getInstance() {
        return instance;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void createGame(ArrayList<Player> players, int x, int y) {
        this.players = players;
        currentPlayer = this.players.get(0);
        gameController = new GameController();
        mapController = new MapController();
        map = mapController.generateTiles(x, y);
        rivers = mapController.generateRivers(map, x, y);
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public GameController getGame() {
        return this.gameController;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void nextTurn() {
        Player nextPlayer = players.get(players.indexOf(currentPlayer) + 1);
        currentPlayer = nextPlayer != null ? nextPlayer : this.players.get(0);
    }

    public User getUserByUsername(String username) {
        for (User user : this.users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByNickname(String nickname) {
        for (User user : this.users) {
            if (user.getNickname().equals(nickname)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByPLayer() {
    }

    public void sortPlayers() {
    }

<<<<<<< HEAD
//    public void addTile() {
//        tiles.add(new Tile(GenerateMap.createCoordinate(), GenerateMap.createCoordinate(), GenerateMap.landType(),
//                GenerateMap.landFeature(), GenerateMap.resource()));
//    }
=======
    public void addTileToCity() {
    }

    public void getMilitaryUnitByTile() {
    }

    public void getCivilianUnitByTile() {
    }
>>>>>>> 23130f893eb72565a7024e7cd366b4e766a7fe1d
}
