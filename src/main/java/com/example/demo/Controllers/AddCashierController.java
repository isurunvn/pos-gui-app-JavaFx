package com.example.demo.Controllers;

import com.example.demo.DAO.CashierDAO;
import com.example.demo.Models.Cashier;
import com.example.demo.Utils.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddCashierController {

    @FXML
    private TextField cashierNameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField shopIdField;

    @FXML
    private void handleCreateCashier() {
        String cashierName = cashierNameField.getText();
        String password = passwordField.getText();
        String shopIdText = shopIdField.getText();

        if (cashierName.isEmpty() || password.isEmpty() || shopIdText.isEmpty()) {
            // Show error message
            System.out.println("Please fill in all fields");
            return;
        }

        int shopId;
        try {
            shopId = Integer.parseInt(shopIdText);
        } catch (NumberFormatException e) {
            // Show error message
            System.out.println("Invalid shop ID format");
            return;
        }

        Cashier cashier = new Cashier();
        cashier.setCashierName(cashierName);
        cashier.setPassword(password);
        cashier.setShopID(shopId);
        CashierDAO cashierDAO = new CashierDAO();

        try {
            cashierDAO.addCashier(cashier);
            // Navigate back to the previous screen
            handleBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleBack() throws IOException {
        // Navigate back to the previous screen
        SceneSwitcher.handleBackPage((Stage) cashierNameField.getScene().getWindow(), "/com/example/demo/viewCashiers.fxml");
    }
}