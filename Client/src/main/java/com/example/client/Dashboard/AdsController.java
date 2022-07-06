package com.example.client.Dashboard;

import com.example.client.Dashboard.Posts.NewPage;
import com.example.client.socket.GetInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class AdsController {

    // Home
    @FXML
    public Tab home;
    @FXML
    private ComboBox<String> homeTabCity;
    @FXML
    private ComboBox<String> mainBranchCategories;
    @FXML
    private ComboBox<String> branchTwoCategories;
    @FXML
    private TextField searchText;
    @FXML
    private TextField priceFrom;
    @FXML
    private TextField priceTo;
    @FXML
    private Pagination homePagination;

    // Recommended
    @FXML
    public Tab recommended;
    @FXML
    public ComboBox<String> recommendedTabCity;
    @FXML
    public Pagination recommendedPagination;

    private List<VBox> PagesList = new ArrayList<>();
    private ArrayList<String> keys = new ArrayList<>();
    private ArrayList<Object> values =  new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();

    @FXML
    public void initialize(String tab) {
        if (tab.equals("home")) {
            setCity("homeTabCity");
            setMainCategories();
            mainBranchCategories.getSelectionModel().select("");
            branchTwoCategories.getSelectionModel().select("");
            homeTabCity.getSelectionModel().select("Tehran");
            keys.add("city");
            values.add("Tehran");
            //int size = (int) Math.ceil((double) GetInfo.getSizeOfPosts(keys, values) / 8);
            homePagination.setPageCount(1);
            homePagination.setMaxPageIndicatorCount(2);
            homePagination.setPageFactory(this::CreatePage);
        } else if (tab.equals("recommended")) {
            setCity("recommendedTabCity");
            recommendedTabCity.getSelectionModel().select("Tehran");
            keys.add("city");
            values.add("Tehran");
            //int size = (int) Math.ceil((double) GetInfo.getSizeOfPosts(keys, values) / 8);
            recommendedPagination.setPageCount(1);
            recommendedPagination.setMaxPageIndicatorCount(2);
            recommendedPagination.setPageFactory(this::CreatePage);
        }
    }

    private Node CreatePage(int pageIndex) {
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
        NewPage newPage = new NewPage(post, vBox, "ExploreAds");
        PagesList.add(newPage.getPage());
        return PagesList.get(pageIndex);
    }

    private void setMainCategories() {
        String[] mainCategories = {"", "Digital Goods", "Entertainment", "Equipment", "Estate", "Home Goods", "Personal Items", "Recruitment", "Services", "Social", "Vehicles"};
        ObservableList<String> temp = FXCollections.observableArrayList();
        temp.addAll(mainCategories);
        mainBranchCategories.setItems(temp);
    }

    private void setCity(String city) {
        String[] cities = {"Tehran", "Shiraz", "Mashhad", "Arak", "Ardabil", "Orumieh", "Esfahan", "Ahwaz", "Ilam", " Bojnord",
                "Bandar Abbas", "Bushehr", "Birjand", "Tabriz", "Khorramabad", "Rasht", "Zahedan", "Zanjan", "Sari", "Semnan",
                "Sanandaj", "Shahr e Kord", "Qazvin", "Qom", "Karaj", "Kerman", "Kermanshah", "Gorgan", "Hamedan", "Yasuj", "Yazd"};

        ObservableList<String> temp = FXCollections.observableArrayList();
        temp.addAll(cities);

        if (city.equals("homeTabCity")) {
            this.homeTabCity.setItems(temp);
            this.homeTabCity.getSelectionModel().select(0);
        } else if (city.equals("recommendedTabCity")) {
            recommendedTabCity.setItems(temp);
            recommendedTabCity.getSelectionModel().select(0);
        }
    }

    @FXML
    void MainBranchCategoriesFunction(ActionEvent event) {
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
        if (mainBranch != null) {
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
                    branchTwo.add("");
                    break;
            }
        }

        branchTwoCategories.setItems(branchTwo);
    }

    @FXML
    void searchButton() {
        PagesList.clear();
        keys.clear();
        values.clear();
        keys.add("city");
        values.add(homeTabCity.getValue());
        priceFrom.clear();
        priceTo.clear();
        //int size = (int) Math.ceil((double) GetInfo.getSizeOfPosts(keys, values) / 8);
        homePagination.setPageCount(1);
        homePagination.setMaxPageIndicatorCount(2);
        homePagination.setPageFactory(this::CreatePage);
    }

    private ArrayList<String> search(ArrayList<String> post) {
        JSONObject jsonObject;
        String str1, str2;
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < post.size(); i++) {
            jsonObject = new JSONObject(post.get(i));
            str1 = jsonObject.getString("title").toLowerCase();
            str2 = searchText.getText().toLowerCase();
            if (str1.contains(str2)) {
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
        values.add(homeTabCity.getValue());
        if (!mainBranchCategories.getValue().equals("") && !branchTwoCategories.getValue().equals("")) {
            keys.add("branchMain");
            values.add(mainBranchCategories.getValue());
            keys.add("branch1");
            values.add(branchTwoCategories.getValue());
        } else if (!mainBranchCategories.getValue().equals("")) {
            keys.add("branchMain");
            values.add(mainBranchCategories.getValue());
        }

        //int size = (int) Math.ceil((double) GetInfo.getSizeOfPosts(keys, values) / 8);
        homePagination.setPageCount(1);
        homePagination.setMaxPageIndicatorCount(2);
        homePagination.setPageFactory(this::CreatePage);
    }

    @FXML
    void cityBox(ActionEvent event) {
        PagesList.clear();
        keys.clear();
        values.clear();
        keys.add("city");
        values.add(homeTabCity.getValue());
        searchText.clear();

        //int size = (int) Math.ceil((double) GetInfo.getSizeOfPosts(keys, values) / 8);
        homePagination.setPageCount(1);
        homePagination.setMaxPageIndicatorCount(2);
        homePagination.setPageFactory(this::CreatePage);
    }

    private void priceFilter() {
        if (!priceFrom.getText().equals("") && !priceTo.getText().equals("")) {
            //list = GetInfo.priceFilter(Long.parseLong(priceFrom.getText()), Long.parseLong(priceTo.getText()), list);
        }
    }

    @FXML
    void homeButton(Event event) {
        if (home.isSelected()) {
            PagesList.clear();
            keys.clear();
            values.clear();
            initialize("home");
        }
    }

    @FXML
    void recommendedButton(Event event) {
        if (recommended.isSelected()) {
            PagesList.clear();
            keys.clear();
            values.clear();
            initialize("recommended");
        }
    }
}


