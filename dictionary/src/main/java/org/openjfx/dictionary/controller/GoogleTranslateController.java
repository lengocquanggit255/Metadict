package org.openjfx.dictionary.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.openjfx.dictionary.cmd.Helper;

import com.voicerss.tts.Languages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class GoogleTranslateController implements Initializable {
    private Button selectedButtonLF;
    private Button selectedButtonLT;
    private String currentLF;
    private String currentLT;
    @FXML
    private Button detectLanguageButtonLF;
    @FXML
    private Button EnLanguageButtonLF;
    @FXML
    private Button ViLanguageButtonLF;
    @FXML
    private Button CnLanguageButtonLF;
    @FXML
    private Button speakButtonLF;
    @FXML
    private TextField LFTextField;

    @FXML
    private Button EnLanguageButtonLT;
    @FXML
    private Button ViLanguageButtonLT;
    @FXML
    private Button CnLanguageButtonLT;
    @FXML
    private Button speakButtonLT;
    @FXML
    private TextField LTTextField;

    @FXML
    private Button translateButton;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (selectedButtonLF != null) {
            selectedButtonLF.getStyleClass().remove("selected-button");
        }
        selectedButtonLF = detectLanguageButtonLF;
        selectedButtonLF.getStyleClass().remove("notSelected-button");
        selectedButtonLF.getStyleClass().add("selected-button");

        if (selectedButtonLT != null) {
            selectedButtonLT.getStyleClass().remove("selected-button");
        }
        selectedButtonLT = EnLanguageButtonLT;
        selectedButtonLT.getStyleClass().remove("notSelected-button");
        selectedButtonLT.getStyleClass().add("selected-button");

        currentLF = "";
        currentLT = Languages.Vietnamese;
    }

    public void reload() {
        LTTextField.setText("");
        LFTextField.setText("");

        currentLF = "";
        currentLT = Languages.Vietnamese;
        detectLanguageButtonLF.getStyleClass().remove("notSelected-button");
        detectLanguageButtonLF.getStyleClass().add("notSelected-button");

        EnLanguageButtonLF.getStyleClass().remove("notSelected-button");
        EnLanguageButtonLF.getStyleClass().add("notSelected-button");

        ViLanguageButtonLF.getStyleClass().remove("notSelected-button");
        ViLanguageButtonLF.getStyleClass().add("notSelected-button");

        CnLanguageButtonLF.getStyleClass().remove("notSelected-button");
        CnLanguageButtonLF.getStyleClass().add("notSelected-button");

        EnLanguageButtonLT.getStyleClass().remove("notSelected-button");
        EnLanguageButtonLT.getStyleClass().add("notSelected-button");

        ViLanguageButtonLT.getStyleClass().remove("notSelected-button");
        ViLanguageButtonLT.getStyleClass().add("notSelected-button");

        CnLanguageButtonLT.getStyleClass().remove("notSelected-button");
        CnLanguageButtonLT.getStyleClass().add("notSelected-button");

        if (selectedButtonLF != null) {
            selectedButtonLF.getStyleClass().remove("selected-button");
        }
        selectedButtonLF = detectLanguageButtonLF;
        selectedButtonLF.getStyleClass().remove("notSelected-button");
        selectedButtonLF.getStyleClass().add("selected-button");
        if (selectedButtonLT != null) {
            selectedButtonLT.getStyleClass().remove("selected-button");
        }
        selectedButtonLT = EnLanguageButtonLT;
        selectedButtonLT.getStyleClass().remove("notSelected-button");
        selectedButtonLT.getStyleClass().add("selected-button");
    }

    @FXML
    private void translate() {
        String text = LFTextField.getText();
        try {
            String translatedText = Helper.googleTranslate(currentLF, currentLT, text);
            LTTextField.setText(translatedText);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void speakLF() {
        if (!currentLF.isEmpty())
            try {
                Helper.speak(LFTextField.getText(), currentLF);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

    @FXML
    private void speakLT() {
        if (!currentLT.isEmpty())
            try {
                Helper.speak(LTTextField.getText(), currentLT);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

    @FXML
    private void selectLanguageFrom(ActionEvent event) {
        Object source = event.getSource();

        if (source == detectLanguageButtonLF) {
            currentLF = "";
        } else if (source == EnLanguageButtonLF) {
            currentLF = Languages.English_UnitedStates;
        } else if (source == ViLanguageButtonLF) {
            currentLF = Languages.Vietnamese;
        } else if (source == CnLanguageButtonLF) {
            currentLF = Languages.Chinese_China;
        }

        // Apply the selected style to the button
        if (selectedButtonLF != null) {
            selectedButtonLF.getStyleClass().remove("selected-button");
        }
        selectedButtonLF.getStyleClass().add("notSelected-button");
        selectedButtonLF = (Button) source;
        selectedButtonLF.getStyleClass().remove("notSelected-button");
        selectedButtonLF.getStyleClass().add("selected-button");

        System.out.println("Current LF: " + currentLF);
    }

    @FXML
    private void selectLanguageTo(ActionEvent event) {
        Object source = event.getSource();

        if (source == EnLanguageButtonLT) {
            currentLT = Languages.English_UnitedStates;
        } else if (source == ViLanguageButtonLT) {
            currentLT = Languages.Vietnamese;
        } else if (source == CnLanguageButtonLT) {
            currentLT = Languages.Chinese_China;
        }

        if (selectedButtonLT != null) {
            selectedButtonLT.getStyleClass().remove("selected-button");
        }
        selectedButtonLT.getStyleClass().add("notSelected-button");
        selectedButtonLT = (Button) source;
        selectedButtonLT.getStyleClass().remove("notSelected-button");
        selectedButtonLT.getStyleClass().add("selected-button");

        System.out.println("Current LT: " + currentLT);
    }

    @FXML
    private void mouseExitButton(MouseEvent event) {
        translateButton.setStyle(
                "-fx-background-color: #03a9f4; -fx-border-color: TRANSPARENT; -fx-background-radius: 10px; -fx-border-radius: 10px;");
    }

    @FXML
    private void mouseEnterButton(MouseEvent event) {
        translateButton.setStyle(
                "-fx-background-color: #03a9f4; -fx-border-color: WHITE; -fx-background-radius: 10px; -fx-border-radius: 10px;");
    }

}
