package civilization.controllers;

// import org.json.*;
import civilization.models.*;
import civilization.views.*;

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

    public void createUser(Matcher matcher) {
        if (Database.getInstance().getUserByUsername(matcher.group("username").trim()) != null) {
            RegisterMenuView.accountExists(matcher.group("username").trim());
            return;
        } else if (Database.getInstance().getUserByNickname(matcher.group("nickname").trim()) != null) {
            RegisterMenuView.nicknameExists(matcher.group("nickname").trim());
            return;
        }
        Database.getInstance().addUser(new User(matcher.group("username").trim(),
                matcher.group("password").trim(),
                matcher.group("nickname").trim()));
        RegisterMenuView.userCreated();
    }

    public Boolean canLogin(Matcher matcher) {
        User user;
        if ((user = Database.getInstance().getUserByUsername(matcher.group("username").trim())) == null) {
            RegisterMenuView.accountDoesNotExists();
            return false;
        } else if (!user.getPassword().equals(matcher.group("password").trim())) {
            RegisterMenuView.incorrectPassword();
            return false;
        }
        return true;
    }

    public String run() {
        return RegisterMenuView.run();
    }

}
