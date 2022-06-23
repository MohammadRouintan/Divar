package com.example.client.Dashboard.Posts;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FullViewAds {

    private JSONObject post;
    private Parent parent;
    private Pagination pagination;

    public FullViewAds(Parent parent, JSONObject post){
        this.post = post;
        this.parent = parent;
        this.pagination = (Pagination)parent;
        AddBox(parent,post);
    }

    private void AddBox(Parent parent,JSONObject post){


        VBox mainVBox = (VBox)parent.getParent();
        mainVBox.getChildren().clear();


        VBox AdsVBox = new VBox();
        Button backButton = new Button("Back");

        //set Back button
        HBox hBox = new HBox();
        hBox.getChildren().add(backButton);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
            public void handle(ActionEvent event) {
               mainVBox.getChildren().clear();
               mainVBox.getChildren().add(pagination);
            }
        });

        Label title = new Label(post.getString("title"));
        Label time = new Label(post.getString("time"));
        Label price = new Label(post.getString("price"));
        AdsVBox.setPrefWidth(1200);
        AdsVBox.setPrefHeight(570);
        mainVBox.getChildren().addAll(hBox,title,time,price);
        //post.getJSONArray("rowName").length();
        //post.getJSONArray("columnName").length();
    }

    private void BackButton(Pagination pagination){
        VBox mainVbox = (VBox) parent.getParent();
        mainVbox.getChildren().add(new Label("helllo"));
    }

    private Pagination makeSlideShow(JSONObject post){
        Pagination pagination = new Pagination();
        JSONArray images = post.getJSONArray("imageName");
        return pagination;
    }
}
