package com.example.demo.Controllers;

import com.example.demo.DAO.OrderDAO;
import com.example.demo.Models.Order;
import com.example.demo.Models.UserSessionManager;
import com.example.demo.Utils.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminHeaderController implements Initializable {
    @FXML
    private Label userName;
    @FXML
    private Button logoutButton;

    public void initialize(URL location, ResourceBundle resources) {
        // Set the username label
        String Username = UserSessionManager.getInstance().getCurrentSession().getUserName();
        userName.setText( Username);


    }

    //Logout function
    public void handleLogout() throws IOException {
        UserSessionManager.getInstance().clearSession();
        SceneSwitcher.switchScene((Stage) logoutButton.getScene().getWindow(), "/com/example/demo/login.fxml");
    }

}