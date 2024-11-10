package com.example.demo.DAO;

import com.example.demo.Models.Order;
import com.example.demo.Models.OrderItem;
import com.example.demo.Models.Product;
import com.example.demo.Utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public void saveOrder(Order order) {
        String orderSQL = "INSERT INTO `order` (OrderID, CashierID, Total) VALUES (?, ?, ?)";
        String orderItemSQL = "INSERT INTO orderdetails (OrderID, ProductID, Quantity) VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection()) {
            // Save the Order
            try (PreparedStatement orderStmt = connection.prepareStatement(orderSQL, Statement.RETURN_GENERATED_KEYS)) {
                orderStmt.setInt(1, order.getOrderId());
                orderStmt.setInt(2, order.getCashierId());
                orderStmt.setDouble(3, order.getTotal());
                orderStmt.executeUpdate();

                // Get generated Order ID if it's auto-incremented
                ResultSet generatedKeys = orderStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    order.setOrderId(generatedKeys.getInt(1));
                }

                // Save each OrderItem
                try (PreparedStatement orderItemStmt = connection.prepareStatement(orderItemSQL)) {
                    for (OrderItem item : order.getItems()) {
                        orderItemStmt.setInt(1, order.getOrderId());
                        orderItemStmt.setInt(2, item.getProduct().getProductId());
                        orderItemStmt.setInt(3, item.getQuantity());
                        orderItemStmt.addBatch();
                    }
                    orderItemStmt.executeBatch();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Order getOrderById(int orderId) {
        String orderSQL = "SELECT * FROM `order` WHERE OrderID = ?";
        String orderItemSQL = "SELECT * FROM orderdetails JOIN product ON orderdetails.ProductID = product.ProductID WHERE OrderID = ?";
        Order order = null;

        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement orderStmt = connection.prepareStatement(orderSQL)) {
                orderStmt.setInt(1, orderId);
                ResultSet orderResult = orderStmt.executeQuery();

                if (orderResult.next()) {
                    int cashierId = orderResult.getInt("CashierID");
                    double total = orderResult.getDouble("Total");

                    order = new Order(cashierId);
                    order.setOrderId(orderId);
                    order.setTotal(total);

                    // Get OrderItems
                    try (PreparedStatement orderItemStmt = connection.prepareStatement(orderItemSQL)) {
                        orderItemStmt.setInt(1, orderId);
                        ResultSet itemResult = orderItemStmt.executeQuery();

                        List<OrderItem> items = new ArrayList<>();
                        while (itemResult.next()) {
                            int productId = itemResult.getInt("ProductID");
                            String productName = itemResult.getString("ProductName");
                            double productPrice = itemResult.getDouble("Price");
                            int quantity = itemResult.getInt("Quantity");

                            Product product = new Product();
                            product.setProductId(productId);
                            product.setProductName(productName);
                            product.setPrice(productPrice);
                            items.add(new OrderItem(product, quantity));
                        }
                        order.getItems().addAll(items);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public List<Order> getAllOrders() {
        String orderSQL = "SELECT * FROM `order`";
        List<Order> orders = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(orderSQL)) {

            while (resultSet.next()) {
                int orderId = resultSet.getInt("OrderID");
                int cashierId = resultSet.getInt("CashierID");
                double total = resultSet.getDouble("Total");
                String cashierName = null;
                try (Statement cashierStatement = connection.createStatement();
                     ResultSet cashierResult = cashierStatement.executeQuery("SELECT CashierName FROM cashier WHERE CashierID = " + cashierId)) {
                    if (cashierResult.next()) {
                        cashierName = cashierResult.getString("CashierName");
                    }
                }

                Order order = new Order(cashierId);
                order.setOrderId(orderId);
                order.setTotal(total);
                order.setCashierName(cashierName);
                orders.add(order);

//                Order order = getOrderById(orderId);
//                if (order != null) {
//                    orders.add(order);
//                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public void deleteOrderById(int orderId) {
        String orderSQL = "DELETE FROM `order` WHERE OrderID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(orderSQL)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
