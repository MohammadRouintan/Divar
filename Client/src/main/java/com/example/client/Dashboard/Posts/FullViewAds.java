package com.example.client.Dashboard.Posts;

import com.example.client.socket.GetInfo;
import com.example.client.socket.ImageController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
    protected ArrayList<Node> nodesOfMainVbox;
    protected VBox mainVBox;
    String paneName;

    public FullViewAds(Parent parent, JSONObject post, String paneName){
        this.post = post;
        this.parent = parent;
        this.paneName = paneName;
        this.mainVBox = (VBox)this.parent.getParent();
        this.nodesOfMainVbox = getAllChildren(this.mainVBox);
        mainVBox.getChildren();
        AddBox(parent,post);
    }

    protected void AddBox(Parent parent,JSONObject post){


        mainVBox.getChildren().clear();


        VBox AdsVBox = new VBox();

        Pagination slideShow = makeSlideShow(post);

        //set Back button
        HBox hBox = new HBox();
        Button backButton = new Button("Back");
        hBox.getChildren().add(backButton);

        backButton.setOnAction(event -> {
            mainVBox.getChildren().clear();
            for(Node node : nodesOfMainVbox){
                mainVBox.getChildren().add(node);
            }
         });

        Label title = new Label(post.getString("title"));
        Label time = new Label(post.getString("time"));
        Label price;
        TextField priceField = new TextField();
        priceField.setPromptText("NewPrice");
        priceField.setAlignment(Pos.CENTER);
        priceField.setMaxWidth(300);
        Label exchange = new Label();

        if (post.getBoolean("agreement")) {
            price = new Label("agreement");
        } else {
            price = new Label(String.valueOf(post.getLong("price")));
        }

        if (post.getBoolean("exchange")) {
            exchange.setText("I want to exchange");
        }

        Button bookmarked = new Button("Bookmark");
        Button chat = new Button("Chat");
        chat.setOnAction(event -> );
        Button delete = new Button("Delete");
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(20);
        buttons.getChildren().addAll(bookmarked, chat);
        bookmarked.setOnAction(event -> GetInfo.updateUserArrays("bookmarkPost", post.getInt("postId")));
        delete.setOnAction(event -> {
            GetInfo.deletePost(post.getInt("postId"));
        });

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
        if (post.getBoolean("auction") && !this.paneName.equals("MyAds")) {
            mainVBox.getChildren().addAll(hBox,slideShow,title,time,priceField,featureColumnHBox,featureRowVBox,descriptionVBox, buttons);
        } else if (!this.paneName.equals("MyAds")){
            mainVBox.getChildren().addAll(hBox,slideShow,title,time,price,exchange,featureColumnHBox,featureRowVBox,descriptionVBox, buttons);
        } else {
            mainVBox.getChildren().addAll(hBox,slideShow,title,time,price,exchange,featureColumnHBox,featureRowVBox,descriptionVBox, delete);
        }
    }


    protected Pagination makeSlideShow(JSONObject post){
        JSONArray images = post.getJSONArray("imageName");
        Pagination slideShow = new Pagination();
        slideShow.setPrefHeight(350);
        slideShow.setPrefWidth(570);
        slideShow.setPageCount(images.length());
        slideShow.setMaxPageIndicatorCount(images.length());
        slideShow.setPageFactory((pageIndex) -> {
            String url = "/post/"+getStringArray(images).get(pageIndex) + ".png";
            ImageController imageController = new ImageController("", getStringArray(images).get(pageIndex), 1);
            imageController.start();
            try {
                imageController.join();
            } catch (InterruptedException e){
                System.err.println(e.getMessage());
            }
            Image img = new Image(imageController.getPath());
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

        JSONArray NameRowFeature = post.getJSONArray("rowName");
        JSONArray ValueRowFeature = post.getJSONArray("rowValue");

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

    public ArrayList<String> getStringArray (JSONArray JArray) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < JArray.length(); i++) {
            list.add(JArray.getString(i));
        }
        return list;
    }

    protected ArrayList<Node> getAllChildren(VBox mainVBox) {
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.addAll(mainVBox.getChildrenUnmodifiable());
        return nodes;
    }
}
