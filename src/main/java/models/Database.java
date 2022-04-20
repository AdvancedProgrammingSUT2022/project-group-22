package models;

import java.util.*;
import controllers.*;

public class Database {
    private static final Database instance = new Database();
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Tile> tiles = new ArrayList<>();
    private String state = "register";

    private GameController game;
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

    public void createGame(ArrayList<Player> players) {
        game = new GameController();
        this.players = players;
        currentPlayer = this.players.get(0);
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public GameController getGame() {
        return this.game;
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

    public void sortPlayers() {
    }

    public void addTile() {
        tiles.add(new Tile(GenerateMap.createCoordinate(), GenerateMap.createCoordinate(), GenerateMap.landType(),
                GenerateMap.landFeature(), GenerateMap.resource()));
    }
}
