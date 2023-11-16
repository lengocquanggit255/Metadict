package org.openjfx.dictionary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class MenuGamePaneController {
    @FXML
    private Button topic1, topic2, topic3, topic4, topic5, topic6, topic7, topic8, topic9, topic10;

    private AnchorPane anchorGamePane;
    private GamePaneController gameController;
    private ContainerController containerController;

    public void setContainerController(ContainerController containerController) {
        this.containerController = containerController;
    }

    public void setGameController(GamePaneController gameController) {
        this.gameController = gameController;
    }

    @FXML
    private void chooseTopic(ActionEvent event) {
        Button button = (Button) event.getSource();

        // Mảng chứa tên các chủ đề
        String[] topics = {"D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\topics\\animal.txt"
                            , "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\topics\\family.txt"
                            , "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\topics\\SportsAndGames.txt"
                            , "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\topics\\traffic.txt"
                            , "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\topics\\schoolAndUniversity.txt"
                            , "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\topics\\colorsAndShapes.txt"
                            , "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\topics\\Body.txt"
                            , "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\topics\\numbers.txt"
                            , "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\topics\\foodsAndDrinks.txt"
                            , "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\topics\\moviesAndMusic.txt"};

        // Lấy chỉ số của button được bấm trong mảng
        int index = Integer.parseInt(button.getId().substring(5)) - 1;

        // Kiểm tra giới hạn chỉ số
        if (index >= 0 && index < topics.length) {
            String selectedTopic = topics[index];
            CopyTextFile.Copy(selectedTopic, "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\vocabulary.txt");
        }
        showGame();
    }

    @FXML
    public void showGame() {
        containerController.content_pane.getChildren().clear();
        gameController.reload();
        if (!containerController.content_pane.getChildren().contains(anchorGamePane)) {
            containerController.content_pane.getChildren().add(anchorGamePane);
        }
    }

    private void clear(String fileName) {
        try {
            Files.write(Paths.get(fileName), new byte[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        clear("vocabulary.txt");
    }

    @FXML
    public void initialize() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gamePane.fxml"));
            anchorGamePane = fxmlLoader.load();
            gameController = fxmlLoader.getController();
            setGameController(this.gameController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
