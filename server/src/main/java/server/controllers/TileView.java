package server.controllers;

public class TileView {
    private String tileImage;
    private String featureImage;

    public TileView(String tileImage, String featureImage) {
        this.tileImage = tileImage;
        this.featureImage = featureImage;
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
