package com.example.client.Dashboard;

import com.example.client.socket.Connect;
import com.example.client.socket.GetInfo;
import com.example.client.socket.ImageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import org.json.JSONObject;

import java.io.*;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddADSController {

        private static int numberOFUploadedImage;
        @FXML
        public void initialize() {
            setMainCategories();
            setCity();
            HBox hBox;
            hBox = makeNewHBox(0);
            hBox.setId("featureRowTextField0");
            featureRowVbox.getChildren().add(hBox);

            VBox vBox;
            vBox = makeNewVBox(0);
            vBox.setId("featureColumnTextField0");
            featureColumnVbox.getChildren().add(vBox);


            setMainCategories();
            setCity();
            numberOFUploadedImage = 0;
            imagesName = new ArrayList<>();
            imagesPath = new ArrayList<>();
        }

        private void setMainCategories(){
            String[] mainCategories = {"Digital Goods", "Entertainment", "Equipment", "Estate", "Home Goods", "Personal Items", "Recruitment", "Services", "Social", "Vehicles"};
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
                hBox = makeNewHBox(addFeatureRowCounter);
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
                vBox = makeNewVBox(addFeatureColumnCounter);
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
        private TextField RowName0 ,RowName1 ,RowName2 ,RowName3 ,RowName4 ,RowName5;
        @FXML
        private TextField RowValue0 ,RowValue1 ,RowValue2 ,RowValue3 ,RowValue4 ,RowValue5;
        @FXML
        private TextField ColumnName0 ,ColumnName1 ,ColumnName2 ,ColumnName3 ,ColumnName4 ,ColumnName5;
        @FXML
        private TextField ColumnValue0 ,ColumnValue1 ,ColumnValue2 ,ColumnValue3 ,ColumnValue4 ,ColumnValue5;

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
                createErrorMassage("Price is invalid !!");
            }else if (mainBranch == null){
                createErrorMassage("Please select MainBranch !!");
            }else if(branchTwo == null){
                createErrorMassage("Please select BranchTwo !!");
            }else if(city == null){
                createErrorMassage("Please select city !!");
            }else if(address.equals("")){
                createErrorMassage("Please write address correctly !!");
            }else if(name.equals("")){
                createErrorMassage("Please write name correctly !!");
            }else if(description.equals("")){
                createErrorMassage("Please write description correctly !!");
//            }//else if(RowName0.getText().equals("") || RowValue0.getText().equals("")){
//                createErrorMassage("");
//            }//else if(ColumnName0.getText().equals("") || ColumnValue0.getText().equals("")){
//                createErrorMassage("");
            }else {
                ArrayList<String> RowName = new ArrayList<>();
                ArrayList<String> RowValue = new ArrayList<>();
                ArrayList<String> ColumnName = new ArrayList<>();
                ArrayList<String> ColumnValue = new ArrayList<>();

                for (int i = 0; i < addFeatureColumnCounter; i++) {
                    switch (i) {
                        case 0:
                            ColumnName.add(getColumnName(0));
                            ColumnValue.add(getColumnValue(0));
                            break;
                        case 1:
                            ColumnName.add(getColumnName(1));
                            ColumnValue.add(getColumnValue(1));
                            break;
                        case 2:
                            ColumnName.add(getColumnName(2));
                            ColumnValue.add(getColumnValue(2));
                            break;
                        case 3:
                            ColumnName.add(getColumnName(3));
                            ColumnValue.add(getColumnValue(3));
                            break;
                        case 4:
                            ColumnName.add(getColumnName(4));
                            ColumnValue.add(getColumnValue(4));
                            break;
                        case 5:
                            ColumnName.add(getColumnName(5));
                            ColumnValue.add(getColumnValue(5));
                            break;
                    }
                }

                for (int i = 0; i < addFeatureRowCounter; i++) {
                    switch (i) {
                        case 0:
                            RowName.add(getRowName(0));
                            RowValue.add(getRowValue(0));
                            break;
                        case 1:
                            RowName.add(getRowName(1));
                            RowValue.add(getRowValue(1));
                            break;
                        case 2:
                            RowName.add(getRowName(2));
                            RowValue.add(getRowValue(2));
                            break;
                        case 3:
                            RowName.add(getRowName(3));
                            RowValue.add(getRowValue(3));
                            break;
                        case 4:
                            RowName.add(getRowName(4));
                            RowValue.add(getRowValue(4));
                            break;
                        case 5:
                            RowName.add(getRowName(5));
                            RowValue.add(getRowValue(5));
                            break;
                    }
                }


                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("title", name);
                jsonObject.put("branchMain", mainBranch);
                jsonObject.put("branch1", branchTwo);
                jsonObject.put("phoneNumber", Connect.getPhoneNumber());
                jsonObject.put("bio", description);
                jsonObject.put("imageName", imagesName);
                jsonObject.put("city", city);
                jsonObject.put("address", address);
                jsonObject.put("time", dtf.format(now));
                jsonObject.put("accept", false);
                jsonObject.put("exchange", exchangeCheckBox.isSelected());
                jsonObject.put("agreement", agreedPriceCheckBox.isSelected());
                jsonObject.put("auction", auctionCheckBox.isSelected());
                jsonObject.put("price", price);
                jsonObject.put("RowName", RowName);
                jsonObject.put("RowValue", RowValue);
                jsonObject.put("ColumnName", ColumnName);
                jsonObject.put("ColumnValue", ColumnValue);

                GetInfo.addPost(jsonObject);
                for (int i = 0; i < imagesName.size(); i++) {
                    ImageController image = new ImageController(imagesPath.get(i) ,imagesName.get(i), 2);
                    image.start();
                }
            }
        }

        @FXML
        void branchTwoCategoriesFunction(ActionEvent event) {
        }

        @FXML
        void cancelPost(ActionEvent event) {
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
                    auctionCheckBox.setSelected(false);
                    auctionCheckBox.setDisable(true);
                    postPriceFiled.setEditable(false);
                    postPriceFiled.setText("");
                }else{
                    auctionCheckBox.setDisable(false);
                    postPriceFiled.setEditable(true);
                }
        }

        @FXML
        private Label priceLabel;

        @FXML
        void auctionCheckBoxFunction(ActionEvent event){
                if(auctionCheckBox.isSelected()){
                    agreedPriceCheckBox.setSelected(false);
                    agreedPriceCheckBox.setDisable(true);
                    exchangeCheckBox.setSelected(false);
                    exchangeCheckBox.setDisable(true);
                    priceLabel.setText("Base Price : ");
                }else{
                    agreedPriceCheckBox.setDisable(false);
                    exchangeCheckBox.setDisable(false);
                    priceLabel.setText("Price : ");
                }

        }

        private FileChooser chooser = new FileChooser();
        private File file = null;
        private ArrayList<String> imagesName;
        private ArrayList<String> imagesPath;

        @FXML
        void uploadImageFunction (ActionEvent event) throws IOException {
            file = chooser.showOpenDialog(null);
            if(file != null) {
                // GetInfo.sendFile(String.valueOf(Integer.parseInt(GetInfo.getLastNameImage()) + 1));
                imagesName.add(String.valueOf(GetInfo.getLastImageName()));
                imagesPath.add(file.getPath().toString());
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
            System.out.println("a");
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
        private HBox makeNewHBox(int counter){
            HBox hBox = new HBox();
            hBox.setPadding(new Insets(10, 0, 10, 0));
            hBox.setAlignment(Pos.CENTER);
            TextField nameTxt = new TextField();
            nameTxt.setMinHeight(35);
            nameTxt.setPrefWidth(110);
            nameTxt.setPrefHeight(35);
            nameTxt.setId("RowName" + counter);
            TextField detailsTxt = new TextField();
            detailsTxt.setMinHeight(35);
            detailsTxt.setPrefWidth(285);
            detailsTxt.setPrefHeight(35);
            detailsTxt.setId("RowValue" + counter);
            hBox.getChildren().add(nameTxt);
            hBox.getChildren().add(detailsTxt);
            return hBox;
        }
    private VBox makeNewVBox(int counter){
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 0, 5, 0));
        vBox.setAlignment(Pos.CENTER);
        TextField nameTxt = new TextField();
        nameTxt.setId("ColumnName" + counter);
        nameTxt.setMinHeight(35);
        nameTxt.setMaxWidth(150);
        TextField detailsTxt = new TextField();
        detailsTxt.setId("ColumnValue" + counter);
        detailsTxt.setMinHeight(35);
        detailsTxt.setMaxWidth(345);
        vBox.getChildren().add(nameTxt);
        vBox.getChildren().add(detailsTxt);
        return vBox;
    }

    private String getRowName(int number) {
        HBox hBox = (HBox) featureRowVbox.getChildren().get(number);
        TextField textField = (TextField) hBox.getChildren().get(0);
        return textField.getText();
    }

    private String getRowValue(int number) {
        HBox hBox = (HBox) featureRowVbox.getChildren().get(number);
        TextField textField = (TextField) hBox.getChildren().get(1);
        return textField.getText();
    }

    private String getColumnName(int number) {
        VBox vBox = (VBox) featureColumnVbox.getChildren().get(number);
        TextField textField = (TextField) vBox.getChildren().get(0);
        return textField.getText();
    }

    private String getColumnValue(int number) {
        VBox vBox = (VBox) featureColumnVbox.getChildren().get(number);
        TextField textField = (TextField) vBox.getChildren().get(1);
        return textField.getText();
    }
}

