package views;

import controllers.*;
import enums.*;
import java.util.regex.*;

public class GameMenuView extends Processor {
    private static GameMenuView instance = null;

    public static GameMenuView getInstance() {
        instance = instance != null ? instance : new GameMenuView();
        return instance;
    }

    public static String run() {
        GameMenuController gameMenuController = new GameMenuController();
        while (true) {
            String command = getInput();
            Matcher matcher;
            if ((matcher = getMatcher(command, Command.PLAYGAME)) != null) {
                if (gameMenuController.playGame(matcher))
                    return "startGame";
            } else if ((matcher = getMatcher(command, Command.MENUEXIT)) != null)
                return "mainMenu";
            else if ((matcher = getMatcher(command, Command.SHOWMENU)) != null)
                System.out.println("game Menu");
            else
                System.out.println("invalid Command!");
        }
    }

    public static void noUserExists(int i) {
        System.out.format("player %d does not exists\n", i);
    }

    public static void gameStarted() {
        System.out.println("game started successfully!");
    }
    // TODO: print messages for game menu controller
}
