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
    CHANGEPASSWORD2("\\s*profile\\s+change\\s+--new\\s(?<newpassword>\\S+)\\s+--password\\s+--current\\s(?<currentpassword>\\S+)\\s*"),



    //game enums
    INFORESEARCH("\\s*info\\s+research\\s*"),
    INFOUNIT("\\s*info\\s+unit\\s*"),
    INFOCITY("\\s*info\\s+city\\s*"),
    INFODIPLOMACY("\\s*info\\s+diplomacy\\s*"),
    INFOVICTORY("\\s*info\\s+victory\\s*"),
    INFODEMOGRAPHICS("\\s*info\\s+demographics\\s*"),
    INFONOTIFICATIONS("\\s*info\\s+notification\\s*"),
    INFOMILITARY("\\s*info\\s+military\\s*"),
    INFOECONOMIC("\\s*info\\s+economic\\s*"),
    INFODIPLOMATIC("\\s*info\\s+diplomacy\\s*"),
    INFODEALS("\\s*info\\s+deals\\s*"),
    SELECTUNITCOMBAT("\\s*select\\s+unit\\s+combat\\s+(?<position>\\d+)\\s*"),
    SELECTUNITNONCOMBAT("\\s*select\\s+unit\\s+non\\s+combat\\s+(?<position>\\d+)\\s*"),
    SELECTCITYNAME("\\s*select\\s+city\\s+(?<cityname>\\S+)\\s*"),
    SELECTCITYPOS("\\s*select\\s+city\\s+(?<position>\\d+)\\s*");







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
