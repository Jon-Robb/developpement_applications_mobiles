package com.example.jonathan_robinson_roberge;

public class Joueur {

    private String nom;
    private float nbPresense;
    private float nbCoupsSur;
    private float nbDoubles;
    private float nbTriples;
    private int nbCircuits;

    public Joueur(String nom, float nbPresense, float nbCoupsSur, float nbDoubles, float nbTriples, int nbCircuits) {
        this.nom = nom;
        this.nbPresense = nbPresense;
        this.nbCoupsSur = nbCoupsSur;
        this.nbDoubles = nbDoubles;
        this.nbTriples = nbTriples;
        this.nbCircuits = nbCircuits;
    }

    public float getmoyenneBaton(){

        return nbCoupsSur / nbPresense;
    }

    public String getNom() {
        return nom;
    }

    public float getNbCircuits() {
        return nbCircuits;
    }
}


