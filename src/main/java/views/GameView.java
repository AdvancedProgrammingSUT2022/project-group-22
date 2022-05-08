package views;

import controllers.GameController;
import enums.Color;
import enums.Command;
import models.User;

import java.util.ArrayList;
import java.util.regex.Matcher;


public class GameView extends Processor {
    private static GameView instance = null;

    Matcher matcher;

//    GameController gameController = GameController.getInstance();

    public static GameView getInstance() {
        instance = instance != null ? instance : new GameView();
        return instance;
    }

    public String  run() {
        String command;
        while (true) {
            command = getInput();
            if ((matcher = getMatcher(command, Command.INFOCITY)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.INFORESEARCH)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.INFODEALS)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.INFODEMOGRAPHICS)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.INFODIPLOMACY)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.INFODIPLOMATIC)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.INFOECONOMIC)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.INFOMILITARY)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.INFONOTIFICATIONS)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.INFOUNIT)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.INFOVICTORY)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.MENUEXIT)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.SELECTCITYNAME)) != null)
                selectCityName(matcher);
            else if ((matcher = getMatcher(command, Command.SELECTCITYPOSITION)) != null)
                selectCityPos(matcher);
            else if((matcher = getMatcher(command,Command.ATTACK)) != null)
                attack(matcher);
            else if((matcher = getMatcher(command,Command.FOUND)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.SELECTUNITCOMBAT)) != null)
                selectUnitCombat(matcher);
            else if ((matcher = getMatcher(command, Command.SELECTUNITNONCOMBAT)) != null)
                selectUnitNonCombat(matcher);
            else if ((matcher = getMatcher(command, Command.MOVETO)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.SLEEP)) != null) {
                sleep();
            }
            else if ((matcher = getMatcher(command, Command.ALERT)) != null)
                alert();
            else if ((matcher = getMatcher(command, Command.FORTIFY)) != null)
                fority();
            else if ((matcher = getMatcher(command, Command.FORTIFYHEAL)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.GARRISON)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.SETUP)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.FOUND)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.CANCEL)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.DELETE)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.WAKE)) != null)
                wake();
            else if ((matcher = getMatcher(command, Command.BUILDROAD)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.BUILDRAILROAD)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.BUILDFARM)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.BUILDMINE)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.BUILDTRADINGPOST)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.BUILDLAMBERMILL)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.BUILDPASTURE)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.BUILDCAMP)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.BUILDPLANTATION)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.BUILDQUARRY)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.REMOVEJUNGLE)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.REMOVEROUTE)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.REPAIR)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.PRINTAREA)) != null)
                GameController.getInstance().printMap(matcher, Command.PRINTAREA);
            else if ((matcher = getMatcher(command, Command.PRINTCITY)) != null)
                GameController.getInstance().printMap(matcher, Command.PRINTCITY);
            else if ((matcher = getMatcher(command, Command.PRINTTILE)) != null)
                GameController.getInstance().printMap(matcher, Command.PRINTTILE);
            else if ((matcher = getMatcher(command, Command.PRINTUNITPOSITION)) != null)
                GameController.getInstance().printMap(matcher, Command.PRINTUNITPOSITION);
            else if ((matcher = getMatcher(command, Command.MAPMOVED)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.MAPMOVEL)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.MAPMOVER)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.MAPMOVEU)) != null)
                return null;
            else if((matcher = getMatcher(command, Command.NEXT)) != null)
                return "next";
            else
                System.out.println("invalid Command!");

        }
    }

    private void wake() {
        GameController.getInstance().wake();
    }

    private void fority() {
        GameController.getInstance().fortify();
    }


    /**********  these are Errors for both printing map and unit action I used most of them  *************/
    public void notCityOwner() {
        System.out.println("you don not have access to this city");
    }

    public void noUnitSelected() {
        System.out.println("no unit has been selected");
    }

    public void invalidTile() {
        System.out.println("no tile with these coordinates exists");
    }

    public void tileOccupied() {
        System.out.println("a similar unit occupies this tile");
    }

    public void tileInaccessible() {
        System.out.println("you can't move to this tile");
    }

    public void mpLow() {
        System.out.println("you don't have enough mp to move to this tile");
    }

    public void unitNotSettler() {
        System.out.println("the selected unit is not a settler unit");
    }

    public void unitNotOnTile() {
        System.out.println("the unit is not on the selected tile");
    }

    public void tileHasOwner() {
        System.out.println("this tile belongs to another civilization");
    }

    public void invalidCity() {
        System.out.println("no city with this name exists");
    }

    public void hasNotChoseAUnit(){
        System.out.println("Please choose a militaryUnit First");
    }

    public void noMilitaryUnitHere(){
        System.out.println("there is no Military Unit here");
    }

    public void sleepSuccessful(){
        System.out.println("This unit put to sleep");
    }

    public void alertMessage(){
        System.out.println("successfully change status of the selected unit");
    }

    public void successfulFortify(){
        System.out.println("Selected unit successfully fortified");
    }

    public void wakeMessage(){
        System.out.println("You can use the selected unit from now on");
    }


    /************** these functions are not for printing map ********************/
    public void accessTileError(){
        System.out.println("you don't have access to this tile");
    }

    public void outOfMap(){
        System.out.println("Coordinate is out of map");
    }

    public void AttackImpossible(){
        System.out.println("Attack is not Possible");
    }

    public void successfullySelected(){
        System.out.println("successfully selected");
    }

    private void selectCityName(Matcher matcher){
        GameController.getInstance().selectCityByName(matcher);
    }

     private void selectCityPos(Matcher matcher) {
         GameController.getInstance().selectCityByCoordinate(matcher);
     }

    private void selectUnitCombat(Matcher matcher) {
        GameController.getInstance().selectUnitCombat(matcher);
    }

    private void attack(Matcher matcher) {
        GameController.getInstance().Attack(matcher);
    }

    private void selectUnitNonCombat(Matcher matcher) {
        GameController.getInstance().selectUnitNonCombat(matcher);
    }

    private void showDemographics(User player) {
        // System.out.println(player.getPopulation());
        // TODO: add get population in player class
    }

    private void sleep() {
        GameController.getInstance().sleep();
    }

    private void alert() {
        GameController.getInstance().alert();
    }



    /************* Please write functions for printing map here **************/

    public void printMap(String player, int totalHappiness, ArrayList<TileView> tiles, int y, int x) {
        System.out.println("Current Player: " + player);
        System.out.println("Total Happiness =" + totalHappiness);
        int temp = 0;
        for (int i = 0; i < 2 * x + 1; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 3; j++) {

                    for (int k = 2 - j; k > 0; k--) {
                        System.out.print(Color.RESET.getColor() + " ");
                    }

                    for (int l = 0; l < y; l++) {
                        if (l % 2 == 0) {
                            System.out.print(tiles.get(temp).getHasRiver().get(5) + "/");
                            if (j == 0) {
                                System.out.print(tiles.get(temp).getColor()[0] + tiles.get(temp).getBackgroundColor()
                                        + "  " + tiles.get(temp).getNickname().charAt(0) + "  ");
                            } else if (j == 1) {
                                System.out.print(tiles.get(temp).getBackgroundColor() + " ");
                                System.out.printf("%02d,%02d", tiles.get(temp).getX(), tiles.get(temp).getY());
                                System.out.print(tiles.get(temp).getBackgroundColor() + " ");
                            } else {
                                System.out.print(
                                        tiles.get(temp).getBackgroundColor() + " " + tiles.get(temp).getColor()[1]
                                                + tiles.get(temp).getCivilianUnit().substring(0, 3) + " "
                                                + tiles.get(temp).getColor()[2]
                                                + tiles.get(temp).getMilitaryUnit().substring(0, 3) + " ");
                            }
                            System.out.print(tiles.get(temp).getHasRiver().get(1) + "\\");
                        } else {
                            if (j == 0) {
                                System.out.print(tiles.get(temp - y).getBackgroundColor() + " "
                                        + tiles.get(temp - y).getFeature().substring(0, 3) + " "
                                        + tiles.get(temp - y).getResourceTileView().substring(0, 3) + " ");
                            } else if (j == 1) {
                                System.out.print(tiles.get(temp - y).getBackgroundColor() + "  "
                                        + tiles.get(temp - y).getImprovement().substring(0, 3) + "  ");
                            } else {
                                System.out.print(tiles.get(temp - y).getHasRiver().get(3) + "-----");
                            }
                        }
                        temp++;
                    }
                    temp -= (y - 1);
                    System.out.println(Color.RESET.getColor());
                }
            } else {
                for (int j = 0; j < 3; j++) {

                    for (int k = j; k > 0; k--) {
                        System.out.print(Color.RESET.getColor() + " ");
                    }

                    for (int l = 0; l < y; l++) {
                        if (l % 2 == 0) {
                            System.out.print(tiles.get(temp).getHasRiver().get(4) + "\\");
                            if (j == 0) {
                                System.out.print(tiles.get(temp).getBackgroundColor() + " "
                                        + tiles.get(temp).getFeature().substring(0, 3) + " "
                                        + tiles.get(temp - y).getResourceTileView().substring(0, 3) + " ");
                            } else if (j == 1) {
                                System.out.print(tiles.get(temp).getBackgroundColor() + "  "
                                        + tiles.get(temp).getImprovement().substring(0, 3) + "  ");
                            } else {
                                System.out.print(tiles.get(temp).getHasRiver().get(3) + "-----");
                            }
                            System.out.print(tiles.get(temp).getHasRiver().get(2) + "/");
                        } else {
                            if (j == 0) {
                                System.out.print(tiles.get(temp).getColor()[0] + tiles.get(temp).getBackgroundColor()
                                        + "  " + tiles.get(temp).getNickname().charAt(0) + "  ");
                            } else if (j == 1) {
                                System.out.print(tiles.get(temp).getBackgroundColor() + " ");
                                System.out.printf("%02d,%02d", tiles.get(temp).getX(), tiles.get(temp).getY());
                                System.out.print(tiles.get(temp).getBackgroundColor() + " ");
                            } else {
                                System.out.print(
                                        tiles.get(temp).getBackgroundColor() + " " + tiles.get(temp).getColor()[1]
                                                + tiles.get(temp).getCivilianUnit().substring(0, 3) + " "
                                                + tiles.get(temp).getColor()[2]
                                                + tiles.get(temp).getMilitaryUnit().substring(0, 3) + " ");
                            }
                        }
                        temp++;
                    }
                    temp -= y;
                    System.out.println(Color.RESET.getColor());
                }
                temp += y;
            }
        }
    }
}
