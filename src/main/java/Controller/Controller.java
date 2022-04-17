package Controller;

import java.util.regex.*;
import Model.*;
import View.*;

public class Controller {
    private static Controller instance = new Controller();
    protected Database database = Database.getInstance();
    protected Processor processor = Processor.getInstance();
    private RegisterMenuController registerMenuController = new RegisterMenuController();
    private MainMenuController mainMenuController = new MainMenuController();
    private GameController gameController = new GameController();
    private ProfileMenuController profileMenuController = new ProfileMenuController();;

    public static Controller getInstance() {
        return instance;
    }

    public String run() {
        String input;
        input = registerMenuController.run();
        while (true) {
            if(input.equals("Exit")) break;

            else{mainMenuController.run();}

        }
        //processor.closeScanner();
    }

    protected Matcher getMatcher(String input, String regex) {
        regex = "^\\s*" + regex.replace(" ", "\\s+") + "\\s*$";
        Matcher matcher = Pattern.compile(regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
