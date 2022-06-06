package civilization.views;

import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;

public class ProfileMenu extends Menu{
    private static ProfileMenu instance = null;
    private static Scene scene;
    private BorderPane pane;


    private ProfileMenu(){
        pane = new BorderPane();
        scene = new Scene(pane, 1280, 800);
        pane.setBackground(new Background(backgroundImage));
        addElements();
    }

    public static ProfileMenu getInstance() {
        return instance == null ? new ProfileMenu() : instance;
    }

    private void addElements(){

    }
}
