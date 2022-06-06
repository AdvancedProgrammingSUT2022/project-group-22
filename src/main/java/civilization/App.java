package civilization;

import civilization.controllers.RegisterMenuController;
import civilization.views.*;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class App extends Application {
    private static Scene scene;
    private static Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {
        RegisterMenuController.loadUsers();
        // Pane pane = FXMLLoader.load(App.class.getResource("fxml/FirstPage.fxml"));
        mainStage = new Stage();
        mainStage.setTitle("Civilization");
        mainStage.setScene(FirstPage.getInstance().getFirstPane().getScene());
        stage = mainStage;
        stage.show();
    }

    public static void setRoot(String fxml) throws Exception {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void setMainStage(Stage mainStage) {
        // Game.getInstance().createGame(App.mainStage);
    }

    public static void setScene(Scene scene) {
        App.scene = scene;
        mainStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }

}