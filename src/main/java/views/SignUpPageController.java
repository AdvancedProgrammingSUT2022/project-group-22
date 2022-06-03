package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import models.*;
import resources.*;

public class SignUpPageController extends View {
    private static SignUpPageController instance = null;
    private GridPane gridPane;
    private TextField usernameField;
    private TextField nicknameField;
    private PasswordField passwordField;
    private Button signUpButton;

    public static SignUpPageController getInstance() {
        instance = instance == null ? new SignUpPageController() : instance;
        return instance;
    }

    public void signUpButtonUI() {
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username, password;
                User user;
                if ((username = usernameField.getText()).isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!",
                            "Please enter your username.");
                } else if ((password = passwordField.getText()).isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!",
                            "Please enter a password.");
                } else if (database.getUserByUsername(username) != null) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!",
                            "Username already taken.");
                } else {
                    // database.addUser(user = new User(username, password));
                    // login(user);
                }

            }
        });
    }

    public GridPane createPage() {
        SignUpPageLayout layout = new SignUpPageLayout();
        gridPane = layout.addGrid();
        layout.addLabels(gridPane);
        usernameField = layout.addUsernameField(gridPane);
        nicknameField = layout.addNicknameField(gridPane);
        passwordField = layout.addPasswordField(gridPane);
        signUpButton = layout.addSignUpButton(gridPane);
        signUpButtonUI();
        return gridPane;
    }
}
