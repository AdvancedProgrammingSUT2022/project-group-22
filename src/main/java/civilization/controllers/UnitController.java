package civilization.controllers;

import civilization.enums.*;
import civilization.models.*;
import java.util.regex.*;

public class UnitController extends GameController {
    private static UnitController instance = null;
    private static MapController mapController = MapController.getInstance();

    public static UnitController getInstance() {
        instance = instance != null ? instance : new UnitController();
        return instance;
    }

    // turn handling + tasks
    public void taskCompleted(CivilianUnit civilianUnit) {
        Civilization player = database.getCurrentPlayer().getCivilization();
        for (CivilianUnit unit : player.getRoadWorkers().keySet()) {
            if (unit.equals(civilianUnit)) {
                player.getRoadWorkers().get(civilianUnit).setHasRoad(true);
                gameView.taskCompleted("road", unit.getPosition().getCoordinates());
                player.getRoadWorkers().remove(civilianUnit);
            }
        }
        for (CivilianUnit unit : player.getRemovalWorkers().keySet()) {
            if (unit.equals(civilianUnit)) {
                civilianUnit.getPosition().removeFeature(player.getRemovalWorkers().get(unit));
                gameView.taskCompleted(player.getRemovalWorkers().get(unit).name(),
                        unit.getPosition().getCoordinates());
                player.getRemovalWorkers().remove(civilianUnit);
            }
        }
        for (CivilianUnit unit : player.getImprovementWorkers().keySet()) {
            if (unit.equals(civilianUnit)) {
                civilianUnit.getPosition().addImprovement(player.getImprovementWorkers().get(civilianUnit));
                gameView.taskCompleted(player.getImprovementWorkers().get(unit).name(),
                        unit.getPosition().getCoordinates());
                player.getImprovementWorkers().remove(civilianUnit);
            }
        }
        for (CivilianUnit unit : player.getBuildingWorkers().keySet()) {
            if (unit.equals(civilianUnit)) {
                civilianUnit.getPosition().addBuilding(player.getBuildingWorkers().get(civilianUnit));
                gameView.taskCompleted(player.getBuildingWorkers().get(unit).name(),
                        unit.getPosition().getCoordinates());
                player.getBuildingWorkers().remove(civilianUnit);
            }
        }
    }

    public void checkMovement(Unit unit) {
        Civilization player = database.getCurrentPlayer().getCivilization();
        String name = player.getCivilianUnits().indexOf(unit) != -1
                ? "CivUnit No. " + player.getCivilianUnits().indexOf(unit)
                : "MilUnit No. " + player.getMilitaryUnits().indexOf(unit);
        Tile position = unit.getPosition();
        moveTo(unit.getTarget().getCoordinates()[0], unit.getTarget().getCoordinates()[1], unit, position);
        if (position.equals(unit.getPosition())) {
            gameView.pathBlocked(name, unit.getPosition().getCoordinates(), unit.getTarget().getCoordinates());
            unit.setTarget(null);
            unit.setTaskTurns(0);
        } else if (unit.getTarget().equals(unit.getPosition())) {
            gameView.moveCompleted(name, unit.getTarget().getCoordinates());
            mapController.printTile(unit.getPosition());
            unit.setTarget(null);
            unit.setTaskTurns(0);
        }
    }

    public void processTasks() {
        Civilization player = database.getCurrentPlayer().getCivilization();
        for (CivilianUnit unit : player.getCivilianUnits()) {
            unit.setTaskTurns(unit.getTaskTurns() - 1);
            if (unit.getTaskTurns() == 0) {
                taskCompleted(unit);
            }
            // if (unit.getTarget() != null) {
            // checkMovement(unit);
            // }
        }
        for (MilitaryUnit unit : player.getMilitaryUnits()) {
            unit.setTaskTurns(unit.getTaskTurns() - 1);
            // if (unit.getTarget() != null) {
            // checkMovement(unit);
            // }
        }
        player.researchProgress();
    }

