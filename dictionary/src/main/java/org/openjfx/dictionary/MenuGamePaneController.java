package org.openjfx.dictionary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuGamePaneController {
    @FXML
    private Button topic1, topic2, topic3, topic4, topic5, topic6, topic7, topic8, topic9, topic10;
    @FXML
    private AnchorPane anchorGameMenu;
    @FXML
    private Button backButton;

    private DefaultGameMenuPaneController defaultGameMenuController;
    private AnchorPane anchorGamePane;
    private GamePaneController gameController;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void setdefaultGameMenuController(DefaultGameMenuPaneController defaultGameMenuController) {
        this.defaultGameMenuController = defaultGameMenuController;
    }

    public void setGameController(GamePaneController gameController) {
        this.gameController = gameController;
    }

    @FXML
    private void chooseTopic(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();

        // Mảng chứa tên các chủ đề
        String[] topics = {
                "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\topics\\animal.txt",
                "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\topics\\family.txt",
                "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\topics\\SportsAndGames.txt",
                "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\topics\\traffic.txt",
                "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\topics\\schoolAndUniversity.txt",
                "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\topics\\colorsAndShapes.txt",
                "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\topics\\Body.txt",
                "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\topics\\numbers.txt",
                "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\topics\\foodsAndDrinks.txt",
                "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\topics\\moviesAndMusic.txt" };

        // Lấy chỉ số của button được bấm trong mảng
        int index = Integer.parseInt(button.getId().substring(5)) - 1;

        // Kiểm tra giới hạn chỉ số
        if (index >= 0 && index < topics.length) {
            String selectedTopic = topics[index];
            clear("vocabulary.txt"); // ! clear trước khi load vào gamePane chứ k khi reload gameMenu
            CopyTextFile.Copy(selectedTopic,
                    "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\vocabulary.txt");
        }
        showGame();
    }

    public void showGame() {
        anchorGameMenu.getChildren().clear();
        gameController.reload();
        if (!anchorGameMenu.getChildren().contains(anchorGamePane)) {
            anchorGameMenu.getChildren().add(anchorGamePane);
        }
    }

    private void clear(String fileName) {
        try {
            Files.write(Paths.get(fileName), new byte[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void backToDefaultMenuButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("defaultGameMenuPane.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gamePane.fxml"));
            anchorGamePane = fxmlLoader.load();
            gameController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMenuDefault(ActionEvent event) throws IOException {
        defaultGameMenuController.comeBackHome(event);
    }

    @FXML
    private void mouseExitButton(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle(
                "-fx-border-radius: 100px; -fx-background-radius: 100px; -fx-background-color: TRANSPARENT; -fx-border-color: TRANSPARENT;");
    }

    @FXML
    private void mouseEnterButton(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle(
                "-fx-border-radius: 100px; -fx-background-radius: 100px; -fx-background-color: TRANSPARENT; -fx-border-color: WHITE;");
    }

    @FXML
    private void mouseExitTopicButton(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle(
                "-fx-background-radius: 30px; -fx-background-color:  #03A9F4;");
    }

    @FXML
    private void mouseEnterTopicButton(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle(
                "-fx-background-radius: 30px; -fx-background-color: #84CBF4;");
    }
}
