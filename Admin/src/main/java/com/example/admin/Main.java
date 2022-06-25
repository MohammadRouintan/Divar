package com.example.admin;

import com.example.admin.socket.DataInput;
import com.example.admin.socket.DataOutput;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 5575);
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
        DataOutput dataOutput = new DataOutput(socket);
        dataOutput.start();
        DataInput dataInput = new DataInput(socket);
        dataInput.start();
        launch();
    }
}