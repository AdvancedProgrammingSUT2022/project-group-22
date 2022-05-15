package controllers;

import enums.*;
import models.*;
import views.GameView;
import java.util.*;
import java.util.regex.*;

/*********** Please read comments Before any changes ******/

public class GameController {
    // the problem with these seems to have been solved
    private static GameController instance = null;
    protected static GameView gameView = GameView.getInstance();
    protected Database database = Database.getInstance();

    public static GameController getInstance() {
        instance = instance != null ? instance : new GameController();
        return instance;
    }

    public void sortTilesByCoordinates(ArrayList<Tile> tiles) {
        Collections.sort(tiles, new Comparator<Tile>() {
            public int compare(Tile a, Tile b) {
                int c1 = a.getCoordinates()[0] == b.getCoordinates()[0] ? 0
                        : a.getCoordinates()[0] > b.getCoordinates()[0] ? 1 : -1;
                int c2 = a.getCoordinates()[1] == b.getCoordinates()[1] ? 0
                        : a.getCoordinates()[1] > b.getCoordinates()[1] ? 1 : -1;
                return (c1 != 0) ? c1 : c2;
            }
        });
    }

    // check methods
    protected Boolean isValidCoordinates(int i, int j) {
        return i >= 0 && j >= 0 && database.getMap().length > i && database.getMap()[0].length > j;
    }

    protected Boolean hasCombatUnit() {
        return database.getCurrentPlayer().getCivilization().getCurrentMilitary() != null;
    }

    protected Boolean hasNonCombatUnit() {
        return database.getCurrentPlayer().getCivilization().getCurrentCivilian() != null;
    }

    protected Boolean isAttackPossible(Matcher matcher) {
        int[] position = new int[2];
        position[0] = Integer.parseInt(matcher.group("positionX"));
        position[1] = Integer.parseInt(matcher.group("positionY"));
        // TODO:Attack for Archer
        return false;
    }

    // checks distance from other city centers
    public Boolean canFoundCity(Tile tile) {
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        database.getTilesInRange(tile, 4, tiles);
        ArrayList<Tile> centers = database.getCityCenters();
        for (Tile temp : tiles) {
            if (centers.indexOf(temp) != -1) {
                return false;
            }
        }
        return true;
    }

    public Boolean hasImprovementTech(Improvement improvement, Feature feature) {
        Civilization player = database.getCurrentPlayer().getCivilization();
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
        MilitaryUnit milUnit;
        if (!isValidCoordinates(i, j)) {
            gameView.invalidTile();
        } else if ((milUnit = database.getMilitaryUnitByTile(database.getMap()[i][j])) == null) {
            gameView.invalidMilitaryUnit();
        } else if (database.getUnitOwner(milUnit).equals(database.getCurrentPlayer())) {
            gameView.unitInaccessible();
        } else {
            database.getCurrentPlayer().getCivilization().setCurrentMilitary(milUnit);
            gameView.unitSelected(milUnit.getUnitType().name().toLowerCase(),
                    milUnit.getPosition().getCoordinates());
        }
    }

    public void selectNonCombatUnit(Matcher matcher) {
        int i = Integer.parseInt(matcher.group("i"));
        int j = Integer.parseInt(matcher.group("j"));
        CivilianUnit civUnit;
        if (!isValidCoordinates(i, j)) {
            gameView.invalidTile();
        } else if ((civUnit = database.getCivilianUnitByTile(database.getMap()[i][j])) == null) {
            gameView.invalidCivilianUnit();
        } else if (!database.getUnitOwner(civUnit).equals(database.getCurrentPlayer())) {
            gameView.unitInaccessible();
        } else {
            database.getCurrentPlayer().getCivilization().setCurrentCivilian(civUnit);
            gameView.unitSelected(civUnit.getUnitType().name().toLowerCase(),
                    civUnit.getPosition().getCoordinates());
        }
    }

