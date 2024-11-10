package com.example.demo.Controllers;

import com.example.demo.DAO.ShopDAO;
import com.example.demo.Models.Cashier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DeleteShopController {

    @FXML
    private TextField shopIdField;

//    @FXML
//    private PasswordField currentPasswordField;
//
//    @FXML
//    private PasswordField newPasswordField;
//
//    private Cashier cashier;
//
//    public void setCashier(Cashier cashier) {
//        this.cashier = cashier;
//    }




    public void handleDeleteShop() {
        int shopId = Integer.parseInt(shopIdField.getText());
        ShopDAO shopDAO = new ShopDAO();
        try {
            shopDAO.deleteShop(shopId);
            System.out.println("Shop deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void handleChangePassword(){

    }
//    @FXML
//    public void handleChangePassword() {
//        String currentPassword = currentPasswordField.getText();
//        String newPassword = newPasswordField.getText();
//
//        // Verify the current password
//        if (!currentPassword.equals(cashier.getPassword())) {
//            showAlert("Error", "Current password is incorrect.", Alert.AlertType.ERROR);
//            return;
//        }

        // Validate the new password
//        if (newPassword.isEmpty() || newPassword.length() < 6) {
//            showAlert("Error", "New password must be at least 6 characters long.", Alert.AlertType.ERROR);
//            return;
//        }

//        // Check if the new password is different from the current password
//        if (currentPassword.equals(newPassword)) {
//            showAlert("Error", "New password cannot be the same as the current password.", Alert.AlertType.ERROR);
//            return;
//        }
//
//        // Update the password in the Cashier object and the database
//        cashier.setPassword(newPassword);
//
//        try {
//            CashierDAO cashiereDAO = new CashierDAO();
//            shopDAO.updateCashierPassword(cashier.getCashierID(), newPassword);
//            showAlert("Success", "Password changed successfully!", Alert.AlertType.INFORMATION);
//
//            // Clear the fields
//            currentPasswordField.clear();
//            newPasswordField.clear();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            showAlert("Error", "Failed to update password in the database.", Alert.AlertType.ERROR);
//        }
//    }
//
//
//    private void showAlert(String title, String message, Alert.AlertType type) {
//        Alert alert = new Alert(type);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
}
