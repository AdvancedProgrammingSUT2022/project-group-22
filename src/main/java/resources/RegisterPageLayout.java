package resources;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class RegisterPageLayout extends Layout {
    public void addLabels(GridPane gridPane) {
        Label headerLabel = new Label("Civilization");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));
    }

    public Button addSignUpButton(GridPane gridPane) {
        Button button = new Button("Sign Up");
        buttonLayout(button);
        gridPane.add(button, 0, 1, 2, 1);
        GridPane.setMargin(button, new Insets(20, 0, 10, 0));
        return button;
    }

    public Button addSignInButton(GridPane gridPane) {
        Button button = new Button("Sign In");
        buttonLayout(button);
        gridPane.add(button, 0, 2, 2, 1);
        GridPane.setMargin(button, new Insets(0, 0, 20, 0));
        return button;
    }
}
