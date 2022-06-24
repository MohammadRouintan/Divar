package com.example.client.Dashboard.Posts;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

        Pagination slideShow = makeSlideShow(post);

        //set Back button
        HBox hBox = new HBox();
        Button backButton = new Button("Back");
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

        HBox featureColumnHBox = makeVerticalFeatureLabel(post);
        VBox featureRowVBox = makeHorizontalFeatureLabel(post);

        Label descriptionLable = new Label("Description");
        Label descriptionText = new Label(post.getString("bio"));
        descriptionText.setPrefWidth(300);
        descriptionText.setWrapText(true);
        VBox descriptionVBox = new VBox();
        descriptionVBox.getChildren().addAll(descriptionLable,descriptionText);

        AdsVBox.setPrefWidth(1200);
        AdsVBox.setPrefHeight(570);
        mainVBox.getChildren().addAll(hBox,slideShow,title,time,price,featureColumnHBox,featureRowVBox,descriptionVBox);
        //post.getJSONArray("rowName").length();
        //post.getJSONArray("columnName").length();
    }


    private void BackButton(Pagination pagination){
        VBox mainVbox = (VBox) parent.getParent();
        mainVbox.getChildren().add(new Label("helllo"));
    }


    private Pagination makeSlideShow(JSONObject post){
        JSONArray images = post.getJSONArray("imageName");
        Pagination slideShow = new Pagination();
        slideShow.setPrefHeight(350);
        slideShow.setPrefWidth(570);
        slideShow.setPageCount(images.length());
        slideShow.setMaxPageIndicatorCount(images.length());
        slideShow.setPageFactory((pageIndex) -> {
            Image img = new Image("/postImage/"+images.getString(pageIndex)+".jpg");
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(400);
            imageView.setFitHeight(350);
            return imageView;
        });
        return slideShow;
    }

    private HBox makeVerticalFeatureLabel(JSONObject post){

        JSONArray NameColumnFeature = post.getJSONArray("ColumnName");
        JSONArray ValueColumnFeature = post.getJSONArray("ColumnValue");

        HBox featureHbox = new HBox();
        for(int i = 0; i < NameColumnFeature.length(); i++){
            VBox vBox = new VBox();
            Label NameLabel = new Label(NameColumnFeature.getString(i));
            Label ValueLabel = new Label(ValueColumnFeature.getString(i));
            vBox.getChildren().addAll(NameLabel,ValueLabel);
            featureHbox.getChildren().add(vBox);
        }
        return featureHbox;
    }

    private VBox makeHorizontalFeatureLabel(JSONObject post){

        JSONArray NameRowFeature = post.getJSONArray("ColumnName");
        JSONArray ValueRowFeature = post.getJSONArray("ColumnValue");

        VBox featureVBox = new VBox();
        for(int i = 0; i < NameRowFeature.length(); i++){
            HBox hBox = new HBox();
            Label NameLabel = new Label(NameRowFeature.getString(i));
            Label ValueLabel = new Label(ValueRowFeature.getString(i));
            hBox.getChildren().addAll(NameLabel,ValueLabel);
            featureVBox.getChildren().add(hBox);
        }

        return featureVBox;
    }

}
