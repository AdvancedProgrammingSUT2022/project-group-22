package enums;

public enum LandType {
    DESERT(0, 0, 0, -33, 1),
    GRASSLAND(2, 0, 0, -33, 1),
    HIILS(0, 2, 0, +25, 2),
    MOUNTAIN(0, 0, 0, 0, -1),
    OCEAN(0, 0, 0, 0, -1),
    DASHT(1, 1, 0, -33, 1),
    SNOW(0, 0, 0, -33, 1),
    TOUNDRA(1, 0, 0, -33, 1),

    PLAIN(2, 0, 0, -33, 1),
    FOREST(1, 1, 0, +25, 2),
    ICE(0, 0, 0, 0, -1),
    JUNGLE(1, -1, 0, +25, 2),
    SWAMP(-1, 0, 0, -33, 2),
    VAHE(3, 0, 1, -33, 1);

    private final int food;
    private final int product;
    private final int gold;
    private final int combatModifier;
    private final int movePrice;

    LandType(int food, int product, int gold, int combatModifier, int movePrice) {
        this.food = food;
        this.combatModifier = combatModifier;
        this.gold = gold;
        this.movePrice = movePrice;
        this.product = product;
    }

    public int getMovePrice() {
        return movePrice;
    }

    public int getCombatModifier() {
        return combatModifier;
    }

    public int getGold() {
        return gold;
    }

    public int getProduct() {
        return product;
    }

    public int getFood() {
        return food;
    }
}
