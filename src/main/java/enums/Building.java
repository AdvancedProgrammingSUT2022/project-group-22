package enums;

public enum Building {
    BARRACKS(80, 1, Technology.BRONZEWORKING),
    GRANARY(100, 1, Technology.POTTERY),
    LIBRARY(80, 1, Technology.WRITING),
    MONUMENT(60, 1, null),
    WALLS(100, 1, Technology.MASONRY),
    WATERMILL(120, 2, Technology.THEWHEEL),
    ARMORY(130, 3, Technology.IRONWORKING),
    BURIALTOMB(120, 0, Technology.PHILOSOPHY),
    CIRCUS(150, 3, Technology.HORSEBACKRIDING),
    COLOSSEUM(150, 3, Technology.CONSTRUCTION),
    COURTHOUSE(200, 5, Technology.MATHEMATICS),
    STABLE(100, 1, Technology.HORSEBACKRIDING),
    TEMPLE(120, 2, Technology.PHILOSOPHY),
    CASTLE(200, 3, Technology.CHIVALRY),
    FORGE(150, 2, Technology.METALCASTING),
    GARDEN(120, 2, Technology.THEOLOGY),
    MARKET(120, 0, Technology.CURRENCY),
    MINT(120, 0, Technology.CURRENCY),
    MONASTERY(120, 2, Technology.THEOLOGY),
    UNIVERSITY(200, 2, Technology.EDUCATION),
    WORKSHOP(100, 2, Technology.METALCASTING),
    BANK(220, 0, Technology.BANKING),
    MILITARYACADEMY(350, 3, Technology.MILITARYSCIENCE),
    MUSEUM(350, 3, Technology.ARCHAEOLOGY),
    OPERAHOUSE(220, 3, Technology.ACOUSTICS),
    PUBLICSCHOOL(350, 3, Technology.SCIENTIFICTHEORY),
    SATRAPSCOURT(220, 0, Technology.BANKING),
    THEATER(300, 5, Technology.PRINTINGPRESS),
    WINDMILL(180, 2, Technology.ECONOMICS),
    ARSENAL(350, 3, Technology.RAILROAD),
    BROADCASTTOWER(600, 3, Technology.RADIO),
    FACTORY(300, 3, Technology.STEAMPOWER),
    HOSPITAL(400, 2, Technology.BIOLOGY),
    MILITARYBASE(450, 4, Technology.TELEGRAPH),
    STOCKEXCHANGE(650, 0, Technology.ELECTRICITY);

    private final int cost;
    private final int maintenance;
    Technology technologyRequired;

    Building(int cost, int maintenance, Technology technology) {
        this.cost = cost;
        this.maintenance = maintenance;
        this.technologyRequired = technology;
    }

    public int getCost() {
        return cost;
    }

    public int getMaintenance() {
        return maintenance;
    }

    public Technology getTechnology() {
        return technologyRequired;
    }
}
