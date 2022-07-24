package client.model;

public class User {
    private static String jwt;

    public User(){}

    public static String getJwt() {
        return jwt;
    }

    public static void setJwt(String jwt) {
        User.jwt = jwt;
    }
}
