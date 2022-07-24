package server.models;

import javafx.scene.image.ImageView;
import server.enums.Avatar;

import java.time.LocalDateTime;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private Civilization civilization;
    private int score;
    private String lastWinTime;
    private String lastLoginTime;

    public User(String username, String password, String nickname, String avatar,
            Civilization civilization, int score, String lastWinTime, String lastLoginTime) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.avatar = avatar;
        this.civilization = civilization;
        this.score = score;
        this.lastWinTime = lastWinTime;
        this.lastLoginTime = lastLoginTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
//        return new ImageView(new Image(App.class.getResource(this.avatar.getUrl()).toExternalForm()));
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    // public void setAvatar(URL url) {
    // try {
    // File file = new File(url.toURI());
    // byte[] bytes = Files.readAllBytes(file.toPath());
    // this.avatar = Base64.getEncoder().encodeToString(bytes);
    // } catch (IOException | URISyntaxException e) {
    // e.printStackTrace();
    // }
    // }

    public Civilization getCivilization() {
        return this.civilization;
    }

    public void setCivilization(Civilization civilization) {
        this.civilization = civilization;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLastWinTime() {
        return lastWinTime;
    }

    public void setLastWinTime(String lastWinTime) {
        this.lastWinTime = lastWinTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
