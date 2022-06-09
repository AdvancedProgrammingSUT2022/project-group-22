package civilization.views;

import civilization.views.components.GameButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class NicknameChangingPage extends Menu{
    private static Scene scene;
    private BorderPane pane;
    private TextField nicknameField;


    public NicknameChangingPage(){
        pane = new BorderPane();
        scene = new Scene(pane, 1280, 800);
        pane.setBackground(new Background(backgroundImage));
        addElements();
    }

    public BorderPane getPane() {
        return pane;
    }

    private void addElements(){
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.maxWidth(350);

        vBox.getChildren().add(createLabel("new nickname"));
        vBox.getChildren().add(addNicknameField());

        vBox.getChildren().add(createApplyButton());

        pane.setCenter(vBox);
    }

    public TextField addNicknameField() {
        nicknameField = new TextField();
        nicknameField.setPrefHeight(40);
        nicknameField.setMaxWidth(250);
        nicknameField.setPromptText("enter new nickname");
        return nicknameField;
    }

    public GameButton createApplyButton(){
        GameButton applyButton = new GameButton("Apply");
        applyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //TODO check if it is possible
            }
        });

        return applyButton;
    }
}