    public void nextTurn() {
        if (!database.getCurrentPlayer().getCivilization().checkUnitTasks()) {
            gameView.turnNotOver();
        } else {
            database.nextTurn();
            gameView.currentPlayer(database.getCurrentPlayer().getUsername());
            database.getCurrentPlayer().getCivilization().addValues();
            processTasks();
            // restore city + unit in combat
        }
    }

    public void skipTurns(Matcher matcher) {
        int num = Integer.parseInt(matcher.group("amount"));
        for (int i = 0; i < num; i++) {
            database.nextTurn();
            database.getCurrentPlayer().getCivilization().addValues();
            processTasks();
            // restore city + unit in combat
        }
        gameView.turnsIncreased(num);
        gameView.currentPlayer(database.getCurrentPlayer().getUsername());
    }

    // create unit
    public void buyUnit(Matcher matcher) {
        Civilization player = database.getCurrentPlayer().getCivilization();
        String type = matcher.group("type").toUpperCase();
        City city;
        UnitType unitType;
        if ((unitType = UnitType.matchUnitType(type)) == null) {
            gameView.noSuchUnitType(type);
        } else if ((city = player.getCurrentCity()) == null) {
            gameView.noCitySelected();
        } else if (((unitType.equals(UnitType.SETTLER) || unitType.equals(UnitType.WORKER))
                && database.getCivilianUnitByTile(city.getCenter()) != null)) {
            gameView.tileOccupied();
        } else if (!(unitType.equals(UnitType.SETTLER) || unitType.equals(UnitType.WORKER))
                && database.getMilitaryUnitByTile(city.getCenter()) != null) {
            gameView.tileOccupied();
        } else if (unitType.getResource() != null && city.hasResource(unitType.getResource())) {
            gameView.insufficientResources();
        } else if (unitType.getTechnology() != null && player.hasTechnology(unitType.getTechnology())) {
            gameView.insufficientTechnologies();
        } else if (player.getGold() < unitType.getCost()) {
            gameView.goldLow();
        } else if (unitType.equals(UnitType.SETTLER)
                && database.getCurrentPlayer().getCivilization().getTotalHappiness() < 0) {
            gameView.negativeHappiness();
        } else {
            player.setGold(player.getGold() - unitType.getCost());
            if (unitType.equals(UnitType.SETTLER) || unitType.equals(UnitType.WORKER)) {
                player.addCivilianUnit(new CivilianUnit(unitType, city.getCenter()));
            } else {
                player.addMilitaryUnits(new MilitaryUnit(unitType, city.getCenter()));
            }
            gameView.unitBought(unitType.name().toLowerCase());
            mapController.printTile(city.getCenter());
        }
    }

    // movement
    public void multiStepMove(Tile tile, Unit unit, int[][] dist, Tile[][] parent) {
        Tile nextTile = tile;
        Tile pointer = tile;
        while (!pointer.equals(unit.getPosition())) {
            nextTile = pointer;
            pointer = parent[pointer.getCoordinates()[0]][pointer.getCoordinates()[1]];
        }
        if (dist[nextTile.getCoordinates()[0]][nextTile.getCoordinates()[1]] <= unit.getMovementPoints()) {
            unit.setMovementPoints(
                    unit.getMovementPoints() - dist[nextTile.getCoordinates()[0]][nextTile.getCoordinates()[1]]);
            database.getCurrentPlayer().getCivilization().updateTileStates(unit.getPosition(), nextTile);
            unit.setPosition(nextTile);
            multiStepMove(nextTile, unit, dist, parent);
        } else {
            for (int k = 0; k < 6; k++) {
                if (database.getNeighbor(nextTile, k) != null) {
                    int i2 = database.getNeighbor(nextTile, k).getCoordinates()[0];
                    int j2 = database.getNeighbor(nextTile, k).getCoordinates()[1];
                    if (dist[i2][j2] < unit.getMovementPoints() && !database.getMap()[i2][j2].getHasRiver()[k]) {
                        unit.setMovementPoints(unit.getMovementPoints()
                                - dist[nextTile.getCoordinates()[0]][nextTile.getCoordinates()[1]]);
                        database.getCurrentPlayer().getCivilization().updateTileStates(unit.getPosition(), nextTile);
                        unit.setPosition(nextTile);
                        parent[nextTile.getCoordinates()[0]][nextTile.getCoordinates()[1]] = database.getMap()[i2][j2];
                        multiStepMove(nextTile, unit, dist, parent);
                        return;
                    }
                }
            }
        }
    }

