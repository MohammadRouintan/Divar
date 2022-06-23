package com.example.client.Dashboard.MyDivarPages;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyAdsController {
    @FXML
    private Label testLabel;

    @FXML
    private ScrollPane scroll;

    @FXML
    private VBox mainVBox;
    @FXML
    private Pagination pagination;

    List<VBox> PagesList = new ArrayList<VBox>();

    String url = "/postImage/1.jpg";
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


        HBox rowHbox1 = makeNewHBox(url,post1,url, post2);
        HBox rowHbox2 = makeNewHBox(url,post1,url, post2);
        HBox rowHbox3 = makeNewHBox(url,post1,url, post2);
        HBox rowHbox4 = makeNewHBox(url,post1,url, post2);

        VBox vBox = makePage(rowHbox1,rowHbox2,rowHbox3,rowHbox4);
        PagesList.add(vBox);
        pagination.setPageFactory(this::CreatPage);


    }

    private Node CreatPage(int pageIndex){
        JSONObject post1 = new JSONObject();
        post1.put("title", "salam");
        post1.put("price", "14000");
        post1.put("time", "5");

        JSONObject post2 = new JSONObject();
        post2.put("title", "khobi");
        post2.put("price", "1400");
        post2.put("time", "6");



        HBox rowHbox1 = makeNewHBox(url,post1,url, post2);
        HBox rowHbox2 = makeNewHBox(url,post1,url, post2);
        HBox rowHbox3 = makeNewHBox(url,post1,url, post2);
        HBox rowHbox4 = makeNewHBox(url,post1,url, post2);
        VBox vBox = makePage(rowHbox1,rowHbox2,rowHbox3,rowHbox4);
        PagesList.add(vBox);
        return PagesList.get(pageIndex);
    }
    private VBox makePage(HBox hBox1,HBox hBox2,HBox hBox3,HBox hBox4){
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().addAll(hBox1,hBox2,hBox3,hBox4);
        return vBox;
    }
    private HBox makeNewHBox(String url,JSONObject post1) {
        HBox rowHBox = new HBox();
        //VBox vBox1 = makeNewVBox(URL1,post1.getString("title"),post1.getString("price"),post1.getString("time"));
        VBox vBox1 = new VBox();
        vBox1.setStyle("-fx-background-color: #e74c3c;");
        rowHBox.getChildren().add(vBox1);
        return rowHBox;
    }

    private HBox makeNewHBox(String url1,JSONObject post1,String url2, JSONObject post2) {
        HBox rowHBox = new HBox();
        VBox vBox1 = makeNewVBox(url1,post1);
        VBox vBox2 = makeNewVBox(url2,post2);
        rowHBox.getChildren().setAll(vBox1,vBox2);
        return rowHBox;
    }

    private VBox makeNewVBox(String url,JSONObject post){
        VBox vBox = new VBox();
        ImageView imageView = new ImageView();
        Image img = new Image(url);
        imageView.setFitHeight(200);
        imageView.setFitWidth(280);
        imageView.setImage(img);
        Label titleLabel = new Label(post.getString("title"));
        Label priceLabel = new Label(post.getString("price"));
        Label timeLabel = new Label(post.getString("time"));
        titleLabel.setAlignment(Pos.CENTER);
        priceLabel.setAlignment(Pos.CENTER);
        timeLabel.setAlignment(Pos.CENTER);
        vBox.setPrefWidth(290);
        vBox.setPrefHeight(280);
        vBox.setStyle("-fx-background-color: #e74c3c");
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().setAll(imageView,titleLabel,priceLabel,timeLabel);
        return vBox;
    }
}
