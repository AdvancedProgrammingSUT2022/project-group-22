package civilization.views.GameView;

import java.util.ArrayList;

import civilization.App;
import civilization.controllers.*;
import civilization.views.*;
import civilization.views.components.*;
import civilization.views.components.Menu;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Map extends Menu {
    private static Map instance = null;
    private StackPane stackPane;
    private ScrollPane scrollPane;
    private AnchorPane tiles;
    private StackPane infoBox;

    private static double TILE_HEIGHT = 196;
    private static double TILE_WIDTH = 224;
    private static double OFFSET = 56;
    private static double FEATURE_WIDTH = 160;
    private static double RESOURCE_WIDTH = 130;

    public static Map getInstance() {
        return instance != null ? instance : (instance = new Map());
    }

    public StackPane getPane() {
        return this.stackPane;
    }

    private Map() {
        // test
        GameMenuController.getInstance()
                .startGame(
                        new ArrayList<UserView>(ScoreboardMenuController.getInstance().createUserView().subList(0, 2)));

        stackPane = new StackPane();
        addElements();
        Scene scene = new Scene(stackPane, 1280, 800);
    }

    public void addElements() {
        createMap(MapController.getInstance().getMap());
        // addInfoBox();
        // setInfoText(MapController.getInstance().getMap()[1][1], null);
        addBackButton();
    }

    public void addInfoBox() {
        infoBox = new StackPane();

        Rectangle background = new Rectangle(300, 400);
        background.setArcWidth(30);
        background.setArcHeight(20);
        background.setFill(Color.web("#00224A", 0.8));
        infoBox.getChildren().add(background);

        StackPane.setMargin(infoBox, new Insets(0, 20, 20, 0));
        StackPane.setAlignment(infoBox, Pos.BOTTOM_RIGHT);
        stackPane.getChildren().add(infoBox);
    }

    public void setInfoText(TileView tile, UnitView unit) {
        if (unit == null) {
            createLabel("Food");
        } else {
        }
    }

    public void addBackButton() {
        ImageView button = new ImageView(
                new Image(App.class.getResource("/civilization/png/GameBackButton.png").toExternalForm()));
        button.setFitWidth(70);
        button.setPreserveRatio(true);
        StackPane.setAlignment(button, Pos.TOP_LEFT);
        StackPane.setMargin(button, new Insets(10, 0, 0, 10));
        stackPane.getChildren().add(button);

        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                App.setScene(GameMenuPage.getInstance().getPane().getScene());
            }
        });
    }

    public void createMap(TileView[][] map) {
        scrollPane = new ScrollPane();
        scrollPane.setContent(tiles = new AnchorPane());
        tiles.setStyle("-fx-background-color: #0B589E;");
        stackPane.getChildren().add(scrollPane);

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
        tileUI(terrain, tileView);
        tile.getChildren().add(terrain);

        if (tileView.getFeatureImage() != null) {
            ImageView feature = new ImageView(
                    new Image(App.class.getResource(tileView.getFeatureImage()).toExternalForm()));
            feature.setFitWidth(FEATURE_WIDTH);
            feature.setPreserveRatio(true);
            StackPane.setAlignment(feature, Pos.TOP_RIGHT);
            StackPane.setMargin(feature, new Insets(8, 8, 0, 0));
            tile.getChildren().add(feature);
        }

        if (tileView.getResourceImage() != null) {
            ImageView resource = new ImageView(
                    new Image(App.class.getResource(tileView.getResourceImage()).toExternalForm()));
            resource.setFitWidth(RESOURCE_WIDTH);
            resource.setPreserveRatio(true);
            StackPane.setAlignment(resource, Pos.BOTTOM_LEFT);
            StackPane.setMargin(resource, new Insets(0, 0, 5, 5));
            tile.getChildren().add(resource);
        }

        // if (tileView.getFogOfWar()) {
        // ImageView clouds = new ImageView(
        // new
        // Image(App.class.getResource("/civilization/png/map/cloud.png").toExternalForm()));
        // clouds.setFitWidth(TILE_WIDTH);
        // clouds.setPreserveRatio(true);
        // tile.getChildren().add(clouds);
        // }
    }

    public void tileUI(ImageView terrain, TileView tileView) {
        terrain.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!tileView.getFogOfWar()) {
                    setInfoText(tileView, null);
                }
            }
        });
    }
}
