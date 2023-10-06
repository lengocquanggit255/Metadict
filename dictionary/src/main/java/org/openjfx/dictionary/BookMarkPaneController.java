package org.openjfx.dictionary;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class BookMarkPaneController implements Initializable {

    @FXML
    private TextField myTextField;

    @FXML
    private ListView<String> myListView;

    @FXML
    private WebView explainWebView;

    @FXML
    private Label targetLabel;

    @FXML
    private Button unMarkButton;

    @FXML
    private Button deleteButton;
    @FXML
    private Button speakButton;

    private String[] words = Helper.dictionary.getTargetOFMarked_word();
    private FilteredList<String> filteredList;

    @FXML
    public void speak() {
        String word = targetLabel.getText();
        Helper.speak(word);
    }

    @FXML
    public void delete() {
        String word = targetLabel.getText();
        Helper.dictionary.remove(word);
        reload();
    }

    @FXML
    public void unMarkWord() {
        if (targetLabel.getText().equals(""))
            return;
        Helper.dictionary.unMarkedWords(targetLabel.getText());
        reload();
        myTextField.requestFocus();
        myTextField.selectAll();
    }

    public void reload() {
        words = Helper.dictionary.getTargetOFMarked_word(); // Update the words array
        targetLabel.setText("");

        WebEngine explainWebEngine = explainWebView.getEngine();
        explainWebEngine.loadContent("");

        myTextField.setText("");
        ObservableList<String> wordsList = FXCollections.observableArrayList(Arrays.asList(words));
        filteredList = new FilteredList<>(wordsList);
        myListView.setItems(filteredList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> wordsList = FXCollections.observableArrayList(Arrays.asList(words));
        filteredList = new FilteredList<>(wordsList);

        myListView.setItems(filteredList);

        myTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("")) {
                targetLabel.setText("");
                WebEngine explainWebEngine = explainWebView.getEngine();
                explainWebEngine.loadContent("");
            }
            filterList(newValue);
        });

        myListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Platform.runLater(() -> myTextField.setText(newValue));

                WebEngine explainWebEngine = explainWebView.getEngine();
                explainWebEngine.loadContent(Helper.dictionary.getWord(newValue).getWord_explain());

                targetLabel.setText(Helper.dictionary.getWord(newValue).getWord_target());
            }
        });

        deleteButton.managedProperty().bind(targetLabel.textProperty().isNotEmpty());
        deleteButton.visibleProperty().bind(targetLabel.textProperty().isNotEmpty());

        unMarkButton.managedProperty().bind(targetLabel.textProperty().isNotEmpty());
        unMarkButton.visibleProperty().bind(targetLabel.textProperty().isNotEmpty());
    }

    private void filterList(String searchText) {
        filteredList.setPredicate(word -> {
            if (searchText == null || searchText.isEmpty()) {
                return true;
            }

            String lowercaseSearchText = searchText.toLowerCase();
            return word.toLowerCase().contains(lowercaseSearchText);
        });
    }

}
