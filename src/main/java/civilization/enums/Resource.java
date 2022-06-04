package civilization.enums;

public enum Resource {
    BANANA("BONUS", 1, 0, 0, Improvement.PLANTATION, null),
    COW("BONUS", 1, 0, 0, Improvement.PASTURE, null),
    GAZELLE("BONUS", 1, 0, 0, Improvement.CAMP, null),
    SHEEP("BONUS", 1, 0, 0, Improvement.PASTURE, null),
    WHEAT("BONUS", 1, 0, 0, Improvement.FARM, null),

    COTTON("LUXURY", 0, 0, 2, Improvement.PLANTATION, null),
    DYES("LUXURY", 0, 0, 2, Improvement.PLANTATION, null),
    FURS("LUXURY", 0, 0, 2, Improvement.CAMP, null),
    GEMS("LUXURY", 0, 0, 3, Improvement.MINE, null),
    GOLD("LUXURY", 0, 0, 2, Improvement.PLANTATION, null),
    INCENSE("LUXURY", 0, 0, 2, Improvement.PLANTATION, null),
    IVORY("LUXURY", 0, 0, 2, Improvement.CAMP, null),
    MARBLE("LUXURY", 0, 0, 2, Improvement.QUARRY, null),
    SILK("LUXURY", 0, 0, 2, Improvement.PLANTATION, null),
    SILVER("LUXURY", 0, 0, 2, Improvement.MINE, null),
    SPICES("LUXURY", 0, 0, 2, Improvement.PLANTATION, null),
    SUGAR("LUXURY", 0, 0, 2, Improvement.PLANTATION, null),

    COAL("STRATEGIC", 0, 1, 0, Improvement.MINE, Technology.SCIENTIFICTHEORY),
    HORSE("STRATEGIC", 0, 1, 0, Improvement.PASTURE, Technology.ANIMALHUSBANDRY),
    IRON("STRATEGIC", 0, 1, 0, Improvement.MINE, Technology.IRONWORKING);

    private final String type;
    private final int food;
    private final int production;
    private final int gold;
    private final Improvement improvement;
    private final Technology technology;

    Resource(String type, int food, int production, int gold, Improvement improvement,
            Technology technology) {
        this.type = type;
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.improvement = improvement;
        this.technology = technology;
    }

    public static Resource matchResource(String resource) {
        for (Resource temp : values()) {
            if (temp.name().equals(resource)) {
                return temp;
            }
        }
        return null;
    }

    public String getType() {
        return this.type;
    }

    public int getFood() {
        return this.food;
    }

    public int getProduction() {
        return this.production;
    }

    public int getGold() {
        return this.gold;
    }

    public Improvement getImprovement() {
        return this.improvement;
    }

    public Technology getTechnology() {
        return this.technology;
    }
}
