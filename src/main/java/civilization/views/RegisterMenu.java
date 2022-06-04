package civilization.views;

import civilization.App;
import civilization.enums.Avatars;
import civilization.views.components.GameButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;

import java.util.Random;

public class RegisterMenu extends Menu{
    private static Scene scene;
    private BorderPane pane;
    private TextField usernameField;
    private TextField nicknameField;
    private PasswordField passwordField;
    private String randomAvatar;

    public RegisterMenu(){
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

        Text title = new Text(530,350,"register menu");
        title.setStyle("-fx-font-size: 50; -fx-font-weight: bold;");

        vBox.getChildren().add(title);
        vBox.getChildren().add(addUsernameField());
        vBox.getChildren().add(addNicknameField());
        vBox.getChildren().add(addPasswordField());
        vBox.getChildren().add(addSignUpButton());

        pane.setCenter(vBox);
    }

    public TextField addNicknameField() {
        nicknameField = new TextField();
        nicknameField.setPrefHeight(40);
        nicknameField.setMaxWidth(200);
        return nicknameField;
    }

    public TextField addUsernameField() {
        usernameField = new TextField();
        usernameField.setPrefHeight(40);
        usernameField.setMaxWidth(200);
        return usernameField;
    }


    public PasswordField addPasswordField() {
        passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        passwordField.setMaxWidth(200);
        return passwordField;
    }

    public GameButton addSignUpButton() {
        GameButton signUpButton = new GameButton("Sign Up");
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (usernameField.getText().isEmpty()) {
                    showPopUp("Please enter a username.");
                } else if (passwordField.getText().isEmpty()) {
                    showPopUp("Please enter a password.");
                } else if (nicknameField.getText().isEmpty()) {
                    showPopUp("Please enter a nickname.");
                } else {
                    Random random = new Random();
                    int i = random.nextInt(Avatars.values().length);
                    randomAvatar = Avatars.values()[i].getUrl();
                }
                // TODO : other errors
            }
        });
        return signUpButton;
    }
}
