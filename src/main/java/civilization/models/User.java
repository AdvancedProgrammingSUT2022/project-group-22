package civilization.models;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Base64;

import civilization.App;
import civilization.enums.Avatar;
import javafx.scene.image.*;

public class User {
    private String username;
    private String password;
    private String nickname;
    private ImageView avatar;
    private Civilization civilization;
    private int score;
    private LocalDateTime lastWinTime;
    private LocalDateTime lastLoginTime;

    public User(String username, String password, String nickname, ImageView avatar,
            Civilization civilization, int score, LocalDateTime lastWinTime, LocalDateTime lastLoginTime) {
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

    public ImageView getAvatar() {
        return avatar;
//        return new ImageView(new Image(App.class.getResource(this.avatar.getUrl()).toExternalForm()));
    }

    public void setAvatar(ImageView avatar) {
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

    public LocalDateTime getLastWinTime() {
        return lastWinTime;
    }

    public void setLastWinTime(LocalDateTime lastWinTime) {
        this.lastWinTime = lastWinTime;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
