package com.example.demo.DAO;

import com.example.demo.Models.Cashier;
import com.example.demo.Utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CashierDAO {
    public Cashier validateCashier(String cashierName, String password) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String query = "SELECT * FROM Cashier WHERE CashierName = ? AND Password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, cashierName);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        Cashier cashier = null;
        if (resultSet.next()) {
//            cashier = new Cashier(
//                    resultSet.getInt("CashierID"),
//                    resultSet.getString("CashierName"),
//                    resultSet.getString("Password"),
//                    resultSet.getInt("ShopID")
//            );
            cashier = new Cashier();
            cashier.setCashierID(resultSet.getInt("CashierID"));
            cashier.setCashierName(resultSet.getString("CashierName"));
            cashier.setPassword(resultSet.getString("Password"));
            cashier.setShopID(resultSet.getInt("ShopID"));
        }
        return cashier;
    }

    public void addCashier(Cashier cashier) throws SQLException {
        String query = "INSERT INTO Cashier (CashierName, Password, ShopID) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, cashier.getCashierName());
            pstmt.setString(2, cashier.getPassword());
            pstmt.setInt(3, cashier.getShopID());
            pstmt.executeUpdate();
        }
    }

    public Cashier getCashierById(int cashierId) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String query = "SELECT * FROM Cashier WHERE CashierID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, cashierId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Cashier cashier = null;
        if (resultSet.next()) {
            cashier = new Cashier();
            cashier.setCashierID(resultSet.getInt("CashierID"));
            cashier.setCashierName(resultSet.getString("CashierName"));
            cashier.setPassword(resultSet.getString("Password"));
            cashier.setShopID(resultSet.getInt("ShopID"));
//            cashier = new Cashier(
//                    resultSet.getInt("CashierID"),
//                    resultSet.getString("CashierName"),
//                    resultSet.getString("Password"),
//                    resultSet.getInt("ShopID")
//            );
        }
        return cashier;
    }

    public Cashier getCashierByName(String cashierName) throws SQLException {

        Connection connection = DBConnection.getConnection();
        String query = "SELECT * FROM Cashier WHERE CashierName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, cashierName);
        ResultSet resultSet = preparedStatement.executeQuery();
        Cashier cashier = null;
        if (resultSet.next()) {
//            cashier = new Cashier(
//                    resultSet.getInt("CashierID"),
//                    resultSet.getString("CashierName"),
//                    resultSet.getString("Password"),
//                    resultSet.getInt("ShopID")
//            );
            cashier = new Cashier();
            cashier.setCashierID(resultSet.getInt("CashierID"));
            cashier.setCashierName(resultSet.getString("CashierName"));
            cashier.setPassword(resultSet.getString("Password"));
            cashier.setShopID(resultSet.getInt("ShopID"));
        }
        return cashier;

    }

    public List<Cashier> getAllCashiers() throws SQLException {
        List<Cashier> cashiers = new ArrayList<>();
        String query = "SELECT * FROM Cashier";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
//                Cashier cashier = new Cashier(
//                        resultSet.getInt("CashierID"),
//                        resultSet.getString("CashierName"),
//                        resultSet.getString("Password"),
//                        resultSet.getInt("ShopID")
//                );

                Cashier cashier = new Cashier();
                cashier.setCashierID(resultSet.getInt("CashierID"));
                cashier.setCashierName(resultSet.getString("CashierName"));
                cashier.setPassword(resultSet.getString("Password"));
                cashier.setShopID(resultSet.getInt("ShopID"));

                ShopDAO shopDAO = new ShopDAO();
                cashier.setShopName(shopDAO.getShopById(cashier.getShopID()).getShopName());
                cashiers.add(cashier);
            }
        }
        return cashiers;
    }

    public void deleteCashier(int cashierId) throws SQLException {
        String query = "DELETE FROM Cashier WHERE CashierID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, cashierId);
            pstmt.executeUpdate();
        }
    }

    public void updateCashier(Cashier cashier) throws SQLException {
    String query = "UPDATE Cashier SET CashierName = ?, Password = ?, ShopID = ? WHERE CashierID = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        pstmt.setString(1, cashier.getCashierName());
        pstmt.setString(2, cashier.getPassword());
        pstmt.setInt(3, cashier.getShopID());
        pstmt.setInt(4, cashier.getCashierID());

        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        throw e;
    }
}
}
