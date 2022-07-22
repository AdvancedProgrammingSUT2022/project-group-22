package civilization.controllers;

import civilization.enums.*;
import civilization.models.*;
import civilization.views.*;
import civilization.views.components.TileView;

import java.util.*;

public class MapController extends GameController {
    private static MapController instance = null;
    private final Random random = new Random();

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

    //
    public TileView[][] getMap() {
        TileView[][] map = new TileView[database.getMap().length][database.getMap()[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = generateTileView(database.getMap()[i][j]);
            }
        }
        return map;
    }

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
            } else {
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
        if (database.getCurrentPlayer().getCivilization().findTile(tile) == 1) {
            return new TileView(tile.getLandType().getUrl(),
                    tile.getFeature() != null ? tile.getFeature().getUrl() : null,
                    canShowResource(tile.getResource()) ? tile.getResource().getUrl() : null, false);
        } else if (database.getCurrentPlayer().getCivilization().findTile(tile) == 0) {
            Tile revealedTile = database.getCurrentPlayer().getCivilization().getRevealedTile(tile);
            return new TileView(revealedTile.getLandType().getUrl(),
                    revealedTile.getFeature() != null ? revealedTile.getFeature().getUrl() : null,
                    canShowResource(revealedTile.getResource()) ? revealedTile.getResource().getUrl() : null, false);
        } else {
            return new TileView(tile.getLandType().getUrl(),
                    tile.getFeature() != null ? tile.getFeature().getUrl() : null,
                    canShowResource(tile.getResource()) ? tile.getResource().getUrl() : null, true);
        }
    }
}