package org.openjfx.dictionary.cmd;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {
    private ArrayList<Word> words;
    private ArrayList<Word> markedWords;

    public Dictionary() {
        words = new ArrayList<Word>();
        markedWords = new ArrayList<Word>();
        importWordsFromFile();
        importBookMarkFromFile();
    }

    public String[] getWords_target() {
        String[] res = new String[words.size()];
        for (int i = 0; i < words.size(); i++) {
            res[i] = this.words.get(i).getWord_target();
        }

        return res;
    }

    public boolean contain(String word_target) {
        return findWord(word_target) != null;
    }

    public int getNumOfWords() {
        return this.words.size();
    }

    private Word findWord(String word_target) {
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getWord_target().equals(word_target)) {
                return words.get(i);
            }
        }
        System.out.println("Can't find the word");
        return null;
    }

    public Word getWord(String word_target) {
        return findWord(word_target);
    }

    public void put(String word_target, String new_word_explain) {
        words.add(new Word(word_target, new_word_explain));
    }

    public void put(Word newWord) {
        words.add(newWord);
    }

    public void update(String word_target, String new_word_explain) {
        Word updatedWord = findWord(word_target);
        updatedWord.setWord_explain(new_word_explain);
    }

    public void remove(String word_target) {
        Word removeWord = findWord(word_target);
        if (removeWord == null) {
            System.out.println("Null removeWord found!");
            return;
        }
        if (removeWord.isMarked()) {
            markedWords.remove(removeWord);
        }
        words.remove(removeWord);
    }

    public String[] getTargetOFMarked_word() {
        String[] res = new String[markedWords.size()];
        for (int i = 0; i < markedWords.size(); i++) {
            res[i] = this.markedWords.get(i).getWord_target();
        }
        return res;
    }

    public void markWord(String word_target) {
        Word markedWord = findWord(word_target);
        markedWord.mark();
        markedWords.add(markedWord);
    }

    public void unMarkedWords(String word_target) {
        Word unMarkedWord = findWord(word_target);
        unMarkedWord.unMark();
        markedWords.remove(unMarkedWord);
    }

    private void importWordsFromFile() {

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
                    this.words.add(newWord);
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

    private void importBookMarkFromFile() {
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

    public void exportDataToFile() {
        exportDictionaryToFile();
        exportBookMarkToFile();
    }

    private void exportDictionaryToFile() {
        String filepath = "D:/QuangWork/Github/OPP/dictionary/src/main/java/org/openjfx/dictionary/cmd/dictionaries.txt";

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
        for (int i = 0; i < this.getNumOfWords(); i++) {
            Word word = words.get(i);
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

    private void exportBookMarkToFile() {

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

}
