package civilization.views;

import civilization.App;
import civilization.controllers.RegisterMenuController;
import civilization.models.Database;
import civilization.views.components.GameButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainMenu extends Menu{
    private static MainMenu instance;
    private static Scene scene;
    private BorderPane pane;

    public static MainMenu getInstance() {
        if (instance == null)
            instance = new MainMenu();
        return instance;
    }

    private MainMenu(){
        super();
        pane = new BorderPane();
        scene = new Scene(pane, 1280, 800);
        pane.setBackground(new Background(backgroundImage));
        addElements();
    }

    public BorderPane getPane() {
        return pane;
    }

    private void addElements(){
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.maxWidth(350);

        Text title = new Text(530,350,"main menu");
        title.setStyle("-fx-font-size: 50; -fx-font-weight: bold;");

        vBox.getChildren().add(title);
        vBox.getChildren().add(addLogoutButton());

        pane.setCenter(vBox);
    }

    private GameButton addLogoutButton(){
        GameButton logoutButton = new GameButton("logout");
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

}
