package civilization.views;

import java.time.format.*;
import java.util.ArrayList;

import civilization.controllers.ScoreboardMenuController;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ScoreboardPage extends Menu {
    private static ScoreboardPage instance;
    private BorderPane pane;
    private GridPane bgGrid;
    private GridPane mainGrid;

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
        addElements();
    }

    public void addElements() {
        Text title = createText("scoreboard");
        BorderPane.setAlignment(title, Pos.CENTER);
        BorderPane.setMargin(title, new Insets(70, 0, 0, 0));
        pane.setTop(title);

        Rectangle rectangle = new Rectangle(900, 550);
        rectangle.setArcWidth(30);
        rectangle.setArcHeight(20);
        rectangle.setFill(Color.web("#00224A", 0.8));

        bgGrid = new GridPane();
        bgGrid.setAlignment(Pos.CENTER);
        bgGrid.setVgap(10);

        mainGrid = new GridPane();
        mainGrid.setAlignment(Pos.CENTER);
        mainGrid.setHgap(70);
        mainGrid.setVgap(14);

        addRatings(ScoreboardMenuController.getInstance().createUserView());

        StackPane centerPane = new StackPane();
        centerPane.getChildren().addAll(rectangle, bgGrid, mainGrid);

        pane.setCenter(centerPane);

        pane.setBottom(createSwitchSceneButton("back", ProfileMenu.getInstance().getPane().getScene()));
    }

    public void addRatings(ArrayList<UserView> users) {
        int i = 1;
        for (UserView user : users) {
            ImageView avatar = user.getAvatar();
            avatar.setPreserveRatio(true);
            avatar.setFitHeight(36);
            mainGrid.add(avatar, 1, i);
            mainGrid.add(createLabel(user.getNickname()), 2, i);
            mainGrid.add(createLabel(Integer.toString(user.getScore())), 3, i);
            mainGrid.add(createLabel(user.getLastWinTime().format(DateTimeFormatter.ofPattern("MMM d uuuu, HH:mm:ss"))),
                    4, i);
            Label lastLoginTime = createLabel("Last Login: "
                    + user.getLastLoginTime().format(DateTimeFormatter.ofPattern("MMM d uuuu, HH:mm:ss")));
            lastLoginTime.setTextFill(Color.web(user.isCurrentUser() ? "#404040" : "#587189"));
            mainGrid.add(lastLoginTime, 5, i);

            Rectangle rectangle = new Rectangle(800, 40);
            rectangle.setArcWidth(20);
            rectangle.setArcHeight(10);
            rectangle.setFill(Color.web((user.isCurrentUser() ? "#4C779B" : "#023D6F"), 1));

            bgGrid.add(rectangle, 2, i++);
        }
    }
}
