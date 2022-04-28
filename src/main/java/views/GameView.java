package views;

import controllers.GameController;
import enums.Commands;
import models.City;
import models.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameView {
    public void run(Player player){
        Matcher matcher;
        GameController gameController = new GameController();
        while(true){
            String command = Processor.getInstance().getInput();
            if((matcher = Commands.getMatcher(command,Commands.INFOCITY))!= null) showCity(player);
            else if((matcher = Commands.getMatcher(command,Commands.INFORESEARCH)) != null) showResearch(player);
            else if((matcher = Commands.getMatcher(command,Commands.INFODEALS)) != null) showDeals(player);
            else if((matcher = Commands.getMatcher(command,Commands.INFODEMOGRAPHICS)) != null)showDemographics(player);
            else if((matcher = Commands.getMatcher(command,Commands.INFODIPLOMACY)) != null) showDiplomacy();
            else if((matcher = Commands.getMatcher(command,Commands.INFODIPLOMATIC)) != null) return;
            else if((matcher = Commands.getMatcher(command,Commands.INFOECONOMIC)) != null) return;
            else if((matcher = Commands.getMatcher(command,Commands.INFOMILITARY)) != null) return;
            else if((matcher = Commands.getMatcher(command,Commands.INFONOTIFICATIONS)) != null) showMessages(player);
            else if((matcher = Commands.getMatcher(command,Commands.INFOUNIT)) != null) return;
            else if((matcher = Commands.getMatcher(command,Commands.INFOVICTORY)) != null) return;
            else if((matcher = Commands.getMatcher(command,Commands.MENUEXIT)) != null) return;
            else if((matcher = Commands.getMatcher(command,Commands.SELECTCITYNAME)) != null) return;
            else if((matcher = Commands.getMatcher(command,Commands.SELECTCITYPOS)) != null) return;
            else if((matcher = Commands.getMatcher(command,Commands.SELECTUNITCOMBAT)) != null) gameController.unitCombatPosition(matcher);
            else if((matcher = Commands.getMatcher(command,Commands.SELECTUNITNONCOMBAT)) != null) return;
            else System.out.println("invalid command");
        }
    }

    private void showCity(Player player){
        for(int i = 0 ; i < player.getCities().size() ; i++){
            System.out.println(player.getCities().get(i).getName());
        }
    }

    private void showResearch(Player player){
        for(int i = 0 ; i < player.getResearches().size() ; i++){
            System.out.println(player.getResearches().get(i));
        }
    }

    private void showDeals(Player player){
    }

    private void showDemographics(Player player){
        System.out.println(player.getPopulation());
    }

    private void showDiplomacy(){
    }

    private void showMessages(Player player){
        for(int i = 0 ; i < player.getMessages().size() ; i++){
            System.out.println(player.getMessages().get(i));
        }
    }

    public static void showCurrentCity(City city){

    }
}
