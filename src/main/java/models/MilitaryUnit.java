package models;

public class MilitaryUnit extends Unit {
    private int experiencePoints;
    private boolean isAlert;

    private boolean isRanged;
    private int range;
    private int rangedCombatStrength;

    private boolean needsSetUp;
    private int setUp;

    public MilitaryUnit(int mp, int cs, Tile position, int xp, boolean isRanged, int range,
            int rangedCS, boolean needsSetUp) {
        this.movementPoints = mp;
        this.combatStrength = cs;
        this.experiencePoints = xp;
        this.positon = position;
        this.isRanged = isRanged;
        this.range = range;
        this.rangedCombatStrength = rangedCS;
        this.isSleeping = false;
        this.isAlert = false;
        this.needsSetUp = needsSetUp;
        this.setUp = 0;
    }

    public int getExperiencePoints() {
        return this.experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public int getRange() {
        return this.range;
    }

    public int getRangedCombatStrength() {
        return this.rangedCombatStrength;
    }

    public void setRangedCombatStrength(int rangedCombatStrength) {
        this.rangedCombatStrength = rangedCombatStrength;
    }

    public boolean isRanged() {
        return this.isRanged;
    }

    public boolean isAlert() {
        return this.isAlert;
    }

    public void switchAlert() {
        this.isAlert = this.isAlert == true ? false : true;
    }

    public boolean needsSetUp() {
        return this.needsSetUp;
    }

    public int getSetUp() {
        return this.setUp;
    }

    public void setUp() {
    }

    public void fortify() {
    }
}
