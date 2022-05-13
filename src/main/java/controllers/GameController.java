package controllers;

import enums.*;
import models.*;
import views.GameView;
import java.util.regex.*;

/*********** Please read comments Before any changes ******/

public class GameController {
    // the problem with these seems to have been solved
    private static GameController instance = null;
    protected static GameView gameView = GameView.getInstance();
    protected Database database = Database.getInstance();
    protected User user = database.getCurrentPlayer();
    protected Tile[][] map = database.getMap();

    // protected Database database;
    // protected User user;
    // Tile[][] map;
    // public GameController() {
    // this.database = Database.getInstance();
    // this.user = database.getCurrentPlayer();
    // this.map = database.getMap();
    // }

    public static GameController getInstance() {
        instance = instance != null ? instance : new GameController();
        return instance;
    }

    // check methods
    protected Boolean isValidCoordinates(int i, int j) {
        return i >= 0 && j >= 0 && database.getMap().length >= i && database.getMap()[0].length >= j;
    }

    protected Boolean hasCombatUnit() {
        return database.getCurrentPlayer().getCivilization().getCurrentMilitary() != null;
    }

    protected Boolean hasNonCombatUnit() {
        return user.getCivilization().getCurrentCivilian() != null;
    }

    protected Boolean isAttackPossible(Matcher matcher) {
        int[] position = new int[2];
        position[0] = Integer.parseInt(matcher.group("positionX"));
        position[1] = Integer.parseInt(matcher.group("positionY"));
        // TODO:Attack for Archer
        return false;
    }

    public Boolean hasImprovementTech(Improvement improvement, Feature feature) {
        Civilization player = user.getCivilization();
        if (improvement.equals(Improvement.FARM)) {
            if (feature.equals(Feature.FOREST)) {
                return player.hasTechnology(Technology.MINING);
            } else if (feature.equals(Feature.JUNGLE)) {
                return player.hasTechnology(Technology.BRONZEWORKING);
            } else if (feature.equals(Feature.SWAMP)) {
                return player.hasTechnology(Technology.MASONRY);
            }
        }
        if (improvement.equals(Improvement.MINE)) {
            if (feature.equals(Feature.JUNGLE)) {
                return player.hasTechnology(Technology.BRONZEWORKING);
            } else if (feature.equals(Feature.SWAMP)) {
                return player.hasTechnology(Technology.MASONRY);
            }
        }
        return player.hasTechnology(improvement.getTechnology());
    }

    public Boolean canBuildImprovement(Improvement improvement, Tile tile) {
        for (Improvement temp : tile.getLandType().getImprovements()) {
            if (temp.equals(improvement)) {
                return true;
            }
        }
        for (Improvement temp : tile.getFeature().getImprovements()) {

            if (temp.equals(improvement)) {
                return true;
            }
        }
        return false;
    }

    // select methods
    public void selectCombatUnit(Matcher matcher) {
        int i = Integer.parseInt(matcher.group("i"));
        int j = Integer.parseInt(matcher.group("j"));
        if (!isValidCoordinates(i, j)) {
            GameView.getInstance().invalidTile();
        }
        MilitaryUnit milUnit;
        if ((milUnit = Database.getInstance().getMilitaryUnitByTile(map[i][j])) == null) {
            GameView.getInstance().invalidMilitaryUnit();
        } else if (Database.getInstance().getUnitOwner(milUnit).equals(user)) {
            GameView.getInstance().unitInaccessible();
        } else {
            user.getCivilization().setCurrentMilitary(milUnit);
            GameView.getInstance().successfullySelected();
        }
    }

    public void selectNonCombatUnit(Matcher matcher) {
        int i = Integer.parseInt(matcher.group("i"));
        int j = Integer.parseInt(matcher.group("j"));
        if (!isValidCoordinates(i, j)) {
            GameView.getInstance().invalidTile();
        }
        CivilianUnit civUnit;
        if ((civUnit = Database.getInstance().getCivilianUnitByTile(map[i][j])) == null) {
            GameView.getInstance().invalidCivilianUnit();
        } else if (Database.getInstance().getUnitOwner(civUnit).equals(user)) {
            GameView.getInstance().unitInaccessible();
        } else {
            user.getCivilization().setCurrentCivilian(civUnit);
            GameView.getInstance().successfullySelected();
        }
    }

