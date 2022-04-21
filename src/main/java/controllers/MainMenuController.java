package controllers;

import models.User;

import java.util.regex.*;

public class MainMenuController{
    User user;
    String name = "main menu";

    public void createGame(Matcher matcher) {
        //TODO: scan players and create a new game
    }

    public void logout(Matcher matcher) {
    }

    public void changeMenu(Matcher matcher) {
    }

    public void showMenu(Matcher matcher) {
        // print name
    }

    public String  run(User user) {
        this.user = user;
        return null;
    }

    public void menuNavigation(String whichMenu){

    }
}
