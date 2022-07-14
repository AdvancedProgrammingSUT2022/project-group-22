package civilization.enums;

import java.util.*;

public enum LandType {
    DESERT(0, 0, 0, -33, 1, Color.YELLOW_BG, Feature.OASIS, Feature.FLOODPLAIN,
            Arrays.asList(Resource.IRON, Resource.GOLD, Resource.SILVER, Resource.GEMS, Resource.MARBLE,
                    Resource.COTTON, Resource.INCENSE, Resource.SHEEP),
            Arrays.asList(Improvement.FARM, Improvement.MINE, Improvement.PASTURE, Improvement.PLANTATION,
                    Improvement.QUARRY, Improvement.TRADINGPOST, Improvement.FACTORY),
            "/civilization/png/tiles/Desert.png"),
    GRASSLAND(2, 0, 0, -33, 1, Color.GREEN_BRIGHT_BG, Feature.JUNGLE, Feature.SWAMP,
            Arrays.asList(Resource.IRON, Resource.HORSE, Resource.COAL, Resource.COW, Resource.GOLD,
                    Resource.GEMS, Resource.MARBLE, Resource.COTTON, Resource.SHEEP),
            Arrays.asList(Improvement.FARM, Improvement.MINE, Improvement.PASTURE, Improvement.PLANTATION,
                    Improvement.QUARRY, Improvement.TRADINGPOST, Improvement.FACTORY),
            "/civilization/png/tiles/Grassland.png"),
    HILL(0, 2, 0, +25, 2, Color.RED_BRIGHT_BG, Feature.JUNGLE, Feature.FOREST,
            Arrays.asList(Resource.IRON, Resource.COAL, Resource.GAZELLE,
                    Resource.GOLD, Resource.SILVER, Resource.GEMS, Resource.MARBLE, Resource.SHEEP),
            Arrays.asList(Improvement.CAMP, Improvement.MINE, Improvement.QUARRY), "/civilization/png/tiles/Hill.png"),
    MOUNTAIN(0, 0, 0, 0, -1, Color.RED_BG, null, null, null, null, "/civilization/png/tiles/Mountain.png"),
    OCEAN(0, 0, 0, 0, -1, Color.BLUE_BRIGHT_BG, null, null, null, null, "/civilization/png/tiles/Ocean.png"),
    PLAIN(1, 1, 0, -33, 1, Color.GREEN_BG, Feature.JUNGLE, Feature.FOREST,
            Arrays.asList(Resource.IRON, Resource.HORSE, Resource.COAL, Resource.WHEAT, Resource.GOLD, Resource.GEMS,
                    Resource.MARBLE, Resource.IVORY, Resource.COTTON, Resource.INCENSE, Resource.SHEEP),
            Arrays.asList(Improvement.CAMP, Improvement.FARM, Improvement.MINE, Improvement.PASTURE,
                    Improvement.PLANTATION, Improvement.QUARRY, Improvement.TRADINGPOST, Improvement.FACTORY),
            "/civilization/png/tiles/Plain.png"),
    SNOW(0, 0, 0, -33, 1, Color.WHITE_BRIGHT_BG, null, null, Arrays.asList(Resource.IRON),
            Arrays.asList(Improvement.MINE, Improvement.FACTORY), "/civilization/png/tiles/Snow.png"),
    TUNDRA(1, 0, 0, -33, 1, Color.PURPLE_BRIGHT_BG, Feature.JUNGLE, null,
            Arrays.asList(Resource.IRON, Resource.HORSE, Resource.GAZELLE,
                    Resource.SILVER, Resource.GEMS, Resource.MARBLE, Resource.FURS),
            Arrays.asList(Improvement.CAMP, Improvement.FARM, Improvement.MINE, Improvement.PASTURE,
                    Improvement.QUARRY, Improvement.TRADINGPOST, Improvement.FACTORY),
            "/civilization/png/tiles/Tundra.png");

    private final int food;
    private final int production;
    private final int gold;
    private final int combatModifier;
    private final int movementCost;
    private final Color color;
    private final Feature feature1;
    private final Feature feature2;
    private final List<Resource> resources;
    private final List<Improvement> improvements;
    private final String url;

    private final Random random = new Random();

    LandType(int food, int production, int gold, int combatModifier, int movementCost, Color color,
            Feature feature1, Feature feature2, List<Resource> resources, List<Improvement> improvements, String url) {
        this.food = food;
        this.combatModifier = combatModifier;
        this.gold = gold;
        this.movementCost = movementCost;
        this.production = production;
        this.color = color;
        this.feature1 = feature1;
        this.feature2 = feature2;
        this.resources = resources;
        this.improvements = improvements;
        this.url = url;
    }

    public LandType randomLandType() {
        return LandType.values()[random.nextInt(Arrays.asList(LandType.values()).size())];
    }

    public Feature randomFeature() {
        int num = random.nextInt(3);
        return num == 0 ? null : num == 1 ? this.feature1 : this.feature2;
    }

    public Resource randomResource(Feature feature) {
        ArrayList<Resource> resourceList = new ArrayList<Resource>();
        if (this.getResources() != null) {
            resourceList.addAll(this.getResources());
        }
        if (feature.equals(this.feature1) && this.feature1.getResources() != null) {
            resourceList.addAll(this.feature1.getResources());
        } else if (feature.equals(this.feature2) && this.feature2.getResources() != null) {
            resourceList.addAll(this.feature2.getResources());
        }
        int i = random.nextInt(resourceList.size() + 1);
        return i == resourceList.size() ? null : resourceList.get(i);
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
