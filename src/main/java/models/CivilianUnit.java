package models;

import enums.*;

public class CivilianUnit extends Unit {
    private boolean isWorker;
    private boolean isLocked;
    private boolean isSleep = false;

    public CivilianUnit(UnitType unitType, Tile position) {
        this.unitType = unitType;
        this.isWorker = unitType.name().equals("WORKER") ? true : false;
        this.movementPoints = unitType.getMovementPoints();
        this.combatStrength = unitType.getCombatStrengh();
        this.positon = position;
        this.isSleeping = false;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void switchLocked() {
        this.isLocked = !isLocked;
    }

    public boolean isWorker() {
        return isWorker;
    }

    public void setSleep(){
        isSleep = !isSleep;
    }

    public boolean getStatus(){
        return isSleep;
    }
}
