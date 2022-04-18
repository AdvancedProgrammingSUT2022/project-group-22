package models;

public class Unit {
    protected int movementPoints;
    protected int combatStrength;
    protected Tile positon;
    protected boolean isSleeping;
    protected int taskTurns;

    public Tile getPositon() {
        return positon;
    }

    public void setPositon(Tile positon) {
        this.positon = positon;
    }

    public int getMovementPoints() {
        return movementPoints;
    }

    public void setMovementPoints(int movementPoints) {
        this.movementPoints = movementPoints;
    }

    public int getCombatStrength() {
        return combatStrength;
    }

    public void setCombatStrength(int combatStrength) {
        this.combatStrength = combatStrength;
    }

    public void switchSleeping() {
        isSleeping = isSleeping == true ? false : true;
    }

    public int getTaskTurns() {
        return taskTurns;
    }

    public void setTaskTurns(int taskTurns) {
        this.taskTurns = taskTurns;
    }
}
