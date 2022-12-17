package com.example.tpfinalquizvrai;


public class Score {

    private int score;

    public Score(int score) {
        this.score = score;

    }

    public static class Builder
    {
        private int score;


        public Builder setScore(int score){
            this.score = score;
            return this;
        }

        public Score build ()
        {
            return new Score(score);
        }
    }
}
