package client.views;
import client.App;
import client.enums.Graphic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;

public class Menu {
    public static final BackgroundImage backgroundImage = new BackgroundImage(
            new Image(App.class.getResource(Graphic.BACKGROUND.getUrl()).toExternalForm()),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER, BackgroundSize.DEFAULT);;

    // fonts
    public static final Font titleFont = Font.loadFont(
            App.class.getResource("/client/font/CivFont.ttf").toExternalForm(),
            50);

    public static final Font textFont = Font.loadFont(
            App.class.getResource("/client/font/CivFont.ttf").toExternalForm(),
            12);

    public static void showPopUp(String error) {
        Label label = new Label(error);
        label.setStyle(" -fx-background-color: white;");
        Popup popup = new Popup();
        popup.getContent().add(label);
        // label.setMinWidth(80);
        // label.setMinHeight(50);
        popup.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
        popup.setAutoHide(true);
        popup.setAnchorX(690);
        popup.setAnchorY(100);
        popup.show(App.getMainStage());
    }

    public static Text createText(String text) {
        Text title = new Text(text.replaceAll(".(?!$)", "$0 "));
        title.setFont(titleFont);
        title.setFill(Color.WHITE);
        title.setStyle("-fx-font-weight: bold;");
        return title;
    }

    public static Label createLabel(String text) {
        Label label = new Label(text);
        label.setFont(textFont);
        label.setTextFill(Color.WHITE);
        return label;
    }

    public static Button createSwitchSceneButton(String text, Scene scene) {
        Button button = new Button(text);
        button.setFont(textFont);
        button.setStyle("-fx-background-color: none;");
        button.setTextFill(Color.WHITE);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                App.setScene(scene);
            }
        });
        return button;
    }
}
