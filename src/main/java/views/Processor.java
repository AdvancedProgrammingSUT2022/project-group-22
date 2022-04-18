package views;

import models.*;
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

    public void printMap(ArrayList<Tile> tiles) {
    }

    public void printOutput(String output) {
        System.out.println(output);
    }

    public void closeScanner() {
        scanner.close();
    }
}
