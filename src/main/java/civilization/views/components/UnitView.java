package civilization.views.components;

public class UnitView {
    private String unitImage;
    private Boolean isMilitary;
    protected String unitType;
    protected int movementPoints;
    protected int combatStrengh;
    private int[] position;

    public UnitView(String unitImage, Boolean isMilitary, String unitType, int movementPoints, int combatStrengh,
            int[] position) {
        this.unitImage = unitImage;
        this.isMilitary = isMilitary;
        this.unitType = unitType;
        this.movementPoints = movementPoints;
        this.combatStrengh = combatStrengh;
        this.position = position;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public String getUnitType() {
        return unitType;
    }

    public int getMovementPoints() {
        return movementPoints;
    }

    public int getCombatStrengh() {
        return combatStrengh;
    }

    public Boolean isMilitary() {
        return isMilitary;
    }

    public void setIsMilitary(Boolean isMilitary) {
        this.isMilitary = isMilitary;
    }

    public String getUnitImage() {
        return unitImage;
    }

    public void setUnitImage(String unitImage) {
        this.unitImage = unitImage;
    }
}
