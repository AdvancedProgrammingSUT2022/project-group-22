package views;

import controllers.GameController;
import controllers.UnitController;
import enums.Color;
import enums.Command;
import enums.Technology;
import models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

public class GameView extends Processor {
    private static GameView instance = null;

    Matcher matcher;

    // GameController gameController = GameController.getInstance();

    public static GameView getInstance() {
        instance = instance != null ? instance : new GameView();
        return instance;
    }

    public String run() {
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
                return "exit";
            else if ((matcher = getMatcher(command, Command.SELECTCITYNAME)) != null)
                GameController.getInstance().selectCityByName(matcher);
            else if ((matcher = getMatcher(command, Command.SELECTCITYPOSITION)) != null)
                GameController.getInstance().selectCityByPosition(matcher);
            else if ((matcher = getMatcher(command, Command.SELECTUNITCOMBAT)) != null)
                GameController.getInstance().selectCombatUnit(matcher);
            else if ((matcher = getMatcher(command, Command.SELECTUNITNONCOMBAT)) != null)
                GameController.getInstance().selectNonCombatUnit(matcher);
            else if ((matcher = getMatcher(command, Command.ATTACK)) != null)
                UnitController.getInstance().attack(matcher);
            else if ((matcher = getMatcher(command, Command.FOUND)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.MOVETO)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.SLEEP)) != null)
                UnitController.getInstance().sleep();
            else if ((matcher = getMatcher(command, Command.WAKE)) != null)
                UnitController.getInstance().wake();
            else if ((matcher = getMatcher(command, Command.ALERT)) != null)
                UnitController.getInstance().alert();
            else if ((matcher = getMatcher(command, Command.FORTIFY)) != null)
                UnitController.getInstance().fortify();
            else if ((matcher = getMatcher(command, Command.FORTIFYHEAL)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.GARRISON)) != null)
                UnitController.getInstance().garrison();
            else if ((matcher = getMatcher(command, Command.SETUP)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.FOUND)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.CANCEL)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.DELETE)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.BUILDROAD)) != null)
                GameController.getInstance().buildRoad(matcher);
            else if ((matcher = getMatcher(command, Command.BUILDRAILROAD)) != null)
                GameController.getInstance().buildRailRoad();
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
                GameController.getInstance().printArea(matcher);
            else if ((matcher = getMatcher(command, Command.PRINTCITY)) != null)
                GameController.getInstance().printCity(matcher);
            else if ((matcher = getMatcher(command, Command.PRINTTILE)) != null)
                GameController.getInstance().printTile(matcher, Command.PRINTTILE);
            else if ((matcher = getMatcher(command, Command.PRINTUNITPOSITION)) != null)
                GameController.getInstance().printTile(matcher, Command.PRINTUNITPOSITION);
            else if ((matcher = getMatcher(command, Command.MAPMOVED)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.MAPMOVEL)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.MAPMOVER)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.MAPMOVEU)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.NEXT)) != null)
                GameController.getInstance().nextTurn();
            else
                System.out.println("invalid Command!");

        }
    }

    // errors
    public void cityInaccessible() {
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

    public void unitNotWorker() {
        System.out.println("the selected unit is not a worker unit");
    }

    public void unitNotOnTile() {
        System.out.println("the unit is not on the selected tile");
    }

    public void invalidMilitaryUnit() {
        System.out.println("there is no military unit on the selected tile");
    }

    public void invalidCivilianUnit() {
        System.out.println("there is no civilian unit on the selected tile");
    }

    public void unitInaccessible() {
        System.out.println("this unit belongs to another civilization");
    }

    public void tileHasOwner() {
        System.out.println("this tile belongs to another civilization");
    }

    public void tileOwned() {
        System.out.println("you already own this tile");
    }

    public void noCityNearby() {
        System.out.println("this tile isn't neighboring your current civilization");
    }

    public void invalidCity() {
        System.out.println("no city with this name exists");
    }

    public void sleepSuccessful() {
        System.out.println("This unit put to sleep");
    }

    public void alertMessage() {
        System.out.println("successfully change status of the selected unit");
    }

    public void successfulFortify() {
        System.out.println("Selected unit successfully fortified");
    }

    public void wakeMessage() {
        System.out.println("You can use the selected unit from now on");
    }

    public void garrisonMessage() {
        System.out.println("Selected unit attached to the tile");
    }

    public void buildRoadSuccessful() {
        System.out.println("Road built successfully!");
    }

    public void accessTileError() {
        System.out.println("you don't have access to this tile");
    }

    public void outOfMap() {
        System.out.println("Coordinate is out of map");
    }

    public void AttackImpossible() {
        System.out.println("Attack is not Possible");
    }

    public void successfullySelected() {
        System.out.println("successfully selected");
    }

    public void turnNotOver() {
        System.out.println("you have units with unassigned tasks");
    }

    public void goldLow() {
        System.out.println("insufficient gold");
    }

    private void showDemographics(User player) {
        // System.out.println(player.getPopulation());
        // TODO: add get population in player class
    }

    // print map

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

    // print researchMenu
    public void PrintResearchInfo(HashMap<Integer, Technology> currentResearch, ArrayList<Technology> possibles, ArrayList<Technology> done){
        System.out.println("Current researches:");
        for (Integer number: currentResearch.keySet()) {
            int key = number;
            String value = currentResearch.get(number).name();
            System.out.println(key + " " + value);
        }
        System.out.println("Possible technologies to research:");
        for (int i = 0; i < possibles.size(); i++) {
            System.out.println(possibles.get(i).name());
        }
        System.out.println("Your own technologies:");
        for (int i = 0; i < done.size(); i++) {
            System.out.println(done.get(i).name());
        }
    }
}

