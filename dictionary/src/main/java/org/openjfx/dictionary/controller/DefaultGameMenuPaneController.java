package org.openjfx.dictionary.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class DefaultGameMenuPaneController {
    @FXML
    private Button playGameButton, levelGameButton, homeButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void startGame(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/org/openjfx/dictionary/fxml/menuGamePane.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void chooseLevelGame(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/org/openjfx/dictionary/fxml/levelGamePane.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void comeBackHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/org/openjfx/dictionary/fxml/container.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void mouseExitButton(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle(
                "-fx-border-radius: 100px; -fx-background-radius: 100px; -fx-background-color: TRANSPARENT; -fx-border-color: TRANSPARENT;");
    }

    @FXML
    private void mouseEnterButton(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle(
                "-fx-border-radius: 100px; -fx-background-radius: 100px; -fx-background-color: TRANSPARENT; -fx-border-color: WHITE;");
    }
}
