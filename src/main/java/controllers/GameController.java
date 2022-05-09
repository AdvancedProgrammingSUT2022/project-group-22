package controllers;

import enums.*;
import models.*;
import views.GameView;
import java.util.regex.*;

/*********** Please read comments Before any changes ******/

public class GameController {
    /********** these variables have some problem *********/
    private static GameController instance = null;

    // private MapController mapController = MapController.getInstance();
    protected Database database;
    protected User user;
    Tile[][] map;

    public GameController() {
        this.database = Database.getInstance();
        this.user = database.getCurrentPlayer();
        this.map = database.getMap();
    }

    public static GameController getInstance() {
        instance = instance != null ? instance : new GameController();
        return instance;
    }

    /*********** these are not for printing map ***************/

    private boolean hasChosenCombatUnit() {
        return user.getCivilization().getCurrentMilitary() != null;
    }

    private boolean hasChosenNonCombatUnit() {
        return user.getCivilization().getCurrentCivilian() != null;
    }

    private boolean isAttackPossible(Matcher matcher) {
        int[] position = new int[2];
        position[0] = Integer.parseInt(matcher.group("positionX"));
        position[1] = Integer.parseInt(matcher.group("positionY"));
        // TODO:Attack for Archer
        return false;
    }

    public void Attack(Matcher matcher) {
        if (!hasChosenCombatUnit())
            GameView.getInstance().noUnitSelected();
        if (isAttackPossible(matcher))
            GameView.getInstance().AttackImpossible();
    }

    public void selectUnit(Matcher matcher, Command command) {
        int i = Integer.parseInt(matcher.group("i"));
        int j = Integer.parseInt(matcher.group("j"));
        if (i < 0 || j < 0 || map.length < i || map[0].length < j) {
            GameView.getInstance().invalidTile();
        }
        if (command.equals(Command.SELECTUNITCOMBAT)) {
            MilitaryUnit milUnit;
            if ((milUnit = Database.getInstance().getMilitaryUnitByTile(map[i][j])).equals(null)) {
                GameView.getInstance().invalidMilitaryUnit();
            } else if (Database.getInstance().getUnitOwner(milUnit).equals(user)) {
                GameView.getInstance().unitInaccessible();
            } else {
                user.getCivilization().setCurrentMilitary(milUnit);
                GameView.getInstance().successfullySelected();
            }
        } else {
            CivilianUnit civUnit;
            if ((civUnit = Database.getInstance().getCivilianUnitByTile(map[i][j])).equals(null)) {
                GameView.getInstance().invalidCivilianUnit();
            } else if (Database.getInstance().getUnitOwner(civUnit).equals(user)) {
                GameView.getInstance().unitInaccessible();
            } else {
                user.getCivilization().setCurrentCivilian(civUnit);
                GameView.getInstance().successfullySelected();
            }
        }
    }

    public void selectCity(Matcher matcher, Command command) {
        City city;
        if (command.equals(Command.SELECTCITYNAME)) {
            String name = matcher.group("name");
            if ((city = database.getCityByName(name)).equals(null)) {
                GameView.getInstance().invalidCity();
            } else if (!database.getCityOwner(city).equals(user)) {
                GameView.getInstance().cityInaccessible();
            } else {
                user.getCivilization().setCurrentCity(city);
            }
        } else {
            int i = Integer.parseInt(matcher.group("i"));
            int j = Integer.parseInt(matcher.group("j"));
            if (i < 0 || j < 0 || map.length < i || map[0].length < j) {
                GameView.getInstance().invalidTile();
            } else if ((city = database.getCityByTile(map[i][j])).equals(null)) {
                GameView.getInstance().invalidCity();
            } else if (!database.getCityOwner(city).equals(user)) {
                GameView.getInstance().cityInaccessible();
            } else {
                user.getCivilization().setCurrentCity(city);
            }
        }
    }

    public void sleep() {
        if (hasChosenCombatUnit()) {
            user.getCivilization().getCurrentMilitary().setSleep();
            GameView.getInstance().sleepSuccessful();
        } else if (hasChosenNonCombatUnit()) {
            user.getCivilization().getCurrentCivilian().setSleep();
            GameView.getInstance().sleepSuccessful();
        }
        GameView.getInstance().noUnitSelected();
    }

