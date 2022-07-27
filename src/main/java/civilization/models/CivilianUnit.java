package civilization.models;

import civilization.enums.*;

public class CivilianUnit extends Unit {
    private Boolean isWorker;
    private Boolean isLocked;
    private Boolean isSleep = false;

    public CivilianUnit(UnitType unitType, Tile position) {
        super(unitType, position);
        this.isWorker = unitType.name().equals("WORKER") ? true : false;
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
