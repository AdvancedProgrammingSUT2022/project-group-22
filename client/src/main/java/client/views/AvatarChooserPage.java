package client.views;

import client.App;
import client.controller.NetworkController;
import client.enums.Avatar;
import client.model.Request;
import client.model.User;
import client.views.components.AvatarTypeSetter;
import client.views.components.GameButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AvatarChooserPage extends Menu{
    private static AvatarChooserPage instance = null;
    private static Scene scene;
    private BorderPane avatarPane;
    private Avatar choosenAvatar;


    private AvatarChooserPage(){
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


    private void addElements(){
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.maxWidth(350);

        vBox.getChildren().add(createText("choose your avatar"));

        vBox.getChildren().add(createAvatarsToChoose());
        vBox.getChildren().add(createRandomButton());
        vBox.getChildren().add(createUploadButton());
        vBox.getChildren().add(createApplyButton());
        vBox.getChildren().add(createSwitchSceneButton("cancel",ProfileMenu.getInstance().getPane().getScene()));

        avatarPane.setCenter(vBox);
    }


    private HBox createAvatarsToChoose() {
        HBox hBox = new HBox();
        hBox.setSpacing(60);
        hBox.setAlignment(Pos.CENTER);
        List<AvatarTypeSetter> avatarList = new ArrayList<>();
        for (Avatar avatar : Avatar.values()) {
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
                    //ImageView temp = new ImageView(new Image(App.class.getResource(choosenAvatar.getUrl()).toExternalForm()));
                    //ProfileMenuController.getInstance().setAvatar(temp);
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
                //ImageView temp = new ImageView(new Image(App.class.getResource(Avatar.values()[i].getUrl()).toExternalForm()));
                //todo set avatar at server
                setAvatarRequest(Avatar.values()[i].getUrl());
                //ProfileMenuController.getInstance().setAvatar(temp);
                App.setScene(ProfileMenu.getInstance().getPane().getScene());
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
                    Image image = new Image(selectedFile.toURI().toString());
                    //todo set avatar at server
                    //ProfileMenuController.getInstance().setAvatar(new ImageView(image));
                    App.setScene(ProfileMenu.getInstance().getPane().getScene());
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
                setAvatarRequest(choosenAvatar.getUrl());
                App.setScene(ProfileMenu.getInstance().getPane().getScene());
            }
        });
        return applyButton;
    }

    private void setAvatarRequest(String avatar){
        Request request = new Request();
        request.setHeader("setAvatar");
        request.addData("jwt", User.getJwt());
        request.addData("avatar",avatar);
        User.setJwt((String) NetworkController.send(request).getData().get("jwt"));
    }
}
