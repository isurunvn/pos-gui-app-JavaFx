package com.example.demo.Controllers;

import com.example.demo.Models.Cashier;
import com.example.demo.Utils.DBConnection;
import com.example.demo.Utils.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ChangePasswordController {





    @FXML
    private PasswordField currentPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private TextField cashierIDField;






    public void handleChangePassword() {
        try {
            // Parse cashier ID from input (convert from String to int)
            int cashierID = Integer.parseInt(cashierIDField.getText().trim());
            String currentPassword = currentPasswordField.getText();
            String newPassword = newPasswordField.getText();

            // Input validation
            if (currentPassword.isEmpty() || newPassword.isEmpty()) {
                showAlert("Error", "All fields are required.", Alert.AlertType.ERROR);
                return;
            }

            // Fetch cashier details from the database
            if (validateAndChangePassword(cashierID, currentPassword, newPassword)) {
                showAlert("Success", "Password updated successfully!", Alert.AlertType.INFORMATION);
                clearFields();
            } else {
                showAlert("Error", "Invalid cashier ID or current password.", Alert.AlertType.ERROR);
            }

        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid cashier ID. Please enter a valid number.", Alert.AlertType.ERROR);
        }
    }


    private boolean validateAndChangePassword(int cashierID, String currentPassword, String newPassword) {
        String selectQuery = "SELECT Password FROM cashier WHERE CashierID = ?";
        String updateQuery = "UPDATE Cashier SET password = ? WHERE CashierID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {

            // Check if the current password is correct
            selectStmt.setInt(1, cashierID);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                String dbPassword = rs.getString("Password");

                // Verify the current password
                if (!dbPassword.equals(currentPassword)) {
                    return false; // Current password does not match
                }

                // Update the password
                updateStmt.setString(1, newPassword);
                updateStmt.setInt(2, cashierID);
                int rowsAffected = updateStmt.executeUpdate();

                return rowsAffected > 0; // Return true if update was successful
            }

        } catch (SQLException e) {
            showAlert("Error", "Database error occurred. Please try again.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }

        return false; // Return false if cashier not found or update failed
    }




    // Utility methods for alerts and clearing fields
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        cashierIDField.clear();
        currentPasswordField.clear();
        newPasswordField.clear();
    }

//    public void handleBack() throws IOException {
//        try {
//            SceneSwitcher.handleBackToMenu((Stage) mainVBox.getScene().getWindow());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }



}



