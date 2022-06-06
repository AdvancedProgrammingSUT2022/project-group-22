package civilization.views;

import java.time.LocalDateTime;

import civilization.controllers.ScoreboardMenuController;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ScoreboardPage extends Menu {
    private static ScoreboardPage instance;
    private BorderPane pane;
    private VBox vBox;

    public static ScoreboardPage getInstance() {
        return instance != null ? instance : new ScoreboardPage();
    }

    public BorderPane getPane() {
        return this.pane;
    }

    private ScoreboardPage() {
        pane = new BorderPane();
        Scene scene = new Scene(pane, 1280, 800);
        pane.setBackground(new Background(backgroundImage));
        vBox = new VBox();
        // ScoreboardMenuController.getInstance().addUserData();
        addElements();
    }

    public void addElements() {
        Rectangle rectangle = new Rectangle(1100, 650);
        rectangle.setArcWidth(30);
        rectangle.setArcHeight(20);
        rectangle.setFill(Color.web("#00224A", 0.8));

        StackPane centerPane = new StackPane();
        centerPane.getChildren().addAll(rectangle, vBox);

        pane.setCenter(centerPane);
    }

    public void addUser(String nickname, String avatarAddress, int score, LocalDateTime lastWinTime,
            LocalDateTime lastActivityTime, Boolean isCurrentUser) {
        Rectangle rectangle = new Rectangle(950, 55);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(10);
        rectangle.setFill(Color.web((isCurrentUser ? "#4C779B" : "#023D6F"), 1));

        vBox.getChildren().add(rectangle);
    }
}
