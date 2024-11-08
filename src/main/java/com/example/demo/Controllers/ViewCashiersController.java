package com.example.demo.Controllers;

import com.example.demo.DAO.CashierDAO;
import com.example.demo.Models.Cashier;
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

public class ViewCashiersController implements Initializable {

    @FXML
    private TableView<Cashier> cashiersTable;

    @FXML
    private TableColumn<Cashier, Integer> cashierIdColumn;

    @FXML
    private TableColumn<Cashier, String> cashierNameColumn;

    @FXML
    private TableColumn<Cashier, String> passwordColumn;

    @FXML
    private TableColumn<Cashier, Integer> shopIdColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cashierIdColumn.setCellValueFactory(new PropertyValueFactory<>("CashierID"));
        cashierNameColumn.setCellValueFactory(new PropertyValueFactory<>("CashierName"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));
        shopIdColumn.setCellValueFactory(new PropertyValueFactory<>("ShopID"));

        loadCashiers();
    }

    private void loadCashiers() {
        CashierDAO cashierDAO = new CashierDAO();
        try {
            List<Cashier> cashiers = cashierDAO.getAllCashiers();
            cashiersTable.getItems().setAll(cashiers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void handleBack() throws IOException {
        try {
            SceneSwitcher.handleBackToMenu((Stage) cashiersTable.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}