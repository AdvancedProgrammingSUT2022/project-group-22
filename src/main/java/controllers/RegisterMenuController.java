package controllers;

import models.Database;
import models.User;
import views.Processor;
import views.RegisterMenuView;

import java.util.regex.*;

public class RegisterMenuController {
    // String name = "registerMenu";
    public void createUser(Matcher matcher) {
        if(Database.getInstance().getUserByUsername(matcher.group("username")) != null){
            RegisterMenuView.accountExists(matcher.group("username"));
            return;
        }
        else if(Database.getInstance().getUserByNickname(matcher.group("nickname")) != null){
            RegisterMenuView.nicknameExists(matcher.group("nickname"));
            return;
        }
        RegisterMenuView.userCreated();
        Database.getInstance().addUser(new User(matcher.group("username"), matcher.group("password"), matcher.group("nickname")));


    }

    public boolean login(Matcher matcher) {
        if(Database.getInstance().getUserByUsername(matcher.group("username")) == null){
            RegisterMenuView.accountDoesNotExists();
            return false;
        }
        User user = Database.getInstance().getUserByUsername(matcher.group("username"));
        if(!user.getPassword().equals(matcher.group("password"))) {
            RegisterMenuView.accountDoesNotExists();
            return false;
        }
        return true;
    }


    public String run() {
        Matcher matcher;
        while (true) {
            matcher = RegisterMenuView.run();
            return matcher.group("username");
        }
    }


}
