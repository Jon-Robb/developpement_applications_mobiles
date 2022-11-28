package com.example.tp1clonespotify;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;

import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.client.CallResult;
import com.spotify.protocol.types.Track;


public class PlayerActivity extends AppCompatActivity {

    private ImageView imgStartPause, imgRewind, imgSkipBack, imgSkipNext, imgReturn, imgFastForward;
    private SpotifyDiffuseur instance;
    private TextView artistName, songTitle, albumName;
    private boolean isPlayBtn = true;
    private ImageView songImage;

    private String playlist = "spotify:playlist:2I9t0VoXbhjgCwlQ4LasO9";

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        imgStartPause = findViewById(R.id.imgStartPause);
        imgRewind = findViewById(R.id.imgRewind);
        imgSkipBack = findViewById(R.id.imgSkipBack);
        imgSkipNext  = findViewById(R.id.imgSkipNext);
        artistName = findViewById(R.id.playlistName);
        songTitle = findViewById(R.id.songTitle);
        albumName = findViewById(R.id.songArtist);
        songImage = findViewById(R.id.songImg);
        instance = SpotifyDiffuseur.getInstance(PlayerActivity.this);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onStart() {
        super.onStart();

        instance.seConnecter();
        imgStartPause.setOnClickListener(source -> {
            if (!isPlayBtn){
                instance.play(playlist);

                instance.rafraichir();
                imgStartPause.setImageDrawable(getResources().getDrawable(R.drawable.pause));
                isPlayBtn = true;
            }
            else{
                instance.pause();
                isPlayBtn = false;
                imgStartPause.setImageDrawable(getResources().getDrawable(R.drawable.play));
            }
        });

        imgSkipNext.setOnClickListener(source -> {
            instance.next();
        });

        imgSkipBack.setOnClickListener(source -> {
            instance.back();
        });
    }

    public void rafraichir(Chanson chanson, Bitmap imgChanson){
        artistName.setText(chanson.getArtiste().getNom());
        songTitle.setText(chanson.getNom());
        albumName.setText(chanson.getAlbum());
        songImage.setImageBitmap(imgChanson);
    }

    @Override
    protected void onStop() {
        super.onStop();
        instance.pause();
        instance.seDeconnecter();
    }
}


