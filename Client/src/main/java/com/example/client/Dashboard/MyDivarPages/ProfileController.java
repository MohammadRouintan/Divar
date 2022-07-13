package com.example.client.Dashboard.MyDivarPages;

import com.example.client.socket.GetInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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
        setCity();
        user = GetInfo.getUser();
    }

    private int profileName;

    @FXML
    private ImageView profileImage;

    @FXML
    private TextField textFiledFirstName;

    @FXML
    private TextField textFiledLastName;

    @FXML
    private ComboBox cityComboBox;
    @FXML
    private Label errorLabel;

    @FXML
    void uploadData(ActionEvent event) {

        String city = (String) cityComboBox.getValue();
        String firstName = textFiledFirstName.getText();
        String lastName = textFiledLastName.getText();

        if(city == null){
            setErrorMessage("Please set a City");
        } else if(firstName == null){
            setErrorMessage("Please enter your first name");
        } else if (lastName == null){
            setErrorMessage("Please enter your last name");
        } else {
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
            setErrorMessage("Edited successfully");
        }

    }

    private File file = null;
    private FileChooser chooser = new FileChooser();

    @FXML
    void uploadImageProfile(ActionEvent event) {
        file = chooser.showOpenDialog(null);
        if(file != null) {
            JSONObject json = new JSONObject(user);
            if (json.getInt("profileNameImage") != 0) {
                profileName = json.getInt("profileNameImage");
            } else {
                profileName = GetInfo.getLastNameProfileImage();
            }
            Image img = new Image(file.toURI().toString());
            profileImage.setImage(img);
        }
    }


    public void setErrorMessage(String message){
        if(message.equals("Edited successfully")){
            errorLabel.setVisible(true);
            errorLabel.setText(message);
            errorLabel.setStyle("-fx-background-color: #2ecc71");

        }else {
            errorLabel.setVisible(true);
            errorLabel.setText(message);
            errorLabel.setStyle("-fx-background-color: #e74c3c");
        }

    }

    private void setCity() {
        String[] cities = {"","Tehran", "Shiraz", "Mashhad", "Arak", "Ardabil", "Orumieh", "Esfahan", "Ahwaz", "Ilam", " Bojnord",
                "Bandar Abbas", "Bushehr", "Birjand", "Tabriz", "Khorramabad", "Rasht", "Zahedan", "Zanjan", "Sari", "Semnan",
                "Sanandaj", "Shahr e Kord", "Qazvin", "Qom", "Karaj", "Kerman", "Kermanshah", "Gorgan", "Hamedan", "Yasuj", "Yazd"};

        ObservableList<String> temp = FXCollections.observableArrayList();
        temp.addAll(cities);
        cityComboBox.setItems(temp);
    }
}
