package com.example.demo.Controllers;

import com.example.demo.DAO.ProductDAO;
import com.example.demo.Models.Product;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AddProductController {
    @FXML
    private TextField productNameField;
    @FXML
    private TextField priceField;

    @FXML
    private void handleAddProduct() {
        String productName = productNameField.getText();
        double price = Double.parseDouble(priceField.getText());

        Product product = new Product();
        product.setProductName(productName);
        product.setPrice(price);

        ProductDAO productDAO = new ProductDAO();
        try {
            productDAO.addProduct(product);
            System.out.println("Product added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}