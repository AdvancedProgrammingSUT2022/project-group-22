package models;

import java.util.*;
import enums.*;

public class Tile {
    private Player player;

    private int numTile;
    private LandType landType;
    private Feature feature;
    private Resource resource;
    private boolean[] hasRiver = new boolean[6];

    private int food;
    private int gold;
    private int production;
    private int movementCost;
    private int combatModifier;

    private MilitaryUnit garrisonUnit;
    private CivilianUnit workerUnit;
    private ArrayList<Building> buildings = new ArrayList<Building>();
    private boolean[] hasRoad = new boolean[6];

    public Tile(int numTile, LandType landType, Feature feature, Resource resource) {
        this.numTile = numTile;
        this.landType = landType;
        this.feature = feature;
        this.resource = resource;
        this.food = landType.getFood() + (feature != null ? feature.getFood() : 0);
        this.gold = landType.getGold() + (feature != null ? feature.getGold() : 0);
        this.production = landType.getProduction() + (feature != null ? feature.getProduction() : 0);
        this.movementCost = landType.getMovementCost() + (feature != null ? feature.getMovementCost() : 0);
        this.combatModifier = landType.getCombatModifier() + (feature != null ? feature.getCombatModifier() : 0);
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getNumTile() {
        return numTile;
    }

    public LandType getLandType() {
        return this.landType;
    }

    public Feature getFeature() {
        return this.feature;
    }

    public Resource getResource() {
        return this.resource;
    }

    public boolean[] getHasRiver() {
        return this.hasRiver;
    }

    public void setHasRiver(boolean[] hasRiver) {
        this.hasRiver = hasRiver;
    }

    public int getCombatModifier() {
        return this.combatModifier;
    }

    public int getMovementCost() {
        return this.movementCost;
    }

    public int getProduction() {
        return this.production;
    }

    public int getGold() {
        return this.gold;
    }

    public int getFood() {
        return this.food;
    }

    public MilitaryUnit getGarrisonUnit() {
        return garrisonUnit;
    }

    public void setGarrisonUnit(MilitaryUnit garrisonUnit) {
        this.garrisonUnit = garrisonUnit;
    }

    public CivilianUnit getWorkerUnit() {
        return this.workerUnit;
    }

    public void setWorkerUnit(CivilianUnit workerUnit) {
        this.workerUnit = workerUnit;
    }

    public ArrayList<Building> getBuildings() {
        return this.buildings;
    }

    public void addBuilding(Building building) {
        this.buildings.add(building);
    }
}
