package civilization.views;

public class TileView {
    private String tileImage;
    private String featureImage;
    private String resourceImage;

    public TileView(String tileImage, String featureImage, String resourceImage) {
        this.tileImage = tileImage;
        this.featureImage = featureImage;
        this.resourceImage = resourceImage;
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
