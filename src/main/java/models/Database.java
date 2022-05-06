package models;

import controllers.GameController;
import controllers.MapController;

import java.util.ArrayList;

public class Database {
    private static Database instance = null;

    private ArrayList<User> users = new ArrayList<User>();

    private Tile[][] map;

    private String state = "register";

    private GameController gameController;

    private MapController mapController;

    private ArrayList<User> players = new ArrayList<>();

    private User currentPlayer;

    public static Database getInstance() {
        instance = instance != null ? instance : new Database();
        return instance;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public Tile[][] getMap() {
        return this.map;
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

    public void createGame(ArrayList<User> players, int x, int y) {
        this.players = players;
        currentPlayer = this.players.get(0);
        gameController = new GameController();
        mapController = new MapController();
        mapController.generateMap(20, 42);
    }

    public ArrayList<User> getPlayers() {
        return this.players;
    }

    public void addPlayer(User user) {
        this.players.add(user);
    }

    public GameController getGame() {
        return this.gameController;
    }

    public User getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void setCurrentPlayer(User player) {
        currentPlayer = player;
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

    // public User getUserByPLayer(Civilization civilization) {
    // for (User user : this.users) {
    // if (user.getPlayer().equals(civilization)) {
    // return user;
    // }
    // }
    // return null;
    // }

    public void sortPlayers() {
    }

    public void addTileToCity() {
    }

    public MilitaryUnit getMilitaryUnitByTile(Tile tile) {
        for ( User user : this.players) {
            for (MilitaryUnit unit : user.getCivilization().getMilitaryUnits()) {
                if (unit.getPositon().equals(tile)) {
                    return unit;
                }
            }
        }
        return null;
    }

    public CivilianUnit getCivilianUnitByTile(Tile tile) {
        for (User user : this.players) {
            for (CivilianUnit unit : user.getCivilization().getCivilianUnits()) {
                if (unit.getPositon().equals(tile)) {
                    return unit;
                }
            }
        }
        return null;
    }

    public User getUnitOwner(Unit unit) {
        for (User user : this.players) {
            for (CivilianUnit civUnit : user.getCivilization().getCivilianUnits()) {
                if (unit.equals(civUnit)) {
                    return user;
                }
            }
            for (MilitaryUnit milUnit : user.getCivilization().getMilitaryUnits()) {
                if (unit.equals(milUnit)) {
                    return user;
                }
            }
        }
        return null;
    }

    public Tile getNeighbor(Tile tile, int side) {
        int i = tile.getCoordinates()[0];
        int j = tile.getCoordinates()[1];
        if (side == 0) {
            return map[i - 1][j];
        } else if (side == 1) {
            return j % 2 == 0 ? map[i][j + 1] : map[i - 1][j + 1];
        } else if (side == 2) {
            return j % 2 == 0 ? map[i + 1][j + 1] : map[i][j + 1];
        } else if (side == 3) {
            return map[i + 1][j];
        } else if (side == 4) {
            return j % 2 == 0 ? map[i + 1][j - 1] : map[i][j - 1];
        } else if (side == 5) {
            return j % 2 == 0 ? map[i][j - 1] : map[i - 1][j - 1];
        }
        return null;
    }

    public ArrayList<Tile> getCityCenters() {
        ArrayList<Tile> centers = new ArrayList<Tile>();
        for (User user : this.players) {
            for (City city : user.getCivilization().getCities()) {
                centers.add(city.getCenter());
            }
        }
        return centers;
    }

    public City getCityByName(String name) {
        for (User user : this.players) {
            for (City city : user.getCivilization().getCities()) {
                if (city.getName().equals(name)) {
                    return city;
                }
            }
        }
        return null;
    }

    public City getCityByTile(Tile tile) {
        for (User player : this.players) {
            for (City city : player.getCivilization().getCities()) {
                for (Tile cityTile : city.getTiles()) {
                    if (cityTile.equals(tile)) {
                        return city;
                    }
                }
            }
        }
        return null;
    }

    public User getCityOwner(City city) {
        return city.getCenter().getPlayer();
    }
}
