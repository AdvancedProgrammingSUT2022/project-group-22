package views;

import controllers.GameMenuController;
import enums.Commands;

import java.util.regex.Matcher;

public class GameMenuView {
    private static GameMenuView instance = null;

    public static GameMenuView getInstance() {
        instance = instance != null ? instance : new GameMenuView();
        return instance;
    }

    public static String run() {
        GameMenuController gameMenuController = new GameMenuController();
        while (true) {
            String command = Processor.getInstance().getInput();
            Matcher matcher;
            if ((matcher = Commands.getMatcher(command, Commands.PLAYGAME)) != null) {
                if (gameMenuController.playGame(matcher))
                    return "startGame";
            } else if ((matcher = Commands.getMatcher(command, Commands.MENUEXIT)) != null)
                return "mainMenu";
            else if ((matcher = Commands.getMatcher(command, Commands.SHOWMENU)) != null)
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
