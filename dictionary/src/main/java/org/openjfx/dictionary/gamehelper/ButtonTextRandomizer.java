package org.openjfx.dictionary.gamehelper;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.control.Button;

public class ButtonTextRandomizer {
    private final List<String> englishWords;
    private final List<String> vietnameseMeanings;
    private final List<Button> leftButtons;
    private final List<Button> rightButtons;

    public ButtonTextRandomizer(List<String> englishWords, List<String> vietnameseMeanings, List<Button> leftButtons, List<Button> rightButtons) {
        this.englishWords = englishWords;
        this.vietnameseMeanings = vietnameseMeanings;
        this.leftButtons = leftButtons;
        this.rightButtons = rightButtons;
    }

    /**
     * sắp xếp ngẫu nhiên các từ cùng 1 cột.
     */
    public void setRandomText() {
        int[] randomNumbers = new int[5];
        boolean[] check = new boolean[5];
        Random random = new Random();

        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = -1;
        }

        for (int i = 0; i < randomNumbers.length; i++) {
            int randomNumber;
            do {
                randomNumber = random.nextInt(5); // Số ngẫu nhiên từ 0 đến 4
            } while (check[randomNumber]);

            randomNumbers[i] = randomNumber;
            check[randomNumber] = true;
        }

        // Lấy một danh sách các chỉ mục duy nhất
        List<Integer> uniqueIndices = new ArrayList<>();
        for (int i = 0; i < englishWords.size(); i++) {
            uniqueIndices.add(i);
        }
        Collections.shuffle(uniqueIndices);

        // Gán các từ tương ứng vào nút
        for (int i = 0; i < leftButtons.size(); i++) {
            int index = uniqueIndices.get(i);
            String englishWord = englishWords.get(index);
            String vietnameseMeaning = vietnameseMeanings.get(index);

            leftButtons.get(i).setText(englishWord);
            rightButtons.get(randomNumbers[i]).setText(vietnameseMeaning);
        }
    }
}
