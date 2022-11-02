package com.example.annexe_1_shared_preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashSet;

public class AjouterActivity extends AppCompatActivity {

    Button btnAjouterAjouter;
    EcouteurAjouter ec;
    EditText champMemo;
    String liste = "liste";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);

        btnAjouterAjouter = findViewById(R.id.btnAjouterAjouter);
        champMemo = findViewById(R.id.champMemo);

        ec = new EcouteurAjouter();
        btnAjouterAjouter.setOnClickListener(ec);

    }

    public class EcouteurAjouter implements View.OnClickListener{

        @Override
        public void onClick(View source) {

                    String memo = champMemo.getText().toString();

//                    Aller chercher ou créer le fichier SharedPreferences, MODE_PRIVATE : propre à notre app.
                    SharedPreferences memoSauves = getSharedPreferences(liste, Context.MODE_PRIVATE);
//                    Pour écrire dans le fichier
                    SharedPreferences.Editor editor = memoSauves.edit();
//                    Récupérer les mémos déja dans l'ensemble de Strings 'ensemble', s'il n'existe pas, va le créer vide.
                    HashSet<String> ensembleMemos = (HashSet<String>) memoSauves.getStringSet("ensemble", new HashSet<>());

//                    Android ne peut pas modifier directement l'instance retournée, car l'intégrité des données n'est pas garantie
//                    On va donc se créer une copie locale de notre HashSet
                    HashSet<String> copieLocale = new HashSet<>(ensembleMemos);
                    copieLocale.add(memo);
                    editor.putStringSet("ensemble", copieLocale);
                    editor.commit();
                    finish();




        }
    }
}