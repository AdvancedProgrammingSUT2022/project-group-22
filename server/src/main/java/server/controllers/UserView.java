package server.controllers;

import javafx.scene.image.ImageView;

import java.time.LocalDateTime;

public class UserView {
    private String username;
    private String nickname;
    private ImageView avatar;
    private int score;
    private String lastWinTime;
    private String lastLoginTime;
    private Boolean isCurrentUser;

    public UserView(String username, String nickname, ImageView avatar, int score, String lastWinTime,
                    String lastLoginTime, Boolean isCurrentUser) {
        this.nickname = nickname;
        this.avatar = avatar;
        this.score = score;
        this.lastWinTime = lastWinTime;
        this.lastLoginTime = lastLoginTime;
        this.isCurrentUser = isCurrentUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public ImageView getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageView avatar) {
        this.avatar = avatar;
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

    public Boolean isCurrentUser() {
        return isCurrentUser;
    }

    public void setIsCurrentUser(Boolean isCurrentUser) {
        this.isCurrentUser = isCurrentUser;
    }
}
