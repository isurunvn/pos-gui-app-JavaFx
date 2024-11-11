package com.example.demo.Controllers;

import com.example.demo.Utils.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class sidebarController {
    @FXML
    private Button ordersButton;

    public void handleViewOrders() {
        try {
            SceneSwitcher.switchScene((Stage) ordersButton.getScene().getWindow(), "/com/example/demo/viewOrders.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleViewCashiers() {
        try {
            SceneSwitcher.switchScene((Stage) ordersButton.getScene().getWindow(), "/com/example/demo/viewCashiers.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleViewProducts() {
        try {
            SceneSwitcher.switchScene((Stage) ordersButton.getScene().getWindow(), "/com/example/demo/viewProducts.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleViewShops() {
        try {
            SceneSwitcher.switchScene((Stage) ordersButton.getScene().getWindow(), "/com/example/demo/viewShops.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
