package com.example.projetetudiant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AjouterActivity extends AppCompatActivity {

    Button btnRetour;
    EditText champAjouterPensee;
    Ecouteur ec;
    ListePensees instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);

        btnRetour = findViewById(R.id.btnRetour);
        champAjouterPensee = findViewById(R.id.champAjouterPensee);
        ec = new Ecouteur();
        btnRetour.setOnClickListener(ec);
        instance = ListePensees.getInstance(AjouterActivity.this);
    }

    private class Ecouteur implements View

            .OnClickListener{

        @Override
        public void onClick(View source) {
            if (!champAjouterPensee.equals("")){

//                Lors de l appuie sur le bouton retour, on ajoute la pensee dans notre liste grace a notre singleton
                instance.ajouterPensee(champAjouterPensee.getText().toString());
//                Pour le plaisir j envoie un texte qui devra etre ecrit lors du toast
                Intent retour = new Intent();
                retour.putExtra("texte", "Pensée ajoutée!");
                setResult(24, retour);
            }
            finish();
        }
    }
}