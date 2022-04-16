package Model;

public class User {
    private String userName;
    private String passWord;
    private String nickName;
    private Player player;

    public void setPlayer(Player player){
        this.player = player;
    }

    public String getUserName(){
        return userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
