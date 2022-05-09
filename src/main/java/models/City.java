package models;

import enums.*;

import java.util.ArrayList;

public class City {
    private String name;
    private Tile center;
    private ArrayList<Tile> tiles = new ArrayList<Tile>();

    private int food;
    private int production;
    private int population;

    private final int range = 2;
    private int hitPoints;
    private int combatStrength;
    private int rangedCombatStrength;

    private MilitaryUnit garrisonUnit;
    private ArrayList<Resource> resources = new ArrayList<Resource>();
    private ArrayList<Improvement> improvements = new ArrayList<Improvement>();

    public City(Tile tile, User user) {
        // TODO: update combat variables
        this.population = 1;

        this.center = tile;
        this.addTile(tile);
        tile.setPlayer(user);
        if (tile.getFeature().equals(Feature.JUNGLE)) {
            tile.removeJungle();
        }
        tile.setHasRoad(true);

        for (int i = 0; i < 6; i++) {
            Tile neighbor = Database.getInstance().getNeighbor(tile, i);
            if (neighbor.getPlayer() != null) {
                // check tiles up to 3 tiles away
            } else {
                this.addTile(neighbor);
                neighbor.setPlayer(user);
            }
        }

        this.hitPoints = 20;
    }

    public Tile getCenter() {
        return center;
    }

    public void setCenter(Tile center) {
        this.center = center;
    }

    public ArrayList<Tile> getTiles() {
        return this.tiles;
    }

    public void addTile(Tile tile) {
        this.tiles.add(tile);
        this.food += tile.getFood();
        this.production += tile.getProduction();
        // TODO: check if resource is available and add resource
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addValues() {
    }

    public int getFood() {
        return this.food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getProduction() {
        return this.production;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    public int getPopulation() {
        return this.population;
    }

    public void setPopulation(int population) {
        this.population = population;
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
        // this.gold += resource.getGold();
        this.production += resource.getProduction();
    }

    public ArrayList<Improvement> getImprovements() {
        return improvements;
    }

    public void addImprovement(Improvement improvement, Tile tile) {
        tile.addImprovement(improvement);
        this.improvements.add(improvement);
        this.food += improvement.getFood();
        // this.gold += improvement.getGold();
        this.production += improvement.getProduction();
    }

    public MilitaryUnit getGarrisonUnit() {
        return this.garrisonUnit;
    }

    public void setGarrisonUnit(MilitaryUnit garrisonUnit) {
        this.garrisonUnit = garrisonUnit;
    }

    public Boolean hasImprovement(Improvement improvement) {
        for (Improvement tempImprovement : this.improvements) {
            if (tempImprovement.equals(improvement)) {
                return true;
            }
        }
        return false;
    }

    public void produceUnit(UnitType unit) {
    }

    public void build(Building building) {
    }
}
