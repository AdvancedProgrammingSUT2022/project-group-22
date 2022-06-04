package civilization.enums;

public enum Color {

    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),

    BLACK_BRIGHT_BG("\u001B[100m"),
    RED_BRIGHT_BG("\u001B[101m"),
    GREEN_BRIGHT_BG("\u001B[102m"),
    YELLOW_BRIGHT_BG("\u001B[103m"),
    BLUE_BRIGHT_BG("\u001B[104m"),
    PURPLE_BRIGHT_BG("\u001B[105m"),
    CYAN_BRIGHT_BG("\u001B[106m"),
    WHITE_BRIGHT_BG("\u001B[107m"),

    BLACK_BG("\u001B[40m"),
    RED_BG("\u001B[41m"),
    GREEN_BG("\u001B[42m"),
    YELLOW_BG("\u001B[43m"),
    BLUE_BG("\u001B[44m"),
    PURPLE_BG("\u001B[45m"),
    CYAN_BG("\u001B[46m"),
    WHITE_BG("\u001B[47m");

    private final String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
}
