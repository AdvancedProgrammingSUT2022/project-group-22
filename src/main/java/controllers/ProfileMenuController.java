package controllers;

import models.Database;
import models.User;
import views.ProfileMenuView;

import java.util.regex.*;

public class ProfileMenuController{
    User user = new User("a", "a","a");
    public void changeNickname(Matcher matcher) {
        if(Database.getInstance().getUserByNickname(matcher.group("newnickname").trim()) != null) {
            ProfileMenuView.nicknameExists(matcher.group("newnickname").trim());
            return;

        }
        user.setNickname(matcher.group("newnickname").trim());
        ProfileMenuView.changed("nickname");
    }

    public void changePassword(Matcher matcher) {
        if(!user.getPassword().equals(matcher.group("currentpassword").trim())){
            ProfileMenuView.passwordInvalid();
            return;
        }
        if(matcher.group("currentpassword").trim().equals(matcher.group("newpassword").trim())){
            ProfileMenuView.samePassword();
            return;
        }
        user.setPassword(matcher.group("newpassword").trim());
        ProfileMenuView.changed("password");
    }

    public void changeMenu(Matcher matcher) {
    }

    public void showMenu(Matcher matcher) {
    }

    public String  run(User user) {
        this.user = Database.getInstance().getUserByUsername(user.getUsername());
        System.out.println(this.user.getUsername());
        ProfileMenuView.run();
        return null;
    }

    public void menuNavigation(){

    }
}
