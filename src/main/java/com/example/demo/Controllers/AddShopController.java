package com.example.demo.Controllers;

import com.example.demo.DAO.ShopDAO;
import com.example.demo.Models.Shop;
import com.example.demo.Utils.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddShopController {

    @FXML
    private TextField shopNameField;

    @FXML
    private TextField addressField;

    @FXML
    private void handleCreateShop() {
        String shopName = shopNameField.getText();
        String address = addressField.getText();

        if (shopName.isEmpty() || address.isEmpty()) {
            // Show error message
            System.out.println("Please fill in all fields");
            return;
        }

        Shop shop = new Shop();
        shop.setShopName(shopName);
        shop.setAddress(address);
        ShopDAO shopDAO = new ShopDAO();

        try {
            shopDAO.addShop(shop);
            // Navigate back to the previous screen
            handleBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleBack() throws IOException {
            // Navigate back to the previous screen
            SceneSwitcher.handleBackPage((Stage) shopNameField.getScene().getWindow(), "/com/example/demo/viewShops.fxml");

    }
}