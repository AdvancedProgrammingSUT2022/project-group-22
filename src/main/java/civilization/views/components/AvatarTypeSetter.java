package civilization.views.components;

import civilization.App;
import civilization.enums.Avatar;
import civilization.enums.Graphic;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class AvatarTypeSetter extends VBox {
    private ImageView circleImage;
    private ImageView avatarImage;

    private Avatar avatar;

    private boolean isChoosen;

    public AvatarTypeSetter(Avatar avatar) {
        circleImage = new ImageView(new Image(App.class.getResource(Graphic.CROSS.getUrl()).toExternalForm()));
        avatarImage = new ImageView(new Image(App.class.getResource(avatar.getUrl()).toExternalForm()));
        this.avatar = avatar;
        isChoosen = false;
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        this.maxWidth(350);
        this.getChildren().add(avatarImage);
        this.getChildren().add(circleImage);
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public boolean getIsChoosen() {
        return isChoosen;
    }

    public void setIsChoosen(boolean isCircleChoosen) {
        this.isChoosen = isCircleChoosen;
        String imageToSet = this.isChoosen ? Graphic.CHECK.getUrl() : Graphic.CROSS.getUrl();
        circleImage.setImage(new Image(getClass().getResource(imageToSet).toExternalForm()));
    }
}