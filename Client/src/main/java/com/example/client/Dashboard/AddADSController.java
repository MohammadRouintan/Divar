package com.example.client.Dashboard;

import com.example.client.socket.GetInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddADSController {

        private static int numberOFUploadedImage;
        @FXML
        public void initialize() {
                setMainCategories();
                setCity();
                HBox hBox;
                hBox = makeNewHBox();
                hBox.setId("featureRowTextField0");
                featureRowVbox.getChildren().add(hBox);

                VBox vBox;
                vBox = makeNewVBox();
                vBox.setId("featureColumnTextField0");
                featureColumnVbox.getChildren().add(vBox);


            setMainCategories();
            setCity();
            numberOFUploadedImage = 0;
            imagesName = new ArrayList<>();
        }

        private void setMainCategories(){
            String[] mainCategories = {"Digital Commodity", "Entertainment", "Equipment", "Estate", "Home Commodity", "Personal Items", "Recruitment", "Services", "Social", "Vehicles"};
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
        private VBox featureRowVbox;
        @FXML
        private VBox featureColumnVbox;
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
        private ComboBox<String> branchTwoCategories;

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
        private TextArea postDescriptionFiled;

        @FXML
        private TextField postPriceFiled;

        @FXML
        private ComboBox<String> selectCityComboBox;

        @FXML
        private Button uploadImage;

        @FXML
        void MainBranchCategotiesFunction(ActionEvent event) {
            setBranchTwoCategories();
        }

        private static int addFeatureRowCounter = 1;
        @FXML
        void addFeatureRow(ActionEvent event) {
            if(addFeatureRowCounter < 6) {
                HBox hBox = new HBox();
                hBox = makeNewHBox();
                hBox.setId("featureRowTextField"+addFeatureRowCounter);
                featureRowVbox.getChildren().add(hBox);
                addFeatureRowCounter++;
            }
        }
        @FXML
        void removeFeatureRow(ActionEvent event){
            if(addFeatureRowCounter > 1){
                featureRowVbox.getChildren().remove(0);
                addFeatureRowCounter--;
            }
        }
        private static int addFeatureColumnCounter = 1;
        @FXML
        void addFeatureColumn(ActionEvent event){
            if(addFeatureColumnCounter < 6){
                VBox vBox = new VBox();
                vBox = makeNewVBox();
                vBox.setId("featureColumnTextField"+addFeatureColumnCounter);
                featureColumnVbox.getChildren().add(vBox);
                addFeatureColumnCounter++;
            }
        }
        @FXML
        void removeFeatureColumn(ActionEvent event){
            if(addFeatureColumnCounter > 1){
                featureColumnVbox.getChildren().remove(0);
                addFeatureColumnCounter--;
            }
        }
        @FXML
        void addPost(ActionEvent event) {

            String mainBranch = MainBranchCategories.getValue();
            String branchTwo = branchTwoCategories.getValue();
            String city = selectCityComboBox.getValue();
            String address = addressTextFiled.getText();
            String name = nameTextFiled.getText();
            String description = postDescriptionFiled.getText();
            String price = postPriceFiled.getText();

            Pattern pattern = Pattern.compile("[0-9]+");
            Matcher matcher = pattern.matcher(price);

            if(!matcher.matches()){
                createErrorMassage("");
            }else if (mainBranch == null){
                createErrorMassage("");
            }else if(branchTwo == null){
                createErrorMassage("");
            }else if(city == null){
                createErrorMassage("");
            }else if(address.equals("")){
                createErrorMassage("");
            }else if(name.equals("")){
                createErrorMassage("");
            }else if(description.equals("")){
                createErrorMassage("");
            }else {

            }

        }

        @FXML
        void branchTwoCategoriesFunction(ActionEvent event) {
            MainBranchCategories.setValue("");
            branchTwoCategories.setValue("");
            selectCityComboBox.setValue("");
            addressTextFiled.setText("");
            nameTextFiled.setText("");
            postDescriptionFiled.setText("");
            postPriceFiled.setText("");
            img1.setImage(new Image(""));
        }

        @FXML
        void cancelPost(ActionEvent event) {

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
                        postPriceFiled.setEditable(false);
                        postPriceFiled.setText("");
                }else{
                        postPriceFiled.setEditable(true);
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
        private ArrayList<String> imagesName;

        @FXML
        void uploadImageFunction (ActionEvent event) throws IOException {
            file = chooser.showOpenDialog(null);
            if(file != null) {
                // GetInfo.sendFile(String.valueOf(Integer.parseInt(GetInfo.getLastNameImage()) + 1));
                imagesName.add(String.valueOf(Integer.parseInt(GetInfo.getLastNameImage()) + 1));
                priceLabel.setText(file.getPath());
                numberOFUploadedImage++;
                Image img;
                switch (numberOFUploadedImage) {
                    case 1:
                        img = new Image(file.toURI().toString());
                        img1.setImage(img);
                        break;
                    case 2:
                        img = new Image(file.toURI().toString());
                        img2.setImage(img);
                        break;
                    case 3:
                        img = new Image(file.toURI().toString());
                        img3.setImage(img);
                        break;
                    case 4:
                        img = new Image(file.toURI().toString());
                        img4.setImage(img);
                        break;
                    case 5:
                        img = new Image(file.toURI().toString());
                        img5.setImage(img);
                        break;
                    case 6:
                        img = new Image(file.toURI().toString());
                        img6.setImage(img);
                        break;
                    case 7:
                        img = new Image(file.toURI().toString());
                        img7.setImage(img);
                        break;
                    case 8:
                        img = new Image(file.toURI().toString());
                        img8.setImage(img);
                        break;
                    case 9:
                        img = new Image(file.toURI().toString());
                        img9.setImage(img);
                        break;
                }
            }
        }
        @FXML
        private Label errorLabel;

        public void createErrorMassage(String message){
            errorLabel.setText(message);
        }
        public void setBranchTwoCategories() {
            ObservableList<String> branchTwo = FXCollections.observableArrayList();
            String[] estate = {"ResidentialSales", "ResidentialRent", "OfficeSales", "OfficeRent", "ShortTermRent", "ConstructionProject"};
            String[] vehicles = {"Car", "CarSparePartsAndAccessories", "MotorcyclesAndAccessories", "BoatsAndAccessories"};
            String[] digitalCommodity = {"MobileAndTablet", "PC", "Console", "VideoAndAudio", "Telephone"};
            String[] homeCommodity = {"ElectricalAppliance", "kitchenware", "FoodAndDrink", "SewingAccessories", "Sofa", "Lighting", "Carpet", "Mattress", "DecorativeAccessories", "VentilationAppliances", "WashingSupplies", "BathroomUtensils"};
            String[] services = {"EngineAndMachine", "ReceptionAndCeremony", "ComputerAndMobileServices", "FinanceAndAccountingAndInsurance", "Transportation", "ProfessionAndSkill", "HairdressingAndBeauty", "entertainment", "cleaning", "GardeningAndTreePlanting", "educational"};
            String[] personalItems = {"BagsAndShoes", "Accessories", "Cosmetics", "KidsClothes", "Toy"};
            String[] entertainment = {"Ticket", "Tour", "Book", "Bike", "Animals", "Collection", "MusicInstrument", "Sport"};
            String[] social = {"Event", "Voluntarily", "Losts"};
            String[] equipment = {"BuildingMaterials", "Tools", "IndustrialMachinery", "BusinessEquipment", "Wholesale"};
            String[] recruitment = {"Management", "Caretaker", "Architect", "StoreServices", "ComputerAndIT", "Accountants", "Marketer", "Engineer", "Teacher", "Driver", "DoctorAndBeautician", "Artist"};

            String mainBranch = MainBranchCategories.getValue();
            switch (mainBranch) {
                case "Estate":
                    branchTwo.addAll(estate);
                    break;
                case "Vehicles":
                    branchTwo.addAll(vehicles);
                    break;
                case "Digital Commodity":
                    branchTwo.addAll(digitalCommodity);
                    break;
                case "Home Commodity":
                    branchTwo.addAll(homeCommodity);
                    break;
                case "Services":
                    branchTwo.addAll(services);
                    break;
                case "Personal Items":
                    branchTwo.addAll(personalItems);
                    break;
                case "Entertainment":
                    branchTwo.addAll(entertainment);
                    break;
                case "Social":
                    branchTwo.addAll(social);
                    break;
                case "Equipment":
                    branchTwo.addAll(equipment);
                    break;
                case "Recruitment":
                    branchTwo.addAll(recruitment);
                    break;
                default:
                    break;
            }

            branchTwoCategories.setItems(branchTwo);
        }
        private HBox makeNewHBox(){
            HBox hBox = new HBox();
            hBox.setPadding(new Insets(10, 0, 10, 0));
            hBox.setAlignment(Pos.CENTER);
            TextField nameTxt = new TextField();
            nameTxt.setMinHeight(35);
            nameTxt.setPrefWidth(110);
            nameTxt.setPrefHeight(35);
            TextField detailsTxt = new TextField();
            detailsTxt.setMinHeight(35);
            detailsTxt.setPrefWidth(285);
            detailsTxt.setPrefHeight(35);
            hBox.getChildren().add(nameTxt);
            hBox.getChildren().add(detailsTxt);
            return hBox;
        }
    private VBox makeNewVBox(){
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 0, 5, 0));
        vBox.setAlignment(Pos.CENTER);
        TextField nameTxt = new TextField();
        nameTxt.setMinHeight(35);
        nameTxt.setMaxWidth(150);
        TextField detailsTxt = new TextField();
        detailsTxt.setMinHeight(35);
        detailsTxt.setMaxWidth(345);
        vBox.getChildren().add(nameTxt);
        vBox.getChildren().add(detailsTxt);
        return vBox;
    }
}

