package com.example.demo.Controllers;

import com.example.demo.DAO.ShopDAO;
import com.example.demo.Models.Shop;
import com.example.demo.Utils.SceneSwitcher;
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

public class ViewShopsController implements Initializable {

    @FXML
    private TableView<Shop> shopsTable;

    @FXML
    private TableColumn<Shop, Integer> shopIdColumn;

    @FXML
    private TableColumn<Shop, String> shopNameColumn;

    @FXML
    private TableColumn<Shop, String> addressColumn;
    @FXML
    private TableColumn<Shop, Void> actionsColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shopIdColumn.setCellValueFactory(new PropertyValueFactory<>("shopId"));
        shopNameColumn.setCellValueFactory(new PropertyValueFactory<>("shopName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        // Set up the actions column
        setupActionsColumn();

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

//    public void handleBack() throws IOException {
//        try {
//            SceneSwitcher.handleBackToMenu((Stage) shopsTable.getScene().getWindow());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }




    ///////////////
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
                    Shop shop = getTableRow().getItem();
                    handleUpdateShop(shop);
                });

                deleteButton.setOnAction(event -> {
                    Shop shop = getTableRow().getItem();
                    handleDeleteShop(shop);
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

    public void handleAddShop() {
        try {
            SceneSwitcher.switchScene((Stage) shopsTable.getScene().getWindow(), "/com/example/demo/addShop.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleUpdateShop(Shop shop) {
        // Implement update logic here
        if (shop != null) {
            // Example: Show update dialog
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Shop");
            alert.setHeaderText(null);
            alert.setContentText("Update shop with ID: " + shop.getShopId());
            alert.showAndWait();

            // Add your actual update logic here
            redirectToUpdateShopPage(shop);
        }
    }

    ///////
    private void redirectToUpdateShopPage(Shop shop) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/updateShop.fxml"));
            Parent root = loader.load();

            // Get the controller and pass the shop details
            UpdateShopController controller = loader.getController();
            controller.setShopDetails(shop);

            Stage stage = (Stage) shopsTable.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleDeleteShop(Shop shop) {
        // Implement delete logic here
        if (shop != null) {
            // Show confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Shop");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete shop: " + shop.getShopName() + "?");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Add your actual delete logic here
                    shopsTable.getItems().remove(shop);
                    ShopDAO shopDAO = new ShopDAO();
                    try {
                        shopDAO.deleteShop(shop.getShopId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }

}