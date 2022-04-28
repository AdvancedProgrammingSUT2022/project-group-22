package views;


import enums.*;
import java.util.*;


public class MapView {
    private static MapView instance = null;

    public static MapView getInstance() {
        instance = instance != null ? instance : new MapView();
        return instance;
    }

    public void printMap(ArrayList<TileView> tiles) {
        int temp = 0;
        for (int i = 0; i < 7; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 3; j++) {

                    for (int k = 2 - j; k > 0; k--) {
                        System.out.print(Color.RESET.getColor() + " ");
                    }

                    if (i == 6) {
                        System.out.print(Color.RESET.getColor() + " ");
                    }

                    for (int l = 0; l < 3; l++) {

                        if (temp < 18) {
                            System.out.print(tiles.get(temp).hasRiver.get(5) + "/");
                            for (int k = 2 * j + 5; k > 0; k--) {
                                System.out.print(tiles.get(temp).getColor() + " ");
                            }
                        } else {
                            for (int k = 2 * j + 5; k > 0; k--) {
                                System.out.print(Color.RESET.getColor() + " ");
                            }
                        }

                        System.out.print(tiles.get(temp).hasRiver.get(1) + "\\");

                        if (temp >= 5) {
                            for (int k = -2 * j + 9; k > 0; k--) {
                                if (j == 2) {
                                    System.out.print(tiles.get(temp - 5).hasRiver.get(3) + "-");
                                } else {
                                    System.out.print(tiles.get(temp - 5).getColor() + " ");
                                }
                            }
                        } else {
                            for (int k = -2 * j + 9; k > 0; k--) {
                                if (j == 2) {
                                    System.out.print(Color.RESET.getColor() + "-");
                                } else {
                                    System.out.print(Color.RESET.getColor() + " ");
                                }
                            }
                        }

                        if ((temp - 5 >= 0 && l == 2) || temp > 17) {
                            System.out.print(tiles.get(temp - 5).hasRiver.get(2) + "/" + Color.RESET.getColor());
                        }

                        temp += 2;
                    }
                    temp -= 6;
                    System.out.println(Color.RESET.getColor());
                }
            } else {
                for (int j = 0; j < 3; j++) {

                    for (int k = j; k > 0; k--) {
                        System.out.print(Color.RESET.getColor() + " ");
                    }

                    for (int l = 0; l < 3; l++) {
                        System.out.print(tiles.get(temp).hasRiver.get(4) + "\\");
                        for (int k = -2 * j + 9; k > 0; k--) {
                            if (j == 2) {
                                System.out.print(tiles.get(temp).hasRiver.get(3) + "-");
                            } else {
                                System.out.print(tiles.get(temp).getColor() + " ");
                            }
                        }
                        System.out.print(tiles.get(temp).hasRiver.get(2) + "/");
                        temp++;
                        for (int k = 2 * j + 5; k > 0; k--) {
                            System.out.print(tiles.get(temp).getColor() + " ");
                        }
                        if (l == 2) {
                            System.out.print(tiles.get(temp).hasRiver.get(1) + "\\");
                        }
                        temp++;

                    }
                    temp -= 6;
                    System.out.println(Color.RESET.getColor());
                }
                temp += 6;
            }
        }
    }
}
