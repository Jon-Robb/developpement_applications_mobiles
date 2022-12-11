package com.example.annexe_7b_animationboutons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Intent i;
    Button gaucheDroitebtn, btnTitre, btnSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gaucheDroitebtn = findViewById(R.id.btnGaucheDroite);
        btnTitre = findViewById(R.id.btnTitre);
        btnSplash = findViewById(R.id.btnSplash);

//       Selon le bouton, on va vers l activity correspondante

        gaucheDroitebtn.setOnClickListener(source ->{
            i = new Intent(MainActivity.this, GaucheDroiteActivity.class);
            startActivity(i);
        });

        btnTitre.setOnClickListener(source ->{
            i = new Intent(MainActivity.this, AnimationTitreActivity.class);
            startActivity(i);
        });

        btnSplash.setOnClickListener(source ->{
            i = new Intent(MainActivity.this, SplashActivity.class);
            startActivity(i);
        });





    }
}