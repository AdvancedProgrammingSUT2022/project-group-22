module Main {
    requires javafx.controls;
    requires javafx.fxml;

    exports Main;
    opens Main to javafx.fxml;
}