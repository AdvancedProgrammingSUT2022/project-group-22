package Model;

public class Database {
    private static Database instance = new Database();
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
}
