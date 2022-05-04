package views;

import enums.*;
import java.util.*;

public class MapView {
    private static MapView instance = null;

    public static MapView getInstance() {
        instance = instance != null ? instance : new MapView();
        return instance;
    }

    public void printMap(ArrayList<TileView> tiles, int y, int x) {
        int temp = 0;
        for (int i = 0; i < 2 * x + 1; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 3; j++) {

                    for (int k = 2 - j; k > 0; k--) {
                        System.out.print(Color.RESET.getColor() + " ");
                    }

                    for (int l = 0; l < y; l++) {
                        if (l % 2 == 0) {
                            System.out.print(tiles.get(temp).getHasRiver().get(5) + "/");
                            if (j == 0) {
                                System.out.print(tiles.get(temp).getColor()[0] + tiles.get(temp).getBackgroundColor() + "  " + tiles.get(temp).getNickname().charAt(0) + "  ");
                            } else if (j == 1) {
                                System.out.print(tiles.get(temp).getBackgroundColor() + " ");
                                System.out.printf("%02d,%02d", tiles.get(temp).getX(), tiles.get(temp).getY());
                                System.out.print(tiles.get(temp).getBackgroundColor() + " ");
                            } else {
                                System.out.print(tiles.get(temp).getBackgroundColor() + " " + tiles.get(temp).getColor()[1] + tiles.get(temp).getCivilianUnit().substring(0, 3) + " " + tiles.get(temp).getColor()[2] + tiles.get(temp).getMilitaryUnit().substring(0, 3) + " ");
                            }
                            System.out.print(tiles.get(temp).getHasRiver().get(1) + "\\");
                        } else {
                            if (j == 0) {
                                System.out.print(tiles.get(temp - y).getBackgroundColor() + " " + tiles.get(temp - y).getFeature().substring(0, 3) + " " + tiles.get(temp - y).getResourceTileView().substring(0, 3) + " ");
                            } else if (j == 1) {
                                System.out.print(tiles.get(temp - y).getBackgroundColor() + "  " + tiles.get(temp - y).getImprovement().substring(0, 3) + "  ");
                            } else {
                                System.out.print(tiles.get(temp - y).getHasRiver().get(3) + "-----");
                            }
                        }
                        temp++;
                    }
                    temp -= (y - 1);
                    System.out.println(Color.RESET.getColor());
                }
            } else {
                for (int j = 0; j < 3; j++) {

                    for (int k = j; k > 0; k--) {
                        System.out.print(Color.RESET.getColor() + " ");
                    }

                    for (int l = 0; l < y; l++) {
                        if (l % 2 == 0) {
                            System.out.print(tiles.get(temp).getHasRiver().get(4) + "\\");
                            if (j == 0) {
                                System.out.print(tiles.get(temp).getBackgroundColor() + " " + tiles.get(temp).getFeature().substring(0, 3) + " " + tiles.get(temp - y).getResourceTileView().substring(0, 3) + " ");
                            } else if (j == 1) {
                                System.out.print(tiles.get(temp).getBackgroundColor() + "  " + tiles.get(temp).getImprovement().substring(0, 3) + "  ");
                            } else {
                                System.out.print(tiles.get(temp).getHasRiver().get(3) + "-----");
                            }
                            System.out.print(tiles.get(temp).getHasRiver().get(2) + "/");
                        } else {
                            if (j == 0) {
                                System.out.print(tiles.get(temp).getColor()[0] + tiles.get(temp).getBackgroundColor() + "  " + tiles.get(temp).getNickname().charAt(0) + "  ");
                            } else if (j == 1) {
                                System.out.print(tiles.get(temp).getBackgroundColor() + " ");
                                System.out.printf("%02d,%02d", tiles.get(temp).getX(), tiles.get(temp).getY());
                                System.out.print(tiles.get(temp).getBackgroundColor() + " ");
                            } else {
                                System.out.print(tiles.get(temp).getBackgroundColor() + " " + tiles.get(temp).getColor()[1] + tiles.get(temp).getCivilianUnit().substring(0, 3) + " " + tiles.get(temp).getColor()[2] + tiles.get(temp).getMilitaryUnit().substring(0, 3) + " ");
                            }
                        }
                        temp++;
                    }
                    temp -= y;
                    System.out.println(Color.RESET.getColor());
                }
                temp += y;
            }
        }
    }
}
