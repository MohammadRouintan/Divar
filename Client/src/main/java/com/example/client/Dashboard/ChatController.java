package com.example.client.Dashboard;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Field;

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
    private ScrollPane scrollPane;
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
        userHBox.setCursor(Cursor.HAND);

        Circle userImg = new Circle();
        userImg.setRadius(24);
        File file = new File("../Client/src/main/resources/postImage/1.jpg");
        Image img = new Image(file.toURI().toString());
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
        HBox hBox1 = makeMassageHbox(0,true,"111111111111111111111111111111111111111111111111");
        HBox hBox2 = makeMassageHbox(1,false,"222222222222222222222222222222222");
        chatBox.getChildren().addAll(hBox1,hBox2);
    }


    private HBox makeMassageHbox(int user,boolean seen,String text){
        HBox hBox = new HBox();
        hBox.setSpacing(10);


        Circle profileImg = new Circle();
        profileImg.setRadius(13);
        File file = new File("../Client/src/main/resources/postImage/2.jpg");
        Image img = new Image(file.toURI().toString());
        profileImg.setFill(new ImagePattern(img));

        VBox massageVbox = new VBox();
        massageVbox.setStyle("-fx-background-color:#fff;" +
                "-fx-background-radius:10;"
        );
        massageVbox.setPadding(new Insets(5));
        Text textMassage = new Text(text);
        textMassage.setWrappingWidth(280);


        ImageView tickImage = new ImageView();
        tickImage.setFitWidth(20);
        tickImage.setFitHeight(20);

        if(!seen){
            File file1 = new File("../Client/src/main/resources/Icon/tick.png");
            Image img1 = new Image(file1.toURI().toString());
            tickImage.setImage(img1);
        }
        else {
            File file2 = new File("../Client/src/main/resources/Icon/twotick.png");
            Image img2 = new Image(file2.toURI().toString());
            tickImage.setImage(img2);
        }

        massageVbox.getChildren().addAll(textMassage,tickImage);
        if(user == 0){
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setPadding(new Insets(0, 0, 0, 5));
            hBox.getChildren().addAll(profileImg, massageVbox);
            massageVbox.setAlignment(Pos.CENTER_LEFT);
        }

        else {
            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setPadding(new Insets(0, 5, 0, 0));
            hBox.getChildren().addAll(massageVbox, profileImg);
            massageVbox.setAlignment(Pos.CENTER_RIGHT);
        }

        hBox.setMargin(massageVbox,new Insets(10, 0, 10, 0));
        return hBox;
    }
}
