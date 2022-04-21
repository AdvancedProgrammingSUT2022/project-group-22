package enums;

import java.util.*;

public enum Feature {
    FLOODPLAIN(2, 0, 0, -33, 1, Arrays.asList(Resource.WHEAT, Resource.SUGAR)),
    FOREST(1, 1, 0, +25, 2, Arrays.asList(Resource.GAZELLE, Resource.FURS, Resource.DYES, Resource.SILK)),
    ICE(0, 0, 0, 0, -1, null),
    JUNGLE(1, -1, 0, +25, 2, Arrays.asList(Resource.BANANA, Resource.GEMS, Resource.DYES, Resource.SPICES)),
    SWAMP(-1, 0, 0, -33, 2, Arrays.asList(Resource.SUGAR)),
    OASIS(3, 0, 1, -33, 1, null);

    private final int food;
    private final int product;
    private final int gold;
    private final int combatModifier;
    private final int movementCost;
    private final List<Resource> resources;

    Feature(int food, int product, int gold, int combatModifier, int movementCost, List<Resource> resources) {
        this.food = food;
        this.combatModifier = combatModifier;
        this.gold = gold;
        this.movementCost = movementCost;
        this.product = product;
        this.resources = resources;
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

    public int getProduct() {
        return this.product;
    }

    public int getFood() {
        return this.food;
    }
}
