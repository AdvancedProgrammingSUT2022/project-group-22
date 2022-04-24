package enums;

import java.util.*;

public enum LandType {
    DESERT(0, 0, 0, -33, 1, Color.YELLOW_BG, Feature.OASIS, Feature.FLOODPLAIN,
            Arrays.asList(Resource.IRON, Resource.GOLD, Resource.SILVER, Resource.GEMS, Resource.MARBLE,
                    Resource.COTTON, Resource.INCENSE, Resource.SHEEP)),
    GRASSLAND(2, 0, 0, -33, 1, Color.GREEN_BRIGHT_BG, Feature.JUNGLE, Feature.SWAMP,
            Arrays.asList(Resource.IRON, Resource.HORSE, Resource.COAL, Resource.COW, Resource.GOLD,
                    Resource.GEMS, Resource.MARBLE, Resource.COTTON, Resource.SHEEP)),
    HIILS(0, 2, 0, +25, 2, Color.RED_BRIGHT_BG, Feature.JUNGLE, Feature.FOREST,
            Arrays.asList(Resource.IRON, Resource.COAL, Resource.GAZELLE,
                    Resource.GOLD, Resource.SILVER, Resource.GEMS, Resource.MARBLE, Resource.SHEEP)),
    MOUNTAIN(0, 0, 0, 0, -1, Color.RED_BG, null, null, null),
    OCEAN(0, 0, 0, 0, -1, Color.BLUE_BRIGHT_BG, null, null, null),
    PLAIN(1, 1, 0, -33, 1, Color.GREEN_BG, Feature.JUNGLE, Feature.FOREST,
            Arrays.asList(Resource.IRON, Resource.HORSE, Resource.COAL, Resource.WHEAT, Resource.GOLD, Resource.GEMS,
                    Resource.MARBLE, Resource.IVORY, Resource.COTTON, Resource.INCENSE, Resource.SHEEP)),
    SNOW(0, 0, 0, -33, 1, Color.WHITE_BG, null, null, Arrays.asList(Resource.IRON)),
    TUNDRA(1, 0, 0, -33, 1, Color.PURPLE_BRIGHT_BG, Feature.JUNGLE, null,
            Arrays.asList(Resource.IRON, Resource.HORSE, Resource.GAZELLE,
                    Resource.SILVER, Resource.GEMS, Resource.MARBLE, Resource.FURS));

    private final int food;
    private final int production;
    private final int gold;
    private final int combatModifier;
    private final int movementCost;
    private final Color color;
    private final Feature feature1;
    private final Feature feature2;
    private final List<Resource> resources;

    private static final Random random = new Random();

    LandType(int food, int production, int gold, int combatModifier, int movementCost, Color color,
            Feature feature1, Feature feature2, List<Resource> resources) {
        this.food = food;
        this.combatModifier = combatModifier;
        this.gold = gold;
        this.movementCost = movementCost;
        this.production = production;
        this.color = color;
        this.feature1 = feature1;
        this.feature2 = feature2;
        this.resources = resources;
    }

    public static LandType randomLandType() {
        return LandType.values()[random.nextInt(Arrays.asList(LandType.values()).size())];
    }

    public static Feature randomFeature(LandType landType) {
        int num = random.nextInt(3);
        return num == 0 ? null : num == 1 ? landType.getFeature1() : landType.getFeature2();
    }

    public static Resource randomResource(LandType landType) {
        ArrayList<Resource> resourceList = new ArrayList<Resource>();
        resourceList.addAll(landType.getResources());
        resourceList.addAll(landType.getFeature1().getResources());
        resourceList.addAll(landType.getFeature2().getResources());
        int i = random.nextInt(resourceList.size() + 1);
        return i == resourceList.size() ? null : resourceList.get(i);
    }

    public List<Resource> getResources() {
        return this.resources;
    }

    public Feature getFeature2() {
        return this.feature2;
    }

    public Feature getFeature1() {
        return this.feature1;
    }

    public Color getColor() {
        return color;
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
