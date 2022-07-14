package civilization.views;

import civilization.App;
import civilization.controllers.RegisterMenuController;
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

public class LoginMenu extends Menu {
    private static Scene scene;
    private BorderPane pane;
    private TextField usernameField;
    private PasswordField passwordField;

    public LoginMenu() {
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

        vBox.getChildren().add(createText("LOGIN MENU"));

        vBox.getChildren().add(createLabel("username"));
        vBox.getChildren().add(addUsernameField());

        vBox.getChildren().add(createLabel("password"));
        vBox.getChildren().add(addPasswordField());

        vBox.getChildren().add(addLoginButton());

        pane.setCenter(vBox);
    }

    public TextField addUsernameField() {
        usernameField = new TextField();
        usernameField.setPrefHeight(40);
        usernameField.setMaxWidth(250);
        usernameField.setPromptText("enter username");
        return usernameField;
    }

    public PasswordField addPasswordField() {
        passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        passwordField.setMaxWidth(250);
        passwordField.setPromptText("enter password");
        return passwordField;
    }

    public GameButton addLoginButton() {
        GameButton loginButton = new GameButton("Login");
        // signUpButton.setAlignment(Pos.BOTTOM_CENTER);
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            String username, password;

            @Override
            public void handle(ActionEvent event) {
                if ((username = usernameField.getText()).isEmpty()) {
                    showPopUp("Please enter a username.");
                } else if ((password = passwordField.getText()).isEmpty()) {
                    showPopUp("Please enter a password.");
                } else if (RegisterMenuController.login(username, password)) {
                    showPopUp("user logged in successfully");
                    App.setScene(MainMenu.getInstance().getPane().getScene());
                }
            }
        });
        return loginButton;
    }

}
