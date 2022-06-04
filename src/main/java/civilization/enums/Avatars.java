package civilization.enums;

public enum Avatars {
    ALEXANDER("/civilization/png/icons/0.png"),
    AMANITORE("/civilization/png/icons/1.png"),
    AMBIORIX("/civilization/png/icons/2.png"),
    BASIL("/civilization/png/icons/3.png"),
    BATRIOWEBP("/civilization/png/icons/4.png"),
    Catherine_de_Medici("/civilization/png/icons/5.png");

    private String url;

    private Avatars(String url) { this.url = url; }

    public String getUrl() {
        return this.url;
    }
}
