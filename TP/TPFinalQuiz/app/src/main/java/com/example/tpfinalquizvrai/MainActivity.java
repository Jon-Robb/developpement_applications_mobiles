package com.example.tpfinalquizvrai;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    private String url = "https://api.spotify.com/v1/artists?ids=4Z8W4fKeB5YxbusRsdQVPb%2C12Chz98pHFMPJEknJQMWvI";
    private RequeteJSON requete;
    private RequestsSingleton instance;
    private NetworkImageView imgChanson;
    private RequeteListener requeteListener;
    private RequeteTermineeListener requeteTermineeListener;
    private Button btnStart;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(source -> {
            startActivity(new Intent(MainActivity.this, ConteneurFragmentsActivity.class));
        });

        instance = RequestsSingleton.getInstance(MainActivity.this);

        requeteTermineeListener = response -> {

            Gson gson = new GsonBuilder().create();
//            Artiste artiste = gson.fromJson(String.valueOf(response), Artiste.class);
//            System.out.println(artiste.name);
//            System.out.println(artiste.genres);
//            System.out.println(artiste.followers.total);
//            System.out.println(artiste.popularity);
//            System.out.println(artiste.randomFromVecGenres());
            ArtistesVecHelper artistesVecHelper = gson.fromJson(String.valueOf(response), ArtistesVecHelper.class);
            for (Artiste artiste : artistesVecHelper.artists){
                System.out.println(artiste.name + " : " + artiste.followers.total);
            }

        };

        requeteListener = new RequeteListener(requeteTermineeListener);
        requete = new RequeteJSON();

        requete.faireRequete(MainActivity.this, Request.Method.GET, url, requeteListener);

    }
}