package models;

import enums.*;
import java.util.*;

public class Civilization {
    private Color color;
    private int tilePrice;

    private int score;
    private int happiness;
    private int unhappiness;
    private int gold;
    private int beakers;

    private City capital;
    private ArrayList<City> cities = new ArrayList<City>();
    private ArrayList<MilitaryUnit> militaryUnits = new ArrayList<MilitaryUnit>();
    private ArrayList<CivilianUnit> civilianUnits = new ArrayList<CivilianUnit>();
    private ArrayList<Technology> technologies = new ArrayList<Technology>();
    private ArrayList<Technology> possibleTechnologies = new ArrayList<Technology>();
    private HashMap<Technology, Integer> research = new HashMap<Technology, Integer>();
    private ArrayList<String> messages = new ArrayList<String>();
    private ArrayList<Tile> visibleTiles = new ArrayList<Tile>();
    private HashMap<Tile, Tile> revealedTiles = new HashMap<Tile, Tile>();
    private ArrayList<Resource> luxuryResources = new ArrayList<Resource>();

    private HashMap<CivilianUnit, Tile> roadWorkers = new HashMap<CivilianUnit, Tile>();
    private HashMap<CivilianUnit, Improvement> improvementWorkers = new HashMap<CivilianUnit, Improvement>();
    private HashMap<CivilianUnit, Building> buildingWorkers = new HashMap<CivilianUnit, Building>();
    private HashMap<CivilianUnit, Feature> removalWorkers = new HashMap<CivilianUnit, Feature>();

    private City currentCity;
    private MilitaryUnit currentMilitary;
    private CivilianUnit currentCivilian;

