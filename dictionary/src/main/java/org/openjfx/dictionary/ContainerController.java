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
    @FXML
    Button bookMarkButton;

    private AnchorPane anchorSearchPane;
    private AnchorPane anchorAddPane;
    private AnchorPane anchorBookMarkPane;

    private SearchPaneController searchController;
    private AddPaneController addController;
    private BookMarkPaneController bookMarkController;

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
        addController.reload();
        if (!content_pane.getChildren().contains(anchorAddPane)) {
            content_pane.getChildren().add(anchorAddPane);
        }
    }

    @FXML
    public void showBookMarkPane() {
        content_pane.getChildren().clear();
        bookMarkController.reload();
        if (!content_pane.getChildren().contains(anchorBookMarkPane)) {
            content_pane.getChildren().add(anchorBookMarkPane);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        main_pane.getChildren().add(addButton);
        main_pane.getChildren().add(searchButton);
        main_pane.getChildren().add(bookMarkButton);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchPane.fxml"));
            anchorSearchPane = fxmlLoader.load();
            searchController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addPane.fxml"));
            anchorAddPane = fxmlLoader.load();
            addController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bookMarkPane.fxml"));
            anchorBookMarkPane = fxmlLoader.load();
            bookMarkController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}