package controllers;

import java.util.*;
import models.*;
import views.*;
import enums.*;
import views.*;

public class MapController {
    private final Random random = new Random();
    Database database = Database.getInstance();
    Player player = database.getCurrentPlayer();

    public void generateTiles(Tile[][] map, int x, int y) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                LandType landType = LandType.randomLandType();
                Feature feature = LandType.randomFeature(landType);
                Resource resource = LandType.randomResource(landType);
                int[] coordinates = { i, j };
                map[i][j] = new Tile(coordinates, landType, feature, resource);
            }
        }

    public void addRivers(Tile tile) {
        for (int i = 0; i < 6; i++) {
            Boolean hasRiver = random.nextBoolean();
            tile.setHasRiver(i, hasRiver);
            database.getNeighbor(tile, i).setHasRiver(i - 3 % 6, hasRiver);
        }
    }

    public void generateRivers(Tile[][] map, int x, int y) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Tile tile = map[i][j];
                if (tile.getFeature().equals(Feature.FLOODPLAIN)) {
                    addRivers(tile);
                } else if (!tile.getLandType().equals(LandType.DESERT) && !tile.getLandType().equals(LandType.SNOW)
                        && tile.getResource() != null) {
                    if (random.nextInt(5) == 5) {
                        addRivers(tile);
                    }
                }
            }
        }
    }

    public Tile[][] generateMap(int x, int y) {
        Tile[][] map = new Tile[x][y];
        generateTiles(map, x, y);
        generateRivers(map, x, y);
        return map;
    }

    public ArrayList<String> getRiverColor(Boolean[] hasRiver) {
        ArrayList<String> riverColor = new ArrayList<String>();
        for (int i = 0; i < 6; i++) {
            riverColor.add(hasRiver[i] ? Color.CYAN_BRIGHT_BG.getColor() : Color.RESET.getColor());
        }
        return riverColor;
    }

    public void printMap(Tile[][] map, int x1, int y1, int x2, int y2) {
        ArrayList<TileView> tileView = new ArrayList<TileView>();
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                tileView.add(new TileView(map[i][j].getLandType().getColor().getColor(),
                        database.getUserByPLayer(map[i][j].getPlayer()).getNickname(),
                        database.getMilitaryUnitByTile(map[i][j]).getUnitType().name(),
                        database.getCivilianUnitByTile(map[i][j]).getUnitType().name(),
                        map[i][j].getFeature().name(), getRiverColor(map[i][j].getHasRiver())));
            }
        }
        // mapView.printMap(tileView);
    }
}
