package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import resources.*;

public class RegisterPageController extends View {
    private static RegisterPageController instance = null;
    private GridPane gridPane;
    private Button signUpButton;
    private Button signInButton;

    public static RegisterPageController getInstance() {
        instance = instance == null ? new RegisterPageController() : instance;
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

    public GridPane createPage() {
        RegisterPageLayout layout = new RegisterPageLayout();
        gridPane = layout.addGrid();
        layout.addLabels(gridPane);
        signUpButton = layout.addSignUpButton(gridPane);
        signUpButtonUI();
        signInButton = layout.addSignInButton(gridPane);
        signInButtonUI();
        return gridPane;
    }
}
