// CashierViewProductsController.java
package com.example.demo.Controllers;

import com.example.demo.DAO.ProductDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import com.example.demo.Models.Product;
import com.example.demo.Utils.SceneSwitcher;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CashierViewProductsController {

    @FXML
    private TableView<Product> productsTable;

    @FXML
    private TableColumn<Product, Integer> productIdColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    public void initialize() {
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Load product data
        loadProducts();
    }

    private void loadProducts() {
        ProductDAO productDAO = new ProductDAO();
        try {
            List<Product> products = productDAO.getAllProducts();
            ObservableList<Product> productObservableList = FXCollections.observableArrayList(products);
            productsTable.setItems(productObservableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void handleBack() {
        try {
            SceneSwitcher.switchScene((Stage) productsTable.getScene().getWindow(), "/com/example/demo/cashierMenu.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}