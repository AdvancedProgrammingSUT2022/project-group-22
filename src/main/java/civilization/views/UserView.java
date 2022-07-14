package civilization.views;

import javafx.scene.image.ImageView;

import java.time.LocalDateTime;

public class UserView {
    private String nickname;
    private ImageView avatar;
    private int score;
    private LocalDateTime lastWinTime;
    private LocalDateTime lastLoginTime;
    private Boolean isCurrentUser;

    public UserView(String nickname, ImageView avatar, int score, LocalDateTime lastWinTime,
            LocalDateTime lastLoginTime, Boolean isCurrentUser) {
        this.nickname = nickname;
        this.avatar = avatar;
        this.score = score;
        this.lastWinTime = lastWinTime;
        this.lastLoginTime = lastLoginTime;
        this.isCurrentUser = isCurrentUser;
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

    public Boolean isCurrentUser() {
        return isCurrentUser;
    }

    public void setIsCurrentUser(Boolean isCurrentUser) {
        this.isCurrentUser = isCurrentUser;
    }
}
