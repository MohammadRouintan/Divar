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
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
        AddBox(parent,post);
    }
    protected void AddBox(Parent parent,JSONObject post){
        File cssFile = new File("../Client/src/main/resources/com/example/Style/FullViewAds.css");
        File imageSliderCss = new File("../Client/src/main/resources/com/example/Style/ImageSlider.css");

        mainVBox.getChildren().clear();
        mainVBox.setSpacing(18);

        Pagination slideShow = makeSlideShow(post);
        slideShow.getStylesheets().add(imageSliderCss.toURI().toString());

        //set Back button
        HBox hBox = new HBox();
        Button backButton = new Button("");
        File imageUrl = new File("../Client/src/main/resources/com/example/image/previous.png");
        Image img = new Image(imageUrl.toURI().toString());
        ImageView backIcon = new ImageView(img);
        backIcon.setFitWidth(40);
        backIcon.setFitHeight(40);
        backButton.setGraphic(backIcon);
        backButton.setStyle("-fx-background-color: transparent");
        hBox.getChildren().add(backButton);
        hBox.setMargin(backButton,new Insets(10 ,0 ,20,30));
        backButton.setOnAction(event -> {
            mainVBox.getChildren().clear();
            for(Node node : nodesOfMainVbox){
                mainVBox.getChildren().add(node);
            }
         });
        //set Tilte label
        Label title = new Label(post.getString("title"));
        title.getStylesheets().add(cssFile.toURI().toString());
        title.getStyleClass().add("title");
        //city and catergoi Label
        Label cityAndCat = new Label(post.getString("city") +" | "+ post.getString("branch1"));
        cityAndCat.getStylesheets().add(cssFile.toURI().toString());
        cityAndCat.getStyleClass().add("city");
        //set Time label
        Label time = new Label(post.getString("time"));
        time.getStylesheets().add(cssFile.toURI().toString());
        time.getStyleClass().add("time");
        //set price Lable
        Label price = new Label();
        price.getStylesheets().add(cssFile.toURI().toString());
        price.getStyleClass().add("pirce");
        //set priceField for auction
        TextField priceField = new TextField();
        priceField.setPromptText("NewPrice");
        priceField.getStylesheets().add(cssFile.toURI().toString());
        priceField.getStyleClass().add("priceField");
        priceField.setMaxWidth(350);
        priceField.setPrefHeight(45);
        //set OK Button for set new price
        Button auction = new Button("OK");
        auction.getStylesheets().add(cssFile.toURI().toString());
        auction.getStyleClass().add("btn");
        auction.setPrefWidth(350);
        auction.setPrefHeight(45);

        auction.setOnAction(event -> {
            try {
                Long newPrice = Long.parseLong(priceField.getText());
                ArrayList<String> keys = new ArrayList<>();
                ArrayList<Object> values = new ArrayList<>();
                keys.add("price");
                values.add(newPrice);
                GetInfo.updatePost(post.getInt("postId"), keys, values);
                price.setText(newPrice.toString());
                priceField.setText("");
            } catch (Exception e) {

            }
        });

        //exchange Lable
        Label exchange = new Label();
        if (post.getBoolean("exchange")) {
            exchange.setText("I want to exchange");
            exchange.getStylesheets().add(cssFile.toURI().toString());
            exchange.getStyleClass().add("pirce");
        }

        //agreement price
        if (post.getBoolean("agreement")) {
            price.setText("Agreement");
        } else {
            price.setText(String.valueOf(post.getLong("price")));
        }

        //Delete Button
        Button delete = new Button("Delete");
        delete.getStylesheets().add(cssFile.toURI().toString());
        delete.getStyleClass().add("btn");
        delete.setPrefWidth(185);
        delete.setPrefHeight(45);
        delete.setOnAction(event -> {
            GetInfo.deletePost(post.getInt("postId"));
        });

        //button Hbox
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(20);
        //bookmark button
        Button bookmarked = new Button("");
        bookmarked.setOnAction(event -> GetInfo.updateUserArrays("bookmarkPost", post.getInt("postId")));

        //chat button
        Button chat = new Button("Chat");
        chat.getStylesheets().add(cssFile.toURI().toString());
        chat.getStyleClass().add("btn");
        chat.setPrefHeight(46);
        chat.setPrefWidth(180);

        chat.setOnAction(event -> chatButtonFunc(post));
        buttons.getChildren().addAll(bookmarked, chat);

        HBox featureColumnHBox = makeVerticalFeatureLabel(post);
        VBox featureRowVBox = makeHorizontalFeatureLabel(post);

        Label descriptionLabel = new Label("Description");
        Text descriptionText = new Text(post.getString("bio"));
        descriptionText.setTextAlignment(TextAlignment.JUSTIFY);
        descriptionText.setWrappingWidth(460);

        VBox descriptionVBox = new VBox();
        descriptionVBox.getChildren().addAll(descriptionLabel,descriptionText);
        descriptionVBox.setAlignment(Pos.CENTER);

        if (post.getBoolean("auction") && !this.paneName.equals("MyAds") && !post.getString("phoneNumber").equals(GetInfo.phoneNumber)) {
            mainVBox.getChildren().addAll(hBox,slideShow,title,cityAndCat,time,price,priceField,auction,featureColumnHBox,featureRowVBox,descriptionVBox, buttons);
        } else if (!this.paneName.equals("MyAds") && !post.getString("phoneNumber").equals(GetInfo.phoneNumber)){
            mainVBox.getChildren().addAll(hBox,slideShow,title,cityAndCat,time,price,exchange,featureColumnHBox,featureRowVBox,descriptionVBox, buttons);
        } else {
            mainVBox.getChildren().addAll(hBox,slideShow,title,cityAndCat,time,price,exchange,featureColumnHBox,featureRowVBox,descriptionVBox, delete);
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
        File cssFile = new File("../Client/src/main/resources/com/example/Style/FullViewAds.css");
        HBox featureHbox = new HBox();
        featureHbox.setAlignment(Pos.CENTER);
        featureHbox.setSpacing(10);
        for(int i = 0; i < NameColumnFeature.length(); i++){
            VBox vBox = new VBox();
            Label NameLabel = new Label(NameColumnFeature.getString(i));
            NameLabel.getStylesheets().add(cssFile.toURI().toString());
            NameLabel.getStyleClass().add("name");
            NameLabel.setPrefWidth(130);
            NameLabel.setPrefHeight(50);
            Label ValueLabel = new Label(ValueColumnFeature.getString(i));
            ValueLabel.getStylesheets().add(cssFile.toURI().toString());
            ValueLabel.getStyleClass().add("value");
            ValueLabel.setPrefWidth(130);
            ValueLabel.setPrefHeight(50);
            vBox.setSpacing(5);
            vBox.setAlignment(Pos.CENTER);
            vBox.getChildren().addAll(NameLabel,ValueLabel);
            featureHbox.getChildren().add(vBox);
        }
        return featureHbox;
    }

    protected VBox makeHorizontalFeatureLabel(JSONObject post){

        JSONArray NameRowFeature = post.getJSONArray("rowName");
        JSONArray ValueRowFeature = post.getJSONArray("rowValue");
        File cssFile = new File("../Client/src/main/resources/com/example/Style/FullViewAds.css");

        VBox featureVBox = new VBox();
        featureVBox.setSpacing(10);
        featureVBox.setAlignment(Pos.CENTER);
        for(int i = 0; i < NameRowFeature.length(); i++){
            HBox hBox = new HBox();
            Label NameLabel = new Label(NameRowFeature.getString(i));
            NameLabel.getStylesheets().add(cssFile.toURI().toString());
            NameLabel.getStyleClass().add("name");
            NameLabel.setPrefWidth(150);
            NameLabel.setPrefHeight(50);

            Label ValueLabel = new Label(ValueRowFeature.getString(i));
            ValueLabel.getStylesheets().add(cssFile.toURI().toString());
            ValueLabel.getStyleClass().add("value");
            ValueLabel.setPrefWidth(315);
            ValueLabel.setPrefHeight(50);

            hBox.setSpacing(10);
            hBox.setAlignment(Pos.CENTER);
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
//    protected Label setPrice(JSONObject post){
//        Label price = new Label(post.getString("price"));
//        return Label;
//    }

    protected void chatButtonFunc(JSONObject post){
        Pane pane ;
        pane = (Pane) mainVBox.getParent();
        try {
            Pane chatPane = FXMLLoader.load(Main.class.getResource("Section/ChatSection.fxml"));
            pane.getChildren().clear();
            pane.getChildren().add(chatPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
