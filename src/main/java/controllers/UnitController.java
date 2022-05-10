package controllers;

import enums.*;
import models.*;
import views.GameView;
import java.util.regex.*;

public class UnitController extends GameController {
    private static UnitController instance = null;
    protected static GameView gameView = GameView.getInstance();
    protected Database database = Database.getInstance();
    protected User user = database.getCurrentPlayer();
    protected Tile[][] map = database.getMap();

    public static UnitController getInstance() {
        instance = instance != null ? instance : new UnitController();
        return instance;
    }

    // turn handling + tasks
    public void completeTask(CivilianUnit civilianUnit) {
        Civilization player = user.getCivilization();
        for (CivilianUnit unit : player.getRoadWorkers().keySet()) {
            if (unit.equals(civilianUnit)) {
                player.getRoadWorkers().get(civilianUnit).setHasRoad(true);
                gameView.completeTask("road", unit.getPositon());
                player.getRoadWorkers().remove(civilianUnit);
            }
        }
        for (CivilianUnit unit : player.getRemovalWorkers().keySet()) {
            if (unit.equals(civilianUnit)) {
                civilianUnit.getPositon().removeFeature(player.getRemovalWorkers().get(unit));
                gameView.completeTask(player.getRemovalWorkers().get(unit).name(), unit.getPositon());
                player.getRemovalWorkers().remove(civilianUnit);
            }
        }
        for (CivilianUnit unit : player.getImprovementWorkers().keySet()) {
            if (unit.equals(civilianUnit)) {
                civilianUnit.getPositon().addImprovement(player.getImprovementWorkers().get(civilianUnit));
                gameView.completeTask(player.getImprovementWorkers().get(unit).name(), unit.getPositon());
                player.getImprovementWorkers().remove(civilianUnit);
            }
        }
        for (CivilianUnit unit : player.getBuildingWorkers().keySet()) {
            if (unit.equals(civilianUnit)) {
                civilianUnit.getPositon().addBuilding(player.getBuildingWorkers().get(civilianUnit));
                gameView.completeTask(player.getBuildingWorkers().get(unit).name(), unit.getPositon());
                player.getBuildingWorkers().remove(civilianUnit);
            }
        }
    }

    public void checkMovement(Unit unit) {
        Tile position = unit.getPositon();
        moveTo(unit.getTarget().getCoordinates()[0], unit.getTarget().getCoordinates()[1]);
        if (position.equals(unit.getPositon())) {
            unit.setTarget(null);
            unit.setTaskTurns(0);
        } else if (unit.getTarget().equals(unit.getPositon())) {
            gameView.completeMove(unit.getTarget());
            MapController.getInstance().printTile(unit.getPositon());
            unit.setTarget(null);
            unit.setTaskTurns(0);
        }
    }

    public void processTasks() {
        for (CivilianUnit unit : user.getCivilization().getCivilianUnits()) {
            unit.setTaskTurns(unit.getTaskTurns() - 1);
            if (unit.getTaskTurns() == 0) {
                completeTask(unit);
            }
            if (unit.getTarget() != null) {
                checkMovement(unit);
            }
        }
        for (MilitaryUnit unit : user.getCivilization().getMilitaryUnits()) {
            unit.setTaskTurns(unit.getTaskTurns() - 1);
            if (unit.getTarget() != null) {
                checkMovement(unit);
            }
        }
    }

    public void nextTurn() {
        if (!user.getCivilization().checkUnitTasks()) {
            GameView.getInstance().turnNotOver();
        } else {
            Database.getInstance().nextTurn();
            database.getCurrentPlayer().getCivilization().addValues();
            processTasks();
            // restore city + unit in combat
        }
    }

    // movement
    public void multiStepMove(Tile tile, Unit unit, int[][] dist, Tile[][] parent) {
        Tile nextTile = tile;
        Tile pointer = tile;
        while (!pointer.equals(unit.getPositon())) {
            nextTile = pointer;
            pointer = parent[pointer.getCoordinates()[0]][pointer.getCoordinates()[1]];
        }
        if (dist[nextTile.getCoordinates()[0]][nextTile.getCoordinates()[1]] <= unit.getMovementPoints()) {
            user.getCivilization().updateTileStates(unit.getPositon(), nextTile);
            unit.setPositon(nextTile);
            multiStepMove(nextTile, unit, dist, parent);
        } else {
            for (int k = 0; k < 6; k++) {
                int i2 = database.getNeighbor(nextTile, k).getCoordinates()[0];
                int j2 = database.getNeighbor(nextTile, k).getCoordinates()[1];
                if (dist[i2][j2] < unit.getMovementPoints() && !map[i2][j2].getHasRiver()[k]) {
                    user.getCivilization().updateTileStates(unit.getPositon(), nextTile);
                    unit.setPositon(nextTile);
                    parent[nextTile.getCoordinates()[0]][nextTile.getCoordinates()[1]] = map[i2][j2];
                    multiStepMove(nextTile, unit, dist, parent);
                    return;
                }
            }
        }
    }

