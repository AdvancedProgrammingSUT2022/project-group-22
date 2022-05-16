package controllers;

import enums.*;
import models.*;
import views.*;
import java.util.*;
import java.util.regex.*;

public class MapController extends GameController {
    private static MapController instance = null;
    private final Random random = new Random();
    private int lastX1, lastY1, lastX2, lastY2;

    public static MapController getInstance() {
        instance = instance != null ? instance : new MapController();
        return instance;
    }

    // sample map
    private LandType[][] landTypes1 = {
            { LandType.MOUNTAIN, LandType.MOUNTAIN, LandType.DESERT, LandType.HILL, LandType.PLAIN, LandType.PLAIN,
                    LandType.SNOW, LandType.SNOW },
            { LandType.MOUNTAIN, LandType.PLAIN, LandType.DESERT, LandType.HILL, LandType.PLAIN, LandType.GRASSLAND,
                    LandType.TUNDRA, LandType.TUNDRA },
            { LandType.PLAIN, LandType.PLAIN, LandType.HILL, LandType.HILL, LandType.PLAIN, LandType.PLAIN,
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
            hasRiver = addRivers && random.nextBoolean();
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
                    addRivers(tile, random.nextInt(6) == 5);
                } else {
                    addRivers(tile, false);
                }
            }
        }
    }

    public ArrayList<String> getRiverColor(Boolean[] hasRiver) {
        ArrayList<String> riverColor = new ArrayList<String>();
        for (int i = 0; i < 6; i++) {
            if (hasRiver[i] != null) {
                riverColor.add(hasRiver[i] ? Color.CYAN_BRIGHT_BG.getColor() : Color.RESET.getColor());
            }
            else{
                riverColor.add(Color.RESET.getColor());
            }
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
                        : database.getCurrentPlayer().getCivilization().getColor().getColor(),
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
            String[] colors = { Color.WHITE.getColor(), Color.WHITE.getColor(),
                    Color.WHITE.getColor() };
            Boolean[] hasRiver = { false, false, false, false, false, false };
            tileView.add(new TileView(colors, Color.BLACK_BRIGHT_BG.getColor(), null, null, null, null, null, null,
                    getRiverColor(hasRiver), tile.getCoordinates()[0], tile.getCoordinates()[1]));
            // tileView.add(generateTileView(tile));
        }
    }

    // printing
    public void moveMap(Command command) {
        Tile[][] map = database.getMap();
        if (command.equals(Command.MAPMOVEU)) {
            if (!isValidCoordinates(lastX1 - 1, lastY1) || !isValidCoordinates(lastX2 - 1, lastY2)) {
                gameView.invalidTile();
            } else {
                printArea(map, lastX1 - 1, lastY1, lastX2 - 1, lastY2);
            }
        } else if (command.equals(Command.MAPMOVED)) {
            if (!isValidCoordinates(lastX1 + 1, lastY1) || !isValidCoordinates(lastX2 + 1, lastY2)) {
                gameView.invalidTile();
            } else {
                printArea(map, lastX1 + 1, lastY1, lastX2 + 1, lastY2);
            }
        } else if (command.equals(Command.MAPMOVEL)) {
            if (!isValidCoordinates(lastX1, lastY1 - 1) || !isValidCoordinates(lastX2, lastY2 - 1)) {
                gameView.invalidTile();
            } else {
                printArea(map, lastX1, lastY1 - 1, lastX2, lastY2 - 1);
            }
        } else {
            if (!isValidCoordinates(lastX1, lastY1 + 1) || !isValidCoordinates(lastX2, lastY2 + 1)) {
                gameView.invalidTile();
            } else {
                printArea(map, lastX1, lastY1 + 1, lastX2, lastY2 + 1);
            }
        }
    }

    public void printArea(Tile[][] map, int x1, int y1, int x2, int y2) {
        ArrayList<TileView> tileView = new ArrayList<TileView>();
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (isValidCoordinates(i, j)) {
                    addToTileView(tileView, map[i][j]);
                }
            }
        }
        lastX1 = x1;
        lastX2 = x2;
        lastY1 = y1;
        lastY2 = y2;
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
            minX = Math.min(tile.getCoordinates()[0], minX);
            maxX = Math.max(tile.getCoordinates()[0], maxX);
            minY = Math.min(tile.getCoordinates()[1], minY);
            maxY = Math.max(tile.getCoordinates()[1], maxY);
        }
        lastX1 = minX;
        lastX2 = maxX;
        lastY1 = minY;
        lastY2 = maxY;
        GameView.getInstance().printMap(database.getCurrentPlayer().getUsername(),
                database.getCurrentPlayer().getCivilization().getTotalHappiness(),
                tileView, maxY - minY + 1, maxX - minX + 1);
    }

    public void printTile(Tile tile) {
        int x1 = tile.getCoordinates()[0];
        int x2 = tile.getCoordinates()[0];
        int y1 = tile.getCoordinates()[1];
        int y2 = tile.getCoordinates()[1];

        if (x1 - 1 >= 0) {
            x1--;
        }
        if (y1 - 2 >= 0) {
            y1 -= 2;
        } else if (y1 - 1 >= 0) {
            y1--;
        }
        if (x2 + 1 < database.getMap().length) {
            x2 += 1;
        }
        if (y2 + 2 < database.getMap()[0].length) {
            y2 += 2;
        } else if (y2 + 1 < database.getMap()[0].length) {
            y2 += 1;
        }

        lastX1 = x1;
        lastX2 = x2;
        lastY1 = y1;
        lastY2 = y2;
        printArea(database.getMap(), x1, y1, x2, y2);
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
        if (command.equals(Command.PRINTTILE)) {
            int i = Integer.parseInt(matcher.group("i"));
            int j = Integer.parseInt(matcher.group("j"));
            if (!isValidCoordinates(i, j)) {
                GameView.getInstance().invalidTile();
                return;
            } else {
                MapController.getInstance().printTile(database.getMap()[i][j]);
            }

        } else {
            CivilianUnit civUnit = database.getCurrentPlayer().getCivilization().getCurrentCivilian();
            MilitaryUnit milUnit = database.getCurrentPlayer().getCivilization().getCurrentMilitary();
            Unit unit = civUnit != null ? civUnit : milUnit;
            if (unit == null) {
                GameView.getInstance().noUnitSelected();
                return;
            } else {
                MapController.getInstance().printTile(unit.getPosition());
            }
        }
    }
}
