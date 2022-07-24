package server.enums;

public enum UnitType {
    SETTLER(89, 0, 0, 0, 2, "CIVILIAN", null, null),
    WORKER(70, 0, 0, 0, 2, "CIVILIAN", null, null),
    ARCHER(70, 4, 6, 2, 2, "ARCHERY", null, Technology.ARCHERY),
    CHARIOTARCHER(60, 3, 6, 2, 4, "MOUNTED", Resource.HORSE, Technology.THEWHEEL),
    SCOUT(25, 4, 0, 0, 2, "RECON", null, null),
    SPEARMAN(50, 7, 0, 0, 2, "MELEE", null, Technology.BRONZEWORKING),
    WARRIOR(40, 6, 0, 0, 2, "MELEE", null, null),
    CATAPULT(100, 4, 14, 2, 2, "SIEGE", Resource.IRON, Technology.MATHEMATICS),
    HORSEMAN(80, 12, 0, 0, 4, "MOUNTED", Resource.HORSE, Technology.HORSEBACKRIDING),
    SWORDSMAN(80, 11, 0, 0, 2, "MELEE", Resource.IRON, Technology.IRONWORKING),
    CROSSBOWMAN(120, 6, 12, 2, 2, "ARCHERY", null, Technology.MACHINERY),
    KNIGHT(150, 18, 0, 0, 3, "MOUNTED", Resource.HORSE, Technology.CHIVALRY),
    LOGNSWORDSMAN(150, 18, 0, 0, 3, "MELEE", Resource.IRON, Technology.STEEL),
    PIKEMAN(100, 10, 0, 0, 2, "MELEE", null, Technology.CIVILSERVICE),
    TREBUCHE(170, 6, 20, 2, 2, "SIEGE", Resource.IRON, Technology.PHYSICS),
    CANON(250, 10, 26, 2, 2, "SIEGE", null, Technology.CHEMISTRY),
    CAVARLY(260, 25, 0, 0, 3, "MOUNTED", Resource.HORSE, Technology.MILITARYSCIENCE),
    LANCER(220, 22, 0, 0, 4, "MOUNTED", Resource.HORSE, Technology.METALLURGY),
    MUSKETMAN(120, 16, 0, 0, 2, "GUNPOWDER", null, Technology.GUNPOWDER),
    RIFLEMAN(200, 25, 0, 0, 2, "GUNPOWDER", null, Technology.RIFLING),
    ANITTANKGUN(300, 32, 0, 0, 2, "GUNPOWDER", null, Technology.REPLACEABLEPARTS),
    ARTILLERY(420, 16, 32, 3, 2, "SIEGE", null, Technology.DYNAMITE),
    INFANTRY(300, 36, 0, 0, 2, "GUNPOWDER", null, Technology.REPLACEABLEPARTS),
    PANZER(450, 60, 0, 0, 5, "ARMORED", null, Technology.COMBUSTION),
    TANK(450, 50, 0, 0, 4, "ARMORED", null, Technology.COMBUSTION);

    private final int cost;
    private final int combatStrengh;
    private final int rangedCombatStrengh;
    private final int range;
    private final int movementPoints;
    private final String combatType;
    private final Resource resource;
    private final Technology technology;

    UnitType(int cost, int combatStrengh, int rangedCombatStrengh, int range, int movementPoints, String combatType,
            Resource resource, Technology technology) {
        this.cost = cost;
        this.combatStrengh = combatStrengh;
        this.rangedCombatStrengh = rangedCombatStrengh;
        this.range = range;
        this.movementPoints = movementPoints;
        this.combatType = combatType;
        this.resource = resource;
        this.technology = technology;
    }

    public static UnitType matchUnitType(String type) {
        for (UnitType unitType : values()) {
            if (unitType.name().equals(type)) {
                return unitType;
            }
        }
        return null;
    }

    public int getCost() {
        return this.cost;
    }

    public int getRangedCombatStrengh() {
        return this.rangedCombatStrengh;
    }

    public int getCombatStrengh() {
        return this.combatStrengh;
    }

    public int getRange() {
        return this.range;
    }

    public int getMovementPoints() {
        return this.movementPoints;
    }

    public String getCombatType() {
        return this.combatType;
    }

    public Resource getResource() {
        return this.resource;
    }

    public Technology getTechnology() {
        return this.technology;
    }
}
