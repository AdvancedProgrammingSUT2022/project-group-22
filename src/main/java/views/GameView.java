package views;

import controllers.GameController;
import enums.Color;
import enums.Command;
import models.City;
import models.Civilization;

import java.util.ArrayList;
import java.util.regex.Matcher;


public class GameView extends Processor {
    private static GameView instance = null;

    Matcher matcher;

    GameController gameController = GameController.getInstance();

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
            else if((matcher =  getMatcher(command,Command.SLEEP)) != null)
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
                return null;
            else if ((matcher = getMatcher(command, Command.SELECTCITYPOSITION)) != null)
                return null;
            else if((matcher = getMatcher(command,Command.ATTACK)) != null)
                attack(matcher);
            else if((matcher = getMatcher(command,Command.FOUND)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.SELECTUNITCOMBAT)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.SELECTUNITNONCOMBAT)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.MOVETO)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.SLEEP)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.ALERT)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.FORTIFY)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.FORTIFYHEAL)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.GARRISON)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.SETUP)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.ATTACK)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.FOUND)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.CANCEL)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.DELETE)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.WAKE)) != null)
                return null;
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
                gameController.printMap(matcher, Command.PRINTAREA);
            else if ((matcher = getMatcher(command, Command.PRINTCITY)) != null)
                gameController.printMap(matcher, Command.PRINTCITY);
            else if ((matcher = getMatcher(command, Command.PRINTTILE)) != null)
                gameController.printMap(matcher, Command.PRINTTILE);
            else if ((matcher = getMatcher(command, Command.PRINTUNITPOSITION)) != null)
                gameController.printMap(matcher, Command.PRINTUNITPOSITION);
            else if ((matcher = getMatcher(command, Command.MAPMOVED)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.MAPMOVEL)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.MAPMOVER)) != null)
                return null;
            else if ((matcher = getMatcher(command, Command.MAPMOVEU)) != null)
                return null;
            else
                System.out.println("invalid Command!");

        }
    }

    // private void selectCityPos(Civilization civilization, Matcher matcher) {
    // System.out.println(gameController.selectCity(civilization, matcher, "position"));
    // }

    // private void showCity(Civilization civilization) {
    // for (int i = 0; i < civilization.getCities().size(); i++) {
    // System.out.println(civilization.getCities().get(i).getName());
    // }
    // }

    // private void showResearch(Civilization civilization) {
    // for (int i = 0; i < civilization.getTechnologies().size(); i++) {
    // System.out.println(civilization.getTechnologies().get(i));
    // }
    // }

    // private void showDeals(Civilization civilization) {
    // }


    private void showDemographics(Civilization civilization) {
//        System.out.println(civilization.getPopulation());
        //TODO: add get population in civilization class
    }

    // private void showDemographics(Civilization civilization) {
    // System.out.println(civilization.getPopulation());
    // }


    // private void showDiplomacy() {
    // }

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

    // private void showMessages(Civilization civilization) {
    // for (int i = 0; i < civilization.getMessages().size(); i++) {
    // System.out.println(civilization.getMessages().get(i));
    // }
    // }

    // public static void showCurrentCity(City city) {
    // }

    // private void selectUnitCombat(Civilization civilization, Matcher matcher) {
    // System.out.println(gameController.selectUnitCombat(civilization, matcher));
    // }

    // private void selectCityName(Civilization civilization, Matcher matcher) {
    // System.out.println(gameController.selectCity(civilization, matcher, "name"));
    // }

    // private void selectUnitNonCombat(Civilization civilization, Matcher matcher) {

    // }

    public void printMap(ArrayList<TileView> tiles, int y, int x) {
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

    public static void showCurrentCity(City city) {
    }

    private void selectUnitCombat(Matcher matcher){
        System.out.println(gameController.selectUnitCombat(matcher));
    }

    private void selectCityName(Matcher matcher){
        System.out.println(gameController.selectCity(matcher, "name"));
    }

    private void selectUnitNonCombat(Matcher matcher){
        System.out.println(gameController.selectUnitNonCombat(matcher));
    }

    private void attack(Matcher matcher){
        System.out.println(gameController.Attack(matcher));
    }

//    private void sleep(){
//        System.out.println(gameController.sleep());
//    }


}
