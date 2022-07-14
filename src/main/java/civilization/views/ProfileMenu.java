package civilization.views;

import civilization.App;
import civilization.models.Database;
import civilization.views.components.GameButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ProfileMenu extends Menu {
    private static ProfileMenu instance = null;
    private static Scene scene;
    private BorderPane pane;

    private ProfileMenu() {
        pane = new BorderPane();
        scene = new Scene(pane, 1280, 800);
        pane.setBackground(new Background(backgroundImage));
        addElements();
    }

    public static ProfileMenu getInstance() {
        return instance == null ? new ProfileMenu() : instance;
    }

    public BorderPane getPane() {
        return pane;
    }

    private void addElements() {
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.maxWidth(350);

        vBox.getChildren().add(createText("PROFILE MENU"));
        vBox.getChildren().add(Database.getInstance().getLoggedInUser().getAvatar());
        // vBox.getChildren().add(createText(Database.getInstance().getLoggedInUser().getNickname()));
        // vBox.getChildren().add(createText(Database.getInstance().getLoggedInUser().getUsername()));
        // vBox.getChildren().add(createText(Database.getInstance().getLoggedInUser().getPassword()));
        vBox.getChildren().add(createAvatarButton());
        vBox.getChildren().add(createPasswordButton());
        vBox.getChildren().add(createNicknameButton());
        vBox.getChildren()
                .add((createSwitchSceneButton("return to main", MainMenu.getInstance().getPane().getScene())));

        pane.setCenter(vBox);
    }

    private GameButton createAvatarButton() {
        GameButton avatarButton = new GameButton("change avatar");
        avatarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                App.setScene(AvatarChooserPage.getInstance().getPane().getScene());
                return;
            }
        });
        return avatarButton;
    }

    private GameButton createPasswordButton() {
        GameButton passwordButton = new GameButton("change password");
        passwordButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PasswordChangingPage page = new PasswordChangingPage();
                App.setScene(page.getPane().getScene());
                return;
            }
        });
        return passwordButton;
    }

    private GameButton createNicknameButton() {
        GameButton nicknameButton = new GameButton("change nickname");
        nicknameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NicknameChangingPage page = new NicknameChangingPage();
                App.setScene(page.getPane().getScene());
                return;
            }
        });
        return nicknameButton;
    }
}
