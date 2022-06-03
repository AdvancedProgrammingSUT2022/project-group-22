package controllers;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import models.*;
import views.*;

public class RegisterMenuController {
    private static RegisterMenuController instance = null;

    public static RegisterMenuController getInstance() {
        instance = instance == null ? new RegisterMenuController() : instance;
        return instance;
    }

    Database database = Database.getInstance();
    // public void loadUsers() {
    // JSONArray arr = new JSONArray();
    // for (int i = 0; i < arr.length(); i++) {
    // String username = arr.getJSONObject(i).getString("username");
    // String nickname = arr.getJSONObject(i).getString("nickname");
    // String password = arr.getJSONObject(i).getString("password");
    // Database.getInstance().addUser(new User(username, nickname, password));
    // }
    // }

    public void saveUser(User user) {
        // String path = "/app/json/companies.json";

        // JSONObject json = new JSONObject();
        // try {
        // json.put("username", user.getUsername());
        // json.put("nickname", user.getNickname());
        // json.put("password", user.getPassword());
        // } catch (JSONException e) {
        // e.printStackTrace();
        // }

        // try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
        // out.write(json.toString());
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
    }

    public void createUser(GridPane gridPane, String username, String nickname, String password) {
        User user;
        if (database.getUserByUsername(username) != null) {
            MainPageController.getInstance().showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                    "Error!",
                    "Username taken.");
        } else if (database.getUserByNickname(nickname) != null) {
            MainPageController.getInstance().showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                    "Error!",
                    "Nickname taken.");
        } else {
            database.addUser((user = new User(username, password, nickname)));
            saveUser(user);
            database.setCurrentUser(user);
            MainPageController.getInstance().showAlert(Alert.AlertType.CONFIRMATION,
                    gridPane.getScene().getWindow(), "Sign Up Successful!",
                    "Welcome " + user.getUsername() + "!");
        }
    }

    public void login(GridPane gridPane, String username, String password) {
        User user;
        if ((user = Database.getInstance().getUserByUsername(username)) == null) {
            MainPageController.getInstance().showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                    "Error!",
                    "No user with this username exists.");
        } else if (!user.getPassword().equals(password)) {
            MainPageController.getInstance().showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                    "Error!",
                    "Incorrect password.");
        } else {
            database.setCurrentUser(user);
            MainPageController.getInstance().showAlert(Alert.AlertType.CONFIRMATION,
                    gridPane.getScene().getWindow(), "Sign In Successful!",
                    "Welcome " + user.getUsername() + "!");
        }
    }
}
