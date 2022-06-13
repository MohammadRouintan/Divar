package com.example.client.Dashboard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.util.ArrayList;


public class AddADSController {

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
        private ComboBox<?> selectCityComboBox;

        @FXML
        private Button uploadImage;

        @FXML
        void MainBranchCategotiesFunction(ActionEvent event) {
            setBranchTwoCategories();
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
                case "DigitalCommodity":
                    branchTwo.addAll(digitalCommodity);
                    break;
                case "Services":
                    branchTwo.addAll(services);
                    break;
                case "PersonalItems":
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

}

