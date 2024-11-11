package com.example.demo.Controllers;

import com.example.demo.DAO.ProductDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class DeleteProductController {
    @FXML
    private TextField productIdField;

    @FXML
    private void handleRemoveProduct() {
        int productId = Integer.parseInt(productIdField.getText());
        ProductDAO productDAO = new ProductDAO();
        try {
            productDAO.deleteProduct(productId);
            System.out.println("Product removed successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to remove product");
        }
    }
}