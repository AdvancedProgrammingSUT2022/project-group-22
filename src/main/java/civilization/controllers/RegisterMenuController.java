package civilization.controllers;

// import org.json.*;
import civilization.enums.Avatars;
import civilization.models.*;
import civilization.views.*;

import java.util.Random;
import java.util.regex.*;

public class RegisterMenuController {
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

    public static boolean createUser(String username, String nickname, String password) {
        if (Database.getInstance().getUserByUsername(username) != null) {
            Menu.showPopUp("user with username " + username + " already exists");
            return false;
        } else if (Database.getInstance().getUserByNickname(nickname) != null) {
            Menu.showPopUp("user with nickname " + nickname + " already exists");
            return false;
        }
        Random random = new Random();
        int i = random.nextInt(Avatars.values().length);
        String randomAvatar = Avatars.values()[i].getUrl();
        Database.getInstance().addUser(new User(username, password, nickname, randomAvatar));
        Menu.showPopUp("user created successfully");
        return true;
    }

    public static Boolean canLogin(String username, String password) {
        User user;
        if ((user = Database.getInstance().getUserByUsername(username)) == null) {
            Menu.showPopUp("no account with this username exists");
            return false;
        } else if (!user.getPassword().equals(password)) {
            Menu.showPopUp("incorrect password");
            return false;
        }
        Database.getInstance().setLoggedInUser(Database.getInstance().getUserByUsername(username));
        return true;
    }

    public String run() {
        return RegisterMenuView.run();
    }

}
