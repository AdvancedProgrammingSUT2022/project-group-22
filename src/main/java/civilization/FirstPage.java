package civilization;

import civilization.enums.Images;
import civilization.views.Menu;
import civilization.views.RegisterMenu;
import civilization.views.components.GameButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class FirstPage extends Menu {
    private static Scene firstScene;
    private AnchorPane firstPane;

    public FirstPage() {
        super();
        firstPane = new AnchorPane();
        firstScene = new Scene(firstPane, 1280, 800);
        firstPane.setBackground(new Background(backgroundImage));
        addElements();
    }

    public AnchorPane getFirstPane() {
        return firstPane;
    }


    private void addElements(){
        Text title = new Text(530,350,"civilization");
        title.setStyle("-fx-font-size: 50; -fx-font-weight: bold;");

        firstPane.getChildren().add(title);

        firstPane.getChildren().add( createLoginButton());
        firstPane.getChildren().add(createRegisterButton());
        firstPane.getChildren().add(createExitButton());
    }

    private GameButton createLoginButton(){
        GameButton loginButton = new GameButton("login");
        loginButton.setLayoutX(565.5);
        loginButton.setLayoutY(400);
        loginButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {


            }
        });
        return loginButton;
    }

    private GameButton createRegisterButton(){
        GameButton registerButton = new GameButton("register");
        registerButton.setLayoutX(565.5);
        registerButton.setLayoutY(470);
        registerButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                RegisterMenu registerMenu = new RegisterMenu();
                App.setScene(registerMenu.getPane().getScene());
                return;
            }
        });
        return registerButton;
    }

    private GameButton createExitButton(){
        GameButton exitButton = new GameButton("exit");
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


