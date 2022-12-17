package com.example.tpfinalquizvrai;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Artiste {

    String name;
    ArrayList<String> genres;
    Followers followers;
    int popularity;
    Vector<Image> images;



    public int randomFromVecImg(){

        Random random = new Random();
        return random.nextInt(images.size());

    }

    public int randomFromVecGenres(){
        Random random = new Random();
        return random.nextInt(genres.size());
    }
}
