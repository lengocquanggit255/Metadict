package org.openjfx.dictionary.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContainerController implements Initializable {

    @FXML
    public AnchorPane content_pane;
    @FXML
    public AnchorPane main_pane;

    @FXML
    private Button addButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button bookMarkButton;
    @FXML
    private Button gameButton;
    @FXML
    private Button GoogleTranslateButton;

    private AnchorPane anchorSearchPane;
    private AnchorPane anchorAddPane;
    private AnchorPane anchorBookMarkPane;
    private AnchorPane anchorGoogleTranslatePane;

    private SearchPaneController searchController;
    private AddPaneController addController;
    private BookMarkPaneController bookMarkController;
    private GoogleTranslateController GoogleTranslateController;

    public Button buttonGetInfo = new Button();
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void showDefaultGameMenuPane(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/org/openjfx/dictionary/fxml/defaultGameMenuPane.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void showGoogleTranslatePane() {
        content_pane.getChildren().clear();
        GoogleTranslateController.reload();
        if (!content_pane.getChildren().contains(anchorGoogleTranslatePane)) {
            content_pane.getChildren().add(anchorGoogleTranslatePane);
        }
    }

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("/org/openjfx/dictionary/fxml/searchPane.fxml"));
            anchorSearchPane = fxmlLoader.load();
            searchController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/openjfx/dictionary/fxml/addPane.fxml"));
            anchorAddPane = fxmlLoader.load();
            addController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("/org/openjfx/dictionary/fxml/bookMarkPane.fxml"));
            anchorBookMarkPane = fxmlLoader.load();
            bookMarkController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("/org/openjfx/dictionary/fxml/GoogleTranslatePane.fxml"));
            anchorGoogleTranslatePane = fxmlLoader.load();
            GoogleTranslateController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}