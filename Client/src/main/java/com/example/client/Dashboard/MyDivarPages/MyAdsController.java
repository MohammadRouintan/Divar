package com.example.client.Dashboard.MyDivarPages;

import com.example.client.Dashboard.Posts.NewPage;
import com.example.client.socket.GetInfo;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyAdsController {
    @FXML
    private Label testLabel;

    @FXML
    private ScrollPane scroll;

    @FXML
    private VBox mainVBox;

    @FXML
    private Pagination pagination;

    private List<VBox> PagesList = new ArrayList<VBox>();

    @FXML
    public void initialize() {
        ArrayList<JSONObject> userPosts = new ArrayList<>();
        if (isPageReceived.size() == 0) {
            ArrayList<String> list = GetInfo.getUserPosts();
            for (String str : list) {
                userPosts.add(new JSONObject(str));
            }
            isPageReceived.add(true);
            posts.addAll(userPosts);
        }else {
            for (int i = 0; i < Math.min(8, posts.size()); i++) {
                userPosts.add(posts.get(i));
            }
        }
//        GetInfo.getUserPosts();
//        ArrayList<JSONObject> userPosts = new ArrayList<>();
//        for (int i = 0; i < 8; i++) {
//            userPosts.add(new JSONObject(GetInfo.getUserPosts().get(i)));
//        }

        VBox vBox = new VBox();
        NewPage newPage = new NewPage(userPosts,vBox, "MyAds");
        PagesList.add(newPage.getPage());
        pagination.setPageFactory(this::CreatePage);
    }

    private Node CreatePage(int pageIndex) {
        ArrayList<JSONObject> userPosts = new ArrayList<>();
        if (isPageReceived.size() >= pageIndex) {
            for (int i = posts.size() - (pageIndex - 1) * 8; i < Math.min(posts.size() - (pageIndex - 1) * 8 + 8, posts.size() - pageIndex * 8); i++) {
                userPosts.add(posts.get(i));
            }
        } else {
            ArrayList<String> list = GetInfo.getUserPosts();
            for (String str : list) {
                userPosts.add(new JSONObject(str));
            }
            isPageReceived.add(true);
            posts.addAll(userPosts);
        }
        VBox vBox = new VBox();
        NewPage newPage = new NewPage(userPosts, vBox, "MyAds");
        PagesList.add(newPage.getPage());
        return PagesList.get(pageIndex);
    }

    public static ArrayList<Boolean> isPageReceived;
    public static ArrayList<JSONObject> posts;
}