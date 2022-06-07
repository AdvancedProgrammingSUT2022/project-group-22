package civilization.views;

import java.time.LocalDateTime;

public class UserView {
    private String nickname;
    private String avatarAddress;
    private int score;
    private LocalDateTime lastWinTime;
    private LocalDateTime lastActivityTime;
    private Boolean isCurrentUser;

    public UserView(String nickname, String avatarAddress, int score, LocalDateTime lastWinTime,
            LocalDateTime lastActivityTime, Boolean isCurrentUser) {
        this.nickname = nickname;
        this.avatarAddress = avatarAddress;
        this.score = score;
        this.lastWinTime = lastWinTime;
        this.lastActivityTime = lastActivityTime;
        this.isCurrentUser = isCurrentUser;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarAddress() {
        return avatarAddress;
    }

    public void setAvatarAddress(String avatarAddress) {
        this.avatarAddress = avatarAddress;
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

    public LocalDateTime getLastActivityTime() {
        return lastActivityTime;
    }

    public void setLastActivityTime(LocalDateTime lastActivityTime) {
        this.lastActivityTime = lastActivityTime;
    }

    public Boolean isCurrentUser() {
        return isCurrentUser;
    }

    public void setIsCurrentUser(Boolean isCurrentUser) {
        this.isCurrentUser = isCurrentUser;
    }
}
