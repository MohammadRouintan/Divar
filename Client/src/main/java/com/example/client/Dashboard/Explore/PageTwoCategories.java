package com.example.client.Dashboard.Explore;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class PageTwoCategories {
    private String nameCat;
    private VBox mainVbox;
    private VBox vBox;

    public PageTwoCategories(Parent parent, String nameCat) {
        this.mainVbox = (VBox)parent;
        this.vBox = (VBox)mainVbox.getChildren().get(0);
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
            mainVbox.getChildren().add(vBox);
        });
    }

    private void setButtons(String nameCat, VBox vBox) {
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        Button showAll = new Button("ShowAll");
        showAll.setPrefWidth(500);
        showAll.setPrefHeight(44);
        vBox.getChildren().add(showAll);
        switch (nameCat) {
            case "Estate":
                Button ResidentialSales = new Button("ResidentialSales");
                ResidentialSales.setPrefWidth(500);
                ResidentialSales.setPrefHeight(44);
                Button ResidentialRent = new Button("ResidentialRent");
                ResidentialRent.setPrefWidth(500);
                ResidentialRent.setPrefHeight(44);
                Button OfficeSales = new Button("OfficeSales");
                OfficeSales.setPrefWidth(500);
                OfficeSales.setPrefHeight(44);
                Button OfficeRent = new Button("OfficeRent");
                OfficeRent.setPrefWidth(500);
                OfficeRent.setPrefHeight(44);
                Button ShortTermRent = new Button("ShortTermRent");
                ShortTermRent.setPrefWidth(500);
                ShortTermRent.setPrefHeight(44);
                Button ConstructionProject = new Button("ConstructionProject");
                ConstructionProject.setPrefWidth(500);
                ConstructionProject.setPrefHeight(44);
                vBox.getChildren().addAll(ResidentialSales, ResidentialRent, OfficeSales, OfficeRent, ShortTermRent, ConstructionProject);
                break;
            case "Vehicles":
                Button Car = new Button("Car");
                Car.setPrefWidth(500);
                Car.setPrefHeight(44);
                Button CarSparePartsAndAccessories = new Button("CarSparePartsAndAccessories");
                CarSparePartsAndAccessories.setPrefWidth(500);
                CarSparePartsAndAccessories.setPrefHeight(44);
                Button MotorcyclesAndAccessories = new Button("MotorcyclesAndAccessories");
                MotorcyclesAndAccessories.setPrefWidth(500);
                MotorcyclesAndAccessories.setPrefHeight(44);
                Button BoatsAndAccessories = new Button("BoatsAndAccessories");
                BoatsAndAccessories.setPrefWidth(500);
                BoatsAndAccessories.setPrefHeight(44);
                vBox.getChildren().addAll(Car, CarSparePartsAndAccessories, MotorcyclesAndAccessories, BoatsAndAccessories);
                break;
            case "Digital Goods":
                Button MobileAndTablet = new Button("MobileAndTablet");
                MobileAndTablet.setPrefWidth(500);
                MobileAndTablet.setPrefHeight(44);
                Button PC = new Button("PC");
                PC.setPrefWidth(500);
                PC.setPrefHeight(44);
                Button Console = new Button("Console");
                Console.setPrefWidth(500);
                Console.setPrefHeight(44);
                Button VideoAndAudio = new Button("VideoAndAudio");
                VideoAndAudio.setPrefWidth(500);
                VideoAndAudio.setPrefHeight(44);
                Button Telephone = new Button("Telephone");
                Telephone.setPrefWidth(500);
                Telephone.setPrefHeight(44);
                vBox.getChildren().addAll(MobileAndTablet, PC, Console, VideoAndAudio, Telephone);
                break;
            case "Home Goods":
                Button ElectricalAppliance = new Button("ElectricalAppliance");
                ElectricalAppliance.setPrefWidth(500);
                ElectricalAppliance.setPrefHeight(44);
                Button kitchenware = new Button("kitchenware");
                kitchenware.setPrefWidth(500);
                kitchenware.setPrefHeight(44);
                Button FoodAndDrink = new Button("FoodAndDrink");
                FoodAndDrink.setPrefWidth(500);
                FoodAndDrink.setPrefHeight(44);
                Button SewingAccessories = new Button("SewingAccessories");
                SewingAccessories.setPrefWidth(500);
                SewingAccessories.setPrefHeight(44);
                Button Sofa = new Button("Sofa");
                Sofa.setPrefWidth(500);
                Sofa.setPrefHeight(44);
                Button Lighting = new Button("Lighting");
                Lighting.setPrefWidth(500);
                Lighting.setPrefHeight(44);
                Button Carpet = new Button("Carpet");
                Carpet.setPrefWidth(500);
                Carpet.setPrefHeight(44);
                Button Mattress = new Button("Mattress");
                Mattress.setPrefWidth(500);
                Mattress.setPrefHeight(44);
                Button DecorativeAccessories = new Button("DecorativeAccessories");
                DecorativeAccessories.setPrefWidth(500);
                DecorativeAccessories.setPrefHeight(44);
                Button VentilationAppliances = new Button("VentilationAppliances");
                VentilationAppliances.setPrefWidth(500);
                VentilationAppliances.setPrefHeight(44);
                Button WashingSupplies = new Button("WashingSupplies");
                WashingSupplies.setPrefWidth(500);
                WashingSupplies.setPrefHeight(44);
                Button BathroomUtensils = new Button("BathroomUtensils");
                BathroomUtensils.setPrefWidth(500);
                BathroomUtensils.setPrefHeight(44);
                vBox.getChildren().addAll(ElectricalAppliance, kitchenware, FoodAndDrink, SewingAccessories, Sofa, Lighting, Carpet, Mattress, DecorativeAccessories, VentilationAppliances, WashingSupplies, BathroomUtensils);
                break;
            case "Services":
                Button EngineAndMachine = new Button("EngineAndMachine");
                EngineAndMachine.setPrefWidth(500);
                EngineAndMachine.setPrefHeight(44);
                Button ReceptionAndCeremony = new Button("ReceptionAndCeremony");
                ReceptionAndCeremony.setPrefWidth(500);
                ReceptionAndCeremony.setPrefHeight(44);
                Button ComputerAndMobileServices = new Button("ComputerAndMobileServices");
                ComputerAndMobileServices.setPrefWidth(500);
                ComputerAndMobileServices.setPrefHeight(44);
                Button FinanceAndAccountingAndInsurance = new Button("FinanceAndAccountingAndInsurance");
                FinanceAndAccountingAndInsurance.setPrefWidth(500);
                FinanceAndAccountingAndInsurance.setPrefHeight(44);
                Button Transportation = new Button("Transportation");
                Transportation.setPrefWidth(500);
                Transportation.setPrefHeight(44);
                Button ProfessionAndSkill = new Button("ProfessionAndSkill");
                ProfessionAndSkill.setPrefWidth(500);
                ProfessionAndSkill.setPrefHeight(44);
                Button HairdressingAndBeauty = new Button("HairdressingAndBeauty");
                HairdressingAndBeauty.setPrefWidth(500);
                HairdressingAndBeauty.setPrefHeight(44);
                Button entertainment = new Button("entertainment");
                entertainment.setPrefWidth(500);
                entertainment.setPrefHeight(44);
                Button cleaning = new Button("cleaning");
                cleaning.setPrefWidth(500);
                cleaning.setPrefHeight(44);
                Button GardeningAndTreePlanting = new Button("GardeningAndTreePlanting");
                GardeningAndTreePlanting.setPrefWidth(500);
                GardeningAndTreePlanting.setPrefHeight(44);
                Button educational = new Button("educational");
                educational.setPrefWidth(500);
                educational.setPrefHeight(44);
                vBox.getChildren().addAll(EngineAndMachine, ReceptionAndCeremony, ComputerAndMobileServices, FinanceAndAccountingAndInsurance, Transportation, ProfessionAndSkill, HairdressingAndBeauty, entertainment, cleaning, GardeningAndTreePlanting, educational);
                break;
            case "Personal Items":
                Button BagsAndShoes = new Button("BagsAndShoes");
                BagsAndShoes.setPrefWidth(500);
                BagsAndShoes.setPrefHeight(44);
                Button Accessories = new Button("Accessories");
                Accessories.setPrefWidth(500);
                Accessories.setPrefHeight(44);
                Button Cosmetics = new Button("Cosmetics");
                Cosmetics.setPrefWidth(500);
                Cosmetics.setPrefHeight(44);
                Button KidsClothes = new Button("KidsClothes");
                KidsClothes.setPrefWidth(500);
                KidsClothes.setPrefHeight(44);
                Button Toy = new Button("Toy");
                Toy.setPrefWidth(500);
                Toy.setPrefHeight(44);
                vBox.getChildren().addAll(BagsAndShoes, Accessories, Cosmetics, KidsClothes, Toy);
                break;
            case "Entertainment":
                Button Ticket = new Button("Ticket");
                Ticket.setPrefWidth(500);
                Ticket.setPrefHeight(44);
                Button Tour = new Button("Tour");
                Tour.setPrefWidth(500);
                Tour.setPrefHeight(44);
                Button Book = new Button("Book");
                Book.setPrefWidth(500);
                Book.setPrefHeight(44);
                Button Bike = new Button("Bike");
                Bike.setPrefWidth(500);
                Bike.setPrefHeight(44);
                Button Animals = new Button("Animals");
                Animals.setPrefWidth(500);
                Animals.setPrefHeight(44);
                Button Collection = new Button("Collection");
                Collection.setPrefWidth(500);
                Collection.setPrefHeight(44);
                Button MusicInstrument = new Button("MusicInstrument");
                MusicInstrument.setPrefWidth(500);
                MusicInstrument.setPrefHeight(44);
                Button Sport = new Button("Sport");
                Sport.setPrefWidth(500);
                Sport.setPrefHeight(44);
                vBox.getChildren().addAll(Ticket, Tour, Book, Bike, Animals, Collection, MusicInstrument, Sport);
                break;
            case "Social":
                Button Event = new Button("Event");
                Event.setPrefWidth(500);
                Event.setPrefHeight(44);
                Button Voluntarily = new Button("Voluntarily");
                Voluntarily.setPrefWidth(500);
                Voluntarily.setPrefHeight(44);
                Button Losts = new Button("Losts");
                Losts.setPrefWidth(500);
                Losts.setPrefHeight(44);
                vBox.getChildren().addAll(Event, Voluntarily, Losts);
                break;
            case "Equipment":
                Button BuildingMaterials = new Button("BuildingMaterials");
                BuildingMaterials.setPrefWidth(500);
                BuildingMaterials.setPrefHeight(44);
                Button Tools = new Button("Tools");
                Tools.setPrefWidth(500);
                Tools.setPrefHeight(44);
                Button IndustrialMachinery = new Button("IndustrialMachinery");
                IndustrialMachinery.setPrefWidth(500);
                IndustrialMachinery.setPrefHeight(44);
                Button BusinessEquipment = new Button("BusinessEquipment");
                BusinessEquipment.setPrefWidth(500);
                BusinessEquipment.setPrefHeight(44);
                Button Wholesale = new Button("Wholesale");
                Wholesale.setPrefWidth(500);
                Wholesale.setPrefHeight(44);
                vBox.getChildren().addAll(BuildingMaterials, Tools, IndustrialMachinery, BusinessEquipment, Wholesale);
                break;
            case "Recruitment":
                Button Management = new Button("Management");
                Management.setPrefWidth(500);
                Management.setPrefHeight(44);
                Button Caretaker = new Button("Caretaker");
                Caretaker.setPrefWidth(500);
                Caretaker.setPrefHeight(44);
                Button Architect = new Button("Architect");
                Architect.setPrefWidth(500);
                Architect.setPrefHeight(44);
                Button StoreServices = new Button("StoreServices");
                StoreServices.setPrefWidth(500);
                StoreServices.setPrefHeight(44);
                Button ComputerAndIT = new Button("ComputerAndIT");
                ComputerAndIT.setPrefWidth(500);
                ComputerAndIT.setPrefHeight(44);
                Button Accountants = new Button("Accountants");
                Accountants.setPrefWidth(500);
                Accountants.setPrefHeight(44);
                Button Marketer = new Button("Marketer");
                Marketer.setPrefWidth(500);
                Marketer.setPrefHeight(44);
                Button Engineer = new Button("Engineer");
                Engineer.setPrefWidth(500);
                Engineer.setPrefHeight(44);
                Button Teacher = new Button("Teacher");
                Teacher.setPrefWidth(500);
                Teacher.setPrefHeight(44);
                Button Driver = new Button("Driver");
                Driver.setPrefWidth(500);
                Driver.setPrefHeight(44);
                Button DoctorAndBeautician = new Button("DoctorAndBeautician");
                DoctorAndBeautician.setPrefWidth(500);
                DoctorAndBeautician.setPrefHeight(44);
                Button Artist = new Button("Artist");
                Artist.setPrefWidth(500);
                Artist.setPrefHeight(44);
                vBox.getChildren().addAll(Management, Caretaker, Architect, StoreServices, ComputerAndIT, Accountants, Marketer, Engineer, Teacher, Driver, DoctorAndBeautician, Artist);
                break;
        }
    }



}
