module server {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires jjwt.api;
    requires java.xml.bind;


    opens server to javafx.fxml, com.google.gson,java.base,java.time;
    opens server.models to com.google.gson, jjwt.api, java.xml.bind;
    opens server.controllers to com.google.gson;
    opens server.enums to com.google.gson;
    exports server.enums;
    exports server.models;
    exports server;
}