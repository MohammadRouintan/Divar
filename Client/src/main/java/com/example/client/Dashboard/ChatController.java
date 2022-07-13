package com.example.client.Dashboard;

import com.example.client.socket.Connect;
import com.example.client.socket.GetInfo;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class ChatController {
    public static boolean flag;
    @FXML
    private Circle circleImage;
    @FXML
    private VBox Users;
    @FXML
    private Circle imageProfile;
    @FXML
    private Label nameProfile;
    @FXML
    private VBox chatBox;
    @FXML
    private TextField textBox;
    @FXML
    private Button sendButton;
    @FXML
    private ScrollPane scrollPane;

    public ArrayList<String> getStringArray (JSONArray JArray) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < JArray.length(); i++) {
            list.add(JArray.getString(i));
        }
        return list;
    }

    public ArrayList<Boolean> getBooleanArray (JSONArray JArray) {
        ArrayList<Boolean> list = new ArrayList<>();
        for (int i = 0; i < JArray.length(); i++) {
            list.add(JArray.getBoolean(i));
        }
        return list;
    }

    public ArrayList<Integer> getIntArray (JSONArray JArray) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < JArray.length(); i++) {
            list.add(JArray.getInt(i));
        }
        return list;
    }

    @FXML
    private Label text;


    @FXML
    public void initialize(){

        String chats = GetInfo.getChatCount(GetInfo.phoneNumber);

        JSONObject json = new JSONObject(chats);

        ArrayList<Integer> size = getIntArray(json.getJSONArray("size"));
        ArrayList<String> user2 = getStringArray(json.getJSONArray("partner"));
        ArrayList<String> imgs = getStringArray(json.getJSONArray("imageName"));

        for (int i = 0; i < user2.size(); i++) {
            HBox hBox = AddUsers(user2.get(i) ,imgs.get(i) ,size.get(i));
            Users.getChildren().add(hBox);
        }
        String chats1 = GetInfo.getChatCount(GetInfo.phoneNumber);

        JSONObject json1 = new JSONObject(chats);

//            ArrayList<Integer> size1 = getIntArray(json.getJSONArray("size"));
//            ArrayList<String> user = getStringArray(json.getJSONArray("partner"));
//            ArrayList<String> imgs1 = getStringArray(json.getJSONArray("imageName"));
//
//            for
//
//            AddUsers(user ,imgs1 ,size1);

        new Thread(() -> {
            while(true) {
                try {
                    String message = Connect.messageDIS.readUTF();
                    String senderNumber = Connect.messageDIS.readUTF();
                    if(currentNumber.equals(senderNumber)){
                        makeMassageHbox(0, true, message);
                    } else {
                        boolean isOldUser = false;
                        for (int i = 0; i < user2.size(); i++) {
                            if(user2.get(i).equals(senderNumber)){
                                isOldUser = true;
                                AddUsers(senderNumber, imgs.get(i), size.get(i) + 1);
                            }
                        }
                        if(!isOldUser){
                            AddUsers(senderNumber, "", 1);
                        }
                    }
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

    @FXML
    private void sendButton(){

    }

    private HBox AddUsers(String phoneNumber ,String imgName ,int newMessageCount){

        HBox userHBox = null;
        if(GetInfo.isMessageExists(GetInfo.phoneNumber ,phoneNumber)) {

            userHBox = new HBox();
            userHBox.setAlignment(Pos.CENTER_LEFT);
            userHBox.setMinHeight(56);
            userHBox.setMinWidth(194);
            userHBox.setPrefHeight(56);
            userHBox.setPrefWidth(56);
            userHBox.setCursor(Cursor.HAND);

            Circle userImg = new Circle();
            userImg.setRadius(24);
            File file = new File("../Client/src/main/resources/postImage/1.jpg");
            Image img = new Image(file.toURI().toString());
            userImg.setFill(new ImagePattern(img));

            Label userName = new Label(phoneNumber);
            userName.setAlignment(Pos.CENTER);
            userName.setPrefWidth(114);
            userName.setPrefHeight(52);
            Label counterOfMassages = new Label();

            if(newMessageCount != 0) {
                counterOfMassages.setText(String.valueOf(newMessageCount));
                counterOfMassages.setTextFill(Color.web("#ff0000"));
                counterOfMassages.setAlignment(Pos.CENTER);
                counterOfMassages.setPrefWidth(25);
                counterOfMassages.setPrefHeight(52);
            }else{
                counterOfMassages.setVisible(false);
            }

            userHBox.getChildren().addAll(userImg, userName, counterOfMassages);
        }
        userHBox.setOnMouseClicked(event -> ShowMassages(phoneNumber));
        return userHBox;
    }
    private static String currentNumber;

    private void ShowMassages(String phoneNumber){
        currentNumber = phoneNumber;
        JSONObject json = new JSONObject(GetInfo.getChat(GetInfo.phoneNumber ,phoneNumber));

        ArrayList<String> Messages = getStringArray(json.getJSONArray("messages"));
        ArrayList<String> sender = getStringArray(json.getJSONArray("sender"));
        ArrayList<Boolean> seen =  getBooleanArray(json.getJSONArray("seenMessage"));

        for (int i = 0; i < Messages.size() ; i++) {
            int sender1 = 0;
            if(sender.get(i).equals(phoneNumber)){
                sender1 = 1;
            }else{
                sender1 = 0;
            }
            HBox hBox1 = makeMassageHbox(sender1,seen.get(i),Messages.get(i));
            chatBox.getChildren().addAll(hBox1,hBox1);
        }

        int index = -1;

        for (int i = 0; i < sender.size(); i++) {
            if(sender.get(i).equals(GetInfo.phoneNumber)){
                index = i;
                break;
            }
        }

        for (int i = seen.size() - 1; i >= index ; i--) {
            seen.add(true);
        }

    }


    private HBox makeMassageHbox(int user,boolean seen,String text){
        HBox hBox = new HBox();
        hBox.setSpacing(10);


        Circle profileImg = new Circle();
        profileImg.setRadius(13);
        File file = new File("../Client/src/main/resources/postImage/2.jpg");
        Image img = new Image(file.toURI().toString());
        profileImg.setFill(new ImagePattern(img));

        VBox massageVbox = new VBox();
        massageVbox.setStyle("-fx-background-color:#fff;" +
                "-fx-background-radius:10;"
        );
        massageVbox.setPadding(new Insets(5));
        Text textMassage = new Text(text);
        textMassage.setWrappingWidth(280);


        ImageView tickImage = new ImageView();
        tickImage.setFitWidth(20);
        tickImage.setFitHeight(20);

        if(!seen){
            File file1 = new File("../Client/src/main/resources/com/example/image/tick.png");
            Image img1 = new Image(file1.toURI().toString());
            tickImage.setImage(img1);
        }
        else {
            File file2 = new File("../Client/src/main/resources/com/example/image/twotick.png");
            Image img2 = new Image(file2.toURI().toString());
            tickImage.setImage(img2);
        }

        massageVbox.getChildren().addAll(textMassage,tickImage);
        if(user == 0){
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setPadding(new Insets(0, 0, 0, 5));
            hBox.getChildren().addAll(profileImg, massageVbox);
            massageVbox.setAlignment(Pos.CENTER_LEFT);
        }
        else {
            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setPadding(new Insets(0, 5, 0, 0));
            hBox.getChildren().addAll(massageVbox, profileImg);
            massageVbox.setAlignment(Pos.CENTER_RIGHT);
        }

        hBox.setMargin(massageVbox,new Insets(10, 0, 10, 0));
        return hBox;
    }
}