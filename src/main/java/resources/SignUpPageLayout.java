package resources;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class SignUpPageLayout extends Layout {
    public void addLabels(GridPane gridPane) {
        Label headerLabel = new Label("Civilization");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

        Label usernameLabel = new Label("Username: ");
        gridPane.add(usernameLabel, 0, 1);

        Label nicknameLabel = new Label("Nickname: ");
        gridPane.add(nicknameLabel, 0, 2);

        Label passwordLabel = new Label("Password: ");
        gridPane.add(passwordLabel, 0, 3);
    }

    public TextField addUsernameField(GridPane gridPane) {
        TextField usernameField = new TextField();
        usernameField.setPrefHeight(40);
        gridPane.add(usernameField, 1, 1);
        return usernameField;
    }

    public TextField addNicknameField(GridPane gridPane) {
        TextField nicknameField = new TextField();
        nicknameField.setPrefHeight(40);
        gridPane.add(nicknameField, 1, 2);
        return nicknameField;
    }

    public PasswordField addPasswordField(GridPane gridPane) {
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 3);
        return passwordField;
    }

    public Button addSignUpButton(GridPane gridPane) {
        Button button = new Button("Sign Up");
        buttonLayout(button);
        gridPane.add(button, 0, 5, 2, 1);
        GridPane.setMargin(button, new Insets(20, 0, 10, 0));
        return button;
    }
}
