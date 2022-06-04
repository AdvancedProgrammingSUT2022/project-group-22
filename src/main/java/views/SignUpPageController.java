package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import resources.*;
import controllers.*;

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
                String username, password, nickname;
                if ((username = usernameField.getText()).isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!",
                            "Please enter a username.");
                } else if ((password = passwordField.getText()).isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!",
                            "Please enter a password.");
                } else if ((nickname = nicknameField.getText()).isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!",
                            "Please enter a nickname.");
                } else {
                    if (RegisterMenuController.getInstance().createUser(gridPane, username, nickname, password)) {
                        GridPane newGrid;
                        changeStage((newGrid = MainPageController.getInstance().createPage()), "Civilization");
                        MainPageController.getInstance().showAlert(Alert.AlertType.CONFIRMATION,
                                newGrid.getScene().getWindow(), "Sign Up Successful!",
                                "Welcome " + username + "!");
                    }
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
