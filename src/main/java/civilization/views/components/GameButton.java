package civilization.views.components;

import civilization.App;
import civilization.enums.Graphic;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class GameButton extends Button {
    private Background pressedBackground;
    private Background freeBackground;
    public static final String FONTADDRESS = "/civilization/font/CivFont.ttf";

    public GameButton(String text) {
        setText(text);
        setPrefWidth(190);
        setPrefHeight(49);
        createBackgrounds();
        this.setBackground(freeBackground);
        this.setTextFill(Color.WHITE);
        createFont();
        initializeButtonListeners();
    }

    private void createBackgrounds() {
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(App.class.getResource(Graphic.BUTTON.getUrl()).toExternalForm()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        freeBackground = new Background(backgroundImage);
        BackgroundImage backgroundImage2 = new BackgroundImage(
                new Image(App.class.getResource(Graphic.PRESSED_BUTTON.getUrl()).toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pressedBackground = new Background(backgroundImage2);
    }

    private void createFont(){
        Font font = Font.loadFont (App.class.getResource(FONTADDRESS).toExternalForm(),17);
        this.setFont(font);
    }

    private void setButtonPressedStyle() {
        setBackground(pressedBackground);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);
    }

    private void setButtonReleasedStyle() {
        setBackground(freeBackground);
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
    }

    private void initializeButtonListeners() {

        setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressedStyle();
                }

            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleasedStyle();
                }

            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());

            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                setEffect(null);

            }
        });

    }
}
