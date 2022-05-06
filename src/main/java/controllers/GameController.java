package controllers;


import enums.Command;
import models.*;
import views.GameView;

import java.util.regex.Matcher;

public class GameController {

    private static GameController instance = null;

    protected Database database = Database.getInstance();

    protected User user = database.getCurrentPlayer();

    Tile[][] map = database.getMap();
//    CivilianUnit civUnit = user.getCivilization().getCurrentCivilian();
//    MilitaryUnit milUnit = civilization.getCurrentMilitary();
    CivilianUnit civUnit;
    MilitaryUnit milUnit;
    private final int MaxMap = 10;

    public static GameController getInstance() {
        instance = instance != null ? instance : new GameController();
        return instance;
    }


    private boolean hasChosenUnit(User player){
        return player.getCivilization().getCurrentMilitary() != null;
    }

    private boolean isAttackPossible(Matcher matcher, Civilization civilization){
        int[] position = new int[2];
        position[0] = Integer.parseInt(matcher.group("positionX"));
        position[1] = Integer.parseInt(matcher.group("positionY"));
        //TODO:Attack for Archer
        return false;
//        if(civilization.getCurrentMilitary().getUnitType().name().equals(UnitType.ARCHER.name())) UnitController.getInstance().
    }

    public String Attack(Matcher matcher){
        if(!hasChosenUnit(user)) return "Please choose a militaryUnit First";
        if(isAttackPossible(matcher, user.getCivilization())) return "Attack is not Possible";
        return null;
    }

    public String selectCity(Matcher matcher, String howToGet){
        if(howToGet.equals("name")) return selectCityByName(user.getCivilization(), matcher);
        else return selectCityByCoordinate(user.getCivilization(), matcher);
    }

    private String selectCityByCoordinate(Civilization civilization, Matcher matcher){
        if(!isCoordinateCorrect(matcher)) return "Coordinate is out of map";
        int[] position = new int[2];
        position[0] = Integer.parseInt(matcher.group("positionX"));
        position[1] = Integer.parseInt(matcher.group("positionY"));
        if (!whoseTile(position, user)) return "you don't have access to this tile";
        //TODO: how to select city must have some changes
        return "This city selected as the current city";
    }

    private String selectCityByName(Civilization civilization, Matcher matcher){
        for(int i = 0; i < civilization.getCities().size() ; i++){
            if(civilization.getCities().get(i).getName().equals(matcher.group("name"))){
                civilization.setCurrentCity(civilization.getCities().get(i));
                return "This city was set as the current city";
            }
        }
        return "This civilization has no city with this name";
    }

    private boolean whoseTile(int[] position, User user){
        return map[position[0]][position[1]].getPlayer().getNickname().equals(user.getNickname());
    }

    public String selectUnitCombat(Matcher matcher){
        int[] position = new int[2];
        if(!isCoordinateCorrect(matcher)) return "Coordinate is out of map";
        position[0] = Integer.parseInt(matcher.group("positionX"));
        position[1] = Integer.parseInt(matcher.group("positionY"));
        if(!whoseTile(position, user))  return "you don't have access to this tile";
        if(map[position[0]][position[1]].getGarrisonUnit() != null){
            user.getCivilization().setCurrentMilitary(map[position[1]][position[1]].getGarrisonUnit());
            return "This Military Unit selected as the current militaryUnit";
        }else return "there is no Military Unit here";
    }

    public String selectUnitNonCombat(Matcher matcher){
        int[] position = new int[2];
        if(!isCoordinateCorrect(matcher)) return "Coordinate is out of map";
        position[0] = Integer.parseInt(matcher.group("positionX"));
        position[1] = Integer.parseInt(matcher.group("positionY"));
        if(!whoseTile(position, user))  return "you don't have access to this tile";
        if(map[position[0]][position[1]].getWorkerUnit() != null){
            user.getCivilization().setCurrentCivilian(map[position[0]][position[1]].getWorkerUnit());
        }
        return "This nonCombat unit was set as the current civilian unit";
    }

    private boolean isCoordinateCorrect(Matcher matcher) {
        int[] position = new int[2];
        position[1] = Integer.parseInt(matcher.group("positionX"));
        position[2] = Integer.parseInt(matcher.group("positionY"));
        if (!(position[1] < MaxMap && position[2] < MaxMap)) return false;
        return true;
    }

//    public String sleep(){
//        if (UnitController.getInstance().sleep(civilization))
//            return "selected unit successfully sleep";
//        else return "Please select a unit first";
//    }

    private void researchInfo() {
    }

    private void unitsInfo() {
    }

    private void citiesInfo() {
    }

    private void diplomacyInfo() {
    }

    private void victoryInfo() {
    }

    private void demographicsInfo() {
    }

    private void notificationsInfo() {
        // print messages for civilization
    }

    private void militaryInfo() {
    }

    private void economicInfo() {
        // Gold
        // Military
        // Wealth
        // Compare to others
    }

    private void diplomaticInfo() {
    }

    private void dealsInfo() {
    }

    private void setCurrentMilitaryUnit(Matcher matcher) {
    }

    private void setCurrentCivilianUnit(Matcher matcher) {
    }

    private void setCurrentCity(Matcher matcher) {
        // choose one city to show details and build and something like this
    }

    public void printMap(Matcher matcher, Command command) {
        if (command.equals(Command.PRINTAREA)) {
            int i1 = Integer.parseInt(matcher.group("i1"));
            int j1 = Integer.parseInt(matcher.group("j1"));
            int i2 = Integer.parseInt(matcher.group("i2"));
            int j2 = Integer.parseInt(matcher.group("j2"));
            if (map.length < i1 || map.length < i2 || map[0].length < j1 || map[0].length < j2) {
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
            if (map.length < i || map[0].length < j) {
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

    private void nextTurn() {
        // restore city + unit in combat
    }

    public String run() {
        String state = GameView.getInstance().run();
        return state;
    }

    // private boolean isOkToMostagharShodan() {
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
