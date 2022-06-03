package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import controllers.*;
import resources.*;

public class LoginPageController extends View {
    private static LoginPageController instance = null;
    private GridPane gridPane;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button signInButton;

    public static LoginPageController getInstance() {
        instance = instance == null ? new LoginPageController() : instance;
        return instance;
    }

    public void signInButtonUI() {
        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username, password;
                if ((username = usernameField.getText()).isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!",
                            "Please enter your username.");
                } else if ((password = passwordField.getText()).isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!",
                            "Please enter your password.");
                } else {
                    RegisterMenuController.getInstance().login(gridPane, username, password);
                    changeStage(MainPageController.getInstance().createPage(), "Civilization");
                }
            }
        });
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
