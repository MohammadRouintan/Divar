package com.example.client.Dashboard;

import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class AdsController {
    @FXML
    private HBox searchHbox;
    @FXML
    private ComboBox City;
    @FXML
    private TextField searchTextbox;
    @FXML
    private ComboBox<String> mainBranchCategories;
    @FXML
    private ComboBox<String> branchTwoCategories;
    @FXML
    private TextField priceFrom;
    @FXML
    private TextField priceTo;
    @FXML
    private Pagination pagination;

    @FXML
    public void initialize(){
        setMainCategories();
        setCity();

    }
    private void setMainCategories(){
        String[] mainCategories = {"Digital Goods", "Entertainment", "Equipment", "Estate", "Home Goods", "Personal Items", "Recruitment", "Services", "Social", "Vehicles"};
        ObservableList<String> temp = FXCollections.observableArrayList();
        temp.addAll(mainCategories);
        mainBranchCategories.setItems(temp);
    }

    private void setCity(){
        String[] cityes = {"Tehran" ,"Shiraz" ,"Mashhad" ,"Arak" ,"Ardabil" ,"Orumieh" ,"Esfahan" ,"Ahwaz" ,"Ilam" ," Bojnord" ,
                "Bandar Abbas" ,"Bushehr" ,"Birjand" ,"Tabriz" ,"Khorramabad" ,"Rasht" ,"Zahedan" ,"Zanjan" ,"Sari" ,"Semnan" ,"Sanandaj" ,"Shahr e Kord" ,"Qazvin" ,"Qom" ,"Karaj" ,"Kerman" ,"Kermanshah" ,"Gorgan" ,"Hamedan" ,"Yasuj" ,"Yazd"};
        ObservableList<String> temp = FXCollections.observableArrayList();
        temp.addAll(cityes);
        City.setItems(temp);
    }

    @FXML
    void MainBranchCategotiesFunction(ActionEvent event) {
        setBranchTwoCategories();
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

        String mainBranch = mainBranchCategories.getValue();
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

    @FXML
    private void searchButton(){

    }

}

