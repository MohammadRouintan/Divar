package com.example.client.Dashboard;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainDashboardController {

    @FXML
        private Pane mainPane;
    @FXML
    public void initialize(){
        try {
            Pane pane = FXMLLoader.load(Main.class.getResource("Section/AdsSection.fxml"));
            mainPane.getChildren().clear();
            mainPane.getChildren().add(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void AdsButton(ActionEvent event) {
        try {
            Pane pane = FXMLLoader.load(Main.class.getResource("Section/AdsSection.fxml"));
            mainPane.getChildren().clear();
            mainPane.getChildren().add(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void CategoriesButton(ActionEvent event) {
        try {
            Pane pane = FXMLLoader.load(Main.class.getResource("Section/CategoriesSection.fxml"));
            mainPane.getChildren().clear();
            mainPane.getChildren().add(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void AddAdsButton(ActionEvent event) {
        try {
            Pane pane = FXMLLoader.load(Main.class.getResource("Section/AddAdsSection.fxml"));
            mainPane.getChildren().clear();
            mainPane.getChildren().add(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void ChatButton(ActionEvent event) {
        try {
            Pane pane = FXMLLoader.load(Main.class.getResource("Section/ChatSection.fxml"));
            mainPane.getChildren().clear();
            mainPane.getChildren().add(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void MyDivarButton(ActionEvent event) {
        try {
            Pane pane = FXMLLoader.load(Main.class.getResource("Section/MyDivarSection.fxml"));
            mainPane.getChildren().clear();
            mainPane.getChildren().add(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
