package Model;

public enum Resource {
    //remember to split canFind by ","
    BANANA(1,0,0, "FOREST", "farming"),
    COW(1,0,0,"PLAIN","Pasture"),
    GAZELLE(1,0,0,"TOUNDRA,FOREST,HIILS", "CAMP"),
    SHEEP(1,0,0,"DASHT,GRASSLAND,DESERT,HILS", "PASTURE"),
    WHEAT(1,0,0,"DASHT,PLAIN", "FARM");

    private final int food;
    private final int product;
    private final int gold;
    private final String  canFind;
    private final String technology;

    Resource(int food, int product , int gold, String canFind, String technology){
        this.food = food;
        this.product = product;
        this.gold = gold;
        this.canFind = canFind;
        this.technology = technology;
    }

    public String getTechnology() {
        return technology;
    }

    public String getCanFind() {
        return canFind;
    }

    public int getGold() {
        return gold;
    }

    public int getProduct() {
        return product;
    }

    public int getFood() {
        return food;
    }
}
