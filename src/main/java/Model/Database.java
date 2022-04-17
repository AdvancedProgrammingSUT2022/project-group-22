package Model;

import java.util.ArrayList;

public class Database {
    private static Database instance = new Database();

    private ArrayList<User> users = new ArrayList<>();

    private String state = "register";

    public static Database getInstance() {
        return instance;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ArrayList<User> getUsers(){
        return users;
    }
}
