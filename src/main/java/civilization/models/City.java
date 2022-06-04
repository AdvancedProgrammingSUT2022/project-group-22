package civilization.models;

import civilization.enums.*;

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

    public City(Tile tile, User user, String name) {
        // TODO: update combat variables
        this.name = name;
        this.population = 1;

        this.center = tile;
        tile.setFood(tile.getFood() + 2);
        tile.setProduction(tile.getProduction() + 1);

        tile.setPlayer(user);
        this.addTile(tile);
        if (tile.getFeature() != null) {
            if (tile.getFeature().equals(Feature.JUNGLE) || tile.getFeature().equals(Feature.FOREST)
                    || tile.getFeature().equals(Feature.SWAMP)) {
                tile.removeFeature(null);
            }
        }
        tile.setHasRoad(true);

        for (int i = 0; i < 6; i++) {
            Tile neighbor = Database.getInstance().getNeighbor(tile, i);
            if (neighbor != null) {
                if (neighbor.getPlayer() != null) {
                    // check tiles up to 3 tiles away
                } else {
                    this.addTile(neighbor);
                    neighbor.setPlayer(user);
                    if (neighbor.getHasRiver()[(i + 3) % 6]) {
                        neighbor.setHasRoad(true);
                    }
                }
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
        tile.setPlayer(this.getCenter().getPlayer());
        tile.getPlayer().getCivilization().updateTileStates(null, tile);
        // TODO: check if resource is available and add resource
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean hasResource(Resource resource) {
        for (Resource temp : this.resources) {
            if (temp.equals(resource)) {
                return true;
            }
        }
        return false;
    }

    public void addResource(Resource resource) {
        this.resources.add(resource);
    }

    public void activateResources(Improvement improvement) {
        Civilization player = this.center.getPlayer().getCivilization();
        Resource resource;
        for (Tile tile : this.tiles) {
            if ((resource = tile.getResource()) != null && resource.getImprovement().equals(improvement)) {
                if (resource.getType().equals("STRATEGIC")
                        && !player.hasTechnology(resource.getTechnology())) {
                    return;
                }
                tile.activateResource();
                if (resource.getType().equals("LUXURY") && !player.hasLuxuryResource(resource)) {
                    player.setHappiness(player.getHappiness() + 4);
                }
            }
        }
    }

    public ArrayList<Improvement> getImprovements() {
        return improvements;
    }

    public Boolean hasImprovement(Improvement improvement) {
        for (Improvement tempImprovement : this.improvements) {
            if (tempImprovement.equals(improvement)) {
                return true;
            }
        }
        return false;
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

    public void produceUnit(UnitType unit) {
    }

    public void build(Building building) {
    }
}
