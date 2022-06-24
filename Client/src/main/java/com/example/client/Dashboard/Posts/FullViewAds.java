package com.example.client.Dashboard.Posts;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
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
    protected Pagination pagination;

    public FullViewAds(Parent parent, JSONObject post){
        this.post = post;
        this.parent = parent;
        this.pagination = (Pagination)parent;
        AddBox(parent,post);
    }

    protected void AddBox(Parent parent,JSONObject post){


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
        Label price = new Label();
        Label newPrice = new Label("NewPrice : ");
        TextField priceField = new TextField();
        HBox auction = new HBox();
        Label exchange = new Label();

        if (post.getBoolean("auction")) {
            auction.getChildren().addAll(newPrice, priceField);
        } else {
            if (post.getBoolean("agreement")) {
                price = new Label("agreement");
            } else {
                price = new Label(post.getString("price"));
            }

            if (post.getBoolean("exchange")) {
                exchange.setText("I want to exchange");
            }
        }



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
        if (post.getBoolean("auction")) {
            mainVBox.getChildren().addAll(hBox,slideShow,title,time,auction,featureColumnHBox,featureRowVBox,descriptionVBox);
        } else {
            mainVBox.getChildren().addAll(hBox,slideShow,title,time,price,exchange,featureColumnHBox,featureRowVBox,descriptionVBox);
        }
        //post.getJSONArray("rowName").length();
        //post.getJSONArray("columnName").length();

    }


    protected void BackButton(Pagination pagination){
        VBox mainVbox = (VBox) parent.getParent();
        mainVbox.getChildren().add(new Label("helllo"));
    }


    protected Pagination makeSlideShow(JSONObject post){
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

    protected HBox makeVerticalFeatureLabel(JSONObject post){

        JSONArray NameColumnFeature = post.getJSONArray("columnName");
        JSONArray ValueColumnFeature = post.getJSONArray("columnValue");

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

    protected VBox makeHorizontalFeatureLabel(JSONObject post){

        JSONArray NameRowFeature = post.getJSONArray("columnName");
        JSONArray ValueRowFeature = post.getJSONArray("columnValue");

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
//    protected Label setPrice(JSONObject post){
//        Label price = new Label(post.getString("price"));
//        return Label;
//    }
}
