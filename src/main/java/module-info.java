module Civilization {
    requires javafx.controls;
    requires javafx.fxml;

    exports civilization;
    opens civilization to javafx.fxml;
    exports civilization.views;
    opens civilization.views to javafx.fxml;
}