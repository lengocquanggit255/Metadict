package org.openjfx.dictionary.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.openjfx.dictionary.gamehelper.ButtonTextRandomizer;
import org.openjfx.dictionary.gamehelper.SetColorButton;

public class GamePaneController {
    @FXML
    private List<Button> leftButtons = new ArrayList<>();
    @FXML
    private List<Button> rightButtons = new ArrayList<>();
    @FXML
    private Button text0_L, text0_R;
    @FXML
    private Button text1_L, text1_R;
    @FXML
    private Button text2_L, text2_R;
    @FXML
    private Button text3_L, text3_R;
    @FXML
    private Button text4_L, text4_R;
    @FXML
    private Button musicButton, homeButton, backButton;
    @FXML
    private Button yesButton, noButton, xButton;
    @FXML
    private Button yesScoreButton, noScoreButton, xScoreButton;
    @FXML
    private Text yourScoreText, highScoreText;
    @FXML
    private Text minuteText, secondText, scoreText;
    @FXML
    private ImageView imageSpeaker;
    @FXML
    private AnchorPane exitGameBoxPane;
    @FXML
    private AnchorPane yourScoreBoxPane;
    @FXML
    private ImageView heart1, heart2, heart3, heart4, heart5;

    private int minute;
    private int second;
    private int score;
    private int highScore = 0;
    private int checkTrueAnswer;
    private int checkFalseAnswer;
    private Timeline timeline;
    private boolean checkAudio = true;
    private boolean isLeftButtonClicked = false;
    private boolean isRightButtonClicked = false;
    private Button clickRightButton = null;
    private Button clickLeftButton = null;
    private Button clickTempRightButton = null;
    private Button clickTempLeftButton = null;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Image soundOn = new Image(
            "file:///D:/Github/OPP/dictionary/src/main/resources/org/openjfx/dictionary/icons/Speaker_on.png");
    private Image soundOff = new Image(
            "file:///D:/Github/OPP/dictionary/src/main/resources/org/openjfx/dictionary/icons/Speaker_off.png");
    private Image heart = new Image(
            "file:///D:/Github/OPP/dictionary/src/main/resources/org/openjfx/dictionary/icons/heart.png");

    private ButtonTextRandomizer buttonTextRandomizer;
    private SetColorButton colorButton;
    private List<String> englishWords;
    private List<String> vietnameseMeanings;
    private Map<String, String> vocabulary;

    private String c_sound = getClass().getResource("/org/openjfx/dictionary/audio/correct.mp3").toExternalForm();
    private String w_sound = getClass().getResource("/org/openjfx/dictionary/audio/wrong.mp3").toExternalForm();
    private String b_sound = getClass().getResource("/org/openjfx/dictionary/audio/BackgroundMusic.mp3").toExternalForm();

    private Media c_media = new Media(c_sound);
    private MediaPlayer CorrectSound = new MediaPlayer(c_media);
    private Media w_media = new Media(w_sound);
    private MediaPlayer WrongSound = new MediaPlayer(w_media);
    private Media b_media = new Media(b_sound);
    private MediaPlayer BackgroundSound = new MediaPlayer(b_media);

