package com.example.client.Dashboard.MyDivarPages;

import com.example.client.socket.GetInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class ProfileController {

    private String user;

    @FXML
    public void initialize() {
        user = GetInfo.getUser();
    }

    private int profileName;

    @FXML
    private ImageView profileImage;

    @FXML
    private TextField textFiledCity;

    @FXML
    private TextField textFiledFirstName;

    @FXML
    private TextField textFiledLastName;

    @FXML
    private Label errorMessage;

    @FXML
    void uploadData(ActionEvent event) {

        String city = textFiledCity.getText();
        String firstName = textFiledFirstName.getText();
        String lastName = textFiledLastName.getText();

        if(city == null){
            setErrorMessage("");
        }else if(firstName == null){
            setErrorMessage("");
        }else if (lastName == null){
            setErrorMessage("");
        }else{
            ArrayList<String> keys = new ArrayList<>();
            ArrayList<Object> values = new ArrayList<>();
            keys.add("city");
            keys.add("lastName");
            keys.add("firstName");
            keys.add("profileNameImage");
            values.add(city);
            values.add(firstName);
            values.add(lastName);
            values.add(profileName);

            GetInfo.updateUser(keys ,values);
            GetInfo.sendProfile(file.getPath().toString() ,profileName);
        }

    }

    private File file;
    private FileChooser chooser = new FileChooser();

    @FXML
    void uploadImageProfile(ActionEvent event) {
        File file = chooser.showOpenDialog(null);
        if(file != null) {
            try{
                JSONObject json = new JSONObject(user);
                profileName = json.getInt("profileNameImage");
            }catch (Exception e){
                profileName = GetInfo.getLastNameProfileImage();
            }
            Image img = new Image(file.toURI().toString());
            profileImage.setImage(img);
        }
    }

    public void setErrorMessage(String message){
        errorMessage.setText(message);
    }

}
