package org.openjfx.dictionary.controller;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.openjfx.dictionary.cmd.Helper;
import org.w3c.dom.Document;

import com.voicerss.tts.Languages;

public class SearchPaneController implements Initializable {
    @FXML
    private TextField myTextField;

    @FXML
    private ListView<String> myListView;

    @FXML
    private WebView explainWebView;

    @FXML
    private Button markButton;
    @FXML
    private ImageView markButtonImageView;

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

    private String[] words = Helper.dictionary.getWords_target();
    private FilteredList<String> filteredList;
    private String currentSelectedWord = "";

    @FXML
    private void save() {
        editor.setDisable(true);
        editor.setVisible(false);
        saveButton.setDisable(true);
        saveButton.setVisible(false);
        deleteButton.setDisable(false);
        markButton.setDisable(false);
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
        markButton.setDisable(true);
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    private void delete() {
        Helper.dictionary.remove(currentSelectedWord);
        reload();
    }

    @FXML
    private void toggleMarkWord() {
        if (currentSelectedWord.isEmpty()) {
            return;
        }

        boolean isMarked = Helper.dictionary.getWord(currentSelectedWord).isMarked();

        if (isMarked) {
            Helper.dictionary.unMarkedWords(currentSelectedWord);
            markButtonImageView.setImage(new Image(
                    "file:///D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\icons\\markIcon.png"));
        } else {
            Helper.dictionary.markWord(currentSelectedWord);
            markButtonImageView.setImage(new Image(
                    "file:///D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\icons\\filledMarkIcon.png"));
        }
    }

    public void reload() {
        words = Helper.dictionary.getWords_target(); // Update the words array

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

        editor.setDisable(true);
        editor.setVisible(false);

        saveButton.setDisable(true);
        saveButton.setVisible(false);

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

                if (!Helper.dictionary.getWord(currentSelectedWord).isMarked()) {
                    markButtonImageView.setImage(new Image(
                            "file:///D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\icons\\markIcon.png"));
                } else {
                    markButtonImageView.setImage(new Image(
                            "file:///D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\icons\\filledMarkIcon.png"));
                }

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

        markButton.managedProperty().bind(isContentLoaded);
        markButton.visibleProperty().bind(isContentLoaded);

        deleteButton.managedProperty().bind(isContentLoaded);
        deleteButton.visibleProperty().bind(isContentLoaded);
    }

    private void filterList(String searchText) {
        filteredList.setPredicate(word -> {
            if (searchText == null || searchText.isEmpty()) {
                return true;
            }

            String lowercaseSearchText = searchText.toLowerCase();
            String lowercaseWord = word.toLowerCase();

            if (lowercaseWord.startsWith(lowercaseSearchText)) {
                return true;
            } else
                return false;
        });
    }
}