package civilization.views;

import java.util.ArrayList;

import civilization.App;
import civilization.controllers.*;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;

public class Game extends Menu {
    private static Game instance;
    private ScrollPane pane;
    private GridPane tiles;

    public static Game getInstance() {
        return instance != null ? instance : new Game();
    }

    public ScrollPane getPane() {
        return this.pane;
    }

    private Game() {
        // test
        GameMenuController.getInstance()
                .startGame(
                        new ArrayList<UserView>(ScoreboardMenuController.getInstance().createUserView().subList(0, 2)));

        pane = new ScrollPane();
        pane.setContent(tiles = new GridPane());
        Scene scene = new Scene(pane, 1280, 800);
        createMap(MapController.getInstance().getMap());
    }

    public void createMap(TileView[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                ImageView tile = new ImageView(
                        new Image(App.class.getResource(map[i][j].getTileImage()).toExternalForm()));
                tiles.add(tile, j, i);
            }
        }
    }
}
