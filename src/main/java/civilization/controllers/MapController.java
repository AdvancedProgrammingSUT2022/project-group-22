package civilization.controllers;

import civilization.enums.*;
import civilization.models.*;
import civilization.views.GameView.Map;
import civilization.views.components.*;

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
                Tile tile = database.getMap()[i][j];
                if (database.getCurrentPlayer().getCivilization().findTile(tile) == 1) {
                    map[i][j] = generateTileView(tile, false);
                } else if (database.getCurrentPlayer().getCivilization().findTile(tile) == 0) {
                    map[i][j] = generateTileView(database.getCurrentPlayer().getCivilization().getRevealedTile(tile),
                            false);
                } else {
                    map[i][j] = generateTileView(tile, true);
                }
            }
        }
        return map;
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
                int[] coordinates = new int[2];
                coordinates[0] = i;
                coordinates[1] = j;
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

    public TileView generateTileView(Tile tile, Boolean fogOfWar) {
        return new TileView(tile.getCoordinates(), tile.getFood(), tile.getProduction(), tile.getGold(),
                tile.getCombatModifier(),
                tile.getMovementCost(), tile.getLandType().name(),
                tile.getFeature() != null ? tile.getFeature().name() : "N/A",
                canShowResource(tile.getResource()) ? tile.getResource().name() : "N/A",
                tile.getLandType().getUrl(), tile.getFeature() != null ? tile.getFeature().getUrl() : null,
                canShowResource(tile.getResource()) ? tile.getResource().getUrl() : null,
                tile.getBuilding() != null ? tile.getBuilding().getUrl() : null, fogOfWar);
    }

    public static UnitView[] getTileUnit(int i, int j) {
        UnitView[] unitViews = new UnitView[2];
        Unit unit;
        int[] position = { i, j };
        unitViews[0] = (unit = database.getCivilianUnitByTile(database.getMap()[i][j])) != null
                ? new UnitView(unit.getUnitType().getUrl(), false, unit.getUnitType().name(),
                        unit.getMovementPoints(), unit.getCombatStrength(), position)
                : null;
        unitViews[1] = (unit = database.getMilitaryUnitByTile(database.getMap()[i][j])) != null
                ? new UnitView(unit.getUnitType().getUrl(), true, unit.getUnitType().name(),
                        unit.getMovementPoints(), unit.getCombatStrength(), position)
                : null;
        return unitViews;
    }

    public static UnitView getSettler() {
        Unit unit = database.getCurrentPlayer().getCivilization().getCurrentCivilian();
        return new UnitView(unit.getUnitType().getUrl(), false, unit.getUnitType().name(), unit.getMovementPoints(),
                unit.getCombatStrength(), unit.getPosition().getCoordinates());
    }

    public void setSelectedTile(TileView tileView) {
        database.getCurrentPlayer().getCivilization()
                .setSelectedTile(database.getMap()[tileView.getCoordinates()[0]][tileView.getCoordinates()[1]]);
    }

    public void setSelectedUnit(UnitView unitView) {
        Tile tile = database.getMap()[unitView.getPosition()[0]][unitView.getPosition()[1]];
        if (unitView.isMilitary()) {
            database.getCurrentPlayer().getCivilization().setCurrentMilitary(database.getMilitaryUnitByTile(tile));
        } else {
            database.getCurrentPlayer().getCivilization().setCurrentCivilian(database.getCivilianUnitByTile(tile));
        }
    }

    public static Boolean canAccessUnit(UnitView unitView) {
        Tile tile = database.getMap()[unitView.getPosition()[0]][unitView.getPosition()[1]];
        Unit unit;
        if ((!unitView.isMilitary() && (unit = database.getCivilianUnitByTile(tile)) != null)
                || (unitView.isMilitary() && (unit = database.getMilitaryUnitByTile(tile)) != null)) {
            if (database.getUnitOwner(unit).equals(database.getCurrentPlayer())) {
                return true;
            }
        }
        return false;
    }

    public void cheat(String code) {
        Civilization civ = database.getCurrentPlayer().getCivilization();
        Tile currentTile = civ.getSelectedTile();
        UnitType unit;
        Unit oldUnit;
        Building building;
        if (!currentTile.getLandType().equals(LandType.MOUNTAIN) && !currentTile.getLandType().equals(LandType.OCEAN)) {
            if ((unit = UnitType.matchUnitType(code.substring(1))) != null) {
                if (unit.equals(UnitType.SETTLER) || unit.equals(UnitType.WORKER)) {
                    if ((oldUnit = database.getCivilianUnitByTile(currentTile)) != null) {
                        civ.removeUnit(oldUnit);
                    }
                    civ.addCivilianUnit(new CivilianUnit(unit, currentTile));
                } else {
                    if ((oldUnit = database.getMilitaryUnitByTile(currentTile)) != null) {
                        civ.removeUnit(oldUnit);
                    }
                    civ.addMilitaryUnit(new MilitaryUnit(unit, currentTile));
                }
                Map.getInstance().createMap(getMap());
                return;
            }
            if ((building = Building.matchBuilding(code.substring(1))) != null) {
                if (currentTile.getBuilding() != null) {
                    currentTile.removeBuilding();
                }
                currentTile.addBuilding(building);
                Map.getInstance().createMap(getMap());
                return;
            }
        }
    }
}