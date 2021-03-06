package civilization.views;

import civilization.App;
import civilization.controllers.RegisterMenuController;
import civilization.views.components.GameButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SignUpMenu extends Menu {
    private static Scene scene;
    private BorderPane pane;
    private TextField usernameField;
    private TextField nicknameField;
    private PasswordField passwordField;

    public SignUpMenu() {
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

        vBox.getChildren().add(createText("SIGN UP MENU"));

        vBox.getChildren().add(createLabel("username"));
        vBox.getChildren().add(addUsernameField());

        vBox.getChildren().add(createLabel("nickname"));
        vBox.getChildren().add(addNicknameField());

        vBox.getChildren().add(createLabel("password"));
        vBox.getChildren().add(addPasswordField());

        vBox.getChildren().add(addSignUpButton());
        vBox.getChildren().add(createSwitchSceneButton("return",FirstPage.getInstance().getPane().getScene()));

        pane.setCenter(vBox);
    }

    public TextField addUsernameField() {
        usernameField = new TextField();
        usernameField.setPrefHeight(40);
        usernameField.setMaxWidth(250);
        usernameField.setPromptText("enter username");
        return usernameField;
    }

    public TextField addNicknameField() {
        nicknameField = new TextField();
        nicknameField.setPrefHeight(40);
        nicknameField.setMaxWidth(250);
        nicknameField.setPromptText("enter nickname");
        return nicknameField;
    }

    public PasswordField addPasswordField() {
        passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        passwordField.setMaxWidth(250);
        passwordField.setPromptText("enter password");
        return passwordField;
    }

    public GameButton addSignUpButton() {
        GameButton signUpButton = new GameButton("Sign Up");
        // signUpButton.setAlignment(Pos.BOTTOM_CENTER);
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            String username, nickname, password;

            @Override
            public void handle(ActionEvent event) {
                if ((username = usernameField.getText()).isEmpty()) {
                    showPopUp("Please enter a username.");
                } else if ((nickname = nicknameField.getText()).isEmpty()) {
                    showPopUp("Please enter a nickname.");
                } else if ((password = passwordField.getText()).isEmpty()) {
                    showPopUp("Please enter a password.");
                } else if (RegisterMenuController.signUp(username, nickname, password)) {
                    showPopUp("user created and logged in successfully");
                    App.setScene(MainMenu.getInstance().getPane().getScene());
                }
            }
        });
        return signUpButton;
    }
}
