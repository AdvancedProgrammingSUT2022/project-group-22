package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Commands {
    CREATEUSER1(
            "user create (--username|-u) (?<username>\\S+) (--nickname|-n) (?<nickname>\\S+) (--password|-p) (?<password>\\S+)"),
    CREATEUSER2(
            "user create (--username|-u) (?<username>\\S+) (--password|-p) (?<password>\\S+) (--nickname|-n) (?<nickname>\\S+)"),
    CREATEUSER3(
            "user create (--password|-p) (?<password>\\S+) (--username|-u) (?<username>\\S+) (--nickname|-n) (?<nickname>\\S+)"),
    CREATEUSER4(
            "user create (--password|-p) (?<password>\\S+) (--nickname|-n) (?<nickname>\\S+) (--username|-u) (?<username>\\S+)"),
    CREATEUSER5(
            "user create (--nickname|-n) (?<nickname>\\S+) (--username|-u) (?<username>\\S+) (--password|-p) (?<password>\\S+)"),
    CREATEUSER6(
            "user create (--nickname|-n) (?<nickname>\\S+) (--password|-p) (?<password>\\S+) (--username|-u) (?<username>\\S+)"),
    LOGIN1("user login (--username|-u) (?<username>\\S+) (--password|-p) (?<password>\\S+)"),
    LOGIN2("user login (--password|-p) (?<password>\\S+) (--username|-u) (?<username>\\S+)"),

    MENUENTER("menu enter (?<menuname>\\S+)"),
    MENUEXIT("menu exit"),
    LOGOUT("user logout"),
    SHOWMENU("menu show-current"),

    PLAYGAME("play game --player1 (?<username1>\\S+) --player2(?<username2>\\S+)"),

    NICKNAMECHANGE("profile change --nickname (?<newnickname>\\S+)"),
    CHANGEPASSWORD1(
            "profile change --password --current (?<currentpassword>\\S+) --new (?<newpassword>\\S+)"),
    CHANGEPASSWORD2(
            "profile change --new\\s(?<newpassword>\\S+) --password --current\\s(?<currentpassword>\\S+)"),

    // game enums
    INFORESEARCH("info research"),
    INFOUNIT("info unit"),
    INFOCITY("info city"),
    INFODIPLOMACY("info diplomacy"),
    INFOVICTORY("info victory"),
    INFODEMOGRAPHICS("info demographics"),
    INFONOTIFICATIONS("info notification"),
    INFOMILITARY("info military"),
    INFOECONOMIC("info economic"),
    INFODIPLOMATIC("info diplomacy"),
    INFODEALS("info deals");

    private String regex;

    Commands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, Commands command) {
        String regex = "\\s*" + command.regex.replace(" ", " ") + "\\s*";
        Matcher matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(input);
        return matcher.matches() ? matcher : null;

    }
}
