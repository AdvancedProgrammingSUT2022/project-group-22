package controllers;

import models.Database;
import views.Processor;
import views.RegisterMenuView;

import java.util.regex.*;

public class RegisterMenuController {
    // String name = "registerMenu";
    public boolean createUser(Matcher matcher) {
        if(!isUserNameUnique(matcher)) return true;
        if()

        return false;
    }

    public void login(Matcher matcher) {
        // call is there player to check if there is player or not
        // find the current player and pass it to Player Controller
    }

    public void changeMenu(Matcher matcher) {
    }

    public void showMenu(Matcher matcher) {
        // Print Controller name
    }

    private void checkPassword(Matcher matcher){
    }

    private void checkUserName(){
    }

    private boolean isUserNameUnique(Matcher matcher){
        for(int i = 0 ; i < Database.getInstance().getUsers().size() ; i++){
            if(matcher.group("username").trim().equals(Database.getInstance().getUsers().get(i).getUsername())) return false;
        }
        return true;

    }



    public String run() {
        while (true) {
            RegisterMenuView.run();
            // switch between login and createUser and Exit and ShowMenu
        }
    }
}
