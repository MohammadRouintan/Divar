package com.example.client.Dashboard.Posts;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewPage {
    private ArrayList<JSONObject> postsList;
    private VBox page;

    public NewPage(ArrayList<JSONObject> postsList,VBox page){
        this.page = page;
        this.postsList = postsList;
        AddHBox();
    }

    private void AddHBox(){
        if(postsList.size() % 2 ==0){
            for (int i = 0 ; i < postsList.size() - 1 ; i += 2){
                HBox hBox = makeNewHBox(postsList.get(i),postsList.get(i+1));
                page.getChildren().add(hBox);
            }
        }
        else {
            for (int i = 0 ; i < postsList.size() - 1 ; i += 2){
                HBox hBox = makeNewHBox(postsList.get(i),postsList.get(i+1));
                page.getChildren().add(hBox);
            }
            HBox hBox = makeNewHBox(postsList.get(postsList.size()-1));
            page.getChildren().add(hBox);
        }
    }

    public VBox getPage() {
        return page;
    }

    private HBox makeNewHBox(JSONObject post1, JSONObject post2) {
        HBox rowHBox = new HBox();
        VBox vBox1 = makeNewVBox(post1);
        VBox vBox2 = makeNewVBox(post2);
        rowHBox.getChildren().addAll(vBox1,vBox2);
        return rowHBox;
    }

    private HBox makeNewHBox(JSONObject post1) {
        HBox rowHBox = new HBox();
        VBox vBox1 = makeNewVBox(post1);
        rowHBox.getChildren().add(vBox1);
        return rowHBox;
    }

    private VBox makeNewVBox(JSONObject post){
        VBox vBox = new VBox();
        ImageView imageView = new ImageView();
        Image img = new Image(post.getString("image1"));
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
        vBox.getChildren().addAll(imageView,titleLabel,priceLabel,timeLabel);
        vBox.setOnMouseClicked(event -> ShowAds(vBox, post));
        return vBox;
    }

    void ShowAds(VBox vBox,JSONObject post){
        Parent parent = vBox.getParent().getParent().getParent().getParent();
        FullViewAds fullViewAds = new FullViewAds(parent,post);
    }



}
