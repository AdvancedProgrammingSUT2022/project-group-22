package controllers;

import enums.Command;
import models.*;
import views.GameView;

import java.util.regex.Matcher;

/*********** Please read comments Before any changes ******/

public class GameController {
    /********** these variables have some problem, believe me *********/
    private static GameController instance = null;
    protected GameView gameView = GameView.getInstance();
//    private MapController mapController = MapController.getInstance();
    protected Database database ;
    protected User user;
    Tile[][] map;


    /******** I swear these two will face NullPointerException, I moved them in print map line 123 *******/

//    CivilianUnit civUnit = user.getCivilization().getCurrentCivilian();
//    MilitaryUnit milUnit = user.getCivilization().getCurrentMilitary();
    CivilianUnit civUnit;
    MilitaryUnit milUnit;
    private final int MaxMap = 10;

    public GameController(){
        this.user = Database.getInstance().getCurrentPlayer();
        this.database = Database.getInstance();
        this.map = database.getMap();
    }

    public static GameController getInstance() {
        instance = instance != null ? instance : new GameController();
        return instance;
    }

    /***********these are not for printing map***************/

    private boolean hasChosenUnit(User player){
        return player.getCivilization().getCurrentMilitary() != null;
    }

    private boolean isAttackPossible(Matcher matcher){
        int[] position = new int[2];
        position[0] = Integer.parseInt(matcher.group("positionX"));
        position[1] = Integer.parseInt(matcher.group("positionY"));
        // TODO:Attack for Archer
        return false;
    }

    public void Attack(Matcher matcher){
        if(!hasChosenUnit(user)) GameView.getInstance().hasNotChoseAUnit();
        if(isAttackPossible(matcher)) GameView.getInstance().AttackImpossible();
    }

    public void selectCityByName(Matcher matcher){
        City city;
        String name = matcher.group("name");
            if ((city = database.getCityByName(name)) == null) {
                GameView.getInstance().invalidCity();
            } else if (!database.getCityOwner(city).getNickname().equals(user.getNickname())) {
                GameView.getInstance().notCityOwner();
            } else {
                user.getCivilization().setCurrentCity(city);
                GameView.getInstance().successfullySelected();
            }
    }

    public void selectCityByCoordinate( Matcher matcher){
        City city;
        int i = Integer.parseInt(matcher.group("i"));
        int j = Integer.parseInt(matcher.group("j"));
        if (i < 0 || j < 0 || map.length < i || map[0].length < j) {
            GameView.getInstance().invalidTile();
        } else if ((city = database.getCityByTile(map[i][j])) == null) {
            GameView.getInstance().invalidCity();
        } else if (!database.getCityOwner(city).getNickname().equals(user.getNickname())) {
            GameView.getInstance().notCityOwner();
        } else {
            user.getCivilization().setCurrentCity(city);
            GameView.getInstance().successfullySelected();
        }
    }

    public void selectUnitCombat(Matcher matcher) {
        int[] position = new int[2];
        if (!isCoordinateCorrect(matcher))
            GameView.getInstance().outOfMap();
        position[0] = Integer.parseInt(matcher.group("positionX"));
        position[1] = Integer.parseInt(matcher.group("positionY"));

        if (!map[position[0]][position[1]].getPlayer().getNickname().equals(user.getNickname()))
            GameView.getInstance().tileHasOwner();

        if (map[position[0]][position[1]].getGarrisonUnit() != null) {
            user.getCivilization().setCurrentMilitary(map[position[1]][position[1]].getGarrisonUnit());
            GameView.getInstance().successfullySelected();
        } else
            GameView.getInstance().noMilitaryUnitHere();
    }

    public void selectUnitNonCombat(Matcher matcher) {
        int[] position = new int[2];
        if (!isCoordinateCorrect(matcher))
            GameView.getInstance().outOfMap();
        position[0] = Integer.parseInt(matcher.group("positionX"));
        position[1] = Integer.parseInt(matcher.group("positionY"));
        if (!map[position[0]][position[1]].getPlayer().getNickname().equals(user.getNickname()))
            GameView.getInstance().accessTileError();
        if (map[position[0]][position[1]].getWorkerUnit() != null) {
            user.getCivilization().setCurrentCivilian(map[position[0]][position[1]].getWorkerUnit());
        }
        GameView.getInstance().successfullySelected();
    }

    private boolean isCoordinateCorrect(Matcher matcher) {
        int[] position = new int[2];
        position[0] = Integer.parseInt(matcher.group("positionX"));
        position[1] = Integer.parseInt(matcher.group("positionY"));
        if (!(position[0] < MaxMap && position[1] < MaxMap))
            return false;
        return true;
    }

        // if(player.getCurrentMilitary().getUnitType().name().equals(UnitType.ARCHER.name()))
        // UnitController.getInstance().
//    }


    /********  Please write functions that are related to printing map here  **************/
    public void printMap(Matcher matcher, Command command) {
        civUnit = user.getCivilization().getCurrentCivilian();
        milUnit = user.getCivilization().getCurrentMilitary();

        if (command.equals(Command.PRINTAREA)) {
            int i1 = Integer.parseInt(matcher.group("i1"));
            int j1 = Integer.parseInt(matcher.group("j1"));
            int i2 = Integer.parseInt(matcher.group("i2"));
            int j2 = Integer.parseInt(matcher.group("j2"));
            if (i1 < 0 || j1 < 0 || i2 < 0 || j2 < 0 || map.length < i1 || map.length < i2 || map[0].length < j1
                    || map[0].length < j2) {
                gameView.invalidTile();
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
                gameView.invalidTile();
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

    private void nextTurn() {
        // restore city + unit in combat
    }
    /******** run method **********/
    public String run() {
        while (true){
            Database.getInstance().setCurrentPlayer(Database.getInstance().getPlayers().get(0));
            this.user = Database.getInstance().getCurrentPlayer();
            String state = GameView.getInstance().run();
            for(int i = 0 ; i < Database.getInstance().getPlayers().size() ; i++){
                this.user = Database.getInstance().getCurrentPlayer();
            }
            if(state.equals("exit")) return "gameMenu";
        }
    }

    /*******  these functions are for info  ********/

//    private void researchInfo() {
//    }
//
//    private void unitsInfo() {
//    }
//
//    private void citiesInfo() {
//    }
//
//    private void diplomacyInfo() {
//    }
//
//    private void victoryInfo() {
//    }
//
//    private void demographicsInfo() {
//    }
//
//    private void notificationsInfo() {
//        // print messages for civilization
//    }
//
//    private void militaryInfo() {
//    }
//
//    private void economicInfo() {
//        // Gold
//        // Military
//        // Wealth
//        // Compare to others
//    }
//
//    private void diplomaticInfo() {
//    }
//
//    private void dealsInfo() {
//    }
//
    //these are not for info
    //    private void setCurrentMilitaryUnit(Matcher matcher) {
//    }
//
//    private void setCurrentCivilianUnit(Matcher matcher) {
//    }
//
//    private void setCurrentCity(Matcher matcher) {
//        // choose one city to show details and build and something like this
//    }

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
