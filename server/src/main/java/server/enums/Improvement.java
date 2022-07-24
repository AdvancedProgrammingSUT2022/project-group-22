package server.enums;

public enum Improvement {
    CAMP(0, 0, 0, Technology.TRAPPING),
    FARM(1, 0, 0, Technology.AGRICULTURE),
    LUMBERMILL(0, 1, 0, Technology.CONSTRUCTION),
    MINE(0, 1, 0, Technology.MINING),
    PASTURE(0, 0, 0, Technology.ANIMALHUSBANDRY),
    PLANTATION(0, 0, 0, Technology.CALENDAR),
    QUARRY(0, 0, 0, Technology.MASONRY),
    TRADINGPOST(0, 0, 1, Technology.TRAPPING),
    FACTORY(0, 2, 0, Technology.ENGINEERING);

    private final int food;
    private final int production;
    private final int gold;
    private final Technology technology;

    Improvement(int food, int production, int gold, Technology technology) {
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.technology = technology;
    }

    public Technology getTechnology() {
        return technology;
    }

    public int getGold() {
        return gold;
    }

    public int getProduction() {
        return production;
    }

    public int getFood() {
        return food;
    }
}
