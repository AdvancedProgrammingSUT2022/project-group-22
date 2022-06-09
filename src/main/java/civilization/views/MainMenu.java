package civilization.views;

import civilization.App;
import civilization.models.Database;
import civilization.views.components.GameButton;
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

        vBox.getChildren().add(createText("MAINMENU"));
        vBox.getChildren().add(addLogoutButton());
        vBox.getChildren().add(addProfileButton());

        pane.setCenter(vBox);
    }

    private GameButton addLogoutButton() {
        GameButton logoutButton = new GameButton("Logout");
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Database.getInstance().setLoggedInUser(null);
                App.setScene(FirstPage.getInstance().getFirstPane().getScene());
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

}
