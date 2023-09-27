package org.openjfx.dictionary;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SecondaryController {

    @FXML
    Label nameLabel;

    public void displayName(String username) throws IOException {
        nameLabel.setText("Hello: " + username);
    }
}