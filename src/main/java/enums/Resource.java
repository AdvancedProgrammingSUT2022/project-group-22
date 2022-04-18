package enums;

public enum Resource {
    // remember to split canFind by ","
    BANANA(1, 0, 0, "FOREST", "farming"),
    COW(1, 0, 0, "PLAIN", "Pasture"),
    GAZELLE(1, 0, 0, "TOUNDRA,FOREST,HIILS", "CAMP"),
    SHEEP(1, 0, 0, "DASHT,GRASSLAND,DESERT,HILS", "PASTURE"),
    WHEAT(1, 0, 0, "DASHT,PLAIN", "FARM"),
    COTTON(0,0,2,"Grass Land, Plain, Desert", "Plantation"),
    DYES(0,0,2,"Jungle, Forest", "Plantation"),
    FURS(0,0,2,"Forest Tundra","Camp"),
    GEMS(0,0,3,"Jungle,GrassLand,Plains,Desert,Tundra,Hills", "Mine"),
    GOLD(0,0,2,"Desert,Plain","Plantation"),
    IVORY(0,0,2,"Plains", "Camp"),
    MARBLE(0,0,2,"GrassLand,Plains, Desert,Tundra,Hills","Quarry"),
    PEARLS(0,0,2,"Coast","FishingBoat"),
    SILK(0,0,2,"Forest","Plantation"),
    SILVER(0,0,2,"Tundra,Desert,Hills","Mine"),
    SPICES(0,0,2,"Jungle","Plantation"),
    SUGAR(0,0,2,"FloodPlains, Marsh","Plantation");

    private final int food;
    private final int product;
    private final int gold;
    private final String canFind;
    private final String technology;

    Resource(int food, int product, int gold, String canFind, String technology) {
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

    public int getProduction() {
        return product;
    }

    public int getFood() {
        return food;
    }
}
