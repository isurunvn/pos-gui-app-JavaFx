package com.example.demo.Controllers;

import com.example.demo.DAO.ProductDAO;
import com.example.demo.Models.Product;
import com.example.demo.Utils.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateProductController {
    @FXML
    private Label productIdLabel;

    @FXML
    private TextField productNameField;

    @FXML
    private TextField priceField;

    @FXML
    private Button backButton;

    @FXML
    private Button updateProductButton;

    private int productId;

    public void setProductDetails(Product product) {
        productIdLabel.setText(String.valueOf(product.getProductId()));
        productNameField.setText(product.getProductName());
        priceField.setText(String.valueOf(product.getPrice()));
    }

    public void handleBack() throws IOException {
        // Navigate back to the previous screen
        SceneSwitcher.handleBackPage((Stage) backButton.getScene().getWindow(), "/com/example/demo/viewProducts.fxml");
    }

    public void handleUpdateProduct() throws SQLException {
        int productId = Integer.parseInt(productIdLabel.getText());
        String productName = productNameField.getText().trim();
        String priceText = priceField.getText().trim();

        // Validate input
        if (productName.isEmpty() || priceText.isEmpty()) {
            showAlert("Error", "Please fill in all fields");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid price format");
            return;
        }

        try {
            // Create updated product object
            Product updatedProduct = new Product();
            updatedProduct.setProductId(productId);
            updatedProduct.setProductName(productName);
            updatedProduct.setPrice(price);

            ProductDAO productDAO = new ProductDAO();
            productDAO.updateProduct(updatedProduct);

            showAlert("Success", "Product details updated successfully");

            // Navigate back to product list or previous screen
            handleBack();
        } catch (Exception e) {
            showAlert("Error", "Failed to update product details: " + e.getMessage());
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}