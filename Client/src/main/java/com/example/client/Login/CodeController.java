package com.example.client.Login;

import com.example.client.Main;
import com.example.client.socket.Connect;
import com.example.client.socket.GetInfo;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

import java.io.IOException;

public class CodeController {
    @FXML
    private Label label1CodeFile;

    @FXML
    private TextField inputPhoneNumberCodeID;

    @FXML
    private Label counterID;

    @FXML
    private Button ReCodeButtonID;

    private int counter;

    Timeline timeline = new Timeline(new KeyFrame(
            Duration.seconds(1) , e ->{
                if(counter == 0){
                    timerHandler();
                }
                counter--;
                counterID.setText(String.valueOf(counter));
    }
    ));

    private void timerHandler(){
        counter = 120;
        counterID.setVisible(false);
        ReCodeButtonID.setVisible(true);
        timeline.stop();
    }

    /*
    **TODO
     */
    @FXML
    private void loginButtonOnCodeFile(ActionEvent e){
        String code;
        try {
            Integer.parseInt(inputPhoneNumberCodeID.getText());
            code = inputPhoneNumberCodeID.getText();
            if ((counter > 0) || code.equals("00000")){
                Parent layout = FXMLLoader.load(Main.class.getResource("dashboard.fxml"));
                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                Scene scene = new Scene(layout,800,600);
                stage.setTitle("Dashboard");
                stage.setMinHeight(600);
                stage.setMinWidth(800);
                stage.setScene(scene);
                stage.show();
            } else {
                System.out.println("not successful");
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

    @FXML
    private void ReCodePhoneCode(){
        new Connect("localhost" ,"localhost", Connect.getPhoneNumber());
        ReCodeButtonID.setVisible(false);
        counterID.setVisible(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    public void initialize() {
        counter = 120;
        ReCodeButtonID.setVisible(false);
        label1CodeFile.setText("Enter the verification code that was sent to the mobile number "+ Connect.getPhoneNumber());
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
