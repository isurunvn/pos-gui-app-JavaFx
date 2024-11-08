package com.example.demo.Utils;

import com.example.demo.Models.UserSession;
import com.example.demo.Models.UserSessionManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {
    /**
     * Loads a new FXML file and sets it as the current scene.
     *
     * @param stage The current stage where the scene is to be set.
     * @param fxmlPath The path to the FXML file to be loaded.
     * @param dimensions The desired width of the new scene.
     * @throws IOException If there is an issue loading the FXML file.
     */
    public static void switchScene(Stage stage, String fxmlPath, int... dimensions) throws IOException {
    double width = (dimensions.length > 0) ? dimensions[0] : 1000;
    double height = (dimensions.length > 1) ? dimensions[1] : 700;

    FXMLLoader fxmlLoader = new FXMLLoader(SceneSwitcher.class.getResource(fxmlPath));
    Scene scene = new Scene(fxmlLoader.load(), width, height);
    stage.setScene(scene);
    stage.show();
}

    public static void handleBackToMenu(Stage stage) throws IOException {
    UserSession currentSession = UserSessionManager.getInstance().getCurrentSession();
    if (currentSession == null) {
        throw new IllegalStateException("No active user session found.");
    }

    String role = currentSession.getRole();
    String fxmlPath;

    if ("admin".equals(role)) {
        fxmlPath = "/com/example/demo/adminMenu.fxml";
    } else if ("cashier".equals(role)) {
        fxmlPath = "/com/example/demo/cashierMenu.fxml";
    } else {
        throw new IllegalArgumentException("Unknown role: " + role);
    }

    switchScene(stage, fxmlPath, 1000, 700);
}
}
