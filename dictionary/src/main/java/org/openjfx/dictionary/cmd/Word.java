package org.openjfx.dictionary.cmd;

public class Word {
    private String word_target;
    private String word_explain;
    private Boolean isMarked;

    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
        this.isMarked = false;
    }

    public Boolean isMarked() {
        return isMarked;
    }

    public void mark() {
        isMarked = true;
    }

    public void unMark() {
        isMarked = false;
    }

    public String getWord_target() {
        return this.word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_explain() {
        return this.word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

}