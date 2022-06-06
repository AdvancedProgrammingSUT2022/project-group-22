package civilization.controllers;

//import civilization.views.Menu;
//import civilization.views.RegisterMenuView;
//import com.google.gson.*;
//import com.google.gson.reflect.TypeToken;
//import com.google.gson.stream.JsonReader;
//import civilization.enums.Avatar;
//import civilization.models.*;
//import java.io.*;
//import java.util.*;

import civilization.enums.Avatar;
import civilization.models.Database;
import civilization.models.User;
import civilization.views.RegisterMenuView;
import civilization.views.Menu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.List;
import java.util.Random;

public class RegisterMenuController {
    private static Database database = Database.getInstance();

    public static void loadUsers() throws FileNotFoundException, IOException {
        JsonReader reader = new JsonReader(new FileReader("src/main/resources/civilization/json/Users.json"));
        List<User> users = new Gson().fromJson(reader, new TypeToken<List<User>>() {
        }.getType());
        if (users != null) {
            for (User user : users) {
                database.addUser(user);
            }
        }
        reader.close();
    }

    public static void saveUsers() throws FileNotFoundException, IOException {
        try (Writer writer = new FileWriter("src/main/resources/civilization/json/Users.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(database.getUsers(), writer);
            writer.close();
        }
    }

    public static Boolean signUp(String username, String nickname, String password) {
        User user;
        if (database.getUserByUsername(username) != null) {
            Menu.showPopUp("user with username " + username + " already exists");
            return false;
        }
        if (database.getUserByNickname(nickname) != null) {
            Menu.showPopUp("user with nickname " + nickname + " already exists");
            return false;
        }
        Random random = new Random();
        int i = random.nextInt(Avatar.values().length);
        String randomAvatar = Avatar.values()[i].getUrl();
        database.addUser((user = new User(username, password, nickname, randomAvatar, null)));
        database.setCurrentUser(user);
        return true;
    }

    public static Boolean login(String username, String password) {
        User user;
        if ((user = database.getUserByUsername(username)) == null) {
            Menu.showPopUp("no account with this username exists");
            return false;
        }
        if (!user.getPassword().equals(password)) {
            Menu.showPopUp("incorrect password");
            return false;
        }
        database.setCurrentUser(user);
        return true;
    }

    public String run() {
        return RegisterMenuView.run();
    }

}
