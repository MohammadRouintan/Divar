module com.example.admin {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example.admin to javafx.fxml;
    exports com.example.admin;
    opens com.example.admin.login to javafx.fxml;
    exports com.example.admin.login;
    opens com.example.admin.Dashboard to javafx.fxml;
    exports com.example.admin.Dashboard;
}