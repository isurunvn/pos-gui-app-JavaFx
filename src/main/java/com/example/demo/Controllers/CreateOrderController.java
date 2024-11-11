package com.example.demo.Controllers;

import com.example.demo.Models.Order;
import com.example.demo.Models.OrderItem;
import com.example.demo.Models.Product;
import com.example.demo.DAO.OrderDAO;
import com.example.demo.DAO.ProductDAO;
import com.example.demo.Models.UserSessionManager;
import com.example.demo.Utils.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CreateOrderController {
    @FXML
    private ComboBox<String> productComboBox;
    @FXML
    private TextField quantityField;
    @FXML
    private TableView<OrderItem> orderItemsTable;
    @FXML
    private TableColumn<OrderItem, String> productColumn;
    @FXML
    private TableColumn<OrderItem, Integer> quantityColumn;

    private ObservableList<OrderItem> orderItems;

    @FXML
    public void initialize() throws SQLException {
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.getAllProducts();

        List<String> productNames = products.stream()
                .map(Product::getProductName)
                .toList();
        productComboBox.getItems().addAll(productNames);

        orderItems = FXCollections.observableArrayList();
        orderItemsTable.setItems(orderItems);

        productColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    @FXML
    public void handleAddItem() throws SQLException {
        String selectedProductName = productComboBox.getValue();
        int quantity = Integer.parseInt(quantityField.getText());

        ProductDAO productDAO = new ProductDAO();
        Product selectedProduct = productDAO.getProductByName(selectedProductName);

        OrderItem orderItem = new OrderItem(selectedProduct, quantity);
        orderItems.add(orderItem);

        productComboBox.setValue(null);
        quantityField.clear();
    }

    @FXML
    public void handleSubmitOrder() throws SQLException {
        UserSessionManager userSessionManager = UserSessionManager.getInstance();
        int cashierId = userSessionManager.getCurrentSession().getUserID();
        Order order = new Order(cashierId); // Assuming cashierId is 1 for simplicity
        for (OrderItem item : orderItems) {
            order.addItem(item.getProduct(), item.getQuantity());
        }

        OrderDAO orderDAO = new OrderDAO();
        orderDAO.saveOrder(order);

        System.out.println("Order saved successfully!");
        // Close the window after saving the order

        //clear all fields
        orderItems.clear();

    }

    public void handleBack() {
        try {
            SceneSwitcher.switchScene((Stage) orderItemsTable.getScene().getWindow(), "/com/example/demo/cashierMenu.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}