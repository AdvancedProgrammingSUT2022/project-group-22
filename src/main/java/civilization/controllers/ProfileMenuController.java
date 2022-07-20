package civilization.controllers;

import civilization.views.*;
import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.Base64;
import civilization.App;
import civilization.enums.Avatar;
import civilization.models.*;

public class ProfileMenuController {
    private static ProfileMenuController instance = null;
    private static Database database = Database.getInstance();

    public static ProfileMenuController getInstance() {
        return instance == null ? new ProfileMenuController() : instance;
    }

    // public void changeNickname(Matcher matcher, User user) {
    // if (database.getUserByNickname(matcher.group("newnickname").trim()) != null)
    // {
    // ProfileMenuView.nicknameExists(matcher.group("newnickname").trim());
    // return;
    // }
    // setNewNickname(matcher, user);
    // }

    // private void setNewNickname(Matcher matcher, User user) {
    // user.setNickname(matcher.group("newnickname").trim());
    // ProfileMenuView.changed("nickname");
    // }

    // public void changePassword(Matcher matcher) {
    // }
    //
    // private Boolean isPasswordValid(Matcher matcher, User user) {
    // if (!user.getPassword().equals(matcher.group("currentpassword").trim())) {
    // ProfileMenuView.passwordInvalid();
    // return false;
    // }
    // return true;
    // }

    // private Boolean isPasswordDifferent(Matcher matcher) {
    // if
    // (matcher.group("currentpassword").trim().equals(matcher.group("newpassword").trim()))
    // {
    // ProfileMenuView.samePassword();
    // return false;
    // }
    // user.setPassword(matcher.group("newpassword").trim());
    // ProfileMenuView.changed("password");
    // return true;
    // }
    //
    // public void changePassword(Matcher matcher, User user) {
    // if (!isPasswordValid(matcher, user))
    // return;
    // if (!isPasswordDifferent(matcher))
    // return;
    // setNewPassword(matcher, user);
    // }
    //
    // public void changeMenu(Matcher matcher) {
    // }
    //
    // private void setNewPassword(Matcher matcher, User user) {
    // user.setPassword(matcher.group("newpassword").trim());
    // }
    //
    // public Boolean changeMenu(String whichMenu) {
    // if (whichMenu.equals("gameMenu")) {
    // ProfileMenuView.menuNavigationNotPossible();
    // return false;
    // }
    // return true;
    // }

//    public String run(User user) {
//        return ProfileMenuView.run(database.getUserByUsername(user.getUsername()));
//    }

    public void changeAvatar(Avatar avatar) {
//        try {
//            database.getLoggedInUser().setAvatar(avatar);
//            RegisterMenuController.saveUsers();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void menuNavigation() {
    }
}