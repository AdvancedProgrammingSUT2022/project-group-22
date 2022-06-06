package civilization.enums;

public enum Avatar {
    ALEXANDER("/civilization/png/icons/0.png"),
    AMANITORE("/civilization/png/icons/1.png"),
    AMBIORIX("/civilization/png/icons/2.png"),
    BASIL("/civilization/png/icons/3.png"),
    BATRIOWEBP("/civilization/png/icons/4.png"),
    CATHERINE_DE_MEDICI("/civilization/png/icons/5.png"),
    CLEOPATRA("/civilization/png/icons/6.png"),
    CYRUS("/civilization/png/icons/7.png"),
    GANDHI("/civilization/png/icons/8.png"),
    GORGO("/civilization/png/icons/9.png"),
    DIDO("/civilization/png/icons/10.png");

    private String url;

    private Avatar(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
