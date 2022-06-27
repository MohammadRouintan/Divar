package com.example.client.Dashboard.Explore;

import com.example.client.Dashboard.Posts.NewPage;
import com.example.client.socket.GetInfo;
import javafx.fxml.FXML;
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
    private String branchMain;
    private String branch1;
    private VBox mainVBox;
    private VBox vBox;
    private List<VBox> PagesList = new ArrayList<VBox>();

    public PageThreeCategories(Parent parent, String branchMain, String branch1){
        this.mainVBox = (VBox)parent;
        this.vBox = (VBox) mainVBox.getChildren().get(0);
        this.branchMain = branchMain;
        this.branch1 = branch1;
        addPagination();
    }

    private Node CreatePage(int pageIndex) {
        ArrayList<JSONObject> categoriesPosts = new ArrayList<>();
        ArrayList<String> keys = new ArrayList<>();
        ArrayList<Object> values = new ArrayList<>();
        if (this.branch1.equals("ShowAll")) {
            keys.add("branchMain");
            values.add(this.branchMain);
        } else {
            keys.add("branch1");
            values.add(this.branch1);
        }
        ArrayList<String> list = GetInfo.getPosts(8, pageIndex, keys, values);
        for (String str : list) {
            categoriesPosts.add(new JSONObject(str));
        }
        VBox vBox = new VBox();
        NewPage newPage = new NewPage(categoriesPosts, vBox, "CategoriesAds");
        PagesList.add(newPage.getPage());
        return PagesList.get(pageIndex);
    }

    private void addPagination(){
        this.mainVBox.getChildren().clear();
        VBox threeCategories = new VBox();
        Label TitleLabel = new Label(this.branch1);
        Button back = new Button("Back");
        HBox tophBox = new HBox();

        Pagination pagination = new Pagination();
        pagination.setPrefWidth(550);
        pagination.setPrefHeight(1200);
        ArrayList<String> keys = new ArrayList<>();
        ArrayList<Object> values = new ArrayList<>();
        if (this.branch1.equals("ShowAll")) {
            keys.add("branchMain");
            values.add(this.branchMain);
        } else {
            keys.add("branch1");
            values.add(this.branch1);
        }

        int size = (int) Math.ceil((double) GetInfo.getSizeOfPosts(keys, values) / 8);
        if (size != 0) {
            pagination.setPageCount(size);
            pagination.setMaxPageIndicatorCount(2);
            pagination.setPageFactory(this::CreatePage);
        }

        tophBox.getChildren().addAll(back,TitleLabel);
        this.mainVBox.getChildren().addAll(tophBox,pagination);

        back.setOnAction(event -> {
            mainVBox.getChildren().clear();
            mainVBox.getChildren().add(this.vBox);
        });
    }
}
