package com.example.projetpetitionnaire;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;


public class DepartActivity extends AppCompatActivity {


    Button boutonRecup, boutonStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depart);

        boutonRecup = findViewById(R.id.boutonRecup);
        boutonStart = findViewById(R.id.boutonStart);

        Ecouteur ec = new Ecouteur();
        boutonStart.setOnClickListener(ec);
        boutonRecup.setOnClickListener(ec);
    }

    private class Ecouteur implements View.OnClickListener
    {

        @Override
        public void onClick(View view) {
            if ( view == boutonRecup)
            {
                FileInputStream fis = null;
                try {
                    fis = openFileInput("fichier.ser");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    Membre m = (Membre) ois.readObject();
                    ois.close();
                    Toast.makeText(DepartActivity.this, m.getPrenom() + " " + m.getNom() + " " + m.getAge() + " " + m.getObjectif() + " " + m.getDegre(), Toast.LENGTH_LONG).show();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(DepartActivity.this, "Vous n'êtes pas membre", Toast.LENGTH_SHORT).show();
                }


            }
            else  // boutonStart
            {
                Intent i = new Intent(DepartActivity.this, ConteneurFragmentsActivity.class);
                startActivity(i);
            }
        }
    }
}