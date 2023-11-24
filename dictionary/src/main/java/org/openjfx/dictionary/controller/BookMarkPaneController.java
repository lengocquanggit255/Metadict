package org.openjfx.dictionary.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import org.openjfx.dictionary.cmd.Helper;
import org.w3c.dom.Document;

import com.voicerss.tts.Languages;

public class BookMarkPaneController implements Initializable {

    @FXML
    private TextField myTextField;

    @FXML
    private ListView<String> myListView;

    @FXML
    private WebView explainWebView;

    @FXML
    private Button unMarkButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button speakButton;

    @FXML
    private HTMLEditor editor;
    @FXML
    private Button editButton;

    @FXML
    private Button saveButton;

    private String[] words = Helper.dictionary.getTargetOFMarked_word();
    private FilteredList<String> filteredList;
    private String currentSelectedWord = "";

    @FXML
    private void save() {
        editor.setDisable(true);
        editor.setVisible(false);
        saveButton.setDisable(true);
        saveButton.setVisible(false);
        deleteButton.setDisable(false);
        unMarkButton.setDisable(false);
        explainWebView.setDisable(false);
        explainWebView.setVisible(true);
        speakButton.setDisable(false);
        myTextField.setDisable(false);
        myListView.setDisable(false);

        String editedHtml = editor.getHtmlText();
        Helper.dictionary.update(currentSelectedWord, editedHtml);
        WebEngine explainWebEngine = explainWebView.getEngine();
        explainWebEngine.loadContent(editedHtml);
    }

    @FXML
    private void edit() {
        editor.setDisable(false);
        editor.setVisible(true);
        saveButton.setDisable(false);
        saveButton.setVisible(true);
        deleteButton.setDisable(true);
        unMarkButton.setDisable(true);
        explainWebView.setDisable(true);
        explainWebView.setVisible(false);
        speakButton.setDisable(true);
        myTextField.setDisable(true);
        myListView.setDisable(true);
        String currentHtml = explainWebView.getEngine().executeScript("document.documentElement.outerHTML").toString();
        editor.setHtmlText(currentHtml);
    }

    @FXML
    private void speak() {
        try {
            Helper.speak(currentSelectedWord, Languages.English_UnitedStates);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void delete() {
        Helper.dictionary.remove(currentSelectedWord);
        reload();
    }

    @FXML
    private void unMarkWord() {
        if (currentSelectedWord.isEmpty())
            return;
        Helper.dictionary.unMarkedWords(currentSelectedWord);
        reload();
        myTextField.requestFocus();
        myTextField.selectAll();
    }

    public void reload() {
        words = Helper.dictionary.getTargetOFMarked_word(); // Update the words array
        currentSelectedWord = "";

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
                currentSelectedWord = "";
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

                currentSelectedWord = newValue;
            }
        });

        WebEngine webEngine = explainWebView.getEngine();

        BooleanBinding isContentLoaded = Bindings.createBooleanBinding(() -> {
            Document document = webEngine.getDocument();
            if (document != null) {
                String content = document.getDocumentElement().getTextContent();
                return content != null && !content.isEmpty();
            }
            return false;
        }, webEngine.documentProperty());

        editButton.managedProperty().bind(isContentLoaded);
        editButton.visibleProperty().bind(isContentLoaded);

        speakButton.managedProperty().bind(isContentLoaded);
        speakButton.visibleProperty().bind(isContentLoaded);

        unMarkButton.managedProperty().bind(isContentLoaded);
        unMarkButton.visibleProperty().bind(isContentLoaded);

        deleteButton.managedProperty().bind(isContentLoaded);
        deleteButton.visibleProperty().bind(isContentLoaded);

        saveButton.setDisable(true);
        saveButton.setVisible(false);
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
