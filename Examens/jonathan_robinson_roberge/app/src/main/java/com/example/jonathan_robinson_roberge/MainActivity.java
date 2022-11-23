package com.example.jonathan_robinson_roberge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {

    Button btnStats;
    TextView champNbCircuits, champMoyenneBaton;
    EditText champJoueur;
    Ecouteur ec;
    Singleton instance;
    Hashtable<String, Joueur> hashtable;
    DecimalFormat troisDigits, aucunDigit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStats = findViewById(R.id.btnStats);
        champJoueur = findViewById(R.id.champJoueur);
        champNbCircuits = findViewById(R.id.champNbCircuits);
        champMoyenneBaton = findViewById(R.id.champMoyenne);
        troisDigits = new DecimalFormat("#.000");
        aucunDigit = new DecimalFormat("#");


        instance = Singleton.getInstance(MainActivity.this);

        try {
            hashtable = instance.getJoueurs();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ec = new Ecouteur();
        btnStats.setOnClickListener(ec);
    }


    private class Ecouteur implements View

            .OnClickListener{

        @Override
        public void onClick(View view) {

            if (!champJoueur.getText().toString().equals(""))
                if (hashtable.containsKey(champJoueur.getText().toString())){
                    Joueur joueur = hashtable.get(champJoueur.getText().toString());
                    assert joueur != null;
                    champMoyenneBaton.setText("Moyenne au bâton : " + troisDigits.format(joueur.getmoyenneBaton()));
                    champNbCircuits.setText("Nombre de circuits : " + aucunDigit.format(joueur.getNbCircuits()));
                }
                else{
                    Toast t = Toast.makeText(MainActivity.this, "Mauvais nom de joueur", Toast.LENGTH_LONG);
                    t.show();
                    champJoueur.setText("");
                    champJoueur.setHint("Entrez le nom d'un joueur");
                }

        }
    }


}