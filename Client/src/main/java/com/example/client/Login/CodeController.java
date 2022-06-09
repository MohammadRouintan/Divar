package com.example.client.Login;

import com.example.client.socket.Connect;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class CodeController {
    @FXML
    private Label label1CodeFile;

    @FXML
    private TextField inputPhoneNumberCodeID;

    @FXML
    private Label counterID;

    @FXML
    private Button ReCodeButtonID;

    private int counter = 120;

    Timeline timeline = new Timeline(new KeyFrame(
            Duration.seconds(1) , e ->{
                counter--;
                counterID.setText(String.valueOf(counter));
    }
    ));

    Timer timer = new Timer();

    TimerTask timerTask = new TimerTask() {
        int counter = 120;
        @Override
        public void run() {
            if(counter > 0){
                counter--;
                counterID.setText(String.valueOf(counter));
            }else{
                timer.cancel();
            }
        }
    };

    @FXML
    private void loginButtonOnCodeFile(){

    }

    @FXML
    private void ReCodePhoneCode(){

    }

    @FXML
    public void initialize() {
        label1CodeFile.setText(" لطفا کد تایید را به شماره ی  " + Connect.getPhoneNumber() + " ارسال شده را وارد کنید . ");
//        timer.scheduleAtFixedRate(timerTask ,0 ,1200);
        timeline.setCycleCount(120);
        timeline.play();
    }
}
