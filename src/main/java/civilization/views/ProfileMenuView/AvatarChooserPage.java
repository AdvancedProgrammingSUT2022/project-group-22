package civilization.views.ProfileMenuView;

import civilization.views.*;
import civilization.views.components.*;
import civilization.App;
import civilization.controllers.ProfileMenuController;
import civilization.enums.Avatar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AvatarChooserPage extends Menu {
    private static AvatarChooserPage instance = null;
    private static Scene scene;
    private BorderPane avatarPane;
    private Avatar choosenAvatar;

    private AvatarChooserPage() {
        avatarPane = new BorderPane();
        scene = new Scene(avatarPane, 1280, 800);
        avatarPane.setBackground(new Background(backgroundImage));
        addElements();
    }

    public static AvatarChooserPage getInstance() {
        return instance == null ? new AvatarChooserPage() : instance;
    }

    public BorderPane getPane() {
        return avatarPane;
    }

    private void addElements() {
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.maxWidth(350);

        vBox.getChildren().add(createText("choose your avatar"));

        vBox.getChildren().add(createAvatarsToChoose());
        vBox.getChildren().add(createRandomButton());
        vBox.getChildren().add(createUploadButton());
        vBox.getChildren().add(createApplyButton());
        vBox.getChildren().add(createSwitchSceneButton("cancel", ProfileMenuPage.getInstance().getPane().getScene()));

        avatarPane.setCenter(vBox);
    }

    private HBox createAvatarsToChoose() {
        HBox hBox = new HBox();
        hBox.setSpacing(60);
        hBox.setAlignment(Pos.CENTER);
        List<AvatarTypeSetter> avatarList = new ArrayList<>();
        for (Avatar avatar : Avatar.getAvatarSelection()) {
            AvatarTypeSetter avatarToPick = new AvatarTypeSetter(avatar);
            avatarList.add(avatarToPick);
            hBox.getChildren().add(avatarToPick);
            avatarToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    for (AvatarTypeSetter avatar : avatarList) {
                        avatar.setIsChoosen(false);
                    }
                    avatarToPick.setIsChoosen(true);
                    choosenAvatar = avatarToPick.getAvatar();
                    ProfileMenuController.getInstance().changeAvatar(choosenAvatar);
                }
            });
        }
        return hBox;
    }

    private GameButton createRandomButton() {
        GameButton randomButton = new GameButton("random avatar");
        randomButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Random random = new Random();
                int i = random.nextInt(Avatar.values().length);
                ProfileMenuController.getInstance().changeAvatar(Avatar.values()[i]);
                App.setScene(ProfileMenuPage.getInstance().getPane().getScene());
            }
        });
        return randomButton;
    }

    private GameButton createUploadButton() {
        GameButton uploadButton = new GameButton("upload avatar");
        uploadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("png Files", "*.png"));
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    // try {
                    // ProfileMenuController.getInstance().changeAvatar(selectedFile.toURI().toURL());
                    // } catch (MalformedURLException e) {
                    // e.printStackTrace();
                    // }
                    App.setScene(ProfileMenuPage.getInstance().getPane().getScene());
                }
            }
        });
        return uploadButton;
    }

    private GameButton createApplyButton() {
        GameButton applyButton = new GameButton("Apply");
        applyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                App.setScene(ProfileMenuPage.getInstance().getPane().getScene());
            }
        });
        return applyButton;
    }
}
