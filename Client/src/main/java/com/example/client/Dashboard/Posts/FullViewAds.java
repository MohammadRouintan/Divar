package com.example.client.Dashboard.Posts;

import com.example.client.Main;
import com.example.client.socket.GetInfo;
import com.example.client.socket.ImageController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.io.File;
import java.io.File;
import java.io.IOException;
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
        File cssFile = new File("../Client/src/main/resources/Style/FullViewAds.css");

        mainVBox.getChildren().clear();
        VBox AdsVBox = new VBox();
        Pagination slideShow = makeSlideShow(post);

        //set Back button
        HBox hBox = new HBox();
        Button backButton = new Button("");
        File imageUrl = new File("../Client/src/main/resources/Icon/previous.png");
        Image img = new Image(imageUrl.toURI().toString());
        ImageView backIcon = new ImageView(img);
//        backIcon.setFitWidth(40);
//        backIcon.setFitHeight(40);
//        backButton.setGraphic(backIcon);
       // backButton.set
        //backButton.setStyle("-fx-background-color: transparent");
        hBox.getChildren().add(backButton);
        HBox.setMargin(backButton,new Insets(10 ,0 ,20,30));
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
        Button auction = new Button("OK");
        priceField.setPromptText("NewPrice");
        priceField.setAlignment(Pos.CENTER);
        priceField.setMaxWidth(300);
        Label exchange = new Label();
        auction.setOnAction(event -> {
            try {
                Long newPrice = Long.parseLong(priceField.getText());
                ArrayList<String> keys = new ArrayList<>();
                ArrayList<Object> values = new ArrayList<>();
                keys.add("price");
                values.add(newPrice);
                GetInfo.updatePost(post.getInt("postId"), keys, values);
            } catch (Exception e) {

            }
        });

        if (post.getBoolean("agreement")) {
            price = new Label("agreement");
        } else {
            price = new Label(String.valueOf(post.getLong("price")));
        }

        if (post.getBoolean("exchange")) {
            exchange.setText("I want to exchange");
        }

        Button delete = new Button("Delete");

        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(20);
        //bookmark button
        Button bookmarked = new Button("Bookmark");
        //chat button
        Button chat = new Button("Chat");
        chat.setOnAction(event -> chatButtonFunc(post));


        buttons.getChildren().addAll(bookmarked, chat);

        bookmarked.setOnAction(event -> GetInfo.updateUserArrays("bookmarkPost", post.getInt("postId")));
        delete.setOnAction(event -> {
            GetInfo.deletePost(post.getInt("postId"));
        });

        HBox featureColumnHBox = makeVerticalFeatureLabel(post);
        VBox featureRowVBox = makeHorizontalFeatureLabel(post);

        Label descriptionLabel = new Label("Description");
        Label descriptionText = new Label(post.getString("bio"));
        descriptionText.setAlignment(Pos.CENTER);
        descriptionText.setPrefWidth(300);
        descriptionText.setWrapText(true);
        VBox descriptionVBox = new VBox();
        descriptionVBox.getChildren().addAll(descriptionLabel,descriptionText);
        descriptionVBox.setAlignment(Pos.CENTER);

        AdsVBox.setPrefWidth(1200);
        AdsVBox.setPrefHeight(570);
        if (post.getBoolean("auction") && !this.paneName.equals("MyAds") && !post.getString("phoneNumber").equals(GetInfo.phoneNumber)) {
            mainVBox.getChildren().addAll(hBox,slideShow,title,time,priceField,auction,featureRowVBox,featureColumnHBox,descriptionVBox, buttons);
        } else if (!this.paneName.equals("MyAds") && !post.getString("phoneNumber").equals(GetInfo.phoneNumber)){
            mainVBox.getChildren().addAll(hBox,slideShow,title,time,price,exchange,featureRowVBox,featureColumnHBox,descriptionVBox, buttons);
        } else {
            mainVBox.getChildren().addAll(hBox,slideShow,title,time,price,exchange,featureRowVBox,featureColumnHBox,descriptionVBox, delete);
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
            vBox.setSpacing(5);
            vBox.getChildren().addAll(NameLabel,ValueLabel);
            featureHbox.getChildren().add(vBox);
            featureHbox.setAlignment(Pos.CENTER);
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
            hBox.setSpacing(10);
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().addAll(NameLabel,ValueLabel);
            featureVBox.getChildren().add(hBox);
            featureVBox.setAlignment(Pos.CENTER);
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
//    protected Label setPrice(JSONObject post){
//        Label price = new Label(post.getString("price"));
//        return Label;
//    }

    protected void chatButtonFunc(JSONObject post){
        Pane pane ;

        if(mainVBox.getParent().getParent().getTypeSelector().equals("Pane")){
            pane = (Pane) mainVBox.getParent().getParent();
        }else {
            pane = (Pane) mainVBox.getParent().getParent().getParent();
        }

        try {
            Pane chatPane = FXMLLoader.load(Main.class.getResource("Section/ChatSection.fxml"));
            pane.getChildren().clear();
            pane.getChildren().add(chatPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
