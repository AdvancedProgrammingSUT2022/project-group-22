package models;

import enums.*;

public class MilitaryUnit extends Unit {

    private int experiencePoints;
    private Boolean isAlert;
    private Boolean isRanged;
    private int range;
    private int rangedCombatStrength;
    private Boolean needsSetUp;
    private int setUp;
    private Boolean isSleep = false;

    public MilitaryUnit(UnitType unitType, Tile position, Boolean needsSetUp) {
        this.unitType = unitType;
        this.movementPoints = unitType.getMovementPoints();
        this.combatStrength = unitType.getCombatStrengh();
        this.experiencePoints = 0;
        this.position = position;
        this.range = unitType.getRange();
        this.rangedCombatStrength = unitType.getRangedCombatStrengh();
        this.isSleeping = false;
        this.isAlert = false;
        this.needsSetUp = needsSetUp;
        this.setUp = 0;
    }

    public UnitType getUnitType() {
        return unitType;
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

    public Boolean isRanged() {
        return this.isRanged;
    }

    public Boolean isAlert() {
        return this.isAlert;
    }

    public void switchAlert() {
        this.isAlert = this.isAlert == true ? false : true;
    }

    public Boolean needsSetUp() {
        return this.needsSetUp;
    }

    public int getSetUp() {
        return this.setUp;
    }

    public void setUp() {
    }

    public void fortify() {
    }

    public void setSleep() {
        isSleep = !isSleep;
    }

    public Boolean getStatus() {
        return isSleep;
    }
}
