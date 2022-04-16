package View;
import java.util.*;
public class Processor {
    private static Processor instance = new Processor();
    private static Scanner scanner = new Scanner(System.in);

    public static Processor getInstance() {
        return instance;
    }

    public String getInput() {
        return scanner.nextLine();
    }

    public void closeScanner() {
        scanner.close();
    }
}
