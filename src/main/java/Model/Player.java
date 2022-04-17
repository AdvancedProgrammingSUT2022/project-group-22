package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private int score;
    private int population;
    private String currentResearch;
    private ArrayList<String> messages = new ArrayList<>();
    private ArrayList<City> cities = new ArrayList<>();
    private ArrayList<MilitaryUnit> militaryUnits = new ArrayList<>();
    private ArrayList<Resource> resources = new ArrayList<>();
    private ArrayList<Civilian> civilians = new ArrayList<>();
    private City currentCity;
    private MilitaryUnit currentMilitary = null;
    private Civilian currentCivilian = null;

    public ArrayList<MilitaryUnit> getMilitaryUnits() {
        return militaryUnits;
    }

    public void setMilitaryUnits(ArrayList<MilitaryUnit> militaryUnits) {
        this.militaryUnits = militaryUnits;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(City city) {
        this.cities.add(city);
    }

    public void addMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }





}
