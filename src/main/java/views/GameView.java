package views;

import controllers.GameController;
import enums.Color;
import enums.Command;
import models.City;
import models.Player;

import java.util.ArrayList;
import java.util.regex.Matcher;

;

public class GameView extends Processor {
    private static GameView instance = null;
    Matcher matcher;
    GameController gameController = GameController.getInstance();

    public static GameView getInstance() {
        instance = instance != null ? instance : new GameView();
        return instance;
    }

    public void run() {
        String command;
        while (true) {
            command = getInput();
            if ((matcher = getMatcher(command, Command.INFOCITY)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.INFORESEARCH)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.INFODEALS)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.INFODEMOGRAPHICS)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.INFODIPLOMACY)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.INFODIPLOMATIC)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.INFOECONOMIC)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.INFOMILITARY)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.SLEEP)) != null)
                sleep();
            else if ((matcher = getMatcher(command, Command.INFONOTIFICATIONS)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.INFOUNIT)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.INFOVICTORY)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.MENUEXIT)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.SELECTCITYNAME)) != null)
                gameController.selectCity(matcher, Command.SELECTCITYNAME);
            else if ((matcher = getMatcher(command, Command.SELECTCITYPOSITION)) != null)
                gameController.selectCity(matcher, Command.SELECTCITYPOSITION);
            else if ((matcher = getMatcher(command, Command.ATTACK)) != null)
                attack(matcher);
            else if ((matcher = getMatcher(command, Command.FOUND)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.SELECTUNITCOMBAT)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.SELECTUNITNONCOMBAT)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.MOVETO)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.SLEEP)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.ALERT)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.FORTIFY)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.FORTIFYHEAL)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.GARRISON)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.SETUP)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.ATTACK)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.FOUND)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.CANCEL)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.DELETE)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.WAKE)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.BUILDROAD)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.BUILDRAILROAD)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.BUILDFARM)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.BUILDMINE)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.BUILDTRADINGPOST)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.BUILDLAMBERMILL)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.BUILDPASTURE)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.BUILDCAMP)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.BUILDPLANTATION)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.BUILDQUARRY)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.REMOVEJUNGLE)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.REMOVEROUTE)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.REPAIR)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.PRINTAREA)) != null)
                gameController.printMap(matcher, Command.PRINTAREA);
            else if ((matcher = getMatcher(command, Command.PRINTCITY)) != null)
                gameController.printMap(matcher, Command.PRINTCITY);
            else if ((matcher = getMatcher(command, Command.PRINTTILE)) != null)
                gameController.printMap(matcher, Command.PRINTTILE);
            else if ((matcher = getMatcher(command, Command.PRINTUNITPOSITION)) != null)
                gameController.printMap(matcher, Command.PRINTUNITPOSITION);
            else if ((matcher = getMatcher(command, Command.MAPMOVED)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.MAPMOVEL)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.MAPMOVER)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.MAPMOVEU)) != null)
                return;
            else
                System.out.println("invalid Command!");

        }
    }

    // private void selectCityPos(Player player, Matcher matcher) {
    // System.out.println(gameController.selectCity(player, matcher, "position"));
    // }

    // private void showCity(Player player) {
    // for (int i = 0; i < player.getCities().size(); i++) {
    // System.out.println(player.getCities().get(i).getName());
    // }
    // }

    // private void showResearch(Player player) {
    // for (int i = 0; i < player.getTechnologies().size(); i++) {
    // System.out.println(player.getTechnologies().get(i));
    // }
    // }

    // private void showDeals(Player player) {
    // }

    private void showDemographics(Player player) {
        // System.out.println(player.getPopulation());
        // TODO: add get population in player class
    }

    // private void showDemographics(Player player) {
    // System.out.println(player.getPopulation());
    // }

    // private void showDiplomacy() {
    // }

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
        System.out.println("this tile belongs to another player");
    }

    public void invalidCity() {
        System.out.println("no city with this name exists");
    }

    // private void showMessages(Player player) {
    // for (int i = 0; i < player.getMessages().size(); i++) {
    // System.out.println(player.getMessages().get(i));
    // }
    // }

    // public static void showCurrentCity(City city) {
    // }

    // private void selectUnitCombat(Player player, Matcher matcher) {
    // System.out.println(gameController.selectUnitCombat(player, matcher));
    // }

    // private void selectCityName(Player player, Matcher matcher) {
    // System.out.println(gameController.selectCity(player, matcher, "name"));
    // }

    // private void selectUnitNonCombat(Player player, Matcher matcher) {

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

    private void selectUnitCombat(Matcher matcher) {
        System.out.println(gameController.selectUnitCombat(matcher));
    }

    // private void selectCityName(Matcher matcher) {
    // System.out.println(gameController.selectCity(matcher, "name"));
    // }

    private void selectUnitNonCombat(Matcher matcher) {
        System.out.println(gameController.selectUnitNonCombat(matcher));
    }

    private void attack(Matcher matcher) {
        System.out.println(gameController.Attack(matcher));
    }

    private void sleep() {
        System.out.println(gameController.sleep());
    }

}