    public Civilization(Color color) {
        this.color = color;
        this.tilePrice = 8;
        this.happiness = 10;
        this.unhappiness = 0;
        this.gold = 0;
        this.beakers = 0;
        this.capital = null;
        this.currentCity = null;
        this.currentMilitary = null;
        this.currentCivilian = null;
        this.possibleTechnologies.add(Technology.AGRICULTURE);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getTilePrice() {
        return tilePrice;
    }

    public void setTilePrice(int tilePrice) {
        this.tilePrice = tilePrice;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public City getCapital() {
        return this.capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public ArrayList<City> getCities() {
        return this.cities;
    }

    public void addCity(City city) {
        this.cities.add(city);
    }

    public ArrayList<Tile> getTiles() {
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for (City city : this.cities) {
            for (Tile tile : city.getTiles()) {
                tiles.add(tile);
            }
        }
        return tiles;
    }

    public ArrayList<Tile> getCityTiles(City city) {
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for (Tile tile : city.getTiles()) {
            tiles.add(tile);
        }
        return tiles;
    }

    public int findTile(Tile tile) {
        for (Tile tempTile : this.getTiles()) {
            if (tempTile.equals(tile)) {
                return 1;
            }
        }
        for (Tile tempTile : this.visibleTiles) {
            if (tempTile.equals(tile)) {
                return 1;
            }
        }
        for (Tile tempTile : this.revealedTiles.keySet()) {
            if (tempTile.equals(tile)) {
                return 0;
            }
        }
        return -1;
    }

    public ArrayList<Tile> getVisibleTiles() {
        return visibleTiles;
    }

    public Tile getRevealedTile(Tile tile) {
        return this.revealedTiles.get(tile);
    }

    public void addRevealedTile(Tile tile) {
        revealedTiles.put(tile,
                new Tile(tile.getCoordinates(), tile.getLandType(), tile.getFeature(), tile.getResource()));
    }

    public void updateTileStates(Tile oldPos, Tile newPos) {
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        Database.getInstance().getTilesInRange(newPos, 2, tiles);
        for (Tile tile : tiles) {
            if (findTile(tile) == 0) {
                revealedTiles.remove(tile);
            }
            this.visibleTiles.add(tile);
        }
        if (oldPos != null) {
            Database.getInstance().getTilesInRange(oldPos, 2, tiles);
            for (Tile tile : tiles) {
                this.visibleTiles.remove(tile);
                addRevealedTile(tile);
            }
        }
    }

    public int getPopulation() {
        int population = 0;
        for (City city : this.cities) {
            population += city.getPopulation();
        }
        return population;
    }

    public int getHappiness() {
        return this.happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public int getUnhappiness() {
        return this.unhappiness;
    }

    public void setUnhappiness(int unhappiness) {
        this.unhappiness = unhappiness;
    }

    public int getTotalHappiness() {
        return this.happiness - this.unhappiness;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<MilitaryUnit> getMilitaryUnits() {
        return this.militaryUnits;
    }

    public void addMilitaryUnits(MilitaryUnit militaryUnit) {
        this.militaryUnits.add(militaryUnit);
        updateTileStates(null, militaryUnit.position);
    }

    public void deleteMilitaryUnit(MilitaryUnit militaryUnit) {
        militaryUnits.remove(militaryUnit);
    }

    public ArrayList<CivilianUnit> getCivilianUnits() {
        return this.civilianUnits;
    }

    public void addCivilianUnit(CivilianUnit civilianUnit) {
        this.civilianUnits.add(civilianUnit);
        updateTileStates(null, civilianUnit.position);
    }

    public void deleteCivilianUnit(CivilianUnit civilianUnit) {
        civilianUnits.remove(civilianUnit);
    }

    public Boolean checkUnitTasks() {
        for (MilitaryUnit milUnit : this.militaryUnits) {
            if (!milUnit.hasTask() && !milUnit.isSleeping()) {
                return false;
            }
        }
        for (CivilianUnit civUnit : this.civilianUnits) {
            if (!civUnit.hasTask() && !civUnit.isSleeping()) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<String> getMessages() {
        return this.messages;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }

    public HashMap<Technology, Integer> getResearch() {
        return research;
    }

    public void addResearch(Technology technology) {
        if (technology.getCost() > beakers) {
            research.put(technology, technology.getCost() - beakers);
            this.beakers = 0;
            possibleTechnologies.remove(technology);
        } else {
            this.beakers -= technology.getCost();
            addTechnology(technology);
        }
    }

    public void researchProgress() {
        if (!this.research.isEmpty()) {
            for (Technology technology : research.keySet()) {
                int i = research.get(technology);
                if (i > beakers) {
                    research.replace(technology, i - beakers);
                    this.beakers = 0;
                    return;
                } else {
                    this.beakers = this.beakers - technology.getCost();
                    research.remove(technology);
                    addTechnology(technology);
                }
            }

        }
    }

    public ArrayList<Technology> getTechnologies() {
        return technologies;
    }

    public void addTechnology(Technology technology) {
        this.technologies.add(technology);
        String[] temp = technology.getLeadsToTechs().split(",");
        boolean wasPossible = false;
        for (int j = 0; j < temp.length; j++) {
            for (int i = 0; i < this.possibleTechnologies.size(); i++) {
                if (this.possibleTechnologies.get(i).name().equals(temp[j])) {
                    wasPossible = true;
                    break;
                }
            }
            if (!wasPossible) {
                Technology newTech = Technology.valueOf(temp[j]);
                this.addPossibleTechnologies(newTech);
            }
        }
    }

    public Boolean hasTechnology(Technology technology) {
        for (Technology tempTechnology : this.technologies) {
            if (tempTechnology.equals(technology)) {
                return true;
            }
        }
        return false;
    }

    public Boolean hasLuxuryResource(Resource resource){
        for (int i = 0; i < this.luxuryResources.size(); i++) {
            if(resource.equals(this.luxuryResources.get(i))){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Technology> getPossibleTechnologies() {
        return possibleTechnologies;
    }

    public void addPossibleTechnologies(Technology technology) {
        this.possibleTechnologies.add(technology);
    }

    public Boolean isPossibleTechnologies(Technology technology) {
        for (Technology tempTechnology : this.possibleTechnologies) {
            if (tempTechnology.equals(technology)) {
                return true;
            }
        }
        return false;
    }

    public City getCurrentCity() {
        return this.currentCity;
    }

    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
    }

    public MilitaryUnit getCurrentMilitary() {
        return this.currentMilitary;
    }

    public void setCurrentMilitary(MilitaryUnit currentMilitary) {
        this.currentMilitary = currentMilitary;
        this.currentCivilian = null;
    }

    public CivilianUnit getCurrentCivilian() {
        return this.currentCivilian;
    }

    public void setCurrentCivilian(CivilianUnit currentCivilian) {
        this.currentCivilian = currentCivilian;
        this.currentMilitary = null;
    }

    public void addValues() {
        for (City city : this.cities) {
            for (Tile tile : getCityTiles(city)) {
                this.gold += tile.getGold();
                city.setFood(city.getFood() + tile.getFood());
                city.setProduction(city.getProduction() + tile.getProduction());
            }
        }
        this.beakers += 3 * cities.size() + this.getPopulation();
        this.researchProgress();
    }

    public HashMap<CivilianUnit, Tile> getRoadWorkers() {
        return this.roadWorkers;
    }

    public void addRoadWorker(CivilianUnit civilianUnit, Tile tile) {
        this.roadWorkers.put(civilianUnit, tile);
    }

    public HashMap<CivilianUnit, Feature> getRemovalWorkers() {
        return this.removalWorkers;
    }

    public void addRemovalWorker(CivilianUnit civilianUnit, Feature feature) {
        this.removalWorkers.put(civilianUnit, feature);
    }

    public HashMap<CivilianUnit, Improvement> getImprovementWorkers() {
        return this.improvementWorkers;
    }

    public void addImprovementWorker(CivilianUnit civilianUnit, Improvement improvement) {
        this.improvementWorkers.put(civilianUnit, improvement);
    }

    public HashMap<CivilianUnit, Building> getBuildingWorkers() {
        return this.buildingWorkers;
    }

    public void addBuildingWorker(CivilianUnit civilianUnit, Building building) {
        this.buildingWorkers.put(civilianUnit, building);
    }

    public void reset() {
        for (MilitaryUnit milUnit : this.militaryUnits) {
            milUnit.setMovementPoints(milUnit.getUnitType().getMovementPoints());
        }
        for (CivilianUnit civUnit : this.civilianUnits) {
            civUnit.setMovementPoints(civUnit.getUnitType().getMovementPoints());
        }
    }
}
