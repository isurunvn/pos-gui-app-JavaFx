package com.example.demo.Controllers;

import com.example.demo.Models.UserSessionManager;
import com.example.demo.Utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
//    @FXML
//    private Label userNameLabel;
    @FXML
    private Button viewOrderButton;
    @FXML
    private Button createOrderButton;

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//    }


    ///////////////////////////////////////Admin menu functions
    public void handleAddShop() {
        try {
            SceneSwitcher.switchScene((Stage) viewOrderButton.getScene().getWindow(), "/com/example/demo/addShop.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleViewShops() {
        try {
            SceneSwitcher.switchScene((Stage) viewOrderButton.getScene().getWindow(), "/com/example/demo/viewShops.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleRemoveShop() {
        try {
            SceneSwitcher.switchScene((Stage) viewOrderButton.getScene().getWindow(), "/com/example/demo/deleteShop.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleAddProduct() {
        try {
            SceneSwitcher.switchScene((Stage) viewOrderButton.getScene().getWindow(), "/com/example/demo/addProduct.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleRemoveProduct() {
        try {
            SceneSwitcher.switchScene((Stage) viewOrderButton.getScene().getWindow(), "/com/example/demo/removeProduct.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleAddCashier() {
        try {
            SceneSwitcher.switchScene((Stage) viewOrderButton.getScene().getWindow(), "/com/example/demo/addCashier.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleViewCashiers() {
        try {
            SceneSwitcher.switchScene((Stage) viewOrderButton.getScene().getWindow(), "/com/example/demo/viewCashiers.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleRemoveCashier() {
        try {
            SceneSwitcher.switchScene((Stage) viewOrderButton.getScene().getWindow(), "/com/example/demo/deleteCashier.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleViewOrders() {
        try {
            SceneSwitcher.switchScene((Stage) viewOrderButton.getScene().getWindow(), "/com/example/demo/viewOrders.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleViewOrderByID() {
        try {
            SceneSwitcher.switchScene((Stage) viewOrderButton.getScene().getWindow(), "/com/example/demo/viewOrderByID.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleRemoveOrder() {
        try {
            SceneSwitcher.switchScene((Stage) viewOrderButton.getScene().getWindow(), "/com/example/demo/deleteOrder.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    ///////////////////////////////////////Cashier menu functions
    public void handleCreateOrder() {
        try {
            SceneSwitcher.switchScene((Stage) createOrderButton.getScene().getWindow(), "/com/example/demo/createOrder.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    ///////////////////////////////////////Common menu functions
    public void handleViewProducts() {
        try {
            // Reuse the switchScene method to switch to the viewProducts.fxml page
            SceneSwitcher.switchScene((Stage) viewOrderButton.getScene().getWindow(), "/com/example/demo/viewProducts.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleCashierViewProducts() {
        try {
            SceneSwitcher.switchScene((Stage) createOrderButton.getScene().getWindow(), "/com/example/demo/cashierViewProducts.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    ///////////////////////////////////////Logout function
    public void handleLogout() throws IOException {
        UserSessionManager.getInstance().clearSession();
        SceneSwitcher.switchScene((Stage) viewOrderButton.getScene().getWindow(), "/com/example/demo/login.fxml");
    }

}