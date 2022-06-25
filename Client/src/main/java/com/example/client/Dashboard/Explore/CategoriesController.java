package com.example.client.Dashboard.Explore;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class CategoriesController {

    @FXML
    private VBox mainVbox;

    @FXML
    void digitalGoodsButton(ActionEvent event) {
        PageTwoCategories pageTwoCategories = new PageTwoCategories(mainVbox, "Digital Goods");
    }

    @FXML
    void entertainmentButton(ActionEvent event) {
        PageTwoCategories pageTwoCategories = new PageTwoCategories(mainVbox, "Entertainment");
    }

    @FXML
    void equipmentButton(ActionEvent event) {
        PageTwoCategories pageTwoCategories = new PageTwoCategories(mainVbox, "Equipment");
    }

    @FXML
    void estateButton(ActionEvent event) {
        PageTwoCategories pageTwoCategories = new PageTwoCategories(mainVbox, "Estate");
    }

    @FXML
    void homeGoodsButton(ActionEvent event) {
        PageTwoCategories pageTwoCategories = new PageTwoCategories(mainVbox, "Home Goods");
    }

    @FXML
    void personalItemsButton(ActionEvent event) {
        PageTwoCategories pageTwoCategories = new PageTwoCategories(mainVbox, "Personal Items");
    }

    @FXML
    void recruitmentButton(ActionEvent event) {
        PageTwoCategories pageTwoCategories = new PageTwoCategories(mainVbox, "Recruitment");
    }

    @FXML
    void servicesButton(ActionEvent event) {
        PageTwoCategories pageTwoCategories = new PageTwoCategories(mainVbox, "Services");
    }

    @FXML
    void socialButton(ActionEvent event) {
        PageTwoCategories pageTwoCategories = new PageTwoCategories(mainVbox, "Social");
    }

    @FXML
    void vehiclesButton(ActionEvent event) {
        PageTwoCategories pageTwoCategories = new PageTwoCategories(mainVbox, "Vehicles");
    }

}
