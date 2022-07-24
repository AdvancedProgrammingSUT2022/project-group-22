module client {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens client to javafx.fxml;
    opens client.views to javafx.fxml,com.google.gson;
    opens client.model to com.google.gson;
    exports client;
}
