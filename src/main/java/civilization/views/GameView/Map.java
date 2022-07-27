package civilization.views.GameView;

import civilization.App;
import civilization.controllers.*;
import civilization.enums.*;
import civilization.views.GameMenuView.GameMenuPage;
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
    private static double BUILDING_WIDTH = 220;
    private static double UNIT_WIDTH = 110;

    public static Map getInstance() {
        return instance != null ? instance : (instance = new Map());
    }

    public StackPane getPane() {
        return this.stackPane;
    }

    private Map() {
        GameMenuController.getInstance().startGame("player1", "player2", 12, 10);

        stackPane = new StackPane();
        scrollPane = new ScrollPane();
        stackPane.getChildren().add(scrollPane);
        scene = new Scene(stackPane, 1280, 800);
        addElements();
    }

    public void addElements() {
        createMap(MapController.getInstance().getMap());
        addInfoBox();
        setInfoText(null, MapController.getSettler());
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
            info = new Text("Position: [" + unit.getPosition()[0] + ", " + unit.getPosition()[1] + "]"
                    + "\nUnit Type: " + unit.getUnitType() + "\nMovement Points: " + unit.getMovementPoints());
        }
        info.setFont(textFont);
        info.setFill(Color.WHITE);
        info.setStyle("-fx-font-size: 15;");
        info.setLineSpacing(8);
        info.setTextAlignment(TextAlignment.CENTER);

        StackPane.setAlignment(info, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(info, new Insets(0, 60, 160, 0));
        stackPane.getChildren().add(info);
    }

    public void addBackButton() {
        ImageView button = new ImageView(
                new Image(App.class.getResource(Graphic.GAMEBUTTON.getUrl()).toExternalForm()));
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
        scrollPane.setContent(tiles = new AnchorPane());
        tiles.setStyle("-fx-background-color: #0B589E;");

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

    public ImageView addImage(StackPane tile, String image, Double width, Pos position, Insets margins) {
        ImageView img = new ImageView(
                new Image(App.class.getResource(image).toExternalForm()));
        img.setFitWidth(width);
        img.setPreserveRatio(true);
        StackPane.setAlignment(img, position);
        StackPane.setMargin(img, margins);
        tile.getChildren().add(img);

        return img;
    }

    public void createTile(TileView tileView, UnitView[] unitViews, StackPane tile) {
        tileUI(addImage(tile, tileView.getTileImage(), TILE_WIDTH, Pos.CENTER, new Insets(0, 0, 0, 0)), tileView);

        if (unitViews[1] != null) {
            unitUI(addImage(tile, unitViews[1].getUnitImage(), UNIT_WIDTH,
                    tileView.getBuildingImage() == null ? Pos.TOP_LEFT : Pos.BOTTOM_LEFT,
                    new Insets(0, 100, 0, 0)), unitViews[1]);
        }

        if (tileView.getFeatureImage() != null && tileView.getBuildingImage() == null) {
            addImage(tile, tileView.getFeatureImage(), FEATURE_WIDTH, Pos.TOP_RIGHT, new Insets(8, 8, 0, 0));
        }

        if (tileView.getBuildingImage() != null) {
            addImage(tile, tileView.getBuildingImage(), BUILDING_WIDTH, Pos.TOP_CENTER, new Insets(0, 0, 0, 0));
        }

        if (unitViews[0] != null) {
            unitUI(addImage(tile, unitViews[0].getUnitImage(), UNIT_WIDTH, Pos.BOTTOM_RIGHT, new Insets(0, 50, 0, 0)),
                    unitViews[0]);
        }

        if (tileView.getResourceImage() != null && tileView.getBuildingImage() == null) {
            addImage(tile, tileView.getResourceImage(), RESOURCE_WIDTH, Pos.BOTTOM_LEFT, new Insets(0, 0, 5, 5));
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
                }
            }
        }
    }

    public void tileUI(ImageView terrain, TileView tileView) {
        terrain.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!tileView.getFogOfWar()) {
                    setInfoText(tileView, null);
                    MapController.getInstance().setSelectedTile(tileView);
                }
            }
        });
    }

    public void unitUI(ImageView unit, UnitView unitView) {
        unit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (MapController.canAccessUnit(unitView)) {
                    setInfoText(null, unitView);
                    MapController.getInstance().setSelectedUnit(unitView);
                }
            }
        });
    }

    public void addCheatMenu() {
        KeyCodeCombination cheatMenu = new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_ANY);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (cheatMenu.match(event)) {
                    TextInputDialog dialog = new TextInputDialog("Enter your cheat code");
                    dialog.setTitle("Cheat Sheet");
                    dialog.setContentText("Cheat Code:");
                    dialog.setHeaderText(null);
                    dialog.setGraphic(null);
                    dialog.showAndWait();
                    MapController.getInstance().cheat(dialog.getEditor().getText());
                }
            }
        });
    }
}
