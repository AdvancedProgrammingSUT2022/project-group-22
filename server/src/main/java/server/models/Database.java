package server.models;

import server.controllers.*;
import server.enums.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Database {
    private static Database instance = null;
    private ArrayList<User> users = new ArrayList<User>();
    private Tile[][] map;
    private ArrayList<User> players = new ArrayList<User>();
    private User currentPlayer;
    private ArrayList<User> loggedInUsers = new ArrayList<>();

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public Tile[][] getMap() {
        return this.map;
    }

    public synchronized void addUser(User user) {
        this.users.add(user);
    }

    public void createGame(ArrayList<User> players, int x, int y) {
        this.setPlayers(players);
        currentPlayer = this.players.get(0);
        MapController mapController = new MapController();
        this.map = new Tile[x][y];
        mapController.generateTiles(map, x, y);
        mapController.generateRivers(map, x, y);
    }

    public ArrayList<User> getPlayers() {
        return this.players;
    }

    public void setPlayers(ArrayList<User> players) {
        this.players = players;
        players.get(0).setCivilization(new Civilization(Color.BLACK));
        players.get(1).setCivilization(new Civilization(Color.BLUE));
        // for (User player : this.players) {
        // player.setCivilization(new Civilization(Color.getRandomColor()));
        // }
    }

    public User getCurrentPlayer() {
        return this.currentPlayer;
    }

    public ArrayList<User> getLoggedInUsers() {
        return loggedInUsers;
    }

    public synchronized void addLoggedInUser(User loggedInUser) {
        loggedInUsers.add(loggedInUser);
    }

    public Boolean isLoggedInUser(User user){
        if(loggedInUsers.contains(user)){
            return true;
        }
        return false;
    }

    public void nextTurn() {
        this.currentPlayer = players
                .get(players.indexOf(currentPlayer) == players.size() - 1 ? 0 : players.indexOf(currentPlayer) + 1);
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

    public User getLoggedInUserByUsername(String username) {
        for (User user : this.loggedInUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void sortPlayers() {
        // ArrayList<User> temp = new ArrayList<User>();
        // for (int j = 0; j < this.players.size(); j++) {
        // for (int i = 0; i < this.players.size(); i++) {
        // User a = this.players.get(i);
        // User b = this.players.get(i + 1);
        // if (b.getCivilization().getScore() > a.getCivilization().getScore()) {
        // temp.add(i, b);
        // temp.add(i + 1, a);
        // }
        // }
        // }
        // this.players = temp;
    }

    public void sortUsers() {
        if (this.users != null) {
            Collections.sort(this.users, new Comparator<User>() {
                @Override
                public int compare(User a, User b) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm:ss");
                    LocalDateTime ALastWin = LocalDateTime.parse(a.getLastWinTime(), formatter);
                    LocalDateTime  BLastWin= LocalDateTime.parse(b.getLastWinTime(), formatter);
                    int c1 = a.getScore() < b.getScore() ? -1 : a.getScore() == b.getScore() ? 0 : 1;
                    int c2 = ALastWin.compareTo(BLastWin);
                    int c3 = a.getNickname().compareTo(b.getNickname());
                    return (c1 != 0) ? c1 : (c2 != 0) ? c2 : c3;
                }
            });
        }
    }

    public void addTileToCity() {
    }

    public MilitaryUnit getMilitaryUnitByTile(Tile tile) {
        for (User player : this.players) {
            for (MilitaryUnit unit : player.getCivilization().getMilitaryUnits()) {
                if (unit.getPosition().equals(tile)) {
                    return unit;
                }
            }
        }
        return null;
    }

    public CivilianUnit getCivilianUnitByTile(Tile tile) {
        for (User player : this.players) {
            for (CivilianUnit unit : player.getCivilization().getCivilianUnits()) {
                if (unit.getPosition().getCoordinates()[0] == tile.getCoordinates()[0]
                        && unit.getPosition().getCoordinates()[1] == tile.getCoordinates()[1]) {
                    return unit;
                }
            }
        }
        return null;
    }

    public User getUnitOwner(Unit unit) {
        for (User player : this.players) {
            for (CivilianUnit civUnit : player.getCivilization().getCivilianUnits()) {
                if (unit.equals(civUnit)) {
                    return player;
                }
            }
            for (MilitaryUnit milUnit : player.getCivilization().getMilitaryUnits()) {
                if (unit.equals(milUnit)) {
                    return player;
                }
            }
        }
        return null;
    }

    public Tile getNeighbor(Tile tile, int side) {
        int i = tile.getCoordinates()[0];
        int j = tile.getCoordinates()[1];
        int x = map.length;
        int y = map[0].length;
        if (side == 0) {
            return i - 1 >= 0 && i - 1 < x ? map[i - 1][j] : null;
        } else if (side == 1) {
            return j % 2 == 1 ? (j + 1 >= 0 && j + 1 < y ? map[i][j + 1] : null)
                    : (i - 1 >= 0 && i - 1 < x && j + 1 >= 0 && j + 1 < y ? map[i - 1][j + 1] : null);
        } else if (side == 2) {
            return j % 2 == 1 ? (i + 1 >= 0 && i + 1 < x && j + 1 >= 0 && j + 1 < y ? map[i + 1][j + 1] : null)
                    : (j + 1 >= 0 && j + 1 < y ? map[i][j + 1] : null);
        } else if (side == 3) {
            return i + 1 >= 0 && i + 1 < x ? map[i + 1][j] : null;
        } else if (side == 4) {
            return j % 2 == 1 ? (i + 1 >= 0 && i + 1 < x && j - 1 >= 0 && j - 1 < y ? map[i + 1][j - 1] : null)
                    : (j - 1 >= 0 && j - 1 < y ? map[i][j - 1] : null);
        } else if (side == 5) {
            return j % 2 == 1 ? (j - 1 >= 0 && j - 1 < y ? map[i][j - 1] : null)
                    : (i - 1 >= 0 && i - 1 < x && j - 1 >= 0 && j - 1 < y ? map[i - 1][j - 1] : null);
        }
        return null;
    }

    public void getTilesInRange(Tile tile, int range, ArrayList<Tile> tiles) {
        if (range == 0 || tiles.contains(tile)) {
            return;
        }
        tiles.add(tile);
        for (int i = 0; i < 6; i++) {
            if (getNeighbor(tile, i) != null) {
                // if (getNeighbor(tile, i).getLandType().equals(LandType.HILL)
                // || getNeighbor(tile, i).getLandType().equals(LandType.MOUNTAIN)) {
                // tiles.add(getNeighbor(tile, i));
                // for (int j = 0; j < 6; j++) {
                // if (j != (i + 3) % 6 && getNeighbor(getNeighbor(tile, i), j) != null) {
                // getTilesInRange(getNeighbor(getNeighbor(tile, i), j), range - 1, tiles);
                // }
                // }
                // } else {
                getTilesInRange(getNeighbor(tile, i), range - 1, tiles);
                // }
            }
        }
    }

    public ArrayList<Tile> getCityCenters() {
        ArrayList<Tile> centers = new ArrayList<Tile>();
        for (User player : this.players) {
            for (City city : player.getCivilization().getCities()) {
                centers.add(city.getCenter());
            }
        }
        return centers;
    }

    public City getCityByName(String name) {
        for (User player : this.players) {
            for (City city : player.getCivilization().getCities()) {
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

    public City getNearbyCity(Tile tile, User player) {
        City city;
        for (int i = 0; i < 6; i++) {
            if ((city = getCityByTile(getNeighbor(tile, i))) != null && getCityOwner(city).equals(player)) {
                return city;
            }
        }
        return null;
    }
}