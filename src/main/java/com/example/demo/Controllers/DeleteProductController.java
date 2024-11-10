package com.example.demo.Controllers;

import com.example.demo.DAO.ProductDAO;
import com.example.demo.Utils.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class DeleteProductController {
    @FXML
    private TextField productIdField;

    @FXML
    private void handleRemoveProduct() {
        int productId = Integer.parseInt(productIdField.getText());
        ProductDAO productDAO = new ProductDAO();
        try {
            productDAO.removeProduct(productId);
            System.out.println("Product removed successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to remove product");
        }
    }

    public void handleBack() throws IOException {
        try {
            SceneSwitcher.handleBackToMenu((Stage) productIdField.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}