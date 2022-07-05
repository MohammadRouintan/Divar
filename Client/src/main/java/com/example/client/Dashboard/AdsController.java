package com.example.client.Dashboard;

import com.example.client.Dashboard.Posts.NewPage;
import com.example.client.socket.GetInfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class AdsController {
    @FXML
    private HBox searchHbox;
    @FXML
    private ComboBox<String> City;
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

    private List<VBox> PagesList = new ArrayList<VBox>();
    private ArrayList<String> keys = new ArrayList<>();
    private ArrayList<Object> values =  new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();

    @FXML
    public void initialize(){
        setMainCategories();
        setCity();
        mainBranchCategories.getSelectionModel().select("");
        branchTwoCategories.getSelectionModel().select("");
        String city = GetInfo.getUserCity();
        City.getSelectionModel().select(city);
        keys.add("city");
        values.add(city);
        int size = (int) Math.ceil((double) GetInfo.getSizeOfPosts(keys, values) / 8);
        pagination.setPageCount(size);
        pagination.setMaxPageIndicatorCount(2);
        pagination.setPageFactory(this::CreatePage);
    }

    private Node CreatePage(int pageIndex) {
        ArrayList<JSONObject> posts = new ArrayList<>();
        list = GetInfo.getPosts(8, pageIndex, keys, values);
        priceFilter();
        list = search(list);
        for (String str : list) {
            posts.add(new JSONObject(str));
        }

        VBox vBox = new VBox();
        NewPage newPage = new NewPage(posts, vBox, "ExploreAds");
        PagesList.add(newPage.getPage());
        return PagesList.get(pageIndex);
    }

    private void setMainCategories(){
        String[] mainCategories = {"","Digital Goods", "Entertainment", "Equipment", "Estate", "Home Goods", "Personal Items", "Recruitment", "Services", "Social", "Vehicles"};
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
        City.getSelectionModel().select(0);
    }

    @FXML
    void MainBranchCategotiesFunction(ActionEvent event) {
        setBranchTwoCategories();
    }

    public void setBranchTwoCategories() {
        ObservableList<String> branchTwo = FXCollections.observableArrayList();
        String[] estate = {"","ResidentialSales", "ResidentialRent", "OfficeSales", "OfficeRent", "ShortTermRent", "ConstructionProject"};
        String[] vehicles = {"","Car", "CarSparePartsAndAccessories", "MotorcyclesAndAccessories", "BoatsAndAccessories"};
        String[] digitalCommodity = {"","MobileAndTablet", "PC", "Console", "VideoAndAudio", "Telephone"};
        String[] homeCommodity = {"","ElectricalAppliance", "kitchenware", "FoodAndDrink", "SewingAccessories", "Sofa", "Lighting", "Carpet", "Mattress", "DecorativeAccessories", "VentilationAppliances", "WashingSupplies", "BathroomUtensils"};
        String[] services = {"","EngineAndMachine", "ReceptionAndCeremony", "ComputerAndMobileServices", "FinanceAndAccountingAndInsurance", "Transportation", "ProfessionAndSkill", "HairdressingAndBeauty", "entertainment", "cleaning", "GardeningAndTreePlanting", "educational"};
        String[] personalItems = {"","BagsAndShoes", "Accessories", "Cosmetics", "KidsClothes", "Toy"};
        String[] entertainment = {"","Ticket", "Tour", "Book", "Bike", "Animals", "Collection", "MusicInstrument", "Sport"};
        String[] social = {"","Event", "Voluntarily", "Losts"};
        String[] equipment = {"","BuildingMaterials", "Tools", "IndustrialMachinery", "BusinessEquipment", "Wholesale"};
        String[] recruitment = {"","Management", "Caretaker", "Architect", "StoreServices", "ComputerAndIT", "Accountants", "Marketer", "Engineer", "Teacher", "Driver", "DoctorAndBeautician", "Artist"};

        String mainBranch = mainBranchCategories.getValue();
        switch (mainBranch) {
            case "Estate":
                branchTwo.addAll(estate);
                break;
            case "Vehicles":
                branchTwo.addAll(vehicles);
                break;
            case "Digital Goods":
                branchTwo.addAll(digitalCommodity);
                break;
            case "Home Goods":
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
    void searchButton(){
        PagesList.clear();
        keys.clear();
        values.clear();
        keys.add("city");
        values.add(City.getValue());
        priceFrom.clear();
        priceTo.clear();
        int size = (int) Math.ceil((double) GetInfo.getSizeOfPosts(keys, values) / 8);
            pagination.setPageCount(size);
            pagination.setMaxPageIndicatorCount(2);
            pagination.setPageFactory(this::CreatePage);
    }

    private ArrayList<String> search(ArrayList<String> post) {
        JSONObject jsonObject;
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < post.size(); i++) {
            jsonObject = new JSONObject(post.get(i));
            if (jsonObject.getString("title").contains(searchTextbox.getText())) {
                temp.add(post.get(i));
            }
        }
        return temp;
    }


    @FXML
    void applyButton() {
        PagesList.clear();
        keys.clear();
        values.clear();
        keys.add("city");
        values.add(City.getValue());
        if (!mainBranchCategories.getValue().equals("") && !branchTwoCategories.getValue().equals("")) {
            keys.add("branchMain");
            values.add(mainBranchCategories.getValue());
            keys.add("branch1");
            values.add(branchTwoCategories.getValue());
        } else if (!mainBranchCategories.getValue().equals("")) {
            keys.add("branchMain");
            values.add(mainBranchCategories.getValue());
        }


        int size = (int) Math.ceil((double) GetInfo.getSizeOfPosts(keys, values) / 8);
        pagination.setPageCount(size);
        pagination.setMaxPageIndicatorCount(2);
        pagination.setPageFactory(this::CreatePage);
    }

    @FXML
    void cityBox(ActionEvent event) {
        PagesList.clear();
        keys.clear();
        values.clear();
        keys.add("city");
        values.add(City.getValue());
        searchTextbox.clear();

        int size = (int) Math.ceil((double) GetInfo.getSizeOfPosts(keys, values) / 8);
        pagination.setPageCount(size);
        pagination.setMaxPageIndicatorCount(2);
        pagination.setPageFactory(this::CreatePage);
    }

    private void priceFilter() {
        if (!priceFrom.getText().equals("") && !priceTo.getText().equals("")) {
            list = GetInfo.priceFilter(Long.parseLong(priceFrom.getText()), Long.parseLong(priceTo.getText()), list);
        }
    }

}

