package com.example.annexe_2_fichiers_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;
    Button btnAjouter, btnAfficher, btnQuitter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAjouter = findViewById(R.id.btnAjouter);
        btnAfficher = findViewById(R.id.btnAfficher);
        btnQuitter = findViewById(R.id.btnQuitter);

        ec = new Ecouteur();
        btnAjouter.setOnClickListener(ec);
        btnAfficher.setOnClickListener(ec);
        btnQuitter.setOnClickListener(ec);



    }

    public class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View source) {
            if (source == btnAjouter){
                intent = new Intent(MainActivity.this, AjouterActivity.class);
                startActivity(intent);
            }
            else if (source == btnAfficher){
                intent = new Intent(MainActivity.this, ListeActivity.class);
                startActivity(intent);
            }
            else{
                finish();
            }
        }
    }

}