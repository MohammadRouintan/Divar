package com.example.admin.Dashboard;

import com.example.admin.Main;
import com.example.admin.socket.GetInfo;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.JSONObject;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dashboard {

    @FXML
    private TableColumn<Table, String> cityColunmBox;

    @FXML
    private TableColumn<Table, String> mainBranchColumnBox;

    @FXML
    private TableView<Table> table;

    @FXML
    private TableColumn<Table, String> timeColumnBox;

    @FXML
    private TableColumn<Table, String> titleColumnBox;

    @FXML
    private Label userOnlineCount;

    @FXML
    private TableColumn<Table, String> usernameColumnBox;

    @FXML
    private TableColumn<Table, Integer> postIDColumnBox;

    private static int a = 0;

    @FXML
    public void initialize(ActionEvent event){

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        ArrayList<String> posts = GetInfo.Posts;

        for (int i = 0; i < posts.size(); i++) {

            JSONObject object = new JSONObject(posts.get(i));
            usernameColumnBox.setCellValueFactory(new PropertyValueFactory<Table ,String>("username"));
            titleColumnBox.setCellValueFactory(new PropertyValueFactory<Table ,String>("title"));
            timeColumnBox.setCellValueFactory(new PropertyValueFactory<Table ,String>("time"));
            cityColunmBox.setCellValueFactory(new PropertyValueFactory<Table ,String>("city"));
            mainBranchColumnBox.setCellValueFactory(new PropertyValueFactory<Table ,String>("mainBranch"));
            postIDColumnBox.setCellValueFactory(new PropertyValueFactory<Table ,Integer>("postID"));
            List<Table> list = new ArrayList<>();

            Table temp = new Table(object.getString("time") ,object.getString("city") ,object.getString("username") ,object.getString("mainBranch") ,object.getString("title") ,object.getInt("postId"));

            list.add(temp);

            table.getItems().addAll(list);

        }

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if (newSelection != null) {
                try {
                    layout = FXMLLoader.load(Main.class.getResource("showPost.fxml"));
                    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(layout);
                    stage.setTitle("showPost");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }



    Timeline timeline = new Timeline(new KeyFrame(
            Duration.seconds(1) , e ->{
                userOnlineCount.setText(String.valueOf(GetInfo.getUserOnlineCount()));
    }
    ));


    private Stage stage;
    private Scene scene;
    private Parent layout;

    @FXML
    public void refreshButton(ActionEvent event){
        initialize(event);
    }

}
