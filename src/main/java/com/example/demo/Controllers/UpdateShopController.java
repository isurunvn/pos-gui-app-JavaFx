package com.example.demo.Controllers;

import com.example.demo.DAO.ShopDAO;
import com.example.demo.Models.Shop;
import com.example.demo.Utils.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateShopController {
    @FXML
    private Label shopIdLabel;

    @FXML
    private TextField shopNameField;

    @FXML
    private TextField addressField;

    @FXML
    private Button backButton;

    @FXML
    private Button updateShopButton;

    private int shopId;

    public void setShopDetails(Shop shop) {
        shopIdLabel.setText(String.valueOf(shop.getShopId()));
        shopNameField.setText(shop.getShopName());
        addressField.setText(shop.getAddress());
    }

    public void handleBack() throws IOException {
        // Navigate back to the previous screen
        SceneSwitcher.handleBackPage((Stage) backButton.getScene().getWindow(), "/com/example/demo/viewShops.fxml");
    }


    public void handleUpdateShop() throws SQLException {
        int shopId = Integer.parseInt(shopIdLabel.getText());
        String shopName = shopNameField.getText().trim();
        String address = addressField.getText().trim();

        // Validate input
        if (shopName.isEmpty() || address.isEmpty()) {
            showAlert("Error", "Please fill in all fields");
            return;
        }

        System.out.println("Updating shop details...");
        System.out.println("Shop ID: " + shopId);
        System.out.println("Shop Name: " + shopName);
        System.out.println("Address: " + address);

        try {
            // Create updated shop object
            Shop updatedShop = new Shop();
            updatedShop.setShopId(shopId);
            updatedShop.setShopName(shopName);
            updatedShop.setAddress(address);
//
//            // TODO: Add your shop update logic here
            ShopDAO shopDAO = new ShopDAO();
            shopDAO.updateShop(updatedShop);


            showAlert("Success", "Shop details updated successfully");

            // Navigate back to shop list or previous screen
            handleBack();
        } catch (Exception e) {
            showAlert("Error", "Failed to update shop details: " + e.getMessage());
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}