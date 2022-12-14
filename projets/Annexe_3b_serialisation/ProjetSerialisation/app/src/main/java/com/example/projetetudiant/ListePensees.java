package com.example.projetetudiant;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.Vector;

public class ListePensees {

    private static ListePensees instance;
    private Vector<String> lesPensees;
    private Context context;


//    Notre liste de pensee style singleton
    public static ListePensees getInstance(Context context){
        if(instance == null){
            instance = new ListePensees(context);
        }
        return instance;
    }

//    creation de la liste avec une pensee ajoutee lors de sa premiere creation
   private ListePensees (Context context){
        this.context = context;
        lesPensees = new Vector<>();
        lesPensees.add("Vaut mieux avoir du coeur que d'avoir raison");
   }

   public void ajouterPensee(String pensee){
        lesPensees.add(pensee);
   }

   public String getRandomPensee(){

        Random rand = new Random();
        int i = rand.nextInt(lesPensees.size());
        return this.lesPensees.elementAt(i);
   }

    public Vector<String> getLesPensees() {
        return lesPensees;
    }

//    Pour serialiser dans un fichier .ser
//     Memes etapes que pour ecrire dans un fichier mais la cest un objet serialiser qu on met en
//      utilisant un objectoutputStream
    public void serialiserListe() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {

            fos = context.openFileOutput("test.ser", Context.MODE_PRIVATE);
            //Buffer spécial pour les objets
            oos = new ObjectOutputStream(fos);
            oos.writeObject(lesPensees);

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void recupererDuFichierDeSerialisation() throws IOException, ClassNotFoundException {

//        Meme etapes que pour un fichier texte mais ici on utilise un ObjectInputStream
        FileInputStream fis = context.openFileInput("test.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        lesPensees = (Vector<String>)ois.readObject();
        ois.close();

    }





}
