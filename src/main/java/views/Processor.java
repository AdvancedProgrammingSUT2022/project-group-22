package views;

import models.*;
import java.util.*;

public class Processor {
    private static final Processor instance = new Processor();
    private static final Scanner scanner = new Scanner(System.in);

    public static Processor getInstance() {
        return instance;
    }

    public String getInput() {
        return scanner.nextLine();
    }



//    public void printOutput(String output) {
//        System.out.println(output);
//    }

    public void closeScanner() {
        scanner.close();
    }
}
