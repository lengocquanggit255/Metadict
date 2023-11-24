package org.openjfx.dictionary.gamehelper;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class SetColorButton {

    public void setBorderWrongButton(Button button) {
        button.setStyle("-fx-border-width: 3px; -fx-border-radius: 25px; -fx-background-color:  #03A9F4; -fx-background-radius: 25px; -fx-border-color: red; -fx-text-fill: #FFFFFF; -fx-font-size: 15px; -fx-font-weight: BOLD; -fx-wrap-text: TRUE;-fx-alignment: CENTER; -fx-content-display: CENTER;");
    }

    public void setBorderCorrectButton(Button button) {
        button.setStyle("-fx-border-width: 3px; -fx-border-radius: 25px; -fx-background-color:  #03A9F4; -fx-background-radius: 25px; -fx-border-color: green; -fx-text-fill: #FFFFFF; -fx-font-size: 15px; -fx-font-weight: BOLD; -fx-wrap-text: TRUE;-fx-alignment: CENTER; -fx-content-display: CENTER;");
    }

    public void setColorButton(Button button) {
        button.setStyle("-fx-background-color:  #1144A6; -fx-background-radius: 25px; -fx-text-fill: #FFFFFF; -fx-font-size: 15px; -fx-font-weight: BOLD; -fx-wrap-text: TRUE;-fx-alignment: CENTER; -fx-content-display: CENTER;");
    }

    public void resetBorderButton(Button button1, double delayInSeconds) {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(delayInSeconds), event -> {
            button1.setStyle("-fx-border-width: 0px;  -fx-background-color:  #03A9F4; -fx-background-radius: 25px; -fx-text-fill: #FFFFFF; -fx-font-size: 15px; -fx-font-weight: BOLD; -fx-wrap-text: TRUE;-fx-alignment: CENTER; -fx-content-display: CENTER;");
            })
        );

        timeline.play();
    }

    public void resetColorButton(Button button) {
        button.setStyle("-fx-background-color:  #03A9F4; -fx-background-radius: 25px; -fx-text-fill: #FFFFFF; -fx-font-size: 15px; -fx-font-weight: BOLD; -fx-wrap-text: TRUE;-fx-alignment: CENTER; -fx-content-display: CENTER;");
    }

    
}
