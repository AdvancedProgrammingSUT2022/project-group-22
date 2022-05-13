package enums;

public enum Command {
        // user
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
        LOGOUT("user logout"),

        NICKNAMECHANGE("profile change (--nickname|-n) (?<newnickname>\\S+)"),
        CHANGEPASSWORD1(
                        "profile change --password --current (?<currentpassword>\\S+) --new (?<newpassword>\\S+)"),
        CHANGEPASSWORD2(
                        "profile change --new\\s(?<newpassword>\\S+) --password --current\\s(?<currentpassword>\\S+)"),

        // menu navigation
        MENUENTER("menu enter (?<menuname>\\S+)"),
        MENUEXIT("menu exit"),
        SHOWMENU("menu show-current"),
        PLAYGAME("play game --player1 (?<username1>\\S+) --player2 (?<username2>\\S+)"),

        // game enums
        // info
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

        // select
        SELECTUNITCOMBAT("select unit combat (?<i>\\d+) (?<j>\\d+)"),
        SELECTUNITNONCOMBAT("select unit noncombat (?<i>\\d+) (?<j>\\d+)"),
        SELECTCITYNAME("select city (?<name>\\S+)"),
        SELECTCITYPOSITION("select city (?<i>\\d+) (?<j>\\d+)"),

        // unit
        MOVETO("unit move to (?<i>\\d+):(?<j>\\d+)"),
        SLEEP("unit sleep"),
        ALERT("unit alert"),
        FORTIFY("unit fortify"),
        FORTIFYHEAL("unit fortify heal"),
        GARRISON("unit garrison"),
        SETUP("unit setup ranged"),
        ATTACK("unit attack position (?<positionX>\\d+) (?<positionY>\\d+)"),
        FOUND("unit found city"),
        CANCEL("unit cancel mission"),
        WAKE("unit wake"),
        DELETE("unit delete"),

        // build
        BUILDROAD("unit build road"),
        BUILDRAILROAD("unit build rail road"),
        BUILDFARM("unit build farm"),
        BUILDMINE("unit build mine"),
        BUILDTRADINGPOST("unit build trading post"),
        BUILDLUMBERMILL("unit build lumbermill"),
        BUILDPASTURE("unit build pasture"),
        BUILDCAMP("unit build camp"),
        BUILDPLANTATION("unit build plantation"),
        BUILDQUARRY("unit build quarry"),

        REMOVEJUNGLE("unit build jungle"),
        REMOVEROUTE("unit build route"),
        REPAIR("unit repair"),

        NEXT("next"),

        // city
        SETCITIZEN("lock citizen"),
        BUYTILE("buy tile (?<i>\\d+) (?<j>\\d+)"),
        REMOVECITIZEN("remove citizen (?<nmber>\\d+)"),

        // print map
        PRINTAREA("print map (?<i1>\\d+) (?<j1>\\d+) (?<i2>\\d+) (?<j2>\\d+)"),
        PRINTCITY("print map (?<name>\\w+)"),
        PRINTTILE("print map (?<i>\\d+) (?<j>\\d+)"),
        PRINTUNITPOSITION("print map current unit position"),
        MAPMOVER("map move right"),
        MAPMOVEL("map move left"),
        MAPMOVEU("map move up"),
        MAPMOVED("map move down"),

        // cheat sheet
        INCREASETURN("increase -turn (?<amount>\\d+)"),
        INCREASEGOLD("increase gold (?<amount>\\d+)"),
        INSTANTBUILD("instant build (?<building>\\w+) (?<i>\\d+):(?<j>\\d+)");

        private String regex;

        Command(String regex) {
                this.regex = regex;
        }

        public String getRegex() {
                return this.regex;
        }

}
