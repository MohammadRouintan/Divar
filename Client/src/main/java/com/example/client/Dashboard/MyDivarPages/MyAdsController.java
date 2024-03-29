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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyAdsController {
    @FXML
    private Pagination pagination;

    private List<VBox> PagesList = new ArrayList<VBox>();

    @FXML
    public void initialize() {
        int size = (int) Math.ceil((double) GetInfo.getSizeOfArray("userPosts") / 8);
        if (size != 0) {
            pagination.setPageCount(size);
            pagination.setMaxPageIndicatorCount(2);
            pagination.setPageFactory(this::CreatePage);
        }
    }

    private Node CreatePage(int pageIndex) {
        ArrayList<JSONObject> userPosts = new ArrayList<>();
        ArrayList<String> list = GetInfo.getUserPosts(8, pageIndex);
        for (String str : list) {
            userPosts.add(new JSONObject(str));
        }
        VBox vBox = new VBox();
        NewPage newPage = new NewPage(userPosts, vBox, "MyAds");
        PagesList.add(newPage.getPage());
        return PagesList.get(pageIndex);
    }
}