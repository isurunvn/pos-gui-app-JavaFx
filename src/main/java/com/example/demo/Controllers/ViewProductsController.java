package com.example.demo.Controllers;

import com.example.demo.DAO.ProductDAO;
import com.example.demo.Models.Product;
import com.example.demo.Utils.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ViewProductsController implements Initializable {
    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product, Integer> productIdColumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

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

    public void handleBack() throws IOException {
        try {
            SceneSwitcher.handleBackToMenu((Stage) productsTable.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}