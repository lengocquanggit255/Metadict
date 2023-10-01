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
    private Button markButton;
    @FXML
    private ImageView markButtonImageView;

    @FXML
    private Button deleteButton;

    private String[] words = ContainerController.dictionary.getWords_target();
    private FilteredList<String> filteredList;

    @FXML
    public void delete() {
        String word = targetLabel.getText();
        ContainerController.dictionary.remove(word);
        reload();
    }

    @FXML
    public void toggleMarkWord() {
        if (targetLabel.getText().isEmpty()) {
            return;
        }

        String word = targetLabel.getText();
        boolean isMarked = ContainerController.dictionary.getWord(word).isMarked();

        if (isMarked) {
            ContainerController.dictionary.unMarkedWords(word);
            markButtonImageView.setImage(new Image(
                    "D:/QuangWork/Github/OPP/dictionary/src/main/resources/org/openjfx/dictionary/icons/icons8_Star_52px.png"));
        } else {
            ContainerController.dictionary.markWord(word);
            markButtonImageView.setImage(new Image(
                    "D:/QuangWork/Github/OPP/dictionary/src/main/resources/org/openjfx/dictionary/icons/icons8_Star_Filled_52px.png"));
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
                if (!ContainerController.dictionary.getWord(targetLabel.getText()).isMarked()) {
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