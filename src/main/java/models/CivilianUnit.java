package models;

import enums.*;

public class CivilianUnit extends Unit {
    private Boolean isWorker;
    private Boolean isLocked;
    private Boolean isSleep = false;

    public CivilianUnit(UnitType unitType, Tile position) {
        this.unitType = unitType;
        this.isWorker = unitType.name().equals("WORKER") ? true : false;
        this.movementPoints = unitType.getMovementPoints();
        this.combatStrength = unitType.getCombatStrengh();
        this.positon = position;
        this.isSleeping = false;
    }

    public Boolean isLocked() {
        return isLocked;
    }

    public void switchLocked() {
        this.isLocked = !isLocked;
    }

    public Boolean isWorker() {
        return isWorker;
    }

    public void setSleep() {
        isSleep = !isSleep;
    }

    public Boolean getStatus() {
        return isSleep;
    }
}
