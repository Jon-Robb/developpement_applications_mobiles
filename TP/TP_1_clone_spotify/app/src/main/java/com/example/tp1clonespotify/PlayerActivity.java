package com.example.tp1clonespotify;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.net.Uri;

import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.client.CallResult;
import com.spotify.protocol.types.Track;


public class PlayerActivity extends AppCompatActivity {

    private ImageView imgStartPause, imgRewind, imgSkipBack, imgSkipNext, imgReturn, imgFastForward;
    private SpotifyDiffuseur instance;
    private TextView artistName, songTitle, albumName, timeElapsed, timeLeft;
    private boolean isPlayBtn = true;
    private ImageView songImage;
    private SeekBar seekBar;

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
        seekBar = findViewById(R.id.seekBar);
        instance = SpotifyDiffuseur.getInstance(PlayerActivity.this);
        imgStartPause.setImageDrawable(getResources().getDrawable(R.drawable.pause));
        timeElapsed = findViewById(R.id.timeElapsed);
        timeLeft = findViewById(R.id.timeLeft);

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onStart() {
        super.onStart();

        instance.seConnecter(playlist);

        if (instance.getvPlayerState() != null){
            if (instance.getvPlayerState().track != null){
                seekBar.setMax((int) instance.getvPlayerState().track.duration / 1000);
                seekBar.setProgress((int)instance.getvPlayerState().track.duration / 1000);
                timeLeft.setText(String.valueOf((int)instance.getvPlayerState().track.duration));
            }
        }


        imgStartPause.setOnClickListener(source -> {
            if (!isPlayBtn){
                instance.resume();
                imgStartPause.setImageDrawable(getResources().getDrawable(R.drawable.pause));
                isPlayBtn = true;
                timeLeft.setText(String.valueOf((int)instance.getvPlayerState().track.duration));
                instance.rafraichir();

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
            System.out.println("clic");
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


