package com.example.client.Dashboard;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MyDivarController {
    @FXML
    private Pane MyDivarPane;

    @FXML
    void MyAdsButton(ActionEvent event){
        try {
            Pane pane = FXMLLoader.load(Main.class.getResource("MyDivarPages/MyAds.fxml"));
            MyDivarPane.getChildren().clear();
            MyDivarPane.getChildren().add(pane);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void SavedButton(ActionEvent event){
        try {
            Pane pane = FXMLLoader.load(Main.class.getResource("MyDivarPages/MyDivarAds.fxml"));
            MyDivarPane.getChildren().clear();
            MyDivarPane.getChildren().add(pane);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void RecentVisitsButton(ActionEvent event){
        try {
            Pane pane = FXMLLoader.load(Main.class.getResource("MyDivarPages/MyDivarAds.fxml"));
            MyDivarPane.getChildren().clear();
            MyDivarPane.getChildren().add(pane);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void EditProfileButton(ActionEvent event){
        try {
            Pane pane = FXMLLoader.load(Main.class.getResource("MyDivarPages/EditProfile.fxml"));
            MyDivarPane.getChildren().clear();
            MyDivarPane.getChildren().add(pane);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
