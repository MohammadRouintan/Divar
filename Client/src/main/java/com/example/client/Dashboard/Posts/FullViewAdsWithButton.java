package com.example.client.Dashboard.Posts;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONObject;

public class FullViewAdsWithButton extends FullViewAds{

    public FullViewAdsWithButton(Parent parent, JSONObject post) {
        super(parent, post);
    }

    @Override
    protected void AddBox(Parent parent, JSONObject post){
        super.AddBox(parent,post);
        if(post.getBoolean("agreement")){

        }
    }
}
