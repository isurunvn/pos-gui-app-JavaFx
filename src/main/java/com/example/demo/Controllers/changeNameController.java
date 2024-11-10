package com.example.demo.Controllers;

import com.example.demo.Utils.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class changeNameController {

    @FXML
    private TextField cashierIDField;
    @FXML
    private TextField newNameField;



    public void handleChangeName() {
        try {
            // Parse cashier ID from input (convert from String to int)
            int cashierID = Integer.parseInt(cashierIDField.getText().trim());
            String newName = newNameField.getText().trim();

            // Input validation
            if (cashierIDField.getText().isEmpty() || newName.isEmpty()) {
                showAlert("Error", "All fields are required.", Alert.AlertType.ERROR);
                return;
            }

            // Check if the name change was successful
            if (validateAndChangeName(cashierID, newName)) {
                showAlert("Success", "Name updated successfully!", Alert.AlertType.INFORMATION);
                clearFields();
            } else {
                showAlert("Error", "Invalid cashier ID or update failed.", Alert.AlertType.ERROR);
            }

        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid cashier ID. Please enter a valid number.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }


     //Validates the cashier ID and updates the name in the database.

    private boolean validateAndChangeName(int cashierID, String newName) {
        String selectQuery = "SELECT CashierName FROM Cashier WHERE CashierID = ?";
        String updateQuery = "UPDATE Cashier SET CashierName = ? WHERE CashierID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {

            // Check if the cashier exists in the database
            selectStmt.setInt(1, cashierID);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                // Cashier exists, proceed to update the name
                updateStmt.setString(1, newName);
                updateStmt.setInt(2, cashierID);
                int rowsAffected = updateStmt.executeUpdate();

                return rowsAffected > 0; // Return true if update was successful
            } else {
                showAlert("Error", "Cashier ID not found.", Alert.AlertType.ERROR);
            }

        } catch (SQLException e) {
            showAlert("Error", "Database error occurred. Please try again.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }

        return false; // Return false if cashier not found or update failed
    }

    /**
     * Clears the input fields.
     */
    private void clearFields() {
        cashierIDField.clear();
        newNameField.clear();
    }

     //Shows an alert dialog.
        //just a comment
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
