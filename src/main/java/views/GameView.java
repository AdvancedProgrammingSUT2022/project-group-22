package views;

import enums.*;
import controllers.*;
import java.util.*;
import java.util.regex.*;;

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
            else if ((matcher = getMatcher(command, Command.INFONOTIFICATIONS)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.INFOUNIT)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.INFOVICTORY)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.MENUEXIT)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.SELECTCITYNAME)) != null)
                return;
            else if ((matcher = getMatcher(command, Command.SELECTCITYPOSITION)) != null)
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

    // private void showDemographics(Player player) {
    // System.out.println(player.getPopulation());
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
        System.out.println("this tile belongs to another player");
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

}
