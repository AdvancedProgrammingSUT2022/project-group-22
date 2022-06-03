package views;

import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Window;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.*;

public class View {
    private static View instance = null;
    protected Stage stage = new Stage();
    protected Database database = Database.getInstance();

    public static View getInstance() {
        instance = instance == null ? new View() : instance;
        return instance;
    }

    public void showAlert(Alert.AlertType alertType, Window owner, String title,
            String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    protected void changeStage(GridPane gridPane, String title) {
        Scene scene = new Scene(gridPane, 800, 500);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
}
