module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example.client to javafx.fxml;
    exports com.example.client;
    exports com.example.client.socket;
    opens com.example.client.socket to javafx.fxml;
    exports com.example.client.Login;
    opens com.example.client.Login to javafx.fxml;
}