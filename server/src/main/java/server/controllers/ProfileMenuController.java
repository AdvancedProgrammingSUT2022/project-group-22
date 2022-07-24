package server.controllers;

import server.models.*;
import javafx.scene.image.ImageView;

import java.util.Date;

public class ProfileMenuController {
    private static ProfileMenuController instance = null;

    public static ProfileMenuController getInstance() {
        return instance == null ? new ProfileMenuController() : instance;
    }

    public Response changeNickname(String newNickname, User user, Date exp) {
        Response response = new Response();
        if (Database.getInstance().getUserByNickname(newNickname) != null) {
            response.setStatus(400);
            response.addData("message", "user with nickname " + newNickname + " already exists");
            return response;
        }
        Database.getInstance().getLoggedInUsers().remove(user);
        Database.getInstance().getUserByNickname(user.getNickname()).setNickname(newNickname);
        user.setNickname(newNickname);
        Database.getInstance().getLoggedInUsers().add(user);
        String jwt = JwtController.createJWT(user, exp.getTime() - System.currentTimeMillis());
        response.setStatus(200);
        response.addData("message", "nickname changed successfully");
        response.addData("jwt", jwt);
        return response;
    }

    public Response changePassword(String currentPassword, String newPassword, User user, Date exp) {
        Response response = new Response();
        if (!currentPassword.equals(user.getPassword())) {
            response.setStatus(400);
            response.addData("message", "current password is invalid");
            return response;
        } else if (currentPassword.equals(newPassword)) {
            response.setStatus(400);
            response.addData("message", "enter a new password");
            return response;
        }
        Database.getInstance().getLoggedInUsers().remove(user);
        Database.getInstance().getUserByNickname(user.getNickname()).setPassword(newPassword);
        user.setPassword(newPassword);
        Database.getInstance().getLoggedInUsers().add(user);
        String jwt = JwtController.createJWT(user, exp.getTime() - System.currentTimeMillis());
        response.setStatus(200);
        response.addData("message", "password changed successfully");
        response.addData("jwt", jwt);
        return response;
    }

    public void logout(User user) {
        Database.getInstance().getLoggedInUsers().remove(user);
    }

    public Response setAvatar(String avatar, String username, Date exp) {
        Database.getInstance().getLoggedInUserByUsername(username).setAvatar(avatar);
        User user = Database.getInstance().getUserByUsername(username);
        user.setAvatar(avatar);
        String jwt = JwtController.createJWT(user, exp.getTime() - System.currentTimeMillis());
        Response response = new Response();
        response.addData("jwt",jwt);
        return response;
    }
//    public void setAvatar(ImageView avatar) {
//        Database.getInstance().getLoggedInUser().setAvatar(avatar);
////        try {
////            RegisterMenuController.saveUsers();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//    }
//    public ImageView getAvatar(){
//        return Database.getInstance().getLoggedInUser().getAvatar();
//    }

}