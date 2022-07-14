package civilization.views;

import civilization.views.components.GameButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GameMenu extends Menu {

    private static GameMenu instance = null;
    private static Scene scene;
    private BorderPane pane;

    public static GameMenu getInstance() {
        return instance == null ? new GameMenu() : instance;
    }

    private GameMenu() {
        pane = new BorderPane();
        scene = new Scene(pane, 1280, 800);
        pane.setBackground(new Background(backgroundImage));
        addElements();
    }

    public BorderPane getPane() {
        return pane;
    }

    private void addElements() {
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.maxWidth(350);

        vBox.getChildren().add(createText("gameMenu"));
        vBox.getChildren().add(addStartButton());
        vBox.getChildren().add(addBattleButton());
        vBox.getChildren().add(addPlayerButton());
        vBox.getChildren().add(addMapButton());
        vBox.getChildren().add(addContinueButton());
        vBox.getChildren().add(addAutoSaveButton());
        vBox.getChildren().add(createSwitchSceneButton("return", MainMenu.getInstance().getPane().getScene()));

        pane.setCenter(vBox);
    }

    private GameButton addStartButton(){
        GameButton startButton = new GameButton("start");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //ToDo start
            }
        });
        setTooltip("starts new game",startButton);
        return startButton;
    }

    private GameButton addBattleButton(){
        GameButton battleButton = new GameButton("battle");
        battleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //ToDo combat
            }
        });
        setTooltip("play game with someone you choose", battleButton);
        return battleButton;
    }

    private GameButton addPlayerButton(){
        GameButton playerButton = new GameButton("players");
        playerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //ToDo set player numbers
            }
        });
        setTooltip("set number of players", playerButton);
        return playerButton;
    }

    private GameButton addMapButton(){
        GameButton mapButton = new GameButton("map");
        mapButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //ToDo set map dimensions
            }
        });
        setTooltip("set map dimensions", mapButton);
        return mapButton;
    }

    private GameButton addContinueButton(){
        GameButton continueButton = new GameButton("continue");
        continueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //ToDo go to previous games page
            }
        });
        setTooltip("Continuation of previous games", continueButton);
        return continueButton;
    }

    private GameButton addAutoSaveButton(){
        GameButton autoSaveButton = new GameButton("autoSave");
        autoSaveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //ToDo go to auto save setting
            }
        });
        setTooltip("go to auto save setting", autoSaveButton);
        return autoSaveButton;
    }

    private void setTooltip(String text, Button button){
        Tooltip tooltip = new Tooltip(text);
        Tooltip.install(button, tooltip);
    }

}
