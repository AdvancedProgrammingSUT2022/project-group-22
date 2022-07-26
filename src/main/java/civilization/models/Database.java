package civilization.models;

import civilization.controllers.*;
import civilization.enums.*;
import java.util.*;

public class Database {
    private static Database instance = null;
    private ArrayList<User> users = new ArrayList<User>();
    private Tile[][] map;
    private ArrayList<User> players = new ArrayList<User>();
    private User currentPlayer;
    private User loggedInUser = null;

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

    public void addUser(User user) {
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

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
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
                    int c1 = a.getScore() < b.getScore() ? -1 : a.getScore() == b.getScore() ? 0 : 1;
                    int c2 = a.getLastWinTime().compareTo(b.getLastWinTime());
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
        int ip = i, jp = j;
        side = (side + 36) % 6;

        if (j % 2 == 0) {
            ip = (side <= 1 || side == 5) ? i - 1 : (side == 3) ? i + 1 : i;
        } else {
            ip = (side >= 2 && side <= 4) ? i + 1 : (side == 0) ? i - 1 : i;
        }
        jp = (side == 1 || side == 2) ? j + 1 : (side >= 4) ? j - 1 : j;

        if (ip < 0 || ip >= map.length || jp < 0 || jp >= map[0].length)
            return null;
        return map[ip][jp];
    }

    public void getTilesInRange(Tile tile, int range, ArrayList<Tile> tiles) {
        if (tile == null || range < 0)
            return;
        if (!tiles.contains(tile)) {
            tiles.add(tile);
        }
        if ((tile.getLandType().equals(LandType.HILL) || tile.getLandType().equals(LandType.MOUNTAIN)) && range != 2) {
            return;
        }
        for (int i = 0; i < 6; i++) {
            getTilesInRange(getNeighbor(tile, i), range - 1, tiles);
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