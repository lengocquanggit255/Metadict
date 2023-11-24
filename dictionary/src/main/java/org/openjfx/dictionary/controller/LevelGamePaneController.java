package org.openjfx.dictionary.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LevelGamePaneController {
    @FXML
    private Button easyButton, mediumButton, hardButton;
    @FXML
    private Button backButton;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private static int modeNumber = 1;
    private boolean checkStatusButton = false;
    private static Button tempButton = null;

    @FXML
    private void backToDefaultMenuButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/org/openjfx/dictionary/fxml/defaultGameMenuPane.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void chooseModeEasy(ActionEvent event) {
        Button button = (Button) event.getSource();
        if (tempButton != null) resetColorButton(tempButton);
        tempButton = button;
        setColorButton(tempButton);
        checkStatusButton = true;
        modeNumber = 1;
    }

    @FXML
    private void chooseModeMedium(ActionEvent event) {
        Button button = (Button) event.getSource();
        if (tempButton != null) resetColorButton(tempButton);
        tempButton = button;
        setColorButton(tempButton);
        checkStatusButton = true;
        modeNumber = 2;
    }

    @FXML
    private void chooseModeHard(ActionEvent event) {
        Button button = (Button) event.getSource();
        if (tempButton != null) resetColorButton(tempButton);
        tempButton = button;
        setColorButton(tempButton);
        checkStatusButton = true;
        modeNumber = 3;
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

    @FXML
    private void mouseExitModeButton(MouseEvent event) {
        if (!checkStatusButton) {
            Button button = (Button) event.getSource();
            button.setStyle("-fx-background-radius: 30px; -fx-background-color:  #03A9F4;");
        }
    }

    @FXML
    private void mouseEnterModeButton(MouseEvent event) {
        if (!checkStatusButton) {
            Button button = (Button) event.getSource();
            button.setStyle("-fx-background-radius: 30px; -fx-background-color: #84CBF4;");
        }
    }

    private static void setColorButton(Button button) {
        button.setStyle("-fx-background-radius: 30px; -fx-background-color:  #032D59;");
    }

    private void resetColorButton(Button button) {
        button.setStyle("-fx-background-radius: 30px; -fx-background-color:  #03A9F4;");
    }

    public static int getModeNumber() {
        return modeNumber;
    }
}
