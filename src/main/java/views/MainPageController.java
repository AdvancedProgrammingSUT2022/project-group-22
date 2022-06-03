package views;

import controllers.MainMenuController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import resources.*;

public class MainPageController extends View {
    private static MainPageController instance = null;
    private GridPane gridPane;
    private Button signUpButton;
    private Button signInButton;
    private Button profileButton;
    private Button gameButton;

    public static MainPageController getInstance() {
        instance = instance == null ? new MainPageController() : instance;
        return instance;
    }

    public void signUpButtonUI() {
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeStage(SignUpPageController.getInstance().createPage(), "Sign Up");
            }
        });
    }

    public void signInButtonUI() {
        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeStage(LoginPageController.getInstance().createPage(), "Sign In");
            }
        });
    }

    public void profileButtonUI() {
        profileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (MainMenuController.getInstance().isLoggedIn()) {
                    changeStage(LoginPageController.getInstance().createPage(), "Sign In");
                } else {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!",
                            "You need to sign in first.");
                }
            }
        });
    }

    public void gameButtonUI() {
        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (MainMenuController.getInstance().isLoggedIn()) {
                    changeStage(LoginPageController.getInstance().createPage(), "Sign In");
                } else {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!",
                            "You need to sign in first.");
                }
            }
        });
    }

    public GridPane createPage() {
        MainPageLayout layout = new MainPageLayout();
        gridPane = layout.addGrid();
        layout.addLabels(gridPane);
        signUpButton = layout.addSignUpButton(gridPane);
        signUpButtonUI();
        signInButton = layout.addSignInButton(gridPane);
        signInButtonUI();
        profileButton = layout.addProfileButton(gridPane);
        profileButtonUI();
        gameButton = layout.addGameButton(gridPane);
        gameButtonUI();
        return gridPane;
    }
}
