package controllers;

import models.Database;
import models.User;
import views.ProfileMenuView;

import java.util.regex.*;

public class ProfileMenuController{
    public void changeNickname(Matcher matcher, User user) {
        if(Database.getInstance().getUserByNickname(matcher.group("newnickname").trim()) != null) {
            ProfileMenuView.nicknameExists(matcher.group("newnickname").trim());
            return;
        }
        setNewNickname(matcher, user);
    }

    private void setNewNickname(Matcher matcher,User user){
        user.setNickname(matcher.group("newnickname").trim());
        ProfileMenuView.changed("nickname");
    }
    private boolean isPasswordValid(Matcher matcher, User user){
        if(!user.getPassword().equals(matcher.group("currentpassword").trim())){
            ProfileMenuView.passwordInvalid();
            return false;
        }
        return true;
    }

    private boolean isPasswordDifferent(Matcher matcher){
        if(matcher.group("currentpassword").trim().equals(matcher.group("newpassword").trim())){
            ProfileMenuView.samePassword();
            return false;
        }
        return true;
    }
    public void changePassword(Matcher matcher,User user) {
        if(!isPasswordValid(matcher, user)) return;
        if(!isPasswordDifferent(matcher)) return;
        setNewPassword(matcher,user);
    }

    private void setNewPassword(Matcher matcher, User user){
        user.setPassword(matcher.group("newpassword").trim());
        ProfileMenuView.changed("password");
    }

    public boolean changeMenu(String whichMenu) {
        if(whichMenu.equals("gameMenu")) {
            ProfileMenuView.menuNavigationNotPossible();
            return false;
        }
        return true;
    }

    public String  run(User user) {
        String whickMenu;
        while (true){
            whickMenu = ProfileMenuView.run(user);
            if(changeMenu(whickMenu)) return whickMenu;
        }
    }

}
