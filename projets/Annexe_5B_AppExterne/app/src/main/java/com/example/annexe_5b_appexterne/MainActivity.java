package com.example.annexe_5b_appexterne;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;
    Button btnLivre, btnHawkes, btnMarie, btnCourriel, btnPhoto;
    ImageView image;
    LinearLayout conteneur;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCourriel = findViewById(R.id.btnCourriel);
        btnHawkes = findViewById(R.id.btnHawkes);
        btnLivre = findViewById(R.id.btnLivre);
        btnMarie = findViewById(R.id.btnMarie);
        btnPhoto = findViewById(R.id.btnPhoto);
        conteneur = findViewById(R.id.conteneur);

        ec = new Ecouteur();

        for(int i = 0; i < conteneur.getChildCount() - 1; i++){
            conteneur.getChildAt(i).setOnClickListener(ec);
        }
    }

    private class Ecouteur implements View

            .OnClickListener{

        @Override
        public void onClick(View source) {

            if (source == btnLivre){

            }
            else if (source == btnMarie){

            }
            else if (source == btnHawkes){

            }
            else if (source == btnCourriel){

            }
            else{

            }
        }
    }

}