package com.example.demo.DAO;

import com.example.demo.Models.Admin;
import com.example.demo.Utils.DBConnection;

import java.sql.*;

public class AdminDAO {

    public Admin getAdminByName(String AdminName) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String query = "SELECT * FROM Admin WHERE AdminName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, AdminName);
        ResultSet resultSet = preparedStatement.executeQuery();
        Admin admin = null;
        if (resultSet.next()) {
            admin = new Admin(
                    resultSet.getInt("AdminID"),
                    resultSet.getString("AdminName"),
                    resultSet.getString("Password")
            );
        }
        return admin;
    }

    public Admin validateAdmin(String adminName, String password) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String query = "SELECT * FROM Admin WHERE AdminName = ? AND Password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, adminName);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        Admin admin = null;
        if (resultSet.next()) {
            admin = new Admin(
                    resultSet.getInt("AdminID"),
                    resultSet.getString("AdminName"),
                    resultSet.getString("Password")
            );
        }
        return admin;
    }
}
