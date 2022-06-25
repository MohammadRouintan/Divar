package com.example.client.Dashboard;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.json.JSONObject;

public class ChatController {

    @FXML
    private Circle circleImage;
    @FXML
    private VBox Users;
    @FXML
    private Circle imageProfile;
    @FXML
    private Label nameProfile;
    @FXML
    private VBox chatBox;
    @FXML
    private TextField textBox;
    @FXML
    private Button sendButton;

    @FXML
    public void initialize(){
        for (int i = 0; i < 10; i++) {
            HBox hBox = AddUsers();
            Users.getChildren().add(hBox);
        }
    }

    @FXML
    private void sendButton(){

    }

    private HBox AddUsers(){
        HBox userHBox = new HBox();
        userHBox.setAlignment(Pos.CENTER_LEFT);
        userHBox.setMinHeight(56);
        userHBox.setMinWidth(194);
        userHBox.setPrefHeight(56);
        userHBox.setPrefWidth(56);

        Circle userImg = new Circle();
        userImg.setRadius(24);
        Image img = new Image("/postImage/1.jpg");
        userImg.setFill(new ImagePattern(img));

        Label userName = new Label("Ali Mohammadi");
        userName.setAlignment(Pos.CENTER);
        userName.setPrefWidth(114);
        userName.setPrefHeight(52);

        Label counterOfMassages = new Label("23");
        counterOfMassages.setAlignment(Pos.CENTER);
        counterOfMassages.setPrefWidth(25);
        counterOfMassages.setPrefHeight(52);
        userHBox.getChildren().addAll(userImg,userName,counterOfMassages);
        userHBox.setOnMouseClicked(event -> ShowMassages());
        return userHBox;

    }

    private void ShowMassages(){
        Label label = new Label("helllllooo");
        chatBox.getChildren().add(label);
    }
}
