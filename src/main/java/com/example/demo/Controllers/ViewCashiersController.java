package com.example.demo.Controllers;

import com.example.demo.DAO.CashierDAO;
import com.example.demo.Models.Cashier;
import com.example.demo.Models.Shop;
import com.example.demo.Utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
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
    private TableColumn<Cashier, String> shopNameColumn;
    @FXML
    private TableColumn<Cashier, Void> actionsColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cashierIdColumn.setCellValueFactory(new PropertyValueFactory<>("CashierID"));
        cashierNameColumn.setCellValueFactory(new PropertyValueFactory<>("CashierName"));
        shopNameColumn.setCellValueFactory(new PropertyValueFactory<>("ShopName"));

        // Set up the actions column
        setupActionsColumn();

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

//    public void handleBack() throws IOException {
//        try {
//            SceneSwitcher.handleBackToMenu((Stage) cashiersTable.getScene().getWindow());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public void handleAddCashier(){
        try {
            SceneSwitcher.switchScene((Stage) cashiersTable.getScene().getWindow(), "/com/example/demo/addCashier.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupActionsColumn() {
    actionsColumn.setCellFactory(param -> new TableCell<>() {
        private final Button updateButton = new Button("Update");
        private final Button deleteButton = new Button("Delete");
        private final HBox buttonBox = new HBox(5); // 5 is the spacing between buttons

        {
            // Style the buttons
            updateButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
            deleteButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

            buttonBox.getChildren().addAll(updateButton, deleteButton);
            buttonBox.setPadding(new Insets(2));

            // Add button action handlers
            updateButton.setOnAction(event -> {
                Cashier cashier = getTableRow().getItem();
                handleUpdateCashier(cashier);
            });

            deleteButton.setOnAction(event -> {
                Cashier cashier = getTableRow().getItem();
                handleDeleteCashier(cashier);
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

private void handleUpdateCashier(Cashier cashier) {
    // Implement update logic here
    if (cashier != null) {
        // Example: Show update dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update Cashier");
        alert.setHeaderText(null);
        alert.setContentText("Update cashier with ID: " + cashier.getCashierID());
        alert.showAndWait();

        // Add your actual update logic here
        redirectToUpdateCashierPage(cashier);
    }
}

private void redirectToUpdateCashierPage(Cashier cashier) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/updateCashier.fxml"));
        Parent root = loader.load();

        // Get the controller and pass the cashier details
        UpdateCashierController controller = loader.getController();
        controller.setCashierDetails(cashier);

        Stage stage = (Stage) cashiersTable.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private void handleDeleteCashier(Cashier cashier) {
    // Implement delete logic here
    if (cashier != null) {
        // Show confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Cashier");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete cashier: " + cashier.getCashierName() + "?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Add your actual delete logic here
                cashiersTable.getItems().remove(cashier);
                CashierDAO cashierDAO = new CashierDAO();
                try {
                    cashierDAO.deleteCashier(cashier.getCashierID());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
}