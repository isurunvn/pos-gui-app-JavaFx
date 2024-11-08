package com.example.demo.Controllers;

import com.example.demo.DAO.ShopDAO;
import com.example.demo.Models.Shop;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddShopController {

    @FXML
    private TextField shopNameField;

    @FXML
    private TextField shopAddressField;

    @FXML
    private void handleAddShop() {
        String shopName = shopNameField.getText();
        String address = shopAddressField.getText();

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
            System.out.println("Shop added successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}