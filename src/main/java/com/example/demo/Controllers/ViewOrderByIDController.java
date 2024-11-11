package com.example.demo.Controllers;

import com.example.demo.DAO.OrderDAO;
import com.example.demo.Models.Order;
import com.example.demo.Models.OrderItem;
import com.example.demo.Utils.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewOrderByIDController {

    @FXML
    private TextField orderIdInput;
    @FXML
    private Label orderIdLabel;
    @FXML
    private TextField cashierIdInput;
    @FXML
    private Label totalLabel;
    @FXML
    private TableView<OrderItem> orderItemsTable;
    @FXML
    private TableColumn<OrderItem, Integer> productIdColumn;
    @FXML
    private TableColumn<OrderItem, String> productNameColumn;
    @FXML
    private TableColumn<OrderItem, Integer> quantityColumn;
    @FXML
    private TableColumn<OrderItem, Double> priceColumn;

    private OrderDAO orderDAO = new OrderDAO();

    @FXML
    private void initialize() {
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    @FXML
    private void handleSearchButton() {
        String orderIdText = orderIdInput.getText();
        if (orderIdText != null && !orderIdText.isEmpty()) {
            try {
                int orderId = Integer.parseInt(orderIdText);
                viewOrderById(orderId);
            } catch (NumberFormatException e) {
                // Handle invalid input
                showAlert("Invalid Order ID", "Please enter a valid numeric Order ID.");
            }
        } else {
            showAlert("Order ID Required", "Please enter an Order ID.");
        }
    }

    public void viewOrderById(int orderId) {
        Order order = orderDAO.getOrderById(orderId);
        if (order != null) {
            orderIdLabel.setText("Order ID: " + order.getOrderId());
            cashierIdInput.setText(String.valueOf(order.getCashierId()));
            totalLabel.setText("Total: " + order.getTotal());
            orderItemsTable.setItems(FXCollections.observableArrayList(order.getItems()));
        } else {
            showAlert("Order Not Found", "No order found with ID: " + orderId);
        }
    }

    @FXML
    private void handleBackButton() {
        // Implement navigation back to the previous screen
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void handleBack() throws IOException {
        try {
            SceneSwitcher.handleBackToMenu((Stage) orderItemsTable.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleUpdateOrder() {
    String orderIdText = orderIdInput.getText();
    String cashierIdText = cashierIdInput.getText();

    if (orderIdText != null && !orderIdText.isEmpty() && cashierIdText != null && !cashierIdText.isEmpty()) {
        try {
            int orderId = Integer.parseInt(orderIdText);
            int cashierId = Integer.parseInt(cashierIdText);

            Order order = orderDAO.getOrderById(orderId);
            if (order != null) {
                order.setCashierId(cashierId);
                orderDAO.updateOrder(order);
                showAlert("Success", "Cashier ID updated successfully.");
            } else {
                showAlert("Order Not Found", "No order found with ID: " + orderId);
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numeric values for Order ID and Cashier ID.");
        }
    } else {
        showAlert("Input Required", "Please enter both Order ID and Cashier ID.");
    }
}
}