    public void moveTo(int i, int j) {
        Unit unit = hasCombatUnit() ? user.getCivilization().getCurrentMilitary()
                : user.getCivilization().getCurrentCivilian();
        Tile tile = map[i][j];
        if (unit == null) {
            GameView.getInstance().noUnitSelected();
        } else if (map.length < i || map[0].length < j) {
            GameView.getInstance().invalidTile();
        } else if (tile.equals(unit.getPositon())) {
            GameView.getInstance().onTarget();
        } else if (hasNonCombatUnit() && database.getCivilianUnitByTile(tile) != null
                || hasCombatUnit() != null && database.getMilitaryUnitByTile(tile) != null) {
            GameView.getInstance().tileOccupied();
        } else if (tile.getLandType().equals(LandType.MOUNTAIN) || tile.getLandType().equals(LandType.OCEAN)
                || tile.getLandType().equals(LandType.SNOW) || tile.getFeature().equals(Feature.ICE)) {
            GameView.getInstance().tileInaccessible();
        } else {
            ShortestPath shortestPath = new ShortestPath();
            Tile[][] parent = new Tile[map.length][map[0].length];
            int[][] dist = shortestPath.getShortestPaths(map,
                    unit.getPositon().getCoordinates()[0],
                    unit.getPositon().getCoordinates()[1], parent);
            if (dist[i][j] <= unit.getMovementPoints()) {
                user.getCivilization().updateTileStates(unit.getPositon(), tile);
                unit.setPositon(tile);
                MapController.getInstance().printTile(unit.getPositon());

            } else {
                for (int k = 0; k < 6; k++) {
                    int i2 = database.getNeighbor(tile, k).getCoordinates()[0];
                    int j2 = database.getNeighbor(tile, k).getCoordinates()[1];
                    if (dist[i2][j2] < unit.getMovementPoints() && !map[i2][j2].getHasRiver()[k]) {
                        user.getCivilization().updateTileStates(unit.getPositon(), tile);
                        unit.setPositon(tile);
                        MapController.getInstance().printTile(unit.getPositon());

                        return;
                    }
                }
                // GameView.getInstance().mpLow();
                unit.setTarget(tile);
                unit.setTaskTurns(Integer.MAX_VALUE);
                multiStepMove(tile, unit, dist, parent);
            }
        }
    }

    public void move(Matcher matcher) {
        int i = Integer.parseInt(matcher.group("i"));
        int j = Integer.parseInt(matcher.group("j"));
        moveTo(i, j);
    }

    public void attack(Matcher matcher) {
        if (!hasCombatUnit())
            GameView.getInstance().noUnitSelected();
        if (isAttackPossible(matcher))
            GameView.getInstance().AttackImpossible();
    }

    public void sleep() {
        if (hasCombatUnit()) {
            user.getCivilization().getCurrentMilitary().setSleep();
            GameView.getInstance().sleepSuccessful();
        } else if (hasNonCombatUnit()) {
            user.getCivilization().getCurrentCivilian().setSleep();
            GameView.getInstance().sleepSuccessful();
        }
        GameView.getInstance().noUnitSelected();
    }

    public void alert() {
        if (hasCombatUnit()) {
            user.getCivilization().getCurrentMilitary().switchAlert();
            GameView.getInstance().alertMessage();
        } else if (hasNonCombatUnit()) {
            user.getCivilization().getCurrentCivilian().switchSleeping();
            GameView.getInstance().alertMessage();
        } else
            GameView.getInstance().noUnitSelected();
    }

    public void fortify() {
        if (hasCombatUnit()) {
            user.getCivilization().getCurrentMilitary().fortify();
            GameView.getInstance().successfulFortify();
        } else
            GameView.getInstance().noUnitSelected();
    }

