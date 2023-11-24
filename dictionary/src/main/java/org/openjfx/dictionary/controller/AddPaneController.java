package org.openjfx.dictionary.controller;

import org.openjfx.dictionary.cmd.Helper;
import org.openjfx.dictionary.cmd.Word;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

public class AddPaneController {
    @FXML
    private TextField newWordTextField;

    @FXML
    private HTMLEditor explainHTMLEditor;

    @FXML
    private Label messageLabel;

    @FXML
    private void saveNewWord(ActionEvent event) {
        String word_target = newWordTextField.getText();
        String word_explain = explainHTMLEditor.getHtmlText();

        if (word_target == null || word_target.isEmpty()) {
            showMessage("Please fill everything!", false);
            return;
        }

        if (explainHTMLEditor.getHtmlText().equals(
                "<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>")) {
            showMessage("Please fill everything!", false);
            return;
        }

        if (!Helper.dictionary.contain(word_target)) {
            Helper.dictionary.put(new Word(word_target, word_explain));
            showMessage("Successfully added!", true);
            newWordTextField.setText("");
            explainHTMLEditor.setHtmlText("");
        } else {
            showMessage("Word already exists!", false);
            return;
        }
    }

    private void showMessage(String message, boolean isSuccess) {
        messageLabel.setText(message);
        messageLabel.setVisible(true);
        if (isSuccess) {
            messageLabel.setStyle("-fx-background-color: #0285c1; -fx-border-color: #0285c1; -fx-background-radius: 8px; -fx-border-radius: 8px; -fx-text-fill: WHITE;");
        } else {
            messageLabel.setStyle("-fx-background-color: #0285c1; -fx-border-color: #0285c1; -fx-background-radius: 8px; -fx-border-radius: 8px; -fx-text-fill: #ff3232;");
        }
    }

    public void reload() {
        newWordTextField.setText("");
        explainHTMLEditor.setHtmlText("");
        messageLabel.setText("");
        messageLabel.setVisible(false);
    }

}
