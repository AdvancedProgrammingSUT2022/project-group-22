package civilization.views;

import civilization.App;
import civilization.enums.Graphic;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;

public class Menu {
    public static BackgroundImage backgroundImage;

    // fonts
    public static final Font titleFont = Font.loadFont(
            App.class.getResource("/civilization/font/CivFont.ttf").toExternalForm(),
            50);
    public static final Font buttonFont = Font.loadFont(
            App.class.getResource("/civilization/font/CivFont.ttf").toExternalForm(),
            17);
    public static final Font textFont = Font.loadFont(
            App.class.getResource("/civilization/font/CivFont.ttf").toExternalForm(),
            12);

    public Menu() {
        backgroundImage = new BackgroundImage(
                new Image(App.class.getResource(Graphic.BACKGROUND.getUrl()).toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
    }

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
}
