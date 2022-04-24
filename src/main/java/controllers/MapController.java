//package controllers;
//
//import java.util.*;
//import models.*;
//import enums.*;
//import views.*;
//
//public class MapController {
//    // neighboring tiles of map[i][j] : map[i][j - 1] / [i][j + 1] / [i + 1][j] /
//    // [i - 1][j] / [i - 1][j - 1] / [i - 1][j + 1]
//    private final Random random = new Random();
//
//    public void randomTiles(Tile[][] map) {
//        for (int i = 0; i < 25; i++) {
//            for (int j = 0; j < 25; j++) {
//                LandType landType = LandType.randomLandType();
//                Feature feature = LandType.randomFeature(landType);
//                Resource resource = LandType.randomResource(landType);
//                map[i][j] = new Tile(landType, feature, resource);
//            }
//        }
//    }
//
//    pub
//
//    public void locateRivers(Tile[][] map) {
//        boolean[] hasRiver = new boolean[6];
//        for (int i = 0; i < 25; i++) {
//            for (int j = 0; j < 25; j++) {
//                if (map[i][j].getFeature().equals(Feature.FLOODPLAIN)) {
//                    for (int k = 0; k < 6; k++) {
//                        if (random.nextBoolean()) {
//                            addRivers();
//                        }
//                    }
//                } else if (!map[i][j].getLandType().equals(LandType.DESERT) || map[i][j].getResource() != null) {
//                    if (random.nextInt(5) == 5) {
//                        for (int k = 0; k < 6; k++) {
//                            hasRiver[k] = random.nextBoolean();
//                        }
//                    }
//                }
//                map[i][j].setHasRiver(hasRiver);
//            }
//        }
//    }
//
//    public Tile[][] generateMap(int x, int y) {
//        Tile[][] map = new Tile[x][y];
//        randomTiles(map);
//        locateRivers(map);
//        return map;
//    }
//
//    public void printMap(int x1, int y1, int x2, int y2, Tile[][] map) {
//        for (int i = x1; i <= x2; i++) {
//            for (int j = y1; j <= y2; j++) {
//                Processor.getInstance().printOutput("   " + (map[i][j].getHasRiver()[5] ? Colors.BLUE + "\\" : "\\") +);
//
//            }
//        }
//    }
//
//}
