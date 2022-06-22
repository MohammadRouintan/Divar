package com.example.client.Dashboard.MyDivarPages;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class MyAdsController {
    @FXML
    private Label testLabel;

    @FXML
    private ScrollPane scroll;

    int counter = 0;

    @FXML
    public void initialize() {
    }

    @FXML
    private AnchorPane test;

    @FXML
    public void testF(MouseEvent e){
        double x = e.getX();
        double y = e.getY();
        testLabel.setText(x+" , "+y);

        if(y > 300){
            test.setMinHeight(test.getHeight() + 300);
        }
    }

}
