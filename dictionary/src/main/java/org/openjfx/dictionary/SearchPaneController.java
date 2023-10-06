package org.openjfx.dictionary;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class SearchPaneController implements Initializable {
    @FXML
    private TextField myTextField;

    @FXML
    private ListView<String> myListView;

    @FXML
    private WebView explainWebView;

    @FXML
    private Label targetLabel;

    @FXML
    private Button markButton;
    @FXML
    private ImageView markButtonImageView;

    @FXML
    private Button deleteButton;
    @FXML
    private Button speakButton;

    private String[] words = Helper.dictionary.getWords_target();
    private FilteredList<String> filteredList;

    @FXML
    public void speak(){
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
    public void toggleMarkWord() {
        if (targetLabel.getText().isEmpty()) {
            return;
        }

        String word = targetLabel.getText();
        boolean isMarked = Helper.dictionary.getWord(word).isMarked();

        if (isMarked) {
            Helper.dictionary.unMarkedWords(word);
            markButtonImageView.setImage(new Image(
                    "D:/QuangWork/Github/OPP/dictionary/src/main/resources/org/openjfx/dictionary/icons/icons8_Star_52px.png"));
        } else {
            Helper.dictionary.markWord(word);
            markButtonImageView.setImage(new Image(
                    "D:/QuangWork/Github/OPP/dictionary/src/main/resources/org/openjfx/dictionary/icons/icons8_Star_Filled_52px.png"));
        }
    }

    public void reload() {
        words = Helper.dictionary.getWords_target(); // Update the words array

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
                if (!Helper.dictionary.getWord(targetLabel.getText()).isMarked()) {
                    markButtonImageView.setImage(new Image(
                            "D:/QuangWork/Github/OPP/dictionary/src/main/resources/org/openjfx/dictionary/icons/icons8_Star_52px.png"));
                } else {
                    markButtonImageView.setImage(new Image(
                            "D:/QuangWork/Github/OPP/dictionary/src/main/resources/org/openjfx/dictionary/icons/icons8_Star_Filled_52px.png"));
                }

            }
        });

        markButton.managedProperty().bind(targetLabel.textProperty().isNotEmpty());
        markButton.visibleProperty().bind(targetLabel.textProperty().isNotEmpty());

        deleteButton.managedProperty().bind(targetLabel.textProperty().isNotEmpty());
        deleteButton.visibleProperty().bind(targetLabel.textProperty().isNotEmpty());
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