    public void selectCityByName(Matcher matcher) {
        City city;
        String name = matcher.group("name");
        if ((city = database.getCityByName(name)) == null) {
            GameView.getInstance().invalidCity();
        } else if (!database.getCityOwner(city).equals(user)) {
            GameView.getInstance().cityInaccessible();
        } else {
            user.getCivilization().setCurrentCity(city);
        }
    }

    public void selectCityByPosition(Matcher matcher) {
        City city;
        int i = Integer.parseInt(matcher.group("i"));
        int j = Integer.parseInt(matcher.group("j"));
        if (!isValidCoordinates(i, j)) {
            GameView.getInstance().invalidTile();
        } else if ((city = database.getCityByTile(map[i][j])) == null) {
            GameView.getInstance().invalidCity();
        } else if (!database.getCityOwner(city).equals(user)) {
            GameView.getInstance().cityInaccessible();
        } else {
            user.getCivilization().setCurrentCity(city);
        }
    }

    // city methods
    public void buyTile(Matcher matcher) {
        City city;
        int price;
        int i = Integer.parseInt(matcher.group("i"));
        int j = Integer.parseInt(matcher.group("j"));
        if (!isValidCoordinates(i, j)) {
            GameView.getInstance().invalidTile();
        } else if (!map[i][j].getPlayer().equals(user)) {
            GameView.getInstance().tileNotYours();
        } else if (map[i][j].getPlayer().equals(user)) {
            GameView.getInstance().tileOwned();
        } else if ((city = Database.getInstance().getNearbyCity(map[i][j], user)) == null) {
            GameView.getInstance().noCityNearby();
        } else if (user.getCivilization().getGold() < (price = user.getCivilization().getTilePrice())) {
            GameView.getInstance().goldLow();
        } else {
            user.getCivilization().setGold(user.getCivilization().getGold() - price);
            city.addTile(map[i][j]);
            user.getCivilization().updateTileStates(null, map[i][j]);
            user.getCivilization().setTilePrice(price * 8);
        }
    }

    public void addGold(Matcher matcher) {
        int amount = Integer.parseInt(matcher.group("amount"));
        Civilization civilization = database.getCurrentPlayer().getCivilization();
        civilization.setGold(civilization.getGold() + amount);
    }

    // run
    public String run() {
        String state;
        while (true) {
            state = GameView.getInstance().run();
            if (state.equals("exit")) {
                return "game menu";
            }
        }
    }

    // info
    public void researchInfo() {
        GameView.getInstance().PrintResearchInfo(user.getCivilization().getResearch(),
                user.getCivilization().getPossibleTechnologies(), user.getCivilization().getTechnologies());

    }
    //
    // private void unitsInfo() {
    // }
    //
    // private void citiesInfo() {
    // }
    //
    // private void diplomacyInfo() {
    // }
    //
    // private void victoryInfo() {
    // }
    //
    // private void demographicsInfo() {
    // }
    //
    // private void notificationsInfo() {
    // // print messages for civilization
    // }
    //
    // private void militaryInfo() {
    // }
    //
    // private void economicInfo() {
    // // Gold
    // // Military
    // // Wealth
    // // Compare to others
    // }
    //
    // private void diplomaticInfo() {
    // }
    //
    // private void dealsInfo() {
    // }
    //
    // these are not for info
    // private void setCurrentMilitaryUnit(Matcher matcher) {
    // }
    //
    // private void setCurrentCivilianUnit(Matcher matcher) {
    // }
    //
    // private void setCurrentCity(Matcher matcher) {
    // // choose one city to show details and build and something like this
    // }

    // private void chooseUnit() {
    // // to have some changes in unit
    // // use unit controller to apply these changes
    // // call unit controller to change in unit and pass the chosen unit
    // // check if the return string is delete then delete the unit
    // // check if it is ok to mostagher shodan or not
    // }

    // private void assignWorker() {
    // // assign a worker to a tile
    // }

    // private void chooseTile() {
    // // choose Tiles to see
    // }

    // private Tile whichTile() {
    // // for in cities and tiles to choose a tile
    // }

    // private void lost() {
    // // remove city and other changes
    // }

    // private void won() {
    // // add city and other changes
    // }

}
