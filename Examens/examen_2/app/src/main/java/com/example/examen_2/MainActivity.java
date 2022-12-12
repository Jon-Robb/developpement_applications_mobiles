package com.example.examen_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.Hashtable;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private TextView champsAchat;
    private Button btnCommander;
    private ListView liste;
    private Stock instance;
    private Vector<VehiculeHyundai> vecChar;
    private Vector<Hashtable<String, String>> vecHash;
    private Commande commande;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        champsAchat = findViewById(R.id.champAchats);
        btnCommander = findViewById(R.id.btnCommander);
        liste = findViewById(R.id.liste);
        commande = new Commande();
        DecimalFormat df = new DecimalFormat("###,###.00$");

        instance = Stock.getInstance();
        vecChar = instance.retournerListe();

        vecHash = new Vector<>();

        FileInputStream fis = null;
        try {
            fis = openFileInput("fichier.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            commande = (Commande) ois.readObject();
            ois.close();
            Toast.makeText(MainActivity.this, "Poursuivez vos achats", Toast.LENGTH_LONG ).show();

        }
        catch (IOException | ClassNotFoundException e){
            Toast.makeText(MainActivity.this, "Pas de commande active", Toast.LENGTH_LONG ).show();
        }

        for (VehiculeHyundai voiture : vecChar){
            Hashtable hashtable = new Hashtable();
            hashtable.put("nom", voiture.getNom());
            hashtable.put("alimentation", voiture.getAlimentation());
            hashtable.put("prix", df.format(voiture.getPrix()));
            vecHash.add(hashtable);
        }

        String[] strings = {"nom", "alimentation", "prix"};
        int[] integers = {R.id.nom, R.id.alimentation, R.id.prix};

        SimpleAdapter sa = new SimpleAdapter(MainActivity.this, vecHash, R.layout.liste_enfant, strings, integers);
        liste.setAdapter(sa);

        liste.setOnItemClickListener((AdapterView<?> adapterView, View view, int i, long l) ->{
            VehiculeHyundai vehicule = instance.trouverObjet(vecHash.get(i).get("nom"), vecHash.get(i).get("alimentation"));
            if (commande.getNbAchat() < commande.getNbMax()){
                champsAchat.append(vehicule.getNom() + " - " + vehicule.getAlimentation() + "\n");
            }
            commande.ajouterItem(MainActivity.this, vehicule);
        });

        btnCommander.setOnClickListener(btn -> {
            if (commande .getVecVehicule().size() > 0){
                FileOutputStream fos = null;
                try {
                    fos = openFileOutput("fichier.ser", Context.MODE_PRIVATE);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(commande);
                    oos.close();
                    Intent i = new Intent(MainActivity.this, TotalActivity.class);
                    startActivity(i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                Toast.makeText(MainActivity.this, "Votre commande est vide", Toast.LENGTH_LONG).show();
            }

        });

    }
}