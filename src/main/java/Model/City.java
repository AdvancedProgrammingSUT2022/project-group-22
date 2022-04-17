package Model;

import java.util.ArrayList;

public class City {
    private ArrayList<Tiles> tiles = new ArrayList<>();

    private ArrayList<MilitaryUnit> militaryUnits = new ArrayList<>();

    private ArrayList<Civilian> civilians = new ArrayList<>();

    private MilitaryUnit garrisonUnit;

    public void addTile(Tiles tile){
        this.tiles.add(tile);
    }

    public ArrayList<Tiles> getTiles() {
        return tiles;
    }

    public void produceUnit(Unit unit){

    }

    public void buildBuilding(Buildings buildings){

    }

    public void addGarrisonUnit(MilitaryUnit militaryUnit){

    }
}
