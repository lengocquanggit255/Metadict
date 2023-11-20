package org.openjfx.dictionary;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class levelGamePaneController {
    @FXML
    private Button easyButton, mediumButton, hardButton;
    @FXML
    private Button backButton;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void backToDefaultMenuButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("defaultGameMenuPane.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void chooseModeEasy() {

    }

    @FXML
    private void chooseModeMedium() {

    }

    @FXML
    private void chooseModeHard() {

    }

}
