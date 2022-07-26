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
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

public class Map extends Menu {
    private static Map instance = null;
    private Scene scene;
    private StackPane stackPane;
    private ScrollPane scrollPane;
    private AnchorPane tiles;
    Text info;

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
        scene = new Scene(stackPane, 1280, 800);
        addElements();
    }

    public void addElements() {
        createMap(MapController.getInstance().getMap());
        addInfoBox();
        setInfoText(MapController.getInstance().getMap()[1][1], null);
        addBackButton();
        addCheatMenu();
    }

    public void addInfoBox() {
        Rectangle infoBox = new Rectangle(250, 350);
        infoBox.setArcWidth(30);
        infoBox.setArcHeight(20);
        infoBox.setFill(Color.web("#00224A", 0.8));
        StackPane.setAlignment(infoBox, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(infoBox, new Insets(0, 20, 20, 0));
        stackPane.getChildren().add(infoBox);
    }

    public void setInfoText(TileView tile, UnitView unit) {
        stackPane.getChildren().remove(info);

        if (tile != null) {
            info = new Text("Coordinates: [" + tile.getCoordinates()[0] + ", " + tile.getCoordinates()[1] + "]"
                    + "\nLand Type: " + tile.getLandType() + "\nFeature: " + tile.getFeature() + "\nResource: "
                    + tile.getResource() + "\nFood: " + tile.getFood() + "\nProduction: " + tile.getProduction()
                    + "\nGold: " + tile.getGold());
        } else {
            info = new Text();
        }
        info.setFont(textFont);
        info.setFill(Color.WHITE);
        info.setStyle("-fx-font-size: 15;");
        info.setLineSpacing(8);
        info.setTextAlignment(TextAlignment.CENTER);

        StackPane.setAlignment(info, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(info, new Insets(0, 60, 130, 0));
        stackPane.getChildren().add(info);
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
                createTile(map[i][j], MapController.getTileUnit(i, j), tile);
                AnchorPane.setTopAnchor(tile, i * TILE_HEIGHT + (j % 2) * TILE_HEIGHT / 2);
                AnchorPane.setLeftAnchor(tile, j * (TILE_WIDTH - OFFSET));
                tiles.getChildren().add(tile);
            }
        }

        addFogOfWar(map);
    }

    public void createTile(TileView tileView, UnitView[] unitViews, StackPane tile) {
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

        if (unitViews[0] != null) {
        }

        if (unitViews[1] != null) {
        }
    }

    public void addFogOfWar(TileView[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j].getFogOfWar()) {
                    ImageView clouds = new ImageView(
                            new Image(App.class.getResource("/civilization/png/map/cloud.png").toExternalForm()));
                    clouds.setFitWidth(TILE_WIDTH);
                    clouds.setPreserveRatio(true);
                    AnchorPane.setTopAnchor(clouds, i * TILE_HEIGHT + j % 2 * TILE_HEIGHT / 2);
                    AnchorPane.setLeftAnchor(clouds, j * (TILE_WIDTH - OFFSET));
                    tiles.getChildren().add(clouds);
                } else {
                    System.out.println(" > " + map[i][j].getCoordinates()[0] + ":" + map[i][j].getCoordinates()[1]);
                }
            }
        }
    }

    public void tileUI(ImageView terrain, TileView tileView) {
        terrain.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (true || !tileView.getFogOfWar()) {
                    setInfoText(tileView, null);
                }
            }
        });
    }

    public void addCheatMenu() {
        KeyCodeCombination cheatMenu = new KeyCodeCombination(KeyCode.W);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("Hi:" + scene);
                if (cheatMenu.match(event)) {
                    System.out.println("Here");
                    TextInputDialog dialog = new TextInputDialog("Enter your cheat code");
                    dialog.setTitle("Cheat Sheet");
                    dialog.setContentText("Cheat Code:");
                    dialog.setHeaderText(null);
                    dialog.setGraphic(null);
                    dialog.show();
                }
            }
        });
    }
}
