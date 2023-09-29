package org.openjfx.dictionary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.openjfx.dictionary.cmd.Dictionary;

public class ContainerController implements Initializable {
    public static Dictionary dictionary = new Dictionary();
    @FXML
    private AnchorPane content_pane;
    @FXML
    private AnchorPane main_pane;

    @FXML
    Button addButton;
    @FXML
    Button searchButton;

    private AnchorPane anchorSearchPane;
    private AnchorPane otherAnchorPane;

    private SearchPaneController searchController;

    @FXML
    public void showSearchPane() {
        content_pane.getChildren().clear();
        searchController.reload();
        if (!content_pane.getChildren().contains(anchorSearchPane)) {
            content_pane.getChildren().add(anchorSearchPane);
        }
    }

    @FXML
    public void showAddPane() {
        content_pane.getChildren().clear();
        if (!content_pane.getChildren().contains(otherAnchorPane)) {
            content_pane.getChildren().add(otherAnchorPane);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        main_pane.getChildren().add(addButton);
        main_pane.getChildren().add(searchButton);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchPane.fxml"));
            anchorSearchPane = fxmlLoader.load();
            searchController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addPane.fxml"));
            otherAnchorPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}