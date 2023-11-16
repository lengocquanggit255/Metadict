package org.openjfx.dictionary;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class SetColorBorder {

    public void setBorderWrongButton(Button button) {
        button.setStyle("-fx-border-width: 2px; -fx-background-color:  #9078BB; -fx-background-radius: 10px; -fx-border-color: red; -fx-text-fill: #FFFFFF; -fx-font-size: 20px;");
    }

    public void setBorderCorrectButton(Button button) {
        button.setStyle("-fx-border-width: 2px; -fx-background-color:  #9078BB; -fx-background-radius: 10px; -fx-border-color: green; -fx-text-fill: #FFFFFF; -fx-font-size: 20px;");
    }

    public void setColorButton(Button button) {
        button.setStyle("-fx-background-color:  #C9BCE4; -fx-background-radius: 10px; -fx-text-fill: #FFFFFF; -fx-font-size: 20px;");
    }

    public void resetBorderButton(Button button1, double delayInSeconds) {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(delayInSeconds), event -> {
            button1.setStyle("-fx-border-width: 0px;  -fx-background-color:  #9078BB; -fx-background-radius: 10px; -fx-text-fill: #FFFFFF; -fx-font-size: 20px;");
            })
        );

        timeline.play();
    }

    public void resetColorButton(Button button) {
        button.setStyle("-fx-background-color:  #9078BB; -fx-background-radius: 10px; -fx-text-fill: #FFFFFF; -fx-font-size: 20px;");
    }
}
