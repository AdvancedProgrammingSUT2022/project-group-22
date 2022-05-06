package views;

import enums.*;
import java.util.*;
import java.util.regex.*;

public class Processor {
    private static final Processor instance = new Processor();
    private static final Scanner scanner = new Scanner(System.in);

    public static Processor getInstance() {
        return instance;
    }

    public static Matcher getMatcher(String input, Command command) {
        String regex = "\\s*" + command.getRegex().replace(" ", "\\s+") + "\\s*";
        Matcher matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(input);
        return matcher.matches() ? matcher : null;
    }

    public static String getInput() {
        return scanner.nextLine();
    }

    public void printOutput(String output) {
        System.out.println(output);
    }

    public void closeScanner() {
        scanner.close();
    }
}
