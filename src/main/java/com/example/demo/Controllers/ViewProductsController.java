package com.example.demo.Controllers;

import com.example.demo.Models.Shop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import com.example.demo.Models.Product;
import com.example.demo.DAO.ProductDAO;
import com.example.demo.Utils.SceneSwitcher;

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
    @FXML
    private TableColumn<Product, Void> actionsColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        setupActionsColumn();
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

    private void setupActionsColumn() {
        actionsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button updateButton = new Button("Update");
            private final Button deleteButton = new Button("Delete");
            private final HBox buttonBox = new HBox(5);

            {
                updateButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                deleteButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
                buttonBox.getChildren().addAll(updateButton, deleteButton);
                buttonBox.setPadding(new Insets(2));

                updateButton.setOnAction(event -> {
                    Product product = getTableRow().getItem();
                    handleUpdateProduct(product);
                });

                deleteButton.setOnAction(event -> {
                    Product product = getTableRow().getItem();
                    handleDeleteProduct(product);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(buttonBox);
                }
            }
        });
    }

    private void handleUpdateProduct(Product product) {
        if (product != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Product");
            alert.setHeaderText(null);
            alert.setContentText("Update product with ID: " + product.getProductId());
            alert.showAndWait();
            // Add your actual update logic here

            redirectToUpdateProductPage(product);
        }
    }

    ///////
    private void redirectToUpdateProductPage(Product product) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/updateProduct.fxml"));
            Parent root = loader.load();

            // Get the controller and pass the shop details
            UpdateProductController controller = loader.getController();
            controller.setProductDetails(product);

            Stage stage = (Stage) productsTable.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleDeleteProduct(Product product) {
        if (product != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Product");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete product: " + product.getProductName() + "?");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    ProductDAO productDAO = new ProductDAO();
                    try {
                        productDAO.deleteProduct(product.getProductId());
                        productsTable.getItems().remove(product);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

//    public void handleBack() throws IOException {
//        try {
//            SceneSwitcher.handleBackToMenu((Stage) productsTable.getScene().getWindow());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void handleAddProduct() {
        try {
            SceneSwitcher.switchScene((Stage) productsTable.getScene().getWindow(), "/com/example/demo/addProduct.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}