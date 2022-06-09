package civilization.views;

import civilization.views.components.GameButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PasswordChangingPage extends Menu{
    private static Scene scene;
    private BorderPane pane;
    private PasswordField passwordField;


    public PasswordChangingPage(){
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

        vBox.getChildren().add(createLabel("current password"));
        vBox.getChildren().add(addPasswordField());

        vBox.getChildren().add(createLabel("new password"));
        vBox.getChildren().add(addNewPasswordField());

        vBox.getChildren().add(createApplyButton());

        pane.setCenter(vBox);
    }

    public PasswordField addPasswordField() {
        passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        passwordField.setMaxWidth(250);
        passwordField.setPromptText("enter current password");
        return passwordField;
    }

    public PasswordField addNewPasswordField() {
        passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        passwordField.setMaxWidth(250);
        passwordField.setPromptText("enter new password");
        return passwordField;
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
