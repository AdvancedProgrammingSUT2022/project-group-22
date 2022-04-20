package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Commands {
    CREATEUSER1("\\s*user\\s+create\\s+(--username|-u)\\s+(?<username>\\w+)\\s+(--nickname|-n)\\s+(?<nickname>\\w+)\\s+(--password|-p)\\s+(?<password>\\w+)\\s*"),
    CREATEUSER2("\\s*user\\s+create\\s+(--username|-u)\\s+(?<username>\\w+)\\s+(--password|-p)\\s+(?<password>\\w+)\\s+(--nickname|-n)\\s+(?<nickname>\\w+)\\s*"),
    CREATEUSER3("\\s*user\\s+create\\s+(--password|-p)\\s+(?<password>\\w+)\\s+(--username|-u)\\s+(?<username>\\w+)\\s+(--nickname|-n)\\s+(?<nickname>\\w+)\\s*"),
    CREATEUSER4("\\s*user\\s+create\\s+(--password|-p)\\s+(?<password>\\w+)\\s+(--nickname|-n)\\s+(?<nickname>\\w+)\\s+(--username|-u)\\s+(?<username>\\w+)\\s*"),
    CREATEUSER5("\\s*user\\s+create\\s+(--nickname|-n)\\s+(?<nickname>\\w+)\\s+(--username|-u)\\s+(?<username>\\w+)\\s+(--password|-p)\\s+(?<password>\\w+)\\s*"),
    CREATEUSER6("\\s*user\\s+create\\s+(--nickname|-n)\\s+(?<nickname>\\w+)\\s+(--password|-p)\\s+(?<password>\\w+)\\s+(--username|-u)\\s+(?<username>\\w+)\\s*");






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
