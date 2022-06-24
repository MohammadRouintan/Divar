package com.example.client.Dashboard;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class ChatController {

    @FXML
    private Circle circleImage;

    @FXML
    public void initialize(){
        Image img = new Image("/postImage/1.jpg");
        circleImage.setFill(new ImagePattern(img));
    }
}
