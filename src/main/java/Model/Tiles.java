package Model;

public class Tiles {
    private Player player;
    private int coordinate;
    private final int food;
    private final int gold;
    private final int productivity;
    private final int movePrice;
    private final int battleChanges;
    private final LandType landType;
    private final LandType landFeature;
    private Resource resource;
    Tiles( LandType landType, LandType landFeature){
        this.landFeature = landFeature;
        this.landType = landType;
        this.food = this.landFeature.getFood() + this.landType.getFood() ;
        this.battleChanges = this.landFeature.getBattleChange() + this.landType.getBattleChange();
        this.gold = this.landType.getGold() + this.landFeature.getGold() ;
        this.movePrice = this.landType.getMovePrice() + this.landFeature.getMovePrice();
        this.productivity = this.landType.getProduct() + this.landFeature.getProduct();
    }

    public LandType getLandFeature() {
        return landFeature;
    }

    public LandType getLandType() {
        return landType;
    }

    public int getBattleChanges() {
        return battleChanges;
    }

    public int getMovePrice() {
        return movePrice;
    }

    public int getProductivity() {
        return productivity;
    }

    public int getGold() {
        return gold;
    }

    public int getFood() {
        return food;
    }

    public void addResource(Resource resource){
        this.resource = resource;
    }
}
