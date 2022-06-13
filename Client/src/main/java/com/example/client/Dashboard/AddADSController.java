package com.example.client.Dashboard;

import com.example.client.socket.Connect;
import com.example.client.socket.GetInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;


public class AddADSController {

        @FXML
        public void initialize() {
                setMainCategories();
                setCity();
        }

        private void setMainCategories(){
                String[] mainCategories = {"Digital Commodity" ,"Entertainment" ,"Equipment" ,"Estate" ,"Home Commodity" ,"Personal Items " ,"Recruitment" ,"Services" ,"Social" ," Vehicles"};
                ObservableList<String> temp = FXCollections.observableArrayList();
                temp.addAll(mainCategories);
                MainBranchCategories.setItems(temp);
        }

        private void setCity(){
                String[] cityes = {"Tehran" ,"Shiraz" ,"Mashhad" ,"Arak" ,"Ardabil" ,"Orumieh" ,"Esfahan" ,"Ahwaz" ,"Ilam" ," Bojnord" ,
                        "Bandar Abbas" ,"Bushehr" ,"Birjand" ,"Tabriz" ,"Khorramabad" ,"Rasht" ,"Zahedan" ,"Zanjan" ,"Sari" ,"Semnan" ,"Sanandaj" ,"Shahr e Kord" ,"Qazvin" ,"Qom" ,"Karaj" ,"Kerman" ,"Kermanshah" ,"Gorgan" ,"Hamedan" ,"Yasuj" ,"Yazd"};
                ObservableList<String> temp = FXCollections.observableArrayList();
                temp.addAll(cityes);
                selectCityComboBox.setItems(temp);
        }

        @FXML
        private ComboBox<String> MainBranchCategories;

        @FXML
        private Button addFeatureColumn;

        @FXML
        private TextField addressTextFiled;

        @FXML
        private CheckBox agreedPriceCheckBox;

        @FXML
        private CheckBox auctionCheckBox;

        @FXML
        private ComboBox<?> branchThreeCategories;

        @FXML
        private ComboBox<?> branchTwoCategories;

        @FXML
        private CheckBox exchangeCheckBox;

        @FXML
        private TextArea featureColumnName;

        @FXML
        private TextArea featureColumnValue;

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
        private ImageView img9;

        @FXML
        private TextField nameTextFiled;

        @FXML
        private TextArea psotDescriptionFiled;

        @FXML
        private TextField psotPriceFiled;

        @FXML
        private ComboBox<String> selectCityComboBox;

        @FXML
        private Button uploadImage;

        @FXML
        void MainBranchCategotiesFunction(ActionEvent event) {

        }

        @FXML
        void addFeatureRow(ActionEvent event) {

        }

        @FXML
        void addPost(ActionEvent event) {

        }

        @FXML
        void branchTwoCategoriesFunction(ActionEvent event) {

        }

        @FXML
        void canclePost(ActionEvent event) {

        }

        @FXML
        void featureRowName(ActionEvent event) {

        }

        @FXML
        void featureValueRow(ActionEvent event) {

        }

        @FXML
        void selectCityComboBoxFunction(ActionEvent event) {

        }

        @FXML
        void agreedPriceCheckBoxFunction(ActionEvent event){
                if(agreedPriceCheckBox.isSelected()){
                        psotPriceFiled.setEditable(false);
                        psotPriceFiled.setText("");
                }else{
                        psotPriceFiled.setEditable(true);
                }
        }

        @FXML
        private Label priceLabel;

        @FXML
        void auctionCheckBoxFunction(ActionEvent event){
                if(auctionCheckBox.isSelected()){
                        priceLabel.setText("Base Price : ");
                }else{
                        priceLabel.setText("Price : ");
                }
        }

        private FileChooser chooser = new FileChooser();
        private File file = null;

        @FXML
        void uploadImageFunction (ActionEvent event) throws IOException {
                ArrayList<String> imagesName = new ArrayList<>();
                choicePhoto();
        }

        void choicePhoto() throws IOException {
                file = chooser.showOpenDialog(null);
                if(file != null){
                        FileInputStream in = new FileInputStream(file.getPath());
                        GetInfo.sendFile(String.valueOf(Integer.parseInt(GetInfo.getLastNameImage()) + 1));
                        in.close();

                }
        }
}

