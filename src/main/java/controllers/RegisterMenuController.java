package controllers;

import models.*;
import views.*;
import java.util.regex.*;

public class RegisterMenuController {

    public void createUser(Matcher matcher) {
        if (Database.getInstance().getUserByUsername(matcher.group("username").trim()) != null) {
            RegisterMenuView.accountExists(matcher.group("username").trim());
            return;
        } else if (Database.getInstance().getUserByNickname(matcher.group("nickname").trim()) != null) {
            RegisterMenuView.nicknameExists(matcher.group("nickname").trim());
            return;
        }
        RegisterMenuView.userCreated();
        Database.getInstance().addUser(new User(matcher.group("username").trim(),
                matcher.group("password").trim(),
                matcher.group("nickname").trim()));

    }

    public boolean login(Matcher matcher) {
        if (Database.getInstance().getUserByUsername(matcher.group("username").trim()) == null) {
            RegisterMenuView.accountDoesNotExists();
            return false;
        }
        User user = Database.getInstance().getUserByUsername(matcher.group("username").trim());
        if (!user.getPassword().equals(matcher.group("password").trim())) {
            RegisterMenuView.accountDoesNotExists();
            return false;
        }
        return true;
    }

    public String run() {
        Matcher matcher;
        matcher = RegisterMenuView.run();
        return matcher.group("username");

    }

}
