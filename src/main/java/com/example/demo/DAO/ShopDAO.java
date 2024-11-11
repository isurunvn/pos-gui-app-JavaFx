package com.example.demo.DAO;

import com.example.demo.Models.Shop;
import com.example.demo.Utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopDAO {

    public void addShop(Shop shop) throws SQLException {
        String query = "INSERT INTO Shop (ShopName, Address) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, shop.getShopName());
            pstmt.setString(2, shop.getAddress());
            pstmt.executeUpdate();
        }
    }

    public Shop getShopById(int shopId) throws SQLException {
        String query = "SELECT * FROM Shop WHERE ShopId = ?";
        Shop shop = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, shopId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    shop = new Shop();
                    shop.setShopId(rs.getInt("ShopId"));
                    shop.setShopName(rs.getString("ShopName"));
                    shop.setAddress(rs.getString("Address"));
                }
            }
        }

        return shop;
    }

    public List<Shop> getAllShops() throws SQLException {
        List<Shop> shops = new ArrayList<>();
        String query = "SELECT * FROM Shop";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Shop shop = new Shop();
                shop.setShopId(rs.getInt("ShopId"));
                shop.setShopName(rs.getString("ShopName"));
                shop.setAddress(rs.getString("Address"));
                shops.add(shop);
            }
        }

        return shops;
    }

    public void updateShop(Shop shop) throws SQLException {
        String query = "UPDATE Shop SET ShopName = ?, Address = ? WHERE ShopId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, shop.getShopName());
            pstmt.setString(2, shop.getAddress());
            pstmt.setInt(3, shop.getShopId());
            pstmt.executeUpdate();
        }
    }

    public void deleteShop(int shopId) throws SQLException {
        String query = "DELETE FROM Shop WHERE ShopId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, shopId);
            pstmt.executeUpdate();
        }
    }
}