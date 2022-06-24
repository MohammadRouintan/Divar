package com.example.admin.login;
import com.example.admin.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONObject;
import com.example.admin.socket.GetInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class loginController {

    @FXML
    private Label errorMassage;

    @FXML
    private PasswordField passwordTextFiled;

    @FXML
    private TextField usernameTextFiled;

    private Stage stage;
    private Scene scene;
    private Parent layout;

    @FXML
    void login(ActionEvent event) {

        if(usernameTextFiled.getText() != null || passwordTextFiled.getText() != null){
//            String admin = GetInfo.getAdmin();
//            JSONObject object = new JSONObject(admin);
//            String password = object.getString("password");
//            String username = object.getString("username");
            //if(password.equals(passwordTextFiled.getText()) && username.equals(usernameTextFiled.getText())){
                try {
                    GetInfo.Posts =  GetInfo.getPosts();
                    layout = FXMLLoader.load(Main.class.getResource("dashboard.fxml"));
                    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(layout);
                    stage.setTitle("Dashboard");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
           // }
        }else{
            errorMassage.setText("wrong password or username!");
        }

    }
}
