package civilization.views;

public class TileView {
    private String tileImage;
    private String featureImage;
    private String resourceImage;
    private Boolean fogOfWar;

    public TileView(String tileImage, String featureImage, String resourceImage, Boolean fogOfWar) {
        this.tileImage = tileImage;
        this.featureImage = featureImage;
        this.resourceImage = resourceImage;
        this.fogOfWar = fogOfWar;
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
