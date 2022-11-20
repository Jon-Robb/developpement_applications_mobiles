package com.example.annexe_3_serialisation;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DebutActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> lanceur;
    Button btnConnaissance;
    TextView champBonjour;
    Ecouteur ec;
    Utilisateur u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConnaissance = findViewById(R.id.btnConnaissance);
        champBonjour = findViewById(R.id.champBonjour);

        ec = new Ecouteur();
        btnConnaissance.setOnClickListener(ec);

        lanceur = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new CallRetour());

//        try{
//            u = (Utilisateur) savedInstanceState.getSerializable("objetU");
//            champBonjour.setText("Bonjour ! " + u.getPrenom() + " " + u.getNom());
//        }
//        catch (NullPointerException nullPointerException){
//            champBonjour.setText("Bonjour");
//        }

    }

    private class Ecouteur implements View

            .OnClickListener{

        @Override
        public void onClick(View source) {

            lanceur.launch(new Intent(DebutActivity.this, InfoActivity.class));

        }
    }

    private class CallRetour implements ActivityResultCallback<ActivityResult>{


        @SuppressLint("SetTextI18n")
        @Override
        public void onActivityResult(ActivityResult result){
//          C'est ici que le boomerang revient avec les informations (nom, prénom)
//          On transtype les donnees pour les transformer en objet
           if (result.getResultCode() == 24){

               assert result.getData() != null;
               u = (Utilisateur) result.getData().getSerializableExtra("u");
               champBonjour.setText("Bonjour ! " + u.getPrenom() + " " + u.getNom());
           }
        }
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putSerializable("objetU", u);
//        Toast t = Toast.makeText(this, "Passe dans le onSaveInstanceState", Toast.LENGTH_LONG);
//        t.show();
//    }
}