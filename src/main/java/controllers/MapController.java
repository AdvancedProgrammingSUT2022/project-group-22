package controllers;

import enums.*;
import models.*;
import views.*;
import java.util.*;
import java.util.regex.*;

public class MapController extends GameController {
    private static MapController instance = null;
    private final Random random = new Random();

    public static MapController getInstance() {
        instance = instance != null ? instance : new MapController();
        return instance;
    }

    // sample map
    private LandType[][] landTypes1 = {
            { LandType.MOUNTAIN, LandType.MOUNTAIN, LandType.DESERT, LandType.HIILS, LandType.PLAIN, LandType.PLAIN,
                    LandType.SNOW, LandType.SNOW },
            { LandType.MOUNTAIN, LandType.PLAIN, LandType.DESERT, LandType.HIILS, LandType.PLAIN, LandType.GRASSLAND,
                    LandType.TUNDRA, LandType.TUNDRA },
            { LandType.PLAIN, LandType.PLAIN, LandType.HIILS, LandType.HIILS, LandType.PLAIN, LandType.PLAIN,
                    LandType.PLAIN, LandType.GRASSLAND },
            { LandType.OCEAN, LandType.OCEAN, LandType.GRASSLAND, LandType.GRASSLAND, LandType.MOUNTAIN,
                    LandType.MOUNTAIN, LandType.GRASSLAND, LandType.DESERT },
            { LandType.OCEAN, LandType.OCEAN, LandType.GRASSLAND, LandType.OCEAN, LandType.GRASSLAND,
                    LandType.GRASSLAND, LandType.DESERT, LandType.DESERT }
    };

    private Feature[][] features1 = {
            { null, null, Feature.FLOODPLAIN, null, Feature.JUNGLE, Feature.JUNGLE, null, null },
            { null, null, Feature.FLOODPLAIN, null, Feature.FOREST, Feature.JUNGLE, null, null },
            { null, null, null, Feature.FOREST, Feature.FOREST, null, null, null },
            { null, null, Feature.SWAMP, null, null, null, null, null },
            { null, null, null, null, null, Feature.SWAMP, null, null }
    };