    public void moveTo(int i, int j, Unit unit, Tile tile) {
        ShortestPath shortestPath = new ShortestPath();
        Tile[][] parent = new Tile[database.getMap().length][database.getMap()[0].length];
        int[][] dist = shortestPath.getShortestPaths(database.getMap(),
                unit.getPosition().getCoordinates()[0],
                unit.getPosition().getCoordinates()[1], parent);
        if (dist[i][j] <= unit.getMovementPoints()) {
            unit.setMovementPoints(unit.getMovementPoints() - dist[i][j]);
            database.getCurrentPlayer().getCivilization().updateTileStates(unit.getPosition(), tile);
            unit.setPosition(tile);
            mapController.printTile(unit.getPosition());
        } else {
            for (int k = 0; k < 6; k++) {
                if (database.getNeighbor(tile, k) != null) {
                    int i2 = database.getNeighbor(tile, k).getCoordinates()[0];
                    int j2 = database.getNeighbor(tile, k).getCoordinates()[1];
                    if (dist[i2][j2] < unit.getMovementPoints() && !database.getMap()[i2][j2].getHasRiver()[k]) {
                        unit.setMovementPoints(unit.getMovementPoints() - dist[i2][j2]);
                        database.getCurrentPlayer().getCivilization().updateTileStates(unit.getPosition(), tile);
                        unit.setPosition(tile);
                        mapController.printTile(unit.getPosition());
                        return;
                    }
                }
            }
            gameView.mpLow();
            // unit.setTarget(tile);
            // unit.setTaskTurns(Integer.MAX_VALUE);
            // multiStepMove(tile, unit, dist, parent);
        }
    }

    public void move(Matcher matcher) {
        int i = Integer.parseInt(matcher.group("i"));
        int j = Integer.parseInt(matcher.group("j"));
        Unit unit = hasCombatUnit() ? database.getCurrentPlayer().getCivilization().getCurrentMilitary()
                : database.getCurrentPlayer().getCivilization().getCurrentCivilian();
        Tile tile;
        if (unit == null) {
            gameView.noUnitSelected();
        } else if (!isValidCoordinates(i, j)) {
            gameView.invalidTile();
        } else if ((tile = database.getMap()[i][j]).equals(unit.getPosition())) {
            gameView.onTarget();
        } else if (hasNonCombatUnit() && database.getCivilianUnitByTile(tile) != null
                || hasCombatUnit() != null && database.getMilitaryUnitByTile(tile) != null) {
            gameView.tileOccupied();
        } else if (tile.getFeature() != null
                && (tile.getLandType().equals(LandType.MOUNTAIN) || tile.getLandType().equals(LandType.OCEAN)
                        || tile.getLandType().equals(LandType.SNOW) || tile.getFeature().equals(Feature.ICE))) {
            gameView.tileInaccessible();
        } else {
            moveTo(i, j, unit, tile);
        }
    }

    public void attack(Matcher matcher) {
        if (!hasCombatUnit())
            gameView.noUnitSelected();
        if (isAttackPossible(matcher))
            gameView.attackImpossible();
    }

    public void sleep() {
        if (hasCombatUnit()) {
            database.getCurrentPlayer().getCivilization().getCurrentMilitary().setSleep();
            gameView.unitSlept();
        } else if (hasNonCombatUnit()) {
            database.getCurrentPlayer().getCivilization().getCurrentCivilian().setSleep();
            gameView.unitSlept();
        }
        gameView.noUnitSelected();
    }

