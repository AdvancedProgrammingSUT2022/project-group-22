package civilization.enums;

public enum Graphic {
    CHECK("/civilization/png/checkmark.png"),
    CROSS("/civilization/png/cross.png"),
    BUTTON("/civilization/png/button.png"),
    PRESSED_BUTTON("/civilization/png/pressed_button.png"),
    BACKGROUND1("/civilization/png/Menu_background.png"),
    BACKGROUND2("/civilization/png/Menu_background_2.png"),
    GAMEBUTTON("/civilization/png/GameBackButton.png");

    private String url;

    private Graphic(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
