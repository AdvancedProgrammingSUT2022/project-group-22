package civilization.enums;

public enum Images {
    CHECK("/civilization/png/checkmark.png"),
    CROSS("/civilization/png/cross.png"),
    BUTTON("/civilization/png/button.png"),
    PRESSED_BUTTON("/civilization/png/pressed_button.png"),
    BACKGROUND("/civilization/png/Menu_background.png");

    private String url;

    private Images(String url) { this.url = url; }

    public String getUrl() {
        return this.url;
    }
}
