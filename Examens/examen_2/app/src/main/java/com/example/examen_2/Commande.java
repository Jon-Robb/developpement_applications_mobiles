package com.example.examen_2;

import android.content.Context;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Vector;

public class Commande implements Serializable {

    private int nbMax = 2;
    private Vector<VehiculeHyundai> vecVehicule = new Vector<>();
    private double prixTotal = 0;
    private int nbAchat = 0;

    public Commande() {}

    public void ajouterItem(Context context, VehiculeHyundai vehiculeHyundai){

        if (nbAchat < nbMax){
            this.vecVehicule.add(vehiculeHyundai);
            ++nbAchat;
        }
        else{
            Toast.makeText(context, "Vous ne pouvez pas acheter plus de deux véhicules à la fois", Toast.LENGTH_LONG).show();
        }

    }
    public double  calculerCommande(){

        for (VehiculeHyundai vehicule : this.vecVehicule){
            this.prixTotal += vehicule.getPrix();
        }
        return this.getPrixTotal();
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public Vector<VehiculeHyundai> getVecVehicule() {
        return vecVehicule;
    }

    public int getNbAchat() {
        return nbAchat;
    }

    public int getNbMax() {
        return nbMax;
    }
}