    public void alert() {
        if (hasCombatUnit()) {
            database.getCurrentPlayer().getCivilization().getCurrentMilitary().switchAlert();
            gameView.unitAlerted();
        } else if (hasNonCombatUnit()) {
            database.getCurrentPlayer().getCivilization().getCurrentCivilian().switchSleeping();
            gameView.unitAlerted();
        } else
            gameView.noUnitSelected();
    }

    public void fortify() {
        if (hasCombatUnit()) {
            database.getCurrentPlayer().getCivilization().getCurrentMilitary().fortify();
            gameView.unitFortified();
        } else
            gameView.noUnitSelected();
    }

    public void wake() {
        if (hasNonCombatUnit()) {
            database.getCurrentPlayer().getCivilization().getCurrentCivilian().switchSleeping();
            gameView.unitWoke();
        } else if (hasCombatUnit()) {
            database.getCurrentPlayer().getCivilization().getCurrentMilitary().setSleep();
            gameView.unitWoke();
        } else
            gameView.noUnitSelected();
    }

    public void garrison() {
        if (hasCombatUnit()) {
            database.getCurrentPlayer().getCivilization().getCurrentMilitary().getPosition()
                    .setGarrisonUnit(database.getCurrentPlayer().getCivilization().getCurrentMilitary());
            gameView.unitGarrisoned();
        } else
            gameView.noUnitSelected();
    }

    // public Boolean sleep(Civilization civilization) {
    // if(civilization.getCurrentMilitary().getStatus()) {
    // civilization.getCurrentMilitary().setSleep();
    // return true;
    // }
    // else if(civilization.getCurrentCivilian().getStatus()) {
    // civilization.getCurrentCivilian().setSleep();
    // return true;
    // }
    // return false;
    // }

    public void setup() {
    }

    // public void attack(Uni) {
    //
    // }

    public void foundCity(Matcher matcher) {
        CivilianUnit civUnit = database.getCurrentPlayer().getCivilization().getCurrentCivilian();
        String name = matcher.group("name");
        int i = Integer.parseInt(matcher.group("i"));
        int j = Integer.parseInt(matcher.group("j"));
        Tile tile = database.getMap()[i][j];
        if (civUnit == null) {
            gameView.noUnitSelected();
        } else if (civUnit.getUnitType() != UnitType.SETTLER) {
            gameView.unitNotSettler();
        } else if (!isValidCoordinates(i, j)) {
            gameView.invalidTile();
        } else if (civUnit.getPosition() != tile) {
            gameView.unitNotOnTile();
        } else if (tile.getPlayer() != null &&
                !tile.getPlayer().getNickname().equals(database.getCurrentPlayer().getNickname())) {
            gameView.tileNotYours();
        } else if (database.getCityCenters().indexOf(tile) != -1) {
            gameView.cityExists(tile.getCoordinates());
        } else if (!canFoundCity(tile)) {
            gameView.tooCloseToCity(tile.getCoordinates());
        } else if (database.getCurrentPlayer().getCivilization().getTotalHappiness() < 0) {
            gameView.negativeHappiness();
        } else if (database.getCityByName(name) != null) {
            gameView.nameTaken(name);
        } else {
            database.getCurrentPlayer().getCivilization().getCivilianUnits().remove(civUnit);
            database.getCurrentPlayer().getCivilization().setCurrentCivilian(null);
            database.getCurrentPlayer().getCivilization().addCity(new City(tile, database.getCurrentPlayer(), name));
            database.getCurrentPlayer().getCivilization()
                    .setUnhappiness(database.getCurrentPlayer().getCivilization().getUnhappiness() + 1);
            gameView.cityFounded(name, database.getMap()[i][j].getPlayer().getUsername(), i, j);
        }
    }

    // build methods
    public void buildRoad() {
        Civilization player = database.getCurrentPlayer().getCivilization();
        CivilianUnit civUnit;
        Tile tile;
        if (!hasNonCombatUnit()) {
            gameView.noUnitSelected();
        } else if (!(civUnit = player.getCurrentCivilian()).isWorker()) {
            gameView.unitNotWorker();
        } else if ((tile = civUnit.getPosition()).getHasRoad()) {
            gameView.tileHasRoad(tile.getCoordinates());
        } else {
            civUnit.setTaskTurns(3);
            player.addRoadWorker(civUnit, tile);
        }
    }

