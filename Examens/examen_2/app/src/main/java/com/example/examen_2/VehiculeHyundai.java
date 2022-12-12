package com.example.examen_2;


import java.io.Serializable;

public class VehiculeHyundai implements Serializable {
    private String nom;
    private String alimentation;
    private int prix;
    private int qte;

    public VehiculeHyundai ( String nom, String alimentation, int qte)
    {
        this.nom = nom;
        this.alimentation = alimentation;
        this.qte = qte;
    }

    public String getNom() {
        return nom;
    }



    public String getAlimentation() {
        return alimentation;
    }


    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQte() {
        return qte;
    }
}
