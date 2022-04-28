package controllers;

import models.*;
import views.*;
import java.util.regex.*;

public class RegisterMenuController {
    Database database = Database.getInstance();

    // String name = "registerMenu";
    public void createUser(Matcher matcher) {
        if (database.getUserByUsername(matcher.group("username").trim()) != null) {
            RegisterMenuView.accountExists(matcher.group("username").trim());
            return;
        } else if (database.getUserByNickname(matcher.group("nickname").trim()) != null) {
            RegisterMenuView.nicknameExists(matcher.group("nickname").trim());
            return;
        }
        RegisterMenuView.userCreated();
        database.addUser(new User(matcher.group("username").trim(),
                matcher.group("password").trim(),
                matcher.group("nickname").trim()));

    }

    public boolean login(Matcher matcher) {
        if (database.getUserByUsername(matcher.group("username").trim()) == null) {
            RegisterMenuView.accountDoesNotExists();
            return false;
        }
        User user = database.getUserByUsername(matcher.group("username").trim());
        if (!user.getPassword().equals(matcher.group("password").trim())) {
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
