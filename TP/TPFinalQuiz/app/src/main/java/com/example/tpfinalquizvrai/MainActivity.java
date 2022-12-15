package com.example.tpfinalquizvrai;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    private String url = "https://api.spotify.com/v1/artists/4Z8W4fKeB5YxbusRsdQVPb";
    private RequeteJSON requete;
    private RequestsSingleton instance;
    private NetworkImageView imgChanson;
    private RequeteListener requeteListener;
    private RequeteTermineeListener listener;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgChanson = findViewById(R.id.imgChanson);

        instance = RequestsSingleton.getInstance(MainActivity.this);

        listener = response -> {
            Gson gson = new GsonBuilder().create();
            Artiste artiste = gson.fromJson(String.valueOf(response), Artiste.class);
            System.out.println(artiste.name);
            System.out.println(artiste.genres);
            System.out.println(artiste.followers.total);
            System.out.println(artiste.popularity);

            imgChanson.setImageUrl(artiste.images.get(0).getUrl(), instance.getImageLoader());
        };

        requeteListener = new RequeteListener(listener);
        requete = new RequeteJSON();


        requete.faireRequete(MainActivity.this, Request.Method.GET, url, requeteListener);


    }

}