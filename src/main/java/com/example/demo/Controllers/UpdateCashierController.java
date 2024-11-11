package com.example.demo.Controllers;

import com.example.demo.DAO.CashierDAO;
import com.example.demo.Models.Cashier;
import com.example.demo.Utils.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateCashierController {
    @FXML
    private Label cashierIdLabel;

    @FXML
    private TextField cashierNameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField shopIdField;



    @FXML
    private Button backButton;

    @FXML
    private Button updateCashierButton;

    private int cashierId;

    public void setCashierDetails(Cashier cashier) {
        cashierIdLabel.setText(String.valueOf(cashier.getCashierID()));
        cashierNameField.setText(cashier.getCashierName());
        passwordField.setText(cashier.getPassword());
        shopIdField.setText(String.valueOf(cashier.getShopID()));
    }

    public void handleBack() throws IOException {
        // Navigate back to the previous screen
        SceneSwitcher.handleBackPage((Stage) backButton.getScene().getWindow(), "/com/example/demo/viewCashiers.fxml");
    }

    public void handleUpdateCashier() throws SQLException {
        int cashierId = Integer.parseInt(cashierIdLabel.getText());
        String cashierName = cashierNameField.getText().trim();
        String password = passwordField.getText().trim();
        int shopId = Integer.parseInt(shopIdField.getText());

        // Validate input
        if (cashierName.isEmpty() || password.isEmpty() || shopId == 0) {
            showAlert("Error", "Please fill in all fields");
            return;
        }

        System.out.println("Updating cashier details...");
        System.out.println("Cashier ID: " + cashierId);
        System.out.println("Cashier Name: " + cashierName);
        System.out.println("Password: " + password);
        System.out.println("Shop ID: " + shopId);

        try {
            // Create updated cashier object
            Cashier updatedCashier = new Cashier();
            updatedCashier.setCashierID(cashierId);
            updatedCashier.setCashierName(cashierName);
            updatedCashier.setPassword(password);
            updatedCashier.setShopID(shopId);

            // TODO: Add your cashier update logic here
            CashierDAO cashierDAO = new CashierDAO();
            cashierDAO.updateCashier(updatedCashier);

            showAlert("Success", "Cashier details updated successfully");

            // Navigate back to cashier list or previous screen
            handleBack();
        } catch (Exception e) {
            showAlert("Error", "Failed to update cashier details: " + e.getMessage());
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