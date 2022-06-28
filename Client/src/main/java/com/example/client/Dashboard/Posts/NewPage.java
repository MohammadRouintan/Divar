package com.example.client.Dashboard.Posts;
import com.example.client.socket.GetInfo;
import com.example.client.socket.ImageController;

import javafx.geometry.Insets;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.json.JSONObject;

import java.io.File;

import java.util.ArrayList;

public class NewPage {
    private ArrayList<JSONObject> postsList;
    private VBox page;
    private String paneName;

    public NewPage(ArrayList<JSONObject> postsList,VBox page, String paneName){
        this.page = page;
        this.postsList = postsList;
        this.paneName = paneName;
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
        rowHBox.setPadding(new Insets(8));
        rowHBox.getChildren().addAll(vBox1,vBox2);
        rowHBox.setMargin(vBox1,new Insets(5,5,5,5));
        rowHBox.setMargin(vBox2,new Insets(5,5,5,5));
        return rowHBox;
    }

    private HBox makeNewHBox(JSONObject post1) {
        HBox rowHBox = new HBox();
        rowHBox.setPadding(new Insets(8));
        VBox vBox1 = makeNewVBox(post1);
        rowHBox.getChildren().add(vBox1);
        return rowHBox;
    }

    private VBox makeNewVBox(JSONObject post){
        File file = new File("../Client/src/main/resources/Style/newPost.css");
        VBox vBox = new VBox();
        String url = getStringArray(post.getJSONArray("imageName")).get(0);
        ImageController imageController = new ImageController("", getStringArray(post.getJSONArray("imageName")).get(0), 1);
        imageController.start();
        try {
            imageController.join();
        } catch (InterruptedException e){
            System.err.println(e.getMessage());
        }


        Image img = new Image(imageController.getPath());
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(200);
        imageView.setImage(img);
        Label titleLabel = new Label(post.getString("title"));
        Label priceLabel = new Label("Agreement");
        if (post.has("price")) {
            priceLabel = new Label(String.valueOf(post.getLong("price")));
        }

        Label timeLabel = new Label(post.getString("time"));

        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setFont(new Font(17));
        titleLabel.setStyle("-fx-font-weight: bold");

        priceLabel.setAlignment(Pos.CENTER);
        priceLabel.setFont(new Font(16));
        priceLabel.setTextFill(Color.web("#bdc3c7"));
        priceLabel.setStyle("-fx-font-weight: bold");

        timeLabel.setAlignment(Pos.CENTER);
        timeLabel.setFont(new Font(15));
        timeLabel.setTextFill(Color.web("#bdc3c7"));

        vBox.setPrefWidth(270);
        vBox.setMinWidth(270);
        vBox.setPrefHeight(330);
        vBox.setMinHeight(330);
        vBox.setSpacing(8);
        vBox.setMargin(imageView,new Insets(5,10,5,10));
        vBox.getStylesheets().add(file.toURI().toString());
        vBox.getStyleClass().add("post");

        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setCursor(Cursor.HAND);
        vBox.setPadding(new Insets(10,0,5,0));
        vBox.getChildren().addAll(imageView,titleLabel,priceLabel,timeLabel);
        vBox.setOnMouseClicked(event -> {
            ShowAds(vBox, post);
            if (!this.paneName.equals("MyAds") && !this.paneName.equals("LastSeenAds"))
                GetInfo.updateUserArrays("lastSeenPost", post.getInt("postId"));
        });
        return vBox;
    }

    void ShowAds(VBox vBox,JSONObject post){
        Parent parent = vBox.getParent().getParent().getParent().getParent();
        FullViewAds fullViewAds = new FullViewAds(parent,post, this.paneName);
    }

    public ArrayList<String> getStringArray (JSONArray JArray) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < JArray.length(); i++) {
            list.add(JArray.getString(i));
        }
        return list;
    }
    public ArrayList<Object> getObjectArray (JSONArray JArray) {
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < JArray.length(); i++) {
            list.add(JArray.get(i));
        }
        return list;
    }
}
