package org.openjfx.dictionary;

import org.openjfx.dictionary.cmd.Word;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditPaneController {

    @FXML
    private TextField explainTextField;
    @FXML
    private TextField newWordTextField;
    @FXML
    private TextField oldWordTextField;
    @FXML
    private Label messageLabel;

    @FXML
    void saveNewWord(ActionEvent event) {
        String oldWord_target = oldWordTextField.getText();
        String newWord_target = newWordTextField.getText();
        String newWord_explain = explainTextField.getText();
        Word oldWord = ContainerController.dictionary.getWord(oldWord_target);

        if (oldWord == null) {
            messageLabel.setText("Can't find " + oldWord_target + " in dictionary");
            return;
        }
        if (oldWord_target == null || oldWord_target.isEmpty()) {
            messageLabel.setText("Please fill everything!");
            return;
        }

        if (newWord_target == null || newWord_target.isEmpty()) {
            messageLabel.setText("Please fill everything!");
            return;
        }

        if (newWord_explain == null || newWord_explain.isEmpty()) {
            messageLabel.setText("Please fill everything!");
            return;
        }

        oldWord.setWord_target(newWord_target);
        oldWord.setWord_explain(newWord_explain);
        messageLabel.setText("Successfully modified!");

    }

    public void reload() {
        explainTextField.setText("");
        newWordTextField.setText("");
        oldWordTextField.setText("");
        messageLabel.setText("");
    }

}
