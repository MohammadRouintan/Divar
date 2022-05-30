module com.example.server {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;

    opens com.example.server to javafx.fxml;
    exports com.example.server;
}