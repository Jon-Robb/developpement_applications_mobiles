package com.example.tp1clonespotify;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.Hashtable;
import java.util.Vector;

public class PlaylistMenuActivity extends AppCompatActivity {

    private ListView liste;
    private Vector<Hashtable<String,String>> vec;
    private ListePlaylist listePlaylist;
    Ecouteur ec;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_menu);
        liste = findViewById(R.id.liste);

        listePlaylist = new ListePlaylist();
        vec = new Vector<>();

        vec = listePlaylist.getVecHash();

        String strings[] = {"nom", "nbChansons", "duree"};
        int integers[] = {R.id.plName, R.id.nbChansons, R.id.duree};

        SimpleAdapter simpleAdapter = new SimpleAdapter(PlaylistMenuActivity.this, vec, R.layout.liste_enfant, strings, integers);

        liste.setAdapter(simpleAdapter);

        ec = new Ecouteur();

        liste.setOnItemClickListener(ec);


    }

    public class Ecouteur implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent retour = new Intent();
            if (position == 0){
                retour.putExtra("uri", "spotify:playlist:2I9t0VoXbhjgCwlQ4LasO9");
            }
            else if (position == 1){
                retour.putExtra("uri", "spotify:playlist:1bu27chTNb5Jj4XzSEhf1z");
            }
            else{
                retour.putExtra("uri", "spotify:playlist:7GIc01qZ7ruStQCG2ZTdUM");
            }
            setResult(24, retour);
            finish();
        }
    }
}