package com.example.tpfinalquizvrai;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private NetworkImageView imgChanson;
    private Score.Builder s;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s = new Score.Builder();

        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(source -> {
            startActivity(new Intent(MainActivity.this, ConteneurFragmentsActivity.class));
        });


        //            Artiste artiste = gson.fromJson(String.valueOf(response), Artiste.class);
        //            System.out.println(artiste.name);
        //            System.out.println(artiste.genres);
        //            System.out.println(artiste.followers.total);
        //            System.out.println(artiste.popularity);
        //            System.out.println(artiste.randomFromVecGenres());
        RequeteTermineeListener requeteTermineeListener = response -> {

            Gson gson = new GsonBuilder().create();
//            Artiste artiste = gson.fromJson(String.valueOf(response), Artiste.class);
//            System.out.println(artiste.name);
//            System.out.println(artiste.genres);
//            System.out.println(artiste.followers.total);
//            System.out.println(artiste.popularity);
//            System.out.println(artiste.randomFromVecGenres());
            Artistes artistesVecHelper = gson.fromJson(String.valueOf(response), Artistes.class);
            for (Artiste artiste : artistesVecHelper.artists) {
                System.out.println(artiste.name + " : " + artiste.followers.total);
            }

        };

        RequeteListener requeteListener = new RequeteListener(requeteTermineeListener);
        RequeteJSON requete = new RequeteJSON();

        String url = "https://api.spotify.com/v1/artists?ids=4Z8W4fKeB5YxbusRsdQVPb%2C12Chz98pHFMPJEknJQMWvI";
        requete.faireRequete(MainActivity.this, Request.Method.GET, url, requeteListener);

    }

    public Score.Builder getS() {
        return s;
    }


}