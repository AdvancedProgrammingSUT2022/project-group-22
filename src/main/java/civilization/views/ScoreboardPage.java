package civilization.views;

import java.util.ArrayList;

import civilization.App;
import civilization.controllers.ScoreboardMenuController;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ScoreboardPage extends Menu {
    private static ScoreboardPage instance;
    private BorderPane pane;
    private GridPane grid;

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
        addGrid();
        addRatings(ScoreboardMenuController.getInstance().createUserView());
        addElements();
    }

    public void addElements() {
        Rectangle rectangle = new Rectangle(1100, 650);
        rectangle.setArcWidth(30);
        rectangle.setArcHeight(20);
        rectangle.setFill(Color.web("#00224A", 0.8));

        StackPane centerPane = new StackPane();
        centerPane.getChildren().addAll(rectangle, grid);

        pane.setCenter(centerPane);
    }

    public void addGrid() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
    }

    public void addRatings(ArrayList<UserView> users) {
        int i = 1;
        for (UserView user : users) {
            System.out.println(user.getAvatarAddress() + user.isCurrentUser());
            grid.add(new ImageView(new Image(App.class.getResource(user.getAvatarAddress()).toExternalForm())), 1, i);
            Rectangle rectangle = new Rectangle(950, 50);
            rectangle.setArcWidth(20);
            rectangle.setArcHeight(10);
            rectangle.setFill(Color.web((user.isCurrentUser() ? "#4C779B" : "#023D6F"), 1));

            grid.add(rectangle, 2, i++);
        }
    }
}
