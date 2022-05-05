package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Command {

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
        INFODEALS("info deals"),
        SELECTUNITCOMBAT("select unit combat (?<positionX>\\d+) (?<positionY>\\d+)"),
        SELECTUNITNONCOMBAT("select unit noncombat (?<positionX>\\d+) (?<positionY>\\d+)"),
        SELECTCITYNAME("select city name (?<name>\\S+)"),
        SELECTCITYPOSITION("select city position (?<positionX>\\d+) (?<positionY>\\d+)"),
        MOVETO("unit move to (?<position>\\d+:\\d+)"),
        SLEEP("unit sleep"),
        ALERT("unit alert"),
        FORTIFY("unit fortify"),
        FORTIFYHEAL("unit fortify heal"),
        GARRISON("unit garrison"),
        SETUP("unit setup ranged"),
        ATTACK("unit attack position (?<position>\\d+:\\d+)"),
        FOUND("unit found city"),
        CANCEL("unit cancel mission"),
        WAKE("unit wake"),
        DELETE("unit delete"),
        BUILDROAD("unit build road"),
        BUILDRAILROAD("unit build rail road"),
        BUILDFARM("unit build farm"),
        BUILDMINE("unit build mine"),
        BUILDTRADINGPOST("unit build trading post"),
        BUILDLAMBERMILL("unit build lamber miil"),
        BUILDPASTURE("unit build pasture"),
        BUILDCAMP("unit build camp"),
        BUILDPLANTATION("unit build plantation"),
        BUILDQUARRY("unit build quarry"),
        REMOVEJUNGLE("unit build jungle"),
        REMOVEROUTE("unit build route"),
        REPAIR("unit repair"),
        MAPSHOWPOS("map show position (?<position>\\d+:\\d+)"),
        MAPSHOWCITY("map show city (?<city>\\S+)"),
        MAPMOVER("map move right"),
        MAPMOVEL("map move left"),
        MAPMOVEU("map move up"),
        MAPMOVED("map move down"),




        //cheat sheet
        INCREASETURN("increase -turn (?<amount>\\d+)"),
        INCREASEGOLD("increase gold (?<amount>\\d+)"),


        PRINTMAP("print map");

        private String regex;

        Command(String regex) {
                this.regex = regex;
        }

        public static Matcher getMatcher(String input, Command command) {
                String regex = "\\s*" + command.regex.replace(" ", "\\s+") + "\\s*";
                Matcher matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(input);
                return matcher.matches() ? matcher : null;
        }
}
