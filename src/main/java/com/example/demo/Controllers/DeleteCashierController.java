package com.example.demo.Controllers;

import com.example.demo.DAO.CashierDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DeleteCashierController {
    @FXML
    private TextField cashierIdField;

    public void handleDeleteCashier() {
        int cashierId = Integer.parseInt(cashierIdField.getText());
        CashierDAO cashierDAO = new CashierDAO();
        try {
            cashierDAO.deleteCashier(cashierId);
            System.out.println("Cashier deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}