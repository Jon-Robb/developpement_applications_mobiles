package com.example.tpfinalquizvrai;

import java.io.Serializable;
import java.util.ArrayList;

public class Scores implements Serializable {

    private ArrayList<Score> arrayScores;

    public Scores(){
        this.arrayScores = new ArrayList<>();
    }

    public ArrayList<Score> getArrayScores() {
        return arrayScores;
    }

    public void updateArrayScore(Score score) {
        this.arrayScores.add(score);
    }
}
