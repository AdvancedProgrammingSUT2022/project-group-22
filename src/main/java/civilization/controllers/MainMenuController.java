package civilization.controllers;

import civilization.models.User;
import civilization.views.MainMenuView;

import java.util.regex.Matcher;

public class MainMenuController {
    User user;

    public void createGame(Matcher matcher) {
        // TODO: scan players and create a new game
    }

    public String run(User user) {
        this.user = user;
        while (true) {
            String whichMenu = MainMenuView.run();
            if (whichMenu.equals("gameMenu"))
                return "gameMenu";
            else if (whichMenu.equals("profileMenu"))
                return "profileMenu";
            else if (whichMenu.equals("exit"))
                return "registerMenu";
            else if (whichMenu.equals("logout"))
                return "registerMenu";
            else
                MainMenuView.menuNavigationNotPossible();
        }
    }

    // public void menuNavigation(String whichMenu){
    //
    // }
}
