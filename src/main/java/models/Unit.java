package models;

import enums.*;

public class Unit {
    protected UnitType unitType;
    protected int movementPoints;
    protected int combatStrength;
    protected Tile position;
    protected Boolean isSleeping;
    protected int taskTurns;
    protected Tile target;

    public UnitType getUnitType() {
        return this.unitType;
    }

    public Tile getPosition() {
        return position;
    }

    public void setPosition(Tile position) {
        this.position = position;
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
        isSleeping = (isSleeping == true ? false : true);
    }

    public Boolean isSleeping() {
        return this.isSleeping;
    }

    public Boolean hasTask() {
        return this.taskTurns == 0 ? false : true;
    }

    public int getTaskTurns() {
        return taskTurns;
    }

    public void setTaskTurns(int taskTurns) {
        this.taskTurns = taskTurns;
    }

    public Tile getTarget() {
        return this.target;
    }

    public void setTarget(Tile target) {
        this.target = target;
    }
}
