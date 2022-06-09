package com.example.client.Login;

import com.example.client.socket.Connect;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

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
    }


    @FXML
    private void loginButtonOnCodeFile(){
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
        label1CodeFile.setText(" لطفا کد تایید را به شماره ی  " + Connect.getPhoneNumber() + " ارسال شده را وارد کنید . ");
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
