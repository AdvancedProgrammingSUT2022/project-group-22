package civilization.views;

import java.util.ArrayList;

import civilization.App;
import civilization.controllers.*;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.*;
import javafx.scene.layout.*;

public class Game extends Menu {
    private static Game instance;
    private ScrollPane pane;
    private AnchorPane tiles;

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
        pane.setContent(tiles = new AnchorPane());
        Scene scene = new Scene(pane, 1280, 800);
        createMap(MapController.getInstance().getMap());
    }

    public void createMap(TileView[][] map) {
        double tileHeight = 196;
        double tileWidth = 224;
        double offset = 56;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                ImageView tile = new ImageView(
                        new Image(App.class.getResource(map[i][j].getTileImage()).toExternalForm()));
                tile.setFitWidth(tileWidth);
                tile.setPreserveRatio(true);
                AnchorPane.setTopAnchor(tile, j * tileHeight + i % 2 * tileHeight / 2);
                AnchorPane.setLeftAnchor(tile, i * (tileWidth - offset));
                tiles.getChildren().add(tile);
            }
        }
    }
}
