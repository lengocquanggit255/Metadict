package org.openjfx.dictionary;

import org.openjfx.dictionary.cmd.Dictionary;
import org.openjfx.dictionary.cmd.Helper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class Main extends Application {

    public static void main(String[] args) {
        Helper.dictionary = new Dictionary();
        launch();
        Helper.dictionary.exportDataToFile();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            primaryStage.setTitle("MetaDict");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/startPane.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            // Set the application icon
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icons/appIcon.png")));

            primaryStage.show();

            // Center the stage on the screen
            primaryStage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}