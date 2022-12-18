package com.example.tpfinalquizvrai;

import java.util.ArrayList;

public class QuestionHelper {

    public QuestionHelper(){};

    public Artiste generateFollowersAnswer(ArrayList<Artiste> artisteArrayList){
        Artiste bestArt;
        bestArt = artisteArrayList.get(0);
        for (Artiste artiste : artisteArrayList){
            if (artiste.followers.total > bestArt.followers.total){
                bestArt = artiste;
            }
        }
        return bestArt;
    }

    public Artiste generatePopularityAnswer(ArrayList<Artiste> artisteArrayList){
        Artiste bestArt;
        bestArt = artisteArrayList.get(0);
        for (Artiste artiste : artisteArrayList){
            if (artiste.popularity > bestArt.popularity){
                bestArt = artiste;
            }
        }
        return bestArt;
    }



}
