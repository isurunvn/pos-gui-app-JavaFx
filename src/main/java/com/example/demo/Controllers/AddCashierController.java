package com.example.demo.Controllers;

import com.example.demo.DAO.CashierDAO;
import com.example.demo.Models.Cashier;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AddCashierController {

    @FXML
    private TextField cashierNameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField shopIdField;

    @FXML
    private void handleAddCashier() {
        String cashierName = cashierNameField.getText();
        String password = passwordField.getText();
        int shopId = Integer.parseInt(shopIdField.getText());

        Cashier cashier = new Cashier(0, cashierName, password, shopId);
        CashierDAO cashierDAO = new CashierDAO();

        try {
            cashierDAO.addCashier(cashier);
            System.out.println("Cashier added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}