    private void initializeGame() {
        initializeButtons();
        vocabulary = loadVocabulary(
                "D:/Github/OPP/dictionary/src/main/resources/org/openjfx/dictionary/vocabulary.txt");
        englishWords = new ArrayList<>(vocabulary.keySet());
        vietnameseMeanings = new ArrayList<>(vocabulary.values());
        buttonTextRandomizer = new ButtonTextRandomizer(englishWords, vietnameseMeanings, leftButtons, rightButtons);
        buttonTextRandomizer.setRandomText();
        colorButton = new SetColorButton();
        if (LevelGamePaneController.getModeNumber() == 3) {
            heart1.setImage(heart);
            heart2.setImage(heart);
            heart3.setImage(heart);
            heart4.setImage(heart);
            heart5.setImage(heart);
        } 
        exitGameBoxPane.setDisable(true);
        exitGameBoxPane.setVisible(false);
        yourScoreBoxPane.setDisable(true);
        yourScoreBoxPane.setVisible(false);
        score = 0;
        minute = 5;
        second = 0;
        scoreText.setText("0");
        minuteText.setText("0" + minute);
        secondText.setText("0" + second);
        checkTrueAnswer = 0;
        checkFalseAnswer = 5;

        try (BufferedReader reader = new BufferedReader(
                new FileReader("D:/Github/OPP/dictionary/src/main/resources/org/openjfx/dictionary/highScore.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                highScore = Integer.parseInt(line.trim());
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        highScoreText.setText("HIGH SCORE: " + highScore);
    }

    private void initializeSound() {
        // Tải âm thanh
        CorrectSound = new MediaPlayer(c_media);
        CorrectSound.setVolume(0.0); // Đặt âm lượng ban đầu
        CorrectSound.play();
        CorrectSound.stop();
        WrongSound = new MediaPlayer(w_media);
        WrongSound.setVolume(0.0); // Đặt âm lượng ban đầu
        WrongSound.play();
        WrongSound.stop();
        BackgroundSound = new MediaPlayer(b_media);
        BackgroundSound.setCycleCount(MediaPlayer.INDEFINITE); // phát lại liên tục
        BackgroundSound.play();
    }

    private void initializeButtons() {
        leftButtons.clear();// ! Trước khi khởi tạo gì thì cần clear nó trước
        rightButtons.clear();
        // Khởi tạo danh sách nút bên trái và bên phải
        leftButtons.addAll(Arrays.asList(
                text0_L, text1_L, text2_L, text3_L, text4_L));
        rightButtons.addAll(Arrays.asList(
                text0_R, text1_R, text2_R, text3_R, text4_R));

        imageSpeaker.setImage(soundOn);
    }

    /**
     * Đọc từ vựng từ file.
     * 
     * @param fileName tên file từ.
     * @return
     */
    private Map<String, String> loadVocabulary(String fileName) {
        Map<String, String> vocabulary = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ");
                if (parts.length == 2) {
                    String englishWord = parts[0];
                    String vietnameseMeaning = parts[1];
                    vocabulary.put(englishWord, vietnameseMeaning);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vocabulary;
    }

    /**
     * cách thức game hoạt động.
     */
    @FXML
    private void onText_LClick(ActionEvent event) {
        clickLeftButton = (Button) event.getSource();
        CorrectSound.setVolume(1.0);
        WrongSound.setVolume(1.0);
        if (clickTempLeftButton != null) {
            colorButton.resetColorButton(clickTempLeftButton);
        }
        if (isRightButtonClicked) {
            // Check if both buttons have matching text
            if (check(clickLeftButton.getText(), clickRightButton.getText())) {
                checkTrueAnswer++;
                colorButton.setBorderCorrectButton(clickLeftButton);
                colorButton.setBorderCorrectButton(clickRightButton);
                colorButton.resetBorderButton(clickLeftButton, 0.25);
                colorButton.resetBorderButton(clickRightButton, 0.25);
                setUpModeTrue(clickLeftButton, clickRightButton);
                CorrectSound.seek(Duration.ZERO);
                CorrectSound.play();
                score++;
                scoreText.setText(score + "");
                clickRightButton = null;
                clickLeftButton = null;
            } else {
                checkFalseAnswer--;
                colorButton.setBorderWrongButton(clickLeftButton);
                colorButton.setBorderWrongButton(clickRightButton);
                colorButton.resetBorderButton(clickLeftButton, 0.5);
                colorButton.resetBorderButton(clickRightButton, 0.5);
                setUpModeFalse(clickLeftButton, clickRightButton);
                WrongSound.seek(Duration.ZERO);
                WrongSound.play();
            }
            isRightButtonClicked = false;
        } else {
            isLeftButtonClicked = true;
            colorButton.setColorButton(clickLeftButton);
            clickTempLeftButton = clickLeftButton;
        }
    }

    @FXML
    private void onText_RClick(ActionEvent event) {
        clickRightButton = (Button) event.getSource();
        CorrectSound.setVolume(1.0);
        WrongSound.setVolume(1.0);
        if (clickTempRightButton != null)
            colorButton.resetColorButton(clickTempRightButton);
        if (isLeftButtonClicked) {
            // Check if both buttons have matching text
            if (check(clickLeftButton.getText(), clickRightButton.getText())) {
                checkTrueAnswer++;
                colorButton.setBorderCorrectButton(clickLeftButton);
                colorButton.setBorderCorrectButton(clickRightButton);
                colorButton.resetBorderButton(clickLeftButton, 0.25);
                colorButton.resetBorderButton(clickRightButton, 0.25);
                setUpModeTrue(clickLeftButton, clickRightButton);
                CorrectSound.seek(Duration.ZERO);
                CorrectSound.play();
                score++;
                scoreText.setText(score + "");
                clickRightButton = null;
                clickLeftButton = null;
            } else {
                checkFalseAnswer--;
                colorButton.setBorderWrongButton(clickLeftButton);
                colorButton.setBorderWrongButton(clickRightButton);
                colorButton.resetBorderButton(clickLeftButton, 0.5);
                colorButton.resetBorderButton(clickRightButton, 0.5);
                setUpModeFalse(clickLeftButton, clickRightButton);
                WrongSound.seek(Duration.ZERO);
                WrongSound.play();
            }
            isLeftButtonClicked = false;
        } else {
            isRightButtonClicked = true;
            colorButton.setColorButton(clickRightButton);
            clickTempRightButton = clickRightButton;
        }
    }

    /**
     * Kiểm tra nghĩa tương ứng.
     * 
     * @param word_target  từ Tiếng Anh.
     * @param word_explain nghĩa Tiếng Việt.
     * @return trả về kết quả so sánh.
     */
    private boolean check(String word_target, String word_explain) {
        String myword_explain = vocabulary.get(word_target);
        return myword_explain.equals(word_explain);
    }

    /**
     * bật tắt âm thanh.
     */
    @FXML
    private void stopOrPlayMusic(ActionEvent event) {
        if (checkAudio) {
            imageSpeaker.setImage(soundOff);
            BackgroundSound.pause();
            checkAudio = false;
        } else {
            imageSpeaker.setImage(soundOn);
            BackgroundSound.play();
            checkAudio = true;
        }
    }

    @FXML
    private void exitGame(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/org/openjfx/dictionary/fxml/container.fxml"));
        showExitGameBox();
    }

    @FXML
    private void backToChooseTopic(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/org/openjfx/dictionary/fxml/menuGamePane.fxml"));
        showExitGameBox();
    }

    @FXML
    private void playAgain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/org/openjfx/dictionary/fxml/menuGamePane.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void quitGame(ActionEvent event) throws IOException {
        exitGameBoxPane.setDisable(true);
        exitGameBoxPane.setVisible(false);
        BackgroundSound.stop();
        timeline.stop();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void stayInGame(ActionEvent event) {
        exitGameBoxPane.setDisable(true);
        exitGameBoxPane.setVisible(false);
        if (checkAudio) {
            BackgroundSound.play();
        }
        timeline.play();
    }

    @FXML
    private void chooseLevelAgain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/org/openjfx/dictionary/fxml/levelGamePane.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void countdown() {
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
                    if (second > 0)
                        second--;
                    if (second < 10) {
                        secondText.setText("0" + second);
                        if (second == 0 && minute > 0) {
                            second = 60;
                        }
                    } else {
                        secondText.setText(second + "");
                        if (second == 59) {
                            minute--;
                            minuteText.setText("0" + minute);
                        }
                    }
                    if (minute == 0 && second == 0) {
                        yourScoreText.setText(score + "");
                        if (score > highScore) {
                            highScore = score;
                            updateHighScore();
                        }
                        highScoreText.setText("HIGH SCORE: " + highScore);
                        showYourScoreBox();
                    }
                }));

        timeline.setCycleCount(61 * 5); // Đặt số lần lặp lại là giá trị thời gian chạy lùi
        timeline.play();
    }

    public void reload() {
        initializeGame();
        initializeSound();
        countdown();
    }

    private void showExitGameBox() {
        exitGameBoxPane.setDisable(false);
        exitGameBoxPane.setVisible(true);
        BackgroundSound.pause();
        timeline.pause();
    }

    private void showYourScoreBox() {
        yourScoreBoxPane.setDisable(false);
        yourScoreBoxPane.setVisible(true);
        BackgroundSound.stop();
        timeline.stop();
    }

    private void updateHighScore() {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("D:/Github/OPP/dictionary/src/main/resources/org/openjfx/dictionary/highScore.txt"))) {
            writer.write(Integer.toString(highScore));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void setUpModeTrue(Button button1, Button button2) {
        if (LevelGamePaneController.getModeNumber() == 1) {
            buttonTextRandomizer.setRandomText();
        } else {
            button1.setDisable(true);
            button2.setDisable(true);
            if (checkTrueAnswer == 5) {
                checkTrueAnswer = 0;
                for (Button button : leftButtons) {
                    button.setDisable(false);
                }
                for (Button button : rightButtons) {
                    button.setDisable(false);
                }
                buttonTextRandomizer.setRandomText();
            }
        }
    }

    private void setUpModeFalse(Button button1, Button button2) {
        if (LevelGamePaneController.getModeNumber() == 3) {
            switch (checkFalseAnswer) {
            case 4:
                heart5.setImage(null);
                break;
            case 3:
                heart5.setImage(null);
                heart4.setImage(null);
                break;
            case 2:
                heart5.setImage(null);
                heart4.setImage(null);
                heart3.setImage(null);
                break;
            case 1:
                heart5.setImage(null);
                heart4.setImage(null);
                heart3.setImage(null);
                heart2.setImage(null);
                break;
            case 0:
                heart5.setImage(null);
                heart4.setImage(null);
                heart3.setImage(null);
                heart2.setImage(null);
                heart1.setImage(null);
                yourScoreText.setText(score + "");
                if (score > highScore) {
                    highScore = score;
                    updateHighScore();
                }
                highScoreText.setText("HIGH SCORE: " + highScore);
                showYourScoreBox();
                break;
            }   
        }
    }
}
