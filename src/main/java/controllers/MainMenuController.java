package controllers;

import models.User;
import views.MainMenuView;

import java.util.regex.*;

public class MainMenuController {
    User user;

    public void createGame(Matcher matcher) {
        // TODO: scan players and create a new game
    }

    public void showMenu(Matcher matcher) {
        MainMenuView.showMenu();
    }

    public String run(User user) {
        this.user = user;
        while (true) {
            String whichMenu = MainMenuView.run();
            if (whichMenu.equals("gameMenu"))
                return "gameMenu";
            else if (whichMenu.equals("profileMenu"))
                return "profileMenu";
            else if (whichMenu.equals("Exit"))
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
