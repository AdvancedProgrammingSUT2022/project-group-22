package controllers;

import views.*;
import models.*;
import java.util.regex.*;

public class ProfileMenuController {
    Database database = Database.getInstance();
    User user = new User("a", "a", "a");

    public void changeNickname(Matcher matcher, User user) {
        if (database.getUserByNickname(matcher.group("newnickname").trim()) != null) {
            ProfileMenuView.nicknameExists(matcher.group("newnickname").trim());
            return;
        }
        setNewNickname(matcher, user);
    }

    private void setNewNickname(Matcher matcher, User user) {
        user.setNickname(matcher.group("newnickname").trim());
        ProfileMenuView.changed("nickname");
    }

    public void changePassword(Matcher matcher) {
    }

    private Boolean isPasswordValid(Matcher matcher, User user) {
        if (!user.getPassword().equals(matcher.group("currentpassword").trim())) {
            ProfileMenuView.passwordInvalid();
            return false;
        }
        return true;
    }

    private Boolean isPasswordDifferent(Matcher matcher) {
        if (matcher.group("currentpassword").trim().equals(matcher.group("newpassword").trim())) {
            ProfileMenuView.samePassword();
            return false;
        }
        user.setPassword(matcher.group("newpassword").trim());
        ProfileMenuView.changed("password");
        return true;
    }

    public void changePassword(Matcher matcher, User user) {
        if (!isPasswordValid(matcher, user))
            return;
        if (!isPasswordDifferent(matcher))
            return;
        setNewPassword(matcher, user);
    }

    public void changeMenu(Matcher matcher) {
    }

    private void setNewPassword(Matcher matcher, User user) {
        user.setPassword(matcher.group("newpassword").trim());
    }

    public Boolean changeMenu(String whichMenu) {
        if (whichMenu.equals("gameMenu")) {
            ProfileMenuView.menuNavigationNotPossible();
            return false;
        }
        return true;
    }

    public String run(User user) {
        this.user = database.getUserByUsername(user.getUsername());
        String whichMenu;
        whichMenu = ProfileMenuView.run(user);
        return whichMenu;
        // String whichMenu;
        // while (scanner.hasNext()) {
        // whichMenu = ProfileMenuView.run(user);
        // if (changeMenu(whichMenu))
        // return whichMenu;
        // }
    }

    public void menuNavigation() {
    }
}