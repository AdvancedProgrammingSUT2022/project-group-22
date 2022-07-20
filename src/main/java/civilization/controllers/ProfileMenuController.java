package civilization.controllers;

import java.io.*;
import civilization.enums.Avatar;
import civilization.models.*;
import javafx.scene.image.ImageView;

public class ProfileMenuController {
    private static ProfileMenuController instance = null;
    private static Database database = Database.getInstance();

    public static ProfileMenuController getInstance() {
        return instance == null ? new ProfileMenuController() : instance;
    }

    public String changeNickname(String newNickname) {
        if (Database.getInstance().getUserByNickname(newNickname) != null) {
            return "user with nickname " + newNickname + " already exists";
        } else {
            Database.getInstance().getLoggedInUser().setNickname(newNickname);
            return "nickname changed successfully";
        }
    }

    public String changePassword(String currentPassword, String newPassword) {
        if (!currentPassword.equals(Database.getInstance().getLoggedInUser().getPassword())) {
            return "current password is invalid";
        } else if (currentPassword.equals(newPassword)) {
            return "enter a new password";
        } else {
            Database.getInstance().getLoggedInUser().setPassword(newPassword);
            return "password changed successfully";
        }
    }

    public void changeAvatar(Avatar avatar) {
        try {
            database.getLoggedInUser().setAvatar(avatar);
            RegisterMenuController.saveUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ImageView getAvatar() {
        return Database.getInstance().getLoggedInUser().getAvatar();
    }
}