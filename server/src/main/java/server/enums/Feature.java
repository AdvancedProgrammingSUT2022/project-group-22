package server.enums;

import java.util.Arrays;
import java.util.List;

public enum Feature {
    FLOODPLAIN(2, 0, 0, -33, 1, Arrays.asList(Resource.WHEAT, Resource.SUGAR), Arrays.asList(Improvement.PLANTATION),
            "/civilization/png/tiles/Floodplain.png"),
    FOREST(1, 1, 0, +25, 2, Arrays.asList(Resource.GAZELLE, Resource.FURS, Resource.DYES, Resource.SILK),
            Arrays.asList(Improvement.CAMP, Improvement.LUMBERMILL, Improvement.MINE, Improvement.PLANTATION),
            "/civilization/png/tiles/Forest.png"),
    ICE(0, 0, 0, 0, -1, null, null, "/civilization/png/tiles/Ice.png"),
    JUNGLE(1, -1, 0, +25, 2, Arrays.asList(Resource.BANANA, Resource.GEMS, Resource.DYES, Resource.SPICES),
            Arrays.asList(Improvement.MINE, Improvement.PLANTATION), "/civilization/png/tiles/Jungle.png"),
    SWAMP(-1, 0, 0, -33, 2, Arrays.asList(Resource.SUGAR), Arrays.asList(Improvement.MINE, Improvement.PLANTATION),
            "/civilization/png/tiles/Swamp.png"),
    OASIS(3, 0, 1, -33, 1, null, null, "/civilization/png/tiles/Oasis.png");

    private final int food;
    private final int production;
    private final int gold;
    private final int combatModifier;
    private final int movementCost;
    private final List<Resource> resources;
    private final List<Improvement> improvements;
    private final String url;

    Feature(int food, int production, int gold, int combatModifier, int movementCost, List<Resource> resources,
            List<Improvement> improvements, String url) {
        this.food = food;
        this.combatModifier = combatModifier;
        this.gold = gold;
        this.movementCost = movementCost;
        this.production = production;
        this.resources = resources;
        this.improvements = improvements;
        this.url = url;
    }

    public static Feature matchFeature(String feature) {
        for (Feature temp : values()) {
            if (temp.name().equals(feature)) {
                return temp;
            }
        }
        return null;
    }

    public String getUrl() {
        return url;
    }

    public List<Improvement> getImprovements() {
        return improvements;
    }

    public List<Resource> getResources() {
        return this.resources;
    }

    public int getMovementCost() {
        return this.movementCost;
    }

    public int getCombatModifier() {
        return this.combatModifier;
    }

    public int getGold() {
        return this.gold;
    }

    public int getProduction() {
        return this.production;
    }

    public int getFood() {
        return this.food;
    }
}
