package civilization.views;

import civilization.App;
import civilization.enums.Images;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;

public class Menu {
    public static BackgroundImage backgroundImage;

    public Menu(){
        backgroundImage = new BackgroundImage(new Image(App.class.getResource
                (Images.BACKGROUND.getUrl()).toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    }

    public static void showPopUp(String error){
        Label label = new Label(error);
        label.setStyle(" -fx-background-color: white;");
        Popup popup = new Popup();
        popup.getContent().add(label);
//      label.setMinWidth(80);
//      label.setMinHeight(50);
        popup.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
        popup.setAutoHide(true);
        popup.show(App.getMainStage());
    }
}
