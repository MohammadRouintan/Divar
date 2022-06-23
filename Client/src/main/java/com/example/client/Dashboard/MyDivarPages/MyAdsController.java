package com.example.client.Dashboard.MyDivarPages;

import com.example.client.Dashboard.Posts.NewPage;
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

    List<VBox> PagesList = new ArrayList<VBox>();

    String url = "/postImage/1.jpg";

    @FXML
    public void initialize() {
        ArrayList<JSONObject> post = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            JSONObject post1 = new JSONObject();
            post1.put("title", "salam");
            post1.put("price", "14000");
            post1.put("time", "5");
            post1.put("image1", "/postImage/1.jpg");
            post.add(post1);
        }
        VBox vBox = new VBox();
        NewPage newPage = new NewPage(post,vBox);
        PagesList.add(newPage.getPage());
        pagination.setPageFactory(this::CreatPage);

    }

    private Node CreatPage(int pageIndex) {
        ArrayList<JSONObject> post = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            JSONObject post1 = new JSONObject();
            post1.put("title", "salam");
            post1.put("price", "14000");
            post1.put("time", "5");
            post1.put("image1", "/postImage/1.jpg");
            post.add(post1);
        }
        VBox vBox = new VBox();
        NewPage newPage = new NewPage(post,vBox);
        PagesList.add(newPage.getPage());
        return PagesList.get(pageIndex);
    }

}