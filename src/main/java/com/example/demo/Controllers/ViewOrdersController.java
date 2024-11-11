package com.example.demo.Controllers;

import com.example.demo.DAO.OrderDAO;
import com.example.demo.Models.Order;
import com.example.demo.Models.Product;
import com.example.demo.Utils.SceneSwitcher;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewOrdersController implements Initializable {
    @FXML
    private Label userName;
    @FXML
    private TableView<Order> ordersTable;
    @FXML
    private TableColumn<Order, Integer> orderIdColumn;
    @FXML
    private TableColumn<Order, String> shopColumn;
    @FXML
    private TableColumn<Order, Integer> cashierIdColumn;
    @FXML
    private TableColumn<Order, Double> totalColumn;
    @FXML
    private TableColumn<Order, Void> actionsColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        cashierIdColumn.setCellValueFactory(new PropertyValueFactory<>("cashierName"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
//        shopColumn.setCellValueFactory(new PropertyValueFactory<>("shopName"));

        OrderDAO orderDAO = new OrderDAO();
        ObservableList<Order> orders = FXCollections.observableArrayList(orderDAO.getAllOrders());
        ordersTable.setItems(orders);

        setupActionsColumn();
    }

    private void setupActionsColumn() {
        actionsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button viewButton = new Button("View");
            private final Button deleteButton = new Button("Delete");
            private final HBox buttonBox = new HBox(5);

            {
                viewButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                deleteButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
                buttonBox.getChildren().addAll(viewButton, deleteButton);
                buttonBox.setPadding(new Insets(2));

                viewButton.setOnAction(event -> {
                    Order order = getTableRow().getItem();
                    handleViewOrder(order);
                });

                deleteButton.setOnAction(event -> {
                    Order order = getTableRow().getItem();
                    handleDeleteOrder(order);
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

    private void handleDeleteOrder(Order order) {
    if (order != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Order");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete order with ID: " + order.getOrderId() + "?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                OrderDAO orderDAO = new OrderDAO();
                orderDAO.deleteOrderById(order.getOrderId());
                ordersTable.getItems().remove(order);
            }
        });
    }
}

    private void handleViewOrder(Order order) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/viewOrderById.fxml"));
        Parent root = loader.load();

        ViewOrderByIDController controller = loader.getController();
        controller.viewOrderById(order.getOrderId());

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("View Order Details");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public void handleBack() {
        try {
            SceneSwitcher.handleBackToMenu((Stage) ordersTable.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}