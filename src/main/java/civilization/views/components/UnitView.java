package civilization.views.components;

public class UnitView {
    private String unitImage;
    private Boolean isMilitary;

    public UnitView(String unitImage, Boolean isMilitary) {
        this.unitImage = unitImage;
        this.isMilitary = isMilitary;
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
