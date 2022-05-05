package controllers;

import models.Database;
import models.Player;
import models.Tile;

import java.util.regex.*;

public class GameController {
    final int MaxMap = 10;
    Tile[][]  tiles = Database.getInstance().getMap();

    public String selectCity(Player player, Matcher matcher, String howToGet){
        if(howToGet.equals("name")) return selectCityByName(player, matcher);
        else return selectCityByCoordinate(player, matcher);
    }

    private String selectCityByCoordinate(Player player, Matcher matcher){
        if(!isCoordinateCorrect(matcher)) return "Coordinate is out of map";
        int[] position = new int[2];
        position[1] = Integer.parseInt(matcher.group("positionX"));
        position[2] = Integer.parseInt(matcher.group("positionY"));
        if (!whoseTile(position, player)) return "you don't have access to this tile";
        //TODO: how to select city must have some changes
        return "This city selected as the current city";
    }

    private String selectCityByName(Player player, Matcher matcher){
        for(int i = 0 ; i < player.getCities().size() ; i++){
            if(player.getCities().get(i).getName().equals(matcher.group("name"))){
                player.setCurrentCity(player.getCities().get(i));
                return "This city was set as the current city";
            }
        }
        return "This player has no city with this name";
    }

    private boolean whoseTile(int[] position, Player player){
        if(tiles[position[1]][position[2]].getPlayer().getNickname().equals(player.getNickname())) return true;
        return false;
    }

    public String selectUnitCombat(Player player, Matcher matcher){
        int[] position = new int[2];
        if(!isCoordinateCorrect(matcher)) return "Coordinate is out of map";
        position[1] = Integer.parseInt(matcher.group("positionX"));
        position[2] = Integer.parseInt(matcher.group("positionY"));
        if(!whoseTile(position, player))  return "you don't have access to this tile";
        if(tiles[position[1]][position[2]].getGarrisonUnit() != null){
            player.setCurrentMilitary(tiles[position[1]][position[2]].getGarrisonUnit());
            return "This Military Unit selected as the current militaryUnit";
        }else return "there is no Military Unit here";
    }

    private boolean isCoordinateCorrect(Matcher matcher){
        int[] position = new int[2];
        position[1] = Integer.parseInt(matcher.group("positionX"));
        position[2] = Integer.parseInt(matcher.group("positionY"));
        if(!(position[1] < MaxMap && position[2] < MaxMap))return false;
        return true;
    }

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
        // print messages for player
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

    private void showMap(Matcher matcher) {
    }

    private void moveMap(Matcher matcher) {
    }

    public void unitCombatPosition(Matcher matcher) {
    }

    private void nextTurn() {
        // restore city + unit in combat
    }

    public String run() {
        return null;
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
