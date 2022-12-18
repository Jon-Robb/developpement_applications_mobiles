package com.example.tpfinalquizvrai;


import java.io.Serializable;

public class Score implements Serializable {

    private int score;

    public Score() {
        this.score = 0;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score += score;
    }
}
