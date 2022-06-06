package civilization.models;

import java.time.LocalDateTime;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String avatarAddress;
    private Civilization civilization;
    private int score;
    private LocalDateTime lastWinTime;
    private LocalDateTime lastActivityTime;

    public User(String username, String password, String nickname, String avatarAddress,
            Civilization civilization, int score, LocalDateTime lastWinTime, LocalDateTime lastActivityTime) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.avatarAddress = avatarAddress;
        this.civilization = civilization;
        this.score = score;
        this.lastWinTime = lastWinTime;
        this.lastActivityTime = lastActivityTime;
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

    public String getAvatarAddress() {
        return avatarAddress;
    }

    public void setAvatarAddress(String avatarAddress) {
        this.avatarAddress = avatarAddress;
    }

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

    public LocalDateTime getLastActivityTime() {
        return lastActivityTime;
    }

    public void setLastActivityTime(LocalDateTime lastActivityTime) {
        this.lastActivityTime = lastActivityTime;
    }
}
