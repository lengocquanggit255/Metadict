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

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class SearchPaneController implements Initializable {
    @FXML
    private TextField myTextField;

    @FXML
    private ListView<String> myListView;

    @FXML
    private Label explainLabel;

    @FXML
    private Label targetLabel;

    @FXML
    Button markButton;

    private String[] words = ContainerController.dictionary.getWords_target();
    private FilteredList<String> filteredList;

    @FXML
    public void toggleMarkWord() {
        if (targetLabel.getText().equals(""))
            return;
        if (ContainerController.dictionary.getWord(targetLabel.getText()).isMarked()) {
            ContainerController.dictionary.unMarkedWords(targetLabel.getText());
        } else {
            ContainerController.dictionary.markWord(targetLabel.getText());
        }
    }

    public void reload() {
        words = ContainerController.dictionary.getWords_target(); // Update the words array

        targetLabel.setText("");
        explainLabel.setText("");
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
                explainLabel.setText("");
            }
            filterList(newValue);
        });

        myListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Platform.runLater(() -> myTextField.setText(newValue));
                explainLabel.setText(ContainerController.dictionary.getWord(newValue).getWord_explain());
                targetLabel.setText(ContainerController.dictionary.getWord(newValue).getWord_target());
            }
        });

        markButton.managedProperty().bind(targetLabel.textProperty().isNotEmpty());
        markButton.visibleProperty().bind(targetLabel.textProperty().isNotEmpty());
    }

    private void filterList(String searchText) {
        filteredList.setPredicate(word -> {
            if (searchText == null || searchText.isEmpty()) {
                return true;
            }

            String lowercaseSearchText = searchText.toLowerCase();
            return word.toLowerCase().contains(lowercaseSearchText);
        });

        System.out.println(filteredList);
    }
}