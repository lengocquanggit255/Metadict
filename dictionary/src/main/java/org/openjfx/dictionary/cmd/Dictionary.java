package org.openjfx.dictionary.cmd;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {
    private static final int MAX_WORD = 10000;
    private Word[] words = new Word[MAX_WORD];
    private ArrayList<Word> markedWords = new ArrayList<Word>();
    private int numOfWords;

    public Dictionary() {
        numOfWords = 0;
        insertWordsFromFile();
        insertBookMarkFromFile();
    }

    public String[] getTargetOFMarked_word() {
        String[] words = new String[markedWords.size()];
        for (int i = 0; i < markedWords.size(); i++) {
            words[i] = this.markedWords.get(i).getWord_target();
        }

        return words;
    }

    public void markWord(String word_target) {
        int i;
        for (i = 0; i < numOfWords; i++) {
            if (words[i].getWord_target().equals(word_target)) {
                break;
            }
        }
        if (i == numOfWords) {
            return;
        }
        if (words[i].isMarked())
            return;
        markedWords.add(words[i]);
        words[i].mark();
    }

    public void unMarkedWords(String word_target) {
        int i;
        for (i = 0; i < numOfWords; i++) {
            if (words[i].getWord_target().equals(word_target)) {
                break;
            }
        }
        if (i == numOfWords) {
            return;
        }
        if (!words[i].isMarked())
            return;
        markedWords.remove(words[i]);
        words[i].unMark();
    }

    public String[] getWords_target() {
        String[] words = new String[numOfWords];
        for (int i = 0; i < numOfWords; i++) {
            words[i] = this.words[i].getWord_target();
        }

        return words;
    }

    public int getNumOfWords() {
        return this.numOfWords;
    }

    public void put(Word newWord) {
        words[numOfWords++] = newWord;
    }

    public Word getWordAt(int index) {
        return this.words[index];
    }

    public Word getWord(String word_target) {
        for (int i = 0; i < numOfWords; i++) {
            if (words[i].getWord_target().equals(word_target))
                return words[i];
        }
        return null;
    }

    public void remove(Word removedWord) {
        int i;
        for (i = 0; i < numOfWords; i++) {
            if (words[i] == removedWord) {
                break;
            }
        }
        if (i == numOfWords) {
            return;
        }
        while (i < numOfWords - 1) {
            Word temp = words[i];
            words[i] = words[i + 1];
            words[i + 1] = temp;
            i++;
        }
        words[numOfWords - 1] = null;
        numOfWords--;
    }

    public void update(Word target, String word_explain) {
        target.setWord_explain(word_explain);
    }

    private void insertWordsFromFile() {

        String filePath = "D:\\QuangWork\\Github\\OPP\\dictionary\\src\\main\\java\\org\\openjfx\\dictionary\\cmd\\dictionaries.txt";

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("\t");
                if (words.length == 2) {
                    String word_target = words[0];
                    String word_explain = words[1];
                    Word newWord = new Word(word_target, word_explain);
                    this.put(newWord);
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
            System.out.println("Import succeeded!");
            scanner.close();
        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    private void insertBookMarkFromFile() {
        String filePath = "D:\\QuangWork\\Github\\OPP\\dictionary\\src\\main\\java\\org\\openjfx\\dictionary\\cmd\\bookMark.txt";

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("\t");
                if (words.length == 2) {
                    String word_target = words[0];
                    String word_explain = words[1];
                    Word newWord = new Word(word_target, word_explain);
                    markWord(newWord.getWord_target());
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
            System.out.println("Import succeeded!");
            scanner.close();
        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public void exportBookMarkToFile() {

        String filepath = "D:\\QuangWork\\Github\\OPP\\dictionary\\src\\main\\java\\org\\openjfx\\dictionary\\cmd\\bookMark.txt";

        try {
            File file = new File(filepath);

            if (file.createNewFile()) {
                System.out.println("New file created: " + file.getAbsolutePath());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }

        String newText = "";
        for (int i = 0; i < markedWords.size(); i++) {
            Word word = markedWords.get(i);
            newText += word.getWord_target() + "\t" + word.getWord_explain() + "\n";
        }

        try (FileWriter writer = new FileWriter(filepath, false)) {
            writer.write(newText);
            System.out.println("Successfully export the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while rewriting the file.");
            e.printStackTrace();
        }
    }

    public boolean contain(String word_target) {
        for (int i = 0; i < numOfWords; i++) {
            if (words[i].getWord_target().equals(word_target)) {
                return true;
            }
        }
        return false;
    }
}
