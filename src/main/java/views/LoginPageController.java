package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import models.*;
import resources.*;

public class LoginPageController extends View {
    private static LoginPageController instance = null;
    private GridPane gridPane;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button signUpButton;
    private Button signInButton;
    private Button guestButton;

    public static LoginPageController getInstance() {
        instance = instance == null ? new LoginPageController() : instance;
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

    public void signInButtonUI() {
        signInButton.setOnAction(new EventHandler<ActionEvent>() {
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
                } else if ((user = database.getUserByUsername(username)) == null) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!",
                            "No user with this username exists.");
                } else if (!user.getPassword().equals(password)) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!",
                            "Incorrect password.");
                } else {
                    login(user);
                }
            }
        });
    }

    public void guestButtonUI() {
        guestButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // login(new User("guest", "GUEST"));
            }
        });
    }

    public void login(User user) {
        // database.setCurrentUser(user);
        showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Sign In Successful!",
                "Welcome " + user.getUsername() + "!");
        // changeStage(MainPageController.getInstance().createMainPage(), "Main");
    }

    public GridPane createPage() {
        LoginPageLayout layout = new LoginPageLayout();
        gridPane = layout.addGrid();
        layout.addLabels(gridPane);
        usernameField = layout.addUsernameField(gridPane);
        passwordField = layout.addPasswordField(gridPane);
        signInButton = layout.addSignInButton(gridPane);
        signInButtonUI();
        return gridPane;
    }
}
