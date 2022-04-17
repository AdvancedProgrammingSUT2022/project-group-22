package Controller;

import Model.City;
import Model.Player;
import Model.Tiles;

public class PlayerController {
    private Player player;
    UnitController unitController = new UnitController();
    public void run(Player player){
        this.player = player;
        //see what should we do in this func then call its func
    }
    private void showMessages(){
        //print messages for player
    }

    private void showResearches(){
    }

    private void showUnites(){

    }

    private void showCities(){
    }

    private void showDiplomacy(){

    }

    private void showPopulation(){
        //Gold
        //Military
        //Wealth
        //Compare to others
    }

    private void activeUnit(){

    }

    private void stateCompereTo(){
        //get information from date base and each player score and sort them
    }

    private void showMilitaryUnitStatus(){
        //print military unit status
    }

//    private void combat(){
//        //call is combat allowed or not
//    }

    private City chooseCity(){
        //choose one city to show details and build and something like this
    }

//    private boolean isCombatAllowed(){
//        //check if this combat is allowed or not
//    }

    private void assignWorker(){
        //assign a worker to a tile
    }

    private void chooseTile(){
        //choose Tiles to see
    }

    private Tiles whichTile(){
        //for in cities and tiles to choose a tile
    }

//    private boolean canWin(){
//
//    }

    private void lost(){
        //remove city and other changes
    }

    private void won(){
        //add city and other changes
    }

//    private String howCanItFight(){
//        //search in military units and civilian to see what kind of this unit is
//    }

    private void chooseUnit(){
        //to have some changes in unit
        //use unit controller to apply these changes
        //call unit controller to change in unit and pass the chosen unit
        //check if the return string is delete then delete the unit
        //check if it is ok to mostagher shodan or not
    }

    private boolean isOkToMostagharShodan(){

    }
}
