package com.example.client.Dashboard.MyDivarPages;

import com.example.client.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.json.JSONObject;

public class MyAdsController {
    @FXML
    private Label testLabel;

    @FXML
    private ScrollPane scroll;

    @FXML
    private AnchorPane mainAnchorPane;s

    int counter = 0;

    private int numberOfPost;

    @FXML
    public void initialize() {

        JSONObject post1 = new JSONObject();
        post1.put("title", "salam");
        post1.put("price", "14000");
        post1.put("time", "5");

        JSONObject post2 = new JSONObject();
        post2.put("title", "khobi");
        post2.put("price", "1400");
        post2.put("time", "6");
        //Image img = new Image(getClass().getResourceAsStream("1.jpg"));
        HBox rowHbox1 = makeNewHBox(post1, post2);
        mainAnchorPane.getChildren().add(rowHbox1);
    }

    private HBox makeNewHbox() {
        HBox rowHBox = new HBox();
        //VBox vBox1 = makeNewVBox(URL1,post1.getString("title"),post1.getString("price"),post1.getString("time"));
        VBox vBox1 = new VBox();
        vBox1.setPrefWidth(200);
        vBox1.setPrefHeight(200);
        vBox1.setStyle("-fx-background-color: #e74c3c;");
        rowHBox.getChildren().add(vBox1);
        return rowHBox;
    }

    private HBox makeNewHBox(JSONObject post1, JSONObject post2) {
        HBox rowHBox = new HBox();
        VBox vBox1 = makeNewVBox(post1.getString("title"),post1.getString("price"),post1.getString("time"));
        VBox vBox2 = makeNewVBox(post2.getString("title"),post2.getString("price"),post2.getString("time"));
        vBox1.setPrefWidth(200);
        vBox1.setPrefHeight(200);
        vBox2.setPrefWidth(200);
        vBox2.setPrefHeight(200);
        rowHBox.getChildren().setAll(vBox1,vBox2);
        return rowHBox;
    }

    private VBox makeNewVBox(String title,String price,String hour){
        VBox vBox = new VBox();
        //ImageView imageView = new ImageView();
        //imageView.setImage(img);
        Label titleLabel = new Label(title);
        Label priceLabel = new Label(price);
        Label hourLabel = new Label(hour);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().setAll(titleLabel,priceLabel,hourLabel);
        return vBox;
    }
}
