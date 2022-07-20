package civilization.views;

import java.util.ArrayList;

import civilization.App;
import civilization.controllers.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.*;
import javafx.scene.layout.*;

public class Game extends Menu {
    private static Game instance;
    private ScrollPane pane;
    private AnchorPane tiles;

    private static double TILE_HEIGHT = 196;
    private static double TILE_WIDTH = 224;
    private static double OFFSET = 56;
    private static double FEATURE_WIDTH = 160;
    private static double RESOURCE_WIDTH = 130;

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
        tiles.setStyle("-fx-background-color: #0B589E;");
        Scene scene = new Scene(pane, 1280, 800);
        createMap(MapController.getInstance().getMap());
    }

    public void createMap(TileView[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                StackPane tile = new StackPane();
                createTile(map[i][j], tile);
                AnchorPane.setTopAnchor(tile, j * TILE_HEIGHT + i % 2 * TILE_HEIGHT / 2);
                AnchorPane.setLeftAnchor(tile, i * (TILE_WIDTH - OFFSET));
                tiles.getChildren().add(tile);
            }
        }
    }

    public void createTile(TileView tileView, StackPane tile) {
        ImageView terrain = new ImageView(
                new Image(App.class.getResource(tileView.getTileImage()).toExternalForm()));
        terrain.setFitWidth(TILE_WIDTH);
        terrain.setPreserveRatio(true);
        tile.getChildren().add(terrain);

        if (tileView.getFeatureImage() != null) {
            ImageView feature = new ImageView(
                    new Image(App.class.getResource(tileView.getFeatureImage()).toExternalForm()));
            feature.setFitWidth(FEATURE_WIDTH);
            feature.setPreserveRatio(true);
            StackPane.setAlignment(feature, Pos.TOP_RIGHT);
            StackPane.setMargin(feature, new Insets(3, 3, 0, 0));
            tile.getChildren().add(feature);
        }

        if (tileView.getResourceImage() != null) {
            ImageView resource = new ImageView(
                    new Image(App.class.getResource(tileView.getResourceImage()).toExternalForm()));
            resource.setFitWidth(RESOURCE_WIDTH);
            resource.setPreserveRatio(true);
            StackPane.setAlignment(resource, Pos.BOTTOM_LEFT);
            StackPane.setMargin(resource, new Insets(0, 0, 3, 3));
            tile.getChildren().add(resource);
        }
    }
}
