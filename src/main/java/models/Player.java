package models;

import java.util.*;
import enums.*;

public class Player {
    private int score;
    private int population;
    private City capital;
    private ArrayList<City> cities = new ArrayList<City>();
    private ArrayList<MilitaryUnit> militaryUnits = new ArrayList<MilitaryUnit>();
    private ArrayList<CivilianUnit> civilianUnits = new ArrayList<CivilianUnit>();
    private ArrayList<Technology> technologies = new ArrayList<Technology>();
    private ArrayList<String> messages = new ArrayList<>();
    private int happiness;
    private ArrayList<Tile> visibleTiles = new ArrayList<>();
    private ArrayList<Tile> revealedTiles = new ArrayList<>();
    private City currentCity;
    private MilitaryUnit currentMilitary;
    private CivilianUnit currentCivilian;

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

    public ArrayList<Tile> getVisibleTiles() {
        return visibleTiles;
    }

    public void addVisibleTiles(ArrayList<Tile> visibleTiles) {
    }

    public ArrayList<Tile> getRevealedTiles() {
        return revealedTiles;
    }

    public void addRevealedTiles(ArrayList<Tile> revealedTiles) {
    }

    public int getPopulation() {
        return this.population;
    }

    public void setPopulation(int population) {
        this.population = population;
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
    }

    public void deleteMilitaryUnit(MilitaryUnit militaryUnit) {
        militaryUnits.remove(militaryUnit);
    }

    public ArrayList<CivilianUnit> getCivilianUnit() {
        return this.civilianUnits;
    }

    public void addCivilianUnit(CivilianUnit civilianUnit) {
        this.civilianUnits.add(civilianUnit);
    }

    public void deleteCivilianUnit(CivilianUnit civilianUnit) {
        civilianUnits.remove(civilianUnit);
    }

    public ArrayList<String> getMessages() {
        return this.messages;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }

    public ArrayList<Technology> getTechnologies() {
        return technologies;
    }

    public void addTechnology(Technology technology) {
        this.technologies.add(technology);
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
        for (Tile tempTile : this.revealedTiles) {
            if (tempTile.equals(tile)) {
                return 0;
            }
        }
        return -1;
    }

    public void setHappiness() {
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

}
