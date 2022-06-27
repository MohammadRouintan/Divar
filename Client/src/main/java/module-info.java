module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.desktop;


    opens com.example.client to javafx.fxml;
    exports com.example.client;
    exports com.example.client.socket;
    opens com.example.client.socket to javafx.fxml;
    exports com.example.client.Login;
    opens com.example.client.Login to javafx.fxml;
    exports com.example.client.Dashboard;
    opens com.example.client.Dashboard to javafx.fxml;
    exports com.example.client.Dashboard.MyDivarPages;
    opens com.example.client.Dashboard.MyDivarPages to javafx.fxml;
    exports com.example.client.Dashboard.Explore;
    opens com.example.client.Dashboard.Explore to javafx.fxml;
}