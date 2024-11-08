package com.example.demo.Controllers;

import com.example.demo.DAO.OrderDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DeleteOrderController {

    @FXML
    private TextField orderIdField;

    private OrderDAO orderDAO = new OrderDAO();

    @FXML
    private void handleDeleteButton() {
        int orderId = Integer.parseInt(orderIdField.getText());
        orderDAO.deleteOrderById(orderId);
        // Optionally, show a confirmation message or navigate back
    }

    @FXML
    private void handleBackButton() {
        // Implement navigation back to the previous screen
    }

}