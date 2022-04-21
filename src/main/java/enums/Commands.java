package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Commands {
    CREATEUSER1("\\s*user\\s+create\\s+(--username|-u)\\s+(?<username>\\S+)\\s+(--nickname|-n)\\s+(?<nickname>\\S+)\\s+(--password|-p)\\s+(?<password>\\S+)\\s*"),
    CREATEUSER2("\\s*user\\s+create\\s+(--username|-u)\\s+(?<username>\\S+)\\s+(--password|-p)\\s+(?<password>\\S+)\\s+(--nickname|-n)\\s+(?<nickname>\\S+)\\s*"),
    CREATEUSER3("\\s*user\\s+create\\s+(--password|-p)\\s+(?<password>\\S+)\\s+(--username|-u)\\s+(?<username>\\S+)\\s+(--nickname|-n)\\s+(?<nickname>\\S+)\\s*"),
    CREATEUSER4("\\s*user\\s+create\\s+(--password|-p)\\s+(?<password>\\S+)\\s+(--nickname|-n)\\s+(?<nickname>\\S+)\\s+(--username|-u)\\s+(?<username>\\S+)\\s*"),
    CREATEUSER5("\\s*user\\s+create\\s+(--nickname|-n)\\s+(?<nickname>\\S+)\\s+(--username|-u)\\s+(?<username>\\S+)\\s+(--password|-p)\\s+(?<password>\\S+)\\s*"),
    CREATEUSER6("\\s*user\\s+create\\s+(--nickname|-n)\\s+(?<nickname>\\S+)\\s+(--password|-p)\\s+(?<password>\\S+)\\s+(--username|-u)\\s+(?<username>\\S+)\\s*"),
    LOGIN1("\\s*user\\s+login\\s+(--username|-u)\\s+(?<username>\\S+)\\s+(--password|-p)\\s+(?<password>\\S+)\\s*"),
    LOGIN2("\\s*user\\s+login\\s+(--password|-p)\\s+(?<password>\\S+)\\s+(--username|-u)\\s+(?<username>\\S+)\\s*"),




    MENUENTER("\\s*menu\\s+enter\\s+(?<menuname>\\S+)\\s*"),
    MENUEXIT("\\s*menu\\s+exit"),
    LOGOUT("\\s*user\\s+logout"),
    SHOWMENU("\\s*menu\\s+show-current\\s*"),



    PLAYGAME("\\s*play\\s+game\\s+--player1\\s+(?<username1>\\S+)\\s+--player2(?<username2>\\S+)\\s*"),


    NICKNAMECHANGE("\\s*profile\\s+change\\s+--nickname\\s+(?<newnickname>\\S+)"),
    CHANGEPASSWORD1("\\s*profile\\s+change\\s+--password\\s+--current\\s+(?<currentpassword>\\S+)\\s+--new\\s+(?<newpassword>\\S+)\\s*"),
    CHANGEPASSWORD2("\\s*profile\\s+change\\s+--new\\s(?<newpassword>\\S+)\\s+--password\\s+--current\\s(?<currentpassword>\\S+)\\s*");







    private String regex;
    Commands (String regex){
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, Commands command){
        Matcher matcher = Pattern.compile(command.regex, Pattern.CASE_INSENSITIVE).matcher(input);
        if(matcher.matches()) return matcher;
        return null;
    }
}