    public void selectCityByName(Matcher matcher) {
        City city;
        String name = matcher.group("name");
        if ((city = database.getCityByName(name)) == null) {
            gameView.invalidCity();
        } else if (!database.getCityOwner(city).equals(database.getCurrentPlayer())) {
            gameView.cityInaccessible();
        } else {
            database.getCurrentPlayer().getCivilization().setCurrentCity(city);
            gameView.citySelected(city.getName());
        }
    }

    public void selectCityByPosition(Matcher matcher) {
        City city;
        int i = Integer.parseInt(matcher.group("i"));
        int j = Integer.parseInt(matcher.group("j"));
        if (!isValidCoordinates(i, j)) {
            gameView.invalidTile();
        } else if ((city = database.getCityByTile(database.getMap()[i][j])) == null) {
            gameView.invalidCity();
        } else if (!database.getCityOwner(city).equals(database.getCurrentPlayer())) {
            gameView.cityInaccessible();
        } else {
            database.getCurrentPlayer().getCivilization().setCurrentCity(city);
            gameView.citySelected(city.getName());
        }
    }

    // city methods
    public void buyTile(Matcher matcher) {
        City city;
        int price;
        int i = Integer.parseInt(matcher.group("i"));
        int j = Integer.parseInt(matcher.group("j"));
        if (!isValidCoordinates(i, j)) {
            gameView.invalidTile();
        } else if (!database.getMap()[i][j].getPlayer().equals(database.getCurrentPlayer())) {
            gameView.tileNotYours();
        } else if (database.getMap()[i][j].getPlayer().equals(database.getCurrentPlayer())) {
            gameView.tileOwned();
        } else if ((city = database.getNearbyCity(database.getMap()[i][j],
                database.getCurrentPlayer())) == null) {
            gameView.noCityNearby();
        } else if (database.getCurrentPlayer().getCivilization()
                .getGold() < (price = database.getCurrentPlayer().getCivilization().getTilePrice())) {
            gameView.goldLow();
        } else {
            database.getCurrentPlayer().getCivilization()
                    .setGold(database.getCurrentPlayer().getCivilization().getGold() - price);
            city.addTile(database.getMap()[i][j]);
            database.getCurrentPlayer().getCivilization().updateTileStates(null, database.getMap()[i][j]);
            database.getCurrentPlayer().getCivilization().setTilePrice(price * 8);
        }
    }

    public void addGold(Matcher matcher) {
        int amount = Integer.parseInt(matcher.group("amount"));
        Civilization civilization = database.getCurrentPlayer().getCivilization();
        civilization.setGold(civilization.getGold() + amount);
        gameView.goldIncreased(amount);
    }

    public void addResearch(Matcher matcher) {
        String tech = matcher.group("name").toUpperCase();
        Technology technlogy;
        if ((technlogy = Technology.matchTechnology(tech)) == null) {
            gameView.noSuchTechnology(tech);
        } else if (database.getCurrentPlayer().getCivilization().getCities().size() == 0) {
            gameView.dontHaveCity();
        } else if (database.getCurrentPlayer().getCivilization().hasTechnology(technlogy)) {
            gameView.hadTechnology();
        } else if (database.getCurrentPlayer().getCivilization().getPossibleTechnologies().indexOf(technlogy) == -1) {
            gameView.technologyInaccessible();
        } else {
            database.getCurrentPlayer().getCivilization().addResearch(technlogy);
            gameView.researchAdded();
        }
    }

    // run
    public String run() {
        String state;
        while (true) {
            state = gameView.run();
            if (state.equals("exit")) {
                return "gameMenu";
            }
        }
    }

    // info
    public void researchInfo() {
        Civilization player = database.getCurrentPlayer().getCivilization();
        gameView.PrintResearchInfo(database.getCurrentPlayer().getNickname(),
                player.getResearch(),
                player.getPossibleTechnologies(),
                player.getTechnologies());

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
