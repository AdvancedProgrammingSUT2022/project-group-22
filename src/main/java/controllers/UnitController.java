package controllers;

import java.util.regex.*;
import enums.*;
import models.*;
import views.*;

public class UnitController extends GameController {
    private static UnitController instance = null;

    public static UnitController getInstance() {
        instance = instance != null ? instance : new UnitController();
        return instance;
    }

    public void move(Matcher matcher) {
        Unit unit = civUnit != null ? civUnit : milUnit;
        int i = Integer.parseInt(matcher.group("i"));
        int j = Integer.parseInt(matcher.group("j"));
        Tile tile = map[i][j];

        if (unit == null) {
            gameView.noUnitSelected();
            return;
        } else if (map.length < i || map[0].length < j) {
            gameView.invalidTile();
            return;
        } else if (civUnit != null && database.getCivilianUnitByTile(tile) != null
                || milUnit != null && database.getMilitaryUnitByTile(tile) != null) {
            gameView.tileOccupied();
            return;
        } else if (tile.getLandType().equals(LandType.MOUNTAIN) || tile.getLandType().equals(LandType.OCEAN)
                || tile.getLandType().equals(LandType.SNOW) || tile.getFeature().equals(Feature.ICE)) {
            gameView.tileInaccessible();
            return;
        } else {
            ShortestPath shortestPath = new ShortestPath();
            int[][] dist = shortestPath.getShortestPaths(map,
                    unit.getPositon().getCoordinates()[0],
                    unit.getPositon().getCoordinates()[1]);
            if (dist[i][j] <= unit.getMovementPoints()) {
                unit.setPositon(tile);
                return;
            } else {
                for (int k = 0; k < 6; k++) {
                    int i2 = database.getNeighbor(tile, k).getCoordinates()[0];
                    int j2 = database.getNeighbor(tile, k).getCoordinates()[1];
                    if (dist[i2][j2] < unit.getMovementPoints() && !map[i2][j2].getHasRiver()[k]) {
                        unit.setPositon(tile);
                        return;
                    }
                }
                gameView.mpLow();
            }
        }
    }

    public void sleep() {
    }

    public void wake() {
    }

    public void alert() {
    }

    public void fortify() {
        // check if this unit is settler or not
    }

    public void garrison() {
    }

    public void setup() {
    }

    public void attack(Matcher matcher) {
        // baray faz 1 serfan bayad matcher begirim ama badan bayad unit bgirim
    }

    public void foundCity(Matcher matcher) {
        CivilianUnit civUnit = player.getCurrentCivilian();
        int i = Integer.parseInt(matcher.group("i"));
        int j = Integer.parseInt(matcher.group("j"));
        Tile tile = map[i][j];

        if (civUnit == null) {
            gameView.noUnitSelected();
            return;
        } else if (civUnit.getUnitType() != UnitType.SETTLER) {
            gameView.unitNotSettler();
            return;
        } else if (map.length < i || map[0].length < j) {
            gameView.invalidTile();
            return;
        } else if (civUnit.getPositon() != tile) {
            gameView.unitNotOnTile();
        } else if (tile.getPlayer() != null || tile.getPlayer() != player) {
            gameView.tileHasOwner();
            return;
        } // check distance from other city centers
        else {
            player.getCivilianUnits().remove(civUnit);
            player.setCurrentCivilian(null);
            player.addCity(new City(tile, player));
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
        // move(Matcher matcher, player.getCurrentCivilian(),
        // player.getCurrentMilitary());
        // }
        // }
        return null;
    }
}
