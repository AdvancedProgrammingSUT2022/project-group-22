package models;

import java.util.*;
import enums.*;

public class Tile {
    private Player player;
    private LandType landType;
    private LandType landFeature;
    private int[] coordinate;
    private int food;
    private int gold;
    private int production;
    private int movementCost;
    private int combatModifier;
    private Resource resource;
    private CivilianUnit workerUnit;
    private boolean[] hasRiver = new boolean[6];
    private boolean[] hasRoad = new boolean[6];
    private ArrayList<Building> buildings = new ArrayList<Building>();

    public Tile(int x, int y, LandType landType, LandType landFeature, Resource resource) {
        this.coordinate[0] = x;
        this.coordinate[1] = y;
        this.landFeature = landFeature;
        this.landType = landType;
        this.food = this.landFeature.getFood() + this.landType.getFood();
        this.gold = this.landType.getGold() + this.landFeature.getGold();
        this.production = this.landType.getProduct() + this.landFeature.getProduct();
        this.movementCost = this.landType.getMovePrice() + this.landFeature.getMovePrice();
        this.combatModifier = this.landFeature.getCombatModifier() + this.landType.getCombatModifier();
        this.resource = resource;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public LandType getLandFeature() {
        return this.landFeature;
    }

    public LandType getLandType() {
        return this.landType;
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

    public Resource getResource() {
        return this.resource;
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
