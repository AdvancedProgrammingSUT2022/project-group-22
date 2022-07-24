package client.views;

import client.App;
import client.controller.NetworkController;
import client.model.Request;
import client.model.Response;
import client.model.User;
import client.views.components.GameButton;
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
    private PasswordField firstField;
    private PasswordField secondField;


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
        vBox.getChildren().add(createSwitchSceneButton("cancel",ProfileMenu.getInstance().getPane().getScene()));

        pane.setCenter(vBox);
    }

    public PasswordField addPasswordField() {
        firstField = new PasswordField();
        firstField.setPrefHeight(40);
        firstField.setMaxWidth(250);
        firstField.setPromptText("enter current password");
        return firstField;
    }

    public PasswordField addNewPasswordField() {
        secondField = new PasswordField();
        secondField.setPrefHeight(40);
        secondField.setMaxWidth(250);
        secondField.setPromptText("enter new password");
        return secondField;
    }

    public GameButton createApplyButton(){
        GameButton applyButton = new GameButton("Apply");
        applyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Request request = new Request();
                request.setHeader("changePassword");
                request.addData("currentPassword",firstField.getText());
                request.addData("newPassword",secondField.getText());
                request.addData("jwt", User.getJwt());
                Response response = NetworkController.send(request);
                showPopUp((String) response.getData().get("message"));
                if(response.getStatus() == 200){
                    User.setJwt((String) response.getData().get("jwt"));
                    App.setScene(ProfileMenu.getInstance().getPane().getScene());
                }
            }
        });
        return applyButton;
    }

}
