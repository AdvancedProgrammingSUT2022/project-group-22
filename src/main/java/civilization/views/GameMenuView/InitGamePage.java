package civilization.views.GameMenuView;

import civilization.controllers.GameMenuController;
import civilization.views.components.GameButton;
import civilization.views.components.Menu;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class InitGamePage extends Menu {
    private static InitGamePage instance = null;
    private BorderPane pane;
    private TextField player1Field;
    private TextField player2Field;
    private TextField mapX;
    private TextField mapY;

    public static InitGamePage getInstance() {
        return instance == null ? (instance = new InitGamePage()) : instance;
    }

    public BorderPane getPane() {
        return pane;
    }

    public InitGamePage() {
        pane = new BorderPane();
        new Scene(pane, 1280, 800);
        pane.setBackground(new Background(backgroundImage));
        addElements();
    }

    private void addElements() {
        VBox vBox = new VBox();
        vBox.setSpacing(30);
        vBox.setAlignment(Pos.CENTER);
        vBox.maxWidth(350);

        vBox.getChildren().add(createText("START GAME"));

        HBox usernames = new HBox();
        usernames.setSpacing(20);
        usernames.setAlignment(Pos.CENTER);
        usernames.getChildren().add(createLabel("Player 1"));
        usernames.getChildren().add(player1Field = addUsernameField());
        usernames.getChildren().add(createLabel("Player 2"));
        usernames.getChildren().add(player2Field = addUsernameField());
        vBox.getChildren().add(usernames);

        HBox gameSize = new HBox();
        gameSize.setSpacing(20);
        gameSize.setAlignment(Pos.CENTER);
        gameSize.getChildren().add(createLabel("Map Width"));
        gameSize.getChildren().add(mapX = addSizeField());
        gameSize.getChildren().add(createLabel("Map Height"));
        gameSize.getChildren().add(mapY = addSizeField());
        vBox.getChildren().add(gameSize);

        vBox.getChildren().add(addStartButton());
        vBox.getChildren().add(createSwitchSceneButton("back",
                GameMenuPage.getInstance().getPane().getScene()));

        pane.setCenter(vBox);
    }

    public TextField addUsernameField() {
        TextField usernameField = new TextField();
        usernameField.setPrefHeight(40);
        usernameField.setMaxWidth(250);
        usernameField.setPromptText("Enter player's username");
        return usernameField;
    }

    public TextField addSizeField() {
        TextField sizeField = new TextField();
        sizeField.setPrefHeight(40);
        sizeField.setMaxWidth(250);
        sizeField.setPromptText("Max 40");
        return sizeField;
    }

    public GameButton addStartButton() {
        GameButton startButton = new GameButton("Start");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username1, username2;
                String x, y;
                if ((username1 = player1Field.getText()).isEmpty()) {
                    showPopUp("Please choose player 1.");
                } else if ((username2 = player2Field.getText()).isEmpty()) {
                    showPopUp("Please choose player 2.");
                } else if ((x = mapX.getText()).isEmpty()) {
                    showPopUp("Please choose the map's width.");
                } else if ((y = mapY.getText()).isEmpty()) {
                    showPopUp("Please choose the map's height.");
                } else if (Integer.parseInt(x) > 40 || Integer.parseInt(y) > 40) {
                    showPopUp("Please enter a valid size.");
                } else {
                    GameMenuController.getInstance().startGame(username1, username2,
                            Integer.parseInt(x),
                            Integer.parseInt(y));
                }
            }
        });
        return startButton;
    }
}
