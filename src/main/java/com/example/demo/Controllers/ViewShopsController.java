package com.example.demo.Controllers;

import com.example.demo.DAO.ShopDAO;
import com.example.demo.Models.Shop;
import com.example.demo.Utils.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ViewShopsController implements Initializable {

    @FXML
    private TableView<Shop> shopsTable;

    @FXML
    private TableColumn<Shop, Integer> shopIdColumn;

    @FXML
    private TableColumn<Shop, String> shopNameColumn;

    @FXML
    private TableColumn<Shop, String> addressColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shopIdColumn.setCellValueFactory(new PropertyValueFactory<>("shopId"));
        shopNameColumn.setCellValueFactory(new PropertyValueFactory<>("shopName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        loadShops();
    }

    private void loadShops() {
        ShopDAO shopDAO = new ShopDAO();
        try {
            List<Shop> shops = shopDAO.getAllShops();
            shopsTable.getItems().setAll(shops);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void handleBack() throws IOException {
        try {
            SceneSwitcher.handleBackToMenu((Stage) shopsTable.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}