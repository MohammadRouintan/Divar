package com.example.admin.Dashboard;

import com.example.admin.socket.GetInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class showPost {

    public ArrayList<String> getStringArray (JSONArray JArray) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < JArray.length(); i++) {
            list.add(JArray.getString(i));
        }
        return list;
    }


    @FXML
    public void initialize(){

        String post = null;

        for (int i = 0; i < GetInfo.Posts.size(); i++) {
            JSONObject json = new JSONObject(GetInfo.Posts.get(i));
            if(json.getInt("postId") == GetInfo.postID){
                post = GetInfo.Posts.get(i);
            }
        }

        JSONObject json = new JSONObject(post);

        ArrayList<String> imagesName = getStringArray(json.getJSONArray("imageName"));

        for (int i = 0; i < imagesName.size() ; i++) {
            GetInfo.reSaveFile(imagesName.get(i));
            switch (i){
                case 0:
                    img0.setImage(new Image(""));
                    break;
                case 1:
                    img1.setImage(new Image(""));
                    break;
                case 2:
                    img2.setImage(new Image(""));
                    break;
                case 3:
                    img3.setImage(new Image(""));
                    break;
                case 4:
                    img4.setImage(new Image(""));
                    break;
                case 5:
                    img5.setImage(new Image(""));
                    break;
                case 6:
                    img6.setImage(new Image(""));
                    break;
                case 7:
                    img7.setImage(new Image(""));
                    break;
                case 8:
                    img8.setImage(new Image(""));
                    break;
            }
        }

        title.setText(json.getString("title"));
        bio.setText(json.getString("bio"));
        price.setText(json.getString("price"));
        mainBranch.setText(json.getString("branchMain"));
        Branch2.setText(json.getString("branch1"));
        city.setText(json.getString("city"));
        address.setText(json.getString("address"));
        phoneNumber.setText(json.getString("phoneNumber"));

        if(json.getBoolean("exchange") == true){
            Exchange.setSelected(true);
        }

        if(json.getBoolean("auction") == true){
            auction.setSelected(true);
        }

        if(json.getBoolean("agreement") == true){
            agreement.setSelected(true);
        }

        ArrayList<String> rowName = getStringArray(json.getJSONArray("rowName"));
        ArrayList<String> rowValue = getStringArray(json.getJSONArray("rowValue"));

        for (int i = 1; i <= rowName.size() ; i++) {
            switch (i){
                case 1:
                    rowName1.setText(rowName.get(i));
                    rowValue1.setText(rowValue.get(i));
                    break;
                case 2:
                    rowName2.setText(rowName.get(i));
                    rowValue2.setText(rowValue.get(i));
                    break;
                case 3:
                    rowName3.setText(rowName.get(i));
                    rowValue3.setText(rowValue.get(i));
                    break;
                case 4:
                    rowName4.setText(rowName.get(i));
                    rowValue4.setText(rowValue.get(i));
                    break;
                case 5:
                    rowName5.setText(rowName.get(i));
                    rowValue5.setText(rowValue.get(i));
                    break;
                case 6:
                    rowName6.setText(rowName.get(i));
                    rowValue6.setText(rowValue.get(i));
                    break;
            }
        }


        ArrayList<String> columnName = getStringArray(json.getJSONArray("columnName"));
        ArrayList<String> columnValue = getStringArray(json.getJSONArray("columnValue"));

        for (int i = 1; i <= columnValue.size() ; i++) {
            switch (i){
                case 1:
                    ColumnName1.setText(columnName.get(i));
                    columnValue1.setText(columnValue.get(i));
                    break;
                case 2:
                    ColumnName2.setText(columnName.get(i));
                    columnValue2.setText(columnValue.get(i));
                    break;
                case 3:
                    ColumnName3.setText(columnName.get(i));
                    columnValue3.setText(columnValue.get(i));
                    break;
            }
        }

    }

    @FXML
    private Label Branch2;

    @FXML
    private TextField ColumnName1;

    @FXML
    private TextField ColumnName2;

    @FXML
    private TextField ColumnName3;

    @FXML
    private CheckBox Exchange;

    @FXML
    private Label address;

    @FXML
    private CheckBox agreement;

    @FXML
    private CheckBox auction;

    @FXML
    private TextArea bio;

    @FXML
    private Label city;

    @FXML
    private TextField columnValue1;

    @FXML
    private TextField columnValue2;

    @FXML
    private TextField columnValue3;

    @FXML
    private ImageView img0;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img4;

    @FXML
    private ImageView img5;

    @FXML
    private ImageView img6;

    @FXML
    private ImageView img7;

    @FXML
    private ImageView img8;

    @FXML
    private Label mainBranch;

    @FXML
    private Label phoneNumber;

    @FXML
    private Label price;

    @FXML
    private TextField rowName1;

    @FXML
    private TextField rowName2;

    @FXML
    private TextField rowName3;

    @FXML
    private TextField rowName4;

    @FXML
    private TextField rowName5;

    @FXML
    private TextField rowName6;

    @FXML
    private TextField rowValue1;

    @FXML
    private TextField rowValue2;

    @FXML
    private TextField rowValue3;

    @FXML
    private TextField rowValue4;

    @FXML
    private TextField rowValue5;

    @FXML
    private TextField rowValue6;

    @FXML
    private Label title;

    @FXML
    void accept(ActionEvent event) {
        GetInfo.acceptPost(GetInfo.postID);
    }

    @FXML
    void delete(ActionEvent event) {
        GetInfo.deletePost(GetInfo.postID);
    }
}
