package resources;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Layout {
    public GridPane addGrid() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100,
                Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200,
                Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(columnOneConstraints,
                columnTwoConstrains);
        return gridPane;
    }

    public void buttonLayout(Button button) {
        button.setPrefHeight(40);
        button.setDefaultButton(true);
        button.setPrefWidth(100);
        GridPane.setHalignment(button, HPos.CENTER);
    }
}
