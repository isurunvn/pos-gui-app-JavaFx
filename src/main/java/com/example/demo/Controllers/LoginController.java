package com.example.demo.Controllers;

import com.example.demo.DAO.AdminDAO;
import com.example.demo.DAO.CashierDAO;
import com.example.demo.Models.Admin;
import com.example.demo.Models.Cashier;
import com.example.demo.Models.UserSession;
import com.example.demo.Models.UserSessionManager;
import com.example.demo.Utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox<String> roleComboBox;
    @FXML
    private Label errorLabel;

    private AdminDAO adminDAO = new AdminDAO();
    private CashierDAO cashierDAO = new CashierDAO();

    @FXML
    protected void handleLoginButtonAction(ActionEvent event) {
        String userName = userNameField.getText();
        String password = passwordField.getText();
        String role = roleComboBox.getValue();

        try {
            if ("admin".equals(role)) {
                Admin admin = adminDAO.validateAdmin(userName, password);
                if (admin != null) {
                    UserSession userSession = new UserSession(admin.getAdminID(), admin.getAdminName(), "admin");
                    UserSessionManager.getInstance().setCurrentSession(userSession);
                    SceneSwitcher.switchScene((Stage) userNameField.getScene().getWindow(), "/com/example/demo/adminMenu.fxml");
                } else {
                    errorLabel.setText("Invalid admin credentials. Please try again.");
                }
            } else if ("cashier".equals(role)) {
                Cashier cashier = cashierDAO.validateCashier(userName, password);
                if (cashier != null) {
                    UserSession userSession = new UserSession(cashier.getCashierID(), cashier.getCashierName(), "cashier");
                    UserSessionManager.getInstance().setCurrentSession(userSession);
                    SceneSwitcher.switchScene((Stage) userNameField.getScene().getWindow(), "/com/example/demo/cashierMenu.fxml");
                } else {
                    errorLabel.setText("Invalid cashier credentials. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("An error occurred. Please try again.");
        }
    }

}