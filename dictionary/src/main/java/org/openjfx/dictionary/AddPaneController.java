package org.openjfx.dictionary;

import org.openjfx.dictionary.cmd.Word;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddPaneController {
    @FXML
    TextField newWordTextField;

    @FXML
    TextField explainTextField;

    @FXML
    public void saveNewWord(ActionEvent event) {
        String word_target = newWordTextField.getText();
        String word_explain = explainTextField.getText();

        ContainerController.dictionary.put(new Word(word_target, word_explain));

        newWordTextField.setText("");
        explainTextField.setText("");
    }

    public void reload() {
        newWordTextField.setText("");
        explainTextField.setText("");
    }

}
