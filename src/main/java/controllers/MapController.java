package controllers;

import enums.*;
import models.*;
import views.*;
import java.util.*;

public class MapController extends GameController {
    private static MapController instance = null;
    private final Random random = new Random();

    public static MapController getInstance() {
        instance = instance != null ? instance : new MapController();
        return instance;
    }

    public void generateTiles(Tile[][] map, int x, int y) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                LandType landType = LandType.DESERT;
                landType = landType.randomLandType();
                Feature feature = landType.randomFeature();
                Resource resource = landType.randomResource(feature);
                int[] coordinates = { i, j };
                map[i][j] = new Tile(coordinates, landType, feature, resource);
            }
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

    public void generateMap(int x, int y) {
        map = new Tile[x][y];
        generateTiles(map, x, y);
        generateRivers(map, x, y);
    }

    public ArrayList<String> getRiverColor(Boolean[] hasRiver) {
        ArrayList<String> riverColor = new ArrayList<String>();
        for (int i = 0; i < 6; i++) {
            riverColor.add(hasRiver[i] ? Color.CYAN_BRIGHT_BG.getColor() : Color.RESET.getColor());
        }
        return riverColor;
    }

    public TileView generateTileView(Tile tile) {
        User player = tile.getPlayer();
        CivilianUnit civUnit = database.getCivilianUnitByTile(tile);
        MilitaryUnit milUnit = database.getMilitaryUnitByTile(tile);
        String[] colors = {
                player.equals(null) ? Color.RESET.getColor()
                        : player.getCivilization().getColor().getColor(),
                civUnit.equals(null) ? Color.RESET.getColor()
                        : database.getUnitOwner(civUnit).getCivilization().getColor().getColor(),
                milUnit.equals(null) ? Color.RESET.getColor()
                        : database.getUnitOwner(milUnit).getCivilization().getColor().getColor() };

        return new TileView(colors, tile.getLandType().getColor().getColor(),
                player.equals(null) ? "" : user.getNickname(),
                milUnit.equals(null) ? "" : milUnit.getUnitType().name(),
                civUnit.equals(null) ? "" : civUnit.getUnitType().name(),
                tile.getFeature().name(),
                tile.getResource().getType().equals("STRATEGIC") ? "" : tile.getResource().name(),
                tile.getImprovement().equals(null) ? "" : tile.getImprovement().name(),
                getRiverColor(tile.getHasRiver()), tile.getCoordinates()[0], tile.getCoordinates()[1]);
    }

    public void addToTileView(ArrayList<TileView> tileView, Tile tile) {
        if (user.getCivilization().findTile(tile) == 1) {
            tileView.add(generateTileView(tile));
        } else if (user.getCivilization().findTile(tile) == 0) {
            tileView.add(generateTileView(user.getCivilization().getRevealedTile(tile)));
        } else {
            tileView.add(null);
        }
    }

    public void printArea(Tile[][] map, int x1, int y1, int x2, int y2) {
        ArrayList<TileView> tileView = new ArrayList<TileView>();
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                addToTileView(tileView, map[i][j]);
            }
        }
        GameView.getInstance().printMap(user.getUsername(), user.getCivilization().getTotalHappiness(),
                tileView, y2 - y1, x2 - x1);
    }

    public void printCity(City city) {
        // prints city tiles only
        ArrayList<TileView> tileView = new ArrayList<TileView>();
        int minX, minY, maxX, maxY;
        minX = maxX = city.getCenter().getCoordinates()[0];
        minY = maxY = city.getCenter().getCoordinates()[1];
        for (Tile tile : city.getTiles()) {
            addToTileView(tileView, tile);
            minX = tile.getCoordinates()[0] < minX ? tile.getCoordinates()[0] : minX;
            maxX = tile.getCoordinates()[0] > maxX ? tile.getCoordinates()[0] : maxX;
            minY = tile.getCoordinates()[1] < minY ? tile.getCoordinates()[0] : minY;
            maxY = tile.getCoordinates()[1] > maxY ? tile.getCoordinates()[0] : maxY;
        }
        GameView.getInstance().printMap(user.getUsername(), user.getCivilization().getTotalHappiness(),
                tileView, maxY - minY, maxX - minX);
    }

    public void printTile(Tile tile) {
        ArrayList<TileView> tileView = new ArrayList<TileView>();
        addToTileView(tileView, tile);
        int minX, minY, maxX, maxY;
        minX = maxX = tile.getCoordinates()[0];
        minY = maxY = tile.getCoordinates()[1];
        for (int i = 0; i < 6; i++) {
            Tile neighbor = database.getNeighbor(tile, i);
            addToTileView(tileView, neighbor);
            minX = neighbor.getCoordinates()[0] < minX ? neighbor.getCoordinates()[0] : minX;
            maxX = neighbor.getCoordinates()[0] > maxX ? neighbor.getCoordinates()[0] : maxX;
            minY = neighbor.getCoordinates()[1] < minY ? neighbor.getCoordinates()[0] : minY;
            maxY = neighbor.getCoordinates()[1] > maxY ? neighbor.getCoordinates()[0] : maxY;
        }
        GameView.getInstance().printMap(user.getUsername(), user.getCivilization().getTotalHappiness(),
                tileView, maxY - minY, maxX - minX);
    }
}
