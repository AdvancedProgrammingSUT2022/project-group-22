package civilization.views.components;

public class TileView {
    private int[] coordinates;
    private int food;
    private int production;
    private int gold;
    private int combatModifier;
    private int movementCost;
    private String landType;
    private String feature;
    private String resource;
    private String tileImage;
    private String featureImage;
    private String resourceImage;
    private Boolean fogOfWar;

    public TileView(int[] coordinates, int food, int production, int gold, int combatModifier, int movementCost,
            String landType,
            String feature, String resource, String tileImage, String featureImage, String resourceImage,
            Boolean fogOfWar) {
        this.coordinates = coordinates;

        this.food = food;
        this.production = production;
        this.gold = gold;
        this.combatModifier = combatModifier;
        this.movementCost = movementCost;
        this.landType = landType;
        this.feature = feature;
        this.resource = resource;
        this.tileImage = tileImage;
        this.featureImage = featureImage;
        this.resourceImage = resourceImage;
        this.fogOfWar = fogOfWar;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getLandType() {
        return landType;
    }

    public void setLandType(String landType) {
        this.landType = landType;
    }

    public int getMovementCost() {
        return movementCost;
    }

    public void setMovementCost(int movementCost) {
        this.movementCost = movementCost;
    }

    public int getCombatModifier() {
        return combatModifier;
    }

    public void setCombatModifier(int combatModifier) {
        this.combatModifier = combatModifier;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getProduction() {
        return production;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public Boolean getFogOfWar() {
        return fogOfWar;
    }

    public void setFogOfWar(Boolean fogOfWar) {
        this.fogOfWar = fogOfWar;
    }

    public String getResourceImage() {
        return resourceImage;
    }

    public void setResourceImage(String resourceImage) {
        this.resourceImage = resourceImage;
    }

    public String getFeatureImage() {
        return featureImage;
    }

    public void setFeatureImage(String featureImage) {
        this.featureImage = featureImage;
    }

    public String getTileImage() {
        return tileImage;
    }

    public void setTileImage(String tileImage) {
        this.tileImage = tileImage;
    }
}
