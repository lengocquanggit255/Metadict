package org.openjfx.dictionary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContainerController implements Initializable {
    @FXML
    private AnchorPane content_pane;

    private AnchorPane anchorSearchPane;

    private void setContentPane(AnchorPane anchorPane) {
        content_pane.getChildren().setAll(anchorPane);
    }

    public void showSearchPane() {
        setContentPane(anchorSearchPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchPane.fxml"));
            anchorSearchPane = fxmlLoader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

        showSearchPane();
    }
}