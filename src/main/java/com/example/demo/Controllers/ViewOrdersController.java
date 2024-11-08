package com.example.demo.Controllers;

import com.example.demo.DAO.OrderDAO;
import com.example.demo.Models.Order;
import com.example.demo.Utils.DBConnection;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewOrdersController implements Initializable {
    @FXML
    private TableView<Order> ordersTable;
    @FXML
    private TableColumn<Order, Integer> orderIdColumn;
    @FXML
    private TableColumn<Order, Integer> cashierIdColumn;
    @FXML
    private TableColumn<Order, Double> totalColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        cashierIdColumn.setCellValueFactory(new PropertyValueFactory<>("cashierId"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        OrderDAO orderDAO = new OrderDAO();
        // Load orders data
        ObservableList<Order> orders = FXCollections.observableArrayList(orderDAO.getAllOrders());
        ordersTable.setItems(orders);
    }

    public void handleBack() throws IOException {
        try {
            SceneSwitcher.handleBackToMenu((Stage) ordersTable.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to fetch orders from the database
//    private List<Order> fetchOrdersFromDatabase() {
//        List<Order> orders = new ArrayList<>();
//        try {
//            // Establish a connection to the database
////            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");
//
//            Connection connection = DBConnection.getConnection();
//            Statement statement = connection.createStatement();
//            String query = "SELECT * FROM order";
//            ResultSet resultSet = statement.executeQuery(query);
//
//            // Iterate through the result set and create Order objects
//            while (resultSet.next()) {
//                int orderId = resultSet.getInt("order_id");
//                int cashierId = resultSet.getInt("cashier_id");
//                double total = resultSet.getDouble("total");
//                orders.add(new Order(orderId, cashierId, total));
//            }
//
//            // Close the connection
//            resultSet.close();
//            statement.close();
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return orders;
//    }
}