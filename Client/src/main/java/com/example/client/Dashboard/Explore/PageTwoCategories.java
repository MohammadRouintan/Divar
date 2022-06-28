package com.example.client.Dashboard.Explore;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.File;
import java.util.ArrayList;

public class PageTwoCategories {
    private String nameCat;
    private VBox mainVbox;
    private VBox vBox;
    private ArrayList<Node> nodesOfMainVbox;
    public PageTwoCategories(Parent parent, String nameCat) {
        this.mainVbox = (VBox)parent;
        this.nodesOfMainVbox = getAllChildren(this.mainVbox);
        this.nameCat = nameCat;
        addBox();
    }

    private void addBox() {
        this.mainVbox.getChildren().clear();
        VBox twoCategories = new VBox();
        Button back = new Button("Back");
        twoCategories.getChildren().add(back);
        setButtons(this.nameCat, twoCategories);
        this.mainVbox.getChildren().add(twoCategories);
        back.setOnAction(event -> {
            mainVbox.getChildren().clear();
            for(Node node : nodesOfMainVbox){
                mainVbox.getChildren().add(node);
            }
        });
    }

    private void setButtons(String nameCat, VBox vBox) {
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        Button showAll = makeButton("ShowAll");
        vBox.getChildren().add(showAll);
        switch (nameCat) {
            case "Estate":
                Button ResidentialSales = makeButton("ResidentialSales");

                Button ResidentialRent = makeButton("ResidentialRent");

                Button OfficeSales = makeButton("OfficeSales");

                Button OfficeRent = makeButton("OfficeRent");

                Button ShortTermRent = makeButton("ShortTermRent");

                Button ConstructionProject = makeButton("ConstructionProject");

                vBox.getChildren().addAll(ResidentialSales, ResidentialRent, OfficeSales, OfficeRent, ShortTermRent, ConstructionProject);
                break;
            case "Vehicles":
                Button Car = makeButton("Car");

                Button CarSparePartsAndAccessories = makeButton("CarSparePartsAndAccessories");
;
                Button MotorcyclesAndAccessories = makeButton("MotorcyclesAndAccessories");

                Button BoatsAndAccessories = makeButton("BoatsAndAccessories");

                vBox.getChildren().addAll(Car, CarSparePartsAndAccessories, MotorcyclesAndAccessories, BoatsAndAccessories);
                break;

            case "Digital Goods":
                Button MobileAndTablet = makeButton("MobileAndTablet");

                Button PC = makeButton("PC");

                Button Console = makeButton("Console");

                Button VideoAndAudio = makeButton("VideoAndAudio");

                Button Telephone = makeButton("Telephone");

                vBox.getChildren().addAll(MobileAndTablet, PC, Console, VideoAndAudio, Telephone);
                break;

            case "Home Goods":
                Button ElectricalAppliance = makeButton("ElectricalAppliance");

                Button kitchenware = makeButton("kitchenware");

                Button FoodAndDrink = makeButton("FoodAndDrink");

                Button SewingAccessories = makeButton("SewingAccessories");

                Button Sofa = makeButton("Sofa");

                Button Lighting = makeButton("Lighting");

                Button Carpet = makeButton("Carpet");

                Button Mattress = makeButton("Mattress");

                Button DecorativeAccessories = makeButton("DecorativeAccessories");

                Button VentilationAppliances = makeButton("VentilationAppliances");

                Button WashingSupplies = makeButton("WashingSupplies");

                Button BathroomUtensils = makeButton("BathroomUtensils");

                vBox.getChildren().addAll(ElectricalAppliance, kitchenware, FoodAndDrink, SewingAccessories, Sofa, Lighting, Carpet, Mattress, DecorativeAccessories, VentilationAppliances, WashingSupplies, BathroomUtensils);
                break;

            case "Services":
                Button EngineAndMachine = makeButton("EngineAndMachine");

                Button ReceptionAndCeremony = makeButton("ReceptionAndCeremony");

                Button ComputerAndMobileServices = makeButton("ComputerAndMobileServices");

                Button FinanceAndAccountingAndInsurance = makeButton("FinanceAndAccountingAndInsurance");

                Button Transportation = makeButton("Transportation");

                Button ProfessionAndSkill = makeButton("ProfessionAndSkill");

                Button HairdressingAndBeauty = makeButton("HairdressingAndBeauty");

                Button entertainment = makeButton("entertainment");

                Button cleaning = makeButton("cleaning");

                Button GardeningAndTreePlanting = makeButton("GardeningAndTreePlanting");

                Button educational = makeButton("educational");

                vBox.getChildren().addAll(EngineAndMachine, ReceptionAndCeremony, ComputerAndMobileServices, FinanceAndAccountingAndInsurance, Transportation, ProfessionAndSkill, HairdressingAndBeauty, entertainment, cleaning, GardeningAndTreePlanting, educational);
                break;

            case "Personal Items":
                Button BagsAndShoes = makeButton("BagsAndShoes");

                Button Accessories = makeButton("Accessories");

                Button Cosmetics = makeButton("Cosmetics");

                Button KidsClothes = makeButton("KidsClothes");

                Button Toy = makeButton("Toy");

                vBox.getChildren().addAll(BagsAndShoes, Accessories, Cosmetics, KidsClothes, Toy);
                break;

            case "Entertainment":
                Button Ticket = makeButton("Ticket");

                Button Tour = makeButton("Tour");

                Button Book = makeButton("Book");

                Button Bike = makeButton("Bike");

                Button Animals = makeButton("Animals");

                Button Collection = makeButton("Collection");

                Button MusicInstrument = makeButton("MusicInstrument");

                Button Sport = makeButton("Sport");

                vBox.getChildren().addAll(Ticket, Tour, Book, Bike, Animals, Collection, MusicInstrument, Sport);
                break;

            case "Social":
                Button Event = makeButton("Event");

                Button Voluntarily = makeButton("Voluntarily");

                Button Losts = makeButton("Losts");

                vBox.getChildren().addAll(Event, Voluntarily, Losts);
                break;

            case "Equipment":
                Button BuildingMaterials = makeButton("BuildingMaterials");

                Button Tools = makeButton("Tools");

                Button IndustrialMachinery = makeButton("IndustrialMachinery");

                Button BusinessEquipment = makeButton("BusinessEquipment");

                Button Wholesale = makeButton("Wholesale");

                vBox.getChildren().addAll(BuildingMaterials, Tools, IndustrialMachinery, BusinessEquipment, Wholesale);
                break;

            case "Recruitment":
                Button Management = makeButton("Management");

                Button Caretaker = makeButton("Caretaker");

                Button Architect = makeButton("Architect");

                Button StoreServices = makeButton("StoreServices");

                Button ComputerAndIT = makeButton("ComputerAndIT");

                Button Accountants = makeButton("Accountants");

                Button Marketer = makeButton("Marketer");

                Button Engineer = makeButton("Engineer");

                Button Teacher = makeButton("Teacher");

                Button Driver = makeButton("Driver");

                Button DoctorAndBeautician = makeButton("DoctorAndBeautician");

                Button Artist = makeButton("Artist");

                vBox.getChildren().addAll(Management, Caretaker, Architect, StoreServices, ComputerAndIT, Accountants, Marketer, Engineer, Teacher, Driver, DoctorAndBeautician, Artist);
                break;
        }
    }

    private Button makeButton(String Name) {
        File file = new File("../Client/src/main/resources/Style/Categories.css");
        Button button = new Button(Name);
        button.setPrefWidth(500);
        button.setPrefHeight(40);
        button.setFont(new Font(18));
        button.setAlignment(Pos.BASELINE_LEFT);
        button.setPadding(new Insets(0,0,0,30));
        button.getStylesheets().add(file.toURI().toString());
        button.getStyleClass().add("btn");
        button.setOnAction(event -> {
            PageThreeCategories pageThreeCategories = new PageThreeCategories(mainVbox,Name);
        });
        return button;
    }

    protected ArrayList<Node> getAllChildren (VBox mainVBox){
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.addAll(mainVBox.getChildrenUnmodifiable());
        return nodes;
    }
}
