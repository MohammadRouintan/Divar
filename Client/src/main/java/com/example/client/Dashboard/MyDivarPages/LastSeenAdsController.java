package com.example.client.Dashboard.MyDivarPages;

import com.example.client.Dashboard.Posts.NewPage;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.layout.VBox;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LastSeenAdsController {
    @FXML
    private VBox mainVBox;

    @FXML
    private Pagination pagination;

    List<VBox> PagesList = new ArrayList<VBox>();


    @FXML
    public void initialize() {

//        GetInfo.getUserPosts();
//        ArrayList<JSONObject> userPosts = new ArrayList<>();
//        for (int i = 0; i < 8; i++) {
//            userPosts.add(new JSONObject(GetInfo.getUserPosts().get(i)));
//        }
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
            post1.put("agreement", true);
            post1.put("exchange", true);
            post1.put("auction", false);
            post.add(post1);
        }
        VBox vBox = new VBox();
        NewPage newPage = new NewPage(post,vBox, "BookmarkedAds");
        PagesList.add(newPage.getPage());
        pagination.setPageFactory(this::CreatePage);
    }

    private Node CreatePage(int pageIndex) {
//        ArrayList<JSONObject> userPosts = new ArrayList<>();
//        for (int i = (pagination.getCurrentPageIndex() - 1) * 8; i < pagination.getCurrentPageIndex() * 8; i++) {
//            userPosts.add(new JSONObject(GetInfo.getUserPosts().get(i)));
//        }
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
        NewPage newPage = new NewPage(post,vBox,"BookmarkedAds");
        PagesList.add(newPage.getPage());
        return PagesList.get(pageIndex);
    }
}
