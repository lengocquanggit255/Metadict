package org.openjfx.dictionary.cmd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

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
        String path = "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\E_V.txt";
        String line = null;

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path), "UTF8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while ((line = bufferedReader.readLine()) != null) {
                int posSplit = line.indexOf("<html");
                if (posSplit > 0 && posSplit < line.length()) {
                    String word_target = line.substring(0, posSplit);
                    String explainPart = line.substring(posSplit);
                    if (explainPart != null) {
                        String word_explain = explainPart;
                        words.add(new Word(word_target, word_explain));
                    } else {
                        System.out.println("Could not find 'body' element for word: " + word_target);
                    }
                }
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + path + "'");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void importBookMarkFromFile() {
        String filePath = "D:\\Github\\OPP\\dictionary\\src\\main\\java\\org\\openjfx\\dictionary\\output\\bookMark.txt";

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("<html");
                if (words.length == 2) {
                    String word_target = words[0];
                    markWord(word_target);
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
            System.out.println("Import succeeded!");
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void exportDataToFile() {
        exportDictionaryToFile();
        exportBookMarkToFile();
    }

    private void exportBookMarkToFile() {

        String filepath = "D:/Github/OPP/dictionary/src/main/java/org/openjfx/dictionary/output/bookMark.txt";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filepath);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            for (Word word : markedWords) {
                bufferedWriter.write(word.getWord_target() + word.getWord_explain());
                bufferedWriter.newLine();
            }

            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void exportDictionaryToFile() {
        String filepath = "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\E_V.txt";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filepath);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            for (Word word : words) {
                bufferedWriter.write(word.getWord_target() + word.getWord_explain());
                bufferedWriter.newLine();
            }

            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
