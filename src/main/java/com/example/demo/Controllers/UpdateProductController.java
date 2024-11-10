package com.example.demo.Controllers;

import com.example.demo.DAO.ProductDAO;
import com.example.demo.Models.Product;
import com.example.demo.Utils.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateProductController {
    @FXML
    private TextField productIdField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField priceField;

    private ProductDAO productDAO = new ProductDAO();

    @FXML
    private void handleSearchProduct() {
        int productId = Integer.parseInt(productIdField.getText());

        try {
            Product product = productDAO.getProductById(productId);
            if (product != null) {
                productNameField.setText(product.getProductName());
                priceField.setText(String.valueOf(product.getPrice()));
            } else {
                showAlert("Product not found", "No product with the given ID was found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while fetching the product.");
        }
    }

    @FXML
    private void handleUpdateProduct() {
        int productId = Integer.parseInt(productIdField.getText());
        String productName = productNameField.getText();
        double price = Double.parseDouble(priceField.getText());

        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setPrice(price);

        try {
            productDAO.updateProduct(product);
            showAlert("Success", "Product updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while updating the product.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void handleBack() throws IOException {
        try {
            SceneSwitcher.handleBackToMenu((Stage) productIdField.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
