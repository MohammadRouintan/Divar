package com.example.client.Login;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Button loginButtonID;

    private String phoneNumber = "0000000";

    @FXML
    private void loginOnFirstPage(ActionEvent e){
        try {
            Parent layout = FXMLLoader.load(Main.class.getResource("logIn.fxml"));
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(layout,800,600);
            stage.setTitle("Dashboard");
            stage.setMinHeight(600);
            stage.setMinWidth(800);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private TextField inputPhoneNumberIDField;

    @FXML
    /**
     * TODO
     */
    private void getInputNumberField(){
        phoneNumber = inputPhoneNumberCodeID.getText();
        System.out.println(phoneNumber);
    }

    @FXML
    private Button sendButtonPhoneID;

    @FXML
    private void sendButtonPhoneNumber(){}

    @FXML
    private Label label1CodeFile = null;

    @FXML
    public void initialize(){
//        label1CodeFile.setText("لطفا کد تایید را به شماره ی " + phoneNumber + "ارسال شده را وارد کنید .");
    }

    @FXML
    private TextField inputPhoneNumberCodeID;

    @FXML
    private void ReCodePhoneCode(){}

    @FXML
    private void loginButtonOnCodeFile(){}
}