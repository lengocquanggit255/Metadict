package org.openjfx.dictionary.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class StartController {

    @FXML
    private Button startButton;

    @FXML
    void start(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("/org/openjfx/dictionary/fxml/container.fxml"));
            // FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
            Parent root = fxmlLoader.load();
            // Create a new scene
            Scene scene = new Scene(root);

            // Get the current stage
            Stage stage = (Stage) startButton.getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(scene);

            // Center the stage on the screen
            stage.centerOnScreen();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void mouseEnterStartButton() {
        startButton.setStyle(
                "-fx-background-color: TRANSPARENT; -fx-background-radius: 50px; -fx-border-radius: 50px; -fx-border-color: WHITE;");
    }

    @FXML
    private void mouseEnterExitButton() {
        startButton.setStyle(
                "-fx-background-color: TRANSPARENT; -fx-background-radius: 50px; -fx-border-radius: 50px; -fx-border-color: TRANSPARENT;");
    }
}