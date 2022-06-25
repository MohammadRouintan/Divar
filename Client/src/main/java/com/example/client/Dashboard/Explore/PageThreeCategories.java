package com.example.client.Dashboard.Explore;

import com.example.client.Dashboard.Posts.NewPage;
import com.example.client.socket.GetInfo;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PageThreeCategories {
    private String nameCat;
    private VBox mainVBox;
    private VBox vBox;
    private List<VBox> PagesList = new ArrayList<VBox>();

    public PageThreeCategories(Parent parent,String nameCat){
        this.mainVBox = (VBox)parent;
        this.vBox = (VBox) mainVBox.getChildren().get(0);
        this.nameCat = nameCat;
        addPagination();
    }

    private void addPagination(){
        this.mainVBox.getChildren().clear();
        VBox threeCategories = new VBox();
        Label TitleLabel = new Label(nameCat);
        Button back = new Button("Back");
        HBox tophBox = new HBox();

        Pagination pagination = new Pagination();
        pagination.setPrefWidth(550);
        pagination.setPrefHeight(1200);
        ArrayList<String> posts = GetInfo.getPosts(8,"branch1",this.nameCat,0);
        ArrayList<JSONObject> categoryPosts = new ArrayList<>();
        for (String post : posts) {
            categoryPosts.add(new JSONObject(post));
        }
        //test the show of post

//        ArrayList<JSONObject> post = new ArrayList<>();
//        ArrayList<String> imageName = new ArrayList<>();
//        imageName.add("1");
//        imageName.add("2");
//        ArrayList<String> columnName = new ArrayList<>();
//        columnName.add("Salam");
//        columnName.add("Khobi");
//
//        ArrayList<String> columnValue = new ArrayList<>();
//        columnValue.add("dddddd");
//        columnValue.add("bbbbbb");
//
//        for (int i = 0; i < 7; i++) {
//            JSONObject post1 = new JSONObject();
//            post1.put("title", "salam");
//            post1.put("price", "14000");
//            post1.put("time", "5");
//            post1.put("image1", "/postImage/1.jpg");
//            post1.put("imageName",imageName);
//            post1.put("columnName",columnName);
//            post1.put("columnValue",columnValue);
//            post1.put("bio","I have a scene showing 3 images, and I want each of them to take a third of the width of the scene. " +
//                    "From now, I have made 3 Pane of each 30% of it, it works. But in those Pane," +
//                    " I can't make my ImageView use only the width of the Pane.");
//            post1.put("agreement", true);
//            post1.put("exchange", true);
//            post1.put("auction", false);
//            post.add(post1);
//        }

        VBox vBox = new VBox();
        NewPage newPage = new NewPage(categoryPosts,vBox, "BookmarkedAds");
        PagesList.add(newPage.getPage());
        pagination.setPageFactory(this::CreatePage);

        tophBox.getChildren().addAll(back,TitleLabel);
        this.mainVBox.getChildren().addAll(tophBox,pagination);

        back.setOnAction(event -> {
            mainVBox.getChildren().clear();
            mainVBox.getChildren().add(this.vBox);
        });
    }

    private Node CreatePage(int pageIndex) {

        ArrayList<JSONObject> post = new ArrayList<>();
        ArrayList<String> imageName = new ArrayList<>();
        imageName.add("1");
        imageName.add("2");
        ArrayList<String> columnName = new ArrayList<>();
        columnName.add("Salam");
        columnName.add("Khobi");

        ArrayList<String> columnValue = new ArrayList<>();
        columnValue.add("dddddd");
        columnValue.add("bbbbbb");

        for (int i = 0; i < 7; i++) {
            JSONObject post1 = new JSONObject();
            post1.put("title", "salam");
            post1.put("price", "14000");
            post1.put("time", "5");
            post1.put("image1", "/postImage/1.jpg");
            post1.put("imageName",imageName);
            post1.put("columnName",columnName);
            post1.put("columnValue",columnValue);
            post1.put("bio","I have a scene showing 3 images, and I want each of them to take a third of the width of the scene. " +
                    "From now, I have made 3 Pane of each 30% of it, it works. But in those Pane," +
                    " I can't make my ImageView use only the width of the Pane.");
            post.add(post1);
        }
        VBox vBox = new VBox();
        NewPage newPage = new NewPage(post,vBox,"Categories");
        PagesList.add(newPage.getPage());
        return PagesList.get(pageIndex);
    }

}
