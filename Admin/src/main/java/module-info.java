module com.example.admin {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.admin to javafx.fxml;
    exports com.example.admin;
}