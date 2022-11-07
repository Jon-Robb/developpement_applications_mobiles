package com.example.annexe_3_serialisation;

import java.io.Serializable;

public class Utilisateur implements Serializable {

//    Quand c'est une classe de l'API, on peut voir si elle implémente Serializable
//    Lorsqu'on créé une classe nous même, il faut la rendre serializable avec l'implement
//    Si un membre de la classe n'est pas sérializable, notre classe ne pourra pas l'être malgré l'implement

    private String prenom;
    private String nom;

    public Utilisateur(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
