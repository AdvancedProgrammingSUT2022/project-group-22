package client.enums;

public enum Graphic {
    CHECK("/client/png/checkmark.png"),
    CROSS("/client/png/cross.png"),
    BUTTON("/client/png/button.png"),
    PRESSED_BUTTON("/client/png/pressed_button.png"),
    BACKGROUND("/client/png/Menu_background.png");

    private String url;

    private Graphic(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