    public void setImprovementTask(CivilianUnit unit, Improvement improvement) {
        Feature feature = unit.getPosition().getFeature();
        Civilization player = database.getCurrentPlayer().getCivilization();
        if (improvement.equals(Improvement.FARM) || improvement.equals(Improvement.MINE)) {
            if (feature != null && (feature.equals(Feature.FOREST) || feature.equals(Feature.JUNGLE)
                    || feature.equals(Feature.SWAMP))) {
                player.addRemovalWorker(unit, null);
                player.addImprovementWorker(unit, improvement);
                unit.setTaskTurns(feature.equals(Feature.FOREST) ? 10 : feature.equals(Feature.JUNGLE) ? 13 : 12);
                return;
            }
        }
        player.addImprovementWorker(unit, improvement);
        unit.setTaskTurns(6);
    }

    public void buildImprovements(Improvement improvement) {
        CivilianUnit unit;
        if ((unit = database.getCurrentPlayer().getCivilization().getCurrentCivilian()) == null) {
            gameView.noUnitSelected();
        } else if (!unit.getUnitType().equals(UnitType.WORKER)) {
            gameView.unitNotWorker();
        } else if (unit.getPosition().getPlayer() == null
                || !unit.getPosition().getPlayer().equals(database.getCurrentPlayer())) {
            gameView.tileNotYours();
        } else if (!canBuildImprovement(improvement, unit.getPosition())) {
            gameView.invalidLocation();
        } else if (unit.getPosition().getImprovement() != null) {
            gameView.tileHasImprovement();
        } else if (!hasImprovementTech(improvement, unit.getPosition().getFeature())) {
            gameView.insufficientTechnologies();
        } else {
            setImprovementTask(unit, improvement);
        }
    }

    public void buildRailRoad() {
    }

    public void instantBuild() {
        Civilization player = database.getCurrentPlayer().getCivilization();
        CivilianUnit unit;
        if ((unit = player.getCurrentCivilian()) == null) {
            gameView.noUnitSelected();
        } else if (!unit.getUnitType().equals(UnitType.WORKER)) {
            gameView.unitNotWorker();
        } else if (unit.getTaskTurns() == 0 || unit.getTaskTurns() > 1000) {
            gameView.noBuildTask();
        } else {
            taskCompleted(unit);
        }
    }

    public void repair() {
    }

    public void removeFeature(Matcher matcher) {
        CivilianUnit unit;
        String featureName = matcher.group("feature");
        Feature feature = Feature.matchFeature(featureName);
        if (!(feature.equals(Feature.JUNGLE) || feature.equals(Feature.FOREST) || feature.equals(Feature.SWAMP))) {
            gameView.featureIrremovable(featureName);
        } else if ((unit = database.getCurrentPlayer().getCivilization().getCurrentCivilian()) == null) {
            gameView.noUnitSelected();
        } else if (!unit.getUnitType().equals(UnitType.WORKER)) {
            gameView.unitNotWorker();
        } else if (!unit.getPosition().getPlayer().equals(database.getCurrentPlayer())) {
            gameView.tileNotYours();
        } else if (unit.getPosition().getFeature() == null || !unit.getPosition().getFeature().equals(feature)) {
            gameView.noFeature(feature);
        } else {
            database.getCurrentPlayer().getCivilization().addRemovalWorker(unit, null);
            unit.setTaskTurns(feature.equals(Feature.FOREST) ? 10 : feature.equals(Feature.JUNGLE) ? 13 : 12);
        }
    }

    public void delete() {
    }

    public void cancel() {
    }

    public String run() {
        // while (true){
        // if (command.equals(Commands.MOVE)) {
        // move(Matcher matcher, civilization.getCurrentCivilian(),
        // civilization.getCurrentMilitary());
        // }
        // }
        return null;
    }
}