    // generation
    public void setMap(Tile[][] map, int x, int y) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int[] coordinates = { i, j };
                map[i][j] = new Tile(coordinates, landTypes1[i][j], features1[i][j], null);
            }
        }
    }

    public void generateTiles(Tile[][] map, int x, int y) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                LandType landType = LandType.DESERT;
                landType = landType.randomLandType();
                Feature feature = landType.randomFeature();
                Resource resource = null;
                if (feature != null) {
                    resource = landType.randomResource(feature);
                }
                int[] coordinates = { i, j };
                map[i][j] = new Tile(coordinates, landType, feature, resource);
            }
        }
    }

    public void addRivers(Tile tile, Boolean addRivers) {
        Boolean hasRiver;
        for (int i = 0; i < 6; i++) {
            hasRiver = addRivers ? random.nextBoolean() : false;
            tile.setHasRiver(i, hasRiver);
            if (database.getNeighbor(tile, i) != null) {
                database.getNeighbor(tile, i).setHasRiver((i + 3) % 6, hasRiver);
            }
        }
    }

    public void generateRivers(Tile[][] map, int x, int y) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Tile tile = map[i][j];
                if (tile.getFeature() != null && tile.getFeature().equals(Feature.FLOODPLAIN)) {
                    addRivers(tile, true);
                } else if (!tile.getLandType().equals(LandType.DESERT) && !tile.getLandType().equals(LandType.SNOW)
                        && tile.getResource() != null) {
                    addRivers(tile, random.nextInt(6) == 5 ? true : false);
                } else {
                    addRivers(tile, false);
                }
            }
        }
    }

    public ArrayList<String> getRiverColor(Boolean[] hasRiver) {
        ArrayList<String> riverColor = new ArrayList<String>();
        for (int i = 0; i < 6; i++) {
            riverColor.add(hasRiver[i] ? Color.CYAN_BRIGHT_BG.getColor() : Color.RESET.getColor());
        }
        return riverColor;
    }

    public Boolean canShowResource(Resource resource) {
        if (resource == null) {
            return false;
        } else if (resource.getType().equals("STRATEGIC")) {
            return database.getCurrentPlayer().getCivilization().hasTechnology(resource.getTechnology());
        } else {
            return true;
        }
    }

    public TileView generateTileView(Tile tile) {
        User player = tile.getPlayer();
        CivilianUnit civUnit = database.getCivilianUnitByTile(tile);
        MilitaryUnit milUnit = database.getMilitaryUnitByTile(tile);
        String[] colors = {
                player == null ? Color.WHITE.getColor()
                        : player.getCivilization().getColor().getColor(),
                civUnit == null ? Color.WHITE.getColor()
                        : database.getUnitOwner(civUnit).getCivilization().getColor().getColor(),
                milUnit == null ? Color.WHITE.getColor()
                        : database.getUnitOwner(milUnit).getCivilization().getColor().getColor() };

        return new TileView(colors, tile.getLandType().getColor().getColor(),
                player == null ? null : database.getCurrentPlayer().getNickname(),
                milUnit == null ? null : milUnit.getUnitType().name(),
                civUnit == null ? null : civUnit.getUnitType().name(),
                tile.getFeature() == null ? null : tile.getFeature().name(),
                canShowResource(tile.getResource()) ? tile.getResource().name() : null,
                tile.getImprovement() == null ? null : tile.getImprovement().name(),
                getRiverColor(tile.getHasRiver()), tile.getCoordinates()[0], tile.getCoordinates()[1]);
    }

    public void addToTileView(ArrayList<TileView> tileView, Tile tile) {
        if (database.getCurrentPlayer().getCivilization().findTile(tile) == 1) {
            tileView.add(generateTileView(tile));
        } else if (database.getCurrentPlayer().getCivilization().findTile(tile) == 0) {
            tileView.add(generateTileView(database.getCurrentPlayer().getCivilization().getRevealedTile(tile)));
        } else {
            // tileView.add(null);
            tileView.add(generateTileView(tile));
        }
    }

    // printing
    public void printArea(Tile[][] map, int x1, int y1, int x2, int y2) {
        ArrayList<TileView> tileView = new ArrayList<TileView>();
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                addToTileView(tileView, map[i][j]);
            }
        }
        GameView.getInstance().printMap(database.getCurrentPlayer().getUsername(),
                database.getCurrentPlayer().getCivilization().getTotalHappiness(), tileView, y2 - y1 + 1, x2 - x1 + 1);
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
        GameView.getInstance().printMap(database.getCurrentPlayer().getUsername(),
                database.getCurrentPlayer().getCivilization().getTotalHappiness(),
                tileView, maxY - minY + 1, maxX - minX + 1);
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
        GameView.getInstance().printMap(database.getCurrentPlayer().getUsername(),
                database.getCurrentPlayer().getCivilization().getTotalHappiness(),
                tileView, maxY - minY + 1, maxX - minX + 1);
    }

    public void printAreaCheck(Matcher matcher) {
        int i1 = Integer.parseInt(matcher.group("i1"));
        int j1 = Integer.parseInt(matcher.group("j1"));
        int i2 = Integer.parseInt(matcher.group("i2"));
        int j2 = Integer.parseInt(matcher.group("j2"));
        if (!isValidCoordinates(i1, j1) || !isValidCoordinates(i2, j2)) {
            GameView.getInstance().invalidTile();
            return;
        } else {
            MapController.getInstance().printArea(database.getMap(), i1, j1, i2, j2);
        }
    }

    public void printCityCheck(Matcher matcher) {
        String name = matcher.group("name");
        City city;
        if ((city = database.getCityByName(name)) == null) {
            GameView.getInstance().invalidCity();
        } else {
            MapController.getInstance().printCity(city);
        }
    }

    public void printTileCheck(Matcher matcher, Command command) {
        CivilianUnit civUnit = database.getCurrentPlayer().getCivilization().getCurrentCivilian();
        MilitaryUnit milUnit = database.getCurrentPlayer().getCivilization().getCurrentMilitary();
        if (command.equals(Command.PRINTTILE)) {
            int i = Integer.parseInt(matcher.group("i"));
            int j = Integer.parseInt(matcher.group("j"));
            if (!isValidCoordinates(i, j)) {
                GameView.getInstance().invalidTile();
                return;
            } else {
                MapController.getInstance().printTile(map[i][j]);
            }

        } else {
            Unit unit = civUnit != null ? civUnit : milUnit;
            if (unit == null) {
                GameView.getInstance().noUnitSelected();
                return;
            } else {
                MapController.getInstance().printTile(unit.getPositon());
            }
        }
    }
}
