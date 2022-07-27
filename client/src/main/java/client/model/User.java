package client.model;

public class User {
    private static String jwt;
    private static boolean isCustomAvatar;

    public User() {
    }

    public static String getJwt() {
        return jwt;
    }

    public static void setJwt(String jwt) {
        User.jwt = jwt;
    }

    public static boolean isCustomAvatar() {
        return isCustomAvatar;
    }

    public static void setCustomAvatar(boolean customAvatar) {
        User.isCustomAvatar = customAvatar;
    }
}
