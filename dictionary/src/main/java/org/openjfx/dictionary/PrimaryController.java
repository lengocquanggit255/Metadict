package org.openjfx.dictionary;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class PrimaryController implements Initializable {

    @FXML
    private TextField myTextField;

    @FXML
    private ListView<String> myListView;

    private final String[] words = { "House", "City", "Village", "State", "Country", "Traction", "Transport", "Train" };
    private ObservableList<String> filteredList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        myListView.getItems().addAll(words);
        myTextField.setText("");

        myTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterList(newValue);
        });

        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

                if (arg2 != null)
                    myTextField.setText(arg2);

                else {
                    myListView.getSelectionModel().clearSelection();
                }

            }
        });
    }

    private void filterList(String searchText) {
        ObservableList<String> checList = myListView.getItems();
        filteredList.clear();

        if (searchText == null || searchText.isEmpty()) {
            filteredList.addAll(Arrays.asList(words));
            myListView.setItems(filteredList);
            return;
        }

        if (searchText.isEmpty()) {
            filteredList.addAll(Arrays.asList(words));
        } else {
            for (String word : words) {
                if (word.toLowerCase().contains(searchText.toLowerCase())) {
                    filteredList.add(word);
                }
            }
        }

        myListView.setItems(filteredList);
        checList = myListView.getItems();
    }
}