package com.example.tp1clonespotify;


public class Playlist {

    private String nom;
    private int nbChansons;
    private String duree;

    public Playlist(String nom, int nbChansons, String duree) {
        this.nom = nom;
        this.nbChansons = nbChansons;
        this.duree = duree;
    }

    public String getNom() {
        return nom;
    }

    public int getNbChansons() {
        return nbChansons;
    }

    public String getDuree() {
        return duree;
    }
}
