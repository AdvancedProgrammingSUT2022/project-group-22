package models;

import enums.*;
import java.util.*;

public class Tile {
    private User player;
    private int[] coordinates;
    private LandType landType;
    private Feature feature;
    private Resource resource;
    private Boolean[] hasRiver;

    private int food;
    private int gold;
    private int production;
    private int movementCost;
    private int combatModifier;

    private MilitaryUnit garrisonUnit;
    private CivilianUnit workerUnit;
    private Improvement improvement;
    private ArrayList<Building> buildings;
    private Boolean hasRoad;

    public Tile(int[] coordinates, LandType landType, Feature feature, Resource resource) {
        this.coordinates = coordinates;
        this.landType = landType;
        this.feature = feature;
        this.resource = resource;
        this.food = landType.getFood() + (feature != null ? feature.getFood() : 0);
        this.gold = landType.getGold() + (feature != null ? feature.getGold() : 0);
        this.production = landType.getProduction() + (feature != null ? feature.getProduction() : 0);
        this.movementCost = landType.getMovementCost() + (feature != null ? feature.getMovementCost() : 0);
        this.combatModifier = landType.getCombatModifier() + (feature != null ? feature.getCombatModifier() : 0);
        this.hasRoad = false;
        this.player = null;
        this.improvement = null;
        this.hasRiver = new Boolean[6];
        this.buildings = new ArrayList<Building>();
    }

    public User getPlayer() {
        return this.player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public LandType getLandType() {
        return this.landType;
    }

    public void removeFeature(Feature feature) {
        this.food = this.food - this.feature.getFood() + (feature != null ? feature.getFood() : 0);
        this.gold = this.gold - this.feature.getGold() + (feature != null ? feature.getGold() : 0);
        this.production = this.production - this.feature.getProduction()
                + (feature != null ? feature.getProduction() : 0);
        this.movementCost = this.movementCost - this.feature.getMovementCost() +
                (feature != null ? feature.getMovementCost() : 0);
        this.combatModifier = this.combatModifier - this.feature.getMovementCost() +
                (feature != null ? feature.getCombatModifier() : 0);
        // remove related resources and improvemnents
        this.feature = feature;
    }

    public Feature getFeature() {
        return this.feature;
    }

    public Resource getResource() {
        return this.resource;
    }

    public void activateResource() {
        this.gold += this.resource.getGold();
        this.production += this.resource.getProduction();
        this.food += this.resource.getFood();
    }

    public Boolean[] getHasRiver() {
        return this.hasRiver;
    }

    public void setHasRiver(int side, Boolean hasRiver) {
        this.hasRiver[side] = hasRiver;
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

    public void setProduction(int production) {
        this.production = production;
    }

    public int getGold() {
        return this.gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getFood() {
        return this.food;
    }

    public void setFood(int food) {
        this.food = food;
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

    public Improvement getImprovement() {
        return improvement;
    }

    public void addImprovement(Improvement improvement) {
        this.improvement = improvement;
        Database.getInstance().getCityByTile(this).activateResources(improvement);
    }

    public ArrayList<Building> getBuildings() {
        return this.buildings;
    }

    public void addBuilding(Building building) {
        this.buildings.add(building);
    }

    public Boolean getHasRoad() {
        return this.hasRoad;
    }

    public void setHasRoad(Boolean hasRoad) {
        this.hasRoad = hasRoad;
    }
}
