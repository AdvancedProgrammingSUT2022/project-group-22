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

    public void move(Matcher matcher, Command command) {
        CivilianUnit civUnit = user.getCivilization().getCurrentCivilian();
        MilitaryUnit milUnit = user.getCivilization().getCurrentMilitary();
        Unit unit = civUnit != null ? civUnit : milUnit;
        int i = Integer.parseInt(matcher.group("i"));
        int j = Integer.parseInt(matcher.group("j"));
        Tile tile = map[i][j];

        if (unit == null) {
            GameView.getInstance().noUnitSelected();
        } else if (map.length < i || map[0].length < j) {
            GameView.getInstance().invalidTile();
        } else if (tile.equals(unit.getPositon())) {
            GameView.getInstance().onTarget();
        } else if (civUnit != null && database.getCivilianUnitByTile(tile) != null
                || milUnit != null && database.getMilitaryUnitByTile(tile) != null) {
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
                MapController.getInstance().printArea(map, 0, 0, 24, 40);
            } else {
                for (int k = 0; k < 6; k++) {
                    int i2 = database.getNeighbor(tile, k).getCoordinates()[0];
                    int j2 = database.getNeighbor(tile, k).getCoordinates()[1];
                    if (dist[i2][j2] < unit.getMovementPoints() && !map[i2][j2].getHasRiver()[k]) {
                        user.getCivilization().updateTileStates(unit.getPositon(), tile);
                        unit.setPositon(tile);
                        MapController.getInstance().printArea(map, 0, 0, 24, 40);
                        return;
                    }
                }
                if (command.equals(Command.MOVETO)) {
                    GameView.getInstance().mpLow();
                } else {
                    unit.setTarget(tile);
                    unit.setTaskTurns(Integer.MAX_VALUE);
                    multiStepMove(tile, unit, dist, parent);
                }
            }
        }
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

    public void build(Matcher matcher) {
    }

    public void repair() {
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
