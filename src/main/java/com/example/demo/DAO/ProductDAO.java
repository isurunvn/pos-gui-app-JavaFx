package com.example.demo.DAO;// DAO.ProductDAO.java

import com.example.demo.Models.Product;
import com.example.demo.Utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public void addProduct(Product product) throws SQLException {
        String query = "INSERT INTO product (ProductName, Price) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, product.getProductName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.executeUpdate();
        }
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setPrice(rs.getDouble("Price"));
                products.add(product);
            }
        }
        return products;
    }

    public Product getProductById(int productId) throws SQLException {
        String query = "SELECT * FROM Models.Product WHERE ProductID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setPrice(rs.getDouble("Price"));
                return product;
            }
        }
        return null;
    }

    public Product getProductByName(String productName) throws SQLException {
        String query = "SELECT * FROM product WHERE ProductName = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, productName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setPrice(rs.getDouble("Price"));
                return product;
            }
        }
        return null;
    }
    
    public void deleteProduct(int productId) throws SQLException {
        Connection connection = DBConnection.getConnection();

        // Find related OrderIDs in orderdetails table
        String findOrderIDsSQL = "SELECT OrderID FROM orderdetails WHERE ProductID = ?";
        List<Integer> orderIDs = new ArrayList<>();
        try (PreparedStatement findOrderIDsStmt = connection.prepareStatement(findOrderIDsSQL)) {
            findOrderIDsStmt.setInt(1, productId);
            ResultSet rs = findOrderIDsStmt.executeQuery();
            while (rs.next()) {
                orderIDs.add(rs.getInt("OrderID"));
            }
        }

        // Delete related rows in orderdetails table
        String deleteOrderDetailsSQL = "DELETE FROM orderdetails WHERE ProductID = ?";
        try (PreparedStatement deleteOrderDetailsStmt = connection.prepareStatement(deleteOrderDetailsSQL)) {
            deleteOrderDetailsStmt.setInt(1, productId);
            deleteOrderDetailsStmt.executeUpdate();
        }

        // Delete related rows in order table
        if (!orderIDs.isEmpty()) {
            String deleteOrderSQL = "DELETE FROM `order` WHERE OrderID = ?";
            try (PreparedStatement deleteOrderStmt = connection.prepareStatement(deleteOrderSQL)) {
                for (int orderId : orderIDs) {
                    deleteOrderStmt.setInt(1, orderId);
                    deleteOrderStmt.executeUpdate();
                }
            }
        }

        // Delete the product from product table
        String deleteProductSQL = "DELETE FROM product WHERE ProductID = ?";
        try (PreparedStatement deleteProductStmt = connection.prepareStatement(deleteProductSQL)) {
            deleteProductStmt.setInt(1, productId);
            deleteProductStmt.executeUpdate();
        }
    }

    public void updateProduct(Product product) throws SQLException {
    String query = "UPDATE product SET ProductName = ?, Price = ? WHERE ProductID = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        pstmt.setString(1, product.getProductName());
        pstmt.setDouble(2, product.getPrice());
        pstmt.setInt(3, product.getProductId());
        pstmt.executeUpdate();
    }
}
}