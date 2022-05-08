package controllers;

import enums.Feature;
import enums.LandType;
import enums.UnitType;
import models.*;
import views.GameView;

import java.util.regex.Matcher;


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
            GameView.getInstance().noUnitSelected();
            return;
        } else if (map.length < i || map[0].length < j) {
            GameView.getInstance().invalidTile();
            return;
        } else if (civUnit != null && database.getCivilianUnitByTile(tile) != null
                || milUnit != null && database.getMilitaryUnitByTile(tile) != null) {
            GameView.getInstance().tileOccupied();
            return;
        } else if (tile.getLandType().equals(LandType.MOUNTAIN) || tile.getLandType().equals(LandType.OCEAN)
                || tile.getLandType().equals(LandType.SNOW) || tile.getFeature().equals(Feature.ICE)) {
            GameView.getInstance().tileInaccessible();
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
                GameView.getInstance().mpLow();
            }
        }
    }

//    public boolean sleep(Civilization civilization) {
//        if(civilization.getCurrentMilitary().getStatus()) {
//            civilization.getCurrentMilitary().setSleep();
//            return true;
//        }
//        else if(civilization.getCurrentCivilian().getStatus()) {
//            civilization.getCurrentCivilian().setSleep();
//            return true;
//        }
//        return false;
//    }

    public void garrison() {
    }

    public void setup() {
    }

//    public void attack(Uni) {
//
//    }

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
//            tile.getPlayer() != user ,We cannot compare two objects with !=
        } else if (tile.getPlayer() != null || !tile.getPlayer().getNickname().equals(user.getNickname()) ) {
            GameView.getInstance().tileHasOwner();
            return;
        } // check distance from other city centers
        else {
            user.getCivilization().getCivilianUnits().remove(civUnit);
            user.getCivilization().setCurrentCivilian(null);
            user.getCivilization().addCity(new City(tile, user));
            user.getCivilization().setUnhappiness(user.getCivilization().getUnhappiness()+1);
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
