package civilization.views;

import civilization.App;
import civilization.models.Database;
import civilization.views.ProfileMenuView.ProfileMenuPage;
import civilization.views.components.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainMenuPage extends Menu {
    private static MainMenuPage instance = null;
    private static Scene scene;
    private BorderPane pane;

    public static MainMenuPage getInstance() {
        return instance == null ? new MainMenuPage() : instance;
    }

    private MainMenuPage() {
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
        vBox.getChildren().add(addGameMenuButton());
        vBox.getChildren().add(addScoreboardButton());

        pane.setCenter(vBox);
    }

    private GameButton addLogoutButton() {
        GameButton logoutButton = new GameButton("Logout");
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Database.getInstance().setLoggedInUser(null);
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
                App.setScene(ProfileMenuPage.getInstance().getPane().getScene());
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
                App.setScene(GameMenuPage.getInstance().getPane().getScene());
                return;
            }
        });
        return gameButton;
    }

}
