package org.openjfx.dictionary;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;



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
    private Button buttonMusic;
    @FXML
    private Button buttonExit;
    @FXML 
    private Button yesButton;
    @FXML
    private Button noButton;
    @FXML
    private Button xButton;
    @FXML
    private Text minuteText;
    @FXML
    private Text secondText;
    @FXML
    private Text scoreText;

    private int minute = 4;
    private int second = 10;
    private int score = 0;
    
    @FXML
    private ImageView soundOnImage;
    private ImageView soundOffImage;

    @FXML
    private AnchorPane exitGameBoxPane;

    Image soundOn = new Image("file:///D:/Github/OPP/dictionary/src/main/resources/org/openjfx/dictionary/Speaker_on.png");
    Image soundOff = new Image("file:///D:/Github/OPP/dictionary/src/main/resources/org/openjfx/dictionary/Speaker_off.png");

    private ButtonTextRandomizer buttonTextRandomizer;
    private SetColorBorder colorBorder;
    private List<String> englishWords;
    private List<String> vietnameseMeanings;
    Map<String, String> vocabulary = loadVocabulary("D:/Github/OPP/dictionary/src/main/resources/org/openjfx/dictionary/vocabulary.txt");

    String c_sound = getClass().getResource("correct.mp3").toExternalForm();
    String w_sound = getClass().getResource("wrong.mp3").toExternalForm();
    String b_sound = getClass().getResource("BackgroundMusic.mp3").toExternalForm();


    Media c_media = new Media(c_sound);
    MediaPlayer CorrectSound = new MediaPlayer(c_media);
    Media w_media = new Media(w_sound);
    MediaPlayer WrongSound = new MediaPlayer(w_media);
    Media b_media = new Media(b_sound);
    MediaPlayer BackgroundSound = new MediaPlayer(b_media);

    public void initialize() {
        initializeButtons();
        englishWords = new ArrayList<>(vocabulary.keySet());
        vietnameseMeanings = new ArrayList<>(vocabulary.values());
        buttonTextRandomizer = new ButtonTextRandomizer(englishWords, vietnameseMeanings, leftButtons, rightButtons);
        buttonTextRandomizer.setRandomText();
        colorBorder = new SetColorBorder();

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

        exitGameBoxPane.setDisable(true);
        exitGameBoxPane.setVisible(false);
    }

    private void initializeButtons() {
        // Khởi tạo danh sách nút bên trái và bên phải
        leftButtons.addAll(Arrays.asList(
            text0_L, text1_L, text2_L, text3_L, text4_L
        ));
        rightButtons.addAll(Arrays.asList(
            text0_R, text1_R, text2_R, text3_R, text4_R
        ));

        soundOnImage = new ImageView(soundOn);
        soundOffImage = new ImageView(soundOff);
        buttonMusic.setGraphic(soundOnImage);
    }

    /**
     * Đọc từ vựng từ file.
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
    private boolean isLeftButtonClicked = false;
    private boolean isRightButtonClicked = false;
    private Button clickRightButton = null;
    private Button clickLeftButton = null;
    private Button clickTempRightButton = null;
    private Button clickTempLeftButton =  null;

    @FXML
    private void onText_LClick(ActionEvent event) {
        clickLeftButton = (Button) event.getSource();
        CorrectSound.setVolume(1.0);
        WrongSound.setVolume(1.0);
        if (clickTempLeftButton != null) colorBorder.resetColorButton(clickTempLeftButton);
        if (isRightButtonClicked) {
            // Check if both buttons have matching text
            if (check(clickLeftButton.getText(), clickRightButton.getText())) {
                colorBorder.setBorderCorrectButton(clickLeftButton);
                colorBorder.setBorderCorrectButton(clickRightButton); 
                CorrectSound.seek(Duration.ZERO);
                CorrectSound.play();
                score++;
                scoreText.setText(score + "");
                colorBorder.resetBorderButton(clickLeftButton, 0.25);
                colorBorder.resetBorderButton(clickRightButton, 0.25);
                buttonTextRandomizer.setRandomText();
                clickRightButton = null;
                clickLeftButton = null;
            } else {
                colorBorder.setBorderWrongButton(clickLeftButton);
                colorBorder.setBorderWrongButton(clickRightButton);
                WrongSound.seek(Duration.ZERO);
                WrongSound.play();
                colorBorder.resetBorderButton(clickLeftButton, 0.5);
                colorBorder.resetBorderButton(clickRightButton, 0.5);
            }
            isRightButtonClicked = false;
        } else {
            isLeftButtonClicked = true;
            colorBorder.setColorButton(clickLeftButton);
            clickTempLeftButton =  clickLeftButton;
        }
    }

    @FXML
    private void onText_RClick(ActionEvent event) {
        clickRightButton = (Button) event.getSource();
        CorrectSound.setVolume(1.0);
        WrongSound.setVolume(1.0);
        if (clickTempRightButton != null) colorBorder.resetColorButton(clickTempRightButton);
        if (isLeftButtonClicked) {
            // Check if both buttons have matching text
            if (check(clickLeftButton.getText(), clickRightButton.getText())) {
                colorBorder.setBorderCorrectButton(clickLeftButton);  
                colorBorder.setBorderCorrectButton(clickRightButton);            
                CorrectSound.seek(Duration.ZERO);
                CorrectSound.play();
                score++;
                scoreText.setText(score + "");
                colorBorder.resetBorderButton(clickLeftButton, 0.25);
                colorBorder.resetBorderButton(clickRightButton, 0.25);
                buttonTextRandomizer.setRandomText();
                clickRightButton = null;
                clickLeftButton = null;
            } else {
                colorBorder.setBorderWrongButton(clickLeftButton);
                colorBorder.setBorderWrongButton(clickRightButton);
                WrongSound.seek(Duration.ZERO);
                WrongSound.play();
                colorBorder.resetBorderButton(clickLeftButton, 0.5);
                colorBorder.resetBorderButton(clickRightButton, 0.5);
            }
            isLeftButtonClicked = false;
        } else {            
            isRightButtonClicked = true;
            colorBorder.setColorButton(clickRightButton);
            clickTempRightButton =  clickRightButton;
        }
    }
    
    /**
     * Kiểm tra nghĩa tương ứng.
     * @param word_target từ Tiếng Anh.
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
    private boolean checkAudio = true; // biến kiểm tra trạng thái âm thanh
    @FXML
    private void stopOrPlayMusic(ActionEvent event) {
        if (checkAudio) {
            buttonMusic.setGraphic(soundOffImage);
            BackgroundSound.pause();
            checkAudio = false;
        } else {
            buttonMusic.setGraphic(soundOnImage);
            BackgroundSound.play();
            checkAudio = true;
        }        
    }

    @FXML
    private void exitGame(ActionEvent event) {
        BackgroundSound.pause();
        showExitGameBox();
        // if (result == buttonTypeYes) {
        //     // Thoát game
        //     System.exit(0);
        // } else {
        //     // Không làm gì cả, tiếp tục game
        //     BackgroundSound.play();
        // }
    }

    @FXML
    private void quitGame(ActionEvent event) {

    }
    @FXML
    private void stayInGame(ActionEvent event) {}
    
    public void countdown() {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
                second--;
                if (second < 10) secondText.setText("0" + second);
                else secondText.setText(second + "");
                minuteText.setText("0" + minute);
                if (second == 0) {
                    second = 60;
                    minute--;
                }
                if (minute == 0) {
                    
                }
            })
        );

        timeline.setCycleCount(second); // Đặt số lần lặp lại là giá trị thời gian chạy lùi
        timeline.play();
    }

    public void reload() {
        BackgroundSound.play();  
        countdown(); 
        score = 0;
        minute = 4;
        second = 60;
    }

    @FXML
    public void showExitGameBox() {
        exitGameBoxPane.setDisable(false);
        exitGameBoxPane.setVisible(true);
    }

}
