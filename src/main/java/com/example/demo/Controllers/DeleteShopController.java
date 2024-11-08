package com.example.demo.Controllers;

import com.example.demo.DAO.ShopDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DeleteShopController {
    @FXML
    private TextField shopIdField;

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
}