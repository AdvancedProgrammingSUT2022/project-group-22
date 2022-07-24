package client.views;

import client.App;
import client.controller.NetworkController;
import client.model.Request;
import client.model.User;
import client.views.components.GameButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainMenu extends Menu {
    private static MainMenu instance = null;
    private static Scene scene;
    private BorderPane pane;

    public static MainMenu getInstance() {
        return instance == null ? new MainMenu() : instance;
    }

    private MainMenu() {
        pane = new BorderPane();
        scene = new Scene(pane, 1280, 800);
        pane.setBackground(new Background(backgroundImage));
        addElements();
    }

    public BorderPane getPane() {
        return pane;
    }

    private void addElements() {
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.maxWidth(350);

        vBox.getChildren().add(createText("MAIN MENU"));
        vBox.getChildren().add(addLogoutButton());
        vBox.getChildren().add(addProfileButton());
        vBox.getChildren().add(addScoreboardButton());
        vBox.getChildren().add(addGameMenuButton());

        pane.setCenter(vBox);
    }

    private GameButton addLogoutButton() {
        GameButton logoutButton = new GameButton("Logout");
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                Database.getInstance().setLoggedInUser(null);
                // todo log out
                Request request = new Request();
                request.setHeader("logout");
                request.addData("jwt", User.getJwt());
                NetworkController.send(request);
                User.setJwt(null);
                App.setScene(FirstPage.getInstance().getPane().getScene());
                return;
            }
        });
        return logoutButton;
    }

    private GameButton addProfileButton() {
        GameButton profileButton = new GameButton("Profile Menu");
        profileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                App.setScene(ProfileMenu.getInstance().getPane().getScene());
                return;
            }
        });
        return profileButton;
    }

    private GameButton addScoreboardButton() {
        GameButton scoreboardButton = new GameButton("Scoreboard");
        scoreboardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                App.setScene(ScoreboardPage.getInstance().getPane().getScene());
                return;
            }
        });
        return scoreboardButton;
    }

    private GameButton addGameMenuButton() {
        GameButton gameButton = new GameButton("Game Menu");
        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                App.setScene(GameMenu.getInstance().getPane().getScene());
                return;
            }
        });
        return gameButton;
    }

}
