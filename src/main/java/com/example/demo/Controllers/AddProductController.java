package com.example.demo.Controllers;

import com.example.demo.DAO.ProductDAO;
import com.example.demo.Models.Product;
import com.example.demo.Utils.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddProductController {

    @FXML
    private TextField productNameField;

    @FXML
    private TextField priceField;

    @FXML
    private void handleCreateProduct() {
        String productName = productNameField.getText();
        String priceText = priceField.getText();

        if (productName.isEmpty() || priceText.isEmpty()) {
            // Show error message
            System.out.println("Please fill in all fields");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            // Show error message
            System.out.println("Invalid price format");
            return;
        }

        Product product = new Product();
        product.setProductName(productName);
        product.setPrice(price);
        ProductDAO productDAO = new ProductDAO();

        try {
            productDAO.addProduct(product);
            // Navigate back to the previous screen
            handleBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleBack() throws IOException {
        // Navigate back to the previous screen
        SceneSwitcher.handleBackPage((Stage) productNameField.getScene().getWindow(), "/com/example/demo/viewProducts.fxml");
    }
}