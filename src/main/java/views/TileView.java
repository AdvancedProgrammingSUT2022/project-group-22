package views;

import java.util.ArrayList;

public class TileView {
    private String color;
    private String nickname;
    private String militaryUnit;
    private String civilianUnit;
    private String feature;
    ArrayList<String> hasRiver;

    TileView(String color, String nickname, String militaryUnit, String civilianUnit, String feature,
            ArrayList<String> hasRiver) {
        this.color = color;
        this.nickname = nickname;
        this.militaryUnit = militaryUnit;
        this.civilianUnit = civilianUnit;
        this.feature = feature;
        this.hasRiver = hasRiver;
    }

    public String getColor() {
        return color;
    }

    public String getNickname() {
        return nickname;
    }

    public String getMilitaryUnit() {
        return militaryUnit;
    }

    public String getCivilianUnit() {
        return civilianUnit;
    }

    public String getFeature() {
        return feature;
    }

    public ArrayList<String> getHasRiver() {
        return hasRiver;
    }
}
