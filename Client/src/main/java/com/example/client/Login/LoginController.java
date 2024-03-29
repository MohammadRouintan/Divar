package com.example.client.Login;

import com.example.client.Main;
import com.example.client.socket.Connect;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController {

    @FXML
    private Button loginButtonID;

    private String phoneNumber;


    @FXML
    private void loginOnFirstPage(ActionEvent e){
        try {
            CodeController.phoneNumber = phoneNumber;
            Parent layout = FXMLLoader.load(Main.class.getResource("Login/logIn.fxml"));
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
    private Label test;

    @FXML
    /**
     * TODO
     */
    private void getInputNumberField(){

    }

    @FXML
    private Button sendButtonPhoneID;


    private boolean flag = false;

    @FXML
    private void sendButtonPhoneNumber(ActionEvent e){

        phoneNumber = "";

        Pattern pattern = Pattern.compile("09[0-9]{9}");
        Matcher matcher = pattern.matcher(inputPhoneNumberIDField.getText());

        if(matcher.matches()){

            phoneNumber = inputPhoneNumberIDField.getText();
            CodeController.phoneNumber = phoneNumber;
            new Connect("localhost" ,"localhost", phoneNumber);
            try{

                Parent layout = FXMLLoader.load(Main.class.getResource("Login/Code.fxml"));
                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                Scene scene = new Scene(layout,800,600);
                stage.setTitle("Dashboard");
                stage.setMinHeight(600);
                stage.setMinWidth(800);
                stage.setScene(scene);
                stage.show();
                flag = true;
            }catch (IOException ex){
                ex.printStackTrace();
            }

        }

    }

    @FXML
    private void testfunction(){
    }

}