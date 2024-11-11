package com.example.demo.Controllers;

import com.example.demo.Models.UserSessionManager;
import com.example.demo.Utils.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HeaderController implements Initializable {
    @FXML
    private Label userRole;
    @FXML
    private Label userName;
    @FXML
    private Button logoutButton;

    public void initialize(URL location, ResourceBundle resources) {
        // Set the username label
        String Username = UserSessionManager.getInstance().getCurrentSession().getUserName();
        String UserRole = UserSessionManager.getInstance().getCurrentSession().getRole();
        userName.setText( Username);
        userRole.setText(UserRole + ": ");

    }

    //Logout function
    public void handleLogout() throws IOException {
        UserSessionManager.getInstance().clearSession();
        SceneSwitcher.switchScene((Stage) logoutButton.getScene().getWindow(), "/com/example/demo/login.fxml");
    }

}