package controllers;

import java.util.*;
import models.*;
import views.TileView;
import enums.*;

public class MapController {
    // neighboring tiles of map[i][j] : map[i][j - 1] / [i][j + 1] / [i + 1][j] /
    // [i - 1][j] / [i - 1][j - 1] / [i - 1][j + 1]
    private final Random random = new Random();
    Database database = Database.getInstance();
    Player player = database.getCurrentPlayer();

    public Tile[][] generateTiles(int x, int y) {
        Tile[][] map = new Tile[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int numTile = i * y + j;
                LandType landType = LandType.randomLandType();
                Feature feature = LandType.randomFeature(landType);
                Resource resource = LandType.randomResource(landType);
                map[i][j] = new Tile(numTile, landType, feature, resource);
            }
        }
        return map;
    }

    public void addRivers(Boolean[][] rivers, int y, int i, int j) {
        rivers[i * y + j][i * y + j + 1] = random.nextBoolean();
        rivers[i * y + j][i * y + j - 1] = random.nextBoolean();
        rivers[i * y + j][(i + 1) * y + j] = random.nextBoolean();
        rivers[i * y + j][(i - 1) * y + j] = random.nextBoolean();
        rivers[i * y + j][(i - 1) * y + j + 1] = random.nextBoolean();
        rivers[i * y + j][(i - 1) * y + j - 1] = random.nextBoolean();
    }

    public void hasRiver(Boolean[][] rivers, int y, int i, int j) {
        Boolean[] hasRiver;
        rivers[i * y + j][i * y + j + 1] = hasRiver[0];
        rivers[i * y + j][i * y + j - 1] = hasRiver[1];
        rivers[i * y + j][(i + 1) * y + j] = hasRiver[2];
        rivers[i * y + j][(i - 1) * y + j] = hasRiver[3];
        rivers[i * y + j][(i - 1) * y + j + 1] = hasRiver[4];
        rivers[i * y + j][(i - 1) * y + j - 1] = hasRiver[5];
    }

    public Boolean[][] generateRivers(Tile[][] map, int x, int y) {
        Boolean[][] rivers = new Boolean[x * y * x * y][x * y * x * y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (map[i][j].getFeature().equals(Feature.FLOODPLAIN)) {
                    addRivers(rivers, y, i, j);
                } else if (!map[i][j].getLandType().equals(LandType.DESERT) && map[i][j].getResource() != null) {
                    if (random.nextInt(5) == 5) {
                        addRivers(rivers, y, i, j);
                    }
                }
            }
        }
        return rivers;
    }

    public void printMap(Tile[][] map, int x1, int y1, int x2, int y2) {
        ArrayList<TileView> tileView = new ArrayList<TileView>();
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                tileView.add(
                        TileView(map[i][j].getLandType().getColor.getColor(), database.getUserByPLayer(map[i][j].getPlayer()).getNickname(), database.getMilitaryUnitByTile().getType().name(), database.getCivilianUnitByTile().getType().name(), map[i][j].getFeature().name(), hasRiver);
            }
        }
        mapView.printMap(tileView);
    }
}
