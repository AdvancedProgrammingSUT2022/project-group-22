module Civilization {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;

    exports civilization;
    opens civilization to javafx.fxml;
    exports civilization.views;
    opens civilization.views to javafx.fxml;
}