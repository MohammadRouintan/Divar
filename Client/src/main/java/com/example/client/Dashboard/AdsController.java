package com.example.client.Dashboard;

import com.example.client.Dashboard.Posts.NewPage;
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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


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

    List<VBox> PagesList = new ArrayList<VBox>();
    @FXML
    public void initialize(){
        setMainCategories();
        setCity();

        ArrayList<JSONObject> post = new ArrayList<>();
        ArrayList<String> imageName = new ArrayList<>();
        imageName.add("1");
        imageName.add("2");
        ArrayList<String> columnName = new ArrayList<>();
        columnName.add("Salam");
        columnName.add("Khobi");

        ArrayList<String> columnValue = new ArrayList<>();
        columnValue.add("dddddd");
        columnValue.add("bbbbbb");

        for (int i = 0; i < 7; i++) {
            JSONObject post1 = new JSONObject();
            post1.put("title", "salam");
            post1.put("price", "14000");
            post1.put("time", "5");
            post1.put("image1", "/postImage/1.jpg");
            post1.put("imageName",imageName);
            post1.put("columnName",columnName);
            post1.put("columnValue",columnValue);
            post1.put("bio","I have a scene showing 3 images, and I want each of them to take a third of the width of the scene. " +
                    "From now, I have made 3 Pane of each 30% of it, it works. But in those Pane," +
                    " I can't make my ImageView use only the width of the Pane.");
            post1.put("agreement", true);
            post1.put("exchange", true);
            post1.put("auction", false);
            post.add(post1);
        }
        VBox vBox = new VBox();
        NewPage newPage = new NewPage(post,vBox, "BookmarkedAds");
        PagesList.add(newPage.getPage());
        pagination.setPageFactory(this::CreatePage);

    }

    private Node CreatePage(int pageIndex) {
//        ArrayList<JSONObject> userPosts = new ArrayList<>();
//        for (int i = (pagination.getCurrentPageIndex() - 1) * 8; i < pagination.getCurrentPageIndex() * 8; i++) {
//            userPosts.add(new JSONObject(GetInfo.getUserPosts().get(i)));
//        }
        ArrayList<JSONObject> post = new ArrayList<>();
        ArrayList<String> imageName = new ArrayList<>();
        imageName.add("1");
        imageName.add("2");
        ArrayList<String> columnName = new ArrayList<>();
        columnName.add("Salam");
        columnName.add("Khobi");

        ArrayList<String> columnValue = new ArrayList<>();
        columnValue.add("dddddd");
        columnValue.add("bbbbbb");

        for (int i = 0; i < 7; i++) {
            JSONObject post1 = new JSONObject();
            post1.put("title", "salam");
            post1.put("price", "14000");
            post1.put("time", "5");
            post1.put("image1", "/postImage/1.jpg");
            post1.put("imageName",imageName);
            post1.put("columnName",columnName);
            post1.put("columnValue",columnValue);
            post1.put("bio","I have a scene showing 3 images, and I want each of them to take a third of the width of the scene. " +
                    "From now, I have made 3 Pane of each 30% of it, it works. But in those Pane," +
                    " I can't make my ImageView use only the width of the Pane.");
            post.add(post1);
        }
        VBox vBox = new VBox();
        NewPage newPage = new NewPage(post,vBox,"BookmarkedAds");
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
    private void searchButton(){

    }

    @FXML
    private void applyButton(){

    }

}

