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
        setNewNickname(matcher);
    }

    private void setNewNickname(Matcher matcher){
        user.setNickname(matcher.group("newnickname").trim());
        ProfileMenuView.changed("nickname");
    }
    private boolean isPasswordValid(Matcher matcher){
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
    public void changePassword(Matcher matcher) {
        if(!isPasswordValid(matcher)) return;
        if(!isPasswordDifferent(matcher)) return;
        setNewPassword(matcher);
    }

    private void setNewPassword(Matcher matcher){
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
        this.user = Database.getInstance().getUserByUsername(user.getUsername());
        while (true){
            whickMenu = ProfileMenuView.run();
            if(changeMenu(whickMenu)) return whickMenu;
        }
    }

}
