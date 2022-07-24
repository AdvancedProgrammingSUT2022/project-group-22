package client;

import client.controller.NetworkController;
import client.views.FirstPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static Scene scene;
    private static Stage mainStage;
    final int SERVER_PORT = 5000;

    @Override
    public void start(Stage stage) throws Exception {
        //RegisterMenuController.loadUsers();

        if (!NetworkController.connect(SERVER_PORT))
            return;

        mainStage = new Stage();
        mainStage.setTitle("Civilization");
        mainStage.setScene(FirstPage.getInstance().getPane().getScene());

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
