package models;

public class CivilianUnit extends Unit {
    private boolean isWorker;
    private boolean isLocked;

    public CivilianUnit(int mp, Tile position, boolean isWorker, boolean isSleeping) {
        this.isWorker = isWorker;
        this.movementPoints = mp;
        this.combatStrength = 0;
        this.positon = position;
        this.isSleeping = false;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void switchLocked() {
        this.isLocked = isLocked == true ? false : true;
    }

    public boolean isWorker() {
        return isWorker;
    }
}
