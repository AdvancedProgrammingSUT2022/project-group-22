package views;

import java.util.ArrayList;

public class TileView {
    private String color;
    private String backgroundColor;
    private String nickname;
    private String militaryUnit;
    private String civilianUnit;
    private String feature;
    ArrayList<String> hasRiver;

    public TileView(String color, String backgroundColor, String nickname, String militaryUnit,
            String civilianUnit, String feature, ArrayList<String> hasRiver) {
        this.color = color;
        this.backgroundColor = backgroundColor;
        this.nickname = nickname;
        this.militaryUnit = militaryUnit;
        this.civilianUnit = civilianUnit;
        this.feature = feature;
        this.hasRiver = hasRiver;
    }

    public String getColor() {
        return this.color;
    }

    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getMilitaryUnit() {
        return this.militaryUnit;
    }

    public String getCivilianUnit() {
        return this.civilianUnit;
    }

    public String getFeature() {
        return this.feature;
    }

    public ArrayList<String> getHasRiver() {
        return this.hasRiver;
    }
}
