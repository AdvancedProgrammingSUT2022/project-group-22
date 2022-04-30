package views;

import enums.*;
import controllers.*;
import models.*;
import java.util.*;
import java.util.regex.*;;

public class GameView {
    private static GameView instance = null;

    public static GameView getInstance() {
        instance = instance != null ? instance : new GameView();
        return instance;
    }

    public void run(Player player) {
        Matcher matcher;
        GameController gameController = new GameController();
        while (true) {
            String command = Processor.getInstance().getInput();
            if ((matcher = Command.getMatcher(command, Command.INFOCITY)) != null)
                showCity(player);
            else if ((matcher = Command.getMatcher(command, Command.INFORESEARCH)) != null)
                showResearch(player);
            else if ((matcher = Command.getMatcher(command, Command.INFODEALS)) != null)
                showDeals(player);
            else if ((matcher = Command.getMatcher(command, Command.INFODEMOGRAPHICS)) != null)
                showDemographics(player);
            else if ((matcher = Command.getMatcher(command, Command.INFODIPLOMACY)) != null)
                showDiplomacy();
            else if ((matcher = Command.getMatcher(command, Command.INFODIPLOMATIC)) != null)
                return;
            else if ((matcher = Command.getMatcher(command, Command.INFOECONOMIC)) != null)
                return;
            else if ((matcher = Command.getMatcher(command, Command.INFOMILITARY)) != null)
                return;
            else if ((matcher = Command.getMatcher(command, Command.INFONOTIFICATIONS)) != null)
                return;
            else if ((matcher = Command.getMatcher(command, Command.INFOUNIT)) != null)
                return;
            else if ((matcher = Command.getMatcher(command, Command.INFOVICTORY)) != null)
                return;
            else if ((matcher = Command.getMatcher(command, Command.MENUEXIT)) != null)
                return;
            else
                System.out.println("invalid command");
        }
    }

    private void showCity(Player player) {
        for (int i = 0; i < player.getCities().size(); i++) {
            System.out.println(player.getCities().get(i).getName());
        }
    }

    private void showResearch(Player player) {
        for (int i = 0; i < player.getResearches().size(); i++) {
            System.out.println(player.getResearches().get(i));
        }
    }

    private void showDeals(Player player) {
    }

    private void showDemographics(Player player) {
        System.out.println(player.getPopulation());
    }

    private void showDiplomacy() {
    }

    public void noUnitSelected() {
        System.out.println("no unit has been selected");
    }

    public void incorrectTile() {
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

    private void showMessages(Player player) {
        for (int i = 0; i < player.getMessages().size(); i++) {
            System.out.println(player.getMessages().get(i));
        }
    }

    public static void showCurrentCity(City city) {

    }
}
