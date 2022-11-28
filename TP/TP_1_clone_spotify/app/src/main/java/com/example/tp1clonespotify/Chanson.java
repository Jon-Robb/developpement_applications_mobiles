package com.example.tp1clonespotify;


import android.graphics.Bitmap;

public class Chanson {

    private String nom;
    private Artiste artiste;
    private String album;
    private Bitmap imgChanson;


    public Chanson(String nom, Artiste artiste, String album) {
        this.nom = nom;
        this.artiste = artiste;
        this.album = album;
    }

    public String getNom() {
        return nom;
    }

    public Artiste getArtiste() {
        return artiste;
    }

    public String getAlbum() {
        return album;
    }

    public Bitmap getImgChanson() {
        return imgChanson;
    }

    public void setImgChanson(Bitmap imgChanson) {
        this.imgChanson = imgChanson;
    }
}


