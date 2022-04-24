package enums;

public enum UnitType {
    SETTLER(89, 0, 0, 0, 2),
    WORKER(70, 0, 0, 0, 2),
    ARCHER(70, 4, 6, 2, 2),
    CHARIOTARCHER(60, 3, 6, 2, 4),
    SCOUT(25, 4, 0, 0, 2),
    SPEARMAN(50, 7, 0, 0, 2),
    WARRIOR(40, 6, 0, 0, 2),
    CATAPULT(100, 4, 14, 2, 2),
    HORSEMAN(80, 12, 0, 0, 4),
    SWORDSMAN(80, 11, 0, 0, 2),
    CROSSBOWMAN(120, 6, 12, 2, 2),
    KNIGHT(150, 18, 0, 0, 3),
    LOGNSWORDSMAN(150, 18, 0, 0, 3),
    PIKEMAN(100, 10, 0, 0, 2),
    TREBUCHE(170, 6, 20, 2, 2),
    CANON(250, 10, 26, 2, 2),
    CAVARLY(260, 25, 0, 0, 3),
    LANCER(220, 22, 0, 0, 4),
    MUSKETMAN(120, 16, 0, 0, 2),
    RIFLEMAN(200, 25, 0, 0, 2),
    ANITTANKGUN(300, 32, 0, 0, 2),
    ARTILLERY(420, 16, 32, 3, 2),
    INFANTRY(300, 36, 0, 0, 2),
    PANZER(450, 60, 0, 0, 5),
    TANK(450, 50, 0, 0, 4);

    private final int cost;
    private final int combatStrengh;
    private final int rangedCombatStrengh;
    private final int range;
    private final int movementPoints;

    UnitType(int cost, int combatStrengh, int rangedCombatStrengh, int range, int movementPoints) {
        this.cost = cost;
        this.combatStrengh = combatStrengh;
        this.rangedCombatStrengh = rangedCombatStrengh;
        this.range = range;
        this.movementPoints = movementPoints;
    }

    public int getCost() {
        return cost;
    }

    public int getRangedCombatStrengh() {
        return rangedCombatStrengh;
    }

    public int getCombatStrengh() {
        return combatStrengh;
    }

    public int getRange() {
        return range;
    }

    public int getMovementPoints() {
        return movementPoints;
    }
}
