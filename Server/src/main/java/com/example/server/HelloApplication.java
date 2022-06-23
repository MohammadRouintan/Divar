package com.example.server;

import com.example.server.Database.Database;
import com.example.server.socket.AcceptClients;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Database.imageID = Integer.parseInt(Database.lastImageIDFromDatabase());
        Database.profileImageID = Integer.parseInt(Database.lastUserImageId());
        AcceptClients acceptClients = new AcceptClients();
        acceptClients.run();
        launch();
    }
}