    public void alert() {
        if (hasChosenCombatUnit()) {
            user.getCivilization().getCurrentMilitary().switchAlert();
            GameView.getInstance().alertMessage();
        } else if (hasChosenNonCombatUnit()) {
            user.getCivilization().getCurrentCivilian().switchSleeping();
            GameView.getInstance().alertMessage();
        } else
            GameView.getInstance().noUnitSelected();
    }

    public void fortify() {
        if (hasChosenCombatUnit()) {
            user.getCivilization().getCurrentMilitary().fortify();
            GameView.getInstance().successfulFortify();
        } else
            GameView.getInstance().noUnitSelected();
    }

    public void wake() {
        if (hasChosenNonCombatUnit()) {
            user.getCivilization().getCurrentCivilian().switchSleeping();
            GameView.getInstance().wakeMessage();
        } else if (hasChosenCombatUnit()) {
            user.getCivilization().getCurrentMilitary().setSleep();
            GameView.getInstance().wakeMessage();
        } else
            GameView.getInstance().noUnitSelected();
    }

    public void garrison() {
        if (hasChosenCombatUnit()) {
            user.getCivilization().getCurrentMilitary().getPositon()
                    .setGarrisonUnit(user.getCivilization().getCurrentMilitary());
            GameView.getInstance().garrisonMessage();
        } else
            GameView.getInstance().noUnitSelected();
    }

    /******
     * This function will build a road in a tile, Check if worker is selected or not
     ************/
    public void buildRoad() {
        // if(user.getCivilization().)
        // else GameView.getInstance().noUnitSelected();
    }

    private void buildRoadIn0(int i) {
        Boolean[] hasRoad = user.getCivilization().getCurrentMilitary().getPositon().getHasRoad();
        if (hasRoad[0]) {
            user.getCivilization().getCurrentMilitary().getPositon().setHasRoad(0, true);
            GameView.getInstance().buildRoadSuccessful();
        }
    }

    // map printing
    public void printMap(Matcher matcher, Command command) {
        CivilianUnit civUnit = user.getCivilization().getCurrentCivilian();
        MilitaryUnit milUnit = user.getCivilization().getCurrentMilitary();
        if (command.equals(Command.PRINTAREA)) {
            int i1 = Integer.parseInt(matcher.group("i1"));
            int j1 = Integer.parseInt(matcher.group("j1"));
            int i2 = Integer.parseInt(matcher.group("i2"));
            int j2 = Integer.parseInt(matcher.group("j2"));
            // test
            if (map == null) {
                System.out.println("map is null");
                return;
            }
            //
            if (i1 < 0 || j1 < 0 || i2 < 0 || j2 < 0 || map.length < i1 || map.length < i2 ||
                    map[0].length < j1 || map[0].length < j2) {
                GameView.getInstance().invalidTile();
                return;
            } else {
                MapController.getInstance().printArea(map, i1, j1, i2, j2);
            }
        } else if (command.equals(Command.PRINTCITY)) {
            String name = matcher.group("name");
            City city;
            if ((city = database.getCityByName(name)) == null) {
                GameView.getInstance().invalidCity();
            } else {
                MapController.getInstance().printCity(city);
            }
        } else if (command.equals(Command.PRINTTILE)) {
            int i = Integer.parseInt(matcher.group("i"));
            int j = Integer.parseInt(matcher.group("j"));
            if (i < 0 || j < 0 || map.length < i || map[0].length < j) {
                GameView.getInstance().invalidTile();
                return;
            } else {
                MapController.getInstance().printTile(map[i][j]);
            }
        } else {
            Unit unit = civUnit != null ? civUnit : milUnit;
            if (unit == null) {
                GameView.getInstance().noUnitSelected();
                return;
            } else {
                MapController.getInstance().printTile(unit.getPositon());
            }
        }
    }

    private void moveMap(Matcher matcher) {
    }

    public void unitCombatPosition(Matcher matcher) {
    }

    public void nextTurn() {
        Database.getInstance().nextTurn();
        // restore city + unit in combat
    }

    public void buildRailRoad() {
    }

    /******** run method **********/
    public String run() {
        String state;
        while (true) {
            state = GameView.getInstance().run();
            if (state.equals("exit")) {
                return state;
            }
        }
    }

    /******* these functions are for info ********/

    // private void researchInfo() {
    // }
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
