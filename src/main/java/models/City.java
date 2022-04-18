package models;

import java.util.*;
import enums.*;

public class City {
    private ArrayList<Tile> tiles = new ArrayList<Tile>();
    private int food;
    private int gold;
    private int production;
    private int hitPoints;
    private int combatStrength;
    private int rangedCombatStrength;
    private ArrayList<Resource> resources = new ArrayList<Resource>();
    private MilitaryUnit garrisonUnit;

    public City(Tile tile) {
        // TODO: update combat variables
        this.addTile(tile);
    }

    public ArrayList<Tile> getTiles() {
        return this.tiles;
    }

    public void addTile(Tile tile) {
        this.tiles.add(tile);
        this.food += tile.getFood();
        this.gold += tile.getGold();
        this.production += tile.getProduction();
        // TODO: check if resource is available and add resource
    }

    public int getFood() {
        return this.food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getGold() {
        return this.gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getProduction() {
        return this.production;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    public int getHitPoints() {
        return this.hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getCombatStrength() {
        return this.combatStrength;
    }

    public void setCombatStrength(int combatStrength) {
        this.combatStrength = combatStrength;
    }

    public int getRangedCombatStrength() {
        return this.rangedCombatStrength;
    }

    public void setRangedCombatStrength(int rangedCombatStrength) {
        this.rangedCombatStrength = rangedCombatStrength;
    }

    public ArrayList<Resource> getResources() {
        return this.resources;
    }

    public void addResource(Resource resource) {
        this.resources.add(resource);
        this.food += resource.getFood();
        this.gold += resource.getGold();
        this.production += resource.getProduction();
    }

    public MilitaryUnit getGarrisonUnit() {
        return this.garrisonUnit;
    }

    public void setGarrisonUnit(MilitaryUnit garrisonUnit) {
        this.garrisonUnit = garrisonUnit;
    }

    public void produceUnit(UnitType unit) {
    }

    public void build(Building building) {
    }
}
