package com.example.jonathan_robinson_roberge;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

public class Singleton {

    private Context context;
    private static Singleton instance;

    public static Singleton getInstance(Context context){
        if(instance == null){
            instance = new Singleton(context);
        }
        return instance;
    }

    private Singleton(Context context){
        this.context = context;
    }

    public Hashtable<String, Joueur> getJoueurs() throws FileNotFoundException {

        FileInputStream fis = context.openFileInput("stats.txt");

        Scanner sc = new Scanner(fis);
        Hashtable<String, Joueur> hashtable = new Hashtable<>();
        sc.useDelimiter(",|\\n\\r");

        while(sc.hasNext()){

            Joueur joueur = new Joueur(sc.next(), Float.parseFloat(sc.next()), Float.parseFloat(sc.next()), Float.parseFloat(sc.next()),Float.parseFloat(sc.next()), Integer.parseInt(sc.next()));
            hashtable.put(joueur.getNom().trim(), joueur);
        }

        sc.close();

        return hashtable;

    }


}

