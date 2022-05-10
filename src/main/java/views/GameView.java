package views;

import controllers.*;
import enums.*;
import models.*;
import java.util.*;
import java.util.regex.*;

public class GameView extends Processor {
    private static GameView instance = null;
    GameController gameController = GameController.getInstance();
    UnitController unitController = UnitController.getInstance();
    MapController mapController = MapController.getInstance();
    Matcher matcher;

    public static GameView getInstance() {
        instance = instance != null ? instance : new GameView();
        return instance;
    }

    public String run() {
        String command;
        while (true) {
            command = getInput();
            if ((matcher = getMatcher(command, Command.MENUEXIT)) != null)
                return "exit";
            else if ((matcher = getMatcher(command, Command.INFOCITY)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.INFORESEARCH)) != null)
                GameController.getInstance().researchInfo();
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
                unitController.move(matcher);
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
                UnitController.getInstance().buildRoad(matcher);
            else if ((matcher = getMatcher(command, Command.BUILDRAILROAD)) != null)
                UnitController.getInstance().buildRailRoad();
            else if ((matcher = getMatcher(command, Command.BUILDFARM)) != null)
                unitController.buildImprovements(Improvement.FARM);
            else if ((matcher = getMatcher(command, Command.BUILDMINE)) != null)
                unitController.buildImprovements(Improvement.MINE);
            else if ((matcher = getMatcher(command, Command.BUILDTRADINGPOST)) != null)
                unitController.buildImprovements(Improvement.TRADINGPOST);
            else if ((matcher = getMatcher(command, Command.BUILDLUMBERMILL)) != null)
                unitController.buildImprovements(Improvement.LUMBERMILL);
            else if ((matcher = getMatcher(command, Command.BUILDPASTURE)) != null)
                unitController.buildImprovements(Improvement.PASTURE);
            else if ((matcher = getMatcher(command, Command.BUILDCAMP)) != null)
                unitController.buildImprovements(Improvement.CAMP);
            else if ((matcher = getMatcher(command, Command.BUILDPLANTATION)) != null)
                unitController.buildImprovements(Improvement.PLANTATION);
            else if ((matcher = getMatcher(command, Command.BUILDQUARRY)) != null)
                unitController.buildImprovements(Improvement.QUARRY);
            else if ((matcher = getMatcher(command, Command.REMOVEJUNGLE)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.REMOVEROUTE)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.REPAIR)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.PRINTAREA)) != null)
                MapController.getInstance().printAreaCheck(matcher);
            else if ((matcher = getMatcher(command, Command.PRINTCITY)) != null)
                MapController.getInstance().printCityCheck(matcher);
            else if ((matcher = getMatcher(command, Command.PRINTTILE)) != null)
                MapController.getInstance().printTileCheck(matcher, Command.PRINTTILE);
            else if ((matcher = getMatcher(command, Command.PRINTUNITPOSITION)) != null)
                MapController.getInstance().printTileCheck(matcher, Command.PRINTUNITPOSITION);
            else if ((matcher = getMatcher(command, Command.MAPMOVED)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.MAPMOVEL)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.MAPMOVER)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.MAPMOVEU)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.NEXT)) != null)
                UnitController.getInstance().nextTurn();
            else if ((matcher = getMatcher(command, Command.INCREASETURN)) != null)
                UnitController.getInstance().skipTurns(matcher);
            else if ((matcher = getMatcher(command, Command.INCREASEGOLD)) != null)
                GameController.getInstance().addGold(matcher);
            else if ((matcher = getMatcher(command, Command.INSTANTBUILD)) != null)
                UnitController.getInstance().instantBuild(matcher);
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

    public void onTarget() {
        System.out.println("you are already on your selected tile");
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

    public void tileNotYours() {
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

    public void tileHasImprovement() {
        System.out.println("this tile already has an improvement");
    }

    public void noFeature(Feature feature) {
        System.out.println("this tile does not have the " + feature.name().toLowerCase() + "feature");
    }

    public void invalidLocation() {
        System.out.println("you can't build this improvement here");
    }

    public void insufficientTechnologies() {
        System.out.println("you do not have the prerequired technologies");
    }

    public void completeTask(String task, Tile tile) {
        System.out.println(task + "was built on tile " + tile.getCoordinates()[0] + ":" + tile.getCoordinates()[1]);
    }

    public void completeMove(Tile tile) {
        System.out.println("unit has moved to tile " + tile.getCoordinates()[0] + ":" + tile.getCoordinates()[1]);
    }

    private void showDemographics(User player) {
        // System.out.println(player.getPopulation());
        // TODO: add get population in player class
    }

    // print map

    public void printMap(String player, int totalHappiness, ArrayList<TileView> tiles, int y, int x) {
        System.out.println("Current Player: " + player);
        System.out.println("Total Happiness = " + totalHappiness);
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
    public void PrintResearchInfo(HashMap<Integer, Technology> currentResearch, ArrayList<Technology> possibles,
            ArrayList<Technology> done) {
        System.out.println("Current researches:");
        for (Integer number : currentResearch.keySet()) {
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