    public void wake() {
        if (hasNonCombatUnit()) {
            user.getCivilization().getCurrentCivilian().switchSleeping();
            GameView.getInstance().wakeMessage();
        } else if (hasCombatUnit()) {
            user.getCivilization().getCurrentMilitary().setSleep();
            GameView.getInstance().wakeMessage();
        } else
            GameView.getInstance().noUnitSelected();
    }

    public void garrison() {
        if (hasCombatUnit()) {
            user.getCivilization().getCurrentMilitary().getPositon()
                    .setGarrisonUnit(user.getCivilization().getCurrentMilitary());
            GameView.getInstance().garrisonMessage();
        } else
            GameView.getInstance().noUnitSelected();
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
        CivilianUnit civUnit = user.getCivilization().getCurrentCivilian();
        int i = Integer.parseInt(matcher.group("i"));
        int j = Integer.parseInt(matcher.group("j"));
        Tile tile = map[i][j];

        if (civUnit == null) {
            GameView.getInstance().noUnitSelected();
            return;
        } else if (civUnit.getUnitType() != UnitType.SETTLER) {
            GameView.getInstance().unitNotSettler();
            return;
        } else if (map.length < i || map[0].length < j) {
            GameView.getInstance().invalidTile();
            return;
        } else if (civUnit.getPositon() != tile) {
            GameView.getInstance().unitNotOnTile();
        } else if (tile.getPlayer() != null || !tile.getPlayer().getNickname().equals(user.getNickname())) {
            GameView.getInstance().tileNotYours();
            return;
        } // check distance from other city centers
        else {
            user.getCivilization().getCivilianUnits().remove(civUnit);
            user.getCivilization().setCurrentCivilian(null);
            user.getCivilization().addCity(new City(tile, user));
            user.getCivilization().setUnhappiness(user.getCivilization().getUnhappiness() + 1);
        }
    }

    // build methods
    public void buildRoad(Matcher matcher) {
        CivilianUnit civUnit;
        int i = Integer.parseInt(matcher.group("i"));
        int j = Integer.parseInt(matcher.group("j"));
        if (!isValidCoordinates(i, j)) {
            GameView.getInstance().invalidTile();
        } else if (!hasNonCombatUnit() && !hasCombatUnit()) {
            GameView.getInstance().noUnitSelected();
        } else if (!hasNonCombatUnit() || !(civUnit = user.getCivilization().getCurrentCivilian()).isWorker()) {
            GameView.getInstance().unitNotWorker();
        } else if (!civUnit.getPositon().equals(map[i][j])) {
            GameView.getInstance().unitNotOnTile();
        } else {
            civUnit.setTaskTurns(3);
            user.getCivilization().addRoadWorker(civUnit, map[i][j]);
        }
    }

    public void setImprovementTask(CivilianUnit unit, Improvement improvement) {
        Feature feature = unit.getPositon().getFeature();
        Civilization player = user.getCivilization();
        if (improvement.equals(Improvement.FARM) || improvement.equals(Improvement.MINE)) {
            if (feature.equals(Feature.FOREST) || feature.equals(Feature.JUNGLE) || feature.equals(Feature.SWAMP)) {
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
        if ((unit = user.getCivilization().getCurrentCivilian()) == null) {
            gameView.noUnitSelected();
        } else if (!unit.getUnitType().equals(UnitType.WORKER)) {
            gameView.unitNotWorker();
        } else if (!unit.getPositon().getPlayer().equals(user)) {
            gameView.tileNotYours();
        } else if (!canBuildImprovement(improvement, unit.getPositon())) {
            gameView.invalidLocation();
        } else if (unit.getPositon().getImprovement() != null) {
            gameView.tileHasImprovement();
        } else if (!hasImprovementTech(improvement, unit.getPositon().getFeature())) {
            gameView.insufficientTechnologies();
        } else {
            setImprovementTask(unit, improvement);
        }
    }

    public void buildRailRoad() {
    }

    public void repair() {
    }

    public void remove(Feature feature) {
        CivilianUnit unit;
        if ((unit = user.getCivilization().getCurrentCivilian()) == null) {
            gameView.noUnitSelected();
        } else if (!unit.getUnitType().equals(UnitType.WORKER)) {
            gameView.unitNotWorker();
        } else if (!unit.getPositon().getPlayer().equals(user)) {
            gameView.tileNotYours();
        } else if (!unit.getPositon().getFeature().equals(feature)) {
            gameView.noFeature(feature);
        } else {
            user.getCivilization().addRemovalWorker(unit, null);
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
