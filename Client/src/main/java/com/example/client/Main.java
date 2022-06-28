package com.example.client;

import com.example.client.Dashboard.MyDivarPages.MyAdsController;
import com.example.client.socket.GetInfo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login/entrance.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),800,600);
        stage.setMinHeight(600);
        stage.setMinWidth(800);
        stage.setTitle("Divar");
        stage.setScene(scene);
//        stage.setOnCloseRequest(event -> {
//            GetInfo.disconnect();
//
//        });
        stage.show();
    }
    public static void main(String[] args) {
        launch();
        GetInfo.disconnect();
    }
}