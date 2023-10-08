package org.openjfx.dictionary;

import org.openjfx.dictionary.cmd.Helper;
import org.openjfx.dictionary.cmd.Word;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddPaneController {
    @FXML
    TextField newWordTextField;

    @FXML
    TextField explainTextField;

    @FXML
    private Label messageLabel;

    @FXML
    public void saveNewWord(ActionEvent event) {
        String word_target = newWordTextField.getText();
        String word_explain = explainTextField.getText();

        if (word_target == null || word_target.isEmpty()) {
            messageLabel.setText("Please fill everything!");
            return;
        }

        if (word_explain == null || word_explain.isEmpty()) {
            messageLabel.setText("Please fill everything!");
            return;
        }

        if (!Helper.dictionary.contain(word_target)) {
            Helper.dictionary.put(new Word(word_target, word_explain));
            messageLabel.setText("Successfully added!");
            newWordTextField.setText("");
            explainTextField.setText("");
        } else {
            messageLabel.setText("Words already existed!");
            return;
        }
    }

    public void reload() {
        newWordTextField.setText("");
        explainTextField.setText("");
        messageLabel.setText("");
    }

}
