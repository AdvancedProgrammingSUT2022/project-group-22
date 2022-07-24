package server.enums;

public enum Avatar {
    ALEXANDER("/client/png/icons/0.png"),
    AMANITORE("/client/png/icons/1.png"),
    AMBIORIX("/client/png/icons/2.png"),
    BASIL("/client/png/icons/3.png"),
    BATRIO("/client/png/icons/4.png"),
    CATHERINE_DE_MEDICI("/client/png/icons/5.png"),
    CLEOPATRA("/client/png/icons/6.png");
//    CYRUS("/civilization/png/icons/7.png"),
//    GANDHI("/civilization/png/icons/8.png"),
//    GORGO("/civilization/png/icons/9.png");
//    DIDO("/civilization/png/icons/11.png");

    private String url;

    private Avatar(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
