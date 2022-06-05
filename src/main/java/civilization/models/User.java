package civilization.models;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String avatarAddress;
    private Civilization civilization;

    public User(String username, String password, String nickname, String avatarAddress, Civilization civilization) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.avatarAddress = avatarAddress;
        this.civilization = civilization;
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
        return civilization;
    }

    public void setCivilization(Civilization civilization) {
        this.civilization = civilization;
    }
}
