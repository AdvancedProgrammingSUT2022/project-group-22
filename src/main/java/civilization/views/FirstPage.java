package civilization.views;

import civilization.App;
import civilization.views.components.GameButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class FirstPage extends Menu {
    private static FirstPage instance;
    private static Scene firstScene;
    private AnchorPane firstPane;

    public static FirstPage getInstance() {
        if (instance == null)
            instance = new FirstPage();
        return instance;
    }

    private FirstPage() {
        super();
        firstPane = new AnchorPane();
        firstScene = new Scene(firstPane, 1280, 800);
        firstPane.setBackground(new Background(backgroundImage));
        addElements();
    }

    public AnchorPane getFirstPane() {
        return firstPane;
    }

    private void addElements() {
        Text title = new Text(400, 300, "C I V I L I Z A T I O N");
        title.setFont(titleFont);
        title.setFill(Color.WHITE);
        title.setStyle("-fx-font-size: 50; -fx-font-weight: bold;");

        firstPane.getChildren().add(title);

        firstPane.getChildren().add(createLoginButton());
        firstPane.getChildren().add(createSignUpButton());
        firstPane.getChildren().add(createExitButton());
    }

    private GameButton createLoginButton() {
        GameButton loginButton = new GameButton("Login");
        loginButton.setFont(buttonFont);
        loginButton.setTextFill(Color.WHITE);
        loginButton.setLayoutX(565.5);
        loginButton.setLayoutY(400);
        loginButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                LoginMenu loginMenu = new LoginMenu();
                App.setScene(loginMenu.getPane().getScene());
                return;
            }
        });
        return loginButton;
    }

    private GameButton createSignUpButton() {
        GameButton signUpButton = new GameButton("Sign Up");
        signUpButton.setFont(buttonFont);
        signUpButton.setTextFill(Color.WHITE);
        signUpButton.setLayoutX(565.5);
        signUpButton.setLayoutY(470);
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SignUpMenu signUpMenu = new SignUpMenu();
                App.setScene(signUpMenu.getPane().getScene());
                return;
            }
        });
        return signUpButton;
    }

    private GameButton createExitButton() {
        GameButton exitButton = new GameButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setTextFill(Color.WHITE);
        exitButton.setLayoutX(565.5);
        exitButton.setLayoutY(540);
        exitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                App.getMainStage().close();
            }
        });
        return exitButton;
    }

}
