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
        try{
            JSONObject json = new JSONObject(user);
            String lastName = json.getString("lastName");
            String firstName = json.getString("firstName");
            String city = json.getString("city");
            firstNameLabel.setText(firstName);
            lastNameLabel.setText(lastName);
            cityLabel.setText(city);
        }catch (Exception e){
            firstNameLabel.setText("null");
            lastNameLabel.setText("null");
            cityLabel.setText("null");
        }
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
    private Label lastNameLabel;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label cityLabel;

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
            ArrayList<String> updateUserKeys = new ArrayList<>();
            ArrayList<Object> updateUserValues = new ArrayList<>();
            updateUserKeys.add("city");
            updateUserKeys.add("lastName");
            updateUserKeys.add("firstName");
            updateUserKeys.add("profileNameImage");
            updateUserValues.add(city);
            updateUserValues.add(firstName);
            updateUserValues.add(lastName);
            updateUserValues.add(profileName);

            GetInfo.updateUser(updateUserKeys ,updateUserValues);
            GetInfo.sendProfile(file.getPath().toString() ,String.valueOf(profileName));
        }

    }

    private File file = null;
    private FileChooser chooser = new FileChooser();

    @FXML
    void uploadImageProfile(ActionEvent event) {
        file = chooser.showOpenDialog(null);
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
