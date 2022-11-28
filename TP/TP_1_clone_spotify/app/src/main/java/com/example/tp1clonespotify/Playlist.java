package com.example.tp1clonespotify;

import android.graphics.drawable.Drawable;

public class Playlist {

    private String nom;
    private int nbChansons;
    private Drawable image;

    public Playlist(String nom, int nbChansons, Drawable image) {
        this.nom = nom;
        this.nbChansons = nbChansons;
        this.image = image;
    